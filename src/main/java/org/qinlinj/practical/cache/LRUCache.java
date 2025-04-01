package org.qinlinj.practical.cache;

import java.util.*;

/**
 * LRU (Least Recently Used) Cache Implementation
 * <p>
 * Concept and Principles:
 * LRU cache is a caching strategy that discards the least recently used items first when the cache reaches
 * its capacity limit and needs to make room for new elements. This implementation uses a doubly linked list
 * combined with a HashMap to achieve O(1) time complexity for both get and put operations.
 * <p>
 * Advantages of LRU Cache:
 * 1. Efficient use of memory: By discarding least recently used elements, the cache typically maintains
 * items that are more likely to be accessed again.
 * 2. Adaptive to access patterns: The cache automatically adjusts to changing access patterns by keeping
 * frequently or recently accessed items.
 * 3. Constant time operations: Both get and put operations run in O(1) time.
 * 4. Good general-purpose caching strategy: Works well for a wide range of applications and workloads.
 * <p>
 * Implementation Details:
 * - Uses a doubly linked list to maintain the order of elements based on their recency of access.
 * - The most recently accessed element is always at the head of the list.
 * - The least recently accessed element is always at the tail of the list.
 * - A HashMap provides O(1) key-value lookups while the linked list tracks access order.
 * <p>
 * Visual example of LRU cache operations with capacity = 3:
 * <p>
 * Initial state: Empty cache
 * HEAD <-> TAIL
 * <p>
 * After put(1, A):
 * HEAD <-> 1:A <-> TAIL
 * <p>
 * After put(2, B):
 * HEAD <-> 2:B <-> 1:A <-> TAIL
 * <p>
 * After put(3, C): Cache is now full
 * HEAD <-> 3:C <-> 2:B <-> 1:A <-> TAIL
 * <p>
 * After put(4, D): Evict LRU element (1:A)
 * HEAD <-> 4:D <-> 3:C <-> 2:B <-> TAIL
 * <p>
 * After get(3): Move 3:C to head as it's now most recently used
 * HEAD <-> 3:C <-> 4:D <-> 2:B <-> TAIL
 * <p>
 * After put(2, E): Update value and move to head
 * HEAD <-> 2:E <-> 3:C <-> 4:D <-> TAIL
 */
public class LRUCache<K, V> implements Cache<K, V> {
    // HashMap to store key-node pairs for O(1) access
    private Map<K, Node> cache;

    // Maximum number of elements the cache can hold
    private int capacity;

    // Dummy head and tail nodes for the doubly linked list
    // This simplifies node insertion and removal by avoiding null checks
    private Node head; // Most recently used end
    private Node tail; // Least recently used end

