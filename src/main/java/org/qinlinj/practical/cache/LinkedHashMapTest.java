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
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<>(3, 0.75F, true);

        map.put(5, 5);
        map.put(2, 2);
        map.put(9, 9);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("--------------------------------------");

        map.put(2, 10);
        map.get(5);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
