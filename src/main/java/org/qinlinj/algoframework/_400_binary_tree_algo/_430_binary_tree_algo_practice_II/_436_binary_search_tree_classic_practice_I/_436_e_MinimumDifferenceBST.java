package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._436_binary_search_tree_classic_practice_I;

/**
 * Problem 530: Minimum Absolute Difference in BST (Easy)
 * <p>
 * Problem Description:
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference
 * between the values of any two different nodes in the tree.
 * <p>
 * Key Concepts:
 * - BST Inorder Property: Inorder traversal of a BST produces values in sorted order
 * - Adjacent Values: The minimum difference is always between adjacent values in sorted order
 * - State Tracking: Maintain reference to previous node during inorder traversal
 * - Optimization: No need to store all values, just compare with previous value
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */

class _436_e_MinimumDifferenceBST {
    // Reference to previous node in inorder traversal
    private TreeNode prev = null;
    // Minimum difference found so far
    private int minDiff = Integer.MAX_VALUE;

    // Main method for testing
    public static void main(String[] args) {
        _436_e_MinimumDifferenceBST solution = new _436_e_MinimumDifferenceBST();

        // Example 1: [4,2,6,1,3]
        TreeNode root1 = solution.new TreeNode(4);
        root1.left = solution.new TreeNode(2);
        root1.right = solution.new TreeNode(6);
        root1.left.left = solution.new TreeNode(1);
        root1.left.right = solution.new TreeNode(3);

        int result1 = solution.getMinimumDifference(root1);
        System.out.println("Example 1 - Minimum absolute difference: " + result1);
        // Expected output: 1

        // Also test with alternative implementation
        int result1Alt = solution.getMinimumDifferenceAlternative(root1);
        System.out.println("Alternative implementation result: " + result1Alt);

        // Example 2: [1,0,48,null,null,12,49]
        TreeNode root2 = solution.new TreeNode(1);
        root2.left = solution.new TreeNode(0);
        root2.right = solution.new TreeNode(48);
        root2.right.left = solution.new TreeNode(12);
        root2.right.right = solution.new TreeNode(49);

        // Reset state variables before next test
        solution.prev = null;
        solution.minDiff = Integer.MAX_VALUE;

        int result2 = solution.getMinimumDifference(root2);
        System.out.println("\nExample 2 - Minimum absolute difference: " + result2);
        // Expected output: 1

        // Create a more complex BST to test
        TreeNode root3 = solution.new TreeNode(27);
        root3.left = solution.new TreeNode(14);
        root3.right = solution.new TreeNode(35);
        root3.left.left = solution.new TreeNode(10);
        root3.left.right = solution.new TreeNode(19);
        root3.right.left = solution.new TreeNode(31);
        root3.right.right = solution.new TreeNode(42);

        // Reset state variables before next test
        solution.prev = null;
        solution.minDiff = Integer.MAX_VALUE;

        int result3 = solution.getMinimumDifference(root3);
        System.out.println("\nExample 3 - Minimum absolute difference: " + result3);
    }

    /**
     * Main function to find the minimum absolute difference between any two nodes in BST
     *
     * @param root The root of the binary search tree
     * @return The minimum absolute difference
     */
    public int getMinimumDifference(TreeNode root) {
        // Reset state variables
        prev = null;
        minDiff = Integer.MAX_VALUE;

        // Perform inorder traversal to find minimum difference
        inorderTraversal(root);

        return minDiff;
    }

    /**
     * Inorder traversal to find minimum difference
     */
    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        // Visit left subtree
        inorderTraversal(root.left);

        // Process current node (inorder position)
        if (prev != null) {
            // Calculate difference with previous node
            int currentDiff = root.val - prev.val;

            // Update minimum difference if necessary
            minDiff = Math.min(minDiff, currentDiff);
        }

        // Update prev pointer to current node
        prev = root;

        // Visit right subtree
        inorderTraversal(root.right);
    }

    /**
     * Alternative implementation using an ArrayList to store values
     */
    public int getMinimumDifferenceAlternative(TreeNode root) {
        // List to store node values in sorted order
        java.util.List<Integer> values = new java.util.ArrayList<>();

        // Collect all values through inorder traversal
        collectValues(root, values);

        // Find minimum difference between adjacent values
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < values.size(); i++) {
            minDiff = Math.min(minDiff, values.get(i) - values.get(i - 1));
        }

        return minDiff;
    }

    /**
     * Helper method to collect node values in sorted order
     */
    private void collectValues(TreeNode root, java.util.List<Integer> values) {
        if (root == null) {
            return;
        }

        collectValues(root.left, values);
        values.add(root.val);
        collectValues(root.right, values);
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