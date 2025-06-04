package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._872_gas_station_algorithm;

/**
 * GAS STATION PROBLEM - BRUTE FORCE APPROACH
 * LeetCode 134: Gas Station
 * <p>
 * Problem Description:
 * There are n gas stations along a circular route. At station i, you can get gas[i] liters of gas.
 * To travel from station i to station i+1, you need cost[i] liters of gas.
 * You have a car with unlimited gas tank capacity, starting with empty tank.
 * Find the starting station index to complete the circuit, or return -1 if impossible.
 * <p>
 * Key Concepts:
 * 1. Circular Route: After station n-1, next station is 0
 * 2. Net Gain/Loss: At each station, net change = gas[i] - cost[i]
 * 3. Feasibility: Total gas must be >= total cost for solution to exist
 * 4. Brute Force: Try each station as starting point, simulate the journey
 * <p>
 * Brute Force Analysis:
 * - Time Complexity: O(n²) - try each of n starting points, simulate n steps each
 * - Space Complexity: O(1) - only use constant extra space
 * - Limitation: Inefficient for large inputs due to redundant calculations
 * <p>
 * Algorithm Steps:
 * 1. For each possible starting station (0 to n-1):
 * a. Initialize tank = 0
 * b. Simulate journey for n steps
 * c. At each step: add gas, subtract cost, check if tank >= 0
 * d. If complete circuit successfully, return starting station
 * 2. If no starting station works, return -1
 * <p>
 * This approach works but contains redundant computations that can be optimized
 * using mathematical insights or greedy strategies.
 */

import java.util.*;

public class _872_a_GasStationBruteForce {

    /**
     * Brute Force Solution - Try every possible starting point
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static int canCompleteCircuitBruteForce(int[] gas, int[] cost) {
        int n = gas.length;

        // Try each station as starting point
        for (int start = 0; start < n; start++) {
            int tank = 0;
            boolean canComplete = true;

            // Simulate journey starting from 'start'
            for (int step = 0; step < n; step++) {
                int currentStation = (start + step) % n;

                // Add gas at current station
                tank += gas[currentStation];

                // Consume gas to reach next station
                tank -= cost[currentStation];

                // Check if we have enough gas to continue
                if (tank < 0) {
                    canComplete = false;
                    break;
                }
            }

            // If we completed the circuit, return this starting point
            if (canComplete) {
                return start;
            }
        }

        // No valid starting point found
        return -1;
    }

    /**
     * Helper method to check if a solution exists at all
     * If total gas < total cost, no solution is possible
     */
    public static boolean hasSolution(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        return totalGas >= totalCost;
    }

    /**
     * Detailed simulation that shows the journey step by step
     */
    public static int simulateJourneyDetailed(int[] gas, int[] cost, int start) {
        int n = gas.length;
        int tank = 0;

        System.out.println("=== Journey Simulation Starting from Station " + start + " ===");
        System.out.println("Station | Gas Added | Cost | Tank After | Status");
        System.out.println("--------|-----------|------|------------|--------");

        for (int step = 0; step < n; step++) {
            int currentStation = (start + step) % n;
            int gasAdded = gas[currentStation];
            int costToNext = cost[currentStation];

            tank += gasAdded;
            tank -= costToNext;

            String status = tank >= 0 ? "OK" : "FAIL";
            System.out.printf("   %2d   |    %2d     |  %2d  |     %2d     | %s%n",
                    currentStation, gasAdded, costToNext, tank, status);

            if (tank < 0) {
                System.out.println("Journey failed at station " + currentStation);
                return -1;
            }
        }

        System.out.println("Journey completed successfully!");
        return start;
    }

    /**
     * Analyze the problem to understand why brute force has redundancy
     */
    public static void analyzeRedundancy(int[] gas, int[] cost) {
        System.out.println("=== Redundancy Analysis ===");
        System.out.println("Gas:  " + Arrays.toString(gas));
        System.out.println("Cost: " + Arrays.toString(cost));
        System.out.println();

        // Calculate net gain/loss at each station
        int[] netChange = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            netChange[i] = gas[i] - cost[i];
        }
        System.out.println("Net change: " + Arrays.toString(netChange));
        System.out.println();

