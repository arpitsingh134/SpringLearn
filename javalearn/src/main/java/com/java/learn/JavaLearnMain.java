package com.java.learn;

public class JavaLearnMain {
    public static void main(String[] args) {

        MyHashMap<Integer,String> ourmap=new MyHashMap<>();

        ourmap.put(1,"a");
        ourmap.put(2,"b");
        ourmap.put(3,"c");
        ourmap.put(4,"d");
        ourmap.put(2,"e");
        ourmap.put(3,"f");



        System.out.println(ourmap.get(2));

    }
}