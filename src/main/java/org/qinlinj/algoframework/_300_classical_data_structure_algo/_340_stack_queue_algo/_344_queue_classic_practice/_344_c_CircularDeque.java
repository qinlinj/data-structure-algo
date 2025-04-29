package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._344_queue_classic_practice;

/**
 * Queue Classic Problems - Problem 3: Design Circular Deque (LeetCode 641)
 * <p>
 * This implementation demonstrates how to create a circular deque (double-ended queue)
 * that allows operations at both ends of the queue. This is an extension of the circular
 * queue with additional functionality.
 * <p>
 * Key concepts:
 * 1. Double-ended operations (insertion and deletion from both ends)
 * 2. Circular array implementation for space efficiency
 * 3. Proper handling of edge cases (empty/full queue, wrapping around)
 * <p>
 * Operations supported:
 * - MyCircularDeque(k): Constructor, sets the deque capacity to k
 * - insertFront(value): Add element at the front
 * - insertLast(value): Add element at the end
 * - deleteFront(): Remove element from the front
 * - deleteLast(): Remove element from the end
 * - getFront(): Get the front element
 * - getRear(): Get the rear element
 * - isEmpty(): Check if the deque is empty
 * - isFull(): Check if the deque is full
 * <p>
 * Time Complexity:
 * - All operations are O(1), except for resize() which is O(n) but amortized O(1)
 * <p>
 * Space Complexity:
 * - O(k) where k is the capacity of the deque
 */

import java.util.*;

class _344_c_CircularDeque {

    // Circular Deque implementation
    private int cap;
    private MyArrayDeque<Integer> list;
    // Initialize with specified capacity
    public _344_c_CircularDeque(int k) {
        this.cap = k;
        this.list = new MyArrayDeque<>();
    }

    // Main method to demonstrate the implementation
    public static void main(String[] args) {
        _344_c_CircularDeque circularDeque = new _344_c_CircularDeque(3);

        System.out.println(circularDeque.insertLast(1));   // Returns true
        System.out.println(circularDeque.insertLast(2));   // Returns true
        System.out.println(circularDeque.insertFront(3));  // Returns true
        System.out.println(circularDeque.insertFront(4));  // Returns false, deque is full
        System.out.println(circularDeque.getRear());       // Returns 2
        System.out.println(circularDeque.isFull());        // Returns true
        System.out.println(circularDeque.deleteLast());    // Returns true
        System.out.println(circularDeque.insertFront(4));  // Returns true
        System.out.println(circularDeque.getFront());      // Returns 4
    }

    // Add element at the front
    public boolean insertFront(int value) {
        if (list.size() == cap) {
            return false;  // Deque is full
        }

        list.addFirst(value);
        return true;
    }

    // Add element at the end
    public boolean insertLast(int value) {
        if (list.size() == cap) {
            return false;  // Deque is full
        }

        list.addLast(value);
        return true;
    }

    // Remove element from the front
    public boolean deleteFront() {
        if (list.isEmpty()) {
            return false;  // Deque is empty
        }

        list.removeFirst();
        return true;
    }

    // Remove element from the end
    public boolean deleteLast() {
        if (list.isEmpty()) {
            return false;  // Deque is empty
        }

        list.removeLast();
        return true;
    }

    // Get the front element
    public int getFront() {
        if (list.isEmpty()) {
            return -1;  // Deque is empty
        }

        return list.getFirst();
    }

    // Get the last element
    public int getRear() {
        if (list.isEmpty()) {
            return -1;  // Deque is empty
        }

        return list.getLast();
    }

    // Check if the deque is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Check if the deque is full
    public boolean isFull() {
        return list.size() == cap;
    }

    // Custom array-based deque implementation
    static class MyArrayDeque<E> {
        private final static int INIT_CAP = 2;  // Initial capacity
        private int size;           // Current number of elements
        private E[] data;           // Array to store elements
        private int first, last;    // Pointers for front and rear of deque

        // Constructor with specified initial capacity
        @SuppressWarnings("unchecked")
        public MyArrayDeque(int initCap) {
            size = 0;
            data = (E[]) new Object[initCap];
            // first is where we remove from, last is where we add
            first = last = 0;
        }

        // Default constructor
        public MyArrayDeque() {
            this(INIT_CAP);  // Use default capacity
        }

        // Get the front element without removing it
        public E getFirst() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }
            return data[first];
        }

        // Get the rear element without removing it
        public E getLast() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }

            // Handle the case when last is at position 0
            if (last == 0) {
                return data[data.length - 1];
            }
            return data[last - 1];
        }

        // Add element to the front of the deque
        public void addFirst(E e) {
            // Resize if deque is full
            if (size == data.length) {
                resize(size * 2);
            }

            // Move first pointer left (with wraparound)
            if (first == 0) {
                first = data.length - 1;
            } else {
                first--;
            }

            // Insert element at the front
            data[first] = e;
            size++;
        }

        // Add element to the end of the deque
        public void addLast(E e) {
            // Resize if deque is full
            if (size == data.length) {
                resize(size * 2);
            }

            // Insert element at the end
            data[last] = e;
            last++;

            // Wrap around if necessary
            if (last == data.length) {
                last = 0;
            }

            size++;
        }

        // Remove and return the element at the front
        public E removeFirst() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }

            // Shrink array if it's too large
            if (size == data.length / 4) {
                resize(data.length / 2);
            }

            // Get the front element
            E oldVal = data[first];
            data[first] = null;  // Help garbage collection
            first++;

            // Wrap around if necessary
            if (first == data.length) {
                first = 0;
            }

            size--;
            return oldVal;
        }

        // Remove and return the element at the end
        public E removeLast() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }

            // Shrink array if it's too large
            if (size == data.length / 4) {
                resize(data.length / 2);
            }

            // Move last pointer left (with wraparound)
            if (last == 0) {
                last = data.length - 1;
            } else {
                last--;
            }

            // Get and remove the last element
            E oldVal = data[last];
            data[last] = null;  // Help garbage collection

            size--;
            return oldVal;
        }

        // Get the current size
        public int size() {
            return size;
        }

        // Check if deque is empty
        public boolean isEmpty() {
            return size == 0;
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
    }
}
