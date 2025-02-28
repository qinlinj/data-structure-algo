package org.qinlinj.nonlinear.tree.binarytree;

public class TreeNode<E> {
    public TreeNode<E> left;
    public TreeNode<E> right;
    public E data;

    TreeNode(E data) {
        this.data = data;
        left = right = null;
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