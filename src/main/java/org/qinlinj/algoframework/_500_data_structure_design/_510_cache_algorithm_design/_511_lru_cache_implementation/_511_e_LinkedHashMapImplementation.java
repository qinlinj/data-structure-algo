package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._511_lru_cache_implementation;

/**
 * LRU Cache Implementation Using Java's LinkedHashMap
 * <p>
 * Java provides a built-in LinkedHashMap class that can be used to easily implement an LRU cache.
 * LinkedHashMap is a HashMap that maintains insertion order or access order of its elements.
 * <p>
 * Key features of LinkedHashMap for LRU implementation:
 * 1. Can be configured to maintain access order (not just insertion order)
 * 2. Provides removeEldestEntry method that can be overridden to implement eviction policy
 * 3. Maintains O(1) time complexity for operations
 * <p>
 * Advantages of using LinkedHashMap:
 * - Significantly reduces code complexity
 * - Leverages well-tested JDK implementation
 * - Handles edge cases properly
 * <p>
 * This implementation requires:
 * - Set accessOrder=true in the constructor
 * - Override removeEldestEntry for automatic eviction
 */
public class _511_e_LinkedHashMapImplementation {

    public static void main(String[] args) {
        System.out.println("LRU Cache Using Java's LinkedHashMap");
        System.out.println("-----------------------------------");

        // Test the main implementation
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        System.out.println("After put(1, 1): " + cache);

        cache.put(2, 2);
        System.out.println("After put(2, 2): " + cache);

        int val1 = cache.get(1);
        System.out.println("get(1): " + val1);
        System.out.println("After get(1): " + cache + " (1 is now most recent)");

        cache.put(3, 3);
        System.out.println("After put(3, 3): " + cache + " (2 was evicted)");

        int val2 = cache.get(2);
        System.out.println("get(2): " + val2 + " (not found)");

        cache.put(4, 4);
        System.out.println("After put(4, 4): " + cache + " (1 was evicted)");

        // Demonstrate other implementations
        System.out.println("\nSimple LRU Cache Demo:");
        SimpleLRUCache simpleCache = new SimpleLRUCache(2);
        simpleCache.put(1, 10);
        simpleCache.put(2, 20);
        System.out.println(simpleCache);
        simpleCache.get(1);  // Makes 1 most recent
        simpleCache.put(3, 30);  // Should evict 2
        System.out.println(simpleCache);

        System.out.println("\nExtended LRU Cache Demo:");
        ExtendedLRUCache extendedCache = new ExtendedLRUCache(2);
        extendedCache.put(1, 100);
        extendedCache.put(2, 200);
        System.out.println(extendedCache);
        extendedCache.get(1);  // Makes 1 most recent
        extendedCache.put(3, 300);  // Should evict 2
        System.out.println(extendedCache);
    }

    // LRU Cache implementation using Java's LinkedHashMap
    static class LRUCache {
        private int capacity;
        private java.util.LinkedHashMap<Integer, Integer> cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;

            // Initialize LinkedHashMap with:
            // - Initial capacity
            // - Load factor (0.75 is default)
            // - Access order = true (false would be insertion order)
            this.cache = new java.util.LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
                    // Automatically remove eldest entry when size exceeds capacity
                    return size() > capacity;
                }
            };
        }

        // Get value and update access order
        public int get(int key) {
            // getOrDefault handles the case when key is not found
            return cache.getOrDefault(key, -1);
        }

        // Put value and update access order
        public void put(int key, int value) {
            cache.put(key, value);
            // No need to handle eviction - it's automatic thanks to removeEldestEntry
        }

        // Utility method to see cache state
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("LRUCache(capacity=" + capacity + "): [");
            boolean first = true;

            // Using entrySet to get both key and value
            for (java.util.Map.Entry<Integer, Integer> entry : cache.entrySet()) {
                if (!first) {
                    sb.append(" -> ");
                }
                sb.append("(").append(entry.getKey()).append(",").append(entry.getValue()).append(")");
                first = false;
            }

            sb.append("]");
            return sb.toString();
        }
    }

    // Simpler LRU Cache using direct approach (no anonymous inner class)
    static class SimpleLRUCache {
        private int capacity;
        private java.util.LinkedHashMap<Integer, Integer> cache;

        public SimpleLRUCache(int capacity) {
            this.capacity = capacity;
            // Initialize with access order = true
            this.cache = new java.util.LinkedHashMap<>(capacity, 0.75f, true);
        }

        public int get(int key) {
            return cache.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            cache.put(key, value);
            // Manual eviction check
            if (cache.size() > capacity) {
                // Remove the first entry (least recently used)
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
        }

        @Override
        public String toString() {
            return cache.toString();
        }
    }

    // Alternative approach: extending LinkedHashMap
    static class ExtendedLRUCache extends java.util.LinkedHashMap<Integer, Integer> {
        private final int capacity;

        public ExtendedLRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }
}