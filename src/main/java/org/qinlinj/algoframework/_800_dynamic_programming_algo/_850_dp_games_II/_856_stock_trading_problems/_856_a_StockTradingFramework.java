package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._856_stock_trading_problems;

/**
 * STOCK TRADING FRAMEWORK - STATE MACHINE DYNAMIC PROGRAMMING
 * <p>
 * COMPREHENSIVE SOLUTION FOR ALL STOCK TRADING PROBLEMS
 * <p>
 * CORE FRAMEWORK:
 * dp[i][k][s] where:
 * - i = day (0 to n-1)
 * - k = maximum allowed transactions (1 to K)
 * - s = holding state (0 = not holding, 1 = holding stock)
 * <p>
 * STATE TRANSITION EQUATIONS:
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * max(rest,           sell)
 * <p>
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 * max(rest,           buy)
 * <p>
 * BASE CASES:
 * dp[-1][...][0] = 0 (no stock before start = 0 profit)
 * dp[-1][...][1] = -infinity (impossible to hold stock before start)
 * dp[...][0][0] = 0 (no transactions allowed = 0 profit)
 * dp[...][0][1] = -infinity (impossible to hold with no transactions)
 * <p>
 * KEY INSIGHTS:
 * 1. Every problem is a variation of this framework
 * 2. Transaction starts when buying (k decreases on buy)
 * 3. Different constraints modify the base framework
 * 4. Space optimization possible when states only depend on previous day
 * <p>
 * PROBLEM VARIATIONS:
 * - k=1: Only one transaction allowed
 * - k=infinity: Unlimited transactions
 * - k=2: Exactly two transactions
 * - With cooldown: Must wait after selling
 * - With fee: Pay fee on each transaction
 * - General k: Any number of transactions
 */

public class _856_a_StockTradingFramework {

    /**
     * Demonstrates the framework with various problem scenarios
     */
    public static void main(String[] args) {
        _856_a_StockTradingFramework solution = new _856_a_StockTradingFramework();

        System.out.println("=== STOCK TRADING FRAMEWORK DEMONSTRATION ===\n");

        // Test array for demonstrations
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println("Stock prices: [1, 2, 3, 0, 2]\n");

        // Scenario 1: Single transaction (k=1)
        System.out.println("Scenario 1: Single transaction (k=1)");
        int result1 = solution.maxProfitAllInOne(1, prices, 0, 0);
        System.out.println("Result: " + result1);
        System.out.println("Expected: Buy at 1, sell at 3, profit = 2\n");

        // Scenario 2: Two transactions (k=2)
        System.out.println("Scenario 2: Two transactions (k=2)");
        int result2 = solution.maxProfitAllInOne(2, prices, 0, 0);
        System.out.println("Result: " + result2);
        System.out.println("Expected: Buy at 1, sell at 3, buy at 0, sell at 2, profit = 4\n");

        // Scenario 3: Unlimited transactions
        System.out.println("Scenario 3: Unlimited transactions");
        int result3 = solution.maxProfitAllInOne(1000, prices, 0, 0);
        System.out.println("Result: " + result3);
        System.out.println("Expected: Same as scenario 2, profit = 4\n");

        // Scenario 4: With cooldown
        System.out.println("Scenario 4: Unlimited transactions with 1-day cooldown");
        int result4 = solution.maxProfitAllInOne(1000, prices, 1, 0);
        System.out.println("Result: " + result4);
        System.out.println("Expected: Buy at 1, sell at 3, wait 1 day, buy at 0, sell at 2, profit = 4\n");

        // Scenario 5: With transaction fee
        System.out.println("Scenario 5: Unlimited transactions with fee = 1");
        int result5 = solution.maxProfitAllInOne(1000, prices, 0, 1);
        System.out.println("Result: " + result5);
        System.out.println("Expected: Buy at 1, sell at 3 (profit=2-1=1), buy at 0, sell at 2 (profit=2-1=1), total = 2\n");

        // Scenario 6: Complex case with all constraints
        System.out.println("Scenario 6: k=2, cooldown=1, fee=1");
        int result6 = solution.maxProfitAllInOne(2, prices, 1, 1);
        System.out.println("Result: " + result6);
        System.out.println("Expected: Limited by multiple constraints\n");

        // Edge cases
        System.out.println("=== EDGE CASES ===");

        // Empty array
        int[] empty = {};
        System.out.println("Empty array: " + solution.maxProfitAllInOne(2, empty, 0, 0));

        // Single price
        int[] single = {5};
        System.out.println("Single price [5]: " + solution.maxProfitAllInOne(2, single, 0, 0));

        // Decreasing prices
        int[] decreasing = {5, 4, 3, 2, 1};
        System.out.println("Decreasing prices [5,4,3,2,1]: " +
                solution.maxProfitAllInOne(2, decreasing, 0, 0));

        System.out.println("\n=== FRAMEWORK SUMMARY ===");
        System.out.println("The state machine framework can solve ALL stock trading problems:");
        System.out.println("1. Define states: dp[day][transactions][holding_status]");
        System.out.println("2. Apply constraints through modified transitions");
        System.out.println("3. Handle base cases carefully");
        System.out.println("4. Optimize space when possible");
    }

    /**
     * ULTIMATE SOLUTION: Handles all constraints simultaneously
     *
     * @param maxK     Maximum number of transactions
     * @param prices   Stock prices array
     * @param cooldown Days to wait after selling before buying again
     * @param fee      Transaction fee per trade
     * @return Maximum profit achievable
     */
    public int maxProfitAllInOne(int maxK, int[] prices, int cooldown, int fee) {
        int n = prices.length;
        if (n <= 0) return 0;

        // Optimization: if k is large enough, treat as unlimited transactions
        if (maxK > n / 2) {
            return maxProfitUnlimited(prices, cooldown, fee);
        }

        // dp[i][k][0/1] - day i, max k transactions, holding state
        int[][][] dp = new int[n][maxK + 1][2];

        // Initialize base cases for k=0
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;           // No transactions, no stock
            dp[i][0][1] = Integer.MIN_VALUE; // Impossible state
        }

        for (int i = 0; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                if (i - 1 == -1) {
                    // Base case: first day
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i] - fee;
                    continue;
                }

                if (i - cooldown - 1 < 0) {
                    // Base case: within cooldown period
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], -prices[i] - fee);
                    continue;
                }

                // General case with cooldown and fee
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1],
                        dp[i - cooldown - 1][k - 1][0] - prices[i] - fee);
            }
        }

        return dp[n - 1][maxK][0];
    }

    /**
     * Helper method for unlimited transactions with cooldown and fee
     */
    private int maxProfitUnlimited(int[] prices, int cooldown, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
                continue;
            }

            if (i - cooldown - 1 < 0) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i] - fee);
                continue;
            }

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - cooldown - 1][0] - prices[i] - fee);
        }

        return dp[n - 1][0];
    }
}