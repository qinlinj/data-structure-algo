package org.qinlinj.nonlinear.highlevel.map;

import java.util.NoSuchElementException;

/**
 * A Map implementation using a Linked List data structure.
 * <p>
 * Key Characteristics:
 * - Uses a dummy head node for simplified list manipulation
 * - Linear time complexity O(n) for most operations
 * - Allows unique key-value pairs
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    // Dummy head node to simplify list operations
    private Node dummyHead;

    // Number of key-value pairs in the map
    private int size;

    /**
     * Constructs an empty LinkedListMap.
     * Initializes a dummy head node and sets size to 0.
     */
    public LinkedListMap() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    /**
     * Get the number of key-value pairs stored in the map.
     *
     * @return the size of the map (number of entries)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if the map is empty.
     *
     * @return true if the map contains no key-value pairs, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add a new key-value pair to the map.
     * If the key already exists, it will be updated with the new value.
     * <p>
     * Time complexity: O(n) - requires traversing the list to check for existing key
     *
     * @param key   the key to be added
     * @param value the value associated with the key
     */
    @Override
    public void add(K key, V value) { // O(n)
        Node curr = getNode(key);
        if (curr == null) {
            // If key doesn't exist, add new node at the beginning of the list
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            // If key exists, update its value
            curr.value = value;
        }
    }

    /**
     * Find a node with the given key in the map.
     * <p>
     * Time complexity: O(n) - linear search through the list
     *
     * @param key the key to search for
     * @return the node containing the key, or null if not found
     */
    private Node getNode(K key) { // O(n)
        Node curr = dummyHead.next;
        while (curr != null) {
            if (key.equals(curr.key)) {
                break;
            }
            curr = curr.next;
        }
        return curr;
    }

    /**
     * Remove the key-value pair associated with the specified key.
     * <p>
     * Time complexity: O(n) - requires traversing the list to find the key
     *
     * @param key the key to be removed
     * @return the value previously associated with the key, or null if not found
     */
    @Override
    public V remove(K key) { // O(n)
        Node prev = dummyHead;
        Node curr = dummyHead.next;

        // Traverse the list to find the node with the key
        while (curr != null) {
            if (curr.key.equals(key)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        // Remove the node if found
        if (curr != null) {
            prev.next = curr.next;
            curr.next = null;
            size--;
            return curr.value;
        }
        return null;
    }

    /**
     * Update the value associated with the specified key.
     * <p>
     * Time complexity: O(n) - requires finding the key in the list
     *
     * @param key      the key whose value is to be updated
     * @param newValue the new value to be associated with the key
     * @throws NoSuchElementException if the key is not found in the map
     */
    @Override
    public void set(K key, V newValue) { // O(n)
        Node curr = getNode(key);

        if (curr != null) curr.value = newValue;
        else throw new NoSuchElementException("no such corresponding key" + key);
    }

    /**
     * Retrieve the value associated with the specified key.
     * <p>
     * Time complexity: O(n) - requires searching the list
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V get(K key) { // O(n)
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * Check if the map contains the specified key.
     * <p>
     * Time complexity: O(n) - requires searching the list
     *
     * @param key the key to check for existence
     * @return true if the key exists in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) { // O(n)
        Node node = getNode(key);
        return node != null;
    }

    /**
     * Internal node class for the linked list implementation.
     * Stores a key-value pair and a reference to the next node.
     */
    private class Node {
        // Key of the node
        K key;
        // Value associated with the key
        V value;
        // Reference to the next node
        Node next;

        /**
         * Full constructor for a node.
         *
         * @param key   the key for this node
         * @param value the value associated with the key
         * @param next  reference to the next node
         */
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Constructor for a node without a next reference.
         *
         * @param key   the key for this node
         * @param value the value associated with the key
         */
        public Node(K key, V value) {
            this(key, value, null);
        }

        /**
         * Default constructor creates an empty node.
         */
        public Node() {
            this(null, null, null);
        }

        /**
         * String representation of the node.
         *
         * @return a string in the format "key->value"
         */
        @Override
        public String toString() {
            return key.toString() + "->" + value.toString();
        }
    }
}