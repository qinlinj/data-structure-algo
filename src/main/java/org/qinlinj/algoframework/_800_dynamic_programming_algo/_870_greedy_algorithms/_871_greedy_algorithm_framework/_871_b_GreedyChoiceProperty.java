package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

/**
 * GREEDY CHOICE PROPERTY vs OPTIMAL SUBSTRUCTURE
 * <p>
 * Key Concepts:
 * 1. Greedy Choice Property: Can derive global optimal solution through local optimal choices
 * 2. Optimal Substructure: Global optimal solution can be constructed from optimal solutions of subproblems
 * 3. Problem Comparison:
 * - Problem 1 (Maximum Value): Has greedy choice property → Use greedy algorithm
 * - Problem 2 (Minimum Coins): Lacks greedy choice property → Use dynamic programming
 * <p>
 * Greedy Choice Property:
 * - Make locally optimal choice at each step
 * - Don't need to solve all subproblems
 * - Local optimal choices lead to global optimal solution
 * <p>
 * Optimal Substructure:
 * - Need to solve all subproblems first
 * - Then construct global solution from subproblem solutions
 * - Foundation for dynamic programming
 * <p>
 * Example Analysis:
 * - Problem 1: Each choice of 100-yuan bill is locally and globally optimal
 * - Problem 2: Choosing largest denomination isn't always globally optimal
 * (e.g., target=3, choosing 100-yuan bills won't work, need 3×1-yuan bills)
 */

import java.util.*;

public class _871_b_GreedyChoiceProperty {

    /**
     * Compare greedy choice property vs optimal substructure
     */
    public static void compareProperties() {
        System.out.println("=== GREEDY CHOICE PROPERTY vs OPTIMAL SUBSTRUCTURE ===");
        System.out.println();

        System.out.println("GREEDY CHOICE PROPERTY:");
        System.out.println("- Definition: Local optimal choices lead directly to global optimal solution");
        System.out.println("- Characteristic: Don't need to solve all subproblems");
        System.out.println("- Algorithm: Greedy algorithm (high efficiency)");
        System.out.println("- Example: Maximum value problem - always choose 100-yuan bill");
        System.out.println();

        System.out.println("OPTIMAL SUBSTRUCTURE:");
        System.out.println("- Definition: Global optimal solution constructed from optimal subproblems");
        System.out.println("- Characteristic: Must solve all relevant subproblems first");
        System.out.println("- Algorithm: Dynamic programming (explores all possibilities)");
        System.out.println("- Example: Minimum coins problem - need to consider all combinations");
        System.out.println();

        System.out.println("KEY DIFFERENCE:");
        System.out.println("- Greedy: Make choice NOW based on current local optimum");
        System.out.println("- DP: Make choice LATER after knowing all subproblem solutions");
        System.out.println("- Greedy is faster but only works with greedy choice property");
    }

    /**
     * Real-world example: Coin systems
     */
    public static void coinSystemExample() {
        System.out.println("\n=== REAL-WORLD COIN SYSTEM EXAMPLE ===");
        System.out.println();

        // US coin system: {1, 5, 10, 25} cents
        System.out.println("US Coin System {1, 5, 10, 25} cents:");
        System.out.println("- HAS greedy choice property");
        System.out.println("- Always choosing largest possible coin gives optimal solution");
        System.out.println("- Example: 67 cents = 2×25 + 1×10 + 1×5 + 2×1 = 6 coins");
        System.out.println();

        // Artificial coin system: {1, 3, 4} units
        System.out.println("Artificial Coin System {1, 3, 4} units:");
        System.out.println("- LACKS greedy choice property");
        System.out.println("- Greedy for 6 units: 1×4 + 2×1 = 3 coins");
        System.out.println("- Optimal for 6 units: 2×3 = 2 coins");
        System.out.println("- Need dynamic programming for optimal solution");
    }

