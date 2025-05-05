package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._511_lru_cache_implementation;

/**
 * Introduction to LRU (Least Recently Used) Cache Algorithm
 * <p>
 * LRU is a caching strategy used to manage memory when capacity is limited.
 * Core concept: Recent use of data is a good predictor of future use.
 * <p>
 * Key characteristics:
 * 1. When cache is full, it removes the least recently used items first
 * 2. Each time an item is accessed, it becomes the most recently used
 * 3. Used in many systems including OS page replacement, database buffer management, and web caching
 * <p>
 * Example scenario:
 * - Mobile phone with limited app slots in background (e.g., 3 slots)
 * - When opening new apps beyond capacity, the least recently used app is closed
 * - Each time an app is accessed, it moves to the "most recently used" position
 * <p>
 * This implementation follows LeetCode Problem 146, requiring:
 * - O(1) time complexity for both get and put operations
 * - Maintaining a capacity limit, evicting least recently used items as needed
 */
public class _511_a_LRUAlgorithmIntroduction {

    public static void main(String[] args) {
        // Example usage of LRU Cache
        System.out.println("LRU Cache Example:");
        System.out.println("------------------");

        // Create a cache with capacity 2
        LRUCache cache = new LRUCache(2);

        // Put some values
        cache.put(1, 1);
        System.out.println("After put(1, 1): " + cache);

        cache.put(2, 2);
        System.out.println("After put(2, 2): " + cache);

        // Get value 1 (makes it most recently used)
        int val1 = cache.get(1);
        System.out.println("get(1) returned: " + val1);
        System.out.println("After get(1): " + cache + " (1 is now most recent)");

        // Put a new value when capacity is full - should evict key 2
        cache.put(3, 3);
        System.out.println("After put(3, 3): " + cache + " (2 was evicted)");

        // Try to get evicted key
        int val2 = cache.get(2);
        System.out.println("get(2) returned: " + val2 + " (not found)");

        // Update existing key
        cache.put(1, 4);
        System.out.println("After put(1, 4): " + cache + " (updated value and made most recent)");
    }

    // Simple LRU Cache implementation for demo
    static class LRUCache {
        private int capacity;
        private java.util.LinkedHashMap<Integer, Integer> cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            // Using LinkedHashMap with access order (true) for demonstration
            this.cache = new java.util.LinkedHashMap<>(capacity, 0.75f, true);
        }

        public int get(int key) {
            return cache.getOrDefault(key, -1);
        }

        public void put(int key, int val) {
            cache.put(key, val);
            // If over capacity, remove eldest entry
            if (cache.size() > capacity) {
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
        }

        @Override
        public String toString() {
            return cache.toString();
        }
    }
}