package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.linear.arraylist.ArrayList;

public class ArraySet<E> implements Set<E> {
    private ArrayList<E> data;

    public ArraySet() {
        this.data = new ArrayList<>();
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
            data.addLast(e);
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
