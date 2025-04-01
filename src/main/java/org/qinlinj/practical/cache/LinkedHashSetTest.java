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
 * <p>
 * Common Methods of LinkedHashSet:
 * <p>
 * 1. Constructors:
 * - LinkedHashSet(): Creates an empty set with default capacity (16) and load factor (0.75)
 * - LinkedHashSet(int initialCapacity): With specified initial capacity
 * - LinkedHashSet(int initialCapacity, float loadFactor): With specified capacity and load factor
 * - LinkedHashSet(Collection<? extends E> c): Creates a set with elements from the collection
 * <p>
 * 2. Basic Set Operations:
 * - add(E e): Adds an element if not already present - O(1)
 * - remove(Object o): Removes an element if present - O(1)
 * - contains(Object o): Checks if an element exists - O(1)
 * - size(): Returns number of elements in the set - O(1)
 * - isEmpty(): Checks if set is empty - O(1)
 * - clear(): Removes all elements - O(n)
 * <p>
 * 3. Bulk Operations:
 * - addAll(Collection<? extends E> c): Adds all elements from a collection - O(n)
 * - removeAll(Collection<?> c): Removes all elements contained in the collection - O(n)
 * - retainAll(Collection<?> c): Retains only elements contained in the collection - O(n)
 * - containsAll(Collection<?> c): Checks if all elements in a collection are present - O(n)
 * <p>
 * 4. Iteration:
 * - iterator(): Returns an iterator over elements in insertion order
 * - forEach(Consumer<? super E> action): Performs an action on each element (Java 8+)
 * - Supports enhanced for-loop for convenient iteration, maintaining insertion order
 */
public class LinkedHashSetTest {
    public static void main(String[] args) {
        // Create a new LinkedHashSet
        // This set will maintain elements in the order they were first inserted
        Set<Integer> set = new LinkedHashSet<>();

        // Add element 3 to the set
        // Set now contains: [3]
        set.add(3);

        // Add element 1 to the set
        // Set now contains: [3, 1]
        set.add(1);

        // Add element 10 to the set
        // Set now contains: [3, 1, 10]
        set.add(10);

        // Try to add element 1 again (duplicate)
        // Since sets don't allow duplicates, this will not change the set
        // Set still contains: [3, 1, 10]
        set.add(1);

        // Try to add element 3 again (duplicate)
        // Since sets don't allow duplicates, this will not change the set
        // Set still contains: [3, 1, 10]
        set.add(3);

        // Print the set
        // The output will be [3, 1, 10], showing:
        // 1. Elements are in insertion order (3 was added first, then 1, then 10)
        // 2. Duplicates (the second 1 and 3) were ignored
        System.out.println(set);

        /* Expected output:
         * [3, 1, 10]
         */
    }
}