package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._872_gas_station_algorithm;

/**
 * GAS STATION PROBLEM - MATHEMATICAL GRAPH APPROACH
 * <p>
 * Key Mathematical Insight:
 * Transform the problem into finding a starting point in a cumulative sum graph
 * where the cumulative sum never goes below zero.
 * <p>
 * Core Concepts:
 * 1. Net Change Array: diff[i] = gas[i] - cost[i] (net fuel change at station i)
 * 2. Cumulative Sum: Represents fuel level if starting from station 0
 * 3. Graph Visualization: Plot cumulative sum to identify patterns
 * 4. Lowest Point Strategy: The station after the lowest point is optimal starting point
 * 5. Graph Translation: Moving starting point effectively translates the graph upward
 * <p>
 * Mathematical Reasoning:
 * - If we start from station 0, cumulative sum shows fuel level changes
 * - Negative values indicate insufficient fuel at those points
 * - To avoid negative values, we need to "lift" the graph above x-axis
 * - Starting from the station after the minimum point achieves maximum upward shift
 * - This ensures the entire journey stays at or above zero fuel level
 * <p>
 * Algorithm Insight:
 * 1. Calculate cumulative sum starting from index 0
 * 2. Track the minimum sum and its position
 * 3. The optimal starting point is (min_index + 1) % n
 * 4. Verify total gas >= total cost for solution existence
 * <p>
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - only track running values
 * <p>
 * Visual Analogy:
 * Think of the cumulative sum as a graph where:
 * - X-axis represents stations (circular)
 * - Y-axis represents fuel level
 * - Goal: Find starting point that keeps entire graph above x-axis
 * - Solution: Start from the point that provides maximum upward translation
 */

