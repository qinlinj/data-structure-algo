package org.qinlinj.nonlinear.tree.avltree;

/**
 * Test class for AVL Tree implementation.
 * This class demonstrates the basic operations and verification methods of an AVL tree.
 */
public class AVLTreeTest {
    /**
     * Main method to run the AVL tree tests.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize a new AVL tree with Integer elements
        AVLTree<Integer> avl = new AVLTree<>();

        // Add elements to the tree.
        // The numbers 9, 10, 11 would cause an imbalance in a regular BST,
        // but the AVL tree should automatically rebalance
        avl.add(9);
        avl.add(10);
        avl.add(11);

        // Add another element after the initial balancing
        avl.add(13);

        // Verify that the tree still maintains BST properties
        // (each node's left subtree contains only values less than the node,
        // and each node's right subtree contains only values greater than the node)
        System.out.println("is binary search tree：" + avl.isBinarySearchTree());

        // Verify that the tree is balanced
        // (height difference between left and right subtrees of any node is at most 1)
        System.out.println("is balanced：" + avl.isBalanced());
    }
}
