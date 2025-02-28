package org.qinlinj.nonlinear.highlevel.heap;

import org.qinlinj.linear.queue.Queue;

/**
 * PriorityQueue - Implementation of a priority queue using a max heap
 * <p>
 * A priority queue is an abstract data type that operates similar to a normal queue,
 * but with each element having a "priority" associated with it. In this implementation,
 * elements with higher priority (as determined by their natural ordering) are dequeued first.
 * <p>
 * This implementation uses a MaxHeap as the underlying data structure, which provides
 * efficient O(log n) operations for both enqueue and dequeue operations.
 *
 * @param <E> Type of elements in the queue, must implement Comparable interface
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap;  // Using MaxHeap as the underlying data structure

    /**
     * Constructor to initialize an empty priority queue
     */
    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    /**
     * Returns the number of elements in this priority queue
     * <p>
     * Time Complexity: O(1)
     *
     * @return The number of elements in the queue
     */
    @Override
    public int getSize() {
        return maxHeap.size();
    }

    /**
     * Checks if the priority queue is empty
     * <p>
     * Time Complexity: O(1)
     *
     * @return true if the queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    /**
     * Adds an element to the priority queue
     * Elements are internally arranged based on their natural ordering defined by Comparable
     * <p>
     * Time Complexity: O(log n), where n is the number of elements in the queue
     *
     * @param e The element to add to the queue
     */
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    /**
     * Removes and returns the highest priority element from the queue
     * In this implementation, the highest element according to natural ordering is returned
     * <p>
     * Time Complexity: O(log n), where n is the number of elements in the queue
     *
     * @return The highest priority element in the queue
     * @throws IllegalStateException if the queue is empty (handled by MaxHeap implementation)
     */
    @Override
    public E dequeue() {
        return maxHeap.removeMax();
    }

    /**
     * Retrieves, but does not remove, the highest priority element in the queue
     * <p>
     * Time Complexity: O(1)
     *
     * @return The highest priority element in the queue
     * @throws IllegalStateException if the queue is empty (handled by MaxHeap implementation)
     */
    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
