package org.qinlinj.practical.cache;

import java.util.*;

/**
 * LFU (Least Frequently Used) Cache Implementation Using Doubly Linked Lists
 * <p>
 * Concept and Principles:
 * LFU cache is a caching strategy that discards the least frequently accessed items first when the cache reaches
 * its capacity limit. If two items have the same frequency, the least recently used one is evicted first.
 * <p>
 * This implementation uses a combination of HashMap and custom DoubleLinkedList:
 * 1. A map to store each key and its corresponding Node (keyToNode)
 * 2. A map to group keys by their usage count using DoubleLinkedLists (usedCountToKeys)
 * 3. Each DoubleLinkedList maintains keys with the same frequency in order of recency
 * <p>
 * Advantages of this LFU Implementation:
 * 1. Constant time operations: Both get and put run in O(1) time
 * 2. Efficient handling of same-frequency ties: Uses LRU strategy within each frequency group
 * 3. Quick access to least frequently used items through minUsedCount tracking
 * 4. Clear separation of concerns between frequency tracking and recency ordering
 * <p>
 * Visual example of LFU cache operations with capacity = 3:
 * <p>
 * Initial state: Empty cache
 * keyToNode: {}
 * usedCountToKeys: {}
 * minUsedCount: 0
 * <p>
 * After put(1, 10):
 * keyToNode: {1: Node(1,10,1)}
 * usedCountToKeys: {1: [1]}
 * minUsedCount: 1
 * <p>
 * After put(2, 20):
 * keyToNode: {1: Node(1,10,1), 2: Node(2,20,1)}
 * usedCountToKeys: {1: [1,2]}
 * minUsedCount: 1
 * <p>
 * After get(1): Increases count for key 1
 * keyToNode: {1: Node(1,10,2), 2: Node(2,20,1)}
 * usedCountToKeys: {1: [2], 2: [1]}
 * minUsedCount: 1
 * <p>
 * After put(3, 30): Cache is now full
 * keyToNode: {1: Node(1,10,2), 2: Node(2,20,1), 3: Node(3,30,1)}
 * usedCountToKeys: {1: [2,3], 2: [1]}
 * minUsedCount: 1
 * <p>
 * After put(4, 40): Evict key 2 (lowest frequency, earliest inserted)
 * keyToNode: {1: Node(1,10,2), 3: Node(3,30,1), 4: Node(4,40,1)}
 * usedCountToKeys: {1: [3,4], 2: [1]}
 * minUsedCount: 1
 */

/**
 * LFU Cache implementation using a HashMap and DoubleLinkedList combination.
 * This class implements the Least Frequently Used cache eviction policy.
 */
public class LFUCacheDoubleLinkedListImp {
    // Maps each key to its corresponding Node for O(1) access
    private Map<Integer, Node> keyToNode;

    // Maps each frequency count to a DoubleLinkedList of nodes with that count
    // Each list maintains nodes in order of recency (LRU within each frequency)
    private Map<Integer, DoubleLinkedList> usedCountToKeys;

    // Maximum number of elements the cache can hold
    private int capacity;

    // Tracks the minimum frequency across all current cache entries
    // This allows O(1) access to the least frequently used node(s)
    private int minUsedCount;

    /**
     * Constructs a new LFU cache with the specified capacity.
     *
     * @param capacity the maximum number of key-value pairs the cache can hold
     *                 <p>
     *                 Time Complexity: O(1)
     */
    public LFUCacheDoubleLinkedListImp(int capacity) {
        keyToNode = new HashMap<>();
        usedCountToKeys = new HashMap<>();

        this.capacity = capacity;
        minUsedCount = 0;
    }

    /**
     * Retrieves the value associated with the given key from the cache.
     * Also increases the usage count for the key if found.
     * <p>
     * Process:
     * 1. Handle edge cases (capacity = 0 or key doesn't exist)
     * 2. Update the node's frequency count
     * 3. Remove the node from its current frequency list
     * 4. Add it to the next frequency list
     * 5. Update minUsedCount if necessary
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or -1 if not found
     * <p>
     * Time Complexity: O(1)
     */
    public int get(int key) {
        // Special case: if capacity is 0, cache is disabled
        if (capacity == 0) return -1;

        // Check if key exists in cache
        Node node = keyToNode.get(key);
        if (node == null) return -1;

        // Get current frequency count
        int usedCount = node.count;

        // 1. Remove node from its current frequency list
        usedCountToKeys.get(usedCount).remove(node);

        // Increment node's frequency
        node.count = usedCount + 1;

        // 2. Update minimum frequency if necessary
        // If we just removed the last node with the minimum frequency,
        // increment minUsedCount
        if (usedCount == minUsedCount
                && usedCountToKeys.get(usedCount).isEmpty()) {
            minUsedCount++;
        }

        // 3. Add node to the next frequency list
        putUsedCount(node, usedCount + 1);

        return node.val;
    }

