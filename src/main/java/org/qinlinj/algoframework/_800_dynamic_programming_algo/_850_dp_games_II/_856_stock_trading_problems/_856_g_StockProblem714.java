package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._856_stock_trading_problems;

/**
 * LEETCODE 714: BEST TIME TO BUY AND SELL STOCK WITH TRANSACTION FEE (k = infinity + fee)
 * <p>
 * PROBLEM DESCRIPTION:
 * You are given an array prices where prices[i] is the price of a given stock on the ith day,
 * and a non-negative integer fee representing a transaction fee.
 * You may complete as many transactions as you like, but you need to pay the transaction fee
 * for each transaction. A transaction consists of buying and then selling one share of the stock.
 * Return the maximum profit you can achieve.
 * <p>
 * CONSTRAINTS:
 * - Unlimited transactions (k = infinity)
 * - Transaction fee applied to each buy-sell cycle
 * - Cannot hold multiple stocks simultaneously
 * <p>
 * FRAMEWORK MODIFICATION:
 * The transaction fee modifies one of the transition equations:
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])         // No change
 * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)   // Fee applied when buying
 * <p>
 * ALTERNATIVE: Fee can be applied when selling:
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i] - fee)   // Fee when selling
 * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])         // No fee when buying
 * <p>
 * Both approaches are equivalent mathematically, but applying fee when buying
 * helps avoid integer overflow in some test cases.
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(1) for optimized version
 */

import java.util.*;

public class _856_g_StockProblem714 {

