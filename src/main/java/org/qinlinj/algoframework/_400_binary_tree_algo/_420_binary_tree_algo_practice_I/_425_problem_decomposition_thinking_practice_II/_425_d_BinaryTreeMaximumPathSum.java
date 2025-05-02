package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._425_problem_decomposition_thinking_practice_II;

/**
 * Problem 124: Binary Tree Maximum Path Sum
 * <p>
 * Description:
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes
 * has an edge connecting them. A node can only appear in the sequence at most once.
 * The path sum is the sum of the node values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * The path does not need to pass through the root.
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach with a clever twist
 * - Distinguishes between two types of paths:
 * 1. Single-side paths (from a node downward in one direction)
 * 2. Complete paths (that go up and then down, forming an inverted 'V' shape)
 * - Uses a post-order traversal to build solutions from bottom-up
 * - Maintains a global variable to track the maximum path sum seen so far
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree (for recursion stack)
 */
public class _425_d_BinaryTreeMaximumPathSum {
    // Global variable to track the maximum path sum found so far
    private int maxSum = Integer.MIN_VALUE;

    /**
     * Finds the maximum path sum in a binary tree
     *
     * @param root Root of the binary tree
     * @return The maximum path sum
     */
    public int maxPathSum(TreeNode root) {
        // Reset the global maximum for each new calculation
        maxSum = Integer.MIN_VALUE;

        // Compute the maximum single-side path sum, which will also update maxSum
        maxSinglePathSum(root);

        return maxSum;
    }

    /**
     * Helper function to compute the maximum single-side path sum starting from a node
     * While computing this, it also updates the global maxSum variable with the
     * maximum complete path sum (which may form an inverted 'V' through the current node)
     *
     * @param node Current node being processed
     * @return Maximum single-side path sum starting from this node (or 0 if node is null)
     */
    private int maxSinglePathSum(TreeNode node) {
        // Base case: null node contributes 0 to the path sum
        if (node == null) {
            return 0;
        }

        // Calculate the maximum single-side path sum from the left child
        // If negative, ignore this subtree (use 0 instead)
        int leftMax = Math.max(0, maxSinglePathSum(node.left));

        // Calculate the maximum single-side path sum from the right child
        // If negative, ignore this subtree (use 0 instead)
        int rightMax = Math.max(0, maxSinglePathSum(node.right));

        // The complete path through this node forms an inverted 'V'
        // Calculate its sum: left path + current node + right path
        int completePathSum = node.val + leftMax + rightMax;

        // Update the global maximum path sum if this complete path is larger
        maxSum = Math.max(maxSum, completePathSum);

        // Return the maximum single-side path sum starting from this node
        // This will be the current node's value plus the larger of the left or right paths
        return node.val + Math.max(leftMax, rightMax);
    }

    /**
     * Alternative implementation using a helper class to avoid a global variable
     */
    public int maxPathSumWithoutGlobal(TreeNode root) {
        MaxSumHelper helper = new MaxSumHelper();
        helper.maxSinglePathSum(root);
        return helper.maxSum;
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Helper class to encapsulate the tracking of maximum path sum
     */
    private class MaxSumHelper {
        int maxSum = Integer.MIN_VALUE;

        int maxSinglePathSum(TreeNode node) {
            if (node == null) {
                return 0;
            }

            int leftMax = Math.max(0, maxSinglePathSum(node.left));
            int rightMax = Math.max(0, maxSinglePathSum(node.right));

            maxSum = Math.max(maxSum, node.val + leftMax + rightMax);

            return node.val + Math.max(leftMax, rightMax);
        }
    }
}