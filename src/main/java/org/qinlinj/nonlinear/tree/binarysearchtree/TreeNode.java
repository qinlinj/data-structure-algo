package org.qinlinj.nonlinear.tree.binarysearchtree;

/**
 * Represents a node in a binary tree.
 */
public class TreeNode {
    /**
     * The value stored in this node
     */
    public int val;

    /**
     * Reference to the left child node
     */
    public TreeNode left;

    /**
     * Reference to the right child node
     */
    public TreeNode right;

    /**
     * Default constructor - creates an empty node.
     */
    TreeNode() {
    }

    /**
     * Creates a node with the given value.
     *
     * @param val The integer value to store in this node
     */
    TreeNode(int val) {
        this.val = val;
    }

    /**
     * Creates a node with a value and child nodes.
     *
     * @param val   The integer value to store in this node
     * @param left  The left child node
     * @param right The right child node
     */
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}