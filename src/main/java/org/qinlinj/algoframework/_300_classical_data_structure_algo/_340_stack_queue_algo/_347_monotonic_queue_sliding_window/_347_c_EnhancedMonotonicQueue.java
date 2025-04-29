package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._347_monotonic_queue_sliding_window;

/**
 * Enhanced Monotonic Queue Implementation
 * <p>
 * This class provides a more complete and generic implementation of the monotonic queue.
 * The enhancements address the limitations in the basic implementation:
 * <p>
 * 1. Added support for both maximum and minimum value tracking
 * 2. Fixed the pop() method to follow standard queue API (no parameter needed)
 * 3. Added size() method to track the number of elements in the queue
 * <p>
 * A monotonic queue is particularly useful for problems involving:
 * - Sliding window maximum/minimum
 * - Finding the next greater/smaller element
 * - Problems where you need to efficiently track extremes while maintaining order
 * <p>
 * This generic implementation allows the monotonic queue to be used with any
 * comparable type, not just integers.
 */

import java.util.*;

public class _347_c_EnhancedMonotonicQueue {

    /**
     * Demonstrates the enhanced monotonic queue with integers
     */
    public static void demonstrateIntegerQueue() {
        MonotonicQueue<Integer> queue = new MonotonicQueue<>();
        int[] elements = {3, 1, 5, 2, 4};

        System.out.println("Demonstration with integers:");
        System.out.println("Elements to add: " + Arrays.toString(elements));

        // Add elements
        for (int elem : elements) {
            queue.push(elem);
            System.out.println("\nAfter adding " + elem + ":");
            System.out.println(queue);
            System.out.println("Max: " + queue.max());
            System.out.println("Min: " + queue.min());
            System.out.println("Size: " + queue.size());
        }

        // Remove elements
        System.out.println("\nRemoving elements:");
        while (!queue.isEmpty()) {
            int elem = queue.pop();
            System.out.println("\nAfter removing " + elem + ":");
            if (!queue.isEmpty()) {
                System.out.println(queue);
                System.out.println("Max: " + queue.max());
                System.out.println("Min: " + queue.min());
                System.out.println("Size: " + queue.size());
            } else {
                System.out.println("Queue is now empty");
            }
        }
    }

    /**
     * Demonstrates the enhanced monotonic queue with strings
     */
    public static void demonstrateStringQueue() {
        MonotonicQueue<String> queue = new MonotonicQueue<>();
        String[] words = {"banana", "apple", "orange", "pear", "grape"};

        System.out.println("\nDemonstration with strings:");
        System.out.println("Words to add: " + Arrays.toString(words));

        // Add elements
        for (String word : words) {
            queue.push(word);
            System.out.println("\nAfter adding '" + word + "':");
            System.out.println(queue);
            System.out.println("Lexicographically maximum: " + queue.max());
            System.out.println("Lexicographically minimum: " + queue.min());
            System.out.println("Size: " + queue.size());
        }

        // Remove elements
        System.out.println("\nRemoving words:");
        while (!queue.isEmpty()) {
            String word = queue.pop();
            System.out.println("\nAfter removing '" + word + "':");
            if (!queue.isEmpty()) {
                System.out.println(queue);
                System.out.println("Lexicographically maximum: " + queue.max());
                System.out.println("Lexicographically minimum: " + queue.min());
                System.out.println("Size: " + queue.size());
            } else {
                System.out.println("Queue is now empty");
            }
        }
    }

    /**
     * Main method to run demonstrations
     */
    public static void main(String[] args) {
        System.out.println("===== ENHANCED MONOTONIC QUEUE =====\n");

        System.out.println("This implementation provides:");
        System.out.println("1. Support for both maximum and minimum tracking");
        System.out.println("2. Standard queue API (no parameter for pop)");
        System.out.println("3. Size tracking with the size() method");
        System.out.println("4. Generic implementation for any comparable type\n");

        demonstrateIntegerQueue();
        demonstrateStringQueue();

        System.out.println("\n===== CONCLUSION =====");
        System.out.println("The enhanced monotonic queue addresses the limitations of the basic");
        System.out.println("implementation while maintaining O(1) time complexity for key operations.");
        System.out.println("It can be used as a drop-in replacement in sliding window problems and");
        System.out.println("other applications requiring efficient tracking of extremal values.");
    }

    /**
     * Generic implementation of a monotonic queue that can track both maximum and minimum values
     * while maintaining the standard queue FIFO behavior
     */
    static class MonotonicQueue<E extends Comparable<E>> {
        // Standard queue for FIFO operations
        private LinkedList<E> queue = new LinkedList<>();

        // Monotonic decreasing queue for maximum values
        private LinkedList<E> maxQueue = new LinkedList<>();

        // Monotonic increasing queue for minimum values
        private LinkedList<E> minQueue = new LinkedList<>();

        /**
         * Add an element to the queue
         *
         * @param elem The element to add
         */
        public void push(E elem) {
            // Add to the standard queue
            queue.addLast(elem);

            // Update max queue (maintain decreasing order)
            while (!maxQueue.isEmpty() && elem.compareTo(maxQueue.getLast()) > 0) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(elem);

            // Update min queue (maintain increasing order)
            while (!minQueue.isEmpty() && elem.compareTo(minQueue.getLast()) < 0) {
                minQueue.pollLast();
            }
            minQueue.addLast(elem);
        }

        /**
         * Remove and return the first element from the queue (standard FIFO behavior)
         *
         * @return The first element in the queue
         */
        public E pop() {
            if (queue.isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }

            E elem = queue.pollFirst();

            // Update max queue if necessary
            if (elem.equals(maxQueue.getFirst())) {
                maxQueue.pollFirst();
            }

            // Update min queue if necessary
            if (elem.equals(minQueue.getFirst())) {
                minQueue.pollFirst();
            }

            return elem;
        }

        /**
         * Get the current size of the queue
         *
         * @return Number of elements in the queue
         */
        public int size() {
            return queue.size();
        }

        /**
         * Get the maximum element in the queue
         *
         * @return The maximum element
         */
        public E max() {
            if (maxQueue.isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            return maxQueue.getFirst();
        }

        /**
         * Get the minimum element in the queue
         *
         * @return The minimum element
         */
        public E min() {
            if (minQueue.isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            return minQueue.getFirst();
        }

        /**
         * Check if the queue is empty
         *
         * @return true if the queue is empty, false otherwise
         */
        public boolean isEmpty() {
            return queue.isEmpty();
        }

        /**
         * Convert the queue to a string representation
         */
        @Override
        public String toString() {
            return "Queue: " + queue +
                    "\nMax Queue: " + maxQueue +
                    "\nMin Queue: " + minQueue;
        }
    }
}
