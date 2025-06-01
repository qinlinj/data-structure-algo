package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._856_stock_trading_problems;

/**
 * LEETCODE 309: BEST TIME TO BUY AND SELL STOCK WITH COOLDOWN (k = infinity + cooldown)
 * <p>
 * PROBLEM DESCRIPTION:
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * with the following restrictions:
 * - After you sell your stock, you cannot buy stock on the next day (i.e., cooldown 1 day).
 * - You may not engage in multiple transactions simultaneously.
 * <p>
 * CONSTRAINTS:
 * - Unlimited transactions (k = infinity)
 * - 1-day cooldown period after selling
 * - Cannot hold multiple stocks simultaneously
 * <p>
 * FRAMEWORK MODIFICATION:
 * The cooldown constraint modifies the buy transition:
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])     // Same as before
 * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])     // Must wait 1 day after selling
 * <p>
 * STATE INTERPRETATION:
 * dp[i][0] = max profit on day i not holding stock
 * dp[i][1] = max profit on day i holding stock
 * <p>
 * The key insight: when buying on day i, we must use profit from day i-2 (not i-1)
 * because of the mandatory cooldown period.
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(1) for optimized version
 */

import java.util.*;

public class _856_f_StockProblem309 {

    public static void main(String[] args) {
        _856_f_StockProblem309 solution = new _856_f_StockProblem309();

        System.out.println("=== LEETCODE 309: BEST TIME TO BUY AND SELL STOCK WITH COOLDOWN ===\n");

        // Test Case 1: [1,2,3,0,2] -> Expected: 3
        int[] test1 = {1, 2, 3, 0, 2};
        System.out.println("Test Case 1: " + Arrays.toString(test1));
        System.out.println("DP Array result: " + solution.maxProfitDP(test1));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test1));
        System.out.println("Three-state result: " + solution.maxProfitThreeState(test1));
        System.out.println("Explicit cooldown result: " + solution.maxProfitExplicitCooldown(test1));
        System.out.println("Expected: 3 (buy->sell->cooldown->buy->sell)");
        solution.demonstrateStateTransitions(test1);
        solution.compareWithNoCooldown(test1);

        // Test Case 2: [1] -> Expected: 0
        int[] test2 = {1};
        System.out.println("Test Case 2: " + Arrays.toString(test2));
        System.out.println("Result: " + solution.maxProfitOptimized(test2));
        System.out.println("Expected: 0 (single day, no transaction possible)");
        System.out.println();

        // Test Case 3: [1,2,4] -> Expected: 3
        int[] test3 = {1, 2, 4};
        System.out.println("Test Case 3: " + Arrays.toString(test3));
        System.out.println("DP Array result: " + solution.maxProfitDP(test3));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test3));
        System.out.println("Three-state result: " + solution.maxProfitThreeState(test3));
        System.out.println("Expected: 3 (buy at 1, sell at 4)");
        solution.demonstrateStateTransitions(test3);
        solution.compareWithNoCooldown(test3);

        // Test Case 4: [2,1,4,5,2,9,7] -> More complex case
        int[] test4 = {2, 1, 4, 5, 2, 9, 7};
        System.out.println("Test Case 4: " + Arrays.toString(test4));
        System.out.println("DP Array result: " + solution.maxProfitDP(test4));
        System.out.println("Optimized result: " + solution.maxProfitOptimized(test4));
        System.out.println("Three-state result: " + solution.maxProfitThreeState(test4));
        System.out.println("Explicit cooldown result: " + solution.maxProfitExplicitCooldown(test4));
        solution.demonstrateStateTransitions(test4);
        solution.compareWithNoCooldown(test4);

        // Test Case 5: Cooldown makes a difference
        int[] test5 = {1, 2, 1, 2, 1};
        System.out.println("Test Case 5: " + Arrays.toString(test5));
        System.out.println("Result: " + solution.maxProfitOptimized(test5));
        solution.demonstrateStateTransitions(test5);
        solution.compareWithNoCooldown(test5);

        // Edge Cases
        System.out.println("=== EDGE CASES ===");

        int[] empty = {};
        System.out.println("Empty array: " + solution.maxProfitOptimized(empty));

        int[] decreasing = {5, 4, 3, 2, 1};
        System.out.println("Decreasing prices [5,4,3,2,1]: " + solution.maxProfitOptimized(decreasing));
        solution.compareWithNoCooldown(decreasing);

        int[] flat = {3, 3, 3, 3};
        System.out.println("Flat prices [3,3,3,3]: " + solution.maxProfitOptimized(flat));

        System.out.println("\n=== KEY INSIGHTS ===");
        System.out.println("1. Cooldown constraint modifies the buy transition equation");
        System.out.println("2. Must use profit from i-2 when buying on day i");
        System.out.println("3. Can be modeled as 3-state machine: held, sold, rest");
        System.out.println("4. Cooldown typically reduces profit compared to unlimited transactions");
        System.out.println("5. Space optimization still possible with careful variable management");
        System.out.println("6. Framework flexibility: constraints modify transitions, not structure");
    }

    /**
     * Approach 1: Standard DP Array Implementation
     */
    public int maxProfitDP(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // Base case 1: first day
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }

            if (i - 2 == -1) {
                // Base case 2: second day (no cooldown violation possible yet)
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); // Can't use dp[i-2][0]
                continue;
            }

            // General case with cooldown
            dp[i][0] = Math.max(dp[i - 1][0],           // Rest without stock
                    dp[i - 1][1] + prices[i]); // Sell stock

            dp[i][1] = Math.max(dp[i - 1][1],           // Rest with stock
                    dp[i - 2][0] - prices[i]); // Buy after cooldown
        }

        return dp[n - 1][0];
    }

    /**
     * Approach 2: Space-Optimized Implementation
     */
    public int maxProfitOptimized(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int dp_i_0 = 0;              // Not holding stock today
        int dp_i_1 = Integer.MIN_VALUE; // Holding stock today
        int dp_pre_0 = 0;            // Not holding stock 2 days ago

        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }

        return dp_i_0;
    }

    /**
     * Approach 3: Three-State Machine
     * Explicitly model the three possible states:
     * - held: holding stock
     * - sold: just sold stock (in cooldown)
     * - rest: not holding stock and not in cooldown
     */
    public int maxProfitThreeState(int[] prices) {
        if (prices.length <= 1) return 0;

        int held = -prices[0];    // Holding stock
        int sold = 0;             // Just sold (in cooldown)
        int rest = 0;             // Not holding, not in cooldown

        for (int i = 1; i < prices.length; i++) {
            int prevHeld = held;
            int prevSold = sold;
            int prevRest = rest;

            held = Math.max(prevHeld, prevRest - prices[i]); // Keep holding or buy
            sold = prevHeld + prices[i];                      // Must sell if want to sold state
            rest = Math.max(prevRest, prevSold);             // Rest or finish cooldown
        }

        return Math.max(sold, rest); // Can't end while holding stock
    }

    /**
     * Approach 4: Alternative DP with explicit cooldown tracking
     */
    public int maxProfitExplicitCooldown(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        // buy[i] = max profit on day i after buying (holding stock)
        // sell[i] = max profit on day i after selling (in cooldown)
        // rest[i] = max profit on day i after resting (can buy tomorrow)

        int[] buy = new int[n];
        int[] sell = new int[n];
        int[] rest = new int[n];

        buy[0] = -prices[0];
        sell[0] = 0;
        rest[0] = 0;

        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], rest[i - 1] - prices[i]);
            sell[i] = buy[i - 1] + prices[i];
            rest[i] = Math.max(rest[i - 1], sell[i - 1]);
        }

        return Math.max(sell[n - 1], rest[n - 1]);
    }

    /**
     * Demonstrate state transitions with cooldown visualization
     */
    public void demonstrateStateTransitions(int[] prices) {
        int n = prices.length;
        System.out.println("State Transition Demonstration with Cooldown:");
        System.out.println("Day\tPrice\tHeld\tSold\tRest\tAction");
        System.out.println("---\t-----\t----\t----\t----\t------");

        int held = -prices[0];
        int sold = 0;
        int rest = 0;

        System.out.printf("0\t%d\t%d\t%d\t%d\tBuy%n", prices[0], held, sold, rest);

        for (int i = 1; i < n; i++) {
            int prevHeld = held;
            int prevSold = sold;
            int prevRest = rest;

            held = Math.max(prevHeld, prevRest - prices[i]);
            sold = prevHeld + prices[i];
            rest = Math.max(prevRest, prevSold);

            String action = "";
            if (held > prevHeld) action += "Buy ";
            if (sold > prevSold) action += "Sell ";
            if (rest == prevSold && prevSold > prevRest) action += "Cooldown ";
            if (action.isEmpty()) action = "Rest";

            System.out.printf("%d\t%d\t%d\t%d\t%d\t%s%n",
                    i, prices[i], held, sold, rest, action.trim());
        }
        System.out.println();
    }

    /**
     * Compare with unlimited transactions (no cooldown)
     */
    public void compareWithNoCooldown(int[] prices) {
        _856_c_StockProblem122 noCooldown = new _856_c_StockProblem122();

        int withCooldown = maxProfitOptimized(prices);
        int withoutCooldown = noCooldown.maxProfitOptimized(prices);

        System.out.println("Comparison - Prices: " + Arrays.toString(prices));
        System.out.println("Without cooldown: " + withoutCooldown);
        System.out.println("With cooldown: " + withCooldown);
        System.out.println("Cooldown impact: -" + (withoutCooldown - withCooldown));
        System.out.println();
    }
}
