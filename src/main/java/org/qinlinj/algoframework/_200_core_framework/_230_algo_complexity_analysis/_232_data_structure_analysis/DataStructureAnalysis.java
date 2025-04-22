package org.qinlinj.algoframework._200_core_framework._230_algo_complexity_analysis._232_data_structure_analysis;

/**
 * Data Structure Analysis and Amortized Time Complexity
 * <p>
 * This class explains the concept of amortized time complexity analysis for data structures,
 * which differs from analyzing standard algorithms because data structures change dynamically.
 * <p>
 * Key Points:
 * <p>
 * 1. Dynamic Data Structures:
 * - Data structures like dynamic arrays and hash tables can resize
 * - Operations that trigger resizing have higher time complexity (O(n))
 * - Yet we still describe these operations as O(1) in common usage
 * <p>
 * 2. Amortized Analysis:
 * - We use amortized (average) time complexity instead of worst-case
 * - Amortized analysis distributes the cost of expensive operations across all operations
 * - Three common methods for amortized analysis:
 * a) Aggregate analysis (total cost divided by number of operations)
 * b) Accounting method (saving "credits" for future expensive operations)
 * c) Potential method (using a potential function to track state changes)
 * <p>
 * 3. Examples of Amortized Analysis:
 * - Dynamic array resizing
 * - Hash table rehashing
 * - Monotonic queue operations
 */
public class DataStructureAnalysis {
    /**
     * SECTION 1: DYNAMIC ARRAY EXAMPLE
     */

    /**
     * Dynamic Array implementation demonstrating amortized time complexity
     * for add operations that occasionally trigger resizing
     */
    public static class DynamicArray<T> {
        private static final int DEFAULT_CAPACITY = 10;
        private Object[] elements;
        private int size;

        public DynamicArray() {
            elements = new Object[DEFAULT_CAPACITY];
            size = 0;
        }

        /**
         * Add an element to the end of the array
         * <p>
         * Time Complexity:
         * - Best case: O(1) when no resizing is needed
         * - Worst case: O(n) when array needs to be resized
         * - Amortized: O(1) across many operations
         */
        public void add(T element) {
            // Check if we need to resize the array
            if (size == elements.length) {
                resize();
            }

            // Add the element and increment size
            elements[size] = element;
            size++;
        }

        /**
         * Resize the internal array by doubling its capacity
         * This operation costs O(n) time, but happens infrequently
         */
        private void resize() {
            // Create a new array with double the capacity
            Object[] newElements = new Object[elements.length * 2];

            // Copy all elements to the new array
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }

            // Replace the old array with the new one
            elements = newElements;
        }

        /**
         * Get an element at a specific index
         * <p>
         * Time Complexity: O(1) always
         */
        @SuppressWarnings("unchecked")
        public T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            return (T) elements[index];
        }

        /**
         * Remove the last element from the array
         * <p>
         * Time Complexity:
         * - Best case: O(1) when no resizing is needed
         * - Worst case: O(n) when array needs to be shrunk
         * - Amortized: O(1) across many operations
         */
        public T removeLast() {
            if (size == 0) {
                throw new IllegalStateException("Cannot remove from empty array");
            }

            @SuppressWarnings("unchecked")
            T element = (T) elements[size - 1];
            elements[size - 1] = null; // Help garbage collection
            size--;

            // Optional: shrink the array if it's getting too empty
            // Typically done when array is 1/4 full to avoid "thrashing"
            if (size > 0 && size == elements.length / 4) {
                shrink();
            }

            return element;
        }

        /**
         * Shrink the internal array by halving its capacity
         * This operation costs O(n) time, but happens infrequently
         */
        private void shrink() {
            // Create a new array with half the capacity
            Object[] newElements = new Object[elements.length / 2];

            // Copy all elements to the new array
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }

            // Replace the old array with the new one
            elements = newElements;
        }
    }

    /**
     * SECTION 2: HASH TABLE EXAMPLE
     */

    /**
     * Simplified HashTable implementation demonstrating amortized time complexity
     * for put operations that occasionally trigger rehashing
     */
    public static class SimpleHashTable<K, V> {
        private static final int DEFAULT_CAPACITY = 16;
        private static final double LOAD_FACTOR_THRESHOLD = 0.75;

        private Entry<K, V>[] buckets;
        private int size;

        @SuppressWarnings("unchecked")
        public SimpleHashTable() {
            buckets = new Entry[DEFAULT_CAPACITY];
            size = 0;
        }

        /**
         * Insert a key-value pair into the hash table
         * <p>
         * Time Complexity:
         * - Best case: O(1) when no collision and no rehashing
         * - Worst case: O(n) when rehashing is needed
         * - Amortized: O(1) across many operations
         */
        public void put(K key, V value) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            // Calculate load factor and rehash if necessary
            if ((double) size / buckets.length >= LOAD_FACTOR_THRESHOLD) {
                rehash();
            }

            int index = getIndex(key);

            // Handle collision with linked list
            Entry<K, V> entry = buckets[index];

            // If key already exists, update value
            while (entry != null) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }

            // Add new entry at the beginning of the chain
            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.next = buckets[index];
            buckets[index] = newEntry;
            size++;
        }

        /**
         * Get a value by key
         * <p>
         * Time Complexity:
         * - Best case: O(1) when no collision
         * - Worst case: O(n) with many collisions
         * - Average: O(1) with good hash function
         */
        public V get(K key) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            int index = getIndex(key);
            Entry<K, V> entry = buckets[index];

            while (entry != null) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
                entry = entry.next;
            }

            return null;
        }

        /**
         * Calculate the bucket index for a key
         */
        private int getIndex(K key) {
            // Ensure non-negative index
            return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
        }

        /**
         * Rehash the table by creating a new array with double capacity
         * and reinserting all entries
         * <p>
         * Time Complexity: O(n) where n is the number of entries
         */
        @SuppressWarnings("unchecked")
        private void rehash() {
            Entry<K, V>[] oldBuckets = buckets;
            buckets = new Entry[oldBuckets.length * 2];
            size = 0;

            // Reinsert all entries
            for (Entry<K, V> entry : oldBuckets) {
                while (entry != null) {
                    put(entry.key, entry.value);
                    entry = entry.next;
                }
            }
        }

        /**
         * Entry class for hash table (key-value pair with next pointer)
         */
        private static class Entry<K, V> {
            K key;
            V value;
            Entry<K, V> next;

            Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
