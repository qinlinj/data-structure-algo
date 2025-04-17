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

/**
 * Dynamic Programming Summary
 *
 * 1. Framework for Solving DP Problems:
 *    a. Define the state variables
 *    b. Define the dp array/function meaning
 *    c. Define base cases
 *    d. Define state transitions
 *    e. Choose implementation approach (recursive with memoization or iterative)
 *
 * 2. Similarities and Differences:
 *    - Recursive (top-down) with memoization:
 *      * Starts from the original problem
 *      * Breaks down into subproblems
 *      * Uses memoization to avoid redundant calculations
 *      * Usually more intuitive to write
 *
 *    - Iterative (bottom-up) with tabulation:
 *      * Starts from base cases
 *      * Builds up to the original problem
 *      * Systematically fills a dp table
 *      * Usually more efficient (avoids recursion overhead)
 *
 * 3. Key Insight: The most challenging part is identifying the state transition equation.
 *    - This equation directly represents the recursive structure of the problem
 *    - Once you have this equation, the rest is implementation details
 *
 * 4. Space Optimization:
 *    - Often the final step in optimizing a DP solution
 *    - Based on analyzing which previous states are actually needed
 */

/**
 * Final Thoughts on Dynamic Programming:
 *
 * 1. Computer problem-solving is fundamentally about enumeration (trying all possibilities)
 *
 * 2. Algorithm design involves two steps:
 *    - Figuring out how to enumerate all possibilities
 *    - Finding smart ways to enumerate efficiently
 *
 * 3. DP is powerful because it:
 *    - Eliminates redundant calculations through memoization/tabulation
 *    - Provides a systematic approach to problem decomposition
 *    - Trades space for time, which is often a beneficial tradeoff
 *
 * 4. The key to mastering DP is focusing on:
 *    - Identifying states and choices
 *    - Formulating clear state transition equations
 *    - Practice, practice, practice!
 */
public class _152_c_CoinChangeBottomUp {
    /**
     * Visual explanation of dp array for coins=[1,2,5], amount=11:
     *
     * Index (amount): 0  1  2  3  4  5  6  7  8  9  10  11
     * dp value:       0  1  1  2  2  1  2  2  3  3  2   3
     *
     * For example, at amount=6:
     * - Using coin=1: dp[6] = min(dp[6], dp[5] + 1) = min(∞, 1+1) = 2
     * - Using coin=2: dp[6] = min(dp[6], dp[4] + 1) = min(2, 2+1) = 2
     * - Using coin=5: dp[6] = min(dp[6], dp[1] + 1) = min(2, 1+1) = 2
     *
     * So dp[6] = 2, meaning we need at least 2 coins to make amount 6.
     */
    public static void main(String[] args) {
        _152_c_CoinChangeBottomUp solver = new _152_c_CoinChangeBottomUp();

        // Example 1: Standard case
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Example 1: coins = [1, 2, 5], amount = 11");
        System.out.println("Minimum coins needed: " + solver.coinChange(coins1, amount1));
        System.out.println("Expected: 3 (5 + 5 + 1)");

        // Example 2: No solution
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("\nExample 2: coins = [2], amount = 3");
        System.out.println("Minimum coins needed: " + solver.coinChange(coins2, amount2));
        System.out.println("Expected: -1 (impossible to make this amount)");

        // Example 3: Zero amount
        int[] coins3 = {1, 2, 5};
        int amount3 = 0;
        System.out.println("\nExample 3: coins = [1, 2, 5], amount = 0");
        System.out.println("Minimum coins needed: " + solver.coinChange(coins3, amount3));
        System.out.println("Expected: 0 (no coins needed for zero amount)");

        // Performance test
        int[] coins4 = {1, 2, 5};
        int amount4 = 10000;
        long start = System.nanoTime();
        int result = solver.coinChange(coins4, amount4);
        long end = System.nanoTime();

        System.out.println("\nPerformance Test: amount = " + amount4);
        System.out.println("Result: " + result);
        System.out.println("Time taken (ms): " + (end - start) / 1_000_000.0);
    }

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
