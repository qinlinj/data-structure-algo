package org.qinlinj.algoframework._500_data_structure_design._560_application_design._563_classic_design_practice; /**
 * LeetCode Problem 284: Peeking Iterator
 * <p>
 * This problem involves designing an iterator that supports a peek operation in addition to
 * the standard next() and hasNext() operations.
 * <p>
 * Problem Description:
 * - Design an iterator that supports peek() operation, in addition to existing hasNext() and next()
 * - peek() returns the next element without advancing the iterator
 * - next() returns the next element and advances the iterator
 * - hasNext() returns whether there are more elements to iterate
 * <p>
 * Key Insight:
 * - We need to cache the next element to enable peek functionality
 * - When next() is called, we return the cached element and load the new next element
 * - When peek() is called, we simply return the cached element without advancing
 * - hasNext() checks if the cached element exists
 * <p>
 * Implementation Details:
 * - We wrap an existing Iterator<Integer> and enhance it with peek functionality
 * - We fetch and cache the first element during construction
 * - For next(), we return the cached element and update the cache with the next one
 * - For peek(), we simply return the cached element
 * - For hasNext(), we check if the cache is null
 * <p>
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(1) as we only store one element
 */

import java.util.*;

public class _563_g_PeekingIterator {

    public static void main(String[] args) {
        // Example usage
        // Create a list to iterate over
        List<Integer> list = Arrays.asList(1, 2, 3);

        // Create a PeekingIterator using the list's iterator
        PeekingIterator peekingIterator = new PeekingIterator(list.iterator());

        System.out.println("Operations:");
        System.out.println("next() -> " + peekingIterator.next());    // Returns 1
        System.out.println("peek() -> " + peekingIterator.peek());    // Returns 2 (next element without advancing)
        System.out.println("next() -> " + peekingIterator.next());    // Returns 2
        System.out.println("next() -> " + peekingIterator.next());    // Returns 3
        System.out.println("hasNext() -> " + peekingIterator.hasNext()); // Returns false

        // Additional example demonstrating the sequence of operations
        System.out.println("\nAdditional example with a larger list:");
        List<Integer> largerList = Arrays.asList(10, 20, 30, 40, 50);
        PeekingIterator largerIterator = new PeekingIterator(largerList.iterator());

        System.out.println("Initial state:");
        System.out.println("hasNext() -> " + largerIterator.hasNext()); // true
        System.out.println("peek() -> " + largerIterator.peek());       // 10
        System.out.println("peek() again -> " + largerIterator.peek()); // Still 10 (peek doesn't advance)

        System.out.println("\nAfter next():");
        System.out.println("next() -> " + largerIterator.next());       // 10
        System.out.println("hasNext() -> " + largerIterator.hasNext()); // true
        System.out.println("peek() -> " + largerIterator.peek());       // 20

        System.out.println("\nAlternating peek() and next():");
        System.out.println("peek() -> " + largerIterator.peek());       // 20
        System.out.println("next() -> " + largerIterator.next());       // 20
        System.out.println("peek() -> " + largerIterator.peek());       // 30
        System.out.println("next() -> " + largerIterator.next());       // 30
    }

    /**
     * Implementation of the PeekingIterator
     */
    static class PeekingIterator implements Iterator<Integer> {
        // The underlying iterator
        private Iterator<Integer> iterator;

        // Cache for the next element
        private Integer nextElement;

        /**
         * Constructor that takes an existing iterator
         */
        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
            // Cache the first element if it exists
            if (iterator.hasNext()) {
                nextElement = iterator.next();
            } else {
                nextElement = null;
            }
        }

        /**
         * Returns the next element without advancing the iterator
         */
        public Integer peek() {
            return nextElement;
        }

        /**
         * Returns the next element and advances the iterator
         */
        @Override
        public Integer next() {
            // Save the current next element to return
            Integer result = nextElement;

            // Update the cache with the next element from the iterator
            if (iterator.hasNext()) {
                nextElement = iterator.next();
            } else {
                nextElement = null;
            }

            return result;
        }

        /**
         * Returns whether there are more elements to iterate
         */
        @Override
        public boolean hasNext() {
            return nextElement != null;
        }
    }
}