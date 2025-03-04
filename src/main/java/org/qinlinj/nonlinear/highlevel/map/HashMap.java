package org.qinlinj.nonlinear.highlevel.map;

import java.util.NoSuchElementException;

/**
 * A hash table implementation of the Map interface.
 * This class provides a map data structure using hash-based indexing with separate chaining
 * for collision resolution.
 *
 * @param <K> the type of keys stored in this map
 * @param <V> the type of values stored in this map
 */
public class HashMap<K, V> implements Map<K, V> {
    // Array of linked lists (chains) to store key-value pairs
    private Node<K, V>[] data;

    // Number of key-value pairs in the map
    private int size;

    // Threshold ratio to trigger resizing (capacity utilization)
    private double loadFactor;

    /**
     * Constructs a HashMap with specified initial capacity and load factor.
     *
     * @param initCapacity initial size of the underlying array
     * @param loadFactor   threshold ratio that triggers resizing (0.0 to 1.0)
     */
    public HashMap(int initCapacity, double loadFactor) {
        this.data = new Node[initCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    /**
     * Constructs a HashMap with specified initial capacity and default load factor (0.75).
     *
     * @param initCapacity initial size of the underlying array
     */
    public HashMap(int initCapacity) {
        this(initCapacity, 0.75);
    }

    /**
     * Constructs a HashMap with default capacity (10) and specified load factor.
     *
     * @param loadFactor threshold ratio that triggers resizing (0.0 to 1.0)
     */
    public HashMap(double loadFactor) {
        this(10, loadFactor);
    }

    /**
     * Constructs a HashMap with default capacity (10) and default load factor (0.75).
     */
    public HashMap() {
        this(10, 0.75);
    }

    /**
     * Generates a hash code within the bounds of the current array length.
     * Maps a key to an index in the underlying array.
     *
     * @param key    the key to hash
     * @param length the length of the array to fit the hash within
     * @return an index within the bounds of the array
     */
    private int hash(K key, int length) {
        return Math.abs(key.hashCode()) % length;
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
     * If adding the entry causes the load factor to be exceeded, the map is resized.
     *
     * @param key   the key to be added
     * @param value the value associated with the key
     */
    @Override
    public void add(K key, V value) {
        // Calculate the index for this key
        int index = hash(key, data.length);

        // Check if the key already exists
        Node<K, V> curr = getNode(key, index);

        if (curr == null) {
            // Key doesn't exist, add a new node at the beginning of the chain
            data[index] = new Node(key, value, data[index]);
            size++;

            // Check if resizing is needed
            if (size >= data.length * loadFactor) {
                resize(2 * data.length);
            }
        } else {
            // Key exists, update its value
            curr.value = value;
        }
    }

    /**
     * Helper method to find a node with the specified key in a chain.
     *
     * @param key   the key to search for
     * @param index the index in the array where the chain starts
     * @return the node containing the key, or null if not found
     */
    private Node<K, V> getNode(K key, int index) {
        Node<K, V> curr = data[index];

        // Traverse the chain to find the key
        while (curr != null) {
            if (curr.key.equals(key)) {
                break;
            }
            curr = curr.next;
        }

        return curr;
    }

    /**
     * Resize the hash table to accommodate more elements.
     * Rehashes all existing entries into the new array.
     *
     * @param newCapacity the new size for the underlying array
     */
    private void resize(int newCapacity) {
        // Create a new array with larger capacity
        Node[] newData = new Node[newCapacity];

        // Rehash all existing entries
        for (int i = 0; i < data.length; i++) {
            Node<K, V> curr = data[i];

            // Process each node in the current chain
            while (curr != null) {
                K key = curr.key;

                // Calculate new index based on new capacity
                int newIndex = hash(key, newCapacity);

                // Get the current head of the chain at the new index
                Node head = newData[newIndex];

                // Create a new node and insert it at the beginning of the chain
                newData[newIndex] = new Node(key, curr.value);
                if (head != null) {
                    newData[newIndex].next = head;
                }

                // Move to the next node in the original chain
                curr = curr.next;
            }
        }

        // Update reference to the new array
        data = newData;
    }

    /**
     * Remove the key-value pair associated with the specified key.
     *
     * @param key the key to be removed
     * @return the value previously associated with the key, or null if not found
     */
    @Override
    public V remove(K key) {
        // Calculate the index for this key
        int index = hash(key, data.length);

        // Check if the chain exists
        if (data[index] == null) {
            return null;
        }

        Node<K, V> prev = null;
        Node<K, V> curr = data[index];

        // Traverse the chain to find the key
        while (curr != null) {
            if (key.equals(curr.key)) {
                // Found the key, remove the node
                if (prev == null) {
                    // Node is at the head of the chain
                    data[index] = curr.next;
                } else {
                    // Node is in the middle or end of the chain
                    prev.next = curr.next;
                }

                // Clean up the removed node
                curr.next = null;
                size--;

                return curr.value;
            }

            // Move to the next node
            prev = curr;
            curr = curr.next;
        }

        // Key not found
        return null;
    }

    /**
     * Update the value associated with the specified key.
     *
     * @param key      the key whose value is to be updated
     * @param newValue the new value to be associated with the key
     * @throws NoSuchElementException if the key is not found in the map
     */
    @Override
    public void set(K key, V newValue) {
        // Calculate the index for this key
        int index = hash(key, data.length);

        // Find the node with this key
        Node<K, V> node = getNode(key, index);

        if (node == null) {
            // Key not found
            throw new NoSuchElementException("There is no corresponding key: " + key);
        }

        // Update the value
        node.value = newValue;
    }

    /**
     * Retrieve the value associated with the specified key.
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V get(K key) {
        // Calculate the index for this key
        int index = hash(key, data.length);

        // Find the node with this key
        Node<K, V> node = getNode(key, index);

        // Return the value or null if not found
        return node == null ? null : node.value;
    }

    /**
     * Check if the map contains the specified key.
     *
     * @param key the key to check for existence
     * @return true if the key exists in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        // Calculate the index for this key
        int index = hash(key, data.length);

        // Check if a node with this key exists
        Node<K, V> node = getNode(key, index);

        return node != null;
    }

    /**
     * Inner class representing a node in the hash map's linked lists.
     * Each node contains a key-value pair and a reference to the next node in the chain.
     */
    private class Node<K, V> {
        K key;           // The key stored in this node
        V value;         // The value associated with the key
        Node<K, V> next; // Reference to the next node in the chain

        /**
         * Constructs a new node with the specified key, value, and next node reference.
         *
         * @param key   the key to be stored
         * @param value the value associated with the key
         * @param next  reference to the next node in the chain
         */
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Constructs a new node with the specified key and value.
         * The next reference is initialized to null.
         *
         * @param key   the key to be stored
         * @param value the value associated with the key
         */
        public Node(K key, V value) {
            this(key, value, null);
        }

        /**
         * Constructs a new empty node.
         * All fields are initialized to null.
         */
        public Node() {
            this(null, null, null);
        }

        /**
         * Returns a string representation of this node.
         *
         * @return a string in the format "key->value"
         */
        @Override
        public String toString() {
            return key.toString() + "->" + value.toString();
        }
    }
}