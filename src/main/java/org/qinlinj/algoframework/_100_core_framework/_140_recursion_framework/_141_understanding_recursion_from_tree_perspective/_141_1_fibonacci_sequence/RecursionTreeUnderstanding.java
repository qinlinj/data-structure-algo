package org.qinlinj.algoframework._100_core_framework._140_recursion_framework._141_understanding_recursion_from_tree_perspective._141_1_fibonacci_sequence;


/**
 * UNDERSTANDING RECURSION THROUGH TREE STRUCTURES
 * <p>
 * Key Concepts:
 * 1. The most effective way to understand recursion is by visualizing it as a tree structure
 * 2. Each recursive call can be seen as a node in a tree, with its own subtrees
 * 3. The base case represents leaf nodes in the tree
 * 4. Two classic examples demonstrate this perspective:
 * - Fibonacci sequence (binary tree)
 * - Permutation problem (multi-branch tree)
 * <p>
 * Tree Perspective Benefits:
 * - Clarifies the execution flow of recursive algorithms
 * - Helps visualize the call stack behavior
 * - Makes it easier to understand time and space complexity
 * - Provides a mental model for debugging recursive functions
 * <p>
 * Recursion Process:
 * 1. Each function call creates a new node in the tree
 * 2. Computation proceeds by traversing the entire tree
 * 3. Results from child nodes are combined to compute parent node values
 * 4. The final result is obtained when computation returns to the root node
 * <p>
 * 1. UNDERSTANDING RECURSION FROM A TREE PERSPECTIVE:
 * While many approaches exist for understanding recursion:
 * - Mirror analogy: Two mirrors facing each other creating infinite reflections
 * - Stack perspective: Viewing recursion as function calls pushed and popped from a stack
 * <p>
 * The most effective approach is understanding recursion as a tree structure:
 * - Each function call is a node in the tree
 * - Child nodes represent subproblems
 * - Base cases are leaf nodes
 * - The execution follows tree traversal patterns
 * <p>
 * 2. FIBONACCI EXAMPLE DEMONSTRATES THIS CLEARLY:
 * - The Fibonacci function naturally forms a binary tree
 * - Each fib(n) node splits into fib(n-1) and fib(n-2) children
 * - Base cases (n=0, n=1) form the leaves
 * - Computation follows post-order traversal (process children before parent)
 * - Node values combine according to fib(n) = fib(n-1) + fib(n-2)
 * - The tree visualization reveals the inefficiency of naive recursion (duplicate calculations)
 * <p>
 * 3. RECURSIVE THINKING MODELS:
 * - "Traversal" mindset: Moving through a structure (like tree traversal)
 * - "Problem decomposition" mindset: Breaking into smaller subproblems
 * - Both models are illuminated by the tree visualization approach
 */
public class RecursionTreeUnderstanding {
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

    /**
     * Example 1: Fibonacci Sequence
     * <p>
     * The recursive call tree for Fibonacci forms a binary tree where:
     * - Each node computes fib(n) for a specific n
     * - Left child computes fib(n-1)
     * - Right child computes fib(n-2)
     * - Base cases (n=0, n=1) are leaf nodes
     * - Parent nodes sum the results of their children
     * <p>
     * Time Complexity: O(2^n) - each call branches into two recursive calls
     * Space Complexity: O(n) - maximum depth of recursion stack
     */
    public int fibonacci(int n) {
        // Base case (leaf nodes in the recursion tree)
        if (n < 2) {
            return n;
        }

        // Recursive case (internal nodes with two children)
        // This creates a binary tree structure in the recursion
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
