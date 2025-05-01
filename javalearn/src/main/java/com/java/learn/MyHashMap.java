package com.java.learn;


import java.util.Iterator;

public class MyHashMap<K,V> implements Iterable{

    private K key;
    private V value;

    public MyHashMap() {

    }

    public MyHashMap(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Iterator iterator() {
        return new MyHashMapItertor();
    }


    private class MyHashMapItertor  implements Iterator{


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }

}


