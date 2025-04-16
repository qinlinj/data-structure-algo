package org.qinlinj.algoframework._100_core_framework._140_recursion_framework._141_understanding_recursion_from_tree_perspective._141_1_fibonacci_sequence;

public class RecursionVisualizer {
    /**
     * Naive recursive implementation of Fibonacci
     * Time Complexity: O(2^n) - exponential due to repeated calculations
     */
    public static int fib(int n) {
        // Base case: fib(0) = 0, fib(1) = 1
        if (n < 2) {
            return n;
        }
        // Recursive case: fib(n) = fib(n-1) + fib(n-2)
        return fib(n - 1) + fib(n - 2);
    }
}
