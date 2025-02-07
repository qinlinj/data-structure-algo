package org.qinlinj.linear.stack;

public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peak();
}
