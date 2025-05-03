package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 637: Average of Levels in Binary Tree (Easy)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return the average value of the nodes on each level.
 * <p>
 * Key Concepts:
 * - Level Order Traversal for Average Calculation: Uses BFS to process each level
 * - Sum and Count Tracking: Maintains sum of values and count of nodes at each level
 * - Precision Handling: Uses double for averages to prevent integer division issues
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree
 */

import java.util.*;

class _434_g_LevelAverage {
    // Main method for testing
    public static void main(String[] args) {
        _434_g_LevelAverage solution = new _434_g_LevelAverage();

        // Create example tree: [3,9,20,null,null,15,7]
        TreeNode root = solution.new TreeNode(3);
        root.left = solution.new TreeNode(9);
        root.right = solution.new TreeNode(20);
        root.right.left = solution.new TreeNode(15);
        root.right.right = solution.new TreeNode(7);

        List<Double> result = solution.averageOfLevels(root);

        // Print the result
        System.out.println("Average of levels:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Level " + i + ": " + result.get(i));
        }
        // Expected output: [3.0, 14.5, 11.0]
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();

        // Handle empty tree case
        if (root == null) {
            return result;
        }

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Sum of all node values at current level
            double levelSum = 0;

            // For loop controls traversal from left to right at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                levelSum += currentNode.val;

                // Add child nodes to queue for next level processing
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Calculate average and add to result
            // Using 1.0 * levelSum ensures floating-point division
            result.add(levelSum / levelSize);
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