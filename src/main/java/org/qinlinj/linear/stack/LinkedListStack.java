package org.qinlinj.linear.stack;

import org.qinlinj.linear.linkedlist.LinkedList;

public class LinkedListStack<E> implements Stack<E> {
    LinkedList<E> data;

    public LinkedListStack() {
        this.data = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        data.addFirst(e);
    }

    @Override
    public E pop() {
        return data.getFirst();
    }

    @Override
    public E peak() {
        return data.removeFirst();
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack：[");
        int size = data.getSize();
        for (int i = 0; i < size - 1; i++) {
            res.append(data.get(i));
            if (i != size - 2) {
                res.append(",");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
