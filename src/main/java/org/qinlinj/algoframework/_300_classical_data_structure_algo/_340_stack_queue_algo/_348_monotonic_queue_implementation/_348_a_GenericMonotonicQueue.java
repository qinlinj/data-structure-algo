package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._348_monotonic_queue_implementation;

/**
 * Generic Monotonic Queue Implementation
 * <p>
 * This class implements a generic monotonic queue that can efficiently track both
 * maximum and minimum values while maintaining standard queue operations.
 * <p>
 * Key features:
 * 1. Supports generic types that implement the Comparable interface
 * 2. Maintains three internal queues:
 * - A standard queue for FIFO operations
 * - A max queue for O(1) access to maximum value (decreasing order)
 * - A min queue for O(1) access to minimum value (increasing order)
 * 3. Provides standard queue operations:
 * - push(elem): Add element to the queue
 * - pop(): Remove and return first element
 * - size(): Return number of elements
 * - isEmpty(): Check if queue is empty
 * 4. Provides additional operations:
 * - max(): Return maximum element in O(1) time
 * - min(): Return minimum element in O(1) time
 * <p>
 * This implementation is particularly useful for:
 * - Sliding window problems requiring maximum/minimum tracking
 * - Dynamic programming optimization
 * - Problems involving range queries with window constraints
 * <p>
 * Time Complexity:
 * - All operations are amortized O(1)
 * - push() can be O(n) in worst case but amortized O(1) overall
 * <p>
 * Space Complexity: O(n) where n is the number of elements in the queue
 */

import java.util.*;

public class _348_a_GenericMonotonicQueue<E extends Comparable<E>> {

    // Standard queue to store all elements in FIFO order
    private LinkedList<E> q = new LinkedList<>();

    // Monotonically decreasing queue for maximum tracking
    private LinkedList<E> maxq = new LinkedList<>();

    // Monotonically increasing queue for minimum tracking
    private LinkedList<E> minq = new LinkedList<>();

    /**
     * Example usage of the monotonic queue
     */
    public static void main(String[] args) {
        _348_a_GenericMonotonicQueue<Integer> queue = new _348_a_GenericMonotonicQueue<>();

        System.out.println("Demonstrating Generic Monotonic Queue:");

        // Add some elements
        queue.push(3);
        queue.push(1);
        queue.push(5);
        queue.push(2);

        System.out.println("Current max: " + queue.max());  // 5
        System.out.println("Current min: " + queue.min());  // 1
        System.out.println("Queue size: " + queue.size());  // 4

        // Pop an element
        int popped = queue.pop();
        System.out.println("Popped: " + popped);  // 3

        System.out.println("Current max: " + queue.max());  // 5
        System.out.println("Current min: " + queue.min());  // 1
        System.out.println("Queue size: " + queue.size());  // 3

        // Test with strings
        _348_a_GenericMonotonicQueue<String> strQueue = new _348_a_GenericMonotonicQueue<>();
        strQueue.push("banana");
        strQueue.push("apple");
        strQueue.push("orange");

        System.out.println("\nString queue demo:");
        System.out.println("Lexicographically max: " + strQueue.max());  // orange
        System.out.println("Lexicographically min: " + strQueue.min());  // apple
    }

    /**
     * Add an element to the queue
     * @param elem The element to add
     */
    public void push(E elem) {
        // Add to standard queue for FIFO behavior
        q.addLast(elem);

        // Maintain maxq in decreasing order
        // Remove all elements smaller than the new element
        while (!maxq.isEmpty() && maxq.getLast().compareTo(elem) < 0) {
            maxq.pollLast();
        }
        maxq.addLast(elem);

        // Maintain minq in increasing order
        // Remove all elements larger than the new element
        while (!minq.isEmpty() && minq.getLast().compareTo(elem) > 0) {
            minq.pollLast();
        }
        minq.addLast(elem);
    }

    /**
     * Get the maximum element in the queue
     * @return The maximum element
     */
    public E max() {
        if (maxq.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return maxq.getFirst();
    }

    /**
     * Get the minimum element in the queue
     * @return The minimum element
     */
    public E min() {
        if (minq.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return minq.getFirst();
    }

    /**
     * Remove and return the first element from the queue
     * @return The first element
     */
    public E pop() {
        if (q.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        // Remove from standard queue
        E deleteVal = q.pollFirst();

        // Update max queue if necessary
        if (deleteVal.equals(maxq.getFirst())) {
            maxq.pollFirst();
        }

        // Update min queue if necessary
        if (deleteVal.equals(minq.getFirst())) {
            minq.pollFirst();
        }

        return deleteVal;
    }

    /**
     * Get the current size of the queue
     * @return Number of elements in the queue
     */
    public int size() {
        return q.size();
    }

    /**
     * Check if the queue is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return q.isEmpty();
    }
}
