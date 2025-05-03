package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._432_postorder_position_practice_II;

/**
 * Problem 1372: Longest ZigZag Path in a Binary Tree
 * <p>
 * Description:
 * You are given the root of a binary tree.
 * A ZigZag path for a binary tree is defined as follow:
 * - Choose any node in the binary tree and a direction (right or left).
 * - If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * - Change the direction from right to left or from left to right.
 * - Repeat the second and third steps until you can't move in the tree.
 * <p>
 * Zigzag length is defined as the number of nodes visited - 1 (i.e., edges traversed).
 * Return the longest ZigZag path contained in that tree.
 * <p>
 * Key Concepts:
 * - Uses post-order traversal to compute zigzag path lengths bottom-up
 * - Tracks both left-starting and right-starting paths for each node
 * - Maintains state about the current direction in the zigzag pattern
 * - Leverages the defined direction alternation property of zigzag paths
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _432_e_LongestZigZagPath {
    // Global variable to track maximum zigzag path length
    private int maxLength = 0;

    /**
     * Main function to find the longest zigzag path
     *
     * @param root Root of the binary tree
     * @return Length of the longest zigzag path
     */
    public int longestZigZag(TreeNode root) {
        // Reset for this calculation
        maxLength = 0;

        // Start recursive calculation
        getZigZagLengths(root);

        return maxLength;
    }

    /**
     * Helper function to calculate zigzag path lengths
     *
     * @param node Current node being processed
     * @return int[] array where [0] is the length of zigzag path starting left from node,
     * and [1] is the length of zigzag path starting right from node
     */
    private int[] getZigZagLengths(TreeNode node) {
        // Base case: null node has 0 length paths
        if (node == null) {
            return new int[]{-1, -1}; // -1 so when we add 1, we get 0 for leaf nodes
        }

        // Get zigzag lengths for left and right children
        int[] leftChildPaths = getZigZagLengths(node.left);
        int[] rightChildPaths = getZigZagLengths(node.right);

        // Calculate zigzag paths from current node:
        // - Left path: go left from node, then zigzag right from left child
        // - Right path: go right from node, then zigzag left from right child
        int leftZigZag = leftChildPaths[1] + 1;  // Left then right
        int rightZigZag = rightChildPaths[0] + 1; // Right then left

        // Post-order position: update global maximum
        maxLength = Math.max(maxLength, Math.max(leftZigZag, rightZigZag));

        // Return zigzag lengths for parent's calculation
        return new int[]{leftZigZag, rightZigZag};
    }

    /**
     * Alternative implementation using explicit direction tracking
     */
    public int longestZigZagAlternative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Track the maximum length
        int[] maxLength = {0};

        // Start DFS with both directions
        dfs(root, true, 0, maxLength);  // Start going left
        dfs(root, false, 0, maxLength); // Start going right

        return maxLength[0];
    }

    /**
     * Helper function for alternative implementation
     *
     * @param node      Current node
     * @param goLeft    Whether to go left from current node
     * @param length    Current zigzag path length
     * @param maxLength Array to track maximum length (pass by reference)
     */
    private void dfs(TreeNode node, boolean goLeft, int length, int[] maxLength) {
        if (node == null) {
            return;
        }

        // Update maximum length
        maxLength[0] = Math.max(maxLength[0], length);

        if (goLeft) {
            // Go left, then switch direction to right
            dfs(node.left, false, length + 1, maxLength);
            // Reset path if we break the zigzag pattern
            dfs(node.right, true, 0, maxLength);
        } else {
            // Go right, then switch direction to left
            dfs(node.right, true, length + 1, maxLength);
            // Reset path if we break the zigzag pattern
            dfs(node.left, false, 0, maxLength);
        }
    }

    /**
     * Implementation with a more explicit handling of zigzag definition
     */
    public int longestZigZagExplicit(TreeNode root) {
        // Result array: [left-going count, right-going count, max zigzag length]
        int[] result = traverse(root);
        return result[2];
    }

    /**
     * Helper for explicit implementation
     * Returns [left zigzag length, right zigzag length, max zigzag length in subtree]
     */
    private int[] traverse(TreeNode node) {
        if (node == null) {
            return new int[]{-1, -1, -1};
        }

        int[] leftResult = traverse(node.left);
        int[] rightResult = traverse(node.right);

        // Calculate lengths of zigzag paths starting at this node
        // If we go left first, we use the right-going path from the left child
        int leftZigZag = leftResult[1] + 1;
        // If we go right first, we use the left-going path from the right child
        int rightZigZag = rightResult[0] + 1;

        // Calculate maximum zigzag length in this subtree
        int maxInSubtree = Math.max(
                Math.max(leftZigZag, rightZigZag),
                Math.max(leftResult[2], rightResult[2])
        );

        return new int[]{leftZigZag, rightZigZag, maxInSubtree};
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