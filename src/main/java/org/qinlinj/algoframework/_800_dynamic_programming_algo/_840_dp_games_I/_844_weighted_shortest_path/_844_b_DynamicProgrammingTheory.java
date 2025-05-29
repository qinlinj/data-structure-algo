package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._844_weighted_shortest_path;

import java.util.*;

public class _844_b_DynamicProgrammingTheory {
    /**
     * Demonstrates the theoretical foundation of DP approach
     */
    public static class DPTheoryExplanation {

        /**
         * Visualizes how DP breaks down the problem
         * Shows the recursive relationship and optimal substructure
         */
        public static void explainOptimalSubstructure() {
            System.out.println("=== Optimal Substructure Demonstration ===");
            System.out.println("Problem: Find cheapest path from src to dst in k steps");
            System.out.println();

            System.out.println("Decomposition:");
            System.out.println("To reach dst in k steps, we can:");
            System.out.println("1. Come from neighbor s1 in (k-1) steps, then take edge s1->dst");
            System.out.println("2. Come from neighbor s2 in (k-1) steps, then take edge s2->dst");
            System.out.println("3. ... and so on for all neighbors");
            System.out.println();

            System.out.println("Mathematical Relation:");
            System.out.println("dp(dst, k) = min{dp(si, k-1) + weight(si->dst)} for all neighbors si");
            System.out.println();

            System.out.println("Why this works:");
            System.out.println("- If dp(si, k-1) is optimal, then dp(dst, k) using si is also optimal");
            System.out.println("- This is the essence of optimal substructure");
        }

        /**
         * Shows why we need memoization (overlapping subproblems)
         */
        public static void explainOverlappingSubproblems() {
            System.out.println("\n=== Overlapping Subproblems Example ===");
            System.out.println("Consider graph: A->C, B->C, C->D");
            System.out.println("To find paths to D, we might need:");
            System.out.println("- dp(C, 2) when coming from A");
            System.out.println("- dp(C, 2) when coming from B");
            System.out.println("- dp(C, 1) for some other calculation");
            System.out.println();
            System.out.println("Same (city, steps) pairs appear multiple times!");
            System.out.println("Memoization prevents recalculating these subproblems.");
        }

        /**
         * Demonstrates the state space and transitions
         */
        public static void visualizeStateSpace(int n, int maxSteps) {
            System.out.println("\n=== State Space Visualization ===");
            System.out.println("State: (city, remaining_steps)");
            System.out.println("State Space Size: " + n + " cities × " + (maxSteps + 1) + " steps = " + (n * (maxSteps + 1)));
            System.out.println();

            System.out.println("State Transitions:");
            for (int city = 0; city < Math.min(3, n); city++) {
                for (int steps = 0; steps <= Math.min(2, maxSteps); steps++) {
                    System.out.println("State (" + city + ", " + steps + ") can transition to:");
                    if (steps > 0) {
                        System.out.println("  - Any neighbor (neighbor_city, " + (steps - 1) + ")");
                    } else {
                        System.out.println("  - No transitions (base case)");
                    }
                }
            }
        }
    }

    /**
     * Simple recursive solution without memoization to show the basic structure
     */
    public static class BasicRecursiveSolution {
        private Map<Integer, List<int[]>> indegree;
        private int source;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            this.source = src;
            buildIndegreeGraph(flights);
            return dp(dst, k + 1); // k+1 because k is number of stops, we need edges
        }

        private void buildIndegreeGraph(int[][] flights) {
            indegree = new HashMap<>();
            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], price = flight[2];
                indegree.computeIfAbsent(to, key -> new ArrayList<>())
                        .add(new int[]{from, price});
            }
        }

        /**
         * Recursive DP function
         *
         * @param city  current city
         * @param steps remaining steps
         * @return minimum cost to reach city from source in steps
         */
        private int dp(int city, int steps) {
            // Base case 1: reached source
            if (city == source) {
                return 0;
            }

            // Base case 2: no steps left
            if (steps == 0) {
                return -1; // impossible
            }

            int minCost = Integer.MAX_VALUE;

            // Try all incoming edges
            if (indegree.containsKey(city)) {
                for (int[] edge : indegree.get(city)) {
                    int fromCity = edge[0];
                    int price = edge[1];

                    int subproblemCost = dp(fromCity, steps - 1);
                    if (subproblemCost != -1) {
                        minCost = Math.min(minCost, subproblemCost + price);
                    }
                }
            }

            return minCost == Integer.MAX_VALUE ? -1 : minCost;
        }
    }
}
