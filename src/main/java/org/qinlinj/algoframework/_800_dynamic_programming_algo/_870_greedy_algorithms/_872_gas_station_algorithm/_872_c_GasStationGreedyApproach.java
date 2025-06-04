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

import java.util.*;

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

    /**
     * Compare greedy elimination vs brute force
     */
    public static void compareElimination(int[] gas, int[] cost) {
        System.out.println("\n=== Elimination Comparison ===");

        int n = gas.length;
        System.out.println("Array size: " + n + " stations");

        // Count eliminations in greedy approach
        int tank = 0;
        int totalEliminated = 0;
        int eliminationSteps = 0;

        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                eliminationSteps++;
                totalEliminated += (i + 1);  // Stations 0 to i eliminated
                tank = 0;
            }
        }

        System.out.println("Greedy approach:");
        System.out.println("- Elimination steps: " + eliminationSteps);
        System.out.println("- Total stations eliminated: " + totalEliminated);
        System.out.println("- Stations checked: " + n + " (single pass)");

        System.out.println("\nBrute force approach:");
        System.out.println("- Must test each station individually");
        System.out.println("- Stations checked: " + n + " candidates × " + n + " steps = " + (n * n));
        System.out.println("- No elimination optimization");

        System.out.println("\nEfficiency gain:");
        if (eliminationSteps > 0) {
            System.out.println("- Greedy eliminates " + totalEliminated + " stations in " +
                    eliminationSteps + " steps");
            System.out.println("- Brute force would test these " + totalEliminated +
                    " stations individually");
            System.out.printf("- Reduction ratio: %.1f%%\n",
                    100.0 * (n * n - n) / (n * n));
        }
    }

    /**
     * Prove correctness of greedy approach
     */
    public static void proveCorrectness() {
        System.out.println("\n=== Correctness Proof ===");
        System.out.println();

        System.out.println("THEOREM: The greedy elimination approach finds the correct answer.");
        System.out.println();

        System.out.println("PROOF:");
        System.out.println("1. ELIMINATION CORRECTNESS:");
        System.out.println("   If starting from station i with tank=0, we reach station k with tank>0,");
        System.out.println("   but fail to reach station j, then starting from station k with tank=0");
        System.out.println("   will definitely fail to reach station j.");
        System.out.println("   Reason: Less fuel at k means worse prospects for reaching j.");
        System.out.println();

        System.out.println("2. EXISTENCE GUARANTEE:");
        System.out.println("   If total_gas >= total_cost, then a solution must exist.");
        System.out.println("   The greedy algorithm will find it because:");
        System.out.println("   - It eliminates all impossible starting points");
        System.out.println("   - It continues until finding a valid starting point");
        System.out.println("   - The valid point exists by the total gas/cost constraint");
        System.out.println();

        System.out.println("3. OPTIMALITY:");
        System.out.println("   The greedy algorithm finds the lexicographically smallest valid");
        System.out.println("   starting station, which matches the problem requirements.");
        System.out.println();

        System.out.println("4. COMPLEXITY:");
        System.out.println("   - Each station is visited at most once during elimination");
        System.out.println("   - Total time: O(n)");
        System.out.println("   - Space: O(1)");
    }

    /**
     * Compare all three approaches: brute force, graph, and greedy
     */
    public static void compareAllApproaches(int[] gas, int[] cost) {
        System.out.println("\n=== Three-Way Approach Comparison ===");

        long startTime, endTime;

        // Brute force
        startTime = System.nanoTime();
        int bruteResult = bruteForceApproach(gas, cost);
        endTime = System.nanoTime();
        long bruteTime = endTime - startTime;

        // Graph approach
        startTime = System.nanoTime();
        int graphResult = graphApproach(gas, cost);
        endTime = System.nanoTime();
        long graphTime = endTime - startTime;

        // Greedy approach
        startTime = System.nanoTime();
        int greedyResult = canCompleteCircuitGreedy(gas, cost);
        endTime = System.nanoTime();
        long greedyTime = endTime - startTime;

        System.out.println("Results:");
        System.out.printf("Brute Force: %2d (Time: %8.2f μs) [O(n²) time, O(1) space]%n",
                bruteResult, bruteTime / 1000.0);
        System.out.printf("Graph:       %2d (Time: %8.2f μs) [O(n) time,  O(1) space]%n",
                graphResult, graphTime / 1000.0);
        System.out.printf("Greedy:      %2d (Time: %8.2f μs) [O(n) time,  O(1) space]%n",
                greedyResult, greedyTime / 1000.0);

        System.out.println("\nAll approaches give same result: " +
                (bruteResult == graphResult && graphResult == greedyResult));

        System.out.println("\nConceptual Differences:");
        System.out.println("- Brute Force: Test each starting point independently");
        System.out.println("- Graph:       Find minimum point in cumulative sum graph");
        System.out.println("- Greedy:      Eliminate impossible starting points progressively");
        System.out.println("\nBoth Graph and Greedy are optimal, but offer different insights!");
    }

    // Helper methods for comparison
    private static int bruteForceApproach(int[] gas, int[] cost) {
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

    private static int graphApproach(int[] gas, int[] cost) {
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

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    GAS STATION PROBLEM                      ║");
        System.out.println("║                   GREEDY ALGORITHM APPROACH                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Example from tutorial
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        int result = greedyWithExplanation(gas, cost);
        demonstrateGreedyInsight(gas, cost);
        compareElimination(gas, cost);
        proveCorrectness();
        compareAllApproaches(gas, cost);

        // Test with no solution case
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Testing case with no solution:");
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        greedyWithExplanation(gas2, cost2);

        System.out.println("\n=== Key Insights from Greedy Approach ===");
        System.out.println("1. ELIMINATION STRATEGY:");
        System.out.println("   - Don't test impossible starting points individually");
        System.out.println("   - Use failure information to eliminate multiple candidates");
        System.out.println("   - Each failure eliminates a range of starting points");
        System.out.println();

        System.out.println("2. GREEDY CHOICE PROPERTY:");
        System.out.println("   - Local decision (eliminate range) leads to global optimum");
        System.out.println("   - No need to reconsider eliminated candidates");
        System.out.println("   - Progressive elimination naturally finds the answer");
        System.out.println();

        System.out.println("3. ALGORITHM EFFICIENCY:");
        System.out.println("   - Reduces O(n²) brute force to O(n) greedy");
        System.out.println("   - Single pass through array with smart elimination");
        System.out.println("   - Constant space usage");
        System.out.println();

        System.out.println("4. PROBLEM-SOLVING INSIGHT:");
        System.out.println("   - Look for patterns that eliminate multiple possibilities");
        System.out.println("   - Use problem structure to avoid redundant computation");
        System.out.println("   - Mathematical reasoning can reveal algorithmic shortcuts");
        System.out.println();

        System.out.println("The greedy approach transforms complex enumeration into elegant elimination!");
    }
}