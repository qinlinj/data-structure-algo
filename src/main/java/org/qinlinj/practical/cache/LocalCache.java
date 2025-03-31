package org.qinlinj.practical.cache;

import java.util.*;

/**
 * A simple in-memory cache implementation using a HashMap as the underlying storage.
 * <p>
 * This implementation provides a basic caching solution with the following characteristics:
 * - In-memory storage with no persistence
 * - No eviction policy (unbounded size)
 * - No expiration mechanism for cached entries
 * - Not thread-safe by default
 * - O(1) average time complexity for both get and put operations
 * <p>
 * LocalCache is suitable for simple use cases where:
 * - Memory constraints are not a concern
 * - The cache size is expected to remain manageable
 * - Thread-safety is handled externally or not required
 * - Entries do not need to expire automatically
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of values stored in this cache
 */
public class LocalCache<K, V> implements Cache<K, V> {
    private Map<K, V> cache;

    public LocalCache() {
        cache = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }
}
