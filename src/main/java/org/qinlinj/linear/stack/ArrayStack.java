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

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(E e) {
        if (size == data.length) {
            throw new RuntimeException("push failed, stack is full");
        }
        data[size] = e;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("pop failed, stack is empty");
        }
        E res = data[size - 1];
        size--;
        return res;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("pop failed, stack is empty");
        }
        return data[size - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
