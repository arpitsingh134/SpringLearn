package com.java.learn.dsa.start;


import java.util.TreeMap;

class MyCalendarTwo2 {

    private final TreeMap<Integer, Integer> map;

    public MyCalendarTwo2() {
        map = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        map.put(startTime, map.getOrDefault(startTime, 0) + 1);
        map.put(endTime, map.getOrDefault(endTime, 0) - 1);


        int activeBookings = 0;
        for (int events : map.values()) {
            activeBookings += events;
            if (activeBookings > 2) {
                //undo operation in case of removing entry form ma
                map.put(startTime, map.getOrDefault(startTime, 0) - 1);
                map.put(endTime, map.getOrDefault(endTime, 0) + 1);
                return false;
            }
        }
        return true;
    }
}
