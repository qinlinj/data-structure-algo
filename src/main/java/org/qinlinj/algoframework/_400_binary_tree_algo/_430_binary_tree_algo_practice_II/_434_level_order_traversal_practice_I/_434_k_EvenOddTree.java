package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 1609: Even Odd Tree (Medium)
 * <p>
 * Problem Description:
 * A binary tree is named Even-Odd if it meets the following conditions:
 * 1. The root is at level 0 with level 0 being even.
 * 2. All nodes at even-indexed levels have odd integer values in strictly increasing order from left to right.
 * 3. All nodes at odd-indexed levels have even integer values in strictly decreasing order from left to right.
 * <p>
 * Key Concepts:
 * - Level Order Traversal with Value Validation: Uses BFS to check values at each level
 * - Level Parity Checking: Different validation rules for even and odd levels
 * - Strict Monotonicity Checking: Maintains previous value to verify increasing/decreasing order
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree
 */

import java.util.*;

class _434_k_EvenOddTree {
    // Main method for testing
    public static void main(String[] args) {
        _434_k_EvenOddTree solution = new _434_k_EvenOddTree();

        // Create example tree: [1,10,4,3,null,7,9,12,8,6,null,null,2]
        TreeNode root1 = solution.new TreeNode(1);
        root1.left = solution.new TreeNode(10);
        root1.right = solution.new TreeNode(4);
        root1.left.left = solution.new TreeNode(3);
        root1.right.left = solution.new TreeNode(7);
        root1.right.right = solution.new TreeNode(9);
        root1.left.left.left = solution.new TreeNode(12);
        root1.left.left.right = solution.new TreeNode(8);
        root1.right.left.left = solution.new TreeNode(6);
        root1.right.right.right = solution.new TreeNode(2);

        boolean result1 = solution.isEvenOddTree(root1);
        System.out.println("Tree 1 is Even-Odd: " + result1);
        // Expected output: true

        // Create example tree 2: [5,4,2,3,3,7]
        TreeNode root2 = solution.new TreeNode(5);
        root2.left = solution.new TreeNode(4);
        root2.right = solution.new TreeNode(2);
        root2.left.left = solution.new TreeNode(3);
        root2.left.right = solution.new TreeNode(3);
        root2.right.left = solution.new TreeNode(7);

        boolean result2 = solution.isEvenOddTree(root2);
        System.out.println("Tree 2 is Even-Odd: " + result2);
        // Expected output: false (level 2 has duplicate values)
    }

    public boolean isEvenOddTree(TreeNode root) {
        // Handle empty tree case
        if (root == null) {
            return true;
        }

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Track level parity: true for even levels, false for odd levels
        boolean isEvenLevel = true;

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Track previous value for monotonicity check
            // Initialize with appropriate extreme value based on level
            int prev = isEvenLevel ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            // For loop controls traversal from left to right at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                int currentValue = currentNode.val;

                if (isEvenLevel) {
                    // Even level (0, 2, 4, ...) requirements:
                    // - Values must be odd
                    // - Values must be strictly increasing
                    if (currentValue % 2 == 0 || currentValue <= prev) {
                        return false;
                    }
                } else {
                    // Odd level (1, 3, 5, ...) requirements:
                    // - Values must be even
                    // - Values must be strictly decreasing
                    if (currentValue % 2 == 1 || currentValue >= prev) {
                        return false;
                    }
                }

                // Update previous value for next iteration
                prev = currentValue;

                // Add child nodes to queue for next level processing
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Toggle level parity for next level
            isEvenLevel = !isEvenLevel;
        }

        // If we've completed the traversal without issues, the tree is Even-Odd
        return true;
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