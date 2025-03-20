package org.qinlinj.practical.underground;

public class Start {
    private int startTime;
    private String station;

    public Start(String station, int time) {
        this.station = station;
        this.startTime = time;
    }

    public String getStation() {
        return station;
    }

    public int getStartTime() {
        return startTime;
    }
}
