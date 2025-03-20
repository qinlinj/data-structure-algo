package org.qinlinj.practical.underground;

import java.util.*;

public class UndergroundSystem {
    private Map<Integer, Start> startInfo;
    private Map<StartEnd, SumAmount> table;

    public UndergroundSystem() {
        startInfo = new HashMap<>();
        table = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {

    }

    public void checkOut(int id, String stationName, int t) {

    }

    public double getAverageTime(String startStation, String endStation) {
        // TODO
        throw new UnsupportedOperationException("Method not implement yet");
    }

}
