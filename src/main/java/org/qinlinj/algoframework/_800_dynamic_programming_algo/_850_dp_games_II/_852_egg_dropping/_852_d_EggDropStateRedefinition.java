package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._852_egg_dropping;

/**
 * EGG DROPPING PROBLEM - STATE REDEFINITION APPROACH
 * <p>
 * REVOLUTIONARY PERSPECTIVE:
 * Instead of asking "Given K eggs and N floors, what's the minimum trials?"
 * We ask "Given K eggs and M trials, what's the maximum floors we can handle?"
 * <p>
 * STATE REDEFINITION:
 * dp[k][m] = maximum floors we can handle with k eggs and m trials
 * <p>
 * INTUITIVE REASONING:
 * When we drop an egg from floor F:
 * - If it breaks: we have (k-1) eggs and (m-1) trials for floors below F
 * - If it survives: we have k eggs and (m-1) trials for floors above F
 * - Total floors we can handle = floors_below + floors_above + current_floor
 * <p>
 * STATE TRANSITION:
 * dp[k][m] = dp[k-1][m-1] + dp[k][m-1] + 1
 * Where:
 * - dp[k-1][m-1]: floors below (egg breaks scenario)
 * - dp[k][m-1]: floors above (egg survives scenario)
 * - +1: current floor being tested
 * <p>
 * SOLUTION APPROACH:
 * Find minimum m such that dp[K][m] >= N
 * <p>
 * COMPLEXITY IMPROVEMENT:
 * Time: O(K * N) instead of O(K * N * log N)
 * Space: O(K * N)
 * <p>
 * KEY INSIGHTS:
 * 1. State redefinition can dramatically simplify problems
 * 2. "Reverse" thinking often leads to elegant solutions
 * 3. Optimal substructure becomes more apparent
 */

public class _852_d_EggDropStateRedefinition {
}
