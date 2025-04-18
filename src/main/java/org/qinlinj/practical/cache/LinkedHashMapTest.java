package org.qinlinj.practical.cache;

import java.util.*;

/**
 * LinkedHashMap Test Class
 * <p>
 * This class demonstrates the behavior of LinkedHashMap, particularly focusing on its access-order functionality.
 * <p>
 * Concept and Features of LinkedHashMap:
 * <p>
 * 1. LinkedHashMap is a HashMap implementation that maintains the insertion order of elements by default.
 * 2. Unlike HashMap (which has no guaranteed order), LinkedHashMap uses a doubly-linked list running through
 * all its entries to maintain a predictable iteration order.
 * 3. LinkedHashMap can be configured in two modes:
 * - Insertion-order: Elements are iterated in the order they were inserted (default behavior)
 * - Access-order: Elements are iterated according to when they were last accessed, with least
 * recently accessed elements appearing first (LRU behavior)
 * 4. The access-order mode makes LinkedHashMap perfect for implementing LRU caches with minimal code.
 * <p>
 * Visual representation of how LinkedHashMap with access-order works:
 * <p>
 * Initial state after putting 5, 2, 9:
 * [5] -> [2] -> [9]  (in insertion order)
 * <p>
 * After updating entry with key 2:
 * [5] -> [9] -> [2]  (2 moves to the end as most recently accessed)
 * <p>
 * After getting entry with key 5:
 * [9] -> [2] -> [5]  (5 moves to the end as most recently accessed)
 * <p>
 * Time complexity: All basic operations (get, put, remove) have O(1) average time complexity.
 * <p>
 * Common Methods of LinkedHashMap:
 * <p>
 * 1. Constructors:
 * - LinkedHashMap(): Creates an insertion-ordered LinkedHashMap with default capacity and load factor
 * - LinkedHashMap(int initialCapacity): With specified initial capacity
 * - LinkedHashMap(int initialCapacity, float loadFactor): With specified initial capacity and load factor
 * - LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder): With specified order mode
 * - LinkedHashMap(Map<? extends K, ? extends V> m): Creates a LinkedHashMap with same mappings as the given map
 * <p>
 * 2. Basic Map Operations:
 * - put(K key, V value): Adds or updates a mapping - O(1)
 * - get(Object key): Returns the value for a key - O(1)
 * - remove(Object key): Removes a mapping - O(1)
 * - containsKey(Object key): Checks if a key exists - O(1)
 * - containsValue(Object value): Checks if a value exists - O(n)
 * - size(): Returns number of mappings - O(1)
 * - isEmpty(): Checks if map is empty - O(1)
 * - clear(): Removes all mappings - O(n)
 * <p>
 * 3. LinkedHashMap-specific Methods:
 * - removeEldestEntry(Map.Entry<K,V> eldest): Override to implement auto-removal in LRU cache
 * - eldest element is the one that would be removed next if insertion order is used
 * <p>
 * 4. Iteration Methods:
 * - keySet(): Returns a Set view of keys
 * - values(): Returns a Collection view of values
 * - entrySet(): Returns a Set view of mappings
 * - All these respect the ordering of the LinkedHashMap (insertion or access order)
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        // Create a LinkedHashMap with access-order=true
        // Parameters: initialCapacity=3, loadFactor=0.75F, accessOrder=true
        // The 'true' for accessOrder means entries will be ordered by access time
        // (most recently accessed entries will appear last in the iteration)
        Map<Integer, Integer> map = new LinkedHashMap<>(3, 0.75F, true);

        // Add three entries to the map
        // After these operations, entries will be in insertion order: 5, 2, 9
        map.put(5, 5);  // First entry
        map.put(2, 2);  // Second entry
        map.put(9, 9);  // Third entry

        // Print all entries in current order (should be insertion order: 5, 2, 9)
        System.out.println("Initial order after insertion:");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("--------------------------------------");

        // Update the value for key 2 (this counts as an access, moving 2 to the end)
        // Order after this: 5, 9, 2
        map.put(2, 10);

        // Access the entry with key 5 (moves 5 to the end)
        // Order after this: 9, 2, 5
        map.get(5);

        // Print all entries in current order
        // Due to access-order=true, the order now reflects recent access
        // Expected order: 9, 2, 5 (least recently accessed to most recently accessed)
        System.out.println("Order after modifications (access-order):");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        /* Expected output:
         * Initial order after insertion:
         * 5 5
         * 2 2
         * 9 9
         * --------------------------------------
         * Order after modifications (access-order):
         * 9 9
         * 2 10
         * 5 5
         */
    }
}