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