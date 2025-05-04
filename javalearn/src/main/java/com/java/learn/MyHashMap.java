package com.java.learn;


import java.util.*;

public class MyHashMap<K, V> implements Iterable<Map.Entry<K, V>> {


    private final int INITIAL_CAPACITY = 5;
    private List<MapNode<K, V>> bucket;
    private int capacity;
    private int size;

    public MyHashMap() {
        bucket = new ArrayList<>();
        capacity = INITIAL_CAPACITY;
        for (int i = 0; i < capacity; i++) {
            bucket.add(null);
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % capacity;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }


    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        MapNode<K, V> newNode = new MapNode<>(key, value);
        head = bucket.get(bucketIndex);
        newNode.next = head;
        //new key create and add head
        bucket.set(bucketIndex, newNode);

        //load factor
        double loadFactor = (1.0 * size) / bucket.size();
        System.out.println("inserting key" + key + " value" + value);
        System.out.println("load factor" + loadFactor);
        if (loadFactor > 0.7) {
            rehash();
        }
    }

    private void rehash() {
        System.out.println("rehasing buckets");
        List<MapNode<K, V>> temp = bucket;
        bucket = new ArrayList<>();
        capacity *= 2;
        for (int i = 0; i < capacity; i++) {
            bucket.add(null);
        }
        size = 0;
        for (int i = 0; i < temp.size(); i++) {
            MapNode<K, V> head = temp.get(i);
            while (head.next != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    public void remove(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = bucket.get(bucketIndex);
        MapNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    bucket.set(bucketIndex, head.next);
                } else {
                    prev.next = head.next;
                }
                head.next = null;
                size--;
                break;
            }
            prev = head;
            head = head.next;
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<Map.Entry<K, V>> {
        int bucketIndex = 0;
        MapNode<K, V> current = null;

        public MyHashMapIterator() {
            advanceToNextNonEmptyBucket();
        }

        private void advanceToNextNonEmptyBucket() {
            while (bucketIndex < bucket.size() && (current = bucket.get(bucketIndex)) == null) {
                bucketIndex++;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (current == null) throw new NoSuchElementException();

            Map.Entry<K, V> entry = new Entry<>(current.key, current.value);
            current = current.next;
            if (current == null) {
                bucketIndex++;
                advanceToNextNonEmptyBucket();
            }
            return entry;
        }
    }

    private class MapNode<K, V> {
        private final K key;
        private V value;
        private MapNode<K, V> next;


        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }


}




