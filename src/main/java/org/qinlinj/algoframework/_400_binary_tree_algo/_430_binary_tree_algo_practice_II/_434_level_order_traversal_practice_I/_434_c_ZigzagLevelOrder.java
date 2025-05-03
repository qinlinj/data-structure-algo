package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 103: Binary Tree Zigzag Level Order Traversal (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 * <p>
 * Key Concepts:
 * - Zigzag Level Order Traversal: Standard level order traversal with alternating direction
 * - Direction Control: Uses a boolean flag to track whether to traverse left-to-right or right-to-left
 * - LinkedList for Efficient Insertion: Utilizes addFirst() and addLast() methods to implement the zigzag pattern
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) in the worst case
 */

import java.util.*;

class _434_c_ZigzagLevelOrder {
    // Main method for testing
    public static void main(String[] args) {
        _434_c_ZigzagLevelOrder solution = new _434_c_ZigzagLevelOrder();

        // Create example tree: [3,9,20,null,null,15,7]
        TreeNode root = solution.new TreeNode(3);
        root.left = solution.new TreeNode(9);
        root.right = solution.new TreeNode(20);
        root.right.left = solution.new TreeNode(15);
        root.right.right = solution.new TreeNode(7);

        List<List<Integer>> result = solution.zigzagLevelOrder(root);

        // Print the result
        System.out.println("Zigzag level order traversal result:");
        for (List<Integer> level : result) {
            System.out.println(level);
        }
        // Expected output: [[3], [20, 9], [15, 7]]
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        // Handle empty tree case
        if (root == null) {
            return result;
        }

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Flag to control traversal direction
        // true: left to right, false: right to left
        boolean leftToRight = true;

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Using LinkedList for efficient insertion at both ends
            LinkedList<Integer> currentLevel = new LinkedList<>();

            // For loop controls traversal at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Implement zigzag pattern by adding to beginning or end based on direction
                if (leftToRight) {
                    currentLevel.addLast(currentNode.val);
                } else {
                    currentLevel.addFirst(currentNode.val);
                }

                // Add child nodes to queue for next level processing
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Add current level to result
            result.add(currentLevel);

            // Toggle direction for next level
            leftToRight = !leftToRight;
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