package org.qinlinj.nonlinear.tree.binarysearchtree;

public class BinarySearchTreeRecursion<E extends Comparable<E>> {
    private TreeNode root;
    private int size;

    public BinarySearchTreeRecursion() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data) {
            this.data = data;
        }
    }
}
