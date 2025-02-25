package org.qinlinj.nonlinear.tree.rbtree;

public class RBTree<E extends Comparable<E>> {
    private static boolean RED = true;
    private static boolean BLACK = false;

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
