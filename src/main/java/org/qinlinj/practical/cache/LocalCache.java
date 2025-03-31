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
    /**
     * The internal map that stores the cached key-value pairs.
     * HashMap is used for its O(1) average time complexity for get and put operations.
     */
    private Map<K, V> cache;

    /**
     * Constructs a new empty LocalCache with a default initial capacity.
     * <p>
     * Time Complexity: O(1)
     */
    public LocalCache() {
        cache = new HashMap<>();
    }

    /**
     * Retrieves the value associated with the specified key from the cache.
     * If the key is not found, null is returned.
     * <p>
     * Time Complexity: O(1) on average due to HashMap's implementation
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not present
     */
    @Override
    public V get(K key) {
        return cache.get(key);
    }

    /**
     * Associates the specified value with the specified key in the cache.
     * If the cache previously contained a value for the key, the old value is replaced.
     * <p>
     * This implementation has no size limit or eviction policy, so the cache will grow
     * unbounded as new entries are added.
     * <p>
     * Time Complexity: O(1) on average due to HashMap's implementation
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }
}
