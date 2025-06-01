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
}
