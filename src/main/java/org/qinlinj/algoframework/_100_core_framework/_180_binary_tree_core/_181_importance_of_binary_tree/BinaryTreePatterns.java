package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._181_importance_of_binary_tree;

/**
 * BINARY TREE PROBLEM-SOLVING PATTERNS
 * ====================================
 * <p>
 * There are two main thinking patterns for solving binary tree problems:
 * <p>
 * 1. TRAVERSAL PATTERN:
 * Can the problem be solved by traversing the tree once?
 * If yes, use a 'traverse' function with external variables to track the state.
 * The code visits each node and performs operations during the traversal.
 * <p>
 * 2. DIVIDE & CONQUER PATTERN:
 * Can we define a recursive function that solves the original problem by using solutions to subproblems (subtrees)?
 * If yes, clearly define this recursive function and leverage its return values.
 * This approach breaks down the problem into smaller subproblems.
 * <p>
 * Regardless of which pattern you use, you need to consider:
 * - What does each individual node need to do?
 * - When should it do it? (pre-order, in-order, or post-order position)
 * - Don't worry about other nodes; the recursive function will execute the same operation on all nodes.
 * <p>
 * IMPORTANCE OF BINARY TREES:
 * Binary tree algorithms reveal fundamental patterns used across many algorithms:
 * - Quick sort is essentially a pre-order traversal of a binary tree
 * - Merge sort is essentially a post-order traversal of a binary tree
 * <p>
 * These patterns extend beyond trees to dynamic programming, backtracking, divide and conquer, and graph algorithms.
 * Understanding binary tree thinking patterns provides a framework for approaching many complex algorithms.
 */
public class BinaryTreePatterns {
    // Example 1: Count the total number of nodes in a binary tree
    public int countNodes(TreeNode root) {
        // External variable to maintain state during traversal
        int[] count = new int[1];
        // Perform traversal
        traverse(root, count);
        return count[0];
    }

    // =====================================================
    // PATTERN 1: TRAVERSAL
    // =====================================================

    // Traversal function that visits each node once
    private void traverse(TreeNode root, int[] count) {
        if (root == null) return;

        // Pre-order position: counting each node as we visit it
        count[0]++;

        // Continue traversal to child nodes
        traverse(root.left, count);
        traverse(root.right, count);
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
