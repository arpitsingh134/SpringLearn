
package com.java.learn.dsa.start;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PCWithLock {

    public static void main(String[] args) {
        PCWithLock pc = new PCWithLock();

        Thread producer = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }


    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();    // For producer
    private final Condition notEmpty = lock.newCondition();   // For consumer

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            lock.lock();
            try {
                while (buffer.size() == capacity) {
                    notFull.await(); // wait if buffer is full
                }
                System.out.println("Produced: " + value);
                buffer.add(value++);
                notEmpty.signal(); // signal consumer
            } finally {
                lock.unlock();
            }
            Thread.sleep(1000); // simulate delay
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (buffer.isEmpty()) {
                    notEmpty.await(); // wait if buffer is empty
                }
                int val = buffer.poll();
                System.out.println("Consumed: " + val);
                notFull.signal(); // signal producer
            } finally {
                lock.unlock();
            }
            Thread.sleep(1000); // simulate delay
        }
    }
}