package org.qinlinj.practical.underground;

import java.util.*;

/**
 * A system for tracking and analyzing passenger journeys in an underground transport network.
 * This class allows check-in and check-out operations and provides average travel time calculation
 * between different stations.
 */
public class UndergroundSystem {
    /**
     * Data structures:
     * - startInfo: Maps passenger IDs to their check-in information
     * - table: Stores travel statistics for each station pair
     *
     * Both are implemented as HashMap with O(1) average time complexity for operations
     */
    /**
     * Maps passenger IDs to their check-in information (station and time)
     */
    private Map<Integer, Start> startInfo;

    /**
     * Stores travel statistics (sum of travel times and count) for each station pair
     */
    private Map<StartEnd, SumAmount> table;

    /**
     * Initializes a new UndergroundSystem with empty data structures.
     * Constructor initializes the HashMaps.
     * Time Complexity: O(1)
     */
    public UndergroundSystem() {
        startInfo = new HashMap<>();
        table = new HashMap<>();
    }

    /**
     * Records a passenger check-in event at a station.
     * Records a check-in event.
     * HashMap put operation has O(1) average time complexity.
     * Time Complexity: O(1)
     *
     * @param id          The passenger's unique identifier
     * @param stationName The name of the station where check-in occurs
     * @param t           The time when the check-in occurs
     */
    public void checkIn(int id, String stationName, int t) {
        startInfo.put(id, new Start(stationName, t));
    }

    /**
     * Records a passenger check-out event and updates travel statistics.
     * Calculates the travel time between check-in and check-out stations and updates
     * the cumulative statistics for this station pair.
     * Operations:
     * - HashMap get: O(1)
     * - Creating StartEnd object: O(1)
     * - HashMap getOrDefault: O(1)
     * - Setting values and calculations: O(1)
     * - HashMap put: O(1)
     * Time Complexity: O(1)
     *
     * @param id          The passenger's unique identifier
     * @param stationName The name of the station where check-out occurs
     * @param t           The time when the check-out occurs
     */
    public void checkOut(int id, String stationName, int t) {
        Start start = startInfo.get(id);

        StartEnd key = new StartEnd(start.getStation(), stationName);
        SumAmount sumAmount = table.getOrDefault(key, new SumAmount(0, 0));
        sumAmount.setAmount(sumAmount.getAmount() + 1);
        sumAmount.setSum(sumAmount.getSum() + (t - start.getStartTime()));

        table.put(key, sumAmount);
    }

    /**
     * Calculates the average travel time between two stations.
     * Calculates the average travel time.
     * Operations:
     * - Creating StartEnd object: O(1)
     * - HashMap get: O(1)
     * - Arithmetic operations: O(1)
     * Time Complexity: O(1)
     *
     * @param startStation The name of the starting station
     * @param endStation   The name of the ending station
     * @return The average travel time between the specified stations
     */
    public double getAverageTime(String startStation, String endStation) {
        StartEnd key = new StartEnd(startStation, endStation);

        SumAmount sumAmount = table.get(key);
        int sum = sumAmount.getSum();
        int amount = sumAmount.getAmount();
        return 1.0 * sum / amount;
    }
}