import java.util.*;

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

    /**
     * Visualize the cumulative sum graph to understand the algorithm
     */
    public static void visualizeCumulativeSum(int[] gas, int[] cost) {
        int n = gas.length;
        System.out.println("=== Cumulative Sum Graph Visualization ===");
        System.out.println("Gas:  " + Arrays.toString(gas));
        System.out.println("Cost: " + Arrays.toString(cost));
        System.out.println();

        // Calculate net changes and cumulative sum
        int[] netChange = new int[n];
        int[] cumulativeSum = new int[n + 1];  // Include starting point (0)

        cumulativeSum[0] = 0;  // Start with empty tank
        for (int i = 0; i < n; i++) {
            netChange[i] = gas[i] - cost[i];
            cumulativeSum[i + 1] = cumulativeSum[i] + netChange[i];
        }

        System.out.println("Net change:     " + Arrays.toString(netChange));
        System.out.println("Cumulative sum: " + Arrays.toString(cumulativeSum));
        System.out.println();

        // Find minimum point
        int minSum = 0, minIndex = -1;
        for (int i = 0; i <= n; i++) {
            if (cumulativeSum[i] < minSum) {
                minSum = cumulativeSum[i];
                minIndex = i;
            }
        }

        System.out.println("Minimum cumulative sum: " + minSum + " at position " + minIndex);

        // Visualize the graph
        System.out.println("\nGraph Visualization (Y = cumulative fuel level):");
        System.out.println("Station:  " + String.format("%2s", "S"));
        for (int i = 0; i < n; i++) {
            System.out.print(String.format("%3d", i));
        }
        System.out.println();

        System.out.print("Fuel:     " + String.format("%2d", 0));  // Starting fuel
        for (int i = 1; i <= n; i++) {
            System.out.print(String.format("%3d", cumulativeSum[i]));
        }
        System.out.println();

        // Show where fuel goes negative
        System.out.print("Status:   " + String.format("%2s", "OK"));
        for (int i = 1; i <= n; i++) {
            String status = cumulativeSum[i] >= 0 ? "OK" : "XX";
            System.out.print(String.format("%3s", status));
        }
        System.out.println();
        System.out.println();

        // Explain the optimal starting point
        int optimalStart = minIndex == n ? 0 : minIndex;
        System.out.println("Analysis:");
        System.out.println("- Starting from station 0 causes fuel to go negative");
        System.out.println("- Minimum point at position " + minIndex + " (fuel = " + minSum + ")");
        System.out.println("- Optimal starting station: " + optimalStart);
        System.out.println("- This maximally shifts the graph upward");
    }

    /**
     * Demonstrate graph translation concept
     */
    public static void demonstrateGraphTranslation(int[] gas, int[] cost) {
        System.out.println("\n=== Graph Translation Demonstration ===");

        int n = gas.length;
        int[] netChange = new int[n];
        for (int i = 0; i < n; i++) {
            netChange[i] = gas[i] - cost[i];
        }

        // Find optimal starting point using graph method
        int optimalStart = canCompleteCircuitGraph(gas, cost);

        if (optimalStart == -1) {
            System.out.println("No solution exists - cannot demonstrate translation");
            return;
        }

        System.out.println("Original graph (starting from station 0):");
        showGraph(netChange, 0, "Original");

        System.out.println("\nTranslated graph (starting from station " + optimalStart + "):");
        showGraph(netChange, optimalStart, "Translated");

        System.out.println("\nKey insight: Translation ensures fuel level never goes negative!");
    }

    /**
     * Helper method to display a simple graph representation
     */
    private static void showGraph(int[] netChange, int start, String label) {
        int n = netChange.length;
        int fuel = 0;

        System.out.println(label + " Journey:");
        System.out.println("Station | Net Change | Fuel Level | Status");
        System.out.println("--------|------------|------------|--------");

        for (int i = 0; i < n; i++) {
            int station = (start + i) % n;
            fuel += netChange[station];
            String status = fuel >= 0 ? "Safe" : "Fail";
            System.out.printf("   %2d   |     %2d     |     %2d     | %s%n",
                    station, netChange[station], fuel, status);
        }
        System.out.println();
    }

    /**
     * Compare graph approach with brute force
     */
    public static void compareApproaches(int[] gas, int[] cost) {
        System.out.println("\n=== Approach Comparison ===");

        long startTime, endTime;

        // Graph approach
        startTime = System.nanoTime();
        int graphResult = canCompleteCircuitGraph(gas, cost);
        endTime = System.nanoTime();
        long graphTime = endTime - startTime;

        // Brute force for comparison (simplified version)
        startTime = System.nanoTime();
        int bruteResult = bruteForceSimple(gas, cost);
        endTime = System.nanoTime();
        long bruteTime = endTime - startTime;

        System.out.println("Graph Approach:  Result = " + graphResult +
                " (Time: " + graphTime / 1000.0 + " μs)");
        System.out.println("Brute Force:     Result = " + bruteResult +
                " (Time: " + bruteTime / 1000.0 + " μs)");
        System.out.println("Results match: " + (graphResult == bruteResult));
        System.out.println("Speedup: " + String.format("%.2fx", (double) bruteTime / graphTime));
    }

    /**
     * Simplified brute force for comparison
     */
    private static int bruteForceSimple(int[] gas, int[] cost) {
        int n = gas.length;
        for (int start = 0; start < n; start++) {
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
            if (valid) return start;
        }
        return -1;
    }

    /**
     * Mathematical proof explanation
     */
    public static void explainMathematicalProof() {
        System.out.println("\n=== Mathematical Proof Explanation ===");
        System.out.println();
        System.out.println("THEOREM: If total gas >= total cost, then the station immediately");
        System.out.println("after the minimum cumulative sum is the optimal starting point.");
        System.out.println();
        System.out.println("PROOF SKETCH:");
        System.out.println("1. Let S[i] = cumulative sum up to station i (starting from 0)");
        System.out.println("2. S[i] = Σ(gas[j] - cost[j]) for j = 0 to i");
        System.out.println("3. If we start from station k, new cumulative sum = S[i] - S[k-1]");
        System.out.println("4. To ensure all values ≥ 0, we need: min(S[i] - S[k-1]) ≥ 0");
        System.out.println("5. This means: S[k-1] ≤ min(S[i]) for all i");
        System.out.println("6. Therefore: k-1 = argmin(S[i]), so k = argmin(S[i]) + 1");
        System.out.println();
        System.out.println("INTUITION:");
        System.out.println("- Starting from minimum point provides maximum 'boost'");
        System.out.println("- Translates the entire graph upward by maximum amount");
        System.out.println("- Ensures we never run out of fuel during the journey");
        System.out.println();
        System.out.println("CIRCULAR ARRAY PROPERTY:");
        System.out.println("- The array is circular, so we can 'cut' and 'paste' segments");
        System.out.println("- Starting from different point = different arrangement of same data");
        System.out.println("- Graph translation preserves relative relationships");
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    GAS STATION PROBLEM                      ║");
        System.out.println("║                MATHEMATICAL GRAPH APPROACH                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Example from the tutorial
        int[] gas = {4, 3, 1, 2, 7, 4};
        int[] cost = {1, 2, 7, 3, 2, 5};

        visualizeCumulativeSum(gas, cost);
        demonstrateGraphTranslation(gas, cost);
        compareApproaches(gas, cost);
        explainMathematicalProof();

        // Test with more examples
        System.out.println("\n=== Additional Test Cases ===");

        // Test case 1: From tutorial
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Test 1: " + Arrays.toString(gas1) + " / " + Arrays.toString(cost1));
        System.out.println("Result: " + canCompleteCircuitGraph(gas1, cost1));

        // Test case 2: No solution
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Test 2: " + Arrays.toString(gas2) + " / " + Arrays.toString(cost2));
        System.out.println("Result: " + canCompleteCircuitGraph(gas2, cost2));

        System.out.println("\n=== Key Advantages of Graph Approach ===");
        System.out.println("1. O(n) time complexity - single pass through array");
        System.out.println("2. O(1) space complexity - only track running values");
        System.out.println("3. Mathematical elegance - transforms complex problem into graph analysis");
        System.out.println("4. Visual intuition - easy to understand with cumulative sum graph");
        System.out.println("5. Optimal solution - finds answer without redundant calculations");
        System.out.println();
        System.out.println("The graph approach reveals the deep mathematical structure of the problem!");
    }
}