        // Show what happens when we try different starting points
        System.out.println("Analyzing different starting points:");
        for (int start = 0; start < Math.min(gas.length, 4); start++) {
            System.out.println("\nStarting from station " + start + ":");
            int tank = 0;
            for (int step = 0; step < gas.length; step++) {
                int station = (start + step) % gas.length;
                tank += netChange[station];
                System.out.printf("  After station %d: tank = %d%n", station, tank);
                if (tank < 0) {
                    System.out.println("  Failed! Cannot reach next station.");
                    break;
                }
            }
        }

        System.out.println("\nObservation: We're recalculating similar paths multiple times.");
        System.out.println("Key insight: If we fail from station i, we can learn something");
        System.out.println("about which other stations might also fail.");
    }

    /**
     * Compare brute force with early termination optimizations
     */
    public static void compareOptimizations(int[] gas, int[] cost) {
        System.out.println("\n=== Optimization Comparison ===");

        long startTime, endTime;

        // Test basic brute force
        startTime = System.nanoTime();
        int result1 = canCompleteCircuitBruteForce(gas, cost);
        endTime = System.nanoTime();
        long bruteForceTime = endTime - startTime;

        // Test with early total check
        startTime = System.nanoTime();
        int result2 = -1;
        if (hasSolution(gas, cost)) {
            result2 = canCompleteCircuitBruteForce(gas, cost);
        }
        endTime = System.nanoTime();
        long optimizedTime = endTime - startTime;

        System.out.println("Brute Force Result: " + result1 +
                " (Time: " + bruteForceTime / 1000.0 + " μs)");
        System.out.println("With Early Check: " + result2 +
                " (Time: " + optimizedTime / 1000.0 + " μs)");

        System.out.println("\nBoth approaches give same result: " + (result1 == result2));

        if (result1 == -1) {
            System.out.println("Early total check can save time when no solution exists.");
        }
    }

    /**
     * Generate test cases to demonstrate the algorithm
     */
    public static void runTestCases() {
        System.out.println("=== Test Cases ===");

        // Test case 1: Has solution
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Test Case 1:");
        System.out.println("Gas:  " + Arrays.toString(gas1));
        System.out.println("Cost: " + Arrays.toString(cost1));
        int result1 = canCompleteCircuitBruteForce(gas1, cost1);
        System.out.println("Result: " + result1);
        if (result1 != -1) {
            simulateJourneyDetailed(gas1, cost1, result1);
        }
        System.out.println();

        // Test case 2: No solution
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Test Case 2:");
        System.out.println("Gas:  " + Arrays.toString(gas2));
        System.out.println("Cost: " + Arrays.toString(cost2));
        int result2 = canCompleteCircuitBruteForce(gas2, cost2);
        System.out.println("Result: " + result2);
        if (result2 == -1) {
            System.out.println("No solution exists - total gas < total cost");
        }
        System.out.println();

        // Test case 3: Edge case - single station
        int[] gas3 = {5};
        int[] cost3 = {4};
        System.out.println("Test Case 3 (Single Station):");
        System.out.println("Gas:  " + Arrays.toString(gas3));
        System.out.println("Cost: " + Arrays.toString(cost3));
        int result3 = canCompleteCircuitBruteForce(gas3, cost3);
        System.out.println("Result: " + result3);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    GAS STATION PROBLEM                      ║");
        System.out.println("║                   BRUTE FORCE APPROACH                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        runTestCases();

        // Demonstrate redundancy analysis
        int[] gas = {4, 3, 1, 2, 7, 4};
        int[] cost = {1, 2, 7, 3, 2, 5};
        analyzeRedundancy(gas, cost);

        compareOptimizations(gas, cost);

        System.out.println("\n=== Key Insights ===");
        System.out.println("1. Brute force works but has O(n²) time complexity");
        System.out.println("2. We test each starting point independently");
        System.out.println("3. Contains redundant calculations that can be optimized");
        System.out.println("4. Early termination: if total gas < total cost, return -1");
        System.out.println("5. Problem structure suggests more efficient approaches exist");
        System.out.println();
        System.out.println("Next: We'll explore mathematical and greedy optimizations!");
    }
}