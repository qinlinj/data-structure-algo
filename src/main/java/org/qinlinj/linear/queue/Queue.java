package org.qinlinj.linear.queue;

public interface Queue<E> {
    /**
     * Returns the number of elements in the queue.
     *
     * @return the total count of elements currently in the queue
     */
    int getSize();

    /**
     * Checks if the queue contains no elements.
     *
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Adds an element to the rear (end) of the queue.
     *
     * @param e the element to be added to the queue
     */
    void enqueue(E e);

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element removed from the front of the queue
     * //     * @throws EmptyQueueException if the queue is empty
     */
    E dequeue();

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * //     * @throws EmptyQueueException if the queue is empty
     */
    E getFront();
}
