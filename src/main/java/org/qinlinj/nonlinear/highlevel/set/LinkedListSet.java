package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.linear.linkedlist.LinkedList;

public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> data;

    public LinkedListSet() {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(Object o) {

    }

    @Override
    public void remove(Object o) {

    }

    @Override
    public boolean contains(Object o) {
        return false;
    }
}
