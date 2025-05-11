package com.java.learn.dsa.start;


import java.util.ArrayList;

class MyCalendarTwo {

    //list of all current bookings
    ArrayList<Bookings> bookings;
    //list of all current booking intersection points which overlapped twice.
    ArrayList<Bookings> doubleBookings;

    public MyCalendarTwo() {
        bookings = new ArrayList<>();
        doubleBookings = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {


        //check conflict in double bookings
        for (Bookings doubleBooking : doubleBookings) {
            if (isOverlapped(startTime, endTime, doubleBooking.start, doubleBooking.end)) {
                return false;
            }
        }

        // conflict in single booking and add this entry to double booking list for fuuture reference
        for (Bookings booking : bookings) {
            if (isOverlapped(startTime, endTime, booking.start, booking.end)) {
                doubleBookings.add(new Bookings(Math.max(startTime, booking.start), Math.min(endTime, booking.end)));
            }
        }
        bookings.add(new Bookings(startTime, endTime));

        return true;
    }

    private boolean isOverlapped(int s1, int e1, int s2, int e2) {
        return Math.max(s1, s2) < Math.min(e1, e2);
    }
}


class Bookings {
    int start;
    int end;

    public Bookings(int start, int end) {
        this.start = start;
        this.end = end;
    }
}