    /**
     * Constructs a new LRU cache with the specified capacity.
     * Initializes the doubly linked list with dummy head and tail nodes.
     *
     * @param capacity the maximum number of elements the cache can hold
     *                 <p>
     *                 Time Complexity: O(1)
     */
    public LRUCache(int capacity) {
        // Initialize dummy nodes
        head = new Node();
        tail = new Node();

        // Connect dummy nodes
        head.next = tail;
        tail.prev = head;

        // Initialize the cache map
        cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    /**
     * Main method demonstrating the usage of LRU cache.
     * <p>
     * Visual representation of the cache state during execution:
     * <p>
     * 1. Initially: HEAD <-> TAIL
     * 2. After put(1, 1): HEAD <-> 1:1 <-> TAIL
     * 3. After put(2, 2): HEAD <-> 2:2 <-> 1:1 <-> TAIL
     * 4. After put(3, 3): HEAD <-> 3:3 <-> 2:2 <-> 1:1 <-> TAIL (cache is full)
     * 5. After put(4, 4): HEAD <-> 4:4 <-> 3:3 <-> 2:2 <-> TAIL (1:1 is evicted as LRU)
     * 6. After get(3): HEAD <-> 3:3 <-> 4:4 <-> 2:2 <-> TAIL (3 moved to head as MRU)
     * 7. After put(2, 5): HEAD <-> 2:5 <-> 3:3 <-> 4:4 <-> TAIL (2 updated and moved to head)
     * 8. After put(5, 6): HEAD <-> 5:6 <-> 2:5 <-> 3:3 <-> TAIL (4:4 is evicted as LRU)
     * 9. After get(4): returns null (4 was evicted)
     * <p>
     * Expected output:
     * 3
     * null
     * <p>
     * Time Complexity: O(1) for each operation
     */
    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);  // Evicts key 1
        System.out.println(cache.get(3));  // Returns 3 and moves it to the front
        cache.put(2, 5);  // Updates value of key 2 and moves it to the front
        cache.put(5, 6);  // Evicts key 4
        System.out.println(cache.get(4));  // Returns null (not found)
    }

    /**
     * Retrieves the value associated with the given key from the cache.
     * If the key exists, moves the accessed node to the head (most recently used position).
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not in the cache
     * <p>
     * Time Complexity: O(1) - HashMap lookup and list node movement are constant time
     */
    @Override
    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) return null;

        // Move the accessed node to head (mark as most recently used)
        moveNodeToHead(node);
        return node.value;
    }

    /**
     * Moves a node to the head of the doubly linked list, marking it as the most recently used.
     * This is done by first removing the node from its current position and then adding it to the head.
     *
     * @param node the node to be moved to the head
     *             <p>
     *             Time Complexity: O(1) - Doubly linked list operations are constant time
     */
    private void moveNodeToHead(Node node) {
        // Remove the node from its current position
        removeNode(node);

        // Add the node right after the dummy head
        addNodeToHead(node);
    }

    /**
     * Adds a node right after the dummy head, making it the most recently used element.
     *
     * @param node the node to be added after the head
     *             <p>
     *             Time Complexity: O(1)
     */
    private void addNodeToHead(Node node) {
        // Connect node to the node after head
        node.next = head.next;
        head.next.prev = node;

        // Connect head to the node
        head.next = node;
        node.prev = head;
    }

    /**
     * Removes a node from the doubly linked list by updating the links of its neighbors.
     *
     * @param node the node to be removed
     *             <p>
     *             Time Complexity: O(1)
     */
    private void removeNode(Node node) {
        Node preNode = node.prev;
        Node nextNode = node.next;

        // Connect the previous node to the next node
        preNode.next = nextNode;
        nextNode.prev = preNode;

        // Disconnect the node from the list
        node.prev = null;
        node.next = null;
    }

    /**
     * Adds a key-value pair to the cache or updates the value if the key already exists.
     * If adding a new key would exceed the capacity, the least recently used key is evicted.
     * The newly added or updated node is moved to the head of the list (most recently used position).
     * <p>
     * Example with capacity = 3:
     * 1. Current cache: [A:1, B:2, C:3] with access order C->B->A (C most recent, A least recent)
     * 2. Call put(D, 4): A is evicted, cache becomes [D:4, C:3, B:2] with order D->C->B
     * 3. Call put(B, 5): Only value is updated, cache becomes [B:5, D:4, C:3] with order B->D->C
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     *              <p>
     *              Time Complexity: O(1) - HashMap operations and list operations are constant time
     */
    @Override
    public void put(K key, V value) {
        Node node = cache.get(key);
        if (node == null) {
            // Key doesn't exist in cache
            if (cache.size() == capacity) {
                // Cache is full, remove the least recently used node (from tail)
                Node delNode = removeTailNode();
                cache.remove(delNode.key);
            }

            // Create a new node
            node = new Node();
            node.key = key;
            node.value = value;

            // Add to the cache and to the front of the list
            cache.put(key, node);
            addNodeToHead(node);
        } else {
            // Key exists, update value and move to front (most recently used)
            node.value = value;
            moveNodeToHead(node);
        }
    }

    /**
     * Removes the node right before the dummy tail, which is the least recently used node.
     *
     * @return the removed node
     * <p>
     * Time Complexity: O(1)
     */
    private Node removeTailNode() {
        Node delNode = tail.prev;
        removeNode(delNode);
        return delNode;
    }

    /**
     * Node class for the doubly linked list used by the LRU cache.
     * Each node contains a key-value pair and references to the previous and next nodes.
     */
    private class Node {
        K key;       // Cache key
        V value;     // Cache value
        Node next;   // Reference to the next node (more recently used direction)
        Node prev;   // Reference to the previous node (less recently used direction)
    }
}