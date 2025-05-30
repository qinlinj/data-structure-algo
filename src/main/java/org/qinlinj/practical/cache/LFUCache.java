package org.qinlinj.practical.cache;

import java.util.*;

/**
 * LFU (Least Frequently Used) Cache Implementation
 * <p>
 * Concept and Principles:
 * LFU cache is a caching strategy that discards the least frequently accessed items first when the cache reaches
 * its capacity limit. If two items have the same frequency, the least recently used one is evicted first.
 * <p>
 * This implementation uses three hash maps:
 * 1. A map to store the actual key-value pairs (cache)
 * 2. A map to track usage count of each key (keyToUsedCount)
 * 3. A map to group keys by their usage counts (usedCountToKeys)
 * <p>
 * Advantages of LFU Cache:
 * 1. Good for workloads with stable popularity patterns: Keeps frequently accessed items in cache.
 * 2. Handles frequency bias effectively: Items that are genuinely popular stay in cache longer.
 * 3. Efficient with temporal locality: If certain items are consistently accessed, they remain cached.
 * 4. Constant time operations: Both get and put operations run in O(1) time.
 * <p>
 * Limitations:
 * 1. Cache pollution: Once an item becomes "popular," it stays in cache for a long time even if no longer needed.
 * 2. Cold start problem: New entries start with low frequency and are quickly evicted if capacity is reached.
 * 3. More complex implementation compared to LRU or FIFO.
 * <p>
 * Visual example of LFU cache operations with capacity = 3:
 * <p>
 * Initial state: Empty cache
 * cache: {}
 * keyToUsedCount: {}
 * usedCountToKeys: {}
 * minUsedCount: 0
 * <p>
 * After put(1, A):
 * cache: {1:A}
 * keyToUsedCount: {1:1}
 * usedCountToKeys: {1:[1]}
 * minUsedCount: 1
 * <p>
 * After put(2, B):
 * cache: {1:A, 2:B}
 * keyToUsedCount: {1:1, 2:1}
 * usedCountToKeys: {1:[1,2]}
 * minUsedCount: 1
 * <p>
 * After put(3, C): Cache is now full
 * cache: {1:A, 2:B, 3:C}
 * keyToUsedCount: {1:1, 2:1, 3:1}
 * usedCountToKeys: {1:[1,2,3]}
 * minUsedCount: 1
 * <p>
 * After get(1): Increase count for key 1
 * cache: {1:A, 2:B, 3:C}
 * keyToUsedCount: {1:2, 2:1, 3:1}
 * usedCountToKeys: {1:[2,3], 2:[1]}
 * minUsedCount: 1
 * <p>
 * After put(4, D): Evict key 2 (least frequency, first inserted among frequency 1)
 * cache: {1:A, 3:C, 4:D}
 * keyToUsedCount: {1:2, 3:1, 4:1}
 * usedCountToKeys: {1:[3,4], 2:[1]}
 * minUsedCount: 1
 */
public class LFUCache<K, V> implements Cache<K, V> {
    // Main cache that stores key-value pairs
    private Map<K, V> cache;

    // Map to track the frequency (usage count) of each key
    private Map<K, Integer> keyToUsedCount;

    // Map to group keys by their usage counts, using LinkedHashSet to maintain order within each frequency
    // This allows O(1) access to the least recently used key within each frequency group
    private Map<Integer, LinkedHashSet<K>> usedCountToKeys;

    // Maximum capacity of the cache
    private int capacity;

    // Keeps track of the minimum frequency across all items
    // This allows O(1) access to the least frequently used key(s)
    private int minUsedCount;

    /**
     * Constructs a new LFU cache with the specified capacity.
     * Initializes the data structures needed to track item frequencies.
     * <p>
     * Note: There's a bug in the constructor - capacity parameter is missing but used.
     * It should be: public LFUCache(int capacity) { ... }
     * <p>
     * Time Complexity: O(1)
     */
    public LFUCache() {
        cache = new HashMap<>();
        keyToUsedCount = new HashMap<>();
        usedCountToKeys = new HashMap<>();

        this.capacity = capacity;  // Bug: capacity parameter is missing
        minUsedCount = 0;
    }

