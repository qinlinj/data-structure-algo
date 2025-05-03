package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 958: Check Completeness of a Binary Tree (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, determine if it is a complete binary tree.
 * In a complete binary tree, every level except possibly the last is completely filled,
 * and all nodes in the last level are as far left as possible.
 * <p>
 * Key Concepts:
 * - Complete Binary Tree Definition: A binary tree where every level, except possibly the last,
 * is completely filled, and all nodes are as far left as possible
 * - Level Order Traversal with Null Tracking: Uses BFS with a key insight that in a complete binary tree,
 * after encountering the first null node, all subsequent nodes must also be null
 * - State Flag: Uses a boolean flag to track when the first null node is encountered
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree
 */

import java.util.*;

class _434_h_CompleteTreeCheck {
    // Main method for testing
    public static void main(String[] args) {
        _434_h_CompleteTreeCheck solution = new _434_h_CompleteTreeCheck();

        // Create example tree 1: [1,2,3,4,5,6] - a complete binary tree
        TreeNode complete = solution.new TreeNode(1);
        complete.left = solution.new TreeNode(2);
        complete.right = solution.new TreeNode(3);
        complete.left.left = solution.new TreeNode(4);
        complete.left.right = solution.new TreeNode(5);
        complete.right.left = solution.new TreeNode(6);

        // Create example tree 2: [1,2,3,4,5,null,7] - not a complete binary tree
        TreeNode notComplete = solution.new TreeNode(1);
        notComplete.left = solution.new TreeNode(2);
        notComplete.right = solution.new TreeNode(3);
        notComplete.left.left = solution.new TreeNode(4);
        notComplete.left.right = solution.new TreeNode(5);
        notComplete.right.right = solution.new TreeNode(7); // Missing left child, right child present

        // Test both trees
        System.out.println("Tree 1 is complete: " + solution.isCompleteTree(complete));
        // Expected output: true

        System.out.println("Tree 2 is complete: " + solution.isCompleteTree(notComplete));
        // Expected output: false
    }

    public boolean isCompleteTree(TreeNode root) {
        // Handle empty tree case
        if (root == null) {
            return true;
        }

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Flag to indicate if we've seen a null node
        // Once we see a null node, all subsequent nodes must also be null
        // for the tree to be complete
        boolean endReached = false;

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // For loop controls traversal at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                if (currentNode == null) {
                    // First null node encountered, set flag
                    endReached = true;
                } else {
                    // Found a non-null node after a null node
                    if (endReached) {
                        // This means the tree is not complete
                        return false;
                    }

                    // Add left and right children to queue
                    // Note: We add null children too, to check completeness
                    queue.offer(currentNode.left);
                    queue.offer(currentNode.right);
                }
            }
        }

        // If we get here, the tree is complete
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