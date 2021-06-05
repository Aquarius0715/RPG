package com.aquarius0715.rpg;

import java.util.HashMap;
import java.util.Random;

public class Stats {
    public static HashMap<String, Integer> createStatus() {
        HashMap<String, Integer> status = new HashMap<String, Integer>();
        String[] stats_label = {"HP", "MP", "ATK", "DEF", "AGI"};
        for (String label : stats_label) {
            status.put(label, new Random().nextInt(100));
        }
        return status;
    }
}
