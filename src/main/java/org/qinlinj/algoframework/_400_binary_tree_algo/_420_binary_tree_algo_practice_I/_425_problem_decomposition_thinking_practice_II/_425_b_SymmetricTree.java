package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._425_problem_decomposition_thinking_practice_II;

import java.util.*;

/**
 * Problem 101: Symmetric Tree
 * <p>
 * Description:
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach for checking tree symmetry
 * - Recursively compares mirrored subtrees against each other
 * - A tree is symmetric if its left subtree is a mirror of its right subtree
 * - Comparing mirrored nodes requires special traversal order
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _425_b_SymmetricTree {
    /**
     * Checks if a binary tree is symmetric around its center
     *
     * @param root Root of the binary tree
     * @return True if the tree is symmetric, false otherwise
     */
    public boolean isSymmetric(TreeNode root) {
        // Handle edge case: empty tree is considered symmetric
        if (root == null) {
            return true;
        }

        // Check if the left and right subtrees are mirrors of each other
        return isMirror(root.left, root.right);
    }

    /**
     * Helper function to check if two subtrees are mirrors of each other
     *
     * @param left  Root of the left subtree
     * @param right Root of the right subtree
     * @return True if the subtrees are mirrors, false otherwise
     */
    private boolean isMirror(TreeNode left, TreeNode right) {
        // Case 1: Both nodes are null - they are mirrors (empty subtrees match)
        if (left == null && right == null) {
            return true;
        }

        // Case 2: One node is null but the other isn't - not mirrors
        if (left == null || right == null) {
            return false;
        }

        // Case 3: Nodes' values must match for mirroring
        if (left.val != right.val) {
            return false;
        }

        // Case 4: For subtrees to be mirrors:
        // - left's left subtree must mirror right's right subtree
        // - left's right subtree must mirror right's left subtree
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    /**
     * Iterative implementation using a queue
     */
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return true;
        }

        // Queue to store pairs of nodes to compare
        Queue<TreeNode> queue = new LinkedList<>();

        // Add the left and right subtrees to the queue
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            // Remove two nodes to compare
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            // If both null, they are mirrors - continue to next pair
            if (t1 == null && t2 == null) {
                continue;
            }

            // If one is null or values differ, not mirrors
            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }

            // Add the outer pair of nodes (mirrored)
            queue.add(t1.left);
            queue.add(t2.right);

            // Add the inner pair of nodes (mirrored)
            queue.add(t1.right);
            queue.add(t2.left);
        }

        // If we complete the traversal without finding differences, the tree is symmetric
        return true;
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