    /**
     * Helper method to add a node to the appropriate frequency list.
     * Creates a new list if one doesn't exist for the given count.
     *
     * @param node  the node to be added
     * @param count the frequency count to add the node to
     *              <p>
     *              Time Complexity: O(1)
     */
    private void putUsedCount(Node node, int count) {
        // Create a new list for this frequency if it doesn't exist
        if (!usedCountToKeys.containsKey(count)) {
            usedCountToKeys.put(count, new DoubleLinkedList());
        }
        // Add the node to the end of the list (most recently used position)
        usedCountToKeys.get(count).add(node);
    }

    /**
     * Adds a key-value pair to the cache or updates the value if the key already exists.
     * If adding a new key would exceed capacity, the least frequently used item is evicted.
     * <p>
     * Process for existing key:
     * 1. Update the value
     * 2. Call get(key) to update its frequency
     * <p>
     * Process for new key:
     * 1. If at capacity, remove the LFU item
     * 2. Add the new key-value pair with frequency 1
     * 3. Update minimum frequency to 1
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     *              <p>
     *              Time Complexity: O(1)
     */
    public void put(int key, int value) {
        // Special case: if capacity is 0, cache is disabled
        if (capacity == 0) return;

        // If key already exists, update its value and frequency
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            // Update the node in the map
            keyToNode.put(key, node);
            // Update frequency (calls get method)
            get(key);
            return;
        }

        // If cache is full, evict the least frequently used item
        if (keyToNode.size() == capacity) {
            // Get the first (least recently used) node from the minimum frequency list
            Node removeNode = usedCountToKeys.get(minUsedCount).popFirst();
            // Remove it from the key-to-node map
            keyToNode.remove(removeNode.key);
        }

        // Create a new node for the key-value pair with frequency 1
        Node node = new Node(key, value, 1);
        keyToNode.put(key, node);

        // Set minimum frequency to 1 for the new node
        minUsedCount = 1;
        // Add the node to frequency 1 list
        putUsedCount(node, minUsedCount);
    }

    /**
     * Usage Example:
     * LFUCache2 obj = new LFUCache2(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     *
     * Detailed example with capacity = 2:
     *
     * LFUCache2 cache = new LFUCache2(2);
     *
     * cache.put(1, 1);  // Cache: {1=1}
     * cache.put(2, 2);  // Cache: {1=1, 2=2}
     * cache.get(1);     // Returns 1, increases frequency of 1
     * cache.put(3, 3);  // Removes key 2, Cache: {1=1, 3=3}
     * cache.get(2);     // Returns -1 (not found)
     * cache.get(3);     // Returns 3, increases frequency of 3
     * cache.put(4, 4);  // Removes key 1, Cache: {3=3, 4=4}
     * cache.get(1);     // Returns -1 (not found)
     * cache.get(3);     // Returns 3, increases frequency of 3
     * cache.get(4);     // Returns 4, increases frequency of 4
     */
}

/**
 * Node class for the doubly linked list.
 * Each node contains a key, value, usage count, and references to next and previous nodes.
 */
class Node {
    int key;     // Cache key
    int val;     // Cache value
    int count;   // Usage frequency count
    Node next;   // Reference to the next node
    Node prev;   // Reference to the previous node

    // Default constructor
    Node() {
    }

    /**
     * Constructs a new node with specified key, value, and initial count.
     *
     * @param key   the key for the cache entry
     * @param val   the value for the cache entry
     * @param count the initial frequency count (typically 1 for new entries)
     *              <p>
     *              Time Complexity: O(1)
     */
    Node(int key, int val, int count) {
        this.key = key;
        this.val = val;
        this.count = count;
    }
}

/**
 * Custom doubly linked list implementation for managing nodes with the same frequency.
 * This list maintains nodes in order of insertion, allowing for LRU behavior within each frequency group.
 */
class DoubleLinkedList {
    private Node head;  // Dummy head node
    private Node tail;  // Dummy tail node

    /**
     * Initializes an empty doubly linked list with dummy head and tail nodes.
     * This setup simplifies node insertion and removal by avoiding null checks.
     * <p>
     * Time Complexity: O(1)
     */
    DoubleLinkedList() {
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Removes a node from the list and returns it.
     *
     * @param node the node to be removed
     * @return the removed node
     * <p>
     * Time Complexity: O(1)
     */
    Node remove(Node node) {
        // Update links to remove the node from the list
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // Clean up node references
        node.prev = null;
        node.next = null;

        return node;
    }

    /**
     * Adds a node to the end of the list (just before the dummy tail).
     * This makes the newly added node the most recently used within its frequency group.
     *
     * @param node the node to be added
     *             <p>
     *             Time Complexity: O(1)
     */
    void add(Node node) {
        // Insert node just before the tail
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }

    /**
     * Removes and returns the first node in the list (just after the dummy head).
     * This is the least recently used node within this frequency group.
     *
     * @return the removed first node, or null if the list is empty
     * <p>
     * Time Complexity: O(1)
     */
    Node popFirst() {
        if (isEmpty()) return null;
        return remove(this.head.next);
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     * <p>
     * Time Complexity: O(1)
     */
    boolean isEmpty() {
        return this.head.next == this.tail;
    }
}