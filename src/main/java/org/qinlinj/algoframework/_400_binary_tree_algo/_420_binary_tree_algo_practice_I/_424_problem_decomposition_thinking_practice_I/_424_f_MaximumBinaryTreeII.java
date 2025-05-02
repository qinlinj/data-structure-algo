package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._424_problem_decomposition_thinking_practice_I;

import java.util.*;

/**
 * Problem 998: Maximum Binary Tree II
 * <p>
 * Description:
 * A maximum tree is a tree where every node's value is greater than any other value
 * in its subtree.
 * <p>
 * You are given the root of a maximum binary tree and an integer val. Just as in Problem 654,
 * the tree was constructed from an array a using the method:
 * - The root is the maximum value in the array
 * - The left subtree is the maximum tree constructed from the left subarray
 * - The right subtree is the maximum tree constructed from the right subarray
 * <p>
 * Now, given a maximum binary tree root and an integer val, construct the maximum binary tree
 * by inserting val into the original array at the end and rebuilding the tree.
 * <p>
 * Key Concepts:
 * - Builds on the Maximum Binary Tree construction (Problem 654)
 * - Inserts a new value at the end of the original array
 * - Uses the properties of the maximum tree to efficiently insert without rebuilding
 * - Exploits the fact that the new value is always inserted at the end of the array
 * <p>
 * Time Complexity: O(h), where h is the height of the tree (worst case O(n))
 * Space Complexity: O(h) for the recursion stack
 */
public class _424_f_MaximumBinaryTreeII {
    /**
     * Inserts a new value into a maximum binary tree
     *
     * @param root The root of the maximum binary tree
     * @param val  The value to insert
     * @return The root of the updated maximum binary tree
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        // Case 1: Empty tree or val is larger than the root
        if (root == null || root.val < val) {
            // If val is the largest, it becomes the new root with the original tree as its left child
            // This is because val is appended to the end of the array
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        // Case 2: Val is not the largest value
        // Since val is appended to the end of the array, it should be inserted in the right subtree
        root.right = insertIntoMaxTree(root.right, val);

        return root;
    }

    /**
     * Alternative implementation that reconstructs the entire tree from scratch
     * This is less efficient but demonstrates the original array-based construction
     */
    public TreeNode insertIntoMaxTreeRebuild(TreeNode root, int val) {
        // First, recover the original array from the tree
        List<Integer> originalArray = new ArrayList<>();
        recoverArray(root, originalArray);

        // Append val to the end of the array
        originalArray.add(val);

        // Rebuild the maximum tree from the updated array
        return buildMaximumTree(originalArray, 0, originalArray.size() - 1);
    }

    /**
     * Recovers the original array from a maximum binary tree using inorder traversal
     *
     * @param root  The root of the maximum binary tree
     * @param array The list to store the recovered array
     */
    private void recoverArray(TreeNode root, List<Integer> array) {
        if (root == null) {
            return;
        }

        recoverArray(root.left, array);
        array.add(root.val);
        recoverArray(root.right, array);
    }

    /**
     * Builds a maximum binary tree from an array
     *
     * @param nums  The array of values
     * @param start The start index
     * @param end   The end index
     * @return The root of the constructed maximum binary tree
     */
    private TreeNode buildMaximumTree(List<Integer> nums, int start, int end) {
        if (start > end) {
            return null;
        }

        // Find the maximum value and its index
        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums.get(i) > nums.get(maxIndex)) {
                maxIndex = i;
            }
        }

        // Create the root node with the maximum value
        TreeNode root = new TreeNode(nums.get(maxIndex));

        // Recursively build left and right subtrees
        root.left = buildMaximumTree(nums, start, maxIndex - 1);
        root.right = buildMaximumTree(nums, maxIndex + 1, end);

        return root;
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