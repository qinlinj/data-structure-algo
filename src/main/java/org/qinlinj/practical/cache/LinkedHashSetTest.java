package org.qinlinj.practical.cache;

import java.util.*;

/**
 * LinkedHashSet Test Class
 * <p>
 * This class demonstrates the behavior of LinkedHashSet, particularly its insertion-order iteration
 * and set properties.
 * <p>
 * Concept and Features of LinkedHashSet:
 * <p>
 * 1. LinkedHashSet is a HashSet implementation that maintains the insertion order of elements.
 * 2. It combines the constant-time performance of HashSet with predictable iteration order.
 * 3. LinkedHashSet implements the Set interface, which means:
 * - No duplicate elements are allowed
 * - Elements are unique based on their equals() and hashCode() methods
 * - The order of elements is preserved based on when they were first inserted
 * 4. Internally, LinkedHashSet is backed by a LinkedHashMap with the same elements as keys and
 * a dummy value shared for all entries.
 * 5. Unlike LinkedHashMap, LinkedHashSet doesn't offer an access-order mode - it only preserves
 * insertion order.
 * <p>
 * Visual representation of how LinkedHashSet works with duplicates:
 * <p>
 * Initial empty set: []
 * <p>
 * After adding 3: [3]
 * After adding 1: [3, 1]
 * After adding 10: [3, 1, 10]
 * After adding 1 (duplicate): [3, 1, 10] (no change, as 1 already exists)
 * After adding 3 (duplicate): [3, 1, 10] (no change, as 3 already exists)
 * <p>
 * Time complexity: All basic operations (add, remove, contains) have O(1) average time complexity.
 */
public class LinkedHashSetTest {
    public static void main(String[] args) {
        Set<Integer> set = new LinkedHashSet<>();
        set.add(3);
        set.add(1);
        set.add(10);
        set.add(1);
        set.add(3);
        System.out.println(set);
    }
}
