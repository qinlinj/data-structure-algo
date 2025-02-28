package org.qinlinj.nonlinear.highlevel.heap;

/**
 * DataStreamMaxHeapImplementation - Implementation of a data stream using a max heap
 * <p>
 * This implementation uses a max heap data structure to provide balanced performance
 * for both adding elements and removing the maximum value (both O(log n)).
 * <p>
 * Characteristics:
 * - The max heap property ensures the largest element is always at the root
 * - Both operations have logarithmic time complexity
 * - Suitable for scenarios where both operations occur with similar frequency
 * - Most efficient for large data streams
 */
public class DataStreamMaxHeapImplementation {
    private MaxHeap<Integer> maxHeap;  // Max heap data structure

    /**
     * Constructor to initialize the data stream with an empty max heap
     */
    public DataStreamMaxHeapImplementation() {
        maxHeap = new MaxHeap<>();
    }

    /**
     * Adds a new value to the data stream
     * <p>
     * Time Complexity: O(log n) - The heap needs to be rebalanced after insertion
     *
     * @param val The integer value to add to the stream
     */
    public void add(int val) {
        // Add the value to the max heap
        // This internally handles the heapify operation to maintain the max heap property
        maxHeap.add(val);
    }

    /**
     * Removes and returns the maximum value in the data stream
     * <p>
     * Time Complexity: O(log n) - The heap needs to be rebalanced after removal
     *
     * @return The maximum value that was in the stream
     * @throws RuntimeException if the stream is empty (handled by MaxHeap implementation)
     */
    public int removeMax() {
        // Remove and return the maximum value from the heap
        // The max heap property ensures the root is always the maximum element
        return maxHeap.removeMax();
    }
}