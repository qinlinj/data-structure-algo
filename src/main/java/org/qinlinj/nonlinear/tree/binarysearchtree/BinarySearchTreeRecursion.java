package org.qinlinj.nonlinear.tree.binarysearchtree;

public class BinarySearchTreeRecursion<E extends Comparable<E>> {
    private TreeNode root;
    private int size;

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data) {
            this.data = data;
        }
    }
}
