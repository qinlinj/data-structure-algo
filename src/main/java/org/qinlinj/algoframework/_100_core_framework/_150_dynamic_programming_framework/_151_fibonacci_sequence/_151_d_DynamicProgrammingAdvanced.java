package org.qinlinj.algoframework._100_core_framework._150_dynamic_programming_framework._151_fibonacci_sequence;

// @formatter:off
/**
 * Dynamic Programming: State Transition Equations & Space Optimization
 * -------------------------------------------------------------------
 *
 * This class explores two critical aspects of dynamic programming:
 * 1. The importance of state transition equations
 * 2. Space optimization techniques
 *
 * Key concepts:
 * - State transition equation: The mathematical representation of how a problem's solution
 *   at one state relates to solutions at other states. It's the core of dynamic programming.
 * - Space optimization: Techniques to reduce memory usage by only storing necessary states.
 *
 * Understanding state transition equations:
 * - They directly represent the recursive (brute force) solution
 * - They describe how the current state depends on previous states
 * - Identifying them is often the most challenging part of solving DP problems
 * - All DP implementations (recursive, memoized, tabulated) are built around them
 */
public class _151_d_DynamicProgrammingAdvanced {
    /**
     * Example 1: Space-optimized Fibonacci implementation
     *
     * State transition equation: f(n) = f(n-1) + f(n-2)
     *
     * Observation: Each state only depends on the two previous states,
     * so we only need to store two values rather than the entire sequence.
     *
     * This reduces space complexity from O(n) to O(1).
     */
    public int fibonacci(int n) {
        // Base cases
        if (n == 0 || n == 1) {
            return n;
        }

        // Instead of a full DP table, we only keep track of the two most recent values
        int dp_i_1 = 1;  // Represents dp[i-1], initialized to f(1)
        int dp_i_2 = 0;  // Represents dp[i-2], initialized to f(0)

        // Iterate from 2 to n
        for (int i = 2; i <= n; i++) {
            // Calculate current Fibonacci number using state transition equation
            int dp_i = dp_i_1 + dp_i_2;

            // Update variables for next iteration (rolling update)
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }

        // Final result is in dp_i_1
        return dp_i_1;
    }

    /**
     * Example 2: Space-optimized coin change
     *
     * State transition equation: dp[i] = min(dp[i], dp[i-coin] + 1) for each coin
     *
     * While full space optimization isn't possible (we need values for all amounts),
     * we can still demonstrate the principle by using a single array rather than
     * multiple data structures.
     */
    public int coinChange(int[] coins, int amount) {
        // Initialize DP array
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // Use the state transition equation to fill the array
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    // Here is our state transition equation in action
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Example 3: 0-1 Knapsack with space optimization
     *
     * Problem: Given weights and values of n items, put these items in a knapsack
     * of capacity W to get the maximum value.
     *
     * State transition equation:
     * dp[i][w] = max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i])
     *
     * Space optimization: The naive solution uses a 2D array dp[n+1][W+1],
     * but we can optimize to use a 1D array since each row only depends on the previous row.
     */
    public int knapsack(int[] values, int[] weights, int capacity) {
        // Create a 1D array instead of 2D
        int[] dp = new int[capacity + 1];

        // Process each item
        for (int i = 0; i < values.length; i++) {
            // Process weights in reverse to avoid using items multiple times
            // This is crucial for the space optimization to work correctly
            for (int w = capacity; w >= weights[i]; w--) {
                // State transition equation
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }

        return dp[capacity];
    }
}
