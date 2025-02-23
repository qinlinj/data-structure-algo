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

    /************************* Insert *******************************/
    // O(logn)
    public void add(E e) {
        root = add(root, e);
    }

    private TreeNode add(TreeNode node, E e) {

        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    /************************* Retrieve *******************************/
    public boolean contains(E target) {
        TreeNode node = find(target);
        if (node == null) return false;
        return true;
    }

    public TreeNode find(E target) {
        return find(root, target);
    }

    private TreeNode find(TreeNode node, E target) {
        if (node == null) return null;

        if (target.compareTo(node.data) == 0) {
            return node;
        } else if (target.compareTo(node.data) < 0) {
            return find(node.left, target);
        } else {
            return find(node.right, target);
        }
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
