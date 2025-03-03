package org.qinlinj.linear.stack;

public interface Stack<E> {
    /**
     * Returns the number of elements in the stack.
     *
     * @return the total count of elements currently in the stack
     */
    int getSize();

    /**
     * Checks if the stack contains no elements.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Adds an element to the top of the stack.
     *
     * @param e the element to be pushed onto the top of the stack
     */
    void push(E e);

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element removed from the top of the stack
     */
    E pop();

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     */
    E peek();
}