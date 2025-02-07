package org.qinlinj.linear.stack;

import org.qinlinj.linear.array.ArrayList;

public class DynamicArrayStack<E> implements Stack<E> {
    private ArrayList<E> data;

    public DynamicArrayStack(int capacity) {
        this.data = new ArrayList<>();
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
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peak() {
        return data.getLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        for (int i = 0; i < data.getSize(); i++) {
            sb.append(data.get(i));
            if (i != data.getSize() - 1) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
