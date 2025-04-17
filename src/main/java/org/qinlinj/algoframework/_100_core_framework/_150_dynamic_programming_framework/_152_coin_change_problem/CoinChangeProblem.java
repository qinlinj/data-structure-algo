package org.qinlinj.algoframework._100_core_framework._150_dynamic_programming_framework._152_coin_change_problem;

// @formatter:off
/**
 * Dynamic Programming: Coin Change Problem
 * ----------------------------------------
 *
 * This class explores the famous "Coin Change" problem (LeetCode 322) as a case study
 * for understanding dynamic programming concepts, particularly:
 *
 * 1. Optimal substructure
 * 2. State identification
 * 3. State transition equations
 * 4. Recursive vs iterative solutions
 *
 * Problem Statement:
 * Given an array of coin denominations and a target amount, find the minimum
 * number of coins needed to make up that amount. Return -1 if the amount
 * cannot be made up by any combination of coins.
 */
public class CoinChangeProblem {
    /**
     * 1. Brute Force Recursive Solution
     *
     * This solution directly implements the recursive formula:
     * dp(amount) = min(dp(amount - coin) + 1) for each coin in coins
     *
     * Time Complexity: O(k^n) where k is the number of coin denominations
     * and n is the amount (exponential)
     * Space Complexity: O(n) for the recursion stack
     */
    public int coinChangeRecursive(int[] coins, int amount) {
        // Call our recursive helper function
        return dpRecursive(coins, amount);
    }

    /**
     * Recursive helper function
     * dp(amount) represents the minimum coins needed to make up 'amount'
     */
    private int dpRecursive(int[] coins, int amount) {
        // Base cases
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // Initialize result to "infinity"
        int minCoins = Integer.MAX_VALUE;

        // Try each coin as the last coin in the solution
        for (int coin : coins) {
            // Recursively solve the subproblem
            int subproblem = dpRecursive(coins, amount - coin);

            // If the subproblem has a valid solution
            if (subproblem != -1) {
                // Update the minimum (+1 because we used one more coin)
                minCoins = Math.min(minCoins, subproblem + 1);
            }
        }

        // Return the minimum or -1 if no solution
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    /**
     * 2. Top-Down DP with Memoization
     *
     * Same recursive structure but with memoization to avoid redundant calculations
     *
     * Time Complexity: O(n * k) where k is the number of coin denominations
     * Space Complexity: O(n) for memo array and recursion stack
     */
    public int coinChangeMemoized(int[] coins, int amount) {
        // Initialize memoization array with -2 (indicating not calculated)
        int[] memo = new int[amount + 1];
        java.util.Arrays.fill(memo, -2);

        return dpMemoized(coins, amount, memo);
    }

    private int dpMemoized(int[] coins, int amount, int[] memo) {
        // Base cases
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // If already calculated, return the stored result
        if (memo[amount] != -2) return memo[amount];

        // Initialize result to "infinity"
        int minCoins = Integer.MAX_VALUE;

        // Try each coin
        for (int coin : coins) {
            int subproblem = dpMemoized(coins, amount - coin, memo);

            if (subproblem != -1) {
                minCoins = Math.min(minCoins, subproblem + 1);
            }
        }

        // Store and return the result
        memo[amount] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return memo[amount];
    }
}