    public static void main(String[] args) {
        MaxValueProblem.demonstrate();
        MinCoinsProblem.demonstrate();
        compareProperties();
        coinSystemExample();

        System.out.println("\n=== SUMMARY ===");
        System.out.println("1. Greedy algorithms are efficient but only work with greedy choice property");
        System.out.println("2. Dynamic programming works for all problems with optimal substructure");
        System.out.println("3. Key insight: Not all optimization problems have greedy choice property");
        System.out.println("4. Always verify greedy choice property before using greedy algorithms");
    }

    /**
     * Problem 1: Maximum value with limited number of bills
     * This problem HAS greedy choice property
     */
    public static class MaxValueProblem {

        /**
         * Greedy solution: Always choose higher denomination
         * Time Complexity: O(1)
         */
        public static int solve(int numBills) {
            // Local optimal choice: always pick 100-yuan bill
            // Global optimal solution: numBills * 100
            return numBills * 100;
        }

        /**
         * Demonstrate why this has greedy choice property
         */
        public static void demonstrate() {
            System.out.println("=== Problem 1: Maximum Value (HAS Greedy Choice Property) ===");
            System.out.println("Goal: Choose 10 bills to maximize total value");
            System.out.println("Available: 1-yuan and 100-yuan bills");
            System.out.println();

            int numBills = 10;
            System.out.println("Local optimal choice at each step: Choose 100-yuan bill (100 > 1)");
            System.out.println("Global optimal solution: " + solve(numBills) + " yuan");
            System.out.println("Why greedy works: Each local choice contributes to global optimum");
            System.out.println();
        }
    }

    /**
     * Problem 2: Minimum coins to make target amount
     * This problem LACKS greedy choice property (for general coin systems)
     */
    public static class MinCoinsProblem {

        /**
         * Greedy approach (doesn't always work)
         * Time Complexity: O(amount)
         */
        public static int solveGreedy(int amount) {
            if (amount == 0) return 0;
            if (amount < 0) return -1; // impossible

            int coins = 0;
            int[] denominations = {100, 1}; // sorted in descending order

            for (int coin : denominations) {
                while (amount >= coin) {
                    amount -= coin;
                    coins++;
                }
            }

            return amount == 0 ? coins : -1;
        }

        /**
         * Dynamic programming approach (always works)
         * Time Complexity: O(amount * coins)
         */
        public static int solveDynamic(int amount) {
            if (amount == 0) return 0;

            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1); // Initialize with impossible value
            dp[0] = 0;

            int[] coins = {1, 100};

            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i >= coin) {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }

            return dp[amount] > amount ? -1 : dp[amount];
        }

        /**
         * Demonstrate why this lacks greedy choice property
         */
        public static void demonstrate() {
            System.out.println("=== Problem 2: Minimum Coins (LACKS Greedy Choice Property) ===");
            System.out.println("Goal: Use minimum coins to make target amount");
            System.out.println("Available: 1-yuan and 100-yuan coins");
            System.out.println();

            int[] testCases = {3, 150, 203};

            for (int amount : testCases) {
                int greedyResult = solveGreedy(amount);
                int dynamicResult = solveDynamic(amount);

                System.out.println("Target amount: " + amount + " yuan");
                System.out.println("  Greedy approach: " +
                        (greedyResult == -1 ? "Impossible" : greedyResult + " coins"));
                System.out.println("  Dynamic programming: " +
                        (dynamicResult == -1 ? "Impossible" : dynamicResult + " coins"));
                System.out.println("  Greedy works: " + (greedyResult == dynamicResult));
                System.out.println();
            }

            System.out.println("Analysis:");
            System.out.println("- For amount=3: Greedy fails (can't use 100-yuan), DP works (3×1-yuan)");
            System.out.println("- For amount=150: Both work (1×100-yuan + 50×1-yuan)");
            System.out.println("- Greedy choice (largest denomination) isn't always globally optimal");
        }
    }
}