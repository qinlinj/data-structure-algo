package org.qinlinj.practical.cache;

import java.util.*;


/**
 * LRU (Least Recently Used) Cache Implementation using LinkedHashMap
 * <p>
 * Concept and Principles:
 * This implementation leverages Java's LinkedHashMap to create an LRU cache with minimal code.
 * LinkedHashMap maintains insertion order by default, but can be configured to maintain access order
 * by setting the 'accessOrder' parameter to true in its constructor. This makes it perfect for
 * implementing an LRU cache with minimal custom code.
 * <p>
 * Advantages of using LinkedHashMap for LRU Cache:
 * 1. Simplicity: Implementation requires minimal code by leveraging built-in Java collections.
 * 2. Performance: LinkedHashMap provides O(1) average time complexity for all basic operations.
 * 3. Maintainability: Less custom code means fewer potential bugs and easier maintenance.
 * 4. Standard library support: Benefits from Java's well-tested and optimized collections framework.
 * 5. Thread safety considerations: Can be wrapped with Collections.synchronizedMap if needed for
 * concurrent access.
 * <p>
 * How it works:
 * - The LinkedHashMap is initialized with 'accessOrder=true', which means it maintains entries
 * in order of access rather than insertion.
 * - Each time an entry is accessed via get() or put(), it moves to the end of the internal linked list.
 * - By overriding removeEldestEntry(), we can automatically remove the least recently used entry
 * (the first entry in the linked list) when the map exceeds its capacity.
 * <p>
 * Visual example with capacity = 3:
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
 * After put(4, D): Evict LRU element (1)
 * [2:B, 3:C, 4:D]
 * <p>
 * After get(3): Move 3 to end (most recently used position)
 * [2:B, 4:D, 3:C]
 * <p>
 * After put(2, E): Update value and move to end
 * [4:D, 3:C, 2:E]
 */
public class LRUCacheLinkedHashMapImp<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    /**
     * Constructs a new LRU cache based on LinkedHashMap.
     *
     * @param capacity        the maximum number of elements the cache can hold
     * @param initialCapacity the initial capacity of the underlying HashMap
     * @param loadFactor      the load factor of the underlying HashMap
     *                        <p>
     *                        Note: The third parameter in the super constructor is set to true, which enables
     *                        access-order instead of insertion-order. This is crucial for LRU functionality as
     *                        it automatically moves accessed entries to the end of the linked list.
     *                        <p>
     *                        Time Complexity: O(1)
     */
    public LRUCacheLinkedHashMapImp(int capacity, int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCacheLinkedHashMapImp<Integer, Integer> cache =
                new LRUCacheLinkedHashMapImp<>(3, 3, 0.75F);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3));
        cache.put(2, 5);
        cache.put(5, 6);
        System.out.println(cache.get(4));
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (size() > capacity) {
            return true;
        }
        return false;
    }
}
