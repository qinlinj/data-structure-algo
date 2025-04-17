package org.qinlinj.algoframework._100_core_framework._150_dynamic_programming_framework._152_coin_change_problem;

/**
 * Dynamic Programming: Coin Change with Memoization
 * ------------------------------------------------
 * <p>
 * This class demonstrates how to apply memoization to the Coin Change problem,
 * significantly improving the performance of the recursive solution.
 * <p>
 * The approach uses the same recursive structure as the brute force solution,
 * but adds a "memo" array to store the results of already solved subproblems,
 * thereby eliminating redundant calculations.
 * <p>
 * Key insights:
 * 1. The memoization technique transforms an exponential algorithm to polynomial time
 * 2. This approach maintains the intuitive recursive formulation
 * 3. The "memo" array serves a similar purpose to the DP table in bottom-up approaches
 */
public class CoinChangeMemoization {
    /**
     * Memoized solution for Coin Change problem
     * <p>
     * Time Complexity: O(n * k) where n is the amount and k is the number of coin denominations
     * Space Complexity: O(n) for memoization array
     *
     * @param coins  Array of available coin denominations
     * @param amount Target amount to make change for
     * @return Minimum number of coins needed, or -1 if impossible
     */
    public int coinChange(int[] coins, int amount) {
        // Initialize memoization array with a special value (-666) indicating "not calculated"
        int[] memo = new int[amount + 1];
        java.util.Arrays.fill(memo, -666);

        // Call recursive helper with memoization
        return dp(coins, amount, memo);
    }

    /**
     * Recursive helper function with memoization
     *
     * @param coins  Array of available coin denominations
     * @param amount Current target amount to make change for
     * @param memo   Memoization array to avoid redundant calculations
     * @return Minimum coins needed for current amount, or -1 if impossible
     */
    private int dp(int[] coins, int amount, int[] memo) {
        // Base cases
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // Check memo to avoid redundant calculations
        if (memo[amount] != -666) {
            return memo[amount];
        }

        // Initialize result to "infinity"
        int res = Integer.MAX_VALUE;

        // Try each coin as the last coin in the solution
        for (int coin : coins) {
            // Calculate subproblem (amount - coin)
            int subProblem = dp(coins, amount - coin, memo);

            // Skip invalid subproblems
            if (subProblem == -1) continue;

            // Update the minimum number of coins needed
            // Add 1 to count the current coin
            res = Math.min(res, subProblem + 1);
        }

        // Store result in memo before returning
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }
}
