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
}
