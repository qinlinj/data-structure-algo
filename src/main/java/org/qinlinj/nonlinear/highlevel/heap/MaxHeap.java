package org.qinlinj.nonlinear.highlevel.heap;

import java.util.ArrayList;

public class MaxHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        this.data = new ArrayList<>();
    }
}
