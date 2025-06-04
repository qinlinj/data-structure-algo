package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

import java.util.*;

public class _864_b_GreedyChoiceProperty {

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

