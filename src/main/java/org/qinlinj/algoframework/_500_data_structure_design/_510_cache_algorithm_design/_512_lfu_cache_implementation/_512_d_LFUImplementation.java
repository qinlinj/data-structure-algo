package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._512_lfu_cache_implementation;

/**
 * Complete LFU (Least Frequently Used) Cache Implementation
 * <p>
 * This class implements a complete LFU cache with O(1) operations for all methods.
 * The implementation follows a modular approach with helper methods to handle
 * the different aspects of the LFU algorithm.
 * <p>
 * Core Operations:
 * - get(key): Retrieve a value and increase its access frequency
 * - put(key, val): Insert or update a value, managing eviction if needed
 * <p>
 * Helper Operations:
 * - increaseFreq(key): Move a key to the next frequency level
 * - removeMinFreqKey(): Evict the least frequently used key
 * <p>
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(capacity) for storing the cache contents
 * <p>
 * This implementation carefully handles all edge cases:
 * - Empty cache
 * - Updating existing keys
 * - Evicting when at capacity
 * - Maintaining correct minFreq value
 * - Handling keys with same frequency (time-ordered eviction)
 */
public class _512_d_LFUImplementation {

    /**
     * Main method to demonstrate the LFU Cache implementation
     */
    public static void main(String[] args) {
        System.out.println("LFU Cache Implementation Demonstration");
        System.out.println("-------------------------------------");

        // Create a cache with capacity 2
        LFUCache cache = new LFUCache(2);

        // Test operations
        System.out.println("Initial cache: ");
        System.out.println(cache);

        // Put operations
        cache.put(1, 10);
        System.out.println("After put(1, 10):");
        System.out.println(cache);

        cache.put(2, 20);
        System.out.println("After put(2, 20):");
        System.out.println(cache);

        // Get operation (increases frequency of key 1)
        int val1 = cache.get(1);
        System.out.println("get(1) returned: " + val1);
        System.out.println("After get(1):");
        System.out.println(cache);

        // Put operation causing eviction of least frequent key (key 2)
        cache.put(3, 30);
        System.out.println("After put(3, 30): (should evict key 2)");
        System.out.println(cache);

        // Try to get evicted key
        int val2 = cache.get(2);
        System.out.println("get(2) returned: " + val2 + " (expected: -1, key was evicted)");

        // Update an existing key
        cache.put(1, 100);
        System.out.println("After put(1, 100): (update existing key)");
        System.out.println(cache);

        // Access key to increase frequency
        cache.get(3);
        System.out.println("After get(3): (frequency of key 3 increased)");
        System.out.println(cache);

        // Add another key, causing eviction
        cache.put(4, 40);
        System.out.println("After put(4, 40): (should evict key with lowest freq)");
        System.out.println(cache);

        // Complex sequence of operations
        System.out.println("\nComplex sequence test:");
        LFUCache cache2 = new LFUCache(3);

        cache2.put(1, 10);
        cache2.put(2, 20);
        cache2.put(3, 30);
        System.out.println("Initial state with 3 keys:");
        System.out.println(cache2);

        // Increase frequencies differently
        cache2.get(1);  // freq 1->2
        cache2.get(2);  // freq 1->2
        cache2.get(1);  // freq 2->3
        System.out.println("After accessing keys (freq: key1=3, key2=2, key3=1):");
        System.out.println(cache2);

        // Add new key, should evict key3 (lowest freq)
        cache2.put(4, 40);
        System.out.println("After put(4, 40): (should evict key3)");
        System.out.println(cache2);

        // Now all keys have different frequencies
        // Access key4 twice to make its freq = 3 (same as key1)
        cache2.get(4);  // freq 1->2
        cache2.get(4);  // freq 2->3
        System.out.println("After making key4 have freq=3 (same as key1):");
        System.out.println(cache2);

        // Add new key, should evict either key1 or key4 (same freq, depends on insertion order)
        cache2.put(5, 50);
        System.out.println("After put(5, 50): (should evict oldest key with freq=3)");
        System.out.println(cache2);

        System.out.println("\nAll test cases complete!");
    }

    // The complete LFU Cache implementation
    static class LFUCache {
        // Key to value mapping
        private java.util.HashMap<Integer, Integer> keyToVal;

