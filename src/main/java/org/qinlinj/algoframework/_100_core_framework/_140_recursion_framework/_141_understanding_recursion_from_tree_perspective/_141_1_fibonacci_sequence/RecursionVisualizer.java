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

    /**
     * Optimized Fibonacci using memoization
     * Time Complexity: O(n) - each value calculated exactly once
     */
    public static int fibMemoized(int n, Integer[] memo) {
        // Base case
        if (n < 2) {
            return n;
        }

        // Check if already calculated
        if (memo[n] != null) {
            return memo[n];
        }

        // Calculate and store for future use
        memo[n] = fibMemoized(n - 1, memo) + fibMemoized(n - 2, memo);
        return memo[n];
    }

    /**
     * Prints the recursion tree for Fibonacci calculation
     * This is a simplified visualization
     */
    public static void printFibTree(int n, String prefix, boolean isLeft) {
        if (n < 2) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "fib(" + n + ") = " + n);
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + "fib(" + n + ")");

        // Left branch represents fib(n-1)
        printFibTree(n - 1, prefix + (isLeft ? "│   " : "    "), true);

        // Right branch represents fib(n-2)
        printFibTree(n - 2, prefix + (isLeft ? "│   " : "    "), false);
    }

    public static void main(String[] args) {
        int n = 5;

        // Calculate fibonacci
        System.out.println("Fibonacci of " + n + " is: " + fib(n));

        // Print the recursive tree structure
        System.out.println("\nRecursion Tree for fib(" + n + "):");
        printFibTree(n, "", false);

        // Demonstrate memoized version
        Integer[] memo = new Integer[n + 1];
        System.out.println("\nFibonacci of " + n + " using memoization: " + fibMemoized(n, memo));
    }
}
