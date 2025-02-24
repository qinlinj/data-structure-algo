package org.qinlinj.nonlinear.tree.avltree;

public class AVLTree {
    private TreeNode root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return node.height;
    }

    private class TreeNode<E extends Comparable<E>> {
        E data;
        TreeNode left;
        TreeNode right;
        int height = 1;

        public TreeNode(E data) {
            this.data = data;
        }
    }

}