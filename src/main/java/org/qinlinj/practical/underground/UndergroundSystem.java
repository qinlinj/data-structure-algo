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
        startInfo.put(id, new Start(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Start start = startInfo.get(id);

        StartEnd key = new StartEnd(start.getStation(), stationName);
        SumAmount sumAmount = table.getOrDefault(key, new SumAmount(0, 0));
        sumAmount.setAmount(sumAmount.getAmount() + 1);
        sumAmount.setSum(sumAmount.getSum() + (t - start.getStartTime()));

        table.put(key, sumAmount);
    }

    public double getAverageTime(String startStation, String endStation) {
        StartEnd key = new StartEnd(startStation, endStation);

        SumAmount sumAmount = table.get(key);
        int sum = sumAmount.getSum();

        // TODO
        throw new UnsupportedOperationException("Method not implement yet");
    }

}