        // Key to frequency mapping
        private java.util.HashMap<Integer, Integer> keyToFreq;

        // Frequency to keys mapping (with insertion order)
        private java.util.HashMap<Integer, java.util.LinkedHashSet<Integer>> freqToKeys;

        // Current minimum frequency
        private int minFreq;

        // Maximum capacity
        private int capacity;

        /**
         * Initialize the LFU Cache with the given capacity
         */
        public LFUCache(int capacity) {
            this.keyToVal = new java.util.HashMap<>();
            this.keyToFreq = new java.util.HashMap<>();
            this.freqToKeys = new java.util.HashMap<>();
            this.minFreq = 0;
            this.capacity = capacity;
        }

        /**
         * Get the value associated with the key if it exists
         * Increases the frequency of the key if found
         *
         * @param key The key to look up
         * @return The value associated with the key, or -1 if not found
         */
        public int get(int key) {
            // Check if key exists
            if (!keyToVal.containsKey(key)) {
                return -1;
            }

            // Increase the frequency of this key
            increaseFreq(key);

            // Return the value
            return keyToVal.get(key);
        }

        /**
         * Insert or update a key-value pair in the cache
         * Evicts least frequently used item if capacity is reached
         *
         * @param key The key to insert or update
         * @param val The value to associate with the key
         */
        public void put(int key, int val) {
            // Handle edge case of zero capacity
            if (capacity <= 0) {
                return;
            }

            // If key exists, update its value and frequency
            if (keyToVal.containsKey(key)) {
                keyToVal.put(key, val);
                increaseFreq(key);
                return;
            }

            // If at capacity, remove least frequently used key
            if (keyToVal.size() >= capacity) {
                removeMinFreqKey();
            }

            // Insert new key-value pair with frequency 1
            keyToVal.put(key, val);
            keyToFreq.put(key, 1);

            // Add to frequency 1 bucket
            freqToKeys.putIfAbsent(1, new java.util.LinkedHashSet<>());
            freqToKeys.get(1).add(key);

            // Update minimum frequency
            minFreq = 1;
        }

        /**
         * Increase the frequency of a key and update all relevant data structures
         *
         * @param key The key whose frequency should be increased
         */
        private void increaseFreq(int key) {
            // Get current frequency
            int freq = keyToFreq.get(key);

            // Update frequency in key-frequency map
            keyToFreq.put(key, freq + 1);

            // Remove from old frequency bucket
            freqToKeys.get(freq).remove(key);

            // Add to new frequency bucket
            freqToKeys.putIfAbsent(freq + 1, new java.util.LinkedHashSet<>());
            freqToKeys.get(freq + 1).add(key);

            // Clean up empty frequency bucket
            if (freqToKeys.get(freq).isEmpty()) {
                freqToKeys.remove(freq);

                // Update minFreq if needed
                if (freq == minFreq) {
                    minFreq++;
                }
            }
        }

        /**
         * Remove the least frequently used key from the cache
         * If multiple keys have the same frequency, remove the oldest one
         */
        private void removeMinFreqKey() {
            // Get the set of keys with minimum frequency
            java.util.LinkedHashSet<Integer> keyList = freqToKeys.get(minFreq);

            // Get the oldest key (first in insertion order)
            int keyToRemove = keyList.iterator().next();

            // Remove from frequency-keys map
            keyList.remove(keyToRemove);

            // Clean up empty frequency if needed
            if (keyList.isEmpty()) {
                freqToKeys.remove(minFreq);
                // Note: minFreq will be reset to 1 when new key is added
            }

            // Remove from key-value map
            keyToVal.remove(keyToRemove);

            // Remove from key-frequency map
            keyToFreq.remove(keyToRemove);
        }

        /**
         * Get a string representation of the cache
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LFUCache(cap=").append(capacity).append("):\n");
            sb.append("  Values: ").append(keyToVal).append("\n");
            sb.append("  Frequencies: ").append(keyToFreq).append("\n");
            sb.append("  Min Frequency: ").append(minFreq).append("\n");

            sb.append("  Frequency Buckets:\n");
            for (java.util.Map.Entry<Integer, java.util.LinkedHashSet<Integer>> entry : freqToKeys.entrySet()) {
                sb.append("    ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }

            return sb.toString();
        }
    }
}