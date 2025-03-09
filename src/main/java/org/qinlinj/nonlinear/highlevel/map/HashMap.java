package org.qinlinj.nonlinear.highlevel.map;

import java.util.NoSuchElementException;

// @formatter:off
/**
 * HashMap Implementation
 * ======================
 *
 * CONCEPT AND PRINCIPLES:
 * A HashMap is a data structure that implements the Map interface, providing key-value pair storage
 * with efficient lookup, insertion, and deletion operations. This implementation uses hash-based indexing
 * with separate chaining for collision resolution.
 *
 * VISUALIZATION OF A HASHMAP:
 *
 * Array indices:   [0]        [1]        [2]        [3]        [4]
 *                   |          |          |          |          |
 *                   v          v          v          v          v
 *                  null       null      (K3,V3)     null      (K1,V1)
 *                                         |                     |
 *                                         v                     v
 *                                      (K8,V8)               (K6,V6)
 *                                         |                     |
 *                                         v                     v
 *                                       null                  null
 *
 * In this visualization:
 * - Each array index represents a "bucket"
 * - When multiple keys hash to the same index (like K3 and K8), they form a linked list ("chain")
 * - Empty buckets contain null
 *
 * ADVANTAGES:
 * 1. Constant-time (O(1)) average case complexity for basic operations
 * 2. Dynamic size adjustment through resizing
 * 3. Efficient key-based lookups without requiring sorting
 * 4. Flexible storage of any key type that implements hashCode() and equals()
 * 5. Collision handling through separate chaining preserves all data
 *
 * KEY OPERATIONS:
 * - Adding a key-value pair
 * - Retrieving a value by key
 * - Removing a key-value pair
 * - Checking if a key exists
 *
 * HOW IT WORKS:
 * 1. Keys are mapped to array indices using a hash function
 * 2. Collisions (when different keys map to the same index) are handled by chaining
 * 3. When the load factor is exceeded, the array is resized to maintain performance
 *
 * @param <K> the type of keys stored in this map
 * @param <V> the type of values stored in this map
 */
public class HashMap<K, V> implements Map<K, V> {
    /**
     * Array of linked lists (chains) to store key-value pairs.
     * Each index in this array represents a "bucket" that can contain multiple entries
     * linked together in a chain.
     */
    private Node<K, V>[] data;

    /**
     * Number of key-value pairs in the map.
     * This tracks the total entries across all chains.
     */
    private int size;

    /**
     * Threshold ratio to trigger resizing (capacity utilization).
     * When (size / array length) exceeds this value, the hash table is resized
     * to maintain performance.
     */
    private double loadFactor;

    /**
     * Constructs a HashMap with specified initial capacity and load factor.
     *
     * Time Complexity: O(1)
     *
     * EXAMPLE:
     * Creating a HashMap with capacity 4 and load factor 0.75:
     *
     * data = [null, null, null, null]
     * size = 0
     * loadFactor = 0.75
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
     * Time Complexity: O(1)
     *
     * @param initCapacity initial size of the underlying array
     */
    public HashMap(int initCapacity) {
        this(initCapacity, 0.75);
    }

    /**
     * Constructs a HashMap with default capacity (10) and specified load factor.
     *
     * Time Complexity: O(1)
     *
     * @param loadFactor threshold ratio that triggers resizing (0.0 to 1.0)
     */
    public HashMap(double loadFactor) {
        this(10, loadFactor);
    }

    /**
     * Constructs a HashMap with default capacity (10) and default load factor (0.75).
     *
     * Time Complexity: O(1)
     */
    public HashMap() {
        this(10, 0.75);
    }

