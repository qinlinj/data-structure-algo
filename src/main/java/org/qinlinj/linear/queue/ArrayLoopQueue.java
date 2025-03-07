package org.qinlinj.linear.queue;

/**
 * A circular array-based queue implementation with dynamic resizing.
 * This implementation uses a circular buffer to efficiently utilize array space
 * and provides dynamic capacity management.
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayLoopQueue<E> implements Queue<E> {
    // Internal array to store queue elements
    private E[] data;

    // Pointer to the front of the queue
    private int head;

    // Pointer to the rear of the queue
    private int tail;

    // Current number of elements in the queue
    private int size;

    /**
     * Constructor with specified capacity.
     *
     * @param capacity the initial capacity of the queue
     */
    public ArrayLoopQueue(int capacity) {
        // Create a new array of Objects and cast to generic array
        this.data = (E[]) new Object[capacity];
        head = tail = size = 0;
    }

    /**
     * Default constructor with a default capacity of 15.
     */
    public ArrayLoopQueue() {
        this(15);
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the current number of elements
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an element to the rear of the queue.
     * Resizes the array if it's full.
     *
     * @param e the element to be added
     */
    @Override
    public void enqueue(E e) {
        // Resize array if it's full
        if (size == data.length) {
            resize(data.length * 2);
        }

        // Add element at the tail
        data[tail] = e;
        size++;

        // Move tail pointer circularly
        tail = (tail + 1) % data.length;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * Resizes the array down if it becomes too sparse.
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Dequeue error, this queue is empty");
        }

        // Store the element to be removed
        E res = data[head];

        // Clear the reference to help garbage collection
        data[head] = null;

        // Move head pointer circularly
        head = (head + 1) % data.length;
        size--;

        // Resize down if the array becomes too sparse
        if (size <= data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return res;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new RuntimeException("Get front error, this queue is empty");
        }
        return data[head];
    }

    /**
     * Resizes the internal array to a new capacity.
     * Rearranges elements to maintain their original order.
     *
     * @param newCapacity the new capacity for the array
     */
    private void resize(int newCapacity) {
        // Create a new array with the specified capacity
        E[] newData = (E[]) new Object[newCapacity];

        // Rearrange elements in the new array
        for (int i = 0; i < size; i++) {
            // Use circular indexing to handle wrapped-around elements
            newData[i] = data[(head + i) % data.length];
        }

        // Update references
        data = newData;
        head = 0;
        tail = size;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string showing the queue's size, capacity, and elements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue：size = %d, capacity = %d\n", size, data.length));
        sb.append("Head [");

        // Iterate through queue elements circularly
        for (int i = head; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}