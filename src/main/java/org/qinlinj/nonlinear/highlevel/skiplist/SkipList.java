package org.qinlinj.nonlinear.highlevel.skiplist;

import java.util.Random;

// @formatter:off
/**
 * SkipList Implementation
 * =======================
 *
 * CONCEPT AND PRINCIPLES:
 * A skip list is a probabilistic data structure that enhances a linked list with additional "express lanes" to
 * enable fast search, insertion and deletion operations. It's designed to achieve O(log n) time complexity for these
 * operations, similar to balanced trees, but with simpler implementation and less overhead.
 *
 * STRUCTURE:
 * 1. The base level (level 0) is a regular sorted linked-list containing all elements.
 * 2. Higher levels act as "express lanes" with progressively fewer elements.
 * 3. Each node may appear in multiple levels, with decreasing probability at higher levels.
 * 4. Each node at level i appears at level i+1 with probability p (typically 0.5).
 *
 * VISUAL REPRESENTATION:
 * Consider a skip list with elements [1, 3, 6, 9, 12, 17, 19, 21, 25]:
 *
 * Level 3: head --------------------------------------------------------> 19 -------> null
 * Level 2: head --------------------> 9 ----------------> 19 --------> 25 ----------> null
 * Level 1: head --------> 3 --------> 9 --------> 17 ----> 19 --------> 25 ---------> null
 * Level 0: head -> 1 -> 3 -> 6 -> 9 -> 12 -> 17 -> 19 -> 21 -> 25 -> null
 *
 * ADVANTAGES:
 * 1. Simpler implementation than balanced trees (AVL, Red-Black)
 * 2. Almost as efficient as balanced trees for search, insert, delete (O(log n) expected time)
 * 3. Natural support for range queries
 * 4. More memory efficient than many tree implementations for certain workloads
 * 5. Good cache locality due to sequential access patterns
 * 6. Self-adjusting structure without expensive rebalancing operations
 * 7. Excellent for concurrent access with proper implementation
 *
 * APPLICATIONS:
 * - Database indexing
 * - Priority queues
 * - Implementing sorted sets and maps
 * - In-memory caches
 * - Sparse arrays
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
     *
     * Time Complexity: O(1)
     *
     * INITIAL STATE:
     * Level 0: head -> null
     */
    public SkipList() {
        this.levelCount = 1;
        this.dummyHead = new Node(-1);
    }

    /**
     * Checks if the skip list contains a specified element.
     * Simply delegates to the get method and checks if a non-null result is returned.
     *
     * Time Complexity: O(log n) expected, O(n) worst case
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
     * Time Complexity: O(log n) expected, O(n) worst case
     *
     * EXAMPLE:
     * Consider searching for element 17 in this skip list:
     *
     * Level 2: head --------------------> 9 ----------------> 19 ---------> null
     * Level 1: head --------> 3 --------> 9 --------> 17 ----> 19 --------> null
     * Level 0: head --> 1 --> 3 --> 6 --> 9 --> 12 -> 17 ----> 19 --------> null
     *
     * Search steps:
     * 1. Start at head, level 2
     * 2. At level 2: head -> 9 (can advance since 9 < 17)
     * 3. At level 2: 9 -> 19 (stop since 19 > 17)
     * 4. Drop to level 1 at node 9
     * 5. At level 1: 9 -> 17 (found exact match)
     * 6. Return node containing 17
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
     * Time Complexity: O(1) because MAX_LEVEL is a constant
     *
     * The level generation is crucial for maintaining the skip list's logarithmic properties:
     * - Approximately 50% of nodes appear at level 1 (beyond base level 0)
     * - Approximately 25% of nodes appear at level 2
     * - Approximately 12.5% of nodes appear at level 3
     * - And so on...
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
     * Time Complexity: O(log n) expected, O(n) worst case
     *
     * EXAMPLE:
     * Consider adding element 15 to this skip list:
     *
     * Before insertion:
     * Level 2: head --------------------> 9 ----------------> 19 ---------> null
     * Level 1: head --------> 3 --------> 9 --------> 17 ----> 19 --------> null
     * Level 0: head --> 1 --> 3 --> 6 --> 9 --> 12 -> 17 ----> 19 --------> null
     *
     * Step 1: Generate random level for new node (let's say level = 2)
     *
     * Step 2: Find predecessor nodes at each level:
     * - At level 2: predecessor is head (since 9 > 15)
     * - At level 1: predecessor is 9 (since 9 < 15 < 17)
     * - At level 0: predecessor is 12 (since 12 < 15 < 17)
     *
     * Step 3: Insert node at each level:
     * Level 2: head ---------> 15 -------> 9 ----------------> 19 ----------> null
     * Level 1: head --------> 3 --------> 9 --------> 15 -> 17 ----> 19 ----> null
     * Level 0: head --> 1 --> 3 --> 6 --> 9 -> 12 --> 15 -> 17 ----> 19 ----> null
     *
     * Step 4: Fix inconsistency at level 2 (insertion order should maintain sorted order):
     * Level 2: head --------------------> 9 ---------> 15 -----> 19 --------> null
     * Level 1: head --------> 3 --------> 9 --------> 15 -> 17 ----> 19 ----> null
     * Level 0: head --> 1 --> 3 --> 6 --> 9 --> 12 -> 15 -> 17 ----> 19 ----> null
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
     * Time Complexity: O(log n) expected, O(n) worst case
     *
     * EXAMPLE:
     * Consider removing element 9 from this skip list:
     *
     * Before removal:
     * Level 2: head --------------------> 9 ----------------> 19 ---------> null
     * Level 1: head --------> 3 --------> 9 --------> 17 ----> 19 --------> null
     * Level 0: head --> 1 --> 3 --> 6 --> 9 --> 12 -> 17 ----> 19 --------> null
     *
     * Step 1: Find predecessor nodes at each level:
     * - At level 2: predecessor is head
     * - At level 1: predecessor is 3
     * - At level 0: predecessor is 6
     *
     * Step 2: Remove node 9 from each level:
     * Level 2: head -------------------------------> 19 --------> null
     * Level 1: head --------> 3 ----------------> 17 ----> 19 ----> null
     * Level 0: head --> 1 --> 3 --> 6 --> 12 ---> 17 ----> 19 ----> null
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
     * VISUAL REPRESENTATION:
     * For a node with element 17 at levels 0 and 1 in this skip list:
     *
     * Level 1: ... -> node(9) --------> node(17) ----> node(19) -> ...
     * Level 0: ... -> node(12) -------> node(17) ----> node(19) -> ...
     *
     * The node for element 17 has:
     * - data = 17
     * - nextNodes[0] = node(19)  (reference at base level)
     * - nextNodes[1] = node(19)  (reference at level 1)
     * - maxIndexLevel = 2 (appears in 2 levels: 0 and 1)
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
         * Time Complexity: O(1)
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
         * Time Complexity: O(1)
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