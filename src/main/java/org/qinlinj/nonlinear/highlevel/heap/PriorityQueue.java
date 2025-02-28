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
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.removeMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
