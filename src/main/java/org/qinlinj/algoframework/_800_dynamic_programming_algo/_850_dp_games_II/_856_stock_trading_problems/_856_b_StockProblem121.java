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

public class _856_b_StockProblem121 {
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

}
