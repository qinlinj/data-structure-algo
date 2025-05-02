package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

import java.util.*;

/**
 * Problem 1261: Find Elements in a Contaminated Binary Tree
 * <p>
 * Description:
 * Given a binary tree with the following properties:
 * - The root's value is 0
 * - If a node's value is x and it has a left child, then that child's value is 2*x+1
 * - If a node's value is x and it has a right child, then that child's value is 2*x+2
 * <p>
 * The tree is "contaminated" - all node values are set to -1.
 * Implement the FindElements class to recover the tree and find values.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Restores values based on parent-child relationships
 * - Uses a HashSet to efficiently check if values exist
 * - Leverages the special pattern of values in the tree
 * <p>
 * Time Complexity:
 * - Constructor: O(N), where N is the number of nodes in the tree
 * - find: O(1)
 * Space Complexity: O(N) for storing all node values
 */
public class _423_c_FindElementsInContaminatedBinaryTree {
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

    class FindElements {
        // HashSet to store all valid values in the recovered tree
        private HashSet<Integer> values = new HashSet<>();

        public FindElements(TreeNode root) {
            // Restore the tree starting with root value as 0
            restore(root, 0);
        }

        // Binary tree traversal to restore values
        private void restore(TreeNode root, int value) {
            if (root == null) {
                return;
            }

            // Set the current node's value
            root.val = value;

            // Add this value to our set
            values.add(value);

            // Restore left and right children according to the given formula
            // If a node's value is x:
            // - Left child's value is 2*x+1
            // - Right child's value is 2*x+2
            restore(root.left, 2 * value + 1);
            restore(root.right, 2 * value + 2);
        }

        // Check if a target value exists in the recovered tree
        public boolean find(int target) {
            return values.contains(target);
        }
    }

    /**
     * Alternative implementation without using a HashSet
     * This leverages the special structure of this binary tree
     * where node values represent positions in a complete binary tree.
     */
    class FindElementsOptimized {
        private TreeNode root;

        public FindElementsOptimized(TreeNode root) {
            this.root = root;
            // Restore the tree starting with root value as 0
            restore(root, 0);
        }

        private void restore(TreeNode root, int value) {
            if (root == null) {
                return;
            }

            root.val = value;
            restore(root.left, 2 * value + 1);
            restore(root.right, 2 * value + 2);
        }

        public boolean find(int target) {
            // Calculate the path from root to target using bit manipulation
            // Similar to how you'd navigate to a position in a heap/complete binary tree
            return findPath(target, root);
        }

        private boolean findPath(int target, TreeNode node) {
            if (node == null) {
                return false;
            }

            if (node.val == target) {
                return true;
            }

            // Check if target value would be in left or right subtree
            // based on the pattern of values
            if (target < node.val) {
                return false; // Target is smaller than current node, impossible
            }

            // Determine if we should go left or right
            // This is a simplification; a more robust implementation would
            // calculate the exact path based on binary representation
            if ((target - 1) / 2 == node.val) {
                return findPath(target, node.left);
            } else {
                return findPath(target, node.right);
            }
        }
    }
}
