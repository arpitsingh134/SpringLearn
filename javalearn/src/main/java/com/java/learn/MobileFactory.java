package com.java.learn;

import java.util.HashMap;
import java.util.Map;

public class MobileFactory {
    static String type;

    private Map<String, OperatingSystem> map = new HashMap<>();

    public OperatingSystem getMobileFactory(String type) throws Exception {
        return map.getOrDefault(type,new Andriod());
    }

    public void register(OperatingSystem os, String type) {
        map.put(type, os);
    }
}