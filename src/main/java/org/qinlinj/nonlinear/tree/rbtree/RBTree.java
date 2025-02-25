package org.qinlinj.nonlinear.tree.rbtree;

public class RBTree<E extends Comparable<E>> {
    private static boolean RED = true;
    private static boolean BLACK = false;
    private TreeNode root;
    private int size;
    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        boolean color; // 颜色

        public TreeNode(E data) {
            this.data = data;
            this.color = RED;
        }
    }

}
