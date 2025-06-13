package com.java.learn;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test1 {
//    String="GeeksForGeeks";

    public static Queue<Integer> buffer = new LinkedList<>();
    int capacity = 5;

    static Object lock = new Object();

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();

    }

    public static char getChar(String s) {
        //order mention
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        boolean found = false;
        for (Map.Entry entry : map.entrySet()) {
            int value = (int) entry.getValue();
            if (found) return (char) entry.getKey();
            if (value == 1) found = true;
        }
        return 'a';
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (buffer.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.consume();
            lock.notify();
        }

        private void consume() {
            System.out.println(buffer.remove());
        }
    }

    static int i = 0;

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (buffer.size() == 5) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.produce(i++);
            lock.notify();
        }

        private void produce(int val) {
            buffer.offer(val);
        }
    }

    public static char secondNonRepeatingChar(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() == 1)
                .skip(1)
                .map(Map.Entry::getKey)
                .findFirst().orElse('\0');
    }

}

