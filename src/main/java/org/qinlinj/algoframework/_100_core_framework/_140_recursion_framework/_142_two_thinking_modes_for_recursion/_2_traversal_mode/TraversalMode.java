package org.qinlinj.algoframework._100_core_framework._140_recursion_framework._142_two_thinking_modes_for_recursion._2_traversal_mode;

// @formatter:off
public class TraversalMode {
    // Global variables to track state during traversal
    private int depth = 0;    // Current depth during traversal
    private int maxDepth = 0; // Maximum depth found so far

    /**
     * Problem: Calculate the maximum depth of a binary tree
     *
     * Approach using traversal mode:
     * 1. Use a void traverse function to visit each node
     * 2. Maintain a current depth variable during traversal
     * 3. Update maximum depth when visiting leaf nodes
     * 4. Adjust depth when entering/leaving nodes
     *
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (recursion stack)
     */
    public int maxDepth(TreeNode root) {
        // Special case: empty tree
        if (root == null) {
            return 0;
        }

        // Start the traversal
        traverse(root);
        return maxDepth;
    }

    private void traverse(TreeNode root) {}

    /**
     * Definition for a binary tree node
     */
    public static class TreeNode {
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
}
