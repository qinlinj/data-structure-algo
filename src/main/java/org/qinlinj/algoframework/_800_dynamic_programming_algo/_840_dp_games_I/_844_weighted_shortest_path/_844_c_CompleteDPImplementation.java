package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._844_weighted_shortest_path;

/**
 * Complete Dynamic Programming Implementation for Cheapest Flights Problem
 * <p>
 * Implementation Details:
 * 1. Indegree Graph: Store incoming edges for each city with weights
 * 2. Memoization: 2D array memo[city][steps] to cache results
 * 3. State Definition: dp(city, k) = min cost to reach city in k steps from source
 * 4. Initialization: Use special value (-888) to distinguish uncomputed states
 * 5. Base Cases: source city costs 0, impossible states return -1
 * <p>
 * Key Optimizations:
 * - HashMap for indegree adjacency list (sparse graph friendly)
 * - 2D memoization table prevents redundant calculations
 * - Proper handling of impossible states vs uncomputed states
 * - Early termination when no incoming edges exist
 * <p>
 * Time Complexity: O(n * k * E) where E is edges per node
 * Space Complexity: O(n * k) for memoization table + O(E) for graph
 * <p>
 * This approach transforms the shortest path problem into a standard DP problem
 * by adding the constraint of maximum steps, making it solvable with memoized recursion.
 */

import java.util.*;

public class _844_c_CompleteDPImplementation {

    public static void main(String[] args) {
        System.out.println("COMPLETE DYNAMIC PROGRAMMING IMPLEMENTATION");
        System.out.println("=" + "=".repeat(50));

        // Test case 1: Basic example
        System.out.println("Test Case 1: Basic Example");
        int n1 = 3;
        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src1 = 0, dst1 = 2, k1 = 1;

        OptimizedDPSolution solution1 = new OptimizedDPSolution();
        int result1 = solution1.findCheapestPrice(n1, flights1, src1, dst1, k1);
        System.out.println("Result: " + result1 + " (Expected: 200)");
        solution1.printMemoTable(n1, k1 + 1);

        // Test case 2: No intermediate stops
        System.out.println("\nTest Case 2: No Intermediate Stops");
        int result2 = solution1.findCheapestPrice(n1, flights1, src1, dst1, 0);
        System.out.println("Result: " + result2 + " (Expected: 500)");

        // Test case 3: Impossible case
        System.out.println("\nTest Case 3: Impossible Route");
        int[][] flights3 = {{0, 1, 100}, {2, 3, 100}};
        int result3 = solution1.findCheapestPrice(4, flights3, 0, 3, 2);
        System.out.println("Result: " + result3 + " (Expected: -1)");

        // Performance comparison
        PerformanceComparison.compareApproaches(n1, flights1, src1, dst1, k1);

        System.out.println("\nKey Insights:");
        System.out.println("1. Memoization eliminates redundant subproblem calculations");
        System.out.println("2. Indegree representation makes transitions natural");
        System.out.println("3. Both top-down and bottom-up approaches work effectively");
        System.out.println("4. Special values help distinguish impossible vs uncomputed states");
    }

    /**
     * Optimized DP solution with memoization
     */
    public static class OptimizedDPSolution {
        private static final int UNCOMPUTED = -888;
        private Map<Integer, List<int[]>> indegree;
        private int source, destination;
        private int[][] memo;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // Convert stops to edges (k stops = k+1 edges)
            k++;

            this.source = src;
            this.destination = dst;

            // Initialize memoization table
            memo = new int[n][k + 1];
            for (int[] row : memo) {
                Arrays.fill(row, UNCOMPUTED);
            }

            // Build indegree graph
            buildIndegreeGraph(flights);

            return dp(dst, k);
        }

        /**
         * Builds indegree representation of the graph
         * For each city, stores all cities that can reach it and the cost
         */
        private void buildIndegreeGraph(int[][] flights) {
            indegree = new HashMap<>();
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];