    /**
     * Retrieves the value associated with the given key from the cache.
     * Accessing an item increases its usage count, potentially changing its
     * position in the eviction order.
     * <p>
     * Process:
     * 1. If key doesn't exist, return null
     * 2. Get current usage count for the key
     * 3. Remove key from its current frequency group
     * 4. Increment usage count
     * 5. Add key to the new frequency group
     * 6. Update minimum frequency if needed
     * 7. Return the value
     * <p>
     * Visual example:
     * Before get(1):
     * usedCountToKeys: {1:[1,2,3]}
     * keyToUsedCount: {1:1, 2:1, 3:1}
     * minUsedCount: 1
     * <p>
     * After get(1):
     * usedCountToKeys: {1:[2,3], 2:[1]}
     * keyToUsedCount: {1:2, 2:1, 3:1}
     * minUsedCount: 1
     * <p>
     * Time Complexity: O(1)
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not in the cache
     */
    @Override
    public V get(K key) {
        V value = cache.get(key);
        if (value == null) return null;

        // Get current usage count and remove key from its current frequency set
        int usedCount = keyToUsedCount.get(key);
        usedCountToKeys.get(usedCount).remove(key);

        // Increment the usage count in the key-to-count map
        keyToUsedCount.put(key, usedCount + 1);

        // Update minimum frequency if needed
        // This happens when we removed the last key with the minimum frequency
        if (usedCount == minUsedCount
                && usedCountToKeys.get(usedCount).size() == 0) {
            minUsedCount++;
        }

        // Add the key to its new frequency group
        putUsedCount(key, usedCount + 1);

        return value;
    }

    /**
     * Helper method to add a key to the appropriate frequency group.
     * Creates a new frequency group if it doesn't exist yet.
     *
     * @param key   the key to be added
     * @param count the frequency count to add the key to
     *              <p>
     *              Time Complexity: O(1)
     */
    private void putUsedCount(K key, int count) {
        // Create a new frequency group if it doesn't exist
        if (!usedCountToKeys.containsKey(count)) {
            usedCountToKeys.put(count, new LinkedHashSet<>());
        }
        // Add the key to the appropriate frequency group
        usedCountToKeys.get(count).add(key);
    }

    /**
     * Adds a key-value pair to the cache or updates the value if the key already exists.
     * If adding a new key would exceed the capacity, the least frequently used key is evicted.
     * If multiple keys have the same minimum frequency, the least recently used among them is evicted.
     * <p>
     * Process for existing key:
     * 1. Update the value
     * 2. Call get(key) to update its frequency
     * <p>
     * Process for new key:
     * 1. If at capacity, remove the LFU item
     * 2. Add the new key-value pair with frequency 1
     * 3. Update minimum frequency to 1
     * <p>
     * Visual example for adding new key when at capacity:
     * Before put(4, D):
     * cache: {1:A, 2:B, 3:C}
     * usedCountToKeys: {1:[2,3], 2:[1]}
     * keyToUsedCount: {1:2, 2:1, 3:1}
     * minUsedCount: 1
     * <p>
     * After put(4, D): Evicts key 2 (first in the LFU group)
     * cache: {1:A, 3:C, 4:D}
     * usedCountToKeys: {1:[3,4], 2:[1]}
     * keyToUsedCount: {1:2, 3:1, 4:1}
     * minUsedCount: 1
     * <p>
     * Time Complexity: O(1)
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void put(K key, V value) {
        // If the key already exists, update the value and frequency
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);  // This updates frequency
            return;
        }

        // If cache is at capacity, remove the least frequently used item
        if (cache.size() == capacity) {
            // Get the first key from the set with minimum frequency
            // LinkedHashSet maintains insertion order, so the first element is the least recently used
            K removeKey = usedCountToKeys.get(minUsedCount).iterator().next();

            // Remove the key from all data structures
            usedCountToKeys.get(minUsedCount).remove(removeKey);
            cache.remove(removeKey);
            keyToUsedCount.remove(removeKey);
        }

        // Add the new key-value pair to the cache
        cache.put(key, value);
        keyToUsedCount.put(key, 1);  // Initial frequency is 1

        // New keys always have minimum frequency of 1
        minUsedCount = 1;
        putUsedCount(key, minUsedCount);
    }
}