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
