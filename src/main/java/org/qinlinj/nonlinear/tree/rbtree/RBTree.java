package org.qinlinj.nonlinear.tree.rbtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Red-Black Tree Implementation
 * A Red-Black tree is a self-balancing binary search tree that maintains balance using node colors
 * Red-Black trees have the following properties:
 * 1. Each node is either red or black
 * 2. The root node is always black
 * 3. Every leaf node (NIL/null) is black
 * 4. If a node is red, then both its children are black (no consecutive red nodes)
 * 5. For each node, all paths from the node to its descendant leaves contain the same number of black nodes
 */
public class RBTree<E extends Comparable<E>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
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
        root = add(root, e);
        root.color = BLACK;
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
        } else {
            return node;
        }

        if (isRED(node.right) && !isRED(node.left)) {
            node = leftRotate(node);
        }

        if (isRED(node.left) && isRED(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRED(node.left) && isRED(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /************************* retrieve *******************************/
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

    public List<E> preOrder() {
        List<E> res = new ArrayList<>();

        preOrder(root, res);

        return res;
    }

    private void preOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        res.add(node.data);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }

    public List<E> inOrder() {
        List<E> res = new ArrayList<>();

        inOrder(root, res);

        return res;
    }

    private void inOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        inOrder(node.left, res);
        res.add(node.data);
        inOrder(node.right, res);
    }

    public List<E> postOrder() {
        LinkedList res = new LinkedList<E>();

        postOrder(root, res);

        return res;
    }

    private void postOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.data);
    }

    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return minValue(root).data;
    }

    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        return minValue(node.left);
    }

    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return maxValue(root).data;
    }

    private TreeNode maxValue(TreeNode node) {
        if (node.right == null) return node;
        return maxValue(node.right);
    }

    /************************* Delete *******************************/
    public E removeMin() {
        E res = minValue();
        root = removeMin(root);
        return res;
    }

    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        TreeNode leftRoot = removeMin(node.left);
        node.left = leftRoot;

        return node;
    }

    public E removeMax() {
        E res = maxValue();
        root = removeMax(root);
        return res;
    }

    private TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            TreeNode leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        TreeNode rightRoot = removeMax(node.right);
        node.right = rightRoot;

        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;

        if (e.compareTo(node.data) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {

            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            TreeNode successor = minValue(node.right);

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = null;
            node.right = null;
            size--;
            return successor;
        }
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
