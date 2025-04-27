package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._335_binary_search_framework;

/**
 * Example 2: Shipping Packages within Days (LeetCode 1011)
 * <p>
 * Problem:
 * - Packages with weights must be shipped in order within D days
 * - Each day, we load packages in order onto the conveyor belt
 * - We can only load packages with a total weight ≤ the ship's capacity
 * - Find the minimum capacity of the ship needed to ship all packages within D days
 * <p>
 * Binary search solution:
 * - Variable x: Ship's capacity (weight units)
 * - Function f(x): The days needed to ship all packages with capacity x
 * - Target: The given D days
 * - Goal: Find the minimum capacity x where f(x) <= D
 */
public class _335_c_ShippingPackages {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _335_c_ShippingPackages solution = new _335_c_ShippingPackages();

        // Example 1
        int[] weights1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days1 = 5;
        System.out.println("Example 1: " + solution.shipWithinDays(weights1, days1)); // Expected: 15

        // Example 2
        int[] weights2 = {3, 2, 2, 4, 1, 4};
        int days2 = 3;
        System.out.println("Example 2: " + solution.shipWithinDays(weights2, days2)); // Expected: 6

        // Example 3
        int[] weights3 = {1, 2, 3, 1, 1};
        int days3 = 4;
        System.out.println("Example 3: " + solution.shipWithinDays(weights3, days3)); // Expected: 3
    }

    /**
     * Main function to solve the problem
     *
     * @param weights Array of package weights
     * @param days    Days to deliver all packages
     * @return Minimum capacity needed
     */
    public int shipWithinDays(int[] weights, int days) {
        // Initialize search boundaries
        int left = 0;
        int right = 0;

        // Find the maximum weight (minimum possible capacity)
        // and the sum of all weights (maximum possible capacity)
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        // Add 1 to right for exclusive bound
        right += 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (daysNeeded(weights, mid) <= days) {
                // If we can ship all packages within D days with capacity mid,
                // try to find a smaller capacity (search left)
                right = mid;
            } else {
                // Otherwise, we need a larger capacity (search right)
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Calculates how many days are needed to ship all packages with capacity x
     *
     * @param weights  Array of package weights
     * @param capacity Ship's capacity
     * @return Days needed to ship all packages
     */
    private int daysNeeded(int[] weights, int capacity) {
        int days = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            // If adding this package exceeds current capacity, start a new day
            if (currentLoad + weight > capacity) {
                days++;
                currentLoad = 0;
            }
            currentLoad += weight;
        }

        return days;
    }

    /**
     * Alternative implementation of daysNeeded function
     * This matches the original code example's approach
     */
    private int daysNeededAlternative(int[] weights, int capacity) {
        int days = 0;
        for (int i = 0; i < weights.length; ) {
            // Try to load as many packages as possible
            int currentCapacity = capacity;
            while (i < weights.length) {
                if (currentCapacity < weights[i]) break;
                else currentCapacity -= weights[i];
                i++;
            }
            days++;
        }
        return days;
    }
}
