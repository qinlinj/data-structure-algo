package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._512_lfu_cache_implementation;

/**
 * LFU Cache Data Structures
 * <p>
 * This class explains the core data structures needed to implement an efficient
 * LFU (Least Frequently Used) cache algorithm with O(1) operations.
 * <p>
 * The LFU algorithm requires three key mappings:
 * <p>
 * 1. Key-Value Mapping (KV table):
 * - HashMap<Integer, Integer> keyToVal
 * - Maps cache keys to their values
 * - Enables O(1) lookup for the get() operation
 * <p>
 * 2. Key-Frequency Mapping (KF table):
 * - HashMap<Integer, Integer> keyToFreq
 * - Maps cache keys to their access frequency counts
 * - Enables tracking how often each key is accessed
 * <p>
 * 3. Frequency-Keys Mapping (FK table):
 * - HashMap<Integer, LinkedHashSet<Integer>> freqToKeys
 * - Maps frequencies to the set of keys with that frequency
 * - Uses LinkedHashSet to maintain insertion order within each frequency
 * - Enables finding the least frequently used key in O(1) time
 * <p>
 * Additional state variables:
 * - minFreq: Tracks the minimum frequency currently in the cache
 * - capacity: Maximum number of items the cache can hold
 */
public class _512_c_LFUDataStructures {

    // Demonstration of data structures for LFU cache
    public static void main(String[] args) {
        System.out.println("LFU Cache Core Data Structures");
        System.out.println("------------------------------");

        // Create an instance of our data structures demo
        LFUDataStructuresDemo demo = new LFUDataStructuresDemo(3); // capacity of 3

        // Demonstrate the initial state
        System.out.println("Initial state:");
        demo.printState();

        // Add some entries
        demo.put(1, 10);
        demo.put(2, 20);
        demo.put(3, 30);
        System.out.println("\nAfter adding three entries:");
        demo.printState();

        // Access key 1 twice to increase its frequency
        demo.access(1);
        demo.access(1);
        System.out.println("\nAfter accessing key 1 twice:");
        demo.printState();

        // Access key 2 once to increase its frequency
        demo.access(2);
        System.out.println("\nAfter accessing key 2 once:");
        demo.printState();

        // Add a new entry that causes eviction
        System.out.println("\nAdding key 4, should evict key 3 (lowest freq):");
        demo.put(4, 40);
        demo.printState();

        // Access keys to shift frequencies
        demo.access(4);
        demo.access(4);
        System.out.println("\nAfter accessing key 4 twice:");
        demo.printState();

        // Demonstrate eviction of oldest key with same frequency
        demo.put(1, 100); // update key 1
        demo.put(5, 50);  // add new key, should evict key 2 (same freq as 4 but older)
        System.out.println("\nAfter updating key 1 and adding key 5 (evicts key 2):");
        demo.printState();
    }

    // Demo class to illustrate LFU cache data structures
    static class LFUDataStructuresDemo {
        // Key to value mapping
        private java.util.HashMap<Integer, Integer> keyToVal;

        // Key to frequency mapping
        private java.util.HashMap<Integer, Integer> keyToFreq;

        // Frequency to set of keys with that frequency
        private java.util.HashMap<Integer, java.util.LinkedHashSet<Integer>> freqToKeys;

        // Current minimum frequency
        private int minFreq;

        // Maximum capacity
        private int capacity;

        public LFUDataStructuresDemo(int capacity) {
            this.capacity = capacity;
            this.keyToVal = new java.util.HashMap<>();
            this.keyToFreq = new java.util.HashMap<>();
            this.freqToKeys = new java.util.HashMap<>();
            this.minFreq = 0;
        }

        // Insert a key-value pair (simplified)
        public void put(int key, int value) {
            // If key exists, update it
            if (keyToVal.containsKey(key)) {
                keyToVal.put(key, value);
                access(key); // increase frequency
                return;
            }

            // If at capacity, evict least frequent
            if (keyToVal.size() >= capacity) {
                evictLeastFrequent();
            }

            // Add new key with frequency 1
            keyToVal.put(key, value);
            keyToFreq.put(key, 1);

            // Add to frequency 1 bucket
            freqToKeys.putIfAbsent(1, new java.util.LinkedHashSet<>());
            freqToKeys.get(1).add(key);

            // Update minimum frequency
            minFreq = 1;
        }

        // Simulate accessing a key (increasing its frequency)
        public void access(int key) {
            if (!keyToVal.containsKey(key)) return;

            int freq = keyToFreq.get(key);

            // Move to new frequency
            keyToFreq.put(key, freq + 1);

            // Remove from old frequency set
            freqToKeys.get(freq).remove(key);

            // Add to new frequency set
            freqToKeys.putIfAbsent(freq + 1, new java.util.LinkedHashSet<>());
            freqToKeys.get(freq + 1).add(key);

            // Clean up empty frequency and update minFreq if needed
            if (freqToKeys.get(freq).isEmpty()) {
                freqToKeys.remove(freq);
                if (freq == minFreq) {
                    minFreq++;
                }
            }
        }

        // Evict the least frequently used item
        private void evictLeastFrequent() {
            // Get the set of keys with minimum frequency
            java.util.LinkedHashSet<Integer> leastFreqKeys = freqToKeys.get(minFreq);

            // Get oldest key (first inserted) with this frequency
            int keyToRemove = leastFreqKeys.iterator().next();

            // Remove from data structures
            leastFreqKeys.remove(keyToRemove);
            if (leastFreqKeys.isEmpty()) {
                freqToKeys.remove(minFreq);
                // Note: minFreq will be reset to 1 when a new entry is added
            }

            keyToVal.remove(keyToRemove);
            keyToFreq.remove(keyToRemove);
        }

        // Print the current state of all data structures
        public void printState() {
            System.out.println("  KV Map: " + keyToVal);
            System.out.println("  KF Map: " + keyToFreq);

            System.out.println("  FK Map:");
            for (java.util.Map.Entry<Integer, java.util.LinkedHashSet<Integer>> entry : freqToKeys.entrySet()) {
                System.out.println("    " + entry.getKey() + " → " + entry.getValue());
            }

            System.out.println("  minFreq: " + minFreq);
        }
    }

    /**
     * Key points about LinkedHashSet in the FK table:
     *
     * 1. Set operations: Fast add, remove, and contains operations (O(1))
     * 2. Ordered iteration: Maintains insertion order of elements
     * 3. Oldest element: First element returned by iterator is the oldest
     *
     * Why not use:
     * - HashMap alone: Doesn't maintain order, can't determine oldest key
     * - ArrayList: O(n) for element removal, inefficient
     * - LinkedList: O(n) for finding elements, inefficient
     * - TreeSet: O(log n) operations, unnecessary overhead
     *
     * LinkedHashSet gives us the best of both worlds - the O(1) operations 
     * of a hash-based structure with the ordered iteration of a linked list.
     */
}