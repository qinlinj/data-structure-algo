package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._872_gas_station_algorithm;

import java.util.*;

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

    /**
     * Detailed greedy simulation with step-by-step explanation
     */
    public static int greedyWithExplanation(int[] gas, int[] cost) {
        int n = gas.length;

        System.out.println("=== Greedy Algorithm Step-by-Step ===");
        System.out.println("Gas:  " + Arrays.toString(gas));
        System.out.println("Cost: " + Arrays.toString(cost));
        System.out.println();

        // Check total feasibility
        int totalGas = 0, totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        System.out.println("Total gas: " + totalGas + ", Total cost: " + totalCost);
        if (totalGas < totalCost) {
            System.out.println("No solution possible - insufficient total gas");
            return -1;
        }
        System.out.println("Solution exists - total gas >= total cost");
        System.out.println();

        // Greedy elimination process
        int tank = 0;
        int start = 0;

        System.out.println("Greedy Elimination Process:");
        System.out.println("Station | Gas | Cost | Tank | Start | Action");
        System.out.println("--------|-----|------|------|-------|--------");

        for (int i = 0; i < n; i++) {
            int prevTank = tank;
            tank += gas[i] - cost[i];

            String action;
            if (tank < 0) {
                action = "ELIMINATE stations " + start + " to " + i + ", new start = " + (i + 1);
                tank = 0;
                start = i + 1;
            } else {
                action = "Continue";
            }

            System.out.printf("   %2d   | %2d  |  %2d  |  %2d  |   %2d  | %s%n",
                    i, gas[i], cost[i], Math.max(tank, 0), start, action);
        }

        int result = start == n ? 0 : start;
        System.out.println();
        System.out.println("Final result: Station " + result);
        return result;
    }

    /**
     * Demonstrate the greedy insight with concrete example
     */
    public static void demonstrateGreedyInsight(int[] gas, int[] cost) {
        System.out.println("\n=== Demonstrating Greedy Insight ===");
        System.out.println();

        System.out.println("GREEDY INSIGHT:");
        System.out.println("If we start from station i and fail to reach station j,");
        System.out.println("then ANY station between i and j-1 will also fail to reach j.");
        System.out.println();

        // Find a failure point to demonstrate
        int n = gas.length;
        int tank = 0;
        int failureStart = -1, failureEnd = -1;

        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                failureStart = 0;
                failureEnd = i;
                break;
            }
        }

        if (failureStart != -1) {
            System.out.println("EXAMPLE from current data:");
            System.out.printf("Starting from station %d, we fail to reach station %d%n",
                    failureStart, failureEnd + 1);
            System.out.println();

            // Show why intermediate stations also fail
            System.out.println("Why intermediate stations also fail:");
            tank = 0;
            for (int i = failureStart; i <= failureEnd; i++) {
                tank += gas[i] - cost[i];
                System.out.printf("After station %d: tank = %d%n", i, tank);

                if (i < failureEnd) {
                    System.out.printf("If we started from station %d instead:%n", i + 1);
                    System.out.println("- We'd have tank = 0 at station " + (i + 1));
                    System.out.println("- But we currently have tank = " + tank + " > 0");
                    System.out.println("- Since current path fails, starting with tank = 0 must also fail");
                    System.out.println();
                }
            }

            System.out.println("CONCLUSION: Stations " + failureStart + " through " + failureEnd +
                    " can all be eliminated!");
            System.out.println("Next candidate: Station " + (failureEnd + 1));
        } else {
            System.out.println("No failure found in this example, but principle still applies.");
        }
    }
}
