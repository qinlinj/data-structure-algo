package org.qinlinj.practical.underground;

/**
 * Represents the starting point of a journey with time and station information.
 */
public class Start {
    /**
     * The time when the journey starts
     */
    private int startTime;

    /**
     * The name of the starting station
     */
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
