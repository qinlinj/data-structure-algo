package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._419_binary_search_tree_postorder;

/**
 * Binary Search Tree - Maximum Sum BST in Binary Tree
 * <p>
 * This class implements the solution to LeetCode 1373: Maximum Sum BST in Binary Tree
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 * <p>
 * Problem:
 * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a BST.
 * <p>
 * Key insights:
 * 1. This problem demonstrates the power of postorder traversal:
 * - We need information from both subtrees to determine if the current tree is a valid BST
 * - We need to calculate properties (min, max, sum) that depend on subtree properties
 * <p>
 * 2. The inefficient approach:
 * - Using separate recursive functions for checking BST validity, finding min/max values, and calculating sums
 * - This leads to O(n²) time complexity due to repeated traversals
 * <p>
 * 3. The efficient postorder approach:
 * - Calculating all required information in a single traversal
 * - Using return values to propagate multiple pieces of information upward
 * - This reduces time complexity to O(n)
 * <p>
 * This problem perfectly illustrates when and why the postorder position is crucial
 * for efficient tree algorithms.
 */

public class _419_b_MaximumSumBST {

    public static void main(String[] args) {
        _419_b_MaximumSumBST solution = new _419_b_MaximumSumBST();

        // Test on example trees
        TreeNode tree1 = solution.createTestTree1();
        TreeNode tree2 = solution.createTestTree2();

        System.out.println("Testing inefficient approach:");
        System.out.println("Tree 1 maximum BST sum: " + solution.maxSumBST_inefficient(tree1));
        System.out.println("Tree 2 maximum BST sum: " + solution.maxSumBST_inefficient(tree2));

        System.out.println("\nTesting efficient postorder approach:");
        System.out.println("Tree 1 maximum BST sum: " + solution.maxSumBST(tree1));
        System.out.println("Tree 2 maximum BST sum: " + solution.maxSumBST(tree2));

        System.out.println("\nComparison of approaches:");
        System.out.println("1. Inefficient approach: O(n²) - Multiple traversals for each subtree");
        System.out.println("2. Efficient approach: O(n) - Single postorder traversal");
        System.out.println("\nKey takeaway: The postorder position allows us to efficiently");
        System.out.println("calculate and propagate multiple properties in a single traversal.");
    }

    /**
     * Inefficient approach (O(n²)): Using multiple recursive traversals
     */
    public int maxSumBST_inefficient(TreeNode root) {
        // Global variable to store maximum sum
        int[] maxSum = new int[1];

        // Traverse the tree
        traverse(root, maxSum);

        return maxSum[0];
    }

    private void traverse(TreeNode root, int[] maxSum) {
        if (root == null) return;

        // Check if current subtree is a valid BST
        if (isBST(root)) {
            // Calculate sum of all nodes in the BST
            int sum = sumOfNodes(root);
            // Update max sum if necessary
            maxSum[0] = Math.max(maxSum[0], sum);
        }

        // Continue traversal
        traverse(root.left, maxSum);
        traverse(root.right, maxSum);
    }

    // Check if a tree is a valid BST
    private boolean isBST(TreeNode root) {
        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTHelper(TreeNode node, int min, int max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return isBSTHelper(node.left, min, node.val) &&
                isBSTHelper(node.right, node.val, max);
    }

    // Calculate sum of all nodes in a tree
    private int sumOfNodes(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    /**
     * Efficient approach (O(n)): Using postorder traversal with comprehensive return values
     */
    public int maxSumBST(TreeNode root) {
        // Global variable to store maximum sum
        int[] maxSum = new int[1];

        // Perform the optimized traversal
        findMaxMinSum(root, maxSum);

        return maxSum[0];
    }

    /**
     * Helper method that returns an array of size 4 with the following information:
     * [0] - Whether the tree is a valid BST (1 = yes, 0 = no)
     * [1] - Minimum value in the tree
     * [2] - Maximum value in the tree
     * [3] - Sum of all values in the tree
     */
    private int[] findMaxMinSum(TreeNode root, int[] maxSum) {
        // Base case: empty tree is a valid BST
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        // Postorder traversal: first process left and right subtrees
        int[] left = findMaxMinSum(root.left, maxSum);
        int[] right = findMaxMinSum(root.right, maxSum);

        // Postorder position: process current node using subtree information
        int[] res = new int[4];

        // Check if current tree is a valid BST
        if (left[0] == 1 && right[0] == 1 &&
                root.val > left[2] && root.val < right[1]) {
            // Current tree is a valid BST
            res[0] = 1;
            // Min value is the min of left subtree's min and current value
            res[1] = Math.min(left[1], root.val);
            // Max value is the max of right subtree's max and current value
            res[2] = Math.max(right[2], root.val);
            // Sum is the sum of both subtrees plus current value
            res[3] = left[3] + right[3] + root.val;

            // Update global max sum
            maxSum[0] = Math.max(maxSum[0], res[3]);
        } else {
            // Current tree is not a valid BST
            res[0] = 0;
            // Other values don't matter since this isn't a valid BST
            // But for completeness, we set them to values that won't affect parent calculations
            res[1] = Integer.MIN_VALUE;
            res[2] = Integer.MAX_VALUE;
            res[3] = 0;
        }

        return res;
    }

    /**
     * Utility method to create a test tree
     */
    private TreeNode createTestTree1() {
        // Example tree from the LeetCode problem
        //      1
        //     / \
        //    4   3
        //   / \
        //  2   4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        return root;
    }

    private TreeNode createTestTree2() {
        // Another example tree
        //      5
        //     / \
        //    2   5
        //   / \
        //  1   3
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        return root;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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