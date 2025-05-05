package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._512_lfu_cache_implementation;

/**
 * Introduction to LFU (Least Frequently Used) Cache Algorithm
 * <p>
 * The LFU algorithm is a cache eviction strategy that removes items based on how
 * frequently they are accessed. Unlike LRU (Least Recently Used) which prioritizes
 * recency of access, LFU prioritizes frequency of access.
 * <p>
 * Key characteristics:
 * 1. When cache is full, it removes the least frequently used items first
 * 2. If multiple items have the same access frequency, it removes the oldest one
 * 3. Each time an item is accessed, its frequency counter increases
 * <p>
 * Comparison with LRU:
 * - LRU: Evicts based on recency (when was it last used?)
 * - LFU: Evicts based on frequency (how often was it used?)
 * <p>
 * LFU is generally more complex to implement than LRU because:
 * - It requires tracking both frequency counts and insertion order within each frequency
 * - It needs efficient data structures to maintain frequency ordering
 * <p>
 * This implementation follows similar requirements to LRU:
 * - O(1) time complexity for both get and put operations
 * - Maintaining a capacity limit, evicting items based on usage frequency
 */
public class _512_a_LFUAlgorithmIntroduction {

    public static void main(String[] args) {
        // Example usage of LFU Cache
        System.out.println("LFU Cache Example:");
        System.out.println("------------------");

        // Create a cache with capacity 2
        LFUCache cache = new LFUCache(2);

        // Put some values
        cache.put(1, 10);
        System.out.println("After put(1, 10): " + cache);

        cache.put(2, 20);
        System.out.println("After put(2, 20): " + cache);

        // Get value 1 (increases its frequency to 2)
        int val1 = cache.get(1);
        System.out.println("get(1) returned: " + val1);
        System.out.println("After get(1): " + cache + " (frequency of key 1 is now 2)");

        // Put a new value when capacity is full
        // Will evict key 2 because it has lower frequency (1 < 2)
        cache.put(3, 30);
        System.out.println("After put(3, 30): " + cache + " (key 2 was evicted due to lowest frequency)");

        // Try to get evicted key
        int val2 = cache.get(2);
        System.out.println("get(2) returned: " + val2 + " (not found)");

        // Put another new value
        // Both key 1 and 3 have different frequencies, so key 3 (freq=1) will be evicted
        cache.put(4, 40);
        System.out.println("After put(4, 40): " + cache + " (key 3 was evicted due to lowest frequency)");
    }

    // Simple LFU Cache implementation for demo
    static class LFUCache {
        private int capacity;
        private java.util.HashMap<Integer, Integer> keyToVal; // key -> value
        private java.util.HashMap<Integer, Integer> keyToFreq; // key -> frequency
        private java.util.HashMap<Integer, java.util.LinkedHashSet<Integer>> freqToKeys; // frequency -> set of keys
        private int minFreq; // tracks the minimum frequency

        public LFUCache(int capacity) {
            this.capacity = capacity;
            keyToVal = new java.util.HashMap<>();
            keyToFreq = new java.util.HashMap<>();
            freqToKeys = new java.util.HashMap<>();
            minFreq = 0;
        }

        public int get(int key) {
            if (!keyToVal.containsKey(key)) {
                return -1;
            }

            // Increase frequency of this key
            increaseFreq(key);
            return keyToVal.get(key);
        }

        public void put(int key, int val) {
            if (capacity <= 0) return;

            // If key exists, update value and increase frequency
            if (keyToVal.containsKey(key)) {
                keyToVal.put(key, val);
                increaseFreq(key);
                return;
            }

            // If at capacity, remove least frequent item
            if (keyToVal.size() >= capacity) {
                removeLeastFrequent();
            }

            // Add new key with frequency 1
            keyToVal.put(key, val);
            keyToFreq.put(key, 1);
            freqToKeys.putIfAbsent(1, new java.util.LinkedHashSet<>());
            freqToKeys.get(1).add(key);
            minFreq = 1;  // New key is always min frequency
        }

        private void increaseFreq(int key) {
            int freq = keyToFreq.get(key);
            keyToFreq.put(key, freq + 1);

            // Remove from old frequency set
            freqToKeys.get(freq).remove(key);

            // Add to new frequency set
            freqToKeys.putIfAbsent(freq + 1, new java.util.LinkedHashSet<>());
            freqToKeys.get(freq + 1).add(key);

            // Clean up empty frequency set and update minFreq if needed
            if (freqToKeys.get(freq).isEmpty()) {
                freqToKeys.remove(freq);
                if (freq == minFreq) {
                    minFreq++;
                }
            }
        }

        private void removeLeastFrequent() {
            // Get the set of least frequently used keys
            java.util.LinkedHashSet<Integer> leastFreqKeys = freqToKeys.get(minFreq);

            // Get the oldest key (first inserted) with this frequency
            int keyToRemove = leastFreqKeys.iterator().next();

            // Remove from all maps
            leastFreqKeys.remove(keyToRemove);
            if (leastFreqKeys.isEmpty()) {
                freqToKeys.remove(minFreq);
                // Note: no need to update minFreq here as it will be set to 1
                // when a new key is added immediately after this
            }

            keyToVal.remove(keyToRemove);
            keyToFreq.remove(keyToRemove);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("LFUCache{");
            sb.append("values=").append(keyToVal);
            sb.append(", frequencies=").append(keyToFreq);
            sb.append(", minFreq=").append(minFreq);
            sb.append("}");
            return sb.toString();
        }
    }
}