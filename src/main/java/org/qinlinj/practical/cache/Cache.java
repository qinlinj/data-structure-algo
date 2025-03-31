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
    
    void put(K key, V value);
}
