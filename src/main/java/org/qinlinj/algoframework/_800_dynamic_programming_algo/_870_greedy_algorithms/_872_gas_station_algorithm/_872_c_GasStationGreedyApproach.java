package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._872_gas_station_algorithm;

/**
 * GAS STATION PROBLEM - GREEDY ALGORITHM APPROACH
 * <p>
 * Core Greedy Insight:
 * If starting from station i, we can't reach station j, then no station between i and j
 * (inclusive of i, exclusive of j) can be a valid starting point.
 * <p>
 * Mathematical Reasoning:
 * - If from station i (tank=0) we reach station k with tank>0, but can't reach station j
 * - Then starting from station k (tank=0) definitely can't reach station j
 * - Because we had positive fuel when reaching k from i, but still failed
 * - Starting with tank=0 at k gives us even less fuel, so definitely will fail
 * <p>
 * Greedy Strategy:
 * 1. Try starting from station 0, simulate journey
 * 2. When we first encounter tank<0 at station j, we know:
 * - Stations 0 through current station can't be starting points
 * - The next valid candidate is station j+1
 * 3. Reset tank to 0 and continue from station j+1
 * 4. This eliminates multiple stations at once, reducing redundant calculations
 * <p>
 * Key Properties:
 * - Single pass through array: O(n) time complexity
 * - Constant extra space: O(1) space complexity
 * - Eliminates redundant computations from brute force approach
 * - Each station is visited at most twice (once in each direction)
 * <p>
 * Correctness Proof:
 * - Greedy choice eliminates impossible starting points efficiently
 * - If total gas >= total cost, solution exists and will be found
 * - The algorithm naturally finds the optimal starting point
 * <p>
 * Comparison with Graph Approach:
 * - Same time/space complexity as mathematical graph method
 * - Different conceptual framework but equivalent results
 * - Greedy focuses on elimination, graph focuses on translation
 * - Both reveal hidden structure to avoid O(n²) brute force
 */

public class _872_c_GasStationGreedyApproach {
    /**
     * Greedy Algorithm Solution
     * Eliminate impossible starting stations efficiently
     */
    public static int canCompleteCircuitGreedy(int[] gas, int[] cost) {
        int n = gas.length;

        // First check: if total gas < total cost, no solution exists
        int totalGas = 0, totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        if (totalGas < totalCost) {
            return -1;  // Impossible to complete circuit
        }

        // Greedy search for valid starting point
        int tank = 0;       // Current fuel in tank
        int start = 0;      // Current candidate starting station

        for (int i = 0; i < n; i++) {
            // Add gas and consume cost at station i
            tank += gas[i] - cost[i];

            if (tank < 0) {
                // Cannot reach station i+1 from current start
                // By greedy insight, stations start to i are all invalid
                // Next candidate is station i+1
                tank = 0;
                start = i + 1;
            }
        }

        // If we reach here with tank >= 0, start is valid
        // Handle circular array: if start == n, wrap to 0
        return start == n ? 0 : start;
    }

}
