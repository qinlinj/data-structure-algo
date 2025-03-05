package org.qinlinj.nonlinear.highlevel.skiplist;

import java.util.Random;

/**
 * SkipList implementation for efficient search, insertion and deletion operations.
 * A skip list is a probabilistic data structure that allows O(log n) search complexity
 * as well as O(log n) insertion and deletion complexity within an ordered sequence.
 * <p>
 * This implementation uses a maximum of 16 levels, including the base level (original linked list).
 *
 * @param <E> Type of elements stored in the skip list, must implement Comparable interface
 */
public class SkipList<E extends Comparable<E>> {
    // Maximum height of the skip list, including the base linked list level
    private static final int MAX_LEVEL = 16;
    // The current maximum height of this skip list (includes the base linked list level, so minimum is 1)
    private int levelCount;
    // Dummy head node to simplify list operations by avoiding null checks
    private Node dummyHead;
    // Random number generator for determining the level of new nodes
    private Random random = new Random();

    /**
     * Constructs a new empty SkipList.
     * Initializes the level count to 1 (just the base level)
     * and creates a dummy head node with a sentinel value.
     */
    public SkipList() {
        this.levelCount = 1;
        this.dummyHead = new Node(-1);
    }

    /**
     * Checks if the skip list contains a specified element.
     *
     * @param e The element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(E e) {
        return get(e) != null;
    }

    /**
     * Searches for a node containing the specified element.
     * The search begins at the highest level of the skip list and works down,
     * taking advantage of the skip list structure for efficient search.
     *
     * @param e The element to search for
     * @return The node containing the element, or null if not found
     */
    public Node get(E e) {
        Node curr = dummyHead;
        // Start from the highest level and work down, finding the interval where the element should be
        for (int i = levelCount - 1; i >= 0; i--) {
            // Traverse forward at the current level until we find a node with data >= e
            while (curr.nextNodes[i] != null
                    && curr.nextNodes[i].data.compareTo(e) < 0) {
                curr = curr.nextNodes[i];
            }
        }

        // Check if the next node at the base level contains the target element
        if (curr.nextNodes[0] != null
                && curr.nextNodes[0].data.compareTo(e) == 0) {
            return curr.nextNodes[0];
        }

        // Element not found
        return null;
    }

    /**
     * Generates a random level for a new node according to a probabilistic algorithm.
     * Each level has a 50% chance of being included (determined by random odd/even results).
     * This helps maintain the skip list's balanced structure probabilistically.
     *
     * @return A random level between 1 and MAX_LEVEL
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    /**
     * Adds a new element to the skip list.
     * The element is added at multiple levels according to the randomly generated level.
     *
     * @param e The element to add to the skip list
     */
    public void add(E e) {
        // Determine the level for the new node
        // If the list is empty, add at level 1 (base level only)
        // Otherwise, use the probabilistic algorithm to determine the level
        int level = dummyHead.nextNodes[0] == null ? 1 : randomLevel();

        // Initialize an array to keep track of the predecessor nodes at each level
        Node[] prevNodes = new Node[level];
        for (int i = 0; i < level; i++) {
            prevNodes[i] = dummyHead;
        }

        // 1. Find the predecessor nodes at each relevant level
        Node prev = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            // Move forward at the current level until we find a node with data >= e
            while (prev.nextNodes[i] != null
                    && prev.nextNodes[i].data.compareTo(e) < 0) {
                prev = prev.nextNodes[i];
            }

            // If this level is within our target level range, record the predecessor
            if (i < level)
                prevNodes[i] = prev;
        }

        // 2. Create a new node and insert it at the correct position at each level
        Node newNode = new Node(e);
        newNode.maxIndexLevel = level;
        for (int i = 0; i < level; i++) {
            // Connect the new node to the next node at this level
            newNode.nextNodes[i] = prevNodes[i].nextNodes[i];
            // Connect the predecessor to the new node at this level
            prevNodes[i].nextNodes[i] = newNode;
        }

        // Update the skip list's maximum height if necessary
        if (levelCount < level) levelCount = level;
    }

    /**
     * Removes an element from the skip list if it exists.
     * The element is removed from all levels it appears in.
     *
     * @param e The element to remove from the skip list
     */
    public void remove(E e) {
        // 1. Find the predecessor nodes for the target element at each level
        Node<E>[] prevNodes = new Node[levelCount];
        Node prev = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (prev.nextNodes[i] != null
                    && prev.nextNodes[i].data.compareTo(e) < 0) {
                prev = prev.nextNodes[i];
            }

            prevNodes[i] = prev;
        }

        // 2. Remove the node at each level if it exists
        // First, check if the node exists at the base level
        if (prev.nextNodes[0] != null
                && prev.nextNodes[0].data.compareTo(e) == 0) {
            // For each level, check if the node exists and remove it
            for (int i = levelCount - 1; i >= 0; i--) {
                Node delNode = prevNodes[i].nextNodes[i];
                if (delNode != null && delNode.data.compareTo(e) == 0) {
                    // Update the reference to bypass the node being deleted
                    prevNodes[i].nextNodes[i] = delNode.nextNodes[i];
                    // Clear the reference from the deleted node
                    delNode.nextNodes[i] = null;
                }
            }
        }
    }

    /**
     * Node class for the SkipList. Each node contains the data element and
     * an array of references to the next nodes at each level.
     *
     * @param <E> Type of data stored in the node, must implement Comparable interface
     */
    private class Node<E extends Comparable<E>> {
        // Data stored in this node
        E data;

        /**
         * Array of next node references at each level of the skip list.
         * The array index corresponds to the level:
         * nextNodes[0] refers to the next node at the first level (base level)
         * nextNodes[1] refers to the next node at the second level
         * nextNodes[2] refers to the next node at the third level
         * and so on up to nextNodes[MAX_LEVEL-1]
         * <p>
         * When traversing from a higher level to a lower level, we simply decrease the array index by 1.
         */
        Node[] nextNodes;

        // Records the maximum index level that this node participates in
        int maxIndexLevel = 0;

        /**
         * Constructor for creating a new node with the given data.
         * Initializes the nextNodes array with the maximum possible levels.
         *
         * @param data The element to be stored in this node
         */
        Node(E data) {
            this.data = data;
            nextNodes = new Node[MAX_LEVEL];
        }

        /**
         * Returns a string representation of this node, including its data and level information.
         *
         * @return String representation of the node
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxIndexLevel);
            builder.append(" }");
            return builder.toString();
        }
    }
}
