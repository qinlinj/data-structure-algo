package org.qinlinj.practical.cache;

/**
 * Generic interface for cache implementations.
 * <p>
 * This interface defines the core functionality required for any cache implementation.
 * It specifies methods for storing and retrieving values associated with keys.
 * <p>
 * Cache implementations based on this interface might include additional features such as:
 * - Size limits and eviction policies (LRU, LFU, FIFO, etc.)
 * - Time-based expiration
 * - Statistics tracking (hit rate, miss rate)
 * - Thread safety considerations
 * - Persistence options
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of values stored in this cache
 */
public interface Cache<K, V> {

    V get(K key);

    /**
     * Associates the specified value with the specified key in the cache.
     * <p>
     * If the cache previously contained a value for the key, the old value
     * is replaced. This operation may trigger eviction of other entries
     * according to the cache's eviction policy if the cache is at capacity.
     * <p>
     * This operation is typically expected to be fast (O(1) in most implementations)
     * and may have cache-specific side effects such as:
     * - Triggering eviction of other entries
     * - Updating statistics
     * - Persisting values to a backing store
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    void put(K key, V value);
}
