package org.qinlinj.nonlinear.tree.avltree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// @formatter:off
/**
 * AVL Tree Implementation
 *
 * An AVL tree is a self-balancing binary search tree where the heights of the
 * two child subtrees of any node differ by at most one.
 *
 * For example, a balanced AVL tree might look like:
 *        4
 *       / \
 *      2   6
 *     / \ / \
 *    1  3 5  7
 *
 * Each node stores its height, which is used to calculate the balance factor.
 */
public class AVLTree<E extends Comparable<E>> {
    private TreeNode root;  // Root node of the AVL tree
    private int size;       // Number of nodes in the tree

    /**
     * Constructs an empty AVL tree
     */
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the number of nodes in the tree
     *
     * @return the size of the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the tree is empty
     *
     * @return true if the tree contains no nodes, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the height of a node (handles null nodes)
     *
     * @param node the node to check
     * @return the height of the node, or 0 if the node is null
     */
    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return node.height;
    }

    /**
     * Calculates the balance factor of a node
     * Balance factor = height of left subtree - height of right subtree
     *
     * @param node the node to check
     * @return the balance factor of the node
     */
    private int getBalanceFactor(TreeNode node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * Checks if the tree is a valid binary search tree (elements in order)
     *
     * @return true if the tree is a valid BST, false otherwise
     */
    public boolean isBinarySearchTree() {
        List<E> res = inOrder();  // In-order traversal should give sorted elements
        int len = res.size();
        if (len <= 1) {
            return true;  // Empty or single-node trees are valid BSTs
        }

        // Check if elements are in ascending order
        for (int i = 1; i < len; i++) {
            if (res.get(i).compareTo(res.get(i - 1)) < 0) {
                return false;  // Found elements out of order
            }
        }

        return true;
    }

    /**
     * Checks if the tree is balanced according to AVL tree rules
     *
     * @return true if the tree is balanced, false otherwise
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * Recursive helper to check if a subtree is balanced
     *
     * @param node the root of the subtree to check
     * @return true if the subtree is balanced, false otherwise
     */
    private boolean isBalanced(TreeNode node) {
        if (node == null) return true;  // Empty trees are balanced

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) return false;  // Not balanced if |BF| > 1

        // Check if both subtrees are balanced
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * Right Rotation
     *
     * Before:          After:
     *      y             x
     *     / \           / \
     *    x   T4        z   y
     *   / \      ->   / \ / \
     *  z   T3        T1 T2 T3 T4
     * / \
     *T1 T2
     *
     * @param y the root of the subtree to rotate
     * @return the new root after rotation
     */
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode t3 = x.right;

        // Perform rotation
        x.right = y;
        y.left = t3;

        // Update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;  // New root
    }

    /**
     * Left Rotation
     *
     * Before:        After:
     *    y             x
     *   / \           / \
     *  T4  x         y   z
     *     / \   ->  / \ / \
     *    T3  z     T4 T3 T1 T2
     *       / \
     *      T1 T2
     *
     * @param y the root of the subtree to rotate
     * @return the new root after rotation
     */
    private TreeNode leftRotate(TreeNode y) {
        TreeNode x = y.right;
        TreeNode t3 = x.left;

        // Perform rotation
        x.left = y;
        y.right = t3;

        // Update heights
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;  // New root
    }

    /************************* Insert *******************************/
    /**
     * Adds an element to the AVL tree
     * Time Complexity: O(log n)
     *
     * Example: Adding 5 to tree [3, 2, 4]
     * Before:       After:
     *     3            3
     *    / \          / \
     *   2   4        2   4
     *                     \
     *                      5
     *
     * @param e the element to add
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * Recursive helper method to add an element to a subtree
     *
     * @param node the root of the subtree
     * @param e the element to add
     * @return the root of the modified subtree
     */
    private TreeNode add(TreeNode node, E e) {
        // Base case: if node is null, create a new node
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        // Recursively insert into the appropriate subtree
        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        }

        // Update height of this node
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // Calculate balance factor to check if tree became unbalanced
        int balanceFactor = getBalanceFactor(node);

        // Case 1: Left-Left Case
        // Example: Adding 1 to [3, 2]
        // Before:      After rotation:
        //     3             2
        //    /             / \
        //   2             1   3
        //  /
        // 1
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // Case 2: Right-Right Case
        // Example: Adding 5 to [3, 4]
        // Before:      After rotation:
        //   3               4
        //    \             / \
        //     4           3   5
        //      \
        //       5
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // Case 3: Left-Right Case
        // Example: Adding 6 to [3, 2, 7]
        // Before:        After first rotation:    After second rotation:
        //     3                  3                      6
        //    / \                / \                    / \
        //   2   7              2   6                  3   7
        //      /                    \                / \
        //     6                      7              2   -
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Case 4: Right-Left Case
        // Example: Adding 6 to [3, 2, 8]
        // Before:        After first rotation:    After second rotation:
        //     3                  3                      6
        //    / \                / \                    / \
        //   2   8              2   6                  3   8
        //      /                    \                / \
        //     6                      8              2   -
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /************************* Retrieve *******************************/
    /**
     * Checks if the tree contains a specific element
     *
     * @param target the element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(E target) {
        TreeNode node = find(target);
        if (node == null) return false;
        return true;
    }

    /**
     * Finds a node containing the target element
     *
     * @param target the element to search for
     * @return the node containing the element, or null if not found
     */
    public TreeNode find(E target) {
        return find(root, target);
    }

    /**
     * Recursive helper method to find a node containing the target element
     *
     * @param node the root of the subtree to search
     * @param target the element to search for
     * @return the node containing the element, or null if not found
     */
    private TreeNode find(TreeNode node, E target) {
        if (node == null) return null;

        if (target.compareTo(node.data) == 0) {
            return node;  // Element found
        } else if (target.compareTo(node.data) < 0) {
            return find(node.left, target);  // Search left subtree
        } else {
            return find(node.right, target);  // Search right subtree
        }
    }

    /**
     * Returns a list of elements in pre-order traversal
     * (Root, Left, Right)
     *
     * Example for tree:
     *     4
     *    / \
     *   2   6
     *
     * Pre-order: [4, 2, 6]
     *
     * @return list of elements in pre-order
     */
    public List<E> preOrder() {
        List<E> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    /**
     * Recursive helper for pre-order traversal
     */
    private void preOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        res.add(node.data);              // Visit root
        preOrder(node.left, res);        // Visit left subtree
        preOrder(node.right, res);       // Visit right subtree
    }

    /**
     * Returns a list of elements in in-order traversal
     * (Left, Root, Right)
     *
     * Example for tree:
     *     4
     *    / \
     *   2   6
     *
     * In-order: [2, 4, 6]
     *
     * @return list of elements in in-order (sorted order)
     */
    public List<E> inOrder() {
        List<E> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    /**
     * Recursive helper for in-order traversal
     */
    private void inOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        inOrder(node.left, res);         // Visit left subtree
        res.add(node.data);              // Visit root
        inOrder(node.right, res);        // Visit right subtree
    }

    /**
     * Returns a list of elements in post-order traversal
     * (Left, Right, Root)
     *
     * Example for tree:
     *     4
     *    / \
     *   2   6
     *
     * Post-order: [2, 6, 4]
     *
     * @return list of elements in post-order
     */
    public List<E> postOrder() {
        LinkedList<E> res = new LinkedList<>();
        postOrder(root, res);
        return res;
    }

    /**
     * Recursive helper for post-order traversal
     */
    private void postOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        postOrder(node.left, res);       // Visit left subtree
        postOrder(node.right, res);      // Visit right subtree
        res.add(node.data);              // Visit root
    }

    /**
     * Finds the minimum value in the tree
     * Time Complexity: O(log n)
     *
     * @return the smallest element in the tree
     * @throws RuntimeException if the tree is empty
     */
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return minValue(root).data;
    }

    /**
     * Recursive helper to find the node with minimum value
     * Always follows the leftmost path
     *
     * @param node the root of the subtree to search
     * @return the node with minimum value
     */
    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;  // Leftmost node found
        return minValue(node.left);          // Continue searching left
    }

    /**
     * Finds the maximum value in the tree
     * Time Complexity: O(log n)
     *
     * @return the largest element in the tree
     * @throws RuntimeException if the tree is empty
     */
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return maxValue(root).data;
    }

    /**
     * Recursive helper to find the node with maximum value
     * Always follows the rightmost path
     *
     * @param node the root of the subtree to search
     * @return the node with maximum value
     */
    private TreeNode maxValue(TreeNode node) {
        if (node.right == null) return node;  // Rightmost node found
        return maxValue(node.right);          // Continue searching right
    }

    /************************* Delete *******************************/
    /**
     * Removes and returns the minimum value in the tree
     *
     * @return the minimum value that was removed
     */
    public E removeMin() {
        E res = minValue();
        root = remove(root, res);
        return res;
    }

    /**
     * Removes and returns the maximum value in the tree
     *
     * @return the maximum value that was removed
     */
    public E removeMax() {
        E res = maxValue();
        root = remove(root, res);
        return res;
    }

    /**
     * Removes a specific element from the tree
     *
     * @param e the element to remove
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * Recursive helper to remove an element from a subtree
     *
     * Example: Removing 3 from tree [2, 1, 3, 4]
     * Before:         After:
     *      2               2
     *     / \             / \
     *    1   3           1   4
     *         \
     *          4
     *
     * @param node the root of the subtree
     * @param e the element to remove
     * @return the root of the modified subtree
     */
    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;
        TreeNode retNode;

        // Search for the node to remove
        if (e.compareTo(node.data) < 0) {
            // Target is in left subtree
            node.left = remove(node.left, e);
            retNode = node;
        } else if (e.compareTo(node.data) > 0) {
            // Target is in right subtree
            node.right = remove(node.right, e);
            retNode = node;
        } else {
            // Found node to remove

            // Case 1: Node has no left child
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            // Case 2: Node has no right child
            else if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }
            // Case 3: Node has both children
            else {
                // Find in-order successor (smallest element in right subtree)
                TreeNode successor = minValue(node.right);

                // Remove successor from right subtree and update successor's right link
                successor.right = remove(node.right, successor.data);
                successor.left = node.left;  // Set successor's left to node's left

                // Clean up node to be removed
                node.left = null;
                node.right = null;

                retNode = successor;  // Return successor as new root of this subtree
            }
        }

        // If the tree became empty, return null
        if (retNode == null) return null;

        // Update height of current node
        retNode.height = Math.max(getHeight(retNode.left),
                getHeight(retNode.right)) + 1;

        // Check balance and rebalance if necessary, similar to add method
        int balanceFactor = getBalanceFactor(retNode);

        // Case 1: Left-Left Case
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // Case 2: Right-Right Case
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // Case 3: Left-Right Case
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // Case 4: Right-Left Case
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    /**
     * TreeNode class - internal representation of nodes in the AVL tree
     */
    private class TreeNode {
        E data;           // Element stored in the node
        TreeNode left;    // Left child
        TreeNode right;   // Right child
        int height = 1;   // Height of the node (leaf nodes have height 1)

        /**
         * Constructs a new TreeNode with the given data
         *
         * @param data the element to store in the node
         */
        public TreeNode(E data) {
            this.data = data;
        }
    }
}