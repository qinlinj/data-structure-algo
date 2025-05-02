package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._426_combined_thinking_practice;

import java.util.*;

/**
 * Problem 113: Path Sum II
 * <p>
 * Description:
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
 * where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of node values.
 * <p>
 * Key Concepts:
 * - Extension of the Path Sum problem (finding all paths rather than just checking existence)
 * - Can be solved using both "divide and conquer" and "traversal" approaches
 * - Demonstrates backtracking techniques within tree traversal
 * - Requires path tracking and list manipulation
 * <p>
 * Time Complexity: O(n^2), where n is the number of nodes in the tree
 * (O(n) for traversal and O(n) for path copying in worst case)
 * Space Complexity: O(h), where h is the height of the tree for recursion stack
 * Plus O(n) for storing all paths in the result
 */
public class _426_c_PathSumII {
    /**
     * Solution 1: Traversal Approach with Backtracking
     * <p>
     * We traverse the tree while keeping track of:
     * - The current path from root to current node
     * - The sum of values along this path
     * <p>
     * When we reach a leaf where the sum equals targetSum, we add the path to our result.
     *
     * @param root      Root of the binary tree
     * @param targetSum Target sum to find in paths
     * @return List of all paths (each as a list of node values) that sum to targetSum
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // Use a LinkedList for efficient operations at both ends
        LinkedList<Integer> currentPath = new LinkedList<>();

        // Start traversal
        findPaths(root, targetSum, currentPath, result);

        return result;
    }

    /**
     * Helper method for traversal approach
     *
     * @param node         Current node being visited
     * @param remainingSum Remaining sum needed to reach targetSum
     * @param path         Current path from root to this node
     * @param result       List to collect all valid paths
     */
    private void findPaths(TreeNode node, int remainingSum, LinkedList<Integer> path,
                           List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // Add current node to path
        path.addLast(node.val);

        // Check if this is a leaf node and if remainingSum - node.val = 0
        if (node.left == null && node.right == null && remainingSum == node.val) {
            // Found a valid path - add a copy to result
            result.add(new ArrayList<>(path));
        }

        // Recursively check subtrees with updated remaining sum
        findPaths(node.left, remainingSum - node.val, path, result);
        findPaths(node.right, remainingSum - node.val, path, result);

        // Backtrack: remove current node from path
        path.removeLast();
    }

    /**
     * Solution 2: Divide and Conquer Approach
     * <p>
     * In this approach, we:
     * - Break down the problem into finding paths in the left and right subtrees
     * - Combine these paths with the current node's value
     *
     * @param root      Root of the binary tree
     * @param targetSum Target sum to find in paths
     * @return List of all paths that sum to targetSum
     */
    public List<List<Integer>> pathSumDivideConquer(TreeNode root, int targetSum) {
        List<List<Integer>> result = new LinkedList<>();

        // Base case: empty tree
        if (root == null) {
            return result;
        }

        // If this is a leaf node and its value equals targetSum, we found a path
        if (root.left == null && root.right == null && root.val == targetSum) {
            List<Integer> path = new LinkedList<>();
            path.add(root.val);
            result.add(path);
            return result;
        }

        // Find paths in subtrees with updated target
        List<List<Integer>> leftPaths = pathSumDivideConquer(root.left, targetSum - root.val);
        List<List<Integer>> rightPaths = pathSumDivideConquer(root.right, targetSum - root.val);

        // Add current node to the beginning of each path from subtrees
        for (List<Integer> path : leftPaths) {
            path.add(0, root.val);  // Efficient for LinkedList, would be O(n) for ArrayList
            result.add(path);
        }

        for (List<Integer> path : rightPaths) {
            path.add(0, root.val);  // Efficient for LinkedList, would be O(n) for ArrayList
            result.add(path);
        }

        return result;
    }

    /**
     * Alternative implementation combining both approaches
     * Uses recursive traversal but with return values similar to divide and conquer
     */
    public List<List<Integer>> pathSumHybrid(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        findPathsHybrid(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void findPathsHybrid(TreeNode node, int remainingSum, List<Integer> path,
                                 List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // Add current node to path
        path.add(node.val);

        // Check if leaf and sum matches
        if (node.left == null && node.right == null && remainingSum == node.val) {
            result.add(new ArrayList<>(path));
        } else {
            // Not a leaf, continue search in subtrees
            findPathsHybrid(node.left, remainingSum - node.val, path, result);
            findPathsHybrid(node.right, remainingSum - node.val, path, result);
        }

        // Backtrack
        path.remove(path.size() - 1);
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