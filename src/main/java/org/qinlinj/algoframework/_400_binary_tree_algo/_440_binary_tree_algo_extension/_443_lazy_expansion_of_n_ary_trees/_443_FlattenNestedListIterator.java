package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._443_lazy_expansion_of_n_ary_trees;

import java.util.*;

/**
 * Flattening Nested List Iterator
 * ---------------------------------------------------------
 * This class demonstrates the implementation of an iterator for a nested list structure
 * (LeetCode #341: Flatten Nested List Iterator).
 * <p>
 * Key points:
 * 1. The problem deals with a nested data structure that can contain integers or other nested lists
 * 2. We need to create an iterator that flattens this structure to iterate through all integers
 * 3. Two implementations are provided:
 * - Eager approach: Pre-process the entire structure using DFS (simple but memory-intensive)
 * - Lazy approach: Process elements on-demand (more efficient for large structures)
 * 4. The nested structure is essentially an N-ary tree where leaf nodes are integers
 * 5. Demonstrates iterator design pattern for traversing complex data structures
 * <p>
 * Time Complexity:
 * - Eager: O(n) upfront processing, O(1) per next() call
 * - Lazy: Amortized O(1) per next() call, with occasional O(n) when flattening is needed
 */
public class _443_FlattenNestedListIterator {

