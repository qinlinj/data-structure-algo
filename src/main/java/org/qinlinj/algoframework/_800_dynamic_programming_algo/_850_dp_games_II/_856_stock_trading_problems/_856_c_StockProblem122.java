package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._856_stock_trading_problems;

/**
 * LEETCODE 122: BEST TIME TO BUY AND SELL STOCK II (k = infinity)
 * <p>
 * PROBLEM DESCRIPTION:
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share
 * of the stock at any time. However, you can buy it then immediately sell it on the same day.
 * Return the maximum profit you can achieve.
 * <p>
 * CONSTRAINTS:
 * - Unlimited transactions allowed (k = infinity)
 * - Can buy and sell on the same day
 * - Cannot hold multiple stocks simultaneously
 * <p>
 * FRAMEWORK APPLICATION:
 * Since k = infinity, k and k-1 are equivalent, so:
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])  // Not holding
 * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])  // Holding
 * <p>
 * KEY INSIGHT:
 * With unlimited transactions, we can capture every profitable price increase.
 * This leads to a simple greedy solution: buy before every price increase,
 * sell before every price decrease.
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(1) for optimized version
 */

import java.util.*;

public class _856_c_StockProblem122 {

    public static void main(String[] args) {
        _856_c_StockProblem122 solution = new _856_c_StockProblem122();

        System.out.println("=== LEETCODE 122: BEST TIME TO BUY AND SELL STOCK II ===\n");

        // Test Case 1: [7,1,5,3,6,4] -> Expected: 7
        int[] test1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test Case 1: " + Arrays.toString(test1));
        System.out.println("DP Array result: " + solution.maxProfitDP(test1));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test1));
        System.out.println("Greedy result: " + solution.maxProfitGreedy(test1));
        System.out.println("Peak-Valley result: " + solution.maxProfitPeakValley(test1));
        System.out.println("Expected: 7 (multiple transactions: (1->5) + (3->6) = 4 + 3 = 7)");
        solution.demonstrateStateTransitions(test1);

        // Test Case 2: [1,2,3,4,5] -> Expected: 4
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 2: " + Arrays.toString(test2));
        System.out.println("DP Array result: " + solution.maxProfitDP(test2));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test2));
        System.out.println("Greedy result: " + solution.maxProfitGreedy(test2));
        System.out.println("Peak-Valley result: " + solution.maxProfitPeakValley(test2));
        System.out.println("Expected: 4 (buy at 1, sell at 5, or capture each daily increase)");
        solution.demonstrateStateTransitions(test2);

        // Test Case 3: [7,6,4,3,1] -> Expected: 0
        int[] test3 = {7, 6, 4, 3, 1};
        System.out.println("Test Case 3: " + Arrays.toString(test3));
        System.out.println("DP Array result: " + solution.maxProfitDP(test3));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test3));
        System.out.println("Greedy result: " + solution.maxProfitGreedy(test3));
        System.out.println("Peak-Valley result: " + solution.maxProfitPeakValley(test3));
        System.out.println("Expected: 0 (only decreasing prices)");
        solution.demonstrateStateTransitions(test3);

        // Test Case 4: [1,2,1,2,1] -> Expected: 2
        int[] test4 = {1, 2, 1, 2, 1};
        System.out.println("Test Case 4: " + Arrays.toString(test4));
        System.out.println("DP Array result: " + solution.maxProfitDP(test4));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test4));
        System.out.println("Greedy result: " + solution.maxProfitGreedy(test4));
        System.out.println("Peak-Valley result: " + solution.maxProfitPeakValley(test4));
        System.out.println("Expected: 2 (two transactions: (1->2) + (1->2) = 1 + 1 = 2)");
        solution.demonstrateStateTransitions(test4);

        // Edge Cases
        System.out.println("=== EDGE CASES ===");

        int[] empty = {};
        System.out.println("Empty array: " + solution.maxProfitOptimized(empty));

        int[] single = {5};
        System.out.println("Single element [5]: " + solution.maxProfitOptimized(single));

        int[] flat = {3, 3, 3, 3};
        System.out.println("Flat prices [3,3,3,3]: " + solution.maxProfitOptimized(flat));

        System.out.println("\n=== COMPARISON WITH PROBLEM 121 ===");
        int[] comparison = {7, 1, 5, 3, 6, 4};
        _856_b_StockProblem121 problem121 = new _856_b_StockProblem121();
        System.out.println("Prices: " + Arrays.toString(comparison));
        System.out.println("Problem 121 (k=1): " + problem121.maxProfitOptimized(comparison));
        System.out.println("Problem 122 (k=∞): " + solution.maxProfitOptimized(comparison));
        System.out.println("Difference shows the value of multiple transactions!");

        System.out.println("\n=== KEY INSIGHTS ===");
        System.out.println("1. Unlimited transactions allow capturing every profitable price move");
        System.out.println("2. Greedy solution works: sum all positive price differences");
        System.out.println("3. DP framework still applies with k and k-1 being equivalent");
        System.out.println("4. Peak-valley approach shows actual transaction strategy");
        System.out.println("5. Space optimization reduces from O(n) to O(1)");
    }

    /**
     * Approach 1: DP Array Implementation
     */
    public int maxProfitDP(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }

            // Not holding stock
            dp[i][0] = Math.max(dp[i - 1][0],           // Rest
                    dp[i - 1][1] + prices[i]); // Sell

            // Holding stock - key difference from problem 121
            dp[i][1] = Math.max(dp[i - 1][1],           // Rest
                    dp[i - 1][0] - prices[i]); // Buy (can use previous profit)
        }

        return dp[n - 1][0];
    }

    /**
     * Approach 2: Space-Optimized DP
     */
    public int maxProfitOptimized(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }

        return dp_i_0;
    }

    /**
     * Approach 3: Greedy Solution
     * Since we can do unlimited transactions, capture every profitable move
     */
    public int maxProfitGreedy(int[] prices) {
        if (prices.length <= 1) return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // If price increased, we should have bought yesterday and sell today
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }

    /**
     * Approach 4: Peak-Valley Approach with transaction tracking
     */
    public int maxProfitPeakValley(int[] prices) {
        if (prices.length <= 1) return 0;

        int profit = 0;
        List<String> transactions = new ArrayList<>();

        int i = 0;
        while (i < prices.length - 1) {
            // Find valley (local minimum)
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }

            if (i == prices.length - 1) break; // No more peaks

            int valley = i; // Buy point

            // Find peak (local maximum)
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }

            int peak = i; // Sell point

            profit += prices[peak] - prices[valley];
            transactions.add(String.format("Buy day %d (price=%d), Sell day %d (price=%d), Profit=%d",
                    valley, prices[valley], peak, prices[peak],
                    prices[peak] - prices[valley]));
        }

        System.out.println("Peak-Valley Transactions:");
        for (String transaction : transactions) {
            System.out.println("  " + transaction);
        }

        return profit;
    }

    /**
     * Demonstrate state transitions for unlimited transactions
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

            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);

            String action = "";
            if (dp_i_0 > old_dp_i_0) action += "Sell ";
            if (dp_i_1 > old_dp_i_1) action += "Buy ";
            if (action.isEmpty()) action = "Rest";

            System.out.printf("%d\t%d\t%d\t\t%d\t%s%n",
                    i, prices[i], dp_i_0,
                    dp_i_1 == Integer.MIN_VALUE ? -999 : dp_i_1,
                    action.trim());
        }
        System.out.println();
    }
}