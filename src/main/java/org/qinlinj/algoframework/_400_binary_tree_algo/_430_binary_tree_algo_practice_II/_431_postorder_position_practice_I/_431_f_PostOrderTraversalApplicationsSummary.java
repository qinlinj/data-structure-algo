package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._431_postorder_position_practice_I;

/**
 * Post-Order Traversal Applications: Summary
 * <p>
 * This file summarizes various applications of post-order traversal in binary tree problems,
 * highlighting techniques and patterns for effectively using this traversal order.
 * <p>
 * Common Pattern Overview:
 * 1. Post-order traversal is particularly powerful for bottom-up operations
 * 2. The traversal order (left, right, root) allows child results to inform parent decisions
 * 3. Many tree modification, validation, and calculation problems can leverage post-order patterns
 * 4. Post-order position is key for determining node properties that depend on subtree properties
 * <p>
 * Key Techniques:
 * <p>
 * 1. Tree Property Validation:
 * - Checking balanced property (height differences)
 * - Validating BST properties
 * - Ensuring paths have certain characteristics
 * <p>
 * 2. Tree Calculations:
 * - Computing subtree sums, averages, or counts
 * - Calculating node-specific properties like tilt
 * - Finding frequency-based information
 * <p>
 * 3. Tree Modification:
 * - Pruning nodes based on subtree properties
 * - Removing specific types of nodes
 * - Restructuring tree connections
 * <p>
 * 4. Implementation Strategies:
 * - Using global variables vs. return values
 * - Combining multiple calculations in a single traversal
 * - Using helper classes to track multiple values
 */
public class _431_f_PostOrderTraversalApplicationsSummary {
    /**
     * Example 1: Validating a balanced tree
     * Shows how post-order traversal can efficiently check tree properties
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * Helper function for balance validation
     * Returns height if balanced, -1 if unbalanced
     */
    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Get heights of left and right subtrees
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1; // Left subtree is unbalanced

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1; // Right subtree is unbalanced

        // Post-order position: check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current node is unbalanced
        }

        // Return height of current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Example 2: Calculating subtree sum and tilt
     * Shows how to combine multiple calculations in one traversal
     */
    public int findTilt(TreeNode root) {
        int[] totalTilt = {0}; // Array to simulate pass by reference
        sumAndTilt(root, totalTilt);
        return totalTilt[0];
    }

    /**
     * Helper function for tilt calculation
     * Returns subtree sum while updating tilt
     */
    private int sumAndTilt(TreeNode node, int[] totalTilt) {
        if (node == null) {
            return 0;
        }

        // Get sums of left and right subtrees
        int leftSum = sumAndTilt(node.left, totalTilt);
        int rightSum = sumAndTilt(node.right, totalTilt);

        // Post-order position: calculate tilt and update total
        totalTilt[0] += Math.abs(leftSum - rightSum);

        // Return sum of current subtree
        return node.val + leftSum + rightSum;
    }

    /**
     * Example 3: Tree pruning
     * Shows how post-order traversal can modify tree structure
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Process children first
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        // Post-order position: decide if current node should be pruned
        if (root.val == 0 && root.left == null && root.right == null) {
            return null; // Prune this node
        }

        return root;
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
