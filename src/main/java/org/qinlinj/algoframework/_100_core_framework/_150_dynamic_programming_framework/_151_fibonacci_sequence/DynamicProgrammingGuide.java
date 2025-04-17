package org.qinlinj.algoframework._100_core_framework._150_dynamic_programming_framework._151_fibonacci_sequence;

public class DynamicProgrammingGuide {
    /**
     * Example: Fibonacci Sequence
     * Problem: Calculate the nth Fibonacci number where F(1)=1, F(2)=1, F(n)=F(n-1)+F(n-2) for n>2
     * <p>
     * This demonstrates the inefficiency of naive recursion and three approaches to optimize:
     * 1. Naive recursive solution (exponential time)
     * 2. Top-down DP with memoization (linear time)
     * 3. Bottom-up DP with tabulation (linear time)
     */
    // Approach 1: Naive recursive solution - O(2^n) time complexity
    public int fibNaive(int n) {
        if (n == 1 || n == 2) return 1;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    // Approach 2: Top-down DP with memoization - O(n) time complexity
    public int fibMemoization(int n) {
        // Create memoization array to store already computed results
        Integer[] memo = new Integer[n + 1];
        return fibMemoHelper(n, memo);
    }

    private int fibMemoHelper(int n, Integer[] memo) {
        // Base case
        if (n == 1 || n == 2) return 1;

        // If we've already calculated this value, return it
        if (memo[n] != null) return memo[n];

        // Calculate and store the result
        memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        return memo[n];
    }
}
