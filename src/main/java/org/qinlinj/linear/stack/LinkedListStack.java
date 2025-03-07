package org.qinlinj.linear.stack;

import org.qinlinj.linear.linkedlist.LinkedList;

/**
 * A stack implementation using a custom LinkedList as the underlying data structure.
 * This implementation provides stack operations by manipulating the first element of the linked list.
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedListStack<E> implements Stack<E> {
    // Internal LinkedList to store stack elements
    LinkedList<E> data;

    /**
     * Constructor to create a new LinkedListStack.
     * Initializes an empty LinkedList to store stack elements.
     */
    public LinkedListStack() {
        // Create a new empty LinkedList
        this.data = new LinkedList<>();
    }

    /**
     * Returns the current number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int getSize() {
        // Delegate to the underlying LinkedList's getSize method
        return data.getSize();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        // Delegate to the underlying LinkedList's isEmpty method
        return data.isEmpty();
    }

    /**
     * Adds an element to the top of the stack.
     * In this implementation, adding to the top means adding to the first position of the linked list.
     *
     * @param e the element to be pushed onto the stack
     */
    @Override
    public void push(E e) {
        // Add the element to the first position of the LinkedList
        data.addFirst(e);
    }

    /**
     * Removes and returns the top element from the stack.
     * In this implementation, the top element is the first element of the linked list.
     *
     * @return the element at the top of the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    @Override
    public E pop() {
        // Remove and return the first element of the LinkedList
        return data.removeFirst();
    }

    /**
     * Returns the top element of the stack without removing it.
     * In this implementation, the top element is the first element of the linked list.
     *
     * @return the element at the top of the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    @Override
    public E peek() {
        // Retrieve the first element of the LinkedList without removing it
        return data.getFirst();
    }

    /**
     * Provides a string representation of the stack.
     * The elements are printed from top to bottom of the stack.
     *
     * @return a string showing the elements in the stack
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        // Iterate through the elements from the last to the first
        // This ensures the elements are printed in the order they would be popped
        for (int i = data.getSize() - 1; i >= 0; i--) {
            sb.append(data.get(i));
            // Add comma between elements, except for the last one
            if (i != 0) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}