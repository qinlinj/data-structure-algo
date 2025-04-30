package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._413_binary_tree_postorder;

/**
 * MAXIMUM BINARY TREE CONSTRUCTION
 * ================================
 * <p>
 * This class implements the solution for LeetCode 654: Maximum Binary Tree
 * <p>
 * Problem Description:
 * Given an integer array with no duplicates, construct a maximum binary tree with the following rules:
 * 1. The root is the maximum number in the array.
 * 2. The left subtree is the maximum tree constructed from the left part of the array (before the maximum number).
 * 3. The right subtree is the maximum tree constructed from the right part of the array (after the maximum number).
 * <p>
 * Key insights:
 * <p>
 * 1. This is a classic binary tree construction problem that follows the "problem decomposition" approach.
 * <p>
 * 2. For each recursive call:
 * - Find the maximum value in the current subarray - this will be the root
 * - Split the array into left and right parts at the maximum value
 * - Recursively construct the left and right subtrees
 * <p>
 * 3. The base case occurs when the subarray is empty (start > end)
 * <p>
 * 4. Time Complexity: O(n²) in the worst case (when the array is sorted)
 * - Finding the maximum value takes O(n) time
 * - In the worst case, we make n recursive calls
 * <p>
 * 5. Space Complexity: O(n) for the recursion stack
 */

public class _413_b_MaximumBinaryTree {
    /**
     * Helper method to print the tree in preorder traversal
     */
    private static void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    /**
     * Helper method to print the tree in level order (BFS)
     */
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        _413_b_MaximumBinaryTree solution = new _413_b_MaximumBinaryTree();

        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode root = solution.constructMaximumBinaryTree(nums);

        System.out.println("Maximum Binary Tree for input [3, 2, 1, 6, 0, 5]:");
        System.out.println("Preorder traversal:");
        printPreorder(root);
        System.out.println("\n\nLevel order traversal:");
        printLevelOrder(root);

        // Expected output for preorder: 6 3 2 1 5 0
        // Expected output for level order:
        // 6
        // 3 5
        // 2 0
        // 1
    }

    /**
     * Main method for constructing a maximum binary tree from an array
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    /**
     * Helper method to recursively build the maximum binary tree
     *
     * @param nums The input array
     * @param lo   The starting index of the current subarray
     * @param hi   The ending index of the current subarray
     * @return The root of the constructed maximum binary tree
     */
    private TreeNode build(int[] nums, int lo, int hi) {
        // Base case: empty subarray
        if (lo > hi) {
            return null;
        }

        // Find the maximum value and its index in the current subarray
        int maxVal = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }

        // Construct the root node with the maximum value
        TreeNode root = new TreeNode(maxVal);

        // Recursively build left and right subtrees
        // Left subtree: elements before maxIndex
        root.left = build(nums, lo, maxIndex - 1);
        // Right subtree: elements after maxIndex
        root.right = build(nums, maxIndex + 1, hi);

        return root;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
