package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._432_postorder_position_practice_II;

/**
 * Problem 1026: Maximum Difference Between Node and Ancestor
 * <p>
 * Description:
 * Given the root of a binary tree, find the maximum value V for which there exist different
 * nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 * A node A is an ancestor of B if either: any child of A is equal to B, or any child of A
 * is an ancestor of B.
 * <p>
 * Key Concepts:
 * - Uses post-order traversal to track min and max values in subtrees
 * - Calculates differences between ancestors and descendants efficiently
 * - Avoids the need to explicitly track all paths by passing range information
 * - Maintains a global maximum while computing local properties
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _432_c_MaximumDifferenceBetweenNodeAndAncestor {
    // Global variable to track maximum difference
    private int maxDifference = 0;

    /**
     * Main function to find the maximum difference between node and ancestor
     *
     * @param root Root of the binary tree
     * @return Maximum difference between any node and its ancestor
     */
    public int maxAncestorDiff(TreeNode root) {
        // Reset the max difference for this calculation
        maxDifference = 0;

        // Start recursive calculation
        findMinMax(root);

        return maxDifference;
    }

    /**
     * Helper function to find the minimum and maximum values in each subtree
     * while updating the global maximum difference
     *
     * @param node Current node being processed
     * @return int[] array where [0] is the min value and [1] is the max value in the subtree
     */
    private int[] findMinMax(TreeNode node) {
        // Base case: null node doesn't contribute to min/max
        if (node == null) {
            // Return values that won't affect min/max calculations
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }

        // Process left and right subtrees
        int[] leftMinMax = findMinMax(node.left);
        int[] rightMinMax = findMinMax(node.right);

        // Calculate min and max values in the subtree including current node
        int minVal = Math.min(node.val, Math.min(leftMinMax[0], rightMinMax[0]));
        int maxVal = Math.max(node.val, Math.max(leftMinMax[1], rightMinMax[1]));

        // Post-order position: update global maximum difference
        // We want the maximum absolute difference between current node and any descendant
        int maxDiffWithDescendants = Math.max(
                Math.abs(node.val - minVal),
                Math.abs(node.val - maxVal)
        );

        maxDifference = Math.max(maxDifference, maxDiffWithDescendants);

        // Return the min and max values in the subtree
        return new int[]{minVal, maxVal};
    }

    /**
     * Alternative implementation that passes min and max values down the tree
     */
    public int maxAncestorDiffAlternative(TreeNode root) {
        // Use a helper function that tracks min and max values seen along the path
        return maxDiffDFS(root, root.val, root.val);
    }

    /**
     * Helper function for alternative implementation
     */
    private int maxDiffDFS(TreeNode node, int min, int max) {
        if (node == null) {
            // Return the maximum difference encountered so far
            return max - min;
        }

        // Update min and max values
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);

        // Recursively process left and right subtrees
        int leftDiff = maxDiffDFS(node.left, min, max);
        int rightDiff = maxDiffDFS(node.right, min, max);

        // Return the maximum difference from either subtree
        return Math.max(leftDiff, rightDiff);
    }

    /**
     * Another approach using a custom result object
     */
    public int maxAncestorDiffWithCustomObject(TreeNode root) {
        NodeMinMax result = calculateMinMax(root);
        return result.maxDiff;
    }

    /**
     * Helper method for custom object approach
     */
    private NodeMinMax calculateMinMax(TreeNode node) {
        if (node == null) {
            return new NodeMinMax(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        NodeMinMax left = calculateMinMax(node.left);
        NodeMinMax right = calculateMinMax(node.right);

        int minVal = Math.min(node.val, Math.min(left.min, right.min));
        int maxVal = Math.max(node.val, Math.max(left.max, right.max));

        int currentMaxDiff = Math.max(
                Math.max(Math.abs(node.val - minVal), Math.abs(node.val - maxVal)),
                Math.max(left.maxDiff, right.maxDiff)
        );

        return new NodeMinMax(minVal, maxVal, currentMaxDiff);
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
     * Helper class for the custom object approach
     */
    private class NodeMinMax {
        int min;      // Minimum value in subtree
        int max;      // Maximum value in subtree
        int maxDiff;  // Maximum difference encountered so far

        NodeMinMax(int min, int max, int maxDiff) {
            this.min = min;
            this.max = max;
            this.maxDiff = maxDiff;
        }
    }
}