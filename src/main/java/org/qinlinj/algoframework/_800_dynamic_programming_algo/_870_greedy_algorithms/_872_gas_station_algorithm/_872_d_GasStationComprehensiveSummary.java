package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._872_gas_station_algorithm;

import java.util.*;

/**
 * GAS STATION PROBLEM - COMPREHENSIVE SUMMARY AND COMPARISON
 * LeetCode 134: Gas Station
 * <p>
 * Problem Evolution: From O(n²) Brute Force to O(n) Optimal Solutions
 * <p>
 * APPROACH SUMMARY:
 * <p>
 * 1. BRUTE FORCE APPROACH:
 * - Try each station as starting point, simulate full journey
 * - Time: O(n²), Space: O(1)
 * - Straightforward but inefficient due to redundant calculations
 * <p>
 * 2. MATHEMATICAL GRAPH APPROACH:
 * - Transform to cumulative sum graph analysis
 * - Find minimum point, start from next station for maximum "lift"
 * - Time: O(n), Space: O(1)
 * - Elegant mathematical insight with visual interpretation
 * <p>
 * 3. GREEDY ELIMINATION APPROACH:
 * - Use failure patterns to eliminate multiple candidates at once
 * - Key insight: if start i fails at j, then i+1,...,j-1 also fail at j
 * - Time: O(n), Space: O(1)
 * - Efficient elimination strategy based on problem structure
 * <p>
 * CORE INSIGHTS:
 * - All algorithms essentially solve the same mathematical problem
 * - Graph approach focuses on "translation/shifting" perspective
 * - Greedy approach focuses on "elimination/pruning" perspective
 * - Both optimal approaches avoid redundant computation from brute force
 * <p>
 * ALGORITHM EQUIVALENCE:
 * The graph and greedy approaches are mathematically equivalent:
 * - Graph finds argmin(cumulative_sum) + 1
 * - Greedy eliminates stations until finding the same starting point
 * - Both guaranteed to find the unique solution (if exists)
 * <p>
 * PRACTICAL APPLICATIONS:
 * - Resource allocation with circular constraints
 * - Route planning with fuel/cost considerations
 * - Optimization problems with cyclic dependencies
 * - Any scenario requiring optimal starting point in circular systems
 */

public class _872_d_GasStationComprehensiveSummary {
    /**
     * All three approaches implemented for comparison
     */
    public static class AllApproaches {

        /**
         * Brute Force: Try every starting point
         */
        public static int bruteForceSolution(int[] gas, int[] cost) {
            int n = gas.length;

            for (int start = 0; start < n; start++) {
                int tank = 0;
                boolean canComplete = true;

                for (int i = 0; i < n; i++) {
                    int station = (start + i) % n;
                    tank += gas[station] - cost[station];
                    if (tank < 0) {
                        canComplete = false;
                        break;
                    }
                }

                if (canComplete) return start;
            }
            return -1;
        }

        /**
         * Graph Approach: Find minimum cumulative sum point
         */
        public static int graphSolution(int[] gas, int[] cost) {
            int n = gas.length;
            int sum = 0, minSum = 0, start = 0;

            for (int i = 0; i < n; i++) {
                sum += gas[i] - cost[i];
                if (sum < minSum) {
                    minSum = sum;
                    start = i + 1;
                }
            }

            return sum < 0 ? -1 : (start == n ? 0 : start);
        }

        /**
         * Greedy Approach: Eliminate impossible starting points
         */
        public static int greedySolution(int[] gas, int[] cost) {
            int n = gas.length;

            // Check total feasibility
            int totalGas = 0, totalCost = 0;
            for (int i = 0; i < n; i++) {
                totalGas += gas[i];
                totalCost += cost[i];
            }
            if (totalGas < totalCost) return -1;

            // Greedy elimination
            int tank = 0, start = 0;
            for (int i = 0; i < n; i++) {
                tank += gas[i] - cost[i];
                if (tank < 0) {
                    tank = 0;
                    start = i + 1;
                }
            }

            return start == n ? 0 : start;
        }
    }

    /**
     * Comprehensive testing suite
     */
    public static class ComprehensiveTesting {

        public static void runAllTests() {
            System.out.println("=== COMPREHENSIVE TEST SUITE ===");
            System.out.println();

            // Test cases covering different scenarios
            int[][][] testCases = {
                    // Case 1: Standard case with solution
                    {{1, 2, 3, 4, 5}, {3, 4, 5, 1, 2}},

                    // Case 2: No solution case
                    {{2, 3, 4}, {3, 4, 3}},

                    // Case 3: Single station
                    {{5}, {4}},

                    // Case 4: Start from station 0
                    {{3, 1, 1}, {1, 1, 2}},

                    // Case 5: Complex case from tutorial
                    {{4, 3, 1, 2, 7, 4}, {1, 2, 7, 3, 2, 5}},

                    // Case 6: Edge case - exact match
                    {{2, 2, 2}, {2, 2, 2}},

                    // Case 7: Large jumps
                    {{1, 1, 10, 1}, {2, 2, 1, 9}}
            };

            for (int t = 0; t < testCases.length; t++) {
                int[] gas = testCases[t][0];
                int[] cost = testCases[t][1];

                System.out.println("Test Case " + (t + 1) + ":");
                System.out.println("Gas:  " + Arrays.toString(gas));
                System.out.println("Cost: " + Arrays.toString(cost));

                // Run all three approaches
                long startTime, endTime;

                startTime = System.nanoTime();
                int bruteResult = AllApproaches.bruteForceSolution(gas, cost);
                endTime = System.nanoTime();
                long bruteTime = endTime - startTime;

                startTime = System.nanoTime();
                int graphResult = AllApproaches.graphSolution(gas, cost);
                endTime = System.nanoTime();
                long graphTime = endTime - startTime;

                startTime = System.nanoTime();
                int greedyResult = AllApproaches.greedySolution(gas, cost);
                endTime = System.nanoTime();
                long greedyTime = endTime - startTime;

                // Display results
                System.out.printf("Brute Force: %2d (%.2f μs)%n", bruteResult, bruteTime / 1000.0);
                System.out.printf("Graph:       %2d (%.2f μs)%n", graphResult, graphTime / 1000.0);
                System.out.printf("Greedy:      %2d (%.2f μs)%n", greedyResult, greedyTime / 1000.0);

                boolean allMatch = (bruteResult == graphResult) && (graphResult == greedyResult);
                System.out.println("All methods agree: " + allMatch);

                if (bruteResult != -1) {
                    verifyValidStartingPoint(gas, cost, bruteResult);
                }

                System.out.println();
            }
        }

        /**
         * Verify that a proposed starting point actually works
         */
        private static void verifyValidStartingPoint(int[] gas, int[] cost, int start) {
            int n = gas.length;
            int tank = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                int station = (start + i) % n;
                tank += gas[station] - cost[station];
                if (tank < 0) {
                    valid = false;
                    break;
                }
            }

            System.out.println("Verification: Starting point " + start + " is " +
                    (valid ? "VALID" : "INVALID"));
        }
    }
}
