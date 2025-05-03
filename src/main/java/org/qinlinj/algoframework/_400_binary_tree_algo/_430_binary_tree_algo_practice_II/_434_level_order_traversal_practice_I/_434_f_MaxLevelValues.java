package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 515: Find Largest Value in Each Tree Row (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return an array of the largest value in each row of the tree.
 * <p>
 * Key Concepts:
 * - Level Order Traversal for Row Processing: Uses BFS to process each level of the tree
 * - Maximum Value Tracking: Maintains the maximum value seen at each level
 * - Multiple Approaches: Can be solved using either BFS or DFS
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree
 */

import java.util.*;

class _434_f_MaxLevelValues {
    // Main method for testing
    public static void main(String[] args) {
        _434_f_MaxLevelValues solution = new _434_f_MaxLevelValues();

        // Create example tree: [1,3,2,5,3,null,9]
        TreeNode root = solution.new TreeNode(1);
        root.left = solution.new TreeNode(3);
        root.right = solution.new TreeNode(2);
        root.left.left = solution.new TreeNode(5);
        root.left.right = solution.new TreeNode(3);
        root.right.right = solution.new TreeNode(9);

        // Test BFS approach
        List<Integer> resultBFS = solution.largestValues(root);
        System.out.println("Largest values in each row (BFS): " + resultBFS);
        // Expected output: [1, 3, 9]

        // Test DFS approach
        List<Integer> resultDFS = solution.largestValuesDFS(root);
        System.out.println("Largest values in each row (DFS): " + resultDFS);
        // Expected output: [1, 3, 9]
    }

    // BFS approach - Level Order Traversal
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();

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

            // Initialize maximum value for current level with smallest possible integer
            int levelMax = Integer.MIN_VALUE;

            // For loop controls traversal from left to right at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Update maximum value for current level
                levelMax = Math.max(levelMax, currentNode.val);

                // Add child nodes to queue for next level processing
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Add maximum value of current level to result
            result.add(levelMax);
        }

        return result;
    }

    // DFS approach
    public List<Integer> largestValuesDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode node, int depth, List<Integer> maxValues) {
        // Base case: null node
        if (node == null) {
            return;
        }

        // If we're reaching a new level, add the first value we encounter
        if (depth == maxValues.size()) {
            maxValues.add(node.val);
        } else {
            // Otherwise, update the maximum value for this level if needed
            maxValues.set(depth, Math.max(maxValues.get(depth), node.val));
        }

        // Recursively process left and right subtrees
        dfs(node.left, depth + 1, maxValues);
        dfs(node.right, depth + 1, maxValues);
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