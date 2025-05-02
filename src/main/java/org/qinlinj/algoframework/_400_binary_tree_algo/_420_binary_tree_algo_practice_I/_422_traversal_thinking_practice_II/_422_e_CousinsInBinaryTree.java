package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._422_traversal_thinking_practice_II;

/**
 * Problem 993: Cousins in Binary Tree
 * <p>
 * Description:
 * Given the root of a binary tree and two distinct values x and y, return true if the nodes
 * corresponding to the values x and y are cousins, or false otherwise.
 * <p>
 * Two nodes are cousins if they have the same depth but different parents.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Tracks both depth and parent information during traversal
 * - Nodes are cousins if:
 * 1. They are at the same depth
 * 2. They have different parents
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 */
public class _422_e_CousinsInBinaryTree {
    // Variables to store information about nodes x and y
    private TreeNode parentX = null;
    private TreeNode parentY = null;
    private int depthX = 0;
    private int depthY = 0;
    private int x, y;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;

        // Start traversal from root with depth 0 and no parent
        traverse(root, 0, null);

        // Nodes are cousins if they have the same depth but different parents
        return depthX == depthY && parentX != parentY;
    }

    private void traverse(TreeNode root, int depth, TreeNode parent) {
        if (root == null) {
            return;
        }

        // Check if current node is x or y
        if (root.val == x) {
            // Found node x, record its depth and parent
            parentX = parent;
            depthX = depth;
        }
        if (root.val == y) {
            // Found node y, record its depth and parent
            parentY = parent;
            depthY = depth;
        }

        // Continue traversal with updated depth and parent
        traverse(root.left, depth + 1, root);
        traverse(root.right, depth + 1, root);
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
