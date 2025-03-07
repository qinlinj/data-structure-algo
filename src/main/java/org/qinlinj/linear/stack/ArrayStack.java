package org.qinlinj.linear.stack;

import java.util.NoSuchElementException;

/**
 * An array-based implementation of the Stack interface.
 * This stack has a fixed capacity and supports basic stack operations.
 *
 * @param <E> the type of elements stored in the stack
 */
public class ArrayStack<E> implements Stack<E> {
    // Internal array to store stack elements
    private E[] data;

    // Current number of elements in the stack
    private int size;

    /**
     * Constructor to create a new ArrayStack with a specified capacity.
     *
     * @param capacity the maximum number of elements the stack can hold
     */
    public ArrayStack(int capacity) {
        // Create a new array of Objects and cast to generic array
        // This is the standard way to create a generic array in Java
        data = (E[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * Returns the current number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param e the element to be pushed onto the stack
     * @throws RuntimeException if the stack is already at full capacity
     */
    @Override
    public void push(E e) {
        // Check if the stack is full before pushing
        if (size == data.length) {
            throw new RuntimeException("push failed, stack is full");
        }
        // Add the element at the current size index and increment size
        data[size] = e;
        size++;
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() {
        // Check if the stack is empty before popping
        if (isEmpty()) {
            throw new NoSuchElementException("pop failed, stack is empty");
        }
        // Get the top element
        E res = data[size - 1];
        // Decrease the size
        size--;
        // Return the top element
        return res;
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() {
        // Check if the stack is empty before peeking
        if (isEmpty()) {
            throw new NoSuchElementException("pop failed, stack is empty");
        }
        // Return the top element
        return data[size - 1];
    }

    /**
     * Provides a string representation of the stack.
     *
     * @return a string showing the elements in the stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        // Iterate through the elements up to the current size
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            // Add comma between elements, except for the last one
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}