    /**
     * Helper method to convert a nested Java structure to a NestedInteger list
     * For testing purposes
     */
    public static List<NestedInteger> createNestedList(Object[] elements) {
        List<NestedInteger> result = new ArrayList<>();

        for (Object element : elements) {
            if (element instanceof Integer) {
                result.add(new NestedIntegerImpl((Integer) element));
            } else if (element instanceof Object[]) {
                List<NestedInteger> nestedList = createNestedList((Object[]) element);
                result.add(new NestedIntegerImpl(nestedList));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1: [[1,1],2,[1,1]]
        Object[] example1 = {
                new Object[]{1, 1},
                2,
                new Object[]{1, 1}
        };
        List<NestedInteger> nestedList1 = createNestedList(example1);

        System.out.println("Testing with [[1,1],2,[1,1]]:");

        // Test eager implementation
        NestedIteratorEager eagerIt = new NestedIteratorEager(nestedList1);
        System.out.print("Eager Iterator: [");
        while (eagerIt.hasNext()) {
            System.out.print(eagerIt.next());
            if (eagerIt.hasNext()) System.out.print(", ");
        }
        System.out.println("]");

        // Re-create the list since it gets consumed by the iterator
        nestedList1 = createNestedList(example1);

        // Test lazy implementation
        NestedIterator lazyIt = new NestedIterator(nestedList1);
        System.out.print("Lazy Iterator: [");
        while (lazyIt.hasNext()) {
            System.out.print(lazyIt.next());
            if (lazyIt.hasNext()) System.out.print(", ");
        }
        System.out.println("]");

        // Example 2: [1,[4,[6]]]
        Object[] example2 = {
                1,
                new Object[]{4, new Object[]{6}}
        };
        List<NestedInteger> nestedList2 = createNestedList(example2);

        System.out.println("\nTesting with [1,[4,[6]]]:");

        // Test eager implementation
        eagerIt = new NestedIteratorEager(nestedList2);
        System.out.print("Eager Iterator: [");
        while (eagerIt.hasNext()) {
            System.out.print(eagerIt.next());
            if (eagerIt.hasNext()) System.out.print(", ");
        }
        System.out.println("]");

        // Re-create the list
        nestedList2 = createNestedList(example2);

        // Test lazy implementation
        lazyIt = new NestedIterator(nestedList2);
        System.out.print("Lazy Iterator: [");
        while (lazyIt.hasNext()) {
            System.out.print(lazyIt.next());
            if (lazyIt.hasNext()) System.out.print(", ");
        }
        System.out.println("]");

        // Create a larger test case to demonstrate the advantage of lazy evaluation
        System.out.println("\nComparison of lazy vs eager for large nested structure:");
        Object[] largeExample = createLargeNestedStructure(5, 1000);
        List<NestedInteger> largeNestedList = createNestedList(largeExample);

        // Measure time for eager initialization
        long startTime = System.nanoTime();
        eagerIt = new NestedIteratorEager(largeNestedList);
        long eagerInitTime = System.nanoTime() - startTime;
        System.out.println("Eager initialization time: " + eagerInitTime / 1_000_000 + " ms");

        // Re-create the list
        largeNestedList = createNestedList(largeExample);

        // Measure time for lazy initialization
        startTime = System.nanoTime();
        lazyIt = new NestedIterator(largeNestedList);
        long lazyInitTime = System.nanoTime() - startTime;
        System.out.println("Lazy initialization time: " + lazyInitTime / 1_000_000 + " ms");

        // Measure time to retrieve first element
        startTime = System.nanoTime();
        if (eagerIt.hasNext()) eagerIt.next();
        long eagerFirstElementTime = System.nanoTime() - startTime;
        System.out.println("Eager first element retrieval time: " + eagerFirstElementTime / 1_000_000 + " ms");

        startTime = System.nanoTime();
        if (lazyIt.hasNext()) lazyIt.next();
        long lazyFirstElementTime = System.nanoTime() - startTime;
        System.out.println("Lazy first element retrieval time: " + lazyFirstElementTime / 1_000_000 + " ms");
    }

    /**
     * Helper method to create a large nested structure for testing
     *
     * @param depth - Maximum nesting depth
     * @param width - Number of elements at each level
     */
    private static Object[] createLargeNestedStructure(int depth, int width) {
        if (depth <= 0) {
            Object[] result = new Object[width];
            for (int i = 0; i < width; i++) {
                result[i] = i;
            }
            return result;
        }

        Object[] result = new Object[width];
        for (int i = 0; i < width; i++) {
            if (i % 10 == 0) {
                // Every 10th element will be a nested list
                result[i] = createLargeNestedStructure(depth - 1, width / 10);
            } else {
                result[i] = i;
            }
        }
        return result;
    }

    /**
     * This interface defines the nested integer structure as described in the problem.
     * In a real interview, this would be provided to you.
     */
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer.
        // Return null if this NestedInteger holds a nested list.
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list.
        // Return null if this NestedInteger holds a single integer.
        List<NestedInteger> getList();
    }

    /**
     * Implementation of NestedInteger for testing purposes
     * This would not be needed in the actual LeetCode problem.
     */
    public static class NestedIntegerImpl implements NestedInteger {
        private Integer val;
        private List<NestedInteger> list;

        // Constructor for an integer value
        public NestedIntegerImpl(Integer val) {
            this.val = val;
            this.list = null;
        }

        // Constructor for a nested list
        public NestedIntegerImpl(List<NestedInteger> list) {
            this.list = list;
            this.val = null;
        }

        @Override
        public boolean isInteger() {
            return val != null;
        }

        @Override
        public Integer getInteger() {
            return val;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }

    /**
     * Solution 1: Eager Approach - Process the entire nested list upfront
     * <p>
     * This approach flattens the entire structure during initialization
     * by performing DFS traversal and collecting all integers.
     */
    public static class NestedIteratorEager implements Iterator<Integer> {
        private Iterator<Integer> iterator;

        public NestedIteratorEager(List<NestedInteger> nestedList) {
            List<Integer> flattenedList = new ArrayList<>();
            // Flatten the nested list upfront
            flattenList(nestedList, flattenedList);
            this.iterator = flattenedList.iterator();
        }

        @Override
        public Integer next() {
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         * Helper method to flatten a nested list using DFS
         */
        private void flattenList(List<NestedInteger> nestedList, List<Integer> result) {
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    // If current element is an integer, add to result
                    result.add(ni.getInteger());
                } else {
                    // If current element is a list, recursively flatten it
                    flattenList(ni.getList(), result);
                }
            }
        }
    }

    /**
     * Solution 2: Lazy Approach - Process elements on-demand
     * <p>
     * This approach processes elements only when necessary,
     * making it more efficient for large structures.
     */
    public static class NestedIterator implements Iterator<Integer> {
        private LinkedList<NestedInteger> list;

        public NestedIterator(List<NestedInteger> nestedList) {
            // Convert the list to a LinkedList for efficient operations
            this.list = new LinkedList<>(nestedList);
        }

        @Override
        public Integer next() {
            // Make sure the next element is an integer before proceeding
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // Remove and return the first element (guaranteed to be an integer by hasNext)
            return list.removeFirst().getInteger();
        }

        @Override
        public boolean hasNext() {
            // Keep processing until the list is empty or first element is an integer
            while (!list.isEmpty() && !list.getFirst().isInteger()) {
                // Get and remove the first element which is known to be a list
                List<NestedInteger> firstList = list.removeFirst().getList();

                // Add elements of this list to the beginning of our list in reverse order
                // This preserves the original order when elements are processed
                for (int i = firstList.size() - 1; i >= 0; i--) {
                    list.addFirst(firstList.get(i));
                }
            }

            return !list.isEmpty();
        }
    }
}