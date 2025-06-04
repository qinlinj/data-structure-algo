package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._872_gas_station_algorithm;

public class _872_b_GasStationGraphApproach {
    /**
     * Mathematical Graph Solution
     * Find the starting station using minimum cumulative sum approach
     */
    public static int canCompleteCircuitGraph(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;        // Cumulative sum (fuel level changes)
        int minSum = 0;     // Minimum cumulative sum encountered
        int start = 0;      // Starting station (station after minimum point)

        for (int i = 0; i < n; i++) {
            // Calculate net change at station i
            sum += gas[i] - cost[i];

            // If current sum is lower than previous minimum
            if (sum < minSum) {
                // Update minimum sum and starting point
                minSum = sum;
                start = i + 1;  // Start from next station
            }
        }

        // Check if solution exists (total gas >= total cost)
        if (sum < 0) {
            return -1;  // Impossible to complete circuit
        }

        // Handle circular array (if start == n, wrap to 0)
        return start == n ? 0 : start;
    }

}
