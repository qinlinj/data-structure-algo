package org.qinlinj.nonlinear.tree.binarysearchtree;

import java.util.*;

// @formatter:off
/**
 * Binary Search Tree (BST) Implementation
 * ======================================
 *
 * CONCEPT AND PRINCIPLES:
 * A Binary Search Tree is a node-based binary tree data structure that has the following properties:
 * 1. The left subtree of a node contains only nodes with keys less than the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees must also be binary search trees.
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
 * ADVANTAGES:
 * 1. Fast search, insertion, and deletion (O(log n) on average)
 * 2. Naturally maintains elements in sorted order
 * 3. Simple implementation compared to more complex self-balancing trees
 * 4. Efficient range queries
 * 5. Easy traversal in sorted order (using in-order traversal)
 * 6. Good for datasets with frequent insertions and deletions
 *
 * DISADVANTAGES:
 * 1. May degenerate to linked list (O(n) operations) in worst case
 * 2. Unbalanced trees result in decreased performance
 * 3. No O(1) operations like arrays or hash tables
 *
 * APPLICATIONS:
 * - Database indexing
 * - Implementing symbol tables in compilers
 * - Priority queues
 * - Spell checking
 * - Implementing associative arrays (maps)
 *
 * @param <E> Type of elements stored in the tree, must implement Comparable interface
 */
public class BinarySearchTree<E extends Comparable<E>> {
    // Root node of the tree
    TreeNode root;
    // Number of nodes in the tree
    int size;

    /**
     * Constructs an empty binary search tree.
     *
     * Time Complexity: O(1)
     */
    public BinarySearchTree() {
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

    /************************* Create *******************************/
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
     * Step 1: Start at root (8)
     * Step 2: 5 < 8, go to left child (3)
     * Step 3: 5 > 3, go to right child (6)
     * Step 4: 5 < 6, go to left child (null)
     * Step 5: Insert 5 as left child of 6
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
        if (root == null) {
            root = new TreeNode(e);
            size++;
            return;
        }
        TreeNode curr = root;
        while (curr != null) {
            if (curr.data.compareTo(e) == 0) {
                // Element already exists, do nothing
                return;
            } else if (curr.left == null && curr.data.compareTo(e) > 0) {
                // Element should be added as left child
                curr.left = new TreeNode(e);
                size++;
                return;
            } else if (curr.right == null && curr.data.compareTo(e) < 0) {
                // Element should be added as right child
                curr.right = new TreeNode(e);
                size++;
                return;
            }

            // Navigate to the next node
            if (curr.data.compareTo(e) > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    /************************* Retrieve *******************************/
    /**
     * Checks if the tree contains the specified element.
     *
     * Time Complexity: O(h) where h is the height of the tree
     * - Best case: O(log n) for a balanced tree
     * - Worst case: O(n) for a degenerate tree
     *
     * @param target the element to check for
     * @return true if the element exists in the tree, false otherwise
     */
    public boolean contains(E target) {
        if (root == null) {
            return false;
        }
        return find(target) != null;
    }

    /**
     * Searches for a node containing the specified element.
     *
     * Time Complexity: O(h) where h is the height of the tree
     * - Best case: O(log n) for a balanced tree
     * - Worst case: O(n) for a degenerate tree
     *
     * EXAMPLE:
     * Searching for 6 in this BST:
     *      8
     *     / \
     *    3   10
     *   / \
     *  1   6
     *
     * Step 1: Start at root (8)
     * Step 2: 6 < 8, go to left child (3)
     * Step 3: 6 > 3, go to right child (6)
     * Step 4: 6 == 6, found target
     *
     * @param target the element to search for
     * @return the node containing the element, or null if not found
     */
    public TreeNode find(E target) {
        if (root == null) {
            return null;
        }
        TreeNode curr = root;
        while (curr != null) {
            if (curr.data.compareTo(target) == 0) {
                return curr;
            } else if (curr.data.compareTo(target) > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return null;
    }

    // Tree traversal methods

    /**
     * Performs a pre-order traversal of the tree (node, left, right).
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
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
        List<E> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(curr.data);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return list;
    }

    /**
     * Performs an in-order traversal of the tree (left, node, right).
     * This gives elements in ascending order for a BST.
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
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
        List<E> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode node = stack.pop();
            list.add(node.data);
            curr = node.right;
        }
        return list;
    }

    /**
     * Performs a post-order traversal of the tree (left, right, node).
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
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
        LinkedList<E> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.addFirst(curr.data);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return list;
    }

    /**
     * Performs a level-order traversal of the tree (breadth-first).
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(w) where w is the maximum width of the tree
     *
     * EXAMPLE:
     * Level-order traversal of this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Result: [[8], [3, 10], [1, 6, 14]]
     *
     * @return a list of lists containing elements level by level
     */
    public List<List<E>> levelOrder() {
        List<List<E>> list = new ArrayList<>();

        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<E> stageList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                stageList.add(node.data);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(stageList);
        }
        return list;
    }

    /**
     * Finds the minimum value in the tree.
     * In a BST, the minimum value is the leftmost node.
     *
     * Time Complexity: O(h) where h is the height of the tree
     * - Best case: O(log n) for a balanced tree
     * - Worst case: O(n) for a degenerate tree
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
        TreeNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.data;
    }

    /**
     * Finds the maximum value in the tree.
     * In a BST, the maximum value is the rightmost node.
     *
     * Time Complexity: O(h) where h is the height of the tree
     * - Best case: O(log n) for a balanced tree
     * - Worst case: O(n) for a degenerate tree
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
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.data;
    }

    /************************* Delete *******************************/
    /**
     * Removes and returns the minimum value from the tree.
     *
     * Time Complexity: O(h) where h is the height of the tree
     * - Best case: O(log n) for a balanced tree
     * - Worst case: O(n) for a degenerate tree
     *
     * EXAMPLE:
     * Removing the minimum value from this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Step 1: Find the minimum value (1)
     * Step 2: Link parent (3) to the right child of the minimum (null)
     * Step 3: Return the minimum value (1)
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
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr.left != null) {
            prev = curr;
            curr = curr.left;
        }
        if (prev == null) {
            // The root is the minimum
            root = root.right;
        } else {
            // Link the parent of the minimum to the right child of the minimum
            prev.left = curr.right;
        }
        curr.right = null;
        size--;
        return curr.data;
    }

    /**
     * Removes and returns the maximum value from the tree.
     *
     * Time Complexity: O(h) where h is the height of the tree
     * - Best case: O(log n) for a balanced tree
     * - Worst case: O(n) for a degenerate tree
     *
     * EXAMPLE:
     * Removing the maximum value from this BST:
     *      8
     *     / \
     *    3   10
     *   / \    \
     *  1   6    14
     *
     * Step 1: Find the maximum value (14)
     * Step 2: Link parent (10) to the left child of the maximum (null)
     * Step 3: Return the maximum value (14)
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
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr.right != null) {
            prev = curr;
            curr = curr.right;
        }
        if (prev == null) {
            // The root is the maximum
            root = root.left;
        } else {
            // Link the parent of the maximum to the left child of the maximum
            prev.right = curr.left;
        }
        curr.left = null;
        size--;
        return curr.data;
    }

    /**
     * Removes the specified element from the tree.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * @param e the element to remove
     */
    public void remove(E e) {
        // This method is not implemented in the provided code
    }

    /**
     * Alternative implementation of the remove method.
     *
     * @param e the element to remove
     */
    public void remove1(E e) {
        // This method is not implemented in the provided code
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