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
    private Map<K, Node> cache;
    private int capacity;

    private Node head;
    private Node tail;

    /**
     * Constructs a new LRU cache with the specified capacity.
     * Initializes the doubly linked list with dummy head and tail nodes.
     *
     * @param capacity the maximum number of elements the cache can hold
     *                 <p>
     *                 Time Complexity: O(1)
     */
    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;

        cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3));
        cache.put(2, 5);
        cache.put(5, 6);
        System.out.println(cache.get(4));
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
        removeNode(node);

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
        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        Node preNode = node.prev;
        Node nextNode = node.next;

        preNode.next = nextNode;
        nextNode.prev = preNode;

        node.prev = null;
        node.next = null;
    }

    @Override
    public void put(K key, V value) {
        Node node = cache.get(key);
        if (node == null) {
            if (cache.size() == capacity) {
                Node delNode = removeTailNode();
                cache.remove(delNode.key);
            }
            node = new Node();
            node.key = key;
            node.value = value;

            cache.put(key, node);
            addNodeToHead(node);
        } else {
            node.value = value;
            moveNodeToHead(node);
        }
    }

    private Node removeTailNode() {
        Node delNode = tail.prev;
        removeNode(delNode);
        return delNode;
    }

    private class Node {
        K key;
        V value;
        Node next;
        Node prev;
    }
}
