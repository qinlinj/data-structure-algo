package org.qinlinj.algoframework._100_core_framework._150_dynamic_programming_framework._151_fibonacci_sequence;

// @formatter:off
/**
 * Dynamic Programming: Bottom-Up Approach (Tabulation)
 * ---------------------------------------------------
 *
 * This class demonstrates the bottom-up (iterative) approach to dynamic programming,
 * which is often more intuitive and efficient than recursive approaches.
 *
 * Key concepts:
 * 1. Bottom-up DP builds solutions starting from the simplest subproblems (base cases)
 * 2. Solutions are stored in a "DP table" and built up iteratively
 * 3. This approach eliminates the need for recursion and its associated overhead
 * 4. The DP table in bottom-up approach is equivalent to the memo array in top-down approach
 *
 * Advantages of bottom-up over top-down:
 * - Avoids recursion stack overhead and potential stack overflow
 * - Often more efficient due to better memory locality
 * - Usually simpler to implement with loops rather than recursion
 * - Easier to optimize for space complexity in many cases
 */
public class DynamicProgrammingBottomUp {
    /**
     * Example: Fibonacci sequence with bottom-up approach
     *
     * This implementation builds the solution iteratively from the base cases up,
     * filling in the DP table from smallest to largest indices.
     *
     * Time complexity: O(n) - each subproblem computed exactly once
     * Space complexity: O(n) - for the DP table
     */
    public int fibonacci(int n) {
        if (n == 0) return 0;

        // Initialize DP table
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 0;
        dp[1] = 1;

        // Fill the table bottom-up (from smaller to larger subproblems)
        for (int i = 2; i <= n; i++) {
            // State transition: use previously computed values
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return the final answer
        return dp[n];
    }

    /**
     * Space-optimized Fibonacci calculation
     *
     * Since each Fibonacci calculation only depends on the two previous values,
     * we can optimize space complexity from O(n) to O(1).
     */
    public int fibonacciOptimized(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        // Only store the two most recent values
        int prev = 0;
        int curr = 1;

        for (int i = 2; i <= n; i++) {
            // Calculate next value
            int next = prev + curr;
            // Update for next iteration
            prev = curr;
            curr = next;
        }

        return curr;
    }

    /**
     * Example 2: Coin change problem with bottom-up approach
     *
     * Problem: Given coins of certain denominations and a target amount,
     * find the minimum number of coins needed to make up that amount.
     */
    public int coinChange(int[] coins, int amount) {
        // Initialize DP table with "infinity" (amount+1 is sufficient)
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);

        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;

        // Fill the table bottom-up for all amounts from 1 to target
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            // Try each coin denomination
            for (int coin : coins) {
                // If this coin can be used (not larger than current amount)
                if (coin <= currentAmount) {
                    // Update the minimum coins needed
                    // State transition equation: dp[i] = min(dp[i], dp[i-coin] + 1)
                    dp[currentAmount] = Math.min(dp[currentAmount], dp[currentAmount - coin] + 1);
                }
            }
        }

        // If dp[amount] is still "infinity", no solution exists
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Visualization of bottom-up DP for Fibonacci(5):
     *
     * DP table:
     *
     * Index:   0  1  2  3  4  5
     * Value:   0  1  1  2  3  5
     *             ↑  ↑  ↑  ↑  ↑
     * Computed   ↑  ↑  ↑  ↑  ↑
     * In order:  1  2  3  4  5
     *
     * The arrows indicate the direction of computation - from smaller to larger indices.
     * This is exactly the reverse of the top-down approach!
     */

    /**
     * Generic template for bottom-up dynamic programming:
     *
     * 1. Define the state and meaning of the DP table
     * 2. Initialize the DP table with base cases
     * 3. Iterate through states in a bottom-up manner
     * 4. Apply the state transition equation at each step
     * 5. Return the final answer from the appropriate cell of the DP table
     *
     * Example template:
     *
     * public Result solve(InputType input) {
     *     // 1. Initialize DP table
     *     Result[] dp = new Result[n + 1];
     *
     *     // 2. Set base cases
     *     dp[0] = baseValue;
     *
     *     // 3. Fill the table bottom-up
     *     for (State i = 1; i <= n; i++) {
     *         // 4. Consider all choices for current state
     *         for (Choice choice : possibleChoices(i)) {
     *             // 5. Apply state transition equation
     *             dp[i] = getBetterResult(dp[i], calculate(dp[i - something], choice));
     *         }
     *     }
     *
     *     // 6. Return final answer
     *     return dp[n];
     * }
     */

    /**
     * Bottom-up vs Top-down comparison:
     *
     * Bottom-up (Tabulation):
     * - Iterative approach
     * - Starts from base cases
     * - Builds table systematically
     * - No recursion overhead
     * - Often more intuitive visually
     * - Easier to optimize space complexity
     *
     * Top-down (Memoization):
     * - Recursive approach
     * - Starts from original problem
     * - Fills memo as needed
     * - Has recursion overhead
     * - Sometimes more intuitive to write
     * - Solution structure often follows problem description
     */
}
