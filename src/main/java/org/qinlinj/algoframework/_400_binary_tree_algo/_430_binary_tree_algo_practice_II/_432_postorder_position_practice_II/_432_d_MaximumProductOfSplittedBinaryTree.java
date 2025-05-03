package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._432_postorder_position_practice_II;

import java.util.*;

/**
 * Problem 1339: Maximum Product of Splitted Binary Tree
 * <p>
 * Description:
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge
 * such that the product of the sums of the subtrees is maximized.
 * Return the maximum product.
 * <p>
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * <p>
 * Key Concepts:
 * - Uses post-order traversal for both calculating total sum and finding optimal split
 * - Leverages the property that removing an edge creates exactly two subtrees
 * - Efficiently computes the subtree sums in a single traversal
 * - Maintains a global maximum product while evaluating all possible edge removals
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _432_d_MaximumProductOfSplittedBinaryTree {
    // Modulo constant
    private static final int MOD = 1_000_000_007;
    // Global variables
    private long maxProduct = 0;
    private int totalSum = 0;

    /**
     * Main function to find the maximum product after splitting the tree
     *
     * @param root Root of the binary tree
     * @return Maximum product modulo 10^9 + 7
     */
    public int maxProduct(TreeNode root) {
        // Reset for this calculation
        maxProduct = 0;

        // First pass: calculate total tree sum
        totalSum = getSum(root);

        // Second pass: try all possible splits and find maximum product
        calculateSubtreeSumAndUpdateMaxProduct(root);

        // Return result with modulo
        return (int) (maxProduct % MOD);
    }

    /**
     * Helper function to calculate subtree sum and update maximum product
     *
     * @param node Current node being processed
     * @return Sum of the subtree rooted at node
     */
    private int calculateSubtreeSumAndUpdateMaxProduct(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Calculate sums of left and right subtrees
        int leftSum = calculateSubtreeSumAndUpdateMaxProduct(node.left);
        int rightSum = calculateSubtreeSumAndUpdateMaxProduct(node.right);

        // Calculate current subtree sum
        int subtreeSum = node.val + leftSum + rightSum;

        // Post-order position: calculate product if we remove the edge above this node
        // First subtree is the current subtree, second subtree is the rest of the tree
        long product = (long) subtreeSum * (totalSum - subtreeSum);

        // Update maximum product
        maxProduct = Math.max(maxProduct, product);

        // Return subtree sum for parent's calculation
        return subtreeSum;
    }

    /**
     * Helper function to calculate the total sum of the tree
     *
     * @param node Current node being processed
     * @return Sum of the tree rooted at node
     */
    private int getSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.val + getSum(node.left) + getSum(node.right);
    }

    /**
     * Alternative implementation that combines both passes into one
     */
    public int maxProductOnePass(TreeNode root) {
        // Store all subtree sums
        List<Integer> allSums = new ArrayList<>();

        // Calculate total sum and collect all subtree sums
        int totalSum = dfsCollectSums(root, allSums);

        // Find maximum product
        long maxProduct = 0;
        for (int sum : allSums) {
            long product = (long) sum * (totalSum - sum);
            maxProduct = Math.max(maxProduct, product);
        }

        return (int) (maxProduct % MOD);
    }

    /**
     * Helper function for alternative implementation
     */
    private int dfsCollectSums(TreeNode node, List<Integer> sums) {
        if (node == null) {
            return 0;
        }

        int leftSum = dfsCollectSums(node.left, sums);
        int rightSum = dfsCollectSums(node.right, sums);

        int subtreeSum = node.val + leftSum + rightSum;
        sums.add(subtreeSum);

        return subtreeSum;
    }

    /**
     * Optimized implementation that uses a single traversal
     */
    public int maxProductOptimized(TreeNode root) {
        // Calculate total sum first
        int totalSum = getTotalSum(root);

        // Track maximum product
        long[] maxProduct = {0};

        // Find optimal split
        findOptimalSplit(root, totalSum, maxProduct);

        return (int) (maxProduct[0] % MOD);
    }

    /**
     * Helper function to get total sum of the tree
     */
    private int getTotalSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + getTotalSum(root.left) + getTotalSum(root.right);
    }

    /**
     * Helper function to find the optimal split
     */
    private int findOptimalSplit(TreeNode node, int totalSum, long[] maxProduct) {
        if (node == null) {
            return 0;
        }

        int subtreeSum = node.val +
                findOptimalSplit(node.left, totalSum, maxProduct) +
                findOptimalSplit(node.right, totalSum, maxProduct);

        // Calculate product if we remove the edge above this node
        maxProduct[0] = Math.max(maxProduct[0], (long) subtreeSum * (totalSum - subtreeSum));

        return subtreeSum;
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