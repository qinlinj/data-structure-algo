package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._431_postorder_position_practice_I;

/**
 * Problem 110: Balanced Binary Tree
 * <p>
 * Description:
 * Given a binary tree, determine if it is height-balanced.
 * A height-balanced binary tree is defined as a binary tree in which
 * the depth of the two subtrees of every node never differs by more than 1.
 * <p>
 * Key Concepts:
 * - Uses post-order traversal for efficient computation
 * - Combines height calculation with balance checking in a single pass
 * - Demonstrates how to utilize return values and side effects together
 * - Shows efficient bottom-up validation of tree properties
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _431_a_BalancedBinaryTree {
    // Flag to track if the tree is balanced
    private boolean isBalanced = true;

    /**
     * Main function to check if a binary tree is balanced
     *
     * @param root Root of the binary tree
     * @return True if the tree is balanced, false otherwise
     */
    public boolean isBalanced(TreeNode root) {
        // Reset the balance flag
        isBalanced = true;

        // Calculate max depth while checking balance
        maxDepth(root);

        return isBalanced;
    }

    /**
     * Helper function that calculates the max depth of a tree
     * while checking if it's balanced
     *
     * @param root Root of the current subtree
     * @return Maximum depth of the subtree
     */
    private int maxDepth(TreeNode root) {
        // Base case: null node has depth 0
        if (root == null) {
            return 0;
        }

        // If we already found the tree is unbalanced, no need to continue
        if (!isBalanced) {
            return 0; // Return value doesn't matter at this point
        }

        // Calculate max depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Post-order position: check balance using subtree depths
        if (Math.abs(leftDepth - rightDepth) > 1) {
            // Found an unbalanced node
            isBalanced = false;
        }

        // Return the maximum depth of this subtree
        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * Alternative implementation that doesn't use a class field
     * Returns -1 to indicate an unbalanced subtree
     */
    public boolean isBalancedAlternative(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * Helper function that returns:
     * - The height of the tree if it's balanced
     * - -1 if the tree is not balanced
     */
    private int checkHeight(TreeNode root) {
        // Base case: null node has height 0
        if (root == null) {
            return 0;
        }

        // Check left subtree height
        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) {
            // Left subtree is unbalanced
            return -1;
        }

        // Check right subtree height
        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            // Right subtree is unbalanced
            return -1;
        }

        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            // Current node is unbalanced
            return -1;
        }

        // Current node is balanced, return its height
        return 1 + Math.max(leftHeight, rightHeight);
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
}
