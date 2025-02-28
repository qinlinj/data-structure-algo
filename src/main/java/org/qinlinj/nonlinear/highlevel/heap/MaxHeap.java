package org.qinlinj.nonlinear.highlevel.heap;

import org.qinlinj.linear.arraylist.ArrayList;

/**
 * A generic Max Heap implementation where each parent node is greater than or equal to its children.
 * This heap is implemented using an ArrayList as the underlying data structure.
 *
 * @param <E> The type of elements stored in the heap, must be comparable
 */
public class MaxHeap<E extends Comparable<E>> {
    // The underlying data structure to store heap elements
    private ArrayList<E> data;

    /**
     * Constructs a max heap with specified initial capacity
     */
    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    /**
     * Constructs an empty max heap with default capacity
     */
    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    /**
     * Constructs a max heap from an array using heapify process
     * Time complexity: O(n) - more efficient than adding elements one by one O(n log n)
     */
    public MaxHeap(E[] arr) {
        this.data = new ArrayList<>(arr);
        // Heapify process - start from the last non-leaf node and sift down each node
        for (int i = lastNonLeafIndex(); i >= 0; i--) { // O(n)
            siftDown(i); // O(logn)
        }
    }

    /**
     * Returns the number of elements in the heap
     */
    public int size() {
        return data.getSize();
    }

    /**
     * Checks if the heap is empty
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent");
        }
        return (index - 1) / 2;
    }

    private int lastNonLeafIndex() {
        int lastLeafIndex = data.getSize() - 1;
        return parent(lastLeafIndex);
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int index) {
        E e = data.get(index);

        while (index > 0) {
            E parentNode = data.get(parent(index));

            if (e.compareTo(parentNode) <= 0) break;

            data.set(index, parentNode);

            index = parent(index);
        }
        data.set(index, e);
    }

    public E findMax() {
        if (data.getSize() == 0)
            throw new RuntimeException("Heap is Empty");
        return data.getFirst();
    }

    public E removeMax() {
        E max = findMax();
        E last = data.getLast();
        data.set(0, last);

        data.removeLast();

        if (!data.isEmpty()) siftDown(0);

        return max;
    }

    private void siftDown(int index) {
        E e = data.get(index);
        while (leftChild(index) < data.getSize()) {
            int maxNodeIndex = leftChild(index);
            if (rightChild(index) < data.getSize()) {
                if (data.get(rightChild(index)).compareTo(data.get(leftChild(index))) > 0) {
                    maxNodeIndex = rightChild(index);
                }
            }

            if (e.compareTo(data.get(maxNodeIndex)) >= 0) break;

            data.set(index, data.get(maxNodeIndex));

            index = maxNodeIndex;
        }
        data.set(index, e);
    }
}
