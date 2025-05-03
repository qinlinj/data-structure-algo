package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._437_binary_search_tree_classic_practice_II; /**
 * Problem 1382: Balance a Binary Search Tree (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary search tree, return a balanced binary search tree with the same node values.
 * If there is more than one answer, return any of them.
 * A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
 * <p>
 * Key Concepts:
 * - BST Inorder Traversal: Produces a sorted sequence of node values
 * - Two-step Approach: 1) Extract sorted values, 2) Rebuild balanced BST
 * - Reusing Problem 108: Leveraging the solution from "Convert Sorted Array to BST"
 * - Avoiding Complex Rotations: Using the array-based approach instead of AVL/RB tree rotations
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) for storing the sorted array
 */

import java.util.*;

class _437_b_BalanceBST {
    // Main method for testing
    public static void main(String[] args) {
        _437_b_BalanceBST solution = new _437_b_BalanceBST();

        // Example 1: Create a right-skewed BST [1,null,2,null,3,null,4]
        TreeNode skewedBST1 = solution.createSkewedBST(new int[]{1, 2, 3, 4});

        System.out.println("Example 1 - Original skewed BST:");
        solution.printTree(skewedBST1, "", false);
        System.out.println("Is balanced: " + solution.isBalanced(skewedBST1));

        TreeNode balancedBST1 = solution.balanceBST(skewedBST1);

        System.out.println("\nAfter balancing:");
        solution.printTree(balancedBST1, "", false);
        System.out.println("Is balanced: " + solution.isBalanced(balancedBST1));
        System.out.println("Inorder traversal (should be the same as original):");
        solution.printInorder(balancedBST1);
        System.out.println();

        // Example 2: Already balanced BST [2,1,3]
        TreeNode balancedTree = solution.new TreeNode(2);
        balancedTree.left = solution.new TreeNode(1);
        balancedTree.right = solution.new TreeNode(3);

        System.out.println("\nExample 2 - Already balanced BST:");
        solution.printTree(balancedTree, "", false);
        System.out.println("Is balanced: " + solution.isBalanced(balancedTree));

        TreeNode balancedBST2 = solution.balanceBST(balancedTree);

        System.out.println("\nAfter balancing (should be similar):");
        solution.printTree(balancedBST2, "", false);
        System.out.println("Is balanced: " + solution.isBalanced(balancedBST2));
        System.out.println("Inorder traversal (should be the same as original):");
        solution.printInorder(balancedBST2);
        System.out.println();
    }

    /**
     * Main function to balance a BST
     *
     * @param root The root of the possibly unbalanced BST
     * @return Root of the balanced BST with the same values
     */
    public TreeNode balanceBST(TreeNode root) {
        // Step 1: Perform inorder traversal to get sorted values
        List<Integer> sortedValues = new ArrayList<>();
        inorderTraversal(root, sortedValues);

        // Step 2: Build a balanced BST from the sorted array
        return buildBalancedBST(sortedValues, 0, sortedValues.size() - 1);
    }

    /**
     * Helper method to perform inorder traversal and collect values
     */
    private void inorderTraversal(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, values);
        values.add(root.val);
        inorderTraversal(root.right, values);
    }

    /**
     * Helper method to recursively build a balanced BST from a sorted list
     * Similar to Problem 108: Convert Sorted Array to BST
     */
    private TreeNode buildBalancedBST(List<Integer> sortedValues, int left, int right) {
        if (left > right) {
            return null;
        }

        // Find the middle element to be the root
        int mid = left + (right - left) / 2;

        // Create the root node with the middle element
        TreeNode root = new TreeNode(sortedValues.get(mid));

        // Recursively build left subtree using elements before the middle
        root.left = buildBalancedBST(sortedValues, left, mid - 1);

        // Recursively build right subtree using elements after the middle
        root.right = buildBalancedBST(sortedValues, mid + 1, right);

        return root;
    }

    /**
     * Helper method to check if a tree is height-balanced
     */
    private boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * Helper method to check height and balance of a tree
     * Returns -1 if the tree is not balanced, otherwise returns the height
     */
    private int checkHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is not balanced
        }

        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is not balanced
        }

        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current node is not balanced
        }

        // Return height of current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Helper method to print the inorder traversal of the tree
     * Inorder traversal of a BST should produce sorted values
     */
    private void printInorder(TreeNode root) {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    /**
     * Helper method to print tree structure
     */
    private void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "null");
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);

        printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
        printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    /**
     * Helper method to create a skewed BST for testing
     */
    private TreeNode createSkewedBST(int[] values) {
        TreeNode root = null;
        for (int val : values) {
            root = insertNode(root, val);
        }
        return root;
    }

    /**
     * Helper method to insert a node into a BST (for creating test trees)
     */
    private TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else {
            root.right = insertNode(root.right, val);
        }

        return root;
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