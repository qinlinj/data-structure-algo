package org.qinlinj.linear.queue;

import java.util.Arrays;

/**
 * A fixed-size array-based implementation of a Queue.
 * This implementation uses two pointers (head and tail) to manage queue operations.
 *
 * @param <E> the type of elements stored in the queue
 */
public class ArrayQueue<E> implements Queue<E> {
    // Internal array to store queue elements
    private E[] data;

    // Pointer to the front of the queue
    private int head;

    // Pointer to the rear of the queue
    private int tail;

    /**
     * Constructor with specified capacity.
     *
     * @param capacity the maximum number of elements the queue can hold
     */
    public ArrayQueue(int capacity) {
        // Create a new array of Objects and cast to generic array
        this.data = (E[]) new Object[capacity];
        head = tail = 0;
    }

    /**
     * Default constructor with a default capacity of 15.
     */
    public ArrayQueue() {
        // Fixed: use 'this' to correctly call the other constructor
        this(15);
    }

    /**
     * Main method to demonstrate ArrayQueue functionality.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new ArrayQueue with capacity 5
        ArrayQueue<Integer> queue1 = new ArrayQueue<>(5);

        // Enqueue several elements
        queue1.enqueue(10);
        queue1.enqueue(2);
        queue1.enqueue(4);
        queue1.enqueue(3);

        // Print current size
        System.out.println("Size after enqueuing 4 elements: " + queue1.getSize());

        // Dequeue and print first element
        System.out.println("Dequeued element: " + queue1.dequeue());

        // Print size after dequeuing
        System.out.println("Size after dequeuing: " + queue1.getSize());

        // Enqueue another element
        queue1.enqueue(30);

        // Dequeue and print next element
        System.out.println("Dequeued element: " + queue1.dequeue());

        // Print full queue state
        System.out.println("Queue state: " + queue1);
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the current number of elements
     */
    @Override
    public int getSize() {
        return tail - head;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param e the element to be added
     * @throws RuntimeException if the queue is full
     */
    @Override
    public void enqueue(E e) {
        if (tail == data.length) {
            throw new RuntimeException("Enqueue error, this queue is full");
        }
        data[tail] = e;
        tail++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Dequeue error, this queue is empty");
        }
        E res = data[head];
        head++;
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
     * Returns a string representation of the queue.
     *
     * @return a string showing the internal array, head, and tail
     */
    @Override
    public String toString() {
        return "ArrayQueue{" +
                "data=" + Arrays.toString(data) +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
}