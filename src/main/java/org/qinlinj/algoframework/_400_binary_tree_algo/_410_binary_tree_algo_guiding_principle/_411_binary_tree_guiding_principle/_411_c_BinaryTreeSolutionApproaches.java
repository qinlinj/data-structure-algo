package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._411_binary_tree_guiding_principle;

/**
 * BINARY TREE SOLUTION APPROACHES - TRAVERSAL VS. PROBLEM DECOMPOSITION
 * ====================================================================
 * <p>
 * This class explores the two main approaches to solving binary tree problems:
 * 1. Traversal approach (using external variables)
 * 2. Problem decomposition approach (utilizing return values)
 * <p>
 * Key concepts:
 * <p>
 * 1. Traversal Approach:
 * - Uses a traverse function without return value (typically void)
 * - Updates external variables to accumulate the answer
 * - Similar to backtracking algorithm patterns
 * - Often used when we need to visit every node to build the answer
 * <p>
 * 2. Problem Decomposition Approach:
 * - Defines a recursive function with a specific purpose
 * - Uses the return values from subtree function calls
 * - Similar to dynamic programming patterns
 * - Often used when the solution can be built from solutions to subtrees
 * <p>
 * The right approach depends on the problem - certain problems naturally fit
 * one approach better than the other, though many can be solved using either.
 * <p>
 * Example: Finding the maximum depth of a binary tree can be solved using either approach.
 */

public class _411_c_BinaryTreeSolutionApproaches {
    /**
     * APPROACH 1: Using traversal with external variables to find max depth
     */
    private int depth;    // Current depth during traversal
    private int maxDepth; // Maximum depth found so far
    /**
     * APPROACH 1: Using traversal to calculate preorder traversal
     */
    private java.util.List<Integer> preorderResult;

    public static void main(String[] args) {
        _411_c_BinaryTreeSolutionApproaches solution = new _411_c_BinaryTreeSolutionApproaches();

        // Create a sample tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        // Test max depth using both approaches
        System.out.println("Max Depth (Traversal): " + solution.maxDepthTraversal(root));
        System.out.println("Max Depth (Decomposition): " + solution.maxDepthDecomposition(root));

        // Test preorder traversal using both approaches
        System.out.println("Preorder (Traversal): " + solution.preorderTraversalUsingTraverse(root));
        System.out.println("Preorder (Decomposition): " + solution.preorderTraversalDecomposition(root));
    }

    public int maxDepthTraversal(TreeNode root) {
        depth = 0;
        maxDepth = 0;
        traverse(root);
        return maxDepth;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Pre-order position - entering the node, increase depth
        depth++;

        // If we've reached a leaf node, update the max depth
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }

        traverse(root.left);
        traverse(root.right);

        // Post-order position - leaving the node, decrease depth
        depth--;
    }

    /**
     * APPROACH 2: Using problem decomposition to find max depth
     * Definition: Return the maximum depth of the tree rooted at this node
     */
    public int maxDepthDecomposition(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Get the maximum depth of left and right subtrees
        int leftDepth = maxDepthDecomposition(root.left);
        int rightDepth = maxDepthDecomposition(root.right);

        // Post-order position - combine results from subtrees
        // Current tree's depth is 1 (current node) plus the deeper subtree
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public java.util.List<Integer> preorderTraversalUsingTraverse(TreeNode root) {
        preorderResult = new java.util.LinkedList<>();
        preorderTraverse(root);
        return preorderResult;
    }

    private void preorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Pre-order position
        preorderResult.add(root.val);

        preorderTraverse(root.left);
        preorderTraverse(root.right);
    }

    /**
     * APPROACH 2: Using problem decomposition to calculate preorder traversal
     * Definition: Return the preorder traversal of the tree rooted at this node
     */
    public java.util.List<Integer> preorderTraversalDecomposition(TreeNode root) {
        java.util.List<Integer> result = new java.util.LinkedList<>();

        if (root == null) {
            return result;
        }

        // Build preorder traversal: root value + left subtree preorder + right subtree preorder
        result.add(root.val);
        result.addAll(preorderTraversalDecomposition(root.left));
        result.addAll(preorderTraversalDecomposition(root.right));

        return result;
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
