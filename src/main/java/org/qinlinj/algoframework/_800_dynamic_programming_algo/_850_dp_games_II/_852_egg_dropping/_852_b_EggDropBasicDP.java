package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._852_egg_dropping;

/**
 * EGG DROPPING PROBLEM - BASIC DYNAMIC PROGRAMMING SOLUTION
 * <p>
 * STATE DEFINITION:
 * dp(K, N) = minimum trials needed in worst case with K eggs and N floors
 * <p>
 * STATE TRANSITIONS:
 * For each possible floor i (1 <= i <= N):
 * - If egg breaks at floor i: dp(K-1, i-1) + 1
 * - If egg doesn't break at floor i: dp(K, N-i) + 1
 * - Take maximum of these two (worst case)
 * - Take minimum across all floors i (optimal choice)
 * <p>
 * RECURRENCE RELATION:
 * dp(K, N) = 1 + min(max(dp(K-1, i-1), dp(K, N-i))) for i in [1, N]
 * <p>
 * BASE CASES:
 * - dp(K, 0) = 0 (no floors, no trials needed)
 * - dp(1, N) = N (one egg, must try linearly)
 * <p>
 * ALGORITHM FRAMEWORK:
 * 1. Identify states: (eggs, floors)
 * 2. Identify choices: which floor to try
 * 3. Define recurrence based on worst-case analysis
 * 4. Add memoization to avoid recomputation
 * <p>
 * TIME COMPLEXITY: O(K * N^2)
 * SPACE COMPLEXITY: O(K * N)
 */

public class _852_b_EggDropBasicDP {
}
