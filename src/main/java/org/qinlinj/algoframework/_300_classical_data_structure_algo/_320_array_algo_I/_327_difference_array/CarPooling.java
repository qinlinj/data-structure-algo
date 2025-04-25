package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._327_difference_array;

/**
 * Car Pooling (LeetCode 1094)
 * <p>
 * Key Points:
 * 1. Another application of the difference array technique
 * 2. Each trip represents passengers getting on at one stop and off at another
 * 3. Uses difference array to track passenger count changes at each location
 * 4. After processing all trips, checks if capacity is ever exceeded
 * 5. Time Complexity: O(n + m) where n is the number of stops and m is the number of trips
 * 6. Space Complexity: O(n) for the difference array
 */
public class CarPooling {
    /**
     * LeetCode 1094: Car Pooling
     * <p>
     * You are driving a vehicle that has capacity empty seats initially.
     * The vehicle only drives east (i.e., it cannot turn around and drive west).
     * <p>
     * You are given an array trips where trips[i] = [numPassengers_i, from_i, to_i]:
     * - numPassengers_i is the number of passengers in the ith trip
     * - from_i is the starting location of the ith trip
     * - to_i is the ending location of the ith trip
     * <p>
     * The locations are along the x-axis, and eastward movement means an increasing value of x.
     * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
     *
     * @param trips    Array of trips where each trip is [numPassengers, from, to]
     * @param capacity Maximum number of passengers the vehicle can carry
     * @return Whether all trips can be completed without exceeding capacity
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // Maximum number of stops is 1001 (0 to 1000)
        int[] passengerChanges = new int[1001];

        // Create difference array utility
        Difference diff = new Difference(passengerChanges);

        // Process each trip
        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            // Passengers are in the car from 'from' to 'to-1'
            // At 'to', they get off the car
            diff.increment(from, to - 1, numPassengers);
        }

        // Get the passenger count at each stop
        int[] passengerCounts = diff.result();

        // Check if the capacity is ever exceeded
        for (int count : passengerCounts) {
            if (count > capacity) {
                return false;
            }
        }

        return true;
    }

    /**
     * Utility class for difference array operations
     */
    static class Difference {
        // The difference array
        private int[] diff;

        /**
         * Initializes the difference array from the input array
         */
        public Difference(int[] nums) {
            if (nums.length == 0) {
                throw new IllegalArgumentException("Input array cannot be empty");
            }

            diff = new int[nums.length];
            diff[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * Increments all elements in the range [i, j] by val
         */
        public void increment(int i, int j, int val) {
            diff[i] += val;

            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /**
         * Reconstructs the original array after all operations
         */
        public int[] result() {
            int[] res = new int[diff.length];
            res[0] = diff[0];

            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }

            return res;
        }
    }
}
