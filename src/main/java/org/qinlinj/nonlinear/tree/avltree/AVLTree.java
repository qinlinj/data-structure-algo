package org.qinlinj.nonlinear.tree.avltree;

public class AVLTree {
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
