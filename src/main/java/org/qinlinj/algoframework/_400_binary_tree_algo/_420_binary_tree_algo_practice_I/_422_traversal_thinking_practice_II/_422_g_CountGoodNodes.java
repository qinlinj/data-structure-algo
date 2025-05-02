package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._422_traversal_thinking_practice_II;

/**
 * Problem 1448: Count Good Nodes in Binary Tree
 * <p>
 * Description:
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X
 * there are no nodes with a value greater than X.
 * Return the number of good nodes in the binary tree.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Passes information down the tree via function parameters
 * - Tracks the maximum value seen along the path from root to current node
 * - A node is "good" if its value is greater than or equal to the maximum seen so far
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 */
public class _422_g_CountGoodNodes {
    // Counter for good nodes
    private int count = 0;

    public int goodNodes(TreeNode root) {
        // Start traversal from root
        // Initially, the maximum value in the path is the root's value
        traverse(root, root.val);
        return count;
    }

    private void traverse(TreeNode root, int pathMax) {
        if (root == null) {
            return;
        }

        // Check if current node is a "good" node
        // A node is good if its value is >= the maximum value seen so far
        if (root.val >= pathMax) {
            count++;
        }

        // Update the maximum value for the path
        pathMax = Math.max(pathMax, root.val);

        // Continue traversal with the updated maximum
        traverse(root.left, pathMax);
        traverse(root.right, pathMax);
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
