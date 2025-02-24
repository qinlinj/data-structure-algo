package org.qinlinj.nonlinear.tree.avltree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AVLTree<E extends Comparable<E>> {
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

    private int getBalanceFactor(TreeNode node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBinarySearchTree() {
        List<E> res = inOrder();
        int len = res.size();
        if (len <= 1) {
            return true;
        }

        for (int i = 1; i < len; i++) {
            if (res.get(i).compareTo(res.get(i - 1)) < 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode node) {
        if (node == null) return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //        y                                    x
    //       / \                                 /   \
    //      x   T4                             z       y
    //     / \          --------------->      / \     / \
    //    z   T3                            T1   T2 T3   T4
    //   / \
    // T1   T2
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode t3 = x.right;

        // left rotate
        x.right = y;
        y.left = t3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    //    y                             x
    //  /  \                          /   \
    // T4   x                        y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T3  z                     T4 T3 T1 T2
    //      / \
    //     T1 T2
    private TreeNode leftRotate(TreeNode y) {
        TreeNode x = y.right;
        TreeNode t3 = x.left;

        x.left = y;
        y.right = t3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
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
        LinkedList res = new LinkedList<>();

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

    // O(logn)
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

    // O(logn)
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
        root = remove(root, res);
        return res;
    }

    public E removeMax() {
        E res = maxValue();
        root = remove(root, res);
        return res;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;
        TreeNode retNode;

        if (e.compareTo(node.data) < 0) {
            node.left = remove(node.left, e);
            retNode = node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(node.right, e);
            retNode = node;
        } else {
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                TreeNode successor = minValue(node.right);

                successor.right = remove(node.right, successor.data);
                successor.left = node.left;

                node.left = null;
                node.right = null;

                retNode = successor;
                ;
            }
        }

        if (retNode == null) return null;

        retNode.height = Math.max(getHeight(retNode.left),
                getHeight(retNode.right)) + 1;
        int balanceFactor = getBalanceFactor(retNode);
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;
        int height = 1;

        public TreeNode(E data) {
            this.data = data;
        }
    }

}
