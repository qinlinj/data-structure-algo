package org.qinlinj.nonlinear.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// @formatter:off
/**
 * Recursive Binary Search Tree Implementation
 * ==========================================
 *
 * CONCEPT AND PRINCIPLES:
 * A Binary Search Tree is a node-based binary tree data structure that has the following properties:
 * 1. The left subtree of a node contains only nodes with keys less than the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees must also be binary search trees.
 *
 * This implementation uses recursion for all operations, which produces elegant code
 * that closely mirrors the recursive nature of the tree structure itself.
 *
 * VISUALIZATION OF A BST:
 *           8
 *         /   \
 *        3     10
 *       / \      \
 *      1   6      14
 *         / \     /
 *        4   7   13
 *
 * RECURSIVE VS ITERATIVE APPROACH:
 * Advantages of recursive implementation:
 * 1. Cleaner, more elegant code that directly represents the recursive structure
 * 2. Easier to understand and maintain
 * 3. More natural implementation for many tree operations
 *
 * Disadvantages:
 * 1. Potential for stack overflow with very deep trees
 * 2. Slightly higher memory overhead due to call stack
 * 3. May be slightly slower than iterative implementations
 *
 * @param <E> Type of elements stored in the tree, must implement Comparable interface
 */
public class BinarySearchTreeRecursion<E extends Comparable<E>> {
    // Root node of the tree
    private TreeNode root;
    // Number of nodes in the tree
    private int size;

    /**
     * Constructs an empty binary search tree.
     *
     * Time Complexity: O(1)
     */
    public BinarySearchTreeRecursion() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * Time Complexity: O(1)
     *
     * @return the size of the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the tree is empty.
     *
     * Time Complexity: O(1)
     *
     * @return true if the tree contains no nodes, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /************************* Insert *******************************/
    /**
     * Adds a new element to the binary search tree.
     * If the element already exists, the tree remains unchanged.
     *
     * Time Complexity: O(h) where h is the height of the tree
     * - Best case: O(log n) for a balanced tree
     * - Worst case: O(n) for a degenerate tree
     *
     * EXAMPLE:
     * Adding 5 to this BST:
     *      8
     *     / \
     *    3   10
     *   / \
     *  1   6
     *
     * Step 1: add(8, 5)
     *   - 5 < 8, so recursively call add(8.left, 5) = add(3, 5)
     *
     * Step 2: add(3, 5)
     *   - 5 > 3, so recursively call add(3.right, 5) = add(6, 5)
     *
     * Step 3: add(6, 5)
     *   - 5 < 6, so recursively call add(6.left, 5) = add(null, 5)
     *
     * Step 4: add(null, 5)
     *   - Node is null, create new node with value 5
     *   - Return new node to be set as 6.left
     *
     * Result:
     *      8
     *     / \
     *    3   10
     *   / \
     *  1   6
     *     /
     *    5
     *
     * @param e the element to add
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * Recursive helper method for adding an element.
     * Returns the root of the resulting tree after insertion.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * @param node the current node being checked
     * @param e the element to add
     * @return the root of the subtree after insertion
     */
    private TreeNode add(TreeNode node, E e) {
        // Base case: If we reach a null node, create a new node
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        // Recursive case: Navigate to the appropriate subtree
        if (e.compareTo(node.data) < 0) {
            // Element is smaller than current node, go left
            node.left = add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            // Element is larger than current node, go right
            node.right = add(node.right, e);
        }
        // If e equals node.data, do nothing (duplicates not allowed)

        // Return the (possibly modified) subtree root
        return node;
    }

    /************************* Retrieve *******************************/
    /**
     * Checks if the tree contains the specified element.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * @param target the element to check for
     * @return true if the element exists in the tree, false otherwise
     */
    public boolean contains(E target) {
        TreeNode node = find(target);
        if (node == null) return false;
        return true;
    }

    /**
     * Searches for a node containing the specified element.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * @param target the element to search for
     * @return the node containing the element, or null if not found
     */
    public TreeNode find(E target) {
        return find(root, target);
    }

