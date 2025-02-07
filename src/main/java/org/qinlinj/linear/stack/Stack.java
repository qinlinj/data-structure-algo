package org.qinlinj.linear.stack;

public interface Stack<E> {
    int getsize();

    boolean isempty();

    void push(E e);

    E pop();

    E peak();
}
