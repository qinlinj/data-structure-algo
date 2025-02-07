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
        return data.removeFirst();
    }

    @Override
    public E peek() {
        return data.getFirst();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = data.getSize() - 1; i >= 0; i--) {
            sb.append(data.get(i));
            if (i != 0) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