    /**
     * Generates a hash code within the bounds of the current array length.
     * Maps a key to an index in the underlying array.
     *
     * Time Complexity: O(1)
     *
     * EXAMPLE:
     * If key.hashCode() = 12345 and length = 10:
     * index = 12345 % 10 = 5
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
     * Time Complexity: O(1)
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
     * Time Complexity: O(1)
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
     * Time Complexity:
     * - Average case: O(1)
     * - Worst case (many collisions): O(n)
     * - Worst case with resize: O(n)
     *
     * EXAMPLE:
     * Adding (K="apple", V=10) to this HashMap:
     *
     * Before:
     * [0]: null
     * [1]: (K="banana",V=5) -> null
     * [2]: null
     * [3]: null
     *
     * Step 1: hash("apple") % 4 = 0
     * Step 2: No collision at index 0, add new node
     *
     * After:
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="banana",V=5) -> null
     * [2]: null
     * [3]: null
     *
     * EXAMPLE 2 (with collision):
     * Adding (K="cherry", V=20) that hashes to index 1:
     *
     * Before:
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="banana",V=5) -> null
     * [2]: null
     * [3]: null
     *
     * Step 1: hash("cherry") % 4 = 1
     * Step 2: Collision at index 1, add to chain
     *
     * After:
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="cherry",V=20) -> (K="banana",V=5) -> null
     * [2]: null
     * [3]: null
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
     * Time Complexity:
     * - Average case: O(1)
     * - Worst case (with many collisions): O(n)
     *
     * EXAMPLE:
     * Finding key "cherry" in this chain:
     *
     * [1]: (K="cherry",V=20) -> (K="banana",V=5) -> null
     *
     * Step 1: Check first node, key="cherry" matches, return this node
     *
     * EXAMPLE 2:
     * Finding key "orange" in this chain:
     *
     * [1]: (K="cherry",V=20) -> (K="banana",V=5) -> null
     *
     * Step 1: Check first node, no match
     * Step 2: Check second node, no match
     * Step 3: Reached end of chain, return null
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
     * Time Complexity: O(n) where n is the number of entries
     *
     * EXAMPLE:
     * Resizing from capacity 4 to 8 with these entries:
     *
     * Before:
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="cherry",V=20) -> (K="banana",V=5) -> null
     * [2]: null
     * [3]: (K="date",V=15) -> null
     *
     * Step 1: Create new array of size 8
     * Step 2: Rehash each key
     *   - "apple" now hashes to index 2
     *   - "cherry" now hashes to index 3
     *   - "banana" now hashes to index 1
     *   - "date" now hashes to index 5
     *
     * After:
     * [0]: null
     * [1]: (K="banana",V=5) -> null
     * [2]: (K="apple",V=10) -> null
     * [3]: (K="cherry",V=20) -> null
     * [4]: null
     * [5]: (K="date",V=15) -> null
     * [6]: null
     * [7]: null
     *
     * Notice how the collisions between "cherry" and "banana" are resolved
     * after resizing.
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
     * Time Complexity:
     * - Average case: O(1)
     * - Worst case (with many collisions): O(n)
     *
     * EXAMPLE:
     * Removing key "cherry" from this HashMap:
     *
     * Before:
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="cherry",V=20) -> (K="banana",V=5) -> null
     * [2]: null
     * [3]: (K="date",V=15) -> null
     *
     * Step 1: hash("cherry") % 4 = 1
     * Step 2: Look for "cherry" in chain at index 1
     * Step 3: Found "cherry" at head of chain
     * Step 4: Set data[1] to the next node (banana)
     *
     * After:
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="banana",V=5) -> null
     * [2]: null
     * [3]: (K="date",V=15) -> null
     *
     * EXAMPLE 2 (removing from middle of chain):
     * Removing key "grape" from:
     *
     * Before:
     * [2]: (K="fig",V=8) -> (K="grape",V=30) -> (K="kiwi",V=12) -> null
     *
     * Step 1: hash("grape") % 4 = 2
     * Step 2: Look for "grape" in chain at index 2
     * Step 3: Found "grape" in middle of chain
     * Step 4: Link prev node ("fig") to next node ("kiwi")
     *
     * After:
     * [2]: (K="fig",V=8) -> (K="kiwi",V=12) -> null
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
     * Time Complexity:
     * - Average case: O(1)
     * - Worst case (with many collisions): O(n)
     *
     * EXAMPLE:
     * Updating key "apple" from value 10 to 25:
     *
     * Before:
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="banana",V=5) -> null
     *
     * Step 1: hash("apple") % 4 = 0
     * Step 2: Find node with key "apple"
     * Step 3: Update value from 10 to 25
     *
     * After:
     * [0]: (K="apple",V=25) -> null
     * [1]: (K="banana",V=5) -> null
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
     * Time Complexity:
     * - Average case: O(1)
     * - Worst case (with many collisions): O(n)
     *
     * EXAMPLE:
     * Getting value for key "banana" from:
     *
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="cherry",V=20) -> (K="banana",V=5) -> null
     *
     * Step 1: hash("banana") % 4 = 1
     * Step 2: Check first node at index 1, key="cherry" (no match)
     * Step 3: Check second node, key="banana" (match!)
     * Step 4: Return value 5
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
     * Time Complexity:
     * - Average case: O(1)
     * - Worst case (with many collisions): O(n)
     *
     * EXAMPLE:
     * Checking if key "date" exists in:
     *
     * [0]: (K="apple",V=10) -> null
     * [1]: (K="banana",V=5) -> null
     * [2]: null
     * [3]: (K="date",V=15) -> null
     *
     * Step 1: hash("date") % 4 = 3
     * Step 2: Check node at index 3, key="date" (match!)
     * Step 3: Return true
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
     * Returns a string representation of the HashMap.
     * Displays the map contents in the format {key1=value1, key2=value2, ...}
     * and also shows the internal structure with buckets and chains.
     *
     * Time Complexity: O(n) where n is the number of key-value pairs
     *
     * EXAMPLE:
     * For a HashMap containing:
     * [0]: (K="apple",V=25) -> null
     * [1]: (K="elderberry",V=8) -> null
     * [2]: null
     * [3]: (K="cherry",V=20) -> null
     * [4]: (K="date",V=15) -> null
     *
     * The output would be:
     * HashMap{size=4, capacity=5, loadFactor=0.75}
     * {apple=25, elderberry=8, cherry=20, date=15}
     * Internal Structure:
     * [0]: apple->25
     * [1]: elderberry->8
     * [2]:
     * [3]: cherry->20
     * [4]: date->15
     *
     * @return a string representation of the map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HashMap{size=").append(size)
                .append(", capacity=").append(data.length)
                .append(", loadFactor=").append(loadFactor)
                .append("}\n");

        // Append contents in standard map format {k1=v1, k2=v2, ...}
        sb.append("{");
        boolean first = true;
        for (int i = 0; i < data.length; i++) {
            Node<K, V> curr = data[i];
            while (curr != null) {
                if (!first) {
                    sb.append(", ");
                } else {
                    first = false;
                }
                sb.append(curr.key).append("=").append(curr.value);
                curr = curr.next;
            }
        }
        sb.append("}\n");

        // Append internal structure visualization
        sb.append("Internal Structure:\n");
        for (int i = 0; i < data.length; i++) {
            sb.append("[").append(i).append("]: ");
            Node<K, V> curr = data[i];
            if (curr == null) {
                sb.append("\n");
                continue;
            }

            while (curr != null) {
                sb.append(curr);
                if (curr.next != null) {
                    sb.append(" -> ");
                }
                curr = curr.next;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Inner class representing a node in the hash map's linked lists.
     * Each node contains a key-value pair and a reference to the next node in the chain.
     *
     * VISUALIZATION:
     * A single node looks like:
     *
     * +----------------+
     * | key   | "apple"|
     * | value | 10     |
     * | next  | ------>| (points to next node or null)
     * +----------------+
     */
    private class Node<K, V> {
        /**
         * The key stored in this node.
         * Used for lookup and hash calculation.
         */
        K key;

        /**
         * The value associated with the key.
         * This is the data being stored for retrieval.
         */
        V value;

        /**
         * Reference to the next node in the chain.
         * Used to handle collisions through separate chaining.
         */
        Node<K, V> next;

        /**
         * Constructs a new node with the specified key, value, and next node reference.
         *
         * Time Complexity: O(1)
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
         * Time Complexity: O(1)
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
         *
         * Time Complexity: O(1)
         */
        public Node() {
            this(null, null, null);
        }

        /**
         * Returns a string representation of this node.
         *
         * Time Complexity: O(1)
         *
         * @return a string in the format "key->value"
         */
        @Override
        public String toString() {
            return key.toString() + "->" + value.toString();
        }
    }
}