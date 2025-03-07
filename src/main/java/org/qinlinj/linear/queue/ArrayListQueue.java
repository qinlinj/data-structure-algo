package org.qinlinj.linear.queue;

import org.qinlinj.linear.arraylist.ArrayList;

/**
 * A queue implementation using a custom ArrayList as the underlying data structure.
 * This implementation provides dynamic sizing and efficient queue operations
 * by leveraging the ArrayList's capabilities.
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayListQueue<E> implements Queue<E> {
    // Internal ArrayList to store queue elements
    private ArrayList<E> data;

    /**
     * Constructor to create an empty queue.
     * Initializes a new ArrayList with default capacity.
     */
    public ArrayListQueue() {
        // Create a new ArrayList to store queue elements
        this.data = new ArrayList<>();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the current number of elements
     */
    @Override
    public int getSize() {
        // Delegate to the underlying ArrayList's getSize method
        return data.getSize();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // Delegate to the underlying ArrayList's isEmpty method
        return data.isEmpty();
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param e the element to be added
     */
    @Override
    public void enqueue(E e) {
        // Add the element to the end of the ArrayList
        data.addLast(e);
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @Override
    public E dequeue() {
        // Remove and return the first element of the ArrayList
        return data.removeFirst();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @Override
    public E getFront() {
        // Retrieve the first element of the ArrayList
        return data.getFirst();
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string showing the elements in the queue
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue：head [");

        // Iterate through all elements in the ArrayList
        for (int i = 0; i < data.getSize(); i++) {
            res.append(data.get(i));

            // Add comma between elements, except for the last one
            if (i != data.getSize() - 1) {
                res.append(", ");
            }
        }

        res.append("]");
        return res.toString();
    }
}