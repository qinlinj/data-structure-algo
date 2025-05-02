package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

/**
 * Problem 1145: Binary Tree Coloring Game
 * <p>
 * Description:
 * Two players play a game on a binary tree. First, player 1 colors a node red,
 * then player 2 colors a node blue. After that, they take turns coloring an uncolored
 * node adjacent to their colored nodes. The player who can't make a move loses.
 * <p>
 * Given the root, n, and the node x that player 1 will color first,
 * determine if player 2 can guarantee a win.
 * <p>
 * Key Concepts:
 * - Strategic analysis of binary tree structure
 * - Optimal play for player 2 involves blocking player 1 from a large portion of the tree
 * - Calculating subtree sizes to determine optimal strategy
 * - Locating specific nodes in a binary tree
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _423_f_BinaryTreeColoringGame {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        // Find the node that player 1 colored
        TreeNode xNode = findNode(root, x);

        // Count nodes in the left and right subtrees of x
        int leftCount = countNodes(xNode.left);
        int rightCount = countNodes(xNode.right);

        // Count nodes in the parent's subtree (excluding x's subtree)
        int parentCount = n - leftCount - rightCount - 1;

        // Player 2 can win if any of the three regions (left, right, parent)
        // contains more than half of the total nodes
        int threshold = n / 2;

        return Math.max(parentCount, Math.max(leftCount, rightCount)) > threshold;
    }

    /**
     * Find a node with a specific value in the binary tree
     *
     * @param root   The root of the binary tree
     * @param target The value to find
     * @return The node with the target value, or null if not found
     */
    private TreeNode findNode(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        if (root.val == target) {
            return root;
        }

        // Try to find in left subtree
        TreeNode leftResult = findNode(root.left, target);
        if (leftResult != null) {
            return leftResult;
        }

        // Try to find in right subtree
        return findNode(root.right, target);
    }

    /**
     * Count the total number of nodes in a binary tree
     *
     * @param root The root of the binary tree
     * @return The number of nodes in the tree
     */
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Alternative implementation combining find and count operations
     * for better efficiency
     */
    public boolean btreeGameWinningMoveOptimized(TreeNode root, int n, int x) {
        // Array to store the counts: [left subtree, right subtree, parent region]
        int[] counts = new int[3];

        // Find the node and count regions in one pass
        countRegions(root, x, counts, null);

        // The parent region count is derived from the total and other regions
        counts[2] = n - counts[0] - counts[1] - 1; // -1 for node x itself

        // Player 2 wins if any region has more than n/2 nodes
        return counts[0] > n / 2 || counts[1] > n / 2 || counts[2] > n / 2;
    }

    /**
     * Count nodes in different regions of the tree in one traversal
     *
     * @param root   Current node in traversal
     * @param x      Value of node colored by player 1
     * @param counts Array to store counts: [left of x, right of x, parent of x]
     * @param xNode  Reference to store node x when found
     * @return Total count of nodes in current subtree
     */
    private int countRegions(TreeNode root, int x, int[] counts, TreeNode[] xNode) {
        if (root == null) {
            return 0;
        }

        if (root.val == x) {
            // Found node x, count its left and right subtrees
            counts[0] = countNodes(root.left);
            counts[1] = countNodes(root.right);

            if (xNode != null) {
                xNode[0] = root;
            }

            return 1 + counts[0] + counts[1];
        }

        // Continue traversal
        int leftCount = countRegions(root.left, x, counts, xNode);
        int rightCount = countRegions(root.right, x, counts, xNode);

        return 1 + leftCount + rightCount;
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
