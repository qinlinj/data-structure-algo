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
    // Color constants
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // Root node and tree size
    private TreeNode root;
    private int size;

    /**
     * Constructor: Initialize an empty Red-Black tree
     */
    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Get the number of nodes in the tree
     *
     * @return Number of nodes
     */
    public int getSize() {
        return size;
    }

    /**
     * Check if the tree is empty
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if a node is red
     * In Red-Black trees, null nodes are considered black
     *
     * @param node The node to check
     * @return true if the node is red, false otherwise
     */
    private boolean isRED(TreeNode node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    /************************* Insertion Operations *******************************/

    /**
     * Left rotation operation
     * node                    x
     * /  \                  /   \
     * T1   x     ------->  node  T3
     * / \              /  \
     * T2 T3            T1  T2
     *
     * @param node The center node of rotation
     * @return The new root node after rotation
     */
    private TreeNode leftRotate(TreeNode node) {
        TreeNode x = node.right;

        // Perform rotation
        node.right = x.left;
        x.left = node;

        // Adjust colors
        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * Color flip operation
     * When both children of a node are red, make the node red and children black
     *
     * @param node The node to flip colors
     */
    private void flipColors(TreeNode node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * Right rotation operation
     * node                    x
     * /  \                  /   \
     * x   T2     ------->   y   node
     * / \                        /  \
     * y  T1                      T1  T2
     *
     * @param node The center node of rotation
     * @return The new root node after rotation
     */
    private TreeNode rightRotate(TreeNode node) {
        TreeNode x = node.left;

        // Perform rotation
        node.left = x.right;
        x.right = node;

        // Adjust colors
        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * Add an element to the Red-Black tree
     *
     * @param e The element to add
     */
    public void add(E e) {
        root = add(root, e);
        // Maintain the root as black (property 2)
        root.color = BLACK;
    }


    /**
     * Recursively add an element to a specified subtree
     *
     * @param node The root of the current subtree
     * @param e    The element to add
     * @return The root of the subtree after adding the element
     */
    private TreeNode add(TreeNode node, E e) {
        // Create a new node (new nodes are red by default)
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        // Recursively add to left or right subtree
        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        } else {
            // Element already exists, no operation
            return node;
        }

        // Maintain Red-Black tree properties

        // Case 1: Right child is red, left child is black, perform left rotation
        if (isRED(node.right) && !isRED(node.left)) {
            node = leftRotate(node);
        }

        // Case 2: Left child and left-left grandchild are both red, perform right rotation
        if (isRED(node.left) && isRED(node.left.left)) {
            node = rightRotate(node);
        }

        // Case 3: Both left and right children are red, perform color flip
        if (isRED(node.left) && isRED(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /************************* Retrieval Operations *******************************/

    /**
     * Check if the Red-Black tree contains the target element
     *
     * @param target The target element
     * @return true if the element exists, false otherwise
     */
    public boolean contains(E target) {
        TreeNode node = find(target);
        return node != null;
    }

    /**
     * Find the node containing the target element
     *
     * @param target The target element
     * @return The node containing the target element, or null if not found
     */
    public TreeNode find(E target) {
        return find(root, target);
    }

    /**
     * Recursively find the target element in the specified subtree
     *
     * @param node   The root of the current subtree
     * @param target The target element
     * @return The node containing the target element, or null if not found
     */
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
