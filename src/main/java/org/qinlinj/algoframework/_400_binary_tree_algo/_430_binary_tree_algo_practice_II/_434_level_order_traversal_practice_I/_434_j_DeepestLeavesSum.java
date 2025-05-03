package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 1302: Deepest Leaves Sum (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 * <p>
 * Key Concepts:
 * - Level Order Traversal for Deepest Level: Uses BFS to identify and sum the deepest level
 * - Last Level Tracking: The solution tracks only the sum of the last level processed
 * - Optimization: No need to maintain all level sums, just update the sum at each new level
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree
 */

import java.util.*;

class _434_j_DeepestLeavesSum {
    // Main method for testing
    public static void main(String[] args) {
        _434_j_DeepestLeavesSum solution = new _434_j_DeepestLeavesSum();

        // Create example tree: [1,2,3,4,5,null,6,7,null,null,null,null,8]
        TreeNode root = solution.new TreeNode(1);
        root.left = solution.new TreeNode(2);
        root.right = solution.new TreeNode(3);
        root.left.left = solution.new TreeNode(4);
        root.left.right = solution.new TreeNode(5);
        root.right.right = solution.new TreeNode(6);
        root.left.left.left = solution.new TreeNode(7);
        root.right.right.right = solution.new TreeNode(8);

        // Test BFS approach
        int resultBFS = solution.deepestLeavesSum(root);
        System.out.println("Sum of deepest leaves (BFS): " + resultBFS);
        // Expected output: 15 (7 + 8)

        // Test DFS approach
        int resultDFS = solution.deepestLeavesSumDFS(root);
        System.out.println("Sum of deepest leaves (DFS): " + resultDFS);
        // Expected output: 15 (7 + 8)
    }

    // BFS approach
    public int deepestLeavesSum(TreeNode root) {
        // Handle empty tree case
        if (root == null) {
            return 0;
        }

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // The sum variable will be reset at each level
        // When the loop finishes, it will contain the sum of the deepest level
        int sum = 0;

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Reset sum for the current level
            sum = 0;

            // For loop controls traversal from left to right at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Add current node's value to the level sum
                sum += currentNode.val;

                // Add child nodes to queue for next level processing
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // No need to store the sum - it will be overwritten in the next iteration
            // When the loop finishes, sum will contain the value for the deepest level
        }

        // Return the sum of the deepest level
        return sum;
    }

    // DFS approach - using depth tracking
    public int deepestLeavesSumDFS(TreeNode root) {
        // Map to store sums at each depth
        Map<Integer, Integer> depthSums = new HashMap<>();
        // Variable to track maximum depth seen
        int[] maxDepth = new int[1]; // Using array for mutable reference

        // Perform DFS traversal
        dfs(root, 0, depthSums, maxDepth);

        // Return sum at maximum depth
        return depthSums.get(maxDepth[0]);
    }

    private void dfs(TreeNode node, int depth, Map<Integer, Integer> depthSums, int[] maxDepth) {
        if (node == null) {
            return;
        }

        // Update maximum depth if needed
        maxDepth[0] = Math.max(maxDepth[0], depth);

        // Add current node's value to the sum at current depth
        depthSums.put(depth, depthSums.getOrDefault(depth, 0) + node.val);

        // Recursively process left and right subtrees
        dfs(node.left, depth + 1, depthSums, maxDepth);
        dfs(node.right, depth + 1, depthSums, maxDepth);
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