package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.linear.linkedlist.LinkedList;

public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> data;

    public LinkedListSet() {
    }

    public LinkedListSet() {
        this.data = new LinkedList<>();
    }

    @Override
    public int size() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void add(E e) { // O(n)
        if (!data.contains(e)) {
            data.addFirst(e);
        }
    }

    @Override
    public void remove(E e) { // O(n)
        data.removeElement(e);
    }

    @Override
    public boolean contains(E e) { // O(n)
        return data.contains(e);
    }
}
