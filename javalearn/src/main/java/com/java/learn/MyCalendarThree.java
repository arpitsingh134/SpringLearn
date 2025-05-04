
package com.java.learn;

import java.util.TreeMap;

class MyCalendarThree {

    private final TreeMap<Integer, Integer> map;


    public MyCalendarThree() {
        map = new TreeMap<>();

    }

    public int book(int startTime, int endTime) {
        map.put(startTime, map.getOrDefault(startTime, 0) + 1);
        map.put(endTime, map.getOrDefault(endTime, 0) - 1);

        int maxActiveBookings = Integer.MIN_VALUE;
        int activeBookings = 0;
        for (int events : map.values()) {
            activeBookings += events;
            maxActiveBookings = Math.max(maxActiveBookings, activeBookings);

        }
        return maxActiveBookings;
    }
}