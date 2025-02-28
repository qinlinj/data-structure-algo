package org.qinlinj.nonlinear.highlevel.heap;

import org.qinlinj.linear.arraylist.ArrayList;

public class MaxHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
