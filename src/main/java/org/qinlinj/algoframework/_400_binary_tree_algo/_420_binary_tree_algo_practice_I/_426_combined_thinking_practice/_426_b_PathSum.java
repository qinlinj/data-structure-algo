package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._426_combined_thinking_practice;

/**
 * Problem 112: Path Sum
 * <p>
 * Description:
 * Given the root of a binary tree and an integer targetSum, return true if the tree
 * has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 * <p>
 * Key Concepts:
 * - Can be solved using both "divide and conquer" and "traversal" approaches
 * - Path must go from root to leaf (nodes with no children)
 * - Demonstrates how the same problem can be approached with different recursive patterns
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _426_b_PathSum {
    /**
     * Solution 1: Divide and Conquer Approach
     * <p>
     * The problem is broken down recursively:
     * - Does the current subtree have a path with sum = targetSum?
     * - If current node is a leaf, check if its value equals the remaining target sum
     * - Otherwise, check if either subtree has a path with remaining sum
     *
     * @param root      Root of the binary tree
     * @param targetSum Target sum to find in a path
     * @return true if a path with targetSum exists, false otherwise
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: empty tree
        if (root == null) {
            return false;
        }

        // Check if this is a leaf node and its value equals the targetSum
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        // Check if either subtree has a path with the remaining sum
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * Solution 2: Traversal Approach
     * <p>
     * With this approach, we traverse the tree keeping track of the sum so far:
     * - At each node, accumulate its value in the running sum
     * - At leaf nodes, check if the sum equals targetSum
     * - Use backtracking to reset the sum when moving back up the tree
     */
    public boolean hasPathSumTraversal(TreeNode root, int targetSum) {
        return new TraversalHelper(targetSum).hasPathSum(root);
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

    private class TraversalHelper {
        // Target sum to find
        private final int targetSum;
        // Running sum along the current path
        private int currentSum = 0;
        // Whether a path with targetSum is found
        private boolean pathFound = false;

        public TraversalHelper(int targetSum) {
            this.targetSum = targetSum;
        }

        public boolean hasPathSum(TreeNode root) {
            if (root == null) {
                return false;
            }

            traverse(root);
            return pathFound;
        }

        private void traverse(TreeNode node) {
            if (node == null || pathFound) {
                return;
            }

            // Preorder: add node value to current sum
            currentSum += node.val;

            // Check if this is a leaf node and if path sum equals target
            if (node.left == null && node.right == null) {
                if (currentSum == targetSum) {
                    pathFound = true;
                }
            }

            // Traverse subtrees
            traverse(node.left);
            traverse(node.right);

            // Postorder: subtract node value from current sum (backtracking)
            currentSum -= node.val;
        }
    }


}