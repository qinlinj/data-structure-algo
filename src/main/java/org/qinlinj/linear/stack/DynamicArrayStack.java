package org.qinlinj.linear.stack;

import org.qinlinj.linear.arraylist.ArrayList;

/**
 * A dynamic stack implementation using a custom ArrayList as the underlying data structure.
 * This stack can grow dynamically as elements are added, unlike the fixed-size ArrayStack.
 *
 * @param <E> the type of elements stored in the stack
 */
public class DynamicArrayStack<E> implements Stack<E> {
    // Internal ArrayList to store stack elements
    private ArrayList<E> data;

    /**
     * Constructor to create a new DynamicArrayStack with an initial capacity.
     *
     * @param capacity the initial capacity of the underlying ArrayList
     */
    public DynamicArrayStack(int capacity) {
        // Create a new ArrayList with the specified initial capacity
        this.data = new ArrayList<>(capacity);
    }

    /**
     * Returns the current number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int getSize() {
        // Delegate to the underlying ArrayList's getSize method
        return data.getSize();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // Delegate to the underlying ArrayList's isEmpty method
        return data.isEmpty();
    }

    /**
     * Adds an element to the top of the stack.
     * The underlying ArrayList will automatically resize if needed.
     *
     * @param e the element to be pushed onto the stack
     */
    @Override
    public void push(E e) {
        // Add the element to the end of the ArrayList (top of the stack)
        data.addLast(e);
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return the element at the top of the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    @Override
    public E pop() {
        // Remove and return the last element of the ArrayList
        return data.removeLast();
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    @Override
    public E peek() {
        // Retrieve the last element of the ArrayList without removing it
        return data.getLast();
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
        for (int i = 0; i < data.getSize(); i++) {
            sb.append(data.get(i));
            // Add comma between elements, except for the last one
            if (i != data.getSize() - 1) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}