                indegree.computeIfAbsent(to, key -> new ArrayList<>())
                        .add(new int[]{from, price});
            }
        }

        /**
         * DP function with memoization
         * @param city target city to reach
         * @param steps maximum steps allowed
         * @return minimum cost to reach city from source in steps, or -1 if impossible
         */
        private int dp(int city, int steps) {
            // Base case: reached source city
            if (city == source) {
                return 0;
            }

            // Base case: no steps remaining
            if (steps == 0) {
                return -1;
            }

            // Check memoization table
            if (memo[city][steps] != UNCOMPUTED) {
                return memo[city][steps];
            }

            int minCost = Integer.MAX_VALUE;

            // Try all possible previous cities (incoming edges)
            if (indegree.containsKey(city)) {
                for (int[] incomingEdge : indegree.get(city)) {
                    int fromCity = incomingEdge[0];
                    int edgePrice = incomingEdge[1];

                    // Recursive call for subproblem
                    int subproblemCost = dp(fromCity, steps - 1);

                    // Update minimum cost if valid path found
                    if (subproblemCost != -1) {
                        minCost = Math.min(minCost, subproblemCost + edgePrice);
                    }
                }
            }

            // Store result in memo table
            memo[city][steps] = (minCost == Integer.MAX_VALUE) ? -1 : minCost;
            return memo[city][steps];
        }

        /**
         * Debug method to show memoization table
         */
        public void printMemoTable(int n, int maxSteps) {
            System.out.println("\n=== Memoization Table ===");
            System.out.printf("%-8s", "City/Steps");
            for (int step = 0; step <= maxSteps; step++) {
                System.out.printf("%-8d", step);
            }
            System.out.println();

            for (int city = 0; city < n; city++) {
                System.out.printf("%-8d", city);
                for (int step = 0; step <= maxSteps; step++) {
                    if (memo[city][step] == UNCOMPUTED) {
                        System.out.printf("%-8s", "---");
                    } else {
                        System.out.printf("%-8d", memo[city][step]);
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * Alternative bottom-up DP implementation
     */
    public static class BottomUpDPSolution {

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // dp[i][j] = minimum cost to reach city j with at most i edges
            int[][] dp = new int[k + 2][n];

            // Initialize with infinity except source
            for (int i = 0; i <= k + 1; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                dp[i][src] = 0;
            }

            // Fill DP table
            for (int step = 1; step <= k + 1; step++) {
                for (int[] flight : flights) {
                    int from = flight[0];
                    int to = flight[1];
                    int price = flight[2];

                    if (dp[step - 1][from] != Integer.MAX_VALUE) {
                        dp[step][to] = Math.min(dp[step][to],
                                dp[step - 1][from] + price);
                    }
                }
            }

            // Find minimum cost across all possible steps
            int result = Integer.MAX_VALUE;
            for (int step = 0; step <= k + 1; step++) {
                result = Math.min(result, dp[step][dst]);
            }

            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }

    /**
     * Performance comparison utility
     */
    public static class PerformanceComparison {

        public static void compareApproaches(int n, int[][] flights, int src, int dst, int k) {
            System.out.println("\n=== Performance Comparison ===");

            OptimizedDPSolution topDown = new OptimizedDPSolution();
            BottomUpDPSolution bottomUp = new BottomUpDPSolution();

            long start1 = System.nanoTime();
            int result1 = topDown.findCheapestPrice(n, flights, src, dst, k);
            long time1 = System.nanoTime() - start1;

            long start2 = System.nanoTime();
            int result2 = bottomUp.findCheapestPrice(n, flights, src, dst, k);
            long time2 = System.nanoTime() - start2;

            System.out.println("Top-down DP result: " + result1 + " (Time: " + time1 / 1000 + " μs)");
            System.out.println("Bottom-up DP result: " + result2 + " (Time: " + time2 / 1000 + " μs)");
            System.out.println("Results match: " + (result1 == result2));

            // Show memoization table for top-down approach
            topDown.printMemoTable(n, k + 1);
        }
    }
}