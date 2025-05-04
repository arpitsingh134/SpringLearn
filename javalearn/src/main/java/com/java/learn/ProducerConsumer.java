package com.java.learn;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {


    public static void main(String[] args) {
        ProducerConsumer producerConsumer=new ProducerConsumer();

        Thread producer =new Thread(()->{
            try {
                producerConsumer.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer =new Thread(()->{
            try {
                producerConsumer.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }

    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5;


    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (this) {
                while (queue.size() == capacity) {
                    wait();
                }
                System.out.println("Produced: " + value);
                queue.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (queue.size() == 0) {
                    wait();
                }
                int val = queue.poll();
                System.out.println("Consumed: " + val);
                notify();
                Thread.sleep(1000);
            }
        }

    }

}