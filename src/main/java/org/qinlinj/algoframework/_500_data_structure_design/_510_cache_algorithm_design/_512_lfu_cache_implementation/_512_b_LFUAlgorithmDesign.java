package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._512_lfu_cache_implementation;

/**
 * LFU Algorithm Design
 * <p>
 * To implement an LFU Cache with O(1) time complexity for both get and put operations,
 * we need data structures that satisfy these requirements:
 * <p>
 * 1. Fast key-value lookup to retrieve values by key
 * 2. Fast key-frequency tracking to update access counts
 * 3. Fast identification of the least frequently used item
 * 4. Fast determination of the oldest item among those with the same frequency
 * <p>
 * Solution: Three hash tables working together
 * - keyToVal: Maps keys to their values
 * - keyToFreq: Maps keys to their access frequency counts
 * - freqToKeys: Maps frequencies to the set of keys with that frequency
 * (LinkedHashSet to maintain insertion order)
 * <p>
 * Design decisions:
 * - Use a minFreq variable to track the minimum frequency at any time
 * - Use LinkedHashSet for the frequency-to-keys mapping to maintain time order
 * - When multiple items have the same minimum frequency, evict the oldest one
 * - When a key's frequency increases, it must move between frequency buckets
 */
public class _512_b_LFUAlgorithmDesign {

    // Main method to demonstrate the LFU design
    public static void main(String[] args) {
        System.out.println("LFU Algorithm Design Demonstration");
        System.out.println("----------------------------------");

        // Create a diagram of the data structure
        System.out.println("Data Structure Overview:");
        System.out.println("1. Key-Value Map (keyToVal):");
        System.out.println("   1 → 10");
        System.out.println("   2 → 20");
        System.out.println("   3 → 30");
        System.out.println();

        System.out.println("2. Key-Frequency Map (keyToFreq):");
        System.out.println("   1 → 3  // key 1 has been accessed 3 times");
        System.out.println("   2 → 1  // key 2 has been accessed 1 time");
        System.out.println("   3 → 2  // key 3 has been accessed 2 times");
        System.out.println();

        System.out.println("3. Frequency-Keys Map (freqToKeys):");
        System.out.println("   1 → [2]           // keys with frequency 1");
        System.out.println("   2 → [3]           // keys with frequency 2");
        System.out.println("   3 → [1]           // keys with frequency 3");
        System.out.println();

        System.out.println("minFreq = 1  // The minimum frequency currently in use");
        System.out.println();

        // Demonstrate operations
        System.out.println("When we access key 2:");
        System.out.println("1. Lookup value in keyToVal: value = 20");
        System.out.println("2. Lookup current frequency in keyToFreq: freq = 1");
        System.out.println("3. Remove key 2 from freqToKeys[1]");
        System.out.println("4. Add key 2 to freqToKeys[2]");
        System.out.println("5. Update keyToFreq[2] = 2");
        System.out.println("6. If freqToKeys[1] is now empty, update minFreq = 2");
        System.out.println();

        System.out.println("When cache is full and we add a new key 4:");
        System.out.println("1. Find minFreq = 1");
        System.out.println("2. Get the oldest key with minFreq (key 2)");
        System.out.println("3. Remove key 2 from all maps");
        System.out.println("4. Add new key 4 with freq = 1");
        System.out.println("5. Set minFreq = 1");
    }

    // Core data structures for LFU cache
    static class LFUCache {
        // Key to value mapping
        private java.util.HashMap<Integer, Integer> keyToVal;

        // Key to frequency mapping
        private java.util.HashMap<Integer, Integer> keyToFreq;

        // Frequency to set of keys with that frequency (in order of insertion)
        private java.util.HashMap<Integer, java.util.LinkedHashSet<Integer>> freqToKeys;

        // The minimum frequency at any time
        private int minFreq;

        // The maximum capacity of the cache
        private int capacity;

        /**
         * Why we use LinkedHashSet:
         * 1. It's a Set, so we can quickly check if a key exists and remove it - O(1)
         * 2. It maintains insertion order, so we can get the oldest key - O(1)
         * 3. Combined with the freqToKeys map, we can quickly find all keys with a given frequency
         *
         * These properties enable us to efficiently implement all LFU operations in O(1) time.
         */
    }

    /**
     * Implementation workflow:
     *
     * get(key) operation:
     * 1. If key doesn't exist, return -1
     * 2. Otherwise, increment key's frequency
     * 3. Return key's value
     *
     * put(key, value) operation:
     * 1. If key exists:
     *    - Update its value
     *    - Increment its frequency
     * 2. If key doesn't exist:
     *    - If cache is at capacity, remove least frequent key
     *    - Add new key with frequency 1
     *    - Set minFreq to 1
     *
     * increaseFreq(key) operation:
     * 1. Get current frequency of key
     * 2. Increase frequency by 1
     * 3. Move key from old frequency bucket to new frequency bucket
     * 4. If old frequency bucket is now empty and equals minFreq, increment minFreq
     *
     * removeLeastFrequent() operation:
     * 1. Get keys with minFreq
     * 2. Remove the oldest key from this set
     * 3. If the set is now empty, remove the frequency entry
     * 4. Remove key from keyToVal and keyToFreq maps
     */
}