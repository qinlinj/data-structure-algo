package org.qinlinj.algoframework._200_other_algo_framework._230_algo_complexity_analysis._232_data_structure_analysis;

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
     * Main method with demonstration and explanation
     */
    public static void main(String[] args) {
        System.out.println("Data Structure Analysis and Amortized Time Complexity");
        System.out.println("=====================================================\n");

        // Demonstrate dynamic array
        System.out.println("1. Dynamic Array Example:");
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        System.out.println("Adding 20 elements to a dynamic array with initial capacity 10");
        for (int i = 0; i < 20; i++) {
            dynamicArray.add(i);
        }
        System.out.println("- Although resizing occurred (O(n) operation), the amortized cost per add is O(1)");
        System.out.println("- This is because the expensive resizing operation happens infrequently\n");

        // Demonstrate monotonic queue
        System.out.println("2. Monotonic Queue Example:");
        MonotonicQueue monotonicQueue = new MonotonicQueue();
        System.out.println("Push sequence: 3, 1, 5, 2, 4");
        monotonicQueue.push(3);
        monotonicQueue.push(1);
        monotonicQueue.push(5);
        monotonicQueue.push(2);
        monotonicQueue.push(4);

        System.out.println("- When pushing 5, elements 3 and 1 were removed (multiple removals)");
        System.out.println("- When pushing 4, element 2 was removed");
        System.out.println("- Despite these O(n) operations, the amortized cost remains O(1)");
        System.out.println("- This is because each element can only be removed once across all operations\n");

        // Amortized analysis explanation
        System.out.println("Amortized Analysis Methods:");
        System.out.println("1. Aggregate Analysis:");
        System.out.println("   - Consider the total cost of n operations and divide by n");
        System.out.println("   - For dynamic array with n add operations: Total cost is O(n), so amortized cost is O(1)");

        System.out.println("\n2. Accounting Method:");
        System.out.println("   - Charge each operation more than its actual cost");
        System.out.println("   - Use the excess as 'credit' to pay for expensive operations");
        System.out.println("   - Example: Charge O(2) for each add, save O(1) as credit for future resizing");

        System.out.println("\n3. Potential Method:");
        System.out.println("   - Define a potential function mapping data structure state to a number");
        System.out.println("   - Amortized cost = actual cost + change in potential");
        System.out.println("   - Design potential function so expensive operations cause large decrease in potential");
    }

    /**
     * SECTION 2: HASH TABLE EXAMPLE
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
     * SECTION 3: MONOTONIC QUEUE EXAMPLE
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

    /**
     * Monotonic Queue implementation with amortized O(1) operations
     * despite having loops in push method
     */
    public static class MonotonicQueue {
        private java.util.LinkedList<Integer> q = new java.util.LinkedList<>();

        /**
         * Push an element into the queue, maintaining monotonic property
         * <p>
         * Time Complexity:
         * - Best case: O(1) when no elements are removed
         * - Worst case: O(n) when all elements are removed
         * - Amortized: O(1) across a sequence of operations
         * <p>
         * Explanation of amortized complexity:
         * Each element can only be removed from the queue once.
         * Over N operations, at most N elements can be removed,
         * so the total cost is O(N), making the amortized cost O(1) per operation.
         */
        public void push(int e) {
            // Remove all elements smaller than e from the back
            while (!q.isEmpty() && q.getLast() < e) {
                q.pollLast();
            }
            q.addLast(e);
        }

        /**
         * Remove an element from the front of the queue if it matches
         * <p>
         * Time Complexity: O(1)
         */
        public void pop(int e) {
            // Only remove if the element is at the front
            if (!q.isEmpty() && q.getFirst() == e) {
                q.pollFirst();
            }
        }

        /**
         * Get the maximum value in the queue (front element)
         * <p>
         * Time Complexity: O(1)
         */
        public int max() {
            if (q.isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            return q.getFirst();
        }
    }
}