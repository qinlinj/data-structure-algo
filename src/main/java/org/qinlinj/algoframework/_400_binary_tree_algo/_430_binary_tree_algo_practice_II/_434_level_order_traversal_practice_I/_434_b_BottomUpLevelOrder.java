package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 107: Binary Tree Level Order Traversal II (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (i.e., from left to right, level by level from leaf to root).
 * <p>
 * Key Concepts:
 * - Bottom-up Level Order Traversal: Similar to standard level order traversal, but results are
 * returned in reverse level order (bottom to top)
 * - Queue-based Implementation: Uses a queue for the traversal as in standard BFS
 * - Result Reversal: Uses addFirst() with a LinkedList to insert each level at the head of the result list
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) in the worst case
 */

import java.util.*;

class _434_b_BottomUpLevelOrder {
    // Main method for testing
    public static void main(String[] args) {
        _434_b_BottomUpLevelOrder solution = new _434_b_BottomUpLevelOrder();

        // Create example tree: [3,9,20,null,null,15,7]
        TreeNode root = solution.new TreeNode(3);
        root.left = solution.new TreeNode(9);
        root.right = solution.new TreeNode(20);
        root.right.left = solution.new TreeNode(15);
        root.right.right = solution.new TreeNode(7);

        List<List<Integer>> result = solution.levelOrderBottom(root);

        // Print the result
        System.out.println("Bottom-up level order traversal result:");
        for (List<Integer> level : result) {
            System.out.println(level);
        }
        // Expected output: [[15, 7], [9, 20], [3]]
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // Using LinkedList to efficiently add elements at the beginning
        LinkedList<List<Integer>> result = new LinkedList<>();

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

            // Add current level to the BEGINNING of the result list
            // This creates a bottom-up level order effect
            result.addFirst(currentLevel);
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