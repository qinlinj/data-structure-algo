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

    /**
     * Constructs a new Start object with the specified station and time.
     *
     * @param station The name of the starting station
     * @param time    The time when the journey starts
     */
    public Start(String station, int time) {
        this.station = station;
        this.startTime = time;
    }

    /**
     * Returns the name of the starting station.
     *
     * @return The station name
     */
    public String getStation() {
        return station;
    }

    /**
     * Returns the time when the journey starts.
     *
     * @return The start time
     */
    public int getStartTime() {
        return startTime;
    }
}
