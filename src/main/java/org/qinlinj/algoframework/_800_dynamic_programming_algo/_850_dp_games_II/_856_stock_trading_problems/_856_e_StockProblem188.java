package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._856_stock_trading_problems;

/**
 * LEETCODE 188: BEST TIME TO BUY AND SELL STOCK IV (General k)
 * <p>
 * PROBLEM DESCRIPTION:
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day,
 * and an integer k. Find the maximum profit you can achieve. You may complete at most k transactions.
 * Note: You may not engage in multiple transactions simultaneously.
 * <p>
 * CONSTRAINTS:
 * - At most k transactions allowed
 * - k can be any positive integer
 * - Must sell before buying again
 * - Cannot hold multiple stocks simultaneously
 * <p>
 * FRAMEWORK APPLICATION:
 * This is the most general form of the stock trading problem.
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 * <p>
 * KEY OPTIMIZATION:
 * If k >= n/2, then k is effectively unlimited (can make as many transactions as profitable)
 * because each transaction requires at least 2 days, so max possible transactions = n/2.
 * <p>
 * TIME COMPLEXITY: O(n*k) when k < n/2, O(n) when k >= n/2
 * SPACE COMPLEXITY: O(n*k) for DP array, can be optimized to O(k)
 */

import java.util.*;

public class _856_e_StockProblem188 {

    public static void main(String[] args) {
        _856_e_StockProblem188 solution = new _856_e_StockProblem188();

        System.out.println("=== LEETCODE 188: BEST TIME TO BUY AND SELL STOCK IV ===\n");

        // Test Case 1: k=2, prices=[2,4,1] -> Expected: 2
        int[] test1 = {2, 4, 1};
        int k1 = 2;
        System.out.println("Test Case 1: k=" + k1 + ", prices=" + Arrays.toString(test1));
        System.out.println("Main result: " + solution.maxProfit(k1, test1));
        System.out.println("Space optimized: " + solution.maxProfitSpaceOptimized(k1, test1));
        System.out.println("Alternative: " + solution.maxProfitAlternative(k1, test1));
        System.out.println("Expected: 2 (buy at 2, sell at 4)");
        solution.demonstrateStates(k1, test1);

        // Test Case 2: k=2, prices=[3,2,6,5,0,3] -> Expected: 7
        int[] test2 = {3, 2, 6, 5, 0, 3};
        int k2 = 2;
        System.out.println("Test Case 2: k=" + k2 + ", prices=" + Arrays.toString(test2));
        System.out.println("Main result: " + solution.maxProfit(k2, test2));
        System.out.println("Space optimized: " + solution.maxProfitSpaceOptimized(k2, test2));
        System.out.println("Alternative: " + solution.maxProfitAlternative(k2, test2));
        System.out.println("Expected: 7 (buy at 2, sell at 6, buy at 0, sell at 3)");
        solution.demonstrateStates(k2, test2);

        // Test Case 3: Large k (unlimited transactions)
        int[] test3 = {1, 2, 3, 4, 5};
        int k3 = 100;
        System.out.println("Test Case 3: k=" + k3 + ", prices=" + Arrays.toString(test3));
        System.out.println("Main result: " + solution.maxProfit(k3, test3));
        System.out.println("Space optimized: " + solution.maxProfitSpaceOptimized(k3, test3));
        System.out.println("Alternative: " + solution.maxProfitAlternative(k3, test3));
        System.out.println("Expected: 4 (k is large, so unlimited transactions)");
        solution.demonstrateStates(k3, test3);

        // Test Case 4: k=1 (compare with problem 121)
        int[] test4 = {7, 1, 5, 3, 6, 4};
        int k4 = 1;
        System.out.println("Test Case 4: k=" + k4 + ", prices=" + Arrays.toString(test4));
        System.out.println("Main result: " + solution.maxProfit(k4, test4));
        System.out.println("Space optimized: " + solution.maxProfitSpaceOptimized(k4, test4));
        System.out.println("Alternative: " + solution.maxProfitAlternative(k4, test4));
        System.out.println("Expected: 5 (same as problem 121)");
        solution.demonstrateStates(k4, test4);

        // Edge Cases
        System.out.println("=== EDGE CASES ===");

        System.out.println("Empty array: " + solution.maxProfit(2, new int[]{}));
        System.out.println("k=0: " + solution.maxProfit(0, new int[]{1, 2, 3}));
        System.out.println("Single price: " + solution.maxProfit(2, new int[]{5}));
        System.out.println("k > n/2 optimization: " + solution.maxProfit(1000, new int[]{1, 2, 3, 4, 5}));

        System.out.println("\n=== COMPREHENSIVE COMPARISON ===");
        int[] comparison = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("Prices: " + Arrays.toString(comparison));

        _856_b_StockProblem121 problem121 = new _856_b_StockProblem121();
        _856_c_StockProblem122 problem122 = new _856_c_StockProblem122();
        _856_d_StockProblem123 problem123 = new _856_d_StockProblem123();

        System.out.println("Problem 121 (k=1): " + problem121.maxProfitOptimized(comparison));
        System.out.println("Problem 122 (k=∞): " + problem122.maxProfitOptimized(comparison));
        System.out.println("Problem 123 (k=2): " + problem123.maxProfitOptimized(comparison));
        System.out.println("Problem 188 (k=1): " + solution.maxProfit(1, comparison));
        System.out.println("Problem 188 (k=2): " + solution.maxProfit(2, comparison));
        System.out.println("Problem 188 (k=3): " + solution.maxProfit(3, comparison));
        System.out.println("Problem 188 (k=100): " + solution.maxProfit(100, comparison));

        System.out.println("\n=== PERFORMANCE ANALYSIS ===");
        int[] largeArray = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeArray[i] = i % 10;
        }

