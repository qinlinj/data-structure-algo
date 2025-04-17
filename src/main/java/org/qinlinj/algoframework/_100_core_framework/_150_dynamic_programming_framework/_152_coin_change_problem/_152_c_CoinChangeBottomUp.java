package org.qinlinj.algoframework._100_core_framework._150_dynamic_programming_framework._152_coin_change_problem;

// @formatter:off
/**
 * Dynamic Programming: Coin Change Bottom-Up Solution & Summary
 * -----------------------------------------------------------
 *
 * This class implements the bottom-up (iterative) approach for the Coin Change problem
 * and provides a comprehensive summary of dynamic programming concepts.
 *
 * Key Points About Dynamic Programming:
 *
 * 1. Dynamic Programming (DP) is fundamentally about efficient enumeration
 *    - At its core, DP is about solving problems by exploring all possibilities
 *    - The art is in finding "smart ways to enumerate" all possibilities
 *
 * 2. Three essential elements for DP problems:
 *    - Overlapping subproblems: Same subproblems are solved repeatedly
 *    - Optimal substructure: Optimal solution built from optimal solutions of subproblems
 *    - State transition equation: Mathematical relationship between states
 *
 * 3. General approach to solving DP problems:
 *    - Identify states and choices
 *    - Define the meaning of the dp array/function
 *    - Establish base cases
 *    - Write the state transition equation
 *    - Decide between top-down or bottom-up implementation
 *    - Optimize for space if possible
 *
 * This class demonstrates the bottom-up approach to the Coin Change problem,
 * which builds solutions iteratively from the base case up to the target amount.
 */
public class _152_c_CoinChangeBottomUp {
    /**
     * Bottom-up (tabulation) solution for Coin Change problem
     *
     * Time Complexity: O(n * k) where n is the amount and k is the number of coin denominations
     * Space Complexity: O(n) for dp array
     *
     * @param coins Array of available coin denominations
     * @param amount Target amount to make change for
     * @return Minimum number of coins needed, or -1 if impossible
     */
    public int coinChange(int[] coins, int amount) {
        // Initialize dp array
        // dp[i] represents the minimum number of coins needed to make amount i
        int[] dp = new int[amount + 1];

        // Fill dp array with a value larger than any possible answer
        // We use amount+1 instead of Integer.MAX_VALUE to avoid overflow
        java.util.Arrays.fill(dp, amount + 1);

        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;

        // Build the dp array bottom-up (from smaller to larger amounts)
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            // Try each coin denomination
            for (int coin : coins) {
                // If this coin can be used (not larger than current amount)
                if (currentAmount - coin >= 0) {
                    // State transition equation:
                    // dp[i] = min(dp[i], dp[i-coin] + 1)
                    dp[currentAmount] = Math.min(dp[currentAmount], 1 + dp[currentAmount - coin]);
                }
            }
        }

        // If dp[amount] is still amount+1, it means no solution exists
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
