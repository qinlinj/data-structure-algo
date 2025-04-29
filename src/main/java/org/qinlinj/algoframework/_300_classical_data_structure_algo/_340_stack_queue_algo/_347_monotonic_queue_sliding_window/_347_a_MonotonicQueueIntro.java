package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._347_monotonic_queue_sliding_window;

/**
 * Monotonic Queue - Introduction and Concept
 * <p>
 * A monotonic queue is a data structure that maintains the elements in a monotonic order
 * (either increasing or decreasing) while preserving the queue's FIFO (First-In-First-Out) property.
 * <p>
 * Key characteristics:
 * 1. It's a queue with additional constraints - elements are sorted in monotonic order
 * 2. Allows O(1) time complexity for finding the maximum/minimum value in the queue
 * 3. Maintains the time order of elements (unlike a heap which sorts by value)
 * <p>
 * Primary use cases:
 * - Finding maximum/minimum value in a sliding window
 * - Problems where you need to efficiently track extremes in a sequence with the FIFO property
 * <p>
 * The monotonic queue differs from a regular queue in that:
 * - Regular Queue: supports push and pop operations with FIFO order
 * - Monotonic Queue: supports push, max/min, and pop operations while maintaining monotonicity
 * <p>
 * This implementation is designed for finding maximum values, with elements arranged in
 * descending order (largest element at the front).
 */

import java.util.*;

public class _347_a_MonotonicQueueIntro {

    /**
     * Demonstrates how the monotonic queue works with a simple example
     */
    public static void demonstrateMonotonicQueue() {
        MonotonicQueue queue = new MonotonicQueue();
        int[] elements = {3, 1, 5, 2, 4};

        System.out.println("Demonstration of Monotonic Queue operations:");
        System.out.println("Original elements: " + Arrays.toString(elements));

        for (int i = 0; i < elements.length; i++) {
            System.out.println("\nStep " + (i + 1) + ": Adding element " + elements[i]);
            queue.push(elements[i]);

            // Show the internal state of the monotonic queue
            System.out.println("   Queue state: " + queue.maxq);
            System.out.println("   Current maximum: " + queue.max());

            // Demonstrate pop operation
            if (i > 0) {
                int elementToRemove = elements[i - 1];
                System.out.println("   Removing element: " + elementToRemove);
                queue.pop(elementToRemove);
                System.out.println("   Queue state after removal: " + queue.maxq);
            }
        }
    }

    /**
     * Main method to demonstrate the concept
     */
    public static void main(String[] args) {
        System.out.println("===== MONOTONIC QUEUE INTRODUCTION =====\n");
        System.out.println("A monotonic queue is a data structure that maintains elements in");
        System.out.println("monotonic order (increasing or decreasing) while preserving the");
        System.out.println("queue's FIFO (First-In-First-Out) property.\n");

        System.out.println("Key operations:");
        System.out.println("1. push(n) - Add element n to the queue");
        System.out.println("2. max() - Get the maximum element in the queue in O(1) time");
        System.out.println("3. pop(n) - Remove element n from the front of the queue\n");

        System.out.println("Primary use case: Finding the maximum/minimum in a sliding window efficiently\n");

        demonstrateMonotonicQueue();
    }

    /**
     * Implementation of a monotonic queue that maintains maximum values
     */
    static class MonotonicQueue {
        // Double-ended queue to maintain elements in decreasing order
        private LinkedList<Integer> maxq = new LinkedList<>();

        /**
         * Add an element to the queue while maintaining monotonic decreasing order
         *
         * @param n The element to add
         */
        public void push(int n) {
            // Remove all elements smaller than n from the back of the queue
            // This ensures the queue maintains a descending order
            while (!maxq.isEmpty() && maxq.getLast() < n) {
                maxq.pollLast();
            }
            // Add the new element to the back of the queue
            maxq.addLast(n);
        }

        /**
         * Get the maximum element in the queue
         *
         * @return The maximum element
         */
        public int max() {
            // The front element is always the maximum
            return maxq.getFirst();
        }

        /**
         * Remove an element from the front of the queue if it matches the given value
         *
         * @param n The element to remove
         */
        public void pop(int n) {
            // Only remove if the front element is n (the one we want to remove)
            // It might have been "squashed" during a push operation
            if (n == maxq.getFirst()) {
                maxq.pollFirst();
            }
        }
    }
}