        long startTime = System.nanoTime();
        solution.maxProfit(10, largeArray);
        long endTime = System.nanoTime();
        System.out.println("k=10, n=1000: " + (endTime - startTime) / 1000000.0 + " ms");

        startTime = System.nanoTime();
        solution.maxProfit(600, largeArray); // k >= n/2, should use unlimited strategy
        endTime = System.nanoTime();
        System.out.println("k=600, n=1000 (unlimited): " + (endTime - startTime) / 1000000.0 + " ms");

        System.out.println("\n=== KEY INSIGHTS ===");
        System.out.println("1. General framework handles all stock trading problems");
        System.out.println("2. Critical optimization: k >= n/2 means unlimited transactions");
        System.out.println("3. Space can be optimized from O(n*k) to O(k)");
        System.out.println("4. Time complexity: O(n*k) for small k, O(n) for large k");
        System.out.println("5. This problem unifies all previous stock trading variations");
        System.out.println("6. Framework: enumerate all states, apply transitions, handle base cases");
    }

    /**
     * Main solution handling all cases of k
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1 || k <= 0) return 0;

        // Optimization: if k is large enough, treat as unlimited transactions
        if (k >= n / 2) {
            return maxProfitUnlimited(prices);
        }

        // General case: k < n/2
        return maxProfitGeneral(k, prices);
    }

    /**
     * Handle unlimited transactions (when k >= n/2)
     */
    private int maxProfitUnlimited(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    /**
     * Handle general case with limited k
     */
    private int maxProfitGeneral(int k, int[] prices) {
        int n = prices.length;

        // dp[i][j][0/1] = max profit on day i with at most j transactions
        int[][][] dp = new int[n][k + 1][2];

        // Initialize base cases
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i - 1 == -1) {
                    // First day base cases
                    dp[i][j][0] = 0;
                    dp[i][j][1] = j > 0 ? -prices[i] : Integer.MIN_VALUE;
                    continue;
                }

                if (j == 0) {
                    // No transactions allowed
                    dp[i][j][0] = 0;
                    dp[i][j][1] = Integer.MIN_VALUE;
                    continue;
                }

                // State transitions
                dp[i][j][0] = Math.max(dp[i - 1][j][0],           // Rest
                        dp[i - 1][j][1] + prices[i]); // Sell

                dp[i][j][1] = Math.max(dp[i - 1][j][1],           // Rest
                        dp[i - 1][j - 1][0] - prices[i]); // Buy
            }
        }

        return dp[n - 1][k][0];
    }

    /**
     * Space-optimized version using rolling arrays
     */
    public int maxProfitSpaceOptimized(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1 || k <= 0) return 0;

        if (k >= n / 2) {
            return maxProfitUnlimited(prices);
        }

        // Use 2D array instead of 3D (eliminate day dimension)
        int[][] dp = new int[k + 1][2];

        // Initialize base cases
        for (int j = 0; j <= k; j++) {
            dp[j][0] = 0;
            dp[j][1] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < n; i++) {
            // Traverse k in reverse order to avoid using updated values
            for (int j = k; j >= 1; j--) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }

        return dp[k][0];
    }

    /**
     * Alternative implementation with explicit state management
     */
    public int maxProfitAlternative(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1 || k <= 0) return 0;

        if (k >= n / 2) {
            return maxProfitUnlimited(prices);
        }

        // buy[i] = max profit after at most i+1 transactions (currently holding)
        // sell[i] = max profit after at most i+1 transactions (not holding)
        int[] buy = new int[k];
        int[] sell = new int[k];

        // Initialize buy states to negative infinity (impossible initially)
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int i = k - 1; i >= 0; i--) {
                sell[i] = Math.max(sell[i], buy[i] + price);
                buy[i] = Math.max(buy[i], (i > 0 ? sell[i - 1] : 0) - price);
            }
        }

        return sell[k - 1];
    }

    /**
     * Debug method to show state transitions
     */
    public void demonstrateStates(int k, int[] prices) {
        int n = prices.length;
        System.out.println("State transitions for k=" + k + ":");
        System.out.println("Prices: " + Arrays.toString(prices));

        if (k >= n / 2) {
            System.out.println("k >= n/2, using unlimited transaction strategy");
            System.out.println("Result: " + maxProfitUnlimited(prices));
            return;
        }

        int[][] dp = new int[k + 1][2];
        for (int j = 0; j <= k; j++) {
            dp[j][0] = 0;
            dp[j][1] = Integer.MIN_VALUE;
        }

        System.out.println("\nDay\tPrice\tStates (k=1 to " + k + ")");
        System.out.println("---\t-----\t" + "-".repeat(20));

        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
            }

            System.out.printf("%d\t%d\t", i, prices[i]);
            for (int j = 1; j <= k; j++) {
                System.out.printf("[%d,%d] ",
                        dp[j][1] == Integer.MIN_VALUE ? -999 : dp[j][1],
                        dp[j][0]);
            }
            System.out.println();
        }
        System.out.println("Final result: " + dp[k][0]);
        System.out.println();
    }
}
