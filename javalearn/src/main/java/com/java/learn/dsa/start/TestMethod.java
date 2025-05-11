package com.java.learn.dsa.start;

import java.util.*;

public class TestMethod {

    public static void main(String[] args) {



        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
        System.out.println(singletonEnum);
        SingletonEnum singletonEnum1 = SingletonEnum.INSTANCE;
        System.out.println(singletonEnum1);

        OurGenericList<Integer> list = new OurGenericList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for (int i: list) System.out.println(i);


        List<Integer> list1 = new ArrayList<>();

    }

    public enum SingletonEnum {
        INSTANCE;

        public void show() {
            System.out.println("SingletonEnum is working!");
        }
    }

}