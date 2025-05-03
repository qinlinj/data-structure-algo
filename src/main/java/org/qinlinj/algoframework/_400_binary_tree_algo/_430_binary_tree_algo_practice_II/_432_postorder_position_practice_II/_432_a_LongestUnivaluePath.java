package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._432_postorder_position_practice_II;

/**
 * Problem 687: Longest Univalue Path
 * <p>
 * Description:
 * Given the root of a binary tree, return the length of the longest path, where each node
 * in the path has the same value. This path may or may not pass through the root.
 * <p>
 * The length of the path between two nodes is represented by the number of edges between them.
 * <p>
 * Key Concepts:
 * - Uses post-order traversal to compute path lengths bottom-up
 * - Distinguishes between paths that pass through a node vs. paths that end at a node
 * - Maintains a global result while computing local maxima
 * - Properly handles value comparisons between parent and child nodes
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _432_a_LongestUnivaluePath {
    // Global variable to track maximum univalue path length
    private int maxLength = 0;

    /**
     * Main function to find the longest univalue path
     *
     * @param root Root of the binary tree
     * @return Length of the longest univalue path
     */
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Reset the max length for this calculation
        maxLength = 0;

        // Start the recursive calculation
        maxUnivalueLength(root, root.val);

        return maxLength;
    }

    /**
     * Helper function to calculate the maximum univalue path length
     *
     * @param node      Current node being processed
     * @param parentVal Value of the parent node
     * @return Length of the longest univalue path extending from this node matching the parent value
     */
    private int maxUnivalueLength(TreeNode node, int parentVal) {
        // Base case: null node contributes 0 to path length
        if (node == null) {
            return 0;
        }

        // Recursively compute the maximum length for left and right children
        // Each call computes the length of the path with the same value as the current node
        int leftLength = maxUnivalueLength(node.left, node.val);
        int rightLength = maxUnivalueLength(node.right, node.val);

        // Post-order position: update the global maximum
        // The longest univalue path that passes through the current node
        // is the sum of the left and right univalue paths
        maxLength = Math.max(maxLength, leftLength + rightLength);

        // If current node matches parent value, extend the path
        // Otherwise, reset the path length
        if (node.val == parentVal) {
            return 1 + Math.max(leftLength, rightLength);
        } else {
            return 0;
        }
    }

    /**
     * Alternative implementation with a different approach to handling parent-child relationships
     */
    public int longestUnivaluePathAlternative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Reset result for this calculation
        int[] result = {0};

        // Start recursive calculation
        longestPathDFS(root, result);

        return result[0];
    }

    /**
     * Helper method for alternative implementation
     * Returns the length of the longest univalue path starting from current node
     */
    private int longestPathDFS(TreeNode node, int[] result) {
        if (node == null) {
            return 0;
        }

        // Get longest paths from left and right subtrees
        int leftPath = longestPathDFS(node.left, result);
        int rightPath = longestPathDFS(node.right, result);

        // Reset paths if values don't match
        int leftContribution = 0;
        int rightContribution = 0;

        if (node.left != null && node.val == node.left.val) {
            leftContribution = leftPath;
        }

        if (node.right != null && node.val == node.right.val) {
            rightContribution = rightPath;
        }

        // Update the global result - total path through current node
        result[0] = Math.max(result[0], leftContribution + rightContribution);

        // Return the maximum extended path that can continue upward
        return 1 + Math.max(leftContribution, rightContribution);
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