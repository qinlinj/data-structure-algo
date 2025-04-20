package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._185_tree_perspective_algorithms._185_a_problem_decomposition_example;

import org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._185_tree_perspective_algorithms.TreeAlgorithmsComparison;

public class ProblemDecompositionExample {

    // =====================================================
    // EXAMPLE 1: DYNAMIC PROGRAMMING - FOCUS ON "SUBTREES"
    // =====================================================

    // Example 1A: Count nodes in a binary tree
    public int countNodes(TreeAlgorithmsComparison.TreeNode root) {
        // Base case
        if (root == null) {
            return 0;
        }

        // Solve subproblems - get count from left and right subtrees
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        // Combine subproblem results to solve original problem
        // Post-order position: Entire subtree count = left subtree + right subtree + 1
        return leftCount + rightCount + 1;
    }

    // Example 1B: Fibonacci sequence using dynamic programming approach
    public int fibonacci(int n) {
        // Base cases
        if (n <= 1) return n;

        // Solve subproblems
        int fib1 = fibonacci(n - 1);
        int fib2 = fibonacci(n - 2);

        // Combine subproblem results
        return fib1 + fib2;
    }

    // Example 1C: Maximum path sum in a binary tree
    public int maxPathSum(TreeAlgorithmsComparison.TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        // Call helper function to do the calculation
        maxPathSumHelper(root, maxSum);
        return maxSum[0];
    }

    private int maxPathSumHelper(TreeAlgorithmsComparison.TreeNode node, int[] maxSum) {
        if (node == null) return 0;

        // Calculate maximum path sum from left and right subtrees
        // Ignore negative sums by taking max with 0
        int leftGain = Math.max(maxPathSumHelper(node.left, maxSum), 0);
        int rightGain = Math.max(maxPathSumHelper(node.right, maxSum), 0);

        // Current node can be the highest point of the path
        int priceNewPath = node.val + leftGain + rightGain;

        // Update max sum if needed
        maxSum[0] = Math.max(maxSum[0], priceNewPath);

        // Return the max gain if continue the path through this node
        return node.val + Math.max(leftGain, rightGain);
    }
}
