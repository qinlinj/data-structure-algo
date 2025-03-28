package org.qinlinj.practical.underground;

import java.util.*;

/**
 * Represents a route segment with starting and ending stations.
 * This class is used to define a journey between two points in an underground transport system.
 */
public class StartEnd {
    /**
     * The name of the starting station
     */
    private String start;

    /**
     * The name of the ending station
     */
    private String end;

    /**
     * Constructs a new StartEnd object with specified starting and ending stations.
     *
     * @param start The name of the starting station
     * @param end   The name of the ending station
     */
    public StartEnd(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    /**
     * Compares this StartEnd object with another object for equality.
     * Two StartEnd objects are considered equal if they have the same starting and ending stations.
     *
     * @param o The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartEnd startEnd = (StartEnd) o;
        return Objects.equals(start, startEnd.start) &&
                Objects.equals(end, startEnd.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
