package org.qinlinj.practical.cache;

import java.util.*;

/**
 * FIFO (First-In-First-Out) Cache Implementation
 * <p>
 * Concept and Principles:
 * FIFO cache is a simple caching strategy where the oldest inserted elements are evicted first
 * when the cache reaches its capacity and a new element needs to be added.
 * <p>
 * Advantages of FIFO Cache:
 * 1. Simplicity: The implementation is straightforward and easy to understand.
 * 2. Predictable behavior: The oldest elements are always evicted first.
 * 3. Low overhead: Maintaining the queue of keys has minimal performance impact.
 * 4. No need to track access patterns: Unlike LRU, FIFO doesn't need to update data structures on cache reads.
 * <p>
 * Limitations:
 * 1. Does not consider frequency or recency of access when making eviction decisions.
 * 2. May evict frequently used items if they were among the first inserted.
 * <p>
 * Visual example of FIFO cache operations with capacity = 3:
 * <p>
 * Initial state: Empty cache
 * []
 * <p>
 * After put(1, A):
 * [1:A]
 * <p>
 * After put(2, B):
 * [1:A, 2:B]
 * <p>
 * After put(3, C): Cache is now full
 * [1:A, 2:B, 3:C]
 * <p>
 * After put(4, D): Evict oldest element (1)
 * [2:B, 3:C, 4:D]
 * <p>
 * After get(3): No change in order, just return C
 * [2:B, 3:C, 4:D]
 * <p>
 * After put(5, E): Evict oldest element (2)
 * [3:C, 4:D, 5:E]
 */
public class FIFOCache<K, V> implements Cache<K, V> {
    private Map<K, V> cache;
    private Queue<K> queue;
    private int capacity;

    public FIFOCache(int capacity) {
        cache = new HashMap<>(capacity);
        queue = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        FIFOCache<Integer, Integer> cache = new FIFOCache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3));
        cache.put(4, 5);
        System.out.println(cache.get(4));
        cache.put(5, 6);
        System.out.println(cache.get(2));
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        V oldValue = cache.get(key);
        if (oldValue == null) {
            if (cache.size() == capacity) {
                K oldKey = queue.poll();
                cache.remove(oldKey);
            }
            queue.offer(key);
        }
        cache.put(key, value);
    }
}
