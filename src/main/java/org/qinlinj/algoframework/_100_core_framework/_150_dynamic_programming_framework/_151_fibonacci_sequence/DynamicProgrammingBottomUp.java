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
}