    public static void main(String[] args) {
        _856_g_StockProblem714 solution = new _856_g_StockProblem714();

        System.out.println("=== LEETCODE 714: BEST TIME TO BUY AND SELL STOCK WITH TRANSACTION FEE ===\n");

        // Test Case 1: [1,3,2,8,4,9], fee=2 -> Expected: 8
        int[] test1 = {1, 3, 2, 8, 4, 9};
        int fee1 = 2;
        System.out.println("Test Case 1: " + Arrays.toString(test1) + ", fee=" + fee1);
        System.out.println("DP Array result: " + solution.maxProfitDP(test1, fee1));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test1, fee1));
        System.out.println("Fee on sell result: " + solution.maxProfitFeeOnSell(test1, fee1));
        System.out.println("Greedy result: " + solution.maxProfitGreedy(test1, fee1));
        System.out.println("Expected: 8 ((8-1-2) + (9-4-2) = 5 + 3 = 8)");
        solution.compareWithNoFee(test1, fee1);

        // Test Case 2: [1,3,7,5,10,3], fee=3 -> Expected: 6
        int[] test2 = {1, 3, 7, 5, 10, 3};
        int fee2 = 3;
        System.out.println("Test Case 2: " + Arrays.toString(test2) + ", fee=" + fee2);
        System.out.println("DP Array result: " + solution.maxProfitDP(test2, fee2));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test2, fee2));
        System.out.println("Fee on sell result: " + solution.maxProfitFeeOnSell(test2, fee2));
        System.out.println("Greedy result: " + solution.maxProfitGreedy(test2, fee2));
        System.out.println("Expected: 6");
        solution.compareWithNoFee(test2, fee2);

        // Test Case 3: High fee scenario
        int[] test3 = {1, 2, 3, 4, 5};
        int fee3 = 2;
        System.out.println("Test Case 3: " + Arrays.toString(test3) + ", fee=" + fee3);
        System.out.println("Result: " + solution.maxProfitOptimized(test3, fee3));
        System.out.println("High fee reduces profit from multiple small transactions");
        solution.compareWithNoFee(test3, fee3);
        solution.demonstrateFeeImpact(test3);

        // Test Case 4: Fee elimination scenario
        int[] test4 = {1, 4, 2, 5, 3, 6};
        int fee4 = 2;
        System.out.println("Test Case 4: " + Arrays.toString(test4) + ", fee=" + fee4);
        System.out.println("Simulation result: " + solution.maxProfitSimulation(test4, fee4));
        solution.compareWithNoFee(test4, fee4);

        // Edge Cases
        System.out.println("=== EDGE CASES ===");

        int[] empty = {};
        System.out.println("Empty array: " + solution.maxProfitOptimized(empty, 2));

        int[] single = {5};
        System.out.println("Single element [5]: " + solution.maxProfitOptimized(single, 2));

        int[] decreasing = {5, 4, 3, 2, 1};
        System.out.println("Decreasing prices [5,4,3,2,1]: " + solution.maxProfitOptimized(decreasing, 1));

        // Fee higher than any possible profit
        int[] highFee = {1, 2, 3};
        System.out.println("High fee scenario [1,2,3] with fee=5: " + solution.maxProfitOptimized(highFee, 5));

        System.out.println("\n=== COMPREHENSIVE COMPARISON ===");
        int[] comparison = {1, 3, 2, 8, 4, 9};
        int fee = 2;

        _856_b_StockProblem121 problem121 = new _856_b_StockProblem121();
        _856_c_StockProblem122 problem122 = new _856_c_StockProblem122();
        _856_d_StockProblem123 problem123 = new _856_d_StockProblem123();
        _856_e_StockProblem188 problem188 = new _856_e_StockProblem188();
        _856_f_StockProblem309 problem309 = new _856_f_StockProblem309();

        System.out.println("Prices: " + Arrays.toString(comparison));
        System.out.println("Problem 121 (k=1): " + problem121.maxProfitOptimized(comparison));
        System.out.println("Problem 122 (k=∞): " + problem122.maxProfitOptimized(comparison));
        System.out.println("Problem 123 (k=2): " + problem123.maxProfitOptimized(comparison));
        System.out.println("Problem 188 (k=3): " + problem188.maxProfit(3, comparison));
        System.out.println("Problem 309 (k=∞, cooldown): " + problem309.maxProfitOptimized(comparison));
        System.out.println("Problem 714 (k=∞, fee=" + fee + "): " + solution.maxProfitOptimized(comparison, fee));

        System.out.println("\n=== FEE SENSITIVITY ANALYSIS ===");
        solution.demonstrateFeeImpact(comparison);

        // Test both fee application methods
        System.out.println("=== FEE APPLICATION METHODS ===");
        int[] feeTest = {1, 3, 2, 8, 4, 9};
        int testFee = 2;
        System.out.println("Prices: " + Arrays.toString(feeTest) + ", Fee: " + testFee);
        System.out.println("Fee on buy: " + solution.maxProfitOptimized(feeTest, testFee));
        System.out.println("Fee on sell: " + solution.maxProfitFeeOnSell(feeTest, testFee));
        System.out.println("Both methods should give the same result!");

        System.out.println("\n=== KEY INSIGHTS ===");
        System.out.println("1. Transaction fee discourages small-profit trades");
        System.out.println("2. Fee can be applied on buy or sell - mathematically equivalent");
        System.out.println("3. Higher fees lead to fewer but larger transactions");
        System.out.println("4. Greedy approach: only execute trades if profit > fee");
        System.out.println("5. Framework modification: add fee to one transition equation");
        System.out.println("6. Space optimization still achievable with O(1) complexity");
        System.out.println("7. Fee effectively filters out noise in price movements");
    }

    /**
     * Approach 1: DP Array Implementation (fee on buy)
     */
    public int maxProfitDP(int[] prices, int fee) {
        int n = prices.length;
        if (n <= 1) return 0;

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // Base case: first day
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee; // Pay fee when buying
                continue;
            }

            // Not holding stock
            dp[i][0] = Math.max(dp[i - 1][0],           // Rest
                    dp[i - 1][1] + prices[i]); // Sell (no additional fee)

            // Holding stock
            dp[i][1] = Math.max(dp[i - 1][1],                    // Rest
                    dp[i - 1][0] - prices[i] - fee); // Buy (pay fee)
        }

        return dp[n - 1][0];
    }

    /**
     * Approach 2: Space-Optimized Implementation
     */
    public int maxProfitOptimized(int[] prices, int fee) {
        int n = prices.length;
        if (n <= 1) return 0;

        int dp_i_0 = 0;                    // Not holding stock
        int dp_i_1 = Integer.MIN_VALUE;    // Holding stock

        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }

        return dp_i_0;
    }

    /**
     * Approach 3: Fee on selling (alternative implementation)
     */
    public int maxProfitFeeOnSell(int[] prices, int fee) {
        int n = prices.length;
        if (n <= 1) return 0;

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i] - fee); // Pay fee when selling
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);         // No fee when buying
        }

        return dp_i_0;
    }

    /**
     * Approach 4: Greedy approach with transaction tracking
     * Track potential profit and only execute when profit > fee
     */
    public int maxProfitGreedy(int[] prices, int fee) {
        if (prices.length <= 1) return 0;

        int profit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            // If current price is lower, update minimum
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            // If selling now would be profitable (after fee)
            else if (prices[i] - minPrice > fee) {
                profit += prices[i] - minPrice - fee;
                minPrice = prices[i] - fee; // Reset for next potential buy
            }
        }

        return profit;
    }

    /**
     * Approach 5: Transaction simulation with fee tracking
     */
    public int maxProfitSimulation(int[] prices, int fee) {
        if (prices.length <= 1) return 0;

        int totalProfit = 0;
        int buyPrice = -1; // -1 means not currently holding

        System.out.println("Transaction Simulation:");
        System.out.println("Day\tPrice\tAction\t\tProfit\tTotal");
        System.out.println("---\t-----\t------\t\t------\t-----");

        for (int i = 0; i < prices.length; i++) {
            if (buyPrice == -1) {
                // Not holding, consider buying
                if (i == prices.length - 1) {
                    // Last day, can't buy
                    System.out.printf("%d\t%d\tRest\t\t0\t%d%n", i, prices[i], totalProfit);
                } else {
                    // Look ahead to see if buying is worthwhile
                    int maxFuturePrice = 0;
                    for (int j = i + 1; j < prices.length; j++) {
                        maxFuturePrice = Math.max(maxFuturePrice, prices[j]);
                    }

                    if (maxFuturePrice - prices[i] > fee) {
                        buyPrice = prices[i];
                        System.out.printf("%d\t%d\tBuy\t\t-%d\t%d%n",
                                i, prices[i], prices[i] + fee, totalProfit);
                    } else {
                        System.out.printf("%d\t%d\tRest\t\t0\t%d%n", i, prices[i], totalProfit);
                    }
                }
            } else {
                // Currently holding, consider selling
                int profit = prices[i] - buyPrice - fee;
                if (profit > 0 || i == prices.length - 1) {
                    totalProfit += Math.max(0, profit);
                    System.out.printf("%d\t%d\tSell\t\t%d\t%d%n",
                            i, prices[i], Math.max(0, profit), totalProfit);
                    buyPrice = -1;
                } else {
                    System.out.printf("%d\t%d\tHold\t\t0\t%d%n", i, prices[i], totalProfit);
                }
            }
        }
        System.out.println();

        return totalProfit;
    }

    /**
     * Compare with no-fee scenario
     */
    public void compareWithNoFee(int[] prices, int fee) {
        _856_c_StockProblem122 noFee = new _856_c_StockProblem122();

        int withFee = maxProfitOptimized(prices, fee);
        int withoutFee = noFee.maxProfitOptimized(prices);

        System.out.println("Comparison - Prices: " + Arrays.toString(prices) + ", Fee: " + fee);
        System.out.println("Without fee: " + withoutFee);
        System.out.println("With fee: " + withFee);
        System.out.println("Fee impact: -" + (withoutFee - withFee));
        System.out.println();
    }

    /**
     * Demonstrate different fee amounts
     */
    public void demonstrateFeeImpact(int[] prices) {
        System.out.println("Fee Impact Analysis for " + Arrays.toString(prices));
        System.out.println("Fee\tProfit\tTransactions Affected");
        System.out.println("---\t------\t--------------------");

        for (int fee = 0; fee <= 5; fee++) {
            int profit = maxProfitOptimized(prices, fee);
            System.out.printf("%d\t%d\t%s%n", fee, profit,
                    fee == 0 ? "None" : "Small profitable trades eliminated");
        }
        System.out.println();
    }
}
