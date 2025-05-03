package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 1161: Maximum Level Sum of a Binary Tree (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return the level with the maximum sum of node values.
 * If there are multiple levels with the same maximum sum, return the smallest level number.
 * <p>
 * Key Concepts:
 * - Level Order Traversal with Sum Tracking: Uses BFS to calculate sum at each level
 * - Maximum Sum and Level Tracking: Maintains both the maximum sum seen and its corresponding level
 * - Level Numbering: Binary tree levels are 1-indexed in this problem (root is level 1)
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree
 */

import java.util.*;

class _434_i_MaxLevelSum {
    // Main method for testing
    public static void main(String[] args) {
        _434_i_MaxLevelSum solution = new _434_i_MaxLevelSum();

        // Create example tree: [1,7,0,7,-8,null,null]
        TreeNode root = solution.new TreeNode(1);
        root.left = solution.new TreeNode(7);
        root.right = solution.new TreeNode(0);
        root.left.left = solution.new TreeNode(7);
        root.left.right = solution.new TreeNode(-8);

        int result = solution.maxLevelSum(root);

        System.out.println("Level with maximum sum: " + result);
        // Expected output: 2 (level 2 has sum 7+0=7, which is greater than level 1 sum=1 and level 3 sum=7+(-8)=-1)

        // Create second example: [989,null,10250,98693,-89388,null,null,null,-32127]
        TreeNode root2 = solution.new TreeNode(989);
        root2.right = solution.new TreeNode(10250);
        root2.right.left = solution.new TreeNode(98693);
        root2.right.right = solution.new TreeNode(-89388);
        root2.right.right.right = solution.new TreeNode(-32127);

        int result2 = solution.maxLevelSum(root2);

        System.out.println("Level with maximum sum (Example 2): " + result2);
        // Expected output: 2
    }

    public int maxLevelSum(TreeNode root) {
        // Handle empty tree case
        if (root == null) {
            return 0;
        }

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Track current level (1-indexed as per problem)
        int level = 1;

        // Track level with maximum sum and the maximum sum itself
        int maxSumLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Sum of all node values at current level
            int levelSum = 0;

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

            // Check if current level has greater sum than previous max
            if (levelSum > maxSum) {
                maxSum = levelSum;
                maxSumLevel = level;
            }

            // Move to next level
            level++;
        }

        return maxSumLevel;
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