    /**
     * Recursive helper method for finding an element.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * EXAMPLE:
     * Searching for 6 in this BST:
     *      8
     *     / \
     *    3   10
     *   / \
     *  1   6
     *
     * Step 1: find(8, 6)
     *   - 6 < 8, so return find(8.left, 6) = find(3, 6)
     *
     * Step 2: find(3, 6)
     *   - 6 > 3, so return find(3.right, 6) = find(6, 6)
     *
     * Step 3: find(6, 6)
     *   - 6 == 6, so return current node (containing 6)
     *
     * @param node the current node being checked
     * @param target the element to search for
     * @return the node containing the element, or null if not found
     */
    private TreeNode find(TreeNode node, E target) {
        // Base case: If we reach a null node or find the target
        if (node == null) return null;

        if (target.compareTo(node.data) == 0) {
            return node;
        } else if (target.compareTo(node.data) < 0) {
            // Target is smaller than current node, go left
            return find(node.left, target);
        } else {
            // Target is larger than current node, go right
            return find(node.right, target);
        }
    }

    /**
     * Performs a pre-order traversal of the tree (node, left, right).
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (call stack)
     *
     * EXAMPLE:
     * Pre-order traversal of this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Result: [8, 3, 1, 6, 10, 14]
     *
     * @return a list containing elements in pre-order
     */
    public List<E> preOrder() {
        List<E> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    /**
     * Recursive helper method for pre-order traversal.
     *
     * Time Complexity: O(n)
     *
     * @param node the current node
     * @param res the list to add elements to
     */
    private void preOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        // Visit node first, then left subtree, then right subtree
        res.add(node.data);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }

    /**
     * Performs an in-order traversal of the tree (left, node, right).
     * This gives elements in ascending order for a BST.
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (call stack)
     *
     * EXAMPLE:
     * In-order traversal of this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Result: [1, 3, 6, 8, 10, 14]
     *
     * @return a list containing elements in in-order (sorted order)
     */
    public List<E> inOrder() {
        List<E> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    /**
     * Recursive helper method for in-order traversal.
     *
     * Time Complexity: O(n)
     *
     * @param node the current node
     * @param res the list to add elements to
     */
    private void inOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        // Visit left subtree first, then node, then right subtree
        inOrder(node.left, res);
        res.add(node.data);
        inOrder(node.right, res);
    }

    /**
     * Performs a post-order traversal of the tree (left, right, node).
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (call stack)
     *
     * EXAMPLE:
     * Post-order traversal of this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Result: [1, 6, 3, 14, 10, 8]
     *
     * @return a list containing elements in post-order
     */
    public List<E> postOrder() {
        LinkedList<E> res = new LinkedList<>();
        postOrder(root, res);
        return res;
    }

    /**
     * Recursive helper method for post-order traversal.
     *
     * Time Complexity: O(n)
     *
     * @param node the current node
     * @param res the list to add elements to
     */
    private void postOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        // Visit left subtree first, then right subtree, then node
        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.data);
    }

    /**
     * Finds the minimum value in the tree.
     * In a BST, the minimum value is the leftmost node.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * EXAMPLE:
     * The minimum value in this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Is 1, the leftmost node.
     *
     * @return the minimum value in the tree
     * @throws RuntimeException if the tree is empty
     */
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return minValue(root).data;
    }

    /**
     * Recursive helper method for finding the minimum value.
     *
     * Time Complexity: O(h)
     *
     * @param node the current node
     * @return the node containing the minimum value
     */
    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        return minValue(node.left);
    }

    /**
     * Finds the maximum value in the tree.
     * In a BST, the maximum value is the rightmost node.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * EXAMPLE:
     * The maximum value in this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Is 14, the rightmost node.
     *
     * @return the maximum value in the tree
     * @throws RuntimeException if the tree is empty
     */
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return maxValue(root).data;
    }

    /**
     * Recursive helper method for finding the maximum value.
     *
     * Time Complexity: O(h)
     *
     * @param node the current node
     * @return the node containing the maximum value
     */
    private TreeNode maxValue(TreeNode node) {
        if (node.right == null) return node;
        return maxValue(node.right);
    }

    /************************* Delete *******************************/
    /**
     * Removes and returns the minimum value from the tree.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * EXAMPLE:
     * Removing the minimum value from this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Step 1: Find and save the minimum value (1)
     * Step 2: Update root to removeMin(root)
     * Step 3: Return the removed value (1)
     *
     * Result:
     *      8
     *     / \
     *    3   10
     *     \    \
     *      6    14
     *
     * @return the minimum value that was removed
     * @throws RuntimeException if the tree is empty
     */
    public E removeMin() {
        E res = minValue();
        root = removeMin(root);
        return res;
    }

    /**
     * Recursive helper method for removing the minimum value.
     *
     * Time Complexity: O(h)
     *
     * @param node the current node
     * @return the root of the subtree after removing the minimum
     */
    private TreeNode removeMin(TreeNode node) {
        // Base case: We found the minimum node (no left child)
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;  // Help GC
            size--;
            return rightNode;   // Return the right subtree to replace this node
        }

        // Recursive case: Keep going left
        TreeNode leftRoot = removeMin(node.left);
        node.left = leftRoot;

        return node;
    }

    /**
     * Removes and returns the maximum value from the tree.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * EXAMPLE:
     * Removing the maximum value from this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Step 1: Find and save the maximum value (14)
     * Step 2: Update root to removeMax(root)
     * Step 3: Return the removed value (14)
     *
     * Result:
     *      8
     *     / \
     *    3   10
     *   / \
     *  1   6
     *
     * @return the maximum value that was removed
     * @throws RuntimeException if the tree is empty
     */
    public E removeMax() {
        E res = maxValue();
        root = removeMax(root);
        return res;
    }

    /**
     * Recursive helper method for removing the maximum value.
     *
     * Time Complexity: O(h)
     *
     * @param node the current node
     * @return the root of the subtree after removing the maximum
     */
    private TreeNode removeMax(TreeNode node) {
        // Base case: We found the maximum node (no right child)
        if (node.right == null) {
            TreeNode leftNode = node.left;
            node.left = null;  // Help GC
            size--;
            return leftNode;   // Return the left subtree to replace this node
        }

        // Recursive case: Keep going right
        TreeNode rightRoot = removeMax(node.right);
        node.right = rightRoot;

        return node;
    }

    /**
     * Removes the specified element from the tree.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * EXAMPLE:
     * Removing 3 from this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Step 1: remove(8, 3)
     *   - 3 < 8, so node.left = remove(8.left, 3) = remove(3, 3)
     *
     * Step 2: remove(3, 3) - found the node to remove
     *   - Node has both left and right children
     *   - Find successor (minimum of right subtree) = 6
     *   - Remove successor from right subtree
     *   - Set successor's left and right to node's children
     *
     * Result:
     *      8
     *     / \
     *    6   10
     *   /      \
     *  1        14
     *
     * @param e the element to remove
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * Recursive helper method for removing an element.
     *
     * Time Complexity: O(h)
     *
     * @param node the current node
     * @param e the element to remove
     * @return the root of the subtree after removal
     */
    private TreeNode remove(TreeNode node, E e) {
        // Base case: If we reach a null node, element not found
        if (node == null) return null;

        // Recursive case: Navigate to the node to remove
        if (e.compareTo(node.data) < 0) {
            // Element is smaller than current node, go left
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.data) > 0) {
            // Element is larger than current node, go right
            node.right = remove(node.right, e);
            return node;
        } else {
            // Found the node to remove

            // Case 1: Node has no left child
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;  // Help GC
                size--;
                return rightNode;   // Replace node with its right subtree
            }

            // Case 2: Node has no right child
            if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;   // Help GC
                size--;
                return leftNode;    // Replace node with its left subtree
            }

            // Case 3: Node has both children
            // Find the successor (minimum node in right subtree)
            TreeNode successor = minValue(node.right);

            // Remove the successor from the right subtree
            successor.right = removeMin(node.right);

            // Set successor's left to node's left
            successor.left = node.left;

            // Clean up the node to be removed
            node.left = null;
            node.right = null;

            // Replace node with successor
            return successor;
        }
    }

    /**
     * TreeNode class represents a node in the binary search tree.
     * Each node contains data and references to left and right children.
     */
    private class TreeNode {
        E data;          // The element stored in this node
        TreeNode left;   // Reference to the left child
        TreeNode right;  // Reference to the right child

        /**
         * Constructs a new node with the specified element.
         *
         * @param data the element to store in the node
         */
        public TreeNode(E data) {
            this.data = data;
        }
    }
}