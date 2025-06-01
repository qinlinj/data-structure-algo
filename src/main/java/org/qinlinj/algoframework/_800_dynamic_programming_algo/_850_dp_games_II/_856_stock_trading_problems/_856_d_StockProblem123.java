package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._856_stock_trading_problems;

/**
 * LEETCODE 123: BEST TIME TO BUY AND SELL STOCK III (k = 2)
 * <p>
 * PROBLEM DESCRIPTION:
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 * <p>
 * CONSTRAINTS:
 * - At most TWO transactions allowed (k = 2)
 * - Must sell before buying again
 * - Cannot hold multiple stocks simultaneously
 * <p>
 * FRAMEWORK APPLICATION:
 * Since k = 2 is small and fixed, we need to enumerate all states:
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 * <p>
 * For k = 1,2 we get 4 states total:
 * - dp[i][1][0], dp[i][1][1]: First transaction states
 * - dp[i][2][0], dp[i][2][1]: Second transaction states
 * <p>
 * OPTIMIZATION:
 * Can be optimized to O(1) space using state variables.
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(1) for optimized version, O(n*k) for DP array
 */

import java.util.*;

public class _856_d_StockProblem123 {

    public static void main(String[] args) {
        _856_d_StockProblem123 solution = new _856_d_StockProblem123();

        System.out.println("=== LEETCODE 123: BEST TIME TO BUY AND SELL STOCK III ===\n");

        // Test Case 1: [3,3,5,0,0,3,1,4] -> Expected: 6
        int[] test1 = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("Test Case 1: " + Arrays.toString(test1));
        System.out.println("DP Array result: " + solution.maxProfitDP(test1));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test1));
        System.out.println("Intuitive result: " + solution.maxProfitIntuitive(test1));
        System.out.println("Divide & Conquer result: " + solution.maxProfitDivideConquer(test1));
        System.out.println("Expected: 6 (buy at 0, sell at 3, buy at 1, sell at 4)");
        solution.demonstrateStateTransitions(test1);

        // Test Case 2: [1,2,3,4,5] -> Expected: 4
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 2: " + Arrays.toString(test2));
        System.out.println("DP Array result: " + solution.maxProfitDP(test2));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test2));
        System.out.println("Intuitive result: " + solution.maxProfitIntuitive(test2));
        System.out.println("Divide & Conquer result: " + solution.maxProfitDivideConquer(test2));
        System.out.println("Expected: 4 (single transaction: buy at 1, sell at 5)");
        solution.demonstrateStateTransitions(test2);

        // Test Case 3: [7,6,4,3,1] -> Expected: 0
        int[] test3 = {7, 6, 4, 3, 1};
        System.out.println("Test Case 3: " + Arrays.toString(test3));
        System.out.println("DP Array result: " + solution.maxProfitDP(test3));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test3));
        System.out.println("Intuitive result: " + solution.maxProfitIntuitive(test3));
        System.out.println("Divide & Conquer result: " + solution.maxProfitDivideConquer(test3));
        System.out.println("Expected: 0 (decreasing prices, no profitable transactions)");
        solution.demonstrateStateTransitions(test3);

        // Test Case 4: [1,2,4,2,5,7,2,4,9,0] -> More complex case
        int[] test4 = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        System.out.println("Test Case 4: " + Arrays.toString(test4));
        System.out.println("DP Array result: " + solution.maxProfitDP(test4));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test4));
        System.out.println("Intuitive result: " + solution.maxProfitIntuitive(test4));
        System.out.println("Divide & Conquer result: " + solution.maxProfitDivideConquer(test4));
        solution.demonstrateStateTransitions(test4);

        // Edge Cases
        System.out.println("=== EDGE CASES ===");

        int[] empty = {};
        System.out.println("Empty array: " + solution.maxProfitOptimized(empty));

        int[] single = {5};
        System.out.println("Single element [5]: " + solution.maxProfitOptimized(single));

        int[] two = {1, 2};
        System.out.println("Two elements [1,2]: " + solution.maxProfitOptimized(two));

        System.out.println("\n=== COMPARISON WITH PREVIOUS PROBLEMS ===");
        int[] comparison = {3, 3, 5, 0, 0, 3, 1, 4};
        _856_b_StockProblem121 problem121 = new _856_b_StockProblem121();
        _856_c_StockProblem122 problem122 = new _856_c_StockProblem122();

        System.out.println("Prices: " + Arrays.toString(comparison));
        System.out.println("Problem 121 (k=1): " + problem121.maxProfitOptimized(comparison));
        System.out.println("Problem 122 (k=∞): " + problem122.maxProfitOptimized(comparison));
        System.out.println("Problem 123 (k=2): " + solution.maxProfitOptimized(comparison));

        System.out.println("\n=== KEY INSIGHTS ===");
        System.out.println("1. k=2 requires tracking 4 distinct states");
        System.out.println("2. Update order matters in space-optimized solution");
        System.out.println("3. Can be solved by divide-and-conquer approach");
        System.out.println("4. Demonstrates transition from k=1 to general k framework");
        System.out.println("5. Space complexity can be reduced from O(n*k) to O(1)");
    }

    /**
     * Approach 1: Full DP Array Implementation
     */
    public int maxProfitDP(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int maxK = 2;
        // dp[i][k][0/1] = max profit on day i with at most k transactions, holding state
        int[][][] dp = new int[n][maxK + 1][2];

        // Initialize base cases
        for (int i = 0; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                if (i - 1 == -1) {
                    // Base case: first day
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }

                // State transitions
                dp[i][k][0] = Math.max(dp[i - 1][k][0],           // Rest without stock
                        dp[i - 1][k][1] + prices[i]); // Sell stock

                dp[i][k][1] = Math.max(dp[i - 1][k][1],           // Rest with stock
                        dp[i - 1][k - 1][0] - prices[i]); // Buy stock
            }
        }

        return dp[n - 1][maxK][0];
    }

    /**
     * Approach 2: Explicit State Variables (Space Optimized)
     */
    public int maxProfitOptimized(int[] prices) {
        if (prices.length <= 1) return 0;

        // Four states representing all possible combinations
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;  // First transaction
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;  // Second transaction

        for (int price : prices) {
            // Update in reverse order to avoid dependency issues
            // Second transaction (k=2)
            dp_i20 = Math.max(dp_i20, dp_i21 + price);     // Sell second stock
            dp_i21 = Math.max(dp_i21, dp_i10 - price);     // Buy second stock

            // First transaction (k=1)
            dp_i10 = Math.max(dp_i10, dp_i11 + price);     // Sell first stock
            dp_i11 = Math.max(dp_i11, -price);             // Buy first stock
        }

        return dp_i20; // Best profit after at most 2 transactions
    }

    /**
     * Approach 3: Alternative intuitive implementation
     * Track buy and sell prices for two transactions
     */
    public int maxProfitIntuitive(int[] prices) {
        if (prices.length <= 1) return 0;

        int buy1 = -prices[0], sell1 = 0;    // First transaction
        int buy2 = -prices[0], sell2 = 0;    // Second transaction

        for (int i = 1; i < prices.length; i++) {
            // First transaction
            buy1 = Math.max(buy1, -prices[i]);          // Buy at lowest price
            sell1 = Math.max(sell1, buy1 + prices[i]);  // Sell for max profit

            // Second transaction (can use profit from first)
            buy2 = Math.max(buy2, sell1 - prices[i]);   // Buy using first profit
            sell2 = Math.max(sell2, buy2 + prices[i]);  // Sell for max total profit
        }

        return sell2;
    }

    /**
     * Approach 4: Divide and conquer
     * Split array and find best single transaction in each part
     */
    public int maxProfitDivideConquer(int[] prices) {
        if (prices.length <= 1) return 0;

        int n = prices.length;

        // leftProfits[i] = max profit from single transaction in prices[0..i]
        int[] leftProfits = new int[n];
        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            leftProfits[i] = Math.max(leftProfits[i - 1], prices[i] - minPrice);
        }

        // rightProfits[i] = max profit from single transaction in prices[i..n-1]
        int[] rightProfits = new int[n];
        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            rightProfits[i] = Math.max(rightProfits[i + 1], maxPrice - prices[i]);
        }

        // Find maximum profit by trying all possible split points
        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit,
                    leftProfits[i] + (i + 1 < n ? rightProfits[i + 1] : 0));
        }

        return maxProfit;
    }

    /**
     * Demonstrate state transitions with detailed tracking
     */
    public void demonstrateStateTransitions(int[] prices) {
        int n = prices.length;
        System.out.println("State Transition Demonstration for k=2:");
        System.out.println("Day\tPrice\tT1_Hold\tT1_Sold\tT2_Hold\tT2_Sold");
        System.out.println("---\t-----\t-------\t-------\t-------\t-------");

        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int old_dp_i11 = dp_i11;
            int old_dp_i10 = dp_i10;
            int old_dp_i21 = dp_i21;
            int old_dp_i20 = dp_i20;

            dp_i20 = Math.max(dp_i20, dp_i21 + prices[i]);
            dp_i21 = Math.max(dp_i21, dp_i10 - prices[i]);
            dp_i10 = Math.max(dp_i10, dp_i11 + prices[i]);
            dp_i11 = Math.max(dp_i11, -prices[i]);

            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d%n",
                    i, prices[i],
                    dp_i11 == Integer.MIN_VALUE ? -999 : dp_i11,
                    dp_i10,
                    dp_i21 == Integer.MIN_VALUE ? -999 : dp_i21,
                    dp_i20);
        }
        System.out.println();
    }
}