package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._418_binary_search_tree_construction;

/**
 * Binary Search Tree Theory - Introduction
 * <p>
 * This class introduces the fundamental concepts of Binary Search Trees (BST).
 * <p>
 * Key points:
 * 1. A Binary Search Tree is a special type of binary tree where:
 * - All nodes in the left subtree have values less than the root node
 * - All nodes in the right subtree have values greater than the root node
 * - Each subtree is also a valid BST
 * <p>
 * 2. The BST property enables efficient operations like search, insert, and delete,
 * typically with O(log n) time complexity in balanced trees.
 * <p>
 * 3. This file is part of a series on BST algorithms, covering:
 * - BST construction and counting (this document)
 * - BST traversal (inorder traversal is particularly important for BSTs)
 * - Basic BST operations
 */
public class _418_a_BSTIntroduction {

    public static void main(String[] args) {
        _418_a_BSTIntroduction bst = new _418_a_BSTIntroduction();

        // Create a simple BST: 4,2,6,1,3,5,7
        TreeNode root = new TreeNode(4);
        bst.insert(root, 2);
        bst.insert(root, 6);
        bst.insert(root, 1);
        bst.insert(root, 3);
        bst.insert(root, 5);
        bst.insert(root, 7);

        System.out.println("Is the tree a valid BST? " + bst.isValidBST(root));

        // Create an invalid BST
        TreeNode invalidRoot = new TreeNode(4);
        invalidRoot.left = new TreeNode(6); // Invalid: left child > parent
        invalidRoot.right = new TreeNode(2); // Invalid: right child < parent

        System.out.println("Is the invalid tree a valid BST? " +
                bst.isValidBST(invalidRoot));
    }

    // Example: Verifying if a binary tree is a valid BST
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        // Check if current node's value satisfies the BST property
        if (lower != null && node.val <= lower) {
            return false;
        }
        if (upper != null && node.val >= upper) {
            return false;
        }

        // Recursively check left and right subtrees
        // Left subtree must be < node.val, right subtree must be > node.val
        return isValidBST(node.left, lower, node.val) &&
                isValidBST(node.right, node.val, upper);
    }

    // Example: Simple BST insertion
    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        // Recursively insert following BST property
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }

        return root;
    }

    // Basic TreeNode structure for a Binary Search Tree
    public static class TreeNode {
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
