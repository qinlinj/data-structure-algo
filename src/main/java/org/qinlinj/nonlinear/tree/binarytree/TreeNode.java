package org.qinlinj.nonlinear.tree.binarytree;

/**
 * Represents a node in a binary tree.
 *
 * @param <E> The type of data stored in the node.
 */
public class TreeNode<E> {
    /**
     * Reference to the left child node
     */
    public TreeNode<E> left;

    /**
     * Reference to the right child node
     */
    public TreeNode<E> right;

    /**
     * The data element stored in this node
     */
    public E data;

    /**
     * Creates a new TreeNode with the specified data.
     *
     * @param data The data to store in this node
     */
    TreeNode(E data) {
        this.data = data;
        left = right = null;  // Initialize left and right children as null
    }
}

//public class TreeNode {
//    int val;
//    SearchInABinarySearchTree.TreeNode left;
//    SearchInABinarySearchTree.TreeNode right;
//    TreeNode() {}
//    TreeNode(int val) { this.val = val; }
//    TreeNode(int val, SearchInABinarySearchTree.TreeNode left, SearchInABinarySearchTree.TreeNode right) {
//        this.val = val;
//        this.left = left;
//        this.right = right;
//    }
//}