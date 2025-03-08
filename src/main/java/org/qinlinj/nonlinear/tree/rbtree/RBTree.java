package org.qinlinj.nonlinear.tree.rbtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// @formatter:off
/**
 * Red-Black Tree Implementation Based on 2-3-4 Trees
 *
 * CONCEPT AND PRINCIPLES:
 * A Red-Black tree is a self-balancing binary search tree where every node has a color
 * (either red or black) that ensures the tree remains approximately balanced during operations.
 *
 * Red-Black trees have the following properties:
 * 1. Every node is either red or black
 * 2. The root node is always black
 * 3. Every leaf (NIL) is black
 * 4. If a node is red, then both its children are black (no consecutive red nodes)
 * 5. For each node, all simple paths from the node to descendant leaves contain the same number of black nodes
 *
 * RELATIONSHIP WITH 2-3-4 TREES:
 * A Red-Black tree directly corresponds to a 2-3-4 tree:
 * - A black node with no red children represents a 2-node in a 2-3-4 tree
 * - A black node with one red child represents a 3-node in a 2-3-4 tree
 * - A black node with two red children represents a 4-node in a 2-3-4 tree
 *
 * Visual Representation:
 *
 * 2-3-4 Tree Node:        Red-Black Tree:
 * -------------        ---------------
 *    2-node:              B(B)
 *     (B)                /   \
 *    /   \            NIL(B) NIL(B)
 *   A     C
 *
 *    3-node:              B(B)
 *    (A,B)               /   \
 *   /  |  \            A(R)  NIL(B)
 *  *   *   C
 *
 *    4-node:              B(B)
 *   (A,B,C)             /   \
 *  / | | \            A(R)  C(R)
 * *  * *  *
 *
 * TRANSFORMATION RULES:
 * When modifying a Red-Black tree, we use these operations to maintain the properties:
 * 1. Color flips: Convert a 4-node to a 3-node by changing the parent from black to red
 *    and its two children from red to black
 * 2. Rotations: Adjust the tree structure to maintain balance
 *    - Left rotation: Rotate a node to the left when right child is red
 *    - Right rotation: Rotate a node to the right when left child is red
 *
 * ADVANTAGES:
 * 1. Guaranteed O(log n) time complexity for basic operations
 * 2. More efficient for frequent insertions and deletions compared to AVL trees
 * 3. Memory-efficient representation of 2-3-4 trees
 * 4. Widely used in practical applications (e.g., TreeMap/TreeSet in Java)
 * 5. Self-balancing without requiring height information in nodes
 *
 * Time Complexity:
 * - Search: O(log n)
 * - Insert: O(log n)
 * - Delete: O(log n)
 *
 * Space Complexity: O(n) for the tree structure
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
     * Time Complexity: O(1)
     */
    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Main method to demonstrate the Red-Black tree
     */
    public static void main(String[] args) {
        // Create a new Red-Black tree
        RBTree<Integer> tree = new RBTree<>();

        System.out.println("INSERTION TESTING");
        System.out.println("=================");

        // Insert elements and show the tree structure after each
        int[] elementsToInsert = {50, 25, 75, 12, 37, 60, 90, 6, 18, 30, 42, 55, 65, 80, 95};

        for (int element : elementsToInsert) {
            tree.add(element);
            System.out.println("\nAfter inserting " + element + ":");
            System.out.println(tree);
            System.out.println("Valid RB tree: " + tree.isValidRBTree());
        }

        System.out.println("\nTRAVERSAL TESTING");
        System.out.println("=================");
        System.out.println("In-order traversal: " + tree.inOrderTraversal());
        System.out.println("Level-order traversal: " + tree.levelOrderTraversal());

        System.out.println("\nSEARCH TESTING");
        System.out.println("==============");
        System.out.println("Contains 37: " + tree.contains(37));
        System.out.println("Contains 100: " + tree.contains(100));
        System.out.println("Minimum value: " + tree.getMin());
        System.out.println("Maximum value: " + tree.getMax());

        System.out.println("\nDELETION TESTING");
        System.out.println("================");

        // Remove elements and show the tree structure after each
        int[] elementsToRemove = {25, 75, 12, 50, 6, 55, 80, 95, 65};

        for (int element : elementsToRemove) {
            tree.remove(element);
            System.out.println("\nAfter removing " + element + ":");
            System.out.println(tree);
            System.out.println("Valid RB tree: " + tree.isValidRBTree());
        }
    }

    /**
     * Get the number of nodes in the tree
     * Time Complexity: O(1)
     *
     * @return Number of nodes
     */
    public int getSize() {
        return size;
    }

    /**
     * Check if the tree is empty
     * Time Complexity: O(1)
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /************************* Tree Transformation Operations *******************************/

    /**
     * Check if a node is red
     * In Red-Black trees, null nodes are considered black
     * Time Complexity: O(1)
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

    /**
     * Left rotation operation
     * Time Complexity: O(1)
     *
     * EXAMPLE: Left rotation around B
     *
     * Before rotation:
     *      B(B)
     *     /   \
     *    A(B)  D(R)
     *         /   \
     *        C(B)  E(B)
     *
     * After rotation:
     *      D(B)
     *     /   \
     *    B(R)  E(B)
     *   /   \
     *  A(B) C(B)
     *
     * @param node The center node of rotation
     * @return The new root node after rotation
     */
    private TreeNode leftRotate(TreeNode node) {
        TreeNode rightChild = node.right;

        // Perform rotation
        node.right = rightChild.left;
        rightChild.left = node;

        // Adjust colors - transfer original node's color to its replacement
        rightChild.color = node.color;
        node.color = RED;

        return rightChild;
    }

    /**
     * Right rotation operation
     * Time Complexity: O(1)
     *
     * EXAMPLE: Right rotation around D
     *
     * Before rotation:
     *      D(B)
     *     /   \
     *    B(R)  E(B)
     *   /   \
     *  A(B)  C(B)
     *
     * After rotation:
     *      B(B)
     *     /   \
     *    A(B)  D(R)
     *         /   \
     *        C(B)  E(B)
     *
     * @param node The center node of rotation
     * @return The new root node after rotation
     */
    private TreeNode rightRotate(TreeNode node) {
        TreeNode leftChild = node.left;

        // Perform rotation
        node.left = leftChild.right;
        leftChild.right = node;

        // Adjust colors - transfer original node's color to its replacement
        leftChild.color = node.color;
        node.color = RED;

        return leftChild;
    }

    /************************* Insertion Operations *******************************/

    /**
     * Color flip operation - converts a 4-node to a 3-node
     * Time Complexity: O(1)
     *
     * EXAMPLE: Flipping colors of a 4-node
     *
     * Before flip (4-node):
     *      B(B)
     *     /   \
     *    A(R)  C(R)
     *
     * After flip (pushed up to parent):
     *      B(R)
     *     /   \
     *    A(B)  C(B)
     *
     * @param node The node to flip colors
     */
    private void flipColors(TreeNode node) {
        // Check for null nodes to avoid NullPointerException
        if (node == null || node.left == null || node.right == null) {
            return;
        }

        // Flip colors - parent becomes red, children become black
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    /**
     * Add an element to the Red-Black tree using 2-3-4 tree principles
     * In this approach, we split 4-nodes as we go down (top-down approach)
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Adding elements 10, 20, 30, 40, 50 to an empty tree
     *
     * After adding 10:
     *    10(B)
     *
     * After adding 20:
     *    10(B)
     *      \
     *      20(R)
     *
     * After adding 30:
     *    20(B)
     *   /    \
     * 10(B)  30(B)
     * (We split the 4-node as we go down)
     *
     * After adding 40:
     *    20(B)
     *   /    \
     * 10(B)  30(B)
     *          \
     *          40(R)
     *
     * After adding 50:
     *      20(B)
     *     /    \
     *   10(B)  40(B)
     *         /   \
     *      30(B)  50(B)
     * (We split 4-nodes as we go down)
     *
     * @param element The element to add
     */
    public void add(E element) {
        // Start by splitting 4-nodes at the root (if any)
        if (isRED(root) && isRED(root.left) && isRED(root.right)) {
            // Split a 4-node at the root
            root.color = RED;
            root.left.color = BLACK;
            root.right.color = BLACK;
        }

        root = add(root, element);

        // Ensure the root is always black (Property 2)
        root.color = BLACK;
    }

    /************************* Retrieval Operations *******************************/

    /**
     * Recursively add an element to the tree using 2-3-4 tree principles
     * Time Complexity: O(log n)
     *
     * @param node The current node
     * @param element The element to add
     * @return The modified node
     */
    private TreeNode add(TreeNode node, E element) {
        // Base case: Reached a leaf position
        if (node == null) {
            size++;
            return new TreeNode(element);
        }

        // Handle 4-nodes preemptively (top-down approach)
        if (isRED(node.left) && isRED(node.right)) {
            // This node is effectively a 4-node in 2-3-4 tree, split it
            flipColors(node);
        }

        // Recursively insert into appropriate subtree
        int cmp = element.compareTo(node.data);

        if (cmp < 0) {
            node.left = add(node.left, element);
        } else if (cmp > 0) {
            node.right = add(node.right, element);
        } else {
            // Element already exists, no change needed
            return node;
        }

        // Balance the tree as needed after insertion

        // Case 1: Right-leaning red link - need left rotation
        if (isRED(node.right) && !isRED(node.left)) {
            node = leftRotate(node);
        }

        // Case 2: Left-left red links - need right rotation
        if (isRED(node.left) && isRED(node.left.left)) {
            node = rightRotate(node);
        }

        return node;
    }

    /**
     * Check if the tree contains a specific element
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Searching for value 30 in this tree:
     *      20(B)
     *     /    \
     *   10(B)  40(B)
     *         /   \
     *      30(B)  50(B)
     *
     * Steps:
     * 1. Compare with root (20): 30 > 20, go right
     * 2. Compare with 40: 30 < 40, go left
     * 3. Compare with 30: 30 == 30, found!
     *
     * @param element The element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(E element) {
        return contains(root, element);
    }

    /**
     * Recursively search for an element in the tree
     * Time Complexity: O(log n)
     *
     * @param node The current node
     * @param element The element to search for
     * @return true if the element is found, false otherwise
     */
    private boolean contains(TreeNode node, E element) {
        if (node == null) {
            return false;
        }

        int cmp = element.compareTo(node.data);

        if (cmp < 0) {
            return contains(node.left, element);
        } else if (cmp > 0) {
            return contains(node.right, element);
        } else {
            return true;  // Found the element
        }
    }

    /**
     * Get the minimum value in the tree
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Finding minimum in this tree:
     *      20(B)
     *     /    \
     *   10(B)  40(B)
     *         /   \
     *      30(B)  50(B)
     *
     * We follow the leftmost path: 20 → 10 (minimum)
     *
     * @return The minimum element
     * @throws IllegalStateException if the tree is empty
     */
    public E getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }

        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    /************************* Traversal Operations *******************************/

    /**
     * Get the maximum value in the tree
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Finding maximum in this tree:
     *      20(B)
     *     /    \
     *   10(B)  40(B)
     *         /   \
     *      30(B)  50(B)
     *
     * We follow the rightmost path: 20 → 40 → 50 (maximum)
     *
     * @return The maximum element
     * @throws IllegalStateException if the tree is empty
     */
    public E getMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }

        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }

    /**
     * Perform in-order traversal of the tree
     * Time Complexity: O(n)
     *
     * EXAMPLE: In-order traversal of this tree:
     *      20(B)
     *     /    \
     *   10(B)  40(B)
     *         /   \
     *      30(B)  50(B)
     *
     * Result: [10, 20, 30, 40, 50]
     *
     * @return List of elements in sorted order
     */
    public List<E> inOrderTraversal() {
        List<E> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    /**
     * Helper method for in-order traversal
     * Time Complexity: O(n)
     *
     * @param node Current node
     * @param result List to collect the elements
     */
    private void inOrderTraversal(TreeNode node, List<E> result) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, result);   // Visit left subtree
        result.add(node.data);                 // Visit node
        inOrderTraversal(node.right, result);  // Visit right subtree
    }

    /************************* Deletion Operations *******************************/

    /**
     * Perform level-order traversal of the tree
     * Time Complexity: O(n)
     *
     * EXAMPLE: Level-order traversal of this tree:
     *      20(B)
     *     /    \
     *   10(B)  40(B)
     *         /   \
     *      30(B)  50(B)
     *
     * Result: [20, 10, 40, 30, 50]
     *
     * @return List of elements in level order
     */
    public List<E> levelOrderTraversal() {
        List<E> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.data);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return result;
    }

    /**
     * Remove an element from the tree
     * This implementation uses a top-down approach, ensuring nodes have required properties
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Removing 30 from this tree:
     *      20(B)
     *     /    \
     *   10(B)  40(B)
     *         /   \
     *      30(B)  50(B)
     *
     * Result:
     *      20(B)
     *     /    \
     *   10(B)  40(B)
     *            \
     *           50(B)
     *
     * @param element The element to remove
     * @return true if the element was removed, false if not found
     */
    public boolean remove(E element) {
        if (!contains(element)) {
            return false;
        }

        // If both children of root are black, set root to red temporarily
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }

        root = remove(root, element);

        // Restore root to black if tree is not empty
        if (!isEmpty()) {
            root.color = BLACK;
        }

        return true;
    }

    /**
     * Recursively remove an element from the tree
     * Time Complexity: O(log n)
     *
     * @param node Current node
     * @param element Element to remove
     * @return Modified node
     */
    private TreeNode remove(TreeNode node, E element) {
        if (node == null) {
            return null;
        }

        int cmp = element.compareTo(node.data);

        if (cmp < 0) {
            // Going left, ensure path has required properties
            if (!isRED(node.left) && node.left != null && !isRED(node.left.left)) {
                // Make node.left or one of its children red
                node = moveRedLeft(node);
            }

            node.left = remove(node.left, element);
        } else {
            // Handle red left child
            if (isRED(node.left)) {
                node = rightRotate(node);
            }

            // If found and is a leaf, remove it
            if (cmp == 0 && node.right == null) {
                size--;
                return null;
            }

            // Going right, ensure path has required properties
            if (!isRED(node.right) && node.right != null && !isRED(node.right.left)) {
                node = moveRedRight(node);
            }

            if (cmp == 0) {
                // Replace with successor (minimum of right subtree)
                TreeNode successor = findMin(node.right);
                node.data = successor.data;
                node.right = removeMin(node.right);
            } else {
                node.right = remove(node.right, element);
            }
        }

        // Balance the tree after removal
        return balance(node);
    }

    /**
     * Find the minimum node in a subtree
     * Time Complexity: O(log n)
     *
     * @param node Root of the subtree
     * @return Node with minimum value
     */
    private TreeNode findMin(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    /**
     * Remove the minimum element from a subtree
     * Time Complexity: O(log n)
     *
     * @param node Root of the subtree
     * @return Modified subtree
     */
    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            size--;
            return null;
        }

        if (!isRED(node.left) && node.left != null && !isRED(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = removeMin(node.left);

        return balance(node);
    }

    /**
     * Move a red node from right to left
     * Time Complexity: O(1)
     *
     * EXAMPLE: Moving red from right to left
     *
     * Before:
     *      B(B)
     *     /   \
     *    A(B)  C(B)
     *   /       \
     *  T1       T2(R)
     *
     * After:
     *      B(B)
     *     /   \
     *    A(R)  C(B)
     *   /       \
     *  T1       T2(B)
     *
     * @param node The node to process
     * @return The processed node
     */
    private TreeNode moveRedLeft(TreeNode node) {
        // Safety check to prevent NullPointerException
        if (node == null || node.right == null) {
            return node;
        }

        flipColors(node);

        if (node.right != null && isRED(node.right.left)) {
            node.right = rightRotate(node.right);
            node = leftRotate(node);
            flipColors(node);
        }

        return node;
    }

    /**
     * Move a red node from left to right
     * Time Complexity: O(1)
     *
     * EXAMPLE: Moving red from left to right
     *
     * Before:
     *      B(B)
     *     /   \
     *    A(R)  C(B)
     *     \     /
     *     T1   T2
     *
     * After:
     *      B(B)
     *     /   \
     *    A(B)  C(R)
     *     \     /
     *     T1   T2
     *
     * @param node The node to process
     * @return The processed node
     */
    private TreeNode moveRedRight(TreeNode node) {
        // Safety check to prevent NullPointerException
        if (node == null || node.left == null) {
            return node;
        }

        flipColors(node);

        if (node.left != null && isRED(node.left.left)) {
            node = rightRotate(node);
            flipColors(node);
        }

        return node;
    }

    /************************* Validation Operations *******************************/

    /**
     * Balance a node to maintain Red-Black tree properties
     * Time Complexity: O(1)
     *
     * @param node The node to balance
     * @return The balanced node
     */
    private TreeNode balance(TreeNode node) {
        if (node == null) {
            return null;
        }

        // Fix right-leaning red link
        if (isRED(node.right) && !isRED(node.left)) {
            node = leftRotate(node);
        }

        // Fix two consecutive left-leaning red links
        if (isRED(node.left) && node.left != null && isRED(node.left.left)) {
            node = rightRotate(node);
        }

        // Split 4-node
        if (isRED(node.left) && isRED(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /**
     * Check if the tree satisfies all Red-Black tree properties
     * Time Complexity: O(n)
     *
     * @return true if the tree is a valid Red-Black tree
     */
    public boolean isValidRBTree() {
        if (isEmpty()) {
            return true;
        }

        // Property 2: Root must be black
        if (isRED(root)) {
            System.out.println("Root is not black");
            return false;
        }

        // Check all other properties
        return checkBlackHeight(root) != -1;
    }

    /**
     * Check if the tree has a consistent black height and no consecutive red nodes
     * Time Complexity: O(n)
     *
     * @param node The current node
     * @return The black height if valid, -1 if invalid
     */
    private int checkBlackHeight(TreeNode node) {
        if (node == null) {
            return 0;  // Null nodes are black
        }

        // Property 4: No consecutive red nodes
        if (isRED(node) && (isRED(node.left) || isRED(node.right))) {
            System.out.println("Consecutive red nodes detected");
            return -1;
        }

        // Check left and right subtrees
        int leftHeight = checkBlackHeight(node.left);
        int rightHeight = checkBlackHeight(node.right);

        // If either subtree is invalid, propagate the error
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        // Property 5: All paths have same black height
        if (leftHeight != rightHeight) {
            System.out.println("Inconsistent black height");
            return -1;
        }

        // Return this node's contribution to black height
        return isRED(node) ? leftHeight : leftHeight + 1;
    }

    /**
     * Get a string representation of the tree for visualization
     * Time Complexity: O(n)
     *
     * @return String representation of the tree
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Empty tree";
        }

        StringBuilder sb = new StringBuilder();
        printTree(root, sb, 0);
        return sb.toString();
    }

    /**
     * Helper method for tree visualization
     * Time Complexity: O(n)
     *
     * @param node Current node
     * @param sb StringBuilder for output
     * @param level Current tree level (for indentation)
     */
    private void printTree(TreeNode node, StringBuilder sb, int level) {
        if (node == null) {
            return;
        }

        // Print right subtree first (top-down, right-to-left)
        printTree(node.right, sb, level + 1);

        // Print current node with indentation
        for (int i = 0; i < level; i++) {
            sb.append("    ");
        }

        // Add color indicator
        sb.append(node.data).append(node.color == RED ? "(R)" : "(B)").append("\n");

        // Print left subtree
        printTree(node.left, sb, level + 1);
    }

    /**
     * Tree node class for Red-Black Tree
     */
    private class TreeNode {
        E data;           // Data stored in the node
        TreeNode left;    // Left child
        TreeNode right;   // Right child
        boolean color;    // Node color (RED or BLACK)

        /**
         * Create a new node with the given data
         * All new nodes are initially colored red (as in a 2-3-4 tree insertion)
         * Time Complexity: O(1)
         *
         * @param data Data to store in the node
         */
        public TreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.color = RED;  // New nodes are always red
        }
    }
}
