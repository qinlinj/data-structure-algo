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

}
