package org.qinlinj.algoframework._100_core_framework._140_recursion_framework._142_two_thinking_modes_for_recursion._2_traversal_mode;

import java.util.*;

public class TraversalMode {
    // Global variables to track state during traversal
    private int depth = 0;    // Current depth during traversal
    private int maxDepth = 0; // Maximum depth found so far
    /**
     * Example 2: Permutation Problem using Traversal Mode
     */
    private List<List<Integer>> result = new java.util.ArrayList<>();

    /**
     * Demonstration of both algorithms using traversal mode
     */
    public static void main(String[] args) {
        TraversalMode solution = new TraversalMode();

        // Example 1: Maximum depth of a binary tree
        System.out.println("Binary Tree Maximum Depth Example:");

        // Create the example tree from LeetCode problem 104
        //        3
        //       / \
        //      9  20
        //        /  \
        //       15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int depth = solution.maxDepth(root);
        System.out.println("Maximum depth: " + depth); // Should output 3

        // Example 2: Permutations
        System.out.println("\nPermutation Example:");
        int[] nums = {1, 2, 3};
        java.util.List<java.util.List<Integer>> permutations = solution.permute(nums);

        System.out.println("All permutations of [1,2,3]:");
        for (java.util.List<Integer> perm : permutations) {
            System.out.println(perm);
        }
    }

    /**
     * Problem: Calculate the maximum depth of a binary tree
     * <p>
     * Approach using traversal mode:
     * 1. Use a void traverse function to visit each node
     * 2. Maintain a current depth variable during traversal
     * 3. Update maximum depth when visiting leaf nodes
     * 4. Adjust depth when entering/leaving nodes
     * <p>
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (recursion stack)
     */
    public int maxDepth(TreeNode root) {
        // Special case: empty tree
        if (root == null) {
            return 0;
        }

        // Start the traversal
        traverse(root);
        return maxDepth;
    }

    /**
     * Traverse the tree to find maximum depth
     * Note: This function has no return value - it's purely for traversal
     * Results are collected via side effects (updating maxDepth)
     */
    private void traverse(TreeNode root) {
        // Base case: reached null node
        if (root == null) {
            return;
        }

        // Preorder position (entering the node) - increase depth
        depth++;

        // If this is a leaf node, check if we have a new maximum depth
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, depth);
        }

        // Continue traversal
        traverse(root.left);
        traverse(root.right);

        // Postorder position (leaving the node) - decrease depth
        depth--;
    }

    public List<List<Integer>> permute(int[] nums) {
        // Start the traversal with an empty track
        backtrack(nums, new java.util.LinkedList<>(), new boolean[nums.length]);
        return result;
    }

    /**
     * Traverse the permutation decision tree
     * Note: This function has no return value - it's purely for traversal
     * Results are collected at leaf nodes (complete permutations)
     */
    private void backtrack(int[] nums, java.util.LinkedList<Integer> track, boolean[] used) {
        // Base case: reached a leaf node (complete permutation)
        if (track.size() == nums.length) {
            // Collect result at this leaf node
            result.add(new java.util.LinkedList<>(track));
            return;
        }

        // Try each possible choice at this level
        for (int i = 0; i < nums.length; i++) {
            // Skip invalid choices (elements already used)
            if (used[i]) {
                continue;
            }

            // Make a choice
            track.add(nums[i]);
            used[i] = true;

            // Continue traversal with this choice
            backtrack(nums, track, used);

            // Undo the choice (backtrack)
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Definition for a binary tree node
     */
    public static class TreeNode {
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
