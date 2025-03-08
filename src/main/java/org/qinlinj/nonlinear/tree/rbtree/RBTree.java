package org.qinlinj.nonlinear.tree.rbtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// @formatter:off
/**
 * Red-Black Tree Implementation
 *
 * CONCEPT AND PRINCIPLES:
 * A Red-Black tree is a self-balancing binary search tree that maintains balance using node colors.
 * It's a representation of a 2-3 tree that uses color to encode the 3-nodes.
 *
 * Red-Black trees have the following properties:
 * 1. Each node is either red or black
 * 2. The root node is always black
 * 3. Every leaf node (NIL/null) is black
 * 4. If a node is red, then both its children are black (no consecutive red nodes)
 * 5. For each node, all paths from the node to its descendant leaves contain the same number of black nodes
 *
 * RELATIONSHIP WITH 2-3 TREES:
 * A Red-Black tree is a binary tree representation of a 2-3 tree:
 * - A 2-node in a 2-3 tree corresponds to a black node in a Red-Black tree
 * - A 3-node in a 2-3 tree corresponds to a black node with a red child:
 *   * Left-leaning: Black node with red left child
 *   * Right-leaning: Black node with red right child
 *
 * Visual Representation:
 *
 * 2-3 Tree:         Red-Black Tree:
 *    (B)               B(B)
 *   /   \             /   \
 *  A     C           A(B)  C(B)
 *
 * 2-3 Tree:         Red-Black Tree (Left-leaning):
 *   (A,B)              B(B)
 *  /  |  \            /   \
 * *   *   C          A(R)  C(B)
 *
 * ADVANTAGES:
 * 1. Guaranteed O(log n) time complexity for basic operations
 * 2. More efficient memory usage compared to AVL trees
 * 3. Faster insertion and deletion than AVL trees at the cost of slightly slower lookups
 * 4. Self-balancing without explicit height tracking
 * 5. Widely used in practical systems (e.g., many standard libraries)
 *
 * TRANSFORMATION RULES:
 * When inserting or deleting nodes, we apply transformations to maintain the Red-Black properties:
 * 1. Color flips: When a node has two red children, flip colors
 * 2. Left rotation: When a node has a red right child but black left child
 * 3. Right rotation: When a node has a red left child and red left-left grandchild
 *
 * Time Complexity: All operations (search, insert, delete) have O(log n) time complexity
 * Space Complexity: O(n) for the tree structure, O(log n) for recursive call stack
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
     * Main method for testing the Red-Black tree implementation
     * Time Complexity: O(n log n) for building and testing the tree
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new Red-Black tree
        RBTree<Integer> rbTree = new RBTree<>();

        // Test insertion with visualization
        System.out.println("TESTING INSERTION:");
        System.out.println("==================");

        // Insert elements and show tree structure after each insert
        int[] elementsToInsert = {41, 38, 31, 12, 19, 8, 50, 45, 60};
        for (int element : elementsToInsert) {
            rbTree.add(element);
            System.out.println("\nAfter inserting " + element + ":");
            System.out.println(rbTree);
            System.out.println("Tree is valid: " + rbTree.isValidRBTree());
        }

        // Test searching
        System.out.println("\nTESTING SEARCH:");
        System.out.println("===============");

        int[] elementsToFind = {19, 8, 25, 45};
        for (int element : elementsToFind) {
            System.out.println("Contains " + element + ": " + rbTree.contains(element));
        }

        // Test tree traversal
        System.out.println("\nTESTING TRAVERSAL:");
        System.out.println("==================");
        System.out.println("In-order traversal (sorted): " + rbTree.inOrder());
        System.out.println("Pre-order traversal: " + rbTree.preOrder());
        System.out.println("Post-order traversal: " + rbTree.postOrder());

        // Test finding min and max values
        System.out.println("\nTESTING MIN/MAX:");
        System.out.println("================");
        System.out.println("Minimum value: " + rbTree.minValue());
        System.out.println("Maximum value: " + rbTree.maxValue());

        // Test deletion operations
        System.out.println("\nTESTING DELETION:");
        System.out.println("=================");

        // Test removing specific elements
        int[] elementsToRemove = {12, 41, 50};
        for (int element : elementsToRemove) {
            System.out.println("\nRemoving " + element + ":");
            rbTree.remove(element);
            System.out.println(rbTree);
            System.out.println("Tree is valid: " + rbTree.isValidRBTree());
        }

        // Test removeMin and removeMax
        System.out.println("\nRemoving minimum element (" + rbTree.minValue() + "):");
        rbTree.removeMin();
        System.out.println(rbTree);
        System.out.println("Tree is valid: " + rbTree.isValidRBTree());

        System.out.println("\nRemoving maximum element (" + rbTree.maxValue() + "):");
        rbTree.removeMax();
        System.out.println(rbTree);
        System.out.println("Tree is valid: " + rbTree.isValidRBTree());

        // Test with a larger tree
        System.out.println("\nTESTING WITH LARGER DATASET:");
        System.out.println("===========================");

        RBTree<Integer> largeTree = new RBTree<>();
        for (int i = 1; i <= 100; i++) {
            largeTree.add(i);
        }

        System.out.println("Large tree size: " + largeTree.getSize());
        System.out.println("Large tree contains 50: " + largeTree.contains(50));
        System.out.println("Large tree contains 200: " + largeTree.contains(200));
        System.out.println("Is valid Red-Black tree: " + largeTree.isValidRBTree());

        // Remove elements from the large tree
        for (int i = 1; i <= 50; i++) {
            largeTree.remove(i);
        }

        System.out.println("\nAfter removing elements 1-50:");
        System.out.println("Large tree size: " + largeTree.getSize());
        System.out.println("Large tree contains 25: " + largeTree.contains(25));
        System.out.println("Large tree contains 75: " + largeTree.contains(75));
        System.out.println("Is valid Red-Black tree: " + largeTree.isValidRBTree());
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

    /************************* Insertion Operations *******************************/

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
     * Used when a node has a red right child (right-leaning red link)
     * Time Complexity: O(1)
     *
     * EXAMPLE:
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
     *  A(B)  C(B)
     *
     * @param node The center node of rotation (B in the example)
     * @return The new root node after rotation (D in the example)
     */
    private TreeNode leftRotate(TreeNode node) {
        TreeNode x = node.right;

        // Perform rotation
        node.right = x.left;
        x.left = node;

        // Adjust colors - preserve original node's color for x
        x.color = node.color;
        node.color = RED; // Node becomes red

        return x;
    }

    /**
     * Color flip operation
     * Used when both children of a node are red
     * Transforms a temporary 4-node into a structure with a red parent and two black children
     * Time Complexity: O(1)
     *
     * EXAMPLE:
     * Before flip:
     *      B(B)
     *     /   \
     *    A(R)  C(R)
     *
     * After flip:
     *      B(R)
     *     /   \
     *    A(B)  C(B)
     *
     * @param node The node to flip colors
     */
    private void flipColors(TreeNode node) {
        node.color = RED;

        // Only change child colors if they exist
        if (node.left != null) {
            node.left.color = BLACK;
        }

        if (node.right != null) {
            node.right.color = BLACK;
        }
    }

    /**
     * Right rotation operation
     * Used when a node has a red left child and a red left-left grandchild
     * Time Complexity: O(1)
     *
     * EXAMPLE:
     * Before rotation:
     *      C(B)
     *     /   \
     *    A(R)  D(B)
     *   /   \
     *  B(R)  E(B)
     *
     * After rotation:
     *      A(B)
     *     /   \
     *    B(B)  C(R)
     *         /   \
     *        E(B)  D(B)
     *
     * @param node The center node of rotation (C in the example)
     * @return The new root node after rotation (A in the example)
     */
    private TreeNode rightRotate(TreeNode node) {
        TreeNode x = node.left;

        // Perform rotation
        node.left = x.right;
        x.right = node;

        // Adjust colors - preserve original node's color for x
        x.color = node.color;
        node.color = RED; // Node becomes red

        return x;
    }

    /**
     * Add an element to the Red-Black tree
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Adding nodes 10, 5, 15, 3, 7 to an empty tree
     *
     * Start with empty tree
     *
     * After adding 10:
     *    10(B)
     *
     * After adding 5:
     *    10(B)
     *    /
     *   5(R)
     *
     * After adding 15:
     *    10(B)
     *   /    \
     *  5(R)   15(R)
     *
     * (Color flip happens, making 10 red and children black)
     *    10(B) <- root is always black
     *   /    \
     *  5(B)   15(B)
     *
     * After adding 3:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * /
     * 3(R)
     *
     * After adding 7:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(R) 7(R)
     *
     * (Color flip happens at node 5)
     *    10(B)
     *   /    \
     *  5(R)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * @param e The element to add
     */
    public void add(E e) {
        root = add(root, e);
        // Maintain the root as black (property 2)
        root.color = BLACK;
    }

    /************************* Retrieval Operations *******************************/

    /**
     * Recursively add an element to a specified subtree
     * Time Complexity: O(log n)
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

    /**
     * Check if the Red-Black tree contains the target element
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Searching for 7 in this tree:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * Steps:
     * 1. Start at root (10)
     * 2. 7 < 10, go to left child (5)
     * 3. 7 > 5, go to right child (7)
     * 4. Found 7!
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
     * Time Complexity: O(log n)
     *
     * @param target The target element
     * @return The node containing the target element, or null if not found
     */
    public TreeNode find(E target) {
        return find(root, target);
    }

    /**
     * Recursively find the target element in the specified subtree
     * Time Complexity: O(log n)
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

    /**
     * Pre-order traversal: Root-Left-Right
     * Time Complexity: O(n)
     *
     * @return List containing the pre-order traversal result
     */
    public List<E> preOrder() {
        List<E> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    /**
     * Recursively perform pre-order traversal
     * Time Complexity: O(n)
     *
     * @param node The current node
     * @param res  The list to store the traversal result
     */
    private void preOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        res.add(node.data); // Visit root node first
        preOrder(node.left, res); // Then traverse left subtree
        preOrder(node.right, res); // Finally traverse right subtree
    }

    /**
     * In-order traversal: Left-Root-Right
     * The result of in-order traversal is sorted
     * Time Complexity: O(n)
     *
     * EXAMPLE: In-order traversal of this tree:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * Result: [3, 5, 7, 10, 15]
     *
     * @return List containing the in-order traversal result
     */
    public List<E> inOrder() {
        List<E> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    /**
     * Recursively perform in-order traversal
     * Time Complexity: O(n)
     *
     * @param node The current node
     * @param res  The list to store the traversal result
     */
    private void inOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        inOrder(node.left, res); // Traverse left subtree first
        res.add(node.data); // Then visit root node
        inOrder(node.right, res); // Finally traverse right subtree
    }

    /**
     * Post-order traversal: Left-Right-Root
     * Time Complexity: O(n)
     *
     * @return List containing the post-order traversal result
     */
    public List<E> postOrder() {
        LinkedList<E> res = new LinkedList<>();
        postOrder(root, res);
        return res;
    }

    /**
     * Recursively perform post-order traversal
     * Time Complexity: O(n)
     *
     * @param node The current node
     * @param res  The list to store the traversal result
     */
    private void postOrder(TreeNode node, List<E> res) {
        if (node == null) {
            return;
        }

        postOrder(node.left, res); // Traverse left subtree first
        postOrder(node.right, res); // Then traverse right subtree
        res.add(node.data); // Finally visit root node
    }

    /**
     * Get the minimum value in the Red-Black tree
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Finding minimum in this tree:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * We follow the leftmost path: 10 → 5 → 3 (minimum)
     *
     * @return The minimum element
     * @throws RuntimeException If the tree is empty
     */
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return minValue(root).data;
    }

    /**
     * Find the node with minimum value in the specified subtree
     * The minimum value is always at the leftmost node
     * Time Complexity: O(log n)
     *
     * @param node The root of the current subtree
     * @return The node containing the minimum value
     */
    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        return minValue(node.left);
    }

    /**
     * Get the maximum value in the Red-Black tree
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Finding maximum in this tree:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * We follow the rightmost path: 10 → 15 (maximum)
     *
     * @return The maximum element
     * @throws RuntimeException If the tree is empty
     */
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        return maxValue(root).data;
    }

    /************************* Deletion Operations *******************************/

    /**
     * Find the node with maximum value in the specified subtree
     * The maximum value is always at the rightmost node
     * Time Complexity: O(log n)
     *
     * @param node The root of the current subtree
     * @return The node containing the maximum value
     */
    private TreeNode maxValue(TreeNode node) {
        if (node.right == null) return node;
        return maxValue(node.right);
    }

    /**
     * Remove the minimum element from the Red-Black tree
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Removing minimum (3) from this tree:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(R) 7(B)
     *
     * First, make root red if both children are black:
     *    10(R)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(R) 7(B)
     *
     * Then proceed with removal, resulting in:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     *   \
     *    7(B)
     *
     * @return The removed minimum element
     */
    public E removeMin() {
        E res = minValue();

        // If the root is a 2-node, make it a 3-node or 4-node
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }

        root = removeMin(root);

        // Maintain the root as black
        if (root != null) {
            root.color = BLACK;
        }

        return res;
    }

    /**
     * Recursively remove the minimum node from the specified subtree
     * Time Complexity: O(log n)
     *
     * @param node The root of the current subtree
     * @return The root of the subtree after removing the minimum node
     */
    private TreeNode removeMin(TreeNode node) {
        // Found the minimum node (no left child)
        if (node.left == null) {
            // Free the node and update count
            TreeNode rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        // Ensure that the current node's left child or left grandchild is red
        // This ensures we don't delete a black node that would break Red-Black properties
        if (!isRED(node.left) && !isRED(node.left.left)) {
            node = moveRedLeft(node);
        }

        // Continue finding and removing the minimum node in the left subtree
        node.left = removeMin(node.left);

        // Restore Red-Black tree properties
        return balance(node);
    }

    /**
     * Remove the maximum element from the Red-Black tree
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Removing maximum (15) from this tree:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * First, make root red if both children are black:
     *    10(R)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * Then proceed with removal, resulting in:
     *    10(B)
     *   /
     *  5(B)
     * / \
     * 3(B) 7(B)
     *
     * @return The removed maximum element
     */
    public E removeMax() {
        E res = maxValue();

        // If the root is a 2-node, make it a 3-node or 4-node
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }

        root = removeMax(root);

        // Maintain the root as black
        if (root != null) {
            root.color = BLACK;
        }

        return res;
    }

    /**
     * Recursively remove the maximum node from the specified subtree
     * Time Complexity: O(log n)
     *
     * @param node The root of the current subtree
     * @return The root of the subtree after removing the maximum node
     */
    private TreeNode removeMax(TreeNode node) {
        // If the left child is red, perform right rotation
        if (isRED(node.left)) {
            node = rightRotate(node);
        }

        // Found the maximum node (no right child)
        if (node.right == null) {
            // Free the node and update count
            TreeNode leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        // Ensure that the current node's right child or right grandchild is red
        if (!isRED(node.right) && !isRED(node.right.left)) {
            node = moveRedRight(node);
        }

        // Continue finding and removing the maximum node in the right subtree
        node.right = removeMax(node.right);

        // Restore Red-Black tree properties
        return balance(node);
    }

    /**
     * Remove a specific element from the Red-Black tree
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Removing element 5 from this tree:
     *    10(B)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * First, make root red if both children are black:
     *    10(R)
     *   /    \
     *  5(B)   15(B)
     * / \
     * 3(B) 7(B)
     *
     * Then find element 5 and replace it with its successor (7):
     *    10(B)
     *   /    \
     *  7(B)   15(B)
     * /
     * 3(B)
     *
     * @param e The element to remove
     */
    public void remove(E e) {
        if (!contains(e)) {
            return;  // If the element doesn't exist, return directly
        }

        // If the root is a 2-node, make it a 3-node or 4-node
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }

        root = remove(root, e);

        // Maintain the root as black
        if (root != null) {
            root.color = BLACK;
        }
    }

    /**
     * Recursively remove a specific element from the specified subtree
     * Time Complexity: O(log n)
     *
     * @param node The root of the current subtree
     * @param e    The element to remove
     * @return The root of the subtree after removing the element
     */
    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;

        // Target element is less than current node, continue in left subtree
        if (e.compareTo(node.data) < 0) {
            // Ensure left child or its child is red, for safe deletion
            if (!isRED(node.left) && !isRED(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = remove(node.left, e);
        }
        // Target element is greater than or equal to current node
        else {
            // If left child is red, perform right rotation
            if (isRED(node.left)) {
                node = rightRotate(node);
            }

            // If found target element and it's a leaf node, delete directly
            if (e.compareTo(node.data) == 0 && node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // If target element is greater than current node, or need to find successor in right subtree
            if (e.compareTo(node.data) > 0 ||
                    (e.compareTo(node.data) == 0 && node.right != null)) {

                // Ensure right child or its child is red, for safe deletion
                if (!isRED(node.right) && !isRED(node.right.left)) {
                    node = moveRedRight(node);
                }

                // If found target element
                if (e.compareTo(node.data) == 0) {
                    // Find successor node (minimum node in right subtree)
                    TreeNode successor = minValue(node.right);
                    // Replace current node's value with successor's value
                    node.data = successor.data;
                    // Delete successor node
                    node.right = removeMin(node.right);
                } else {
                    // Continue searching in right subtree
                    node.right = remove(node.right, e);
                }
            }
        }

        // Restore Red-Black tree properties
        return balance(node);
    }

    /**
     * Make the left red child black, and make the left-left grandchild red
     * This operation ensures we can safely delete a node
     * Time Complexity: O(1)
     *
     * EXAMPLE: Before moveRedLeft
     *      B(B)
     *     /    \
     *    A(B)   C(B)
     *          /   \
     *         X(B)  Y(R)
     *
     * After moveRedLeft:
     *      B(B)
     *     /    \
     *    A(R)   C(B)
     *   /      /   \
     *  X(R)    Z(B) Y(B)
     *
     * @param node The node to process
     * @return The processed node
     */
    private TreeNode moveRedLeft(TreeNode node) {
        // Check if node has necessary children before flipping colors
        if (node.right == null) {
            // Handle the case where right child is missing
            // This might require a different approach depending on your specific implementation
            node.color = RED;
            return node;
        }

        // Flip colors, borrow a red node
        flipColors(node);

        // If right child's left child is red, perform double rotation
        if (node.right != null && isRED(node.right.left)) {
            node.right = rightRotate(node.right);
            node = leftRotate(node);
            flipColors(node);
        }

        return node;
    }

    /**
     * Make the right red child black, and make the right-right grandchild red
     * This operation ensures we can safely delete a node
     * Time Complexity: O(1)
     *
     * EXAMPLE: Before moveRedRight
     *      B(B)
     *     /    \
     *    A(B)   C(B)
     *   /   \
     *  X(R)  Y(B)
     *
     * After moveRedRight:
     *      B(B)
     *     /    \
     *    A(B)   C(R)
     *   /   \     \
     *  X(B)  Y(B)  Z(R)
     *
     * @param node The node to process
     * @return The processed node
     */
    private TreeNode moveRedRight(TreeNode node) {
        // Check if node has necessary children before flipping colors
        if (node.left == null) {
            // Handle the case where left child is missing
            node.color = RED;
            return node;
        }

        // Flip colors, borrow a red node
        flipColors(node);

        // If left child's left child is red, perform rotation
        if (node.left != null && isRED(node.left.left)) {
            node = rightRotate(node);
            flipColors(node);
        }

        return node;
    }

    /**
     * Balance operation to restore Red-Black tree properties
     * Time Complexity: O(1)
     *
     * This method applies a series of rotations and color adjustments to restore
     * the tree's balance after operations like insertion or deletion
     *
     * EXAMPLE: Balancing a node with a red right child
     * Before:          After:
     *    B(B)            D(B)
     *   /   \           /   \
     *  A(B)  D(R)      B(R)  E(B)
     *       /   \     /   \
     *      C(B)  E(B) A(B) C(B)
     *
     * @param node The node to balance
     * @return The balanced node
     */
    private TreeNode balance(TreeNode node) {
        if (node == null) return null;

        // If right child is red but left child is not, perform left rotation
        if (isRED(node.right) && !isRED(node.left)) {
            node = leftRotate(node);
        }

        // If left child and left-left grandchild are both red, perform right rotation
        if (isRED(node.left) && isRED(node.left.left)) {
            node = rightRotate(node);
        }

        // If both children are red, perform color flip
        if (isRED(node.left) && isRED(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /**
     * Verify that the tree maintains all Red-Black properties
     * Time Complexity: O(n)
     *
     * @return true if the tree is a valid Red-Black tree
     */
    public boolean isValidRBTree() {
        // Property 2: Root must be black
        if (isRED(root)) {
            System.out.println("Root is not black");
            return false;
        }

        // Check all other properties
        return isValidRBTree(root) != -1;
    }

    /**
     * Helper method to check Red-Black tree properties
     * Returns the black height if valid, or -1 if invalid
     * Time Complexity: O(n)
     *
     * @param node The current node
     * @return black height if valid, -1 if invalid
     */
    private int isValidRBTree(TreeNode node) {
        if (node == null) return 0; // Null nodes are black, so black height is 0

        // Property 4: No consecutive red nodes
        if (isRED(node) && (isRED(node.left) || isRED(node.right))) {
            System.out.println("Red node has red child");
            return -1;
        }

        // Check left subtree
        int leftHeight = isValidRBTree(node.left);
        if (leftHeight == -1) return -1;

        // Check right subtree
        int rightHeight = isValidRBTree(node.right);
        if (rightHeight == -1) return -1;

        // Property 5: All paths have same black height
        if (leftHeight != rightHeight) {
            System.out.println("Black heights differ");
            return -1;
        }

        // Return black height of this subtree
        return isRED(node) ? leftHeight : leftHeight + 1;
    }

    /**
     * Get a string representation of the tree for debugging
     * Time Complexity: O(n)
     *
     * @return String representation of the tree
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, 0);
        return sb.toString();
    }

    /**
     * Helper method for toString
     * Time Complexity: O(n)
     *
     * @param node The current node
     * @param sb StringBuilder to append to
     * @param level Current level in the tree (for indentation)
     */
    private void toString(TreeNode node, StringBuilder sb, int level) {
        if (node == null) return;

        // Indent based on level
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }

        // Append node value and color
        sb.append(node.data).append(node.color == RED ? "(R)" : "(B)").append("\n");

        // Process children
        toString(node.left, sb, level + 1);
        toString(node.right, sb, level + 1);
    }

    /**
     * Red-Black Tree Node inner class
     */
    private class TreeNode {
        E data;              // Data stored in the node
        TreeNode left;       // Left child
        TreeNode right;      // Right child
        boolean color;       // Node color

        /**
         * Constructor: Create a new node
         * New nodes are red by default (satisfies property 5, doesn't increase black height)
         * Time Complexity: O(1)
         *
         * @param data Node data
         */
        public TreeNode(E data) {
            this.data = data;
            this.color = RED;
        }
    }
}