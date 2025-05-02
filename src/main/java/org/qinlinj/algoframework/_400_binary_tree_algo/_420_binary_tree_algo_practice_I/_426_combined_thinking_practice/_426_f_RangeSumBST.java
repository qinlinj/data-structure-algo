package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._426_combined_thinking_practice;

/**
 * Problem 938: Range Sum of BST
 * <p>
 * Description:
 * Given the root node of a binary search tree (BST) and two integers low and high,
 * return the sum of values of all nodes with a value in the inclusive range [low, high].
 * <p>
 * Key Concepts:
 * - Leveraging Binary Search Tree (BST) properties for efficient traversal
 * - Can be solved using both "divide and conquer" and "traversal" approaches
 * - Demonstrates how to prune traversal paths using BST properties
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * (could be optimized to O(h + k) where h is height and k is nodes in range)
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _426_f_RangeSumBST {
    /**
     * Solution 1: Traversal Approach
     * <p>
     * In this approach, we:
     * - Traverse the tree and accumulate values within the range
     * - Use BST properties to prune unnecessary branches
     *
     * @param root Root of the binary search tree
     * @param low  Lower bound of the range (inclusive)
     * @param high Upper bound of the range (inclusive)
     * @return Sum of values of all nodes in the range [low, high]
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        // Initialize result
        int[] sum = {0};

        // Start traversal
        traverse(root, low, high, sum);

        return sum[0];
    }

    /**
     * Helper method for traversal approach
     *
     * @param node Current node being visited
     * @param low  Lower bound of the range
     * @param high Upper bound of the range
     * @param sum  Running sum (passed as an array for mutable reference)
     */
    private void traverse(TreeNode node, int low, int high, int[] sum) {
        if (node == null) {
            return;
        }

        // If current node's value is below the range, only explore right subtree
        if (node.val < low) {
            traverse(node.right, low, high, sum);
        }
        // If current node's value is above the range, only explore left subtree
        else if (node.val > high) {
            traverse(node.left, low, high, sum);
        }
        // If current node's value is within the range
        else {
            // Add current node's value to sum
            sum[0] += node.val;

            // Explore both subtrees
            traverse(node.left, low, high, sum);
            traverse(node.right, low, high, sum);
        }
    }

    /**
     * Solution 2: Divide and Conquer Approach
     * <p>
     * In this approach, we:
     * - Define a function that returns the sum of values in the range for a subtree
     * - Recursively solve the problem for each subtree
     *
     * @param root Root of the binary search tree
     * @param low  Lower bound of the range (inclusive)
     * @param high Upper bound of the range (inclusive)
     * @return Sum of values of all nodes in the range [low, high]
     */
    public int rangeSumBSTDivideConquer(TreeNode root, int low, int high) {
        // Base case: empty tree contributes 0 to sum
        if (root == null) {
            return 0;
        }

        // If current node's value is below the range, only consider right subtree
        if (root.val < low) {
            return rangeSumBSTDivideConquer(root.right, low, high);
        }

        // If current node's value is above the range, only consider left subtree
        if (root.val > high) {
            return rangeSumBSTDivideConquer(root.left, low, high);
        }

        // If current node's value is within the range, add it to the sum
        // and recursively process both subtrees
        return root.val +
                rangeSumBSTDivideConquer(root.left, low, high) +
                rangeSumBSTDivideConquer(root.right, low, high);
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