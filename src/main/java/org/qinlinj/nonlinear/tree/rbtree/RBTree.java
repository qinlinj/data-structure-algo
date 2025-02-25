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

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isRED(TreeNode node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    /************************* Insert *******************************/
    //    node                    x
    //    /  \                  /   \
    //   T1   x     ------->  node  T3
    //       / \              /  \
    //      T2 T3            T1  T2
    private TreeNode leftRotate(TreeNode node) {
        TreeNode x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private void flipColors(TreeNode node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //    node                    x
    //    /  \                  /   \
    //   x   T2     ------->   y   node
    //  / \                        /  \
    // y  T1                      T1  T2
    private TreeNode rightRotate(TreeNode node) {
        TreeNode x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    public void add(E e) {
    }

    /************************* retrieve *******************************/
    public boolean contains(E target) {

    }

    public TreeNode find(E target) {

    }

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        boolean color;

        public TreeNode(E data) {
            this.data = data;
            this.color = RED;
        }
    }

}
