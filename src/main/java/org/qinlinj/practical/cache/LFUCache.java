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
    private Map<K, V> cache;

    private Map<K, Integer> keyToUsedCount;

    private Map<Integer, LinkedHashSet<K>> usedCountToKeys;

    private int capacity;

    private int minUsedCount;

    public LFUCache() {
        cache = new HashMap<>();
        keyToUsedCount = new HashMap<>();
        usedCountToKeys = new HashMap<>();

        this.capacity = capacity;
        minUsedCount = 0;
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        if (value == null) return null;

        int usedCount = keyToUsedCount.get(key);
        usedCountToKeys.get(usedCount).remove(key);
        keyToUsedCount.put(key, usedCount + 1);

        if (usedCount == minUsedCount
                && usedCountToKeys.get(usedCount).size() == 0) {
            minUsedCount++;
        }

        putUsedCount(key, usedCount + 1);

        return value;
    }

    private void putUsedCount(K key, int count) {
        if (!usedCountToKeys.containsKey(count)) {
            usedCountToKeys.put(count, new LinkedHashSet<>());
        }
        usedCountToKeys.get(count).add(key);
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }

        if (cache.size() == capacity) {
            K removeKey = usedCountToKeys.get(minUsedCount).iterator().next();
            usedCountToKeys.get(minUsedCount).remove(removeKey);
            cache.remove(removeKey);
            keyToUsedCount.remove(removeKey);
        }

        cache.put(key, value);
        keyToUsedCount.put(key, 1);

        minUsedCount = 1;
        putUsedCount(key, minUsedCount);
    }
}
