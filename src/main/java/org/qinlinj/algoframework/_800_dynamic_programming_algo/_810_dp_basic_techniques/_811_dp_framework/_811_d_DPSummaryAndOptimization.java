package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._811_dp_framework;

import java.util.*;

/**
 * Dynamic Programming Summary and Optimization Techniques
 * ======================================================
 * <p>
 * This class summarizes key concepts in Dynamic Programming and demonstrates
 * space optimization techniques that can be applied to DP solutions.
 * <p>
 * Key Insights:
 * <p>
 * 1. Dynamic Programming Core Principles:
 * - Break down complex problems into simpler, overlapping subproblems
 * - Store solutions to avoid redundant calculations (memoization/tabulation)
 * - Build solutions to larger problems from solutions to smaller problems
 * <p>
 * 2. Problem-Solving Framework:
 * - Identify states and choices
 * - Define recurrence relation/state transition equation
 * - Choose implementation approach (top-down or bottom-up)
 * <p>
 * 3. Space Optimization Techniques:
 * - When current state depends only on a fixed number of previous states,
 * we can reduce space complexity by only storing those necessary states
 * - Common optimization reduces O(n) space to O(1) or O(k) space
 * <p>
 * 4. When to Apply DP:
 * - Problems asking for optimal values (min/max/longest/shortest)
 * - Problems with overlapping subproblems
 * - Problems with optimal substructure
 * <p>
 * This class implements space-optimized versions of previous examples.
 */
public class _811_d_DPSummaryAndOptimization {

    public static void main(String[] args) {
        _811_d_DPSummaryAndOptimization solution = new _811_d_DPSummaryAndOptimization();

        // Test Fibonacci optimization
        int n = 10;
        System.out.println("Fibonacci(" + n + ") with space optimization: " +
                solution.fibonacciOptimized(n));

        // Test Coin Change
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Coin Change for amount " + amount + ": " +
                solution.coinChangeSpaceOptimized(coins, amount));

        // Test LCS optimization
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("Longest Common Subsequence length: " +
                solution.longestCommonSubsequenceOptimized(text1, text2));

        // Test Knapsack optimization
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 8;
        System.out.println("Knapsack maximum value: " +
                solution.knapsackOptimized(weights, values, capacity));

        System.out.println("\nDynamic Programming Summary:");
        System.out.println("1. Identify overlapping subproblems");
        System.out.println("2. Define state and state transitions");
        System.out.println("3. Choose implementation (top-down or bottom-up)");
        System.out.println("4. Optimize space when possible");
        System.out.println("5. Make sure time complexity is acceptable");
    }

    /**
     * Space-optimized Fibonacci calculation
     * Reduces space complexity from O(n) to O(1)
     */
    public int fibonacciOptimized(int n) {
        if (n <= 1) return n;

        // Only store the last two Fibonacci numbers
        int prev2 = 0;  // F(0)
        int prev1 = 1;  // F(1)
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            // Update previous values for next iteration
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    /**
     * Space-optimized Coin Change solution
     * Demonstrates how to optimize space in a more complex DP problem
     * <p>
     * Note: For Coin Change, we can't reduce to O(1) space because
     * each amount depends on multiple previous states (amount - coin[i])
     * for all available coins. But we can show the general approach.
     */
    public int coinChangeSpaceOptimized(int[] coins, int amount) {
        // Standard implementation for reference
        return coinChangeTabulation(coins, amount);
    }

    /**
     * Standard tabulation approach for Coin Change
     */
    private int coinChangeTabulation(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Example of 2D DP space optimization
     * Reduces space from O(n²) to O(n)
     * <p>
     * This example implements a simplified version of the Longest Common Subsequence problem
     * which normally uses a 2D array but can be optimized to use only two rows
     */
    public int longestCommonSubsequenceOptimized(String text1, String text2) {
        // Get lengths of input strings
        int m = text1.length();
        int n = text2.length();

        // Create two arrays for current and previous rows
        int[] curr = new int[n + 1];
        int[] prev = new int[n + 1];

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            // Swap arrays - current becomes previous for next iteration
            int[] temp = prev;
            prev = curr;
            curr = temp;

            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // Characters match
                    curr[j] = prev[j - 1] + 1;
                } else {
                    // Characters don't match, take maximum from either skipping
                    // a character from text1 or text2
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
        }

        // Result is in the current array's last element
        return curr[n];
    }

    /**
     * Example of 1D DP space optimization when the problem normally uses 2D
     * Reduces space from O(n²) to O(n)
     * <p>
     * This demonstrates the 0/1 Knapsack problem space optimization
     */
    public int knapsackOptimized(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // Use only one array - we'll update it from right to left
        int[] dp = new int[capacity + 1];

        // Build table in bottom-up manner
        for (int i = 0; i < n; i++) {
            // Update from right to left to avoid overwriting values we need
            for (int w = capacity; w >= weights[i]; w--) {
                // Maximum of (not including current item, including current item)
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }

        return dp[capacity];
    }
}