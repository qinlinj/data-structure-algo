package org.qinlinj.practical.cache;

import java.util.*;

/**
 * FIFO (First-In-First-Out) Cache Implementation
 *
 * Concept and Principles:
 * FIFO cache is a simple caching strategy where the oldest inserted elements are evicted first
 * when the cache reaches its capacity and a new element needs to be added.
 *
 * Advantages of FIFO Cache:
 * 1. Simplicity: The implementation is straightforward and easy to understand.
 * 2. Predictable behavior: The oldest elements are always evicted first.
 * 3. Low overhead: Maintaining the queue of keys has minimal performance impact.
 * 4. No need to track access patterns: Unlike LRU, FIFO doesn't need to update data structures on cache reads.
 *
 * Limitations:
 * 1. Does not consider frequency or recency of access when making eviction decisions.
 * 2. May evict frequently used items if they were among the first inserted.
 *
 * Visual example of FIFO cache operations with capacity = 3:
 *
 * Initial state: Empty cache
 * []
 *
 * After put(1, A):
 * [1:A]
 *
 * After put(2, B):
 * [1:A, 2:B]
 *
 * After put(3, C): Cache is now full
 * [1:A, 2:B, 3:C]
 *
 * After put(4, D): Evict oldest element (1)
 * [2:B, 3:C, 4:D]
 *
 * After get(3): No change in order, just return C
 * [2:B, 3:C, 4:D]
 *
 * After put(5, E): Evict oldest element (2)
 * [3:C, 4:D, 5:E]
 */
package org.qinlinj.practical.cache;

import java.util.*;

public class FIFOCache<K, V> implements Cache<K, V> {
    // HashMap to store key-value pairs for O(1) access
    private Map<K, V> cache;

    // Queue to maintain the order of insertion (FIFO)
    private Queue<K> queue;

    // Maximum number of elements the cache can hold
    private int capacity;

    /**
     * Constructs a new FIFO cache with the specified capacity.
     *
     * @param capacity the maximum number of elements the cache can hold
     *
     * Time Complexity: O(1)
     */
    public FIFOCache(int capacity) {
        cache = new HashMap<>(capacity);
        queue = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    /**
     * Main method demonstrating the usage of FIFO cache.
     *
     * Visual representation of the cache state during execution:
     *
     * 1. Initially: []
     * 2. After put(1, 1): [1:1]
     * 3. After put(2, 2): [1:1, 2:2]
     * 4. After put(3, 3): [1:1, 2:2, 3:3] (cache is full)
     * 5. After put(4, 4): [2:2, 3:3, 4:4] (1 is evicted as the oldest element)
     * 6. After get(3): returns 3, cache unchanged: [2:2, 3:3, 4:4]
     * 7. After put(4, 5): [2:2, 3:3, 4:5] (updating existing key doesn't affect order)
     * 8. After put(5, 6): [3:3, 4:5, 5:6] (2 is evicted as the oldest element)
     * 9. After get(2): returns null (2 was evicted)
     *
     * Expected output:
     * 3
     * 5
     * null
     *
     * Time Complexity: O(1) for each operation
     */
    public static void main(String[] args) {
        FIFOCache<Integer, Integer> cache = new FIFOCache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3));  // Output: 3
        cache.put(4, 5);                   // Update value for key 4
        System.out.println(cache.get(4));  // Output: 5
        cache.put(5, 6);                   // Evict key 2
        System.out.println(cache.get(2));  // Output: null (key 2 was evicted)
    }

    /**
     * Retrieves the value associated with the given key from the cache.
     * Unlike LRU cache, accessing elements does not affect their eviction order.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not in the cache
     *
     * Time Complexity: O(1) - HashMap lookup is constant time
     */
    @Override
    public V get(K key) {
        return cache.get(key);
    }

    /**
     * Adds a key-value pair to the cache or updates the value if the key already exists.
     * If adding a new key would exceed the capacity, the oldest key (first inserted) is evicted.
     *
     * Example with capacity = 3:
     * 1. Current cache: [A:1, B:2]
     * 2. Call put(C, 3): Cache becomes [A:1, B:2, C:3]
     * 3. Call put(D, 4): A is evicted, cache becomes [B:2, C:3, D:4]
     * 4. Call put(B, 5): Only value is updated, cache becomes [B:5, C:3, D:4]
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     *
     * Time Complexity: O(1) - HashMap operations and queue operations are constant time
     */
    @Override
    public void put(K key, V value) {
        V oldValue = cache.get(key);
        if (oldValue == null) {
            // Key doesn't exist in cache, need to check capacity
            if (cache.size() == capacity) {
                // Cache is full, remove the oldest entry (FIFO principle)
                K oldKey = queue.poll();
                cache.remove(oldKey);
            }
            // Add the new key to the queue
            queue.offer(key);
        }
        // Update or add the key-value pair in the cache
        cache.put(key, value);
    }
}