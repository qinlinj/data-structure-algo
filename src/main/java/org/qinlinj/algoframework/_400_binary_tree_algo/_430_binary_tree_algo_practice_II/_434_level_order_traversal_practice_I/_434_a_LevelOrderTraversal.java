package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 102: Binary Tree Level Order Traversal (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 * <p>
 * Key Concepts:
 * - Level Order Traversal (BFS): Visits all nodes of a binary tree level by level from left to right
 * - Queue-based Implementation: Uses a queue data structure to track nodes at each level
 * - Level Tracking: Uses a nested loop structure - outer loop for levels, inner loop for nodes within a level
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) in the worst case when the tree is completely unbalanced
 */

import java.util.*;

class _434_a_LevelOrderTraversal {
    // Main method for testing
    public static void main(String[] args) {
        _434_a_LevelOrderTraversal solution = new _434_a_LevelOrderTraversal();

        // Create example tree: [3,9,20,null,null,15,7]
        TreeNode root = solution.new TreeNode(3);
        root.left = solution.new TreeNode(9);
        root.right = solution.new TreeNode(20);
        root.right.left = solution.new TreeNode(15);
        root.right.right = solution.new TreeNode(7);

        List<List<Integer>> result = solution.levelOrder(root);

        // Print the result
        System.out.println("Level order traversal result:");
        for (List<Integer> level : result) {
            System.out.println(level);
        }
        // Expected output: [[3], [9, 20], [15, 7]]
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        // Result list to store all levels
        List<List<Integer>> result = new LinkedList<>();

        // Handle empty tree case
        if (root == null) {
            return result;
        }

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            // Get the number of nodes at current level
            int levelSize = queue.size();

            // List to store values of nodes at current level
            List<Integer> currentLevel = new LinkedList<>();

            // For loop controls traversal from left to right at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                // Add child nodes to queue for next level processing
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Add current level's values to result
            result.add(currentLevel);
        }

        return result;
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