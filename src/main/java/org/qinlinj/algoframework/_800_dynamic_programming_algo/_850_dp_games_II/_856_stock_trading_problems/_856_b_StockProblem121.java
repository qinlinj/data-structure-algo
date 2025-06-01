package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._856_stock_trading_problems;

/**
 * LEETCODE 121: BEST TIME TO BUY AND SELL STOCK (k = 1)
 * <p>
 * PROBLEM DESCRIPTION:
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing
 * a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If no profit, return 0.
 * <p>
 * CONSTRAINTS:
 * - Only ONE transaction allowed (k = 1)
 * - Must buy before sell
 * - Cannot hold multiple stocks simultaneously
 * <p>
 * FRAMEWORK APPLICATION:
 * Since k = 1, the state transition becomes:
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])  // Not holding
 * dp[i][1] = max(dp[i-1][1], -prices[i])              // Holding (k-1=0, so dp[i-1][0][0]=0)
 * <p>
 * OPTIMIZATION:
 * Since only adjacent states matter, we can use O(1) space instead of O(n).
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(1) for optimized version, O(n) for DP array version
 */

import java.util.*;

public class _856_b_StockProblem121 {

    public static void main(String[] args) {
        _856_b_StockProblem121 solution = new _856_b_StockProblem121();

        System.out.println("=== LEETCODE 121: BEST TIME TO BUY AND SELL STOCK ===\n");

        // Test Case 1: [7,1,5,3,6,4] -> Expected: 5
        int[] test1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test Case 1: " + Arrays.toString(test1));
        System.out.println("DP Array result: " + solution.maxProfitDP(test1));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test1));
        System.out.println("Intuitive result: " + solution.maxProfitIntuitive(test1));
        System.out.println("Expected: 5 (buy at 1, sell at 6)");
        solution.demonstrateStateTransitions(test1);

        // Test Case 2: [7,6,4,3,1] -> Expected: 0
        int[] test2 = {7, 6, 4, 3, 1};
        System.out.println("Test Case 2: " + Arrays.toString(test2));
        System.out.println("DP Array result: " + solution.maxProfitDP(test2));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test2));
        System.out.println("Intuitive result: " + solution.maxProfitIntuitive(test2));
        System.out.println("Expected: 0 (prices only decrease)");
        solution.demonstrateStateTransitions(test2);

        // Test Case 3: [1,2,3,4,5] -> Expected: 4
        int[] test3 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 3: " + Arrays.toString(test3));
        System.out.println("DP Array result: " + solution.maxProfitDP(test3));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test3));
        System.out.println("Intuitive result: " + solution.maxProfitIntuitive(test3));
        System.out.println("Expected: 4 (buy at 1, sell at 5)");
        solution.demonstrateStateTransitions(test3);

        // Edge Cases
        System.out.println("=== EDGE CASES ===");

        int[] empty = {};
        System.out.println("Empty array: " + solution.maxProfitOptimized(empty));

        int[] single = {5};
        System.out.println("Single element [5]: " + solution.maxProfitOptimized(single));

        int[] two = {1, 2};
        System.out.println("Two elements [1,2]: " + solution.maxProfitOptimized(two));

        System.out.println("\n=== KEY INSIGHTS ===");
        System.out.println("1. This is the simplest case of the stock trading framework");
        System.out.println("2. k=1 simplifies the transition equations significantly");
        System.out.println("3. The 'holding' state can never have profit > 0 (since we can only buy once)");
        System.out.println("4. Space optimization is possible due to Markov property");
        System.out.println("5. Alternative solution: track min price and max profit simultaneously");
    }

    /**
     * Approach 1: Basic DP array implementation
     */
    public int maxProfitDP(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        // dp[i][0] = max profit on day i without holding stock
        // dp[i][1] = max profit on day i while holding stock
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // Base case: first day
                dp[i][0] = 0;           // Don't buy = 0 profit
                dp[i][1] = -prices[i];  // Buy stock = -price
                continue;
            }

            // Not holding stock today
            dp[i][0] = Math.max(dp[i - 1][0],           // Didn't hold yesterday, rest
                    dp[i - 1][1] + prices[i]); // Held yesterday, sell today

            // Holding stock today
            dp[i][1] = Math.max(dp[i - 1][1],    // Held yesterday, rest
                    -prices[i]);    // Buy today (since k=1, previous profit = 0)
        }

        return dp[n - 1][0]; // Must not be holding stock at the end for max profit
    }

    /**
     * Approach 2: Space-optimized O(1) solution
     */
    public int maxProfitOptimized(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        // Use variables instead of array since we only need previous state
        int dp_i_0 = 0;              // Max profit not holding stock
        int dp_i_1 = Integer.MIN_VALUE; // Max profit holding stock

        for (int i = 0; i < n; i++) {
            // Update in correct order to avoid using updated values
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }

        return dp_i_0;
    }

    /**
     * Approach 3: Alternative intuitive solution
     * Track minimum price seen so far and maximum profit
     */
    public int maxProfitIntuitive(int[] prices) {
        if (prices.length <= 1) return 0;

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Update minimum price seen so far
            minPrice = Math.min(minPrice, prices[i]);

            // Calculate profit if we sell today
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }

    /**
     * Helper method to demonstrate the DP state transitions
     */
    public void demonstrateStateTransitions(int[] prices) {
        int n = prices.length;
        System.out.println("State Transition Demonstration:");
        System.out.println("Day\tPrice\tNot Holding\tHolding\tAction");
        System.out.println("---\t-----\t-----------\t-------\t------");

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int old_dp_i_0 = dp_i_0;
            int old_dp_i_1 = dp_i_1;

            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);

            String action = "";
            if (i == 0) {
                action = dp_i_1 == -prices[i] ? "Buy" : "Rest";
            } else {
                if (dp_i_0 > old_dp_i_0) action += "Sell ";
                if (dp_i_1 > old_dp_i_1) action += "Buy ";
                if (action.isEmpty()) action = "Rest";
            }

            System.out.printf("%d\t%d\t%d\t\t%d\t%s%n",
                    i, prices[i], dp_i_0,
                    dp_i_1 == Integer.MIN_VALUE ? -999 : dp_i_1,
                    action.trim());
        }
        System.out.println();
    }
}