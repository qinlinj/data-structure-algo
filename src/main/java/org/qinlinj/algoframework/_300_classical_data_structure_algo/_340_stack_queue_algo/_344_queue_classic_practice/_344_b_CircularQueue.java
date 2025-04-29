package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._344_queue_classic_practice;

/**
 * Queue Classic Problems - Problem 2: Design Circular Queue (LeetCode 622)
 * <p>
 * This implementation demonstrates how to create a circular queue data structure.
 * A circular queue efficiently utilizes space by connecting the end to the beginning,
 * allowing reuse of empty spaces after dequeue operations.
 * <p>
 * Key concepts:
 * 1. Dynamic array resizing for efficient space usage
 * 2. Tracking first and last pointers to manage the circular structure
 * 3. Implementation of basic queue operations: enqueue, dequeue, peek
 * <p>
 * Operations supported:
 * - MyCircularQueue(k): Constructor, sets the queue capacity to k
 * - enQueue(value): Insert element into the queue
 * - deQueue(): Remove element from the front of the queue
 * - Front(): Get the front element
 * - Rear(): Get the rear element
 * - isEmpty(): Check if the queue is empty
 * - isFull(): Check if the queue is full
 * <p>
 * Time Complexity:
 * - All operations are O(1), except for resize() which is O(n) but amortized O(1)
 * <p>
 * Space Complexity:
 * - O(k) where k is the capacity of the queue
 */

import java.util.*;

class _344_b_CircularQueue {
    // Circular Queue implementation
    private ArrayQueue<Integer> q;
    private int maxCapacity;
    // Initialize with queue size k
    public _344_b_CircularQueue(int k) {
        q = new ArrayQueue<>(k);
        maxCapacity = k;
    }

    // Main method to demonstrate the implementation
    public static void main(String[] args) {
        _344_b_CircularQueue circularQueue = new _344_b_CircularQueue(3);

        System.out.println(circularQueue.enQueue(1));  // Returns true
        System.out.println(circularQueue.enQueue(2));  // Returns true
        System.out.println(circularQueue.enQueue(3));  // Returns true
        System.out.println(circularQueue.enQueue(4));  // Returns false, queue is full
        System.out.println(circularQueue.Rear());      // Returns 3
        System.out.println(circularQueue.isFull());    // Returns true
        System.out.println(circularQueue.deQueue());   // Returns true
        System.out.println(circularQueue.enQueue(4));  // Returns true
        System.out.println(circularQueue.Rear());      // Returns 4
    }

    // Insert element into circular queue
    public boolean enQueue(int value) {
        if (q.size() == maxCapacity) {
            return false;  // Queue is full
        }
        q.enqueue(value);
        return true;
    }

    // Delete element from circular queue
    public boolean deQueue() {
        if (q.isEmpty()) {
            return false;  // Queue is empty
        }
        q.dequeue();
        return true;
    }

    // Get front item from queue
    public int Front() {
        if (q.isEmpty()) {
            return -1;  // Queue is empty
        }
        return q.peekFirst();
    }

    // Get last item from queue
    public int Rear() {
        if (q.isEmpty()) {
            return -1;  // Queue is empty
        }
        return q.peekLast();
    }

    // Check if the circular queue is empty
    public boolean isEmpty() {
        return q.isEmpty();
    }

    // Check if the circular queue is full
    public boolean isFull() {
        return q.size() == maxCapacity;
    }

    // ArrayQueue implementation for the circular queue
    static class ArrayQueue<E> {
        private final static int INIT_CAP = 2;  // Initial capacity
        private int size;           // Current number of elements
        private E[] data;           // Array to store elements
        private int first, last;    // Pointers for front and rear of queue

        // Constructor with specified initial capacity
        @SuppressWarnings("unchecked")
        public ArrayQueue(int initCap) {
            size = 0;
            data = (E[]) new Object[initCap];
            first = last = 0;  // Both pointers start at index 0
        }

        // Default constructor
        public ArrayQueue() {
            this(INIT_CAP);  // Use default capacity
        }

        // Add element to the end of the queue
        public void enqueue(E e) {
            // Resize if queue is full
            if (size == data.length) {
                resize(size * 2);
            }

            // Add element at last position
            data[last] = e;
            last++;

            // Wrap around if necessary (circular property)
            if (last == data.length) {
                last = 0;
            }

            size++;
        }

        // Remove and return the element at the front
        public E dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }

            // Shrink array if it's too large to save space
            if (size == data.length / 4) {
                resize(data.length / 2);
            }

            // Get the front element
            E oldVal = data[first];
            data[first] = null;  // Help garbage collection
            first++;

            // Wrap around if necessary (circular property)
            if (first == data.length) {
                first = 0;
            }

            size--;
            return oldVal;
        }

        // Resize the array to the new capacity
        @SuppressWarnings("unchecked")
        private void resize(int newCap) {
            E[] temp = (E[]) new Object[newCap];

            // Copy elements to the new array, maintaining their order
            for (int i = 0; i < size; i++) {
                temp[i] = data[(first + i) % data.length];
            }

            // Reset pointers
            first = 0;
            last = size;
            data = temp;
        }

        // Get the front element without removing it
        public E peekFirst() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return data[first];
        }

        // Get the rear element without removing it
        public E peekLast() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            // Handle the case when last is at position 0
            if (last == 0) {
                return data[data.length - 1];
            }
            return data[last - 1];
        }

        // Get the current size of the queue
        public int size() {
            return size;
        }

        // Check if queue is empty
        public boolean isEmpty() {
            return size == 0;
        }
    }
}
