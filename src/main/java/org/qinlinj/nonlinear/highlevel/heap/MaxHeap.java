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

    /**
     * Calculates the index of the left child for a node at the given index
     * For a node at index i, its left child is at 2i + 1
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * Calculates the index of the right child for a node at the given index
     * For a node at index i, its right child is at 2i + 2
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * Calculates the index of the parent for a node at the given index
     * For a node at index i, its parent is at (i-1)/2
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * Returns the index of the last non-leaf node in the heap
     * This is the parent of the last element in the array
     */
    private int lastNonLeafIndex() {
        int lastLeafIndex = data.getSize() - 1;
        return parent(lastLeafIndex);
    }

    /**
     * Adds a new element to the heap
     * The element is first added to the end, then sifted up to maintain the heap property
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * Moves an element up the heap until it's in the correct position
     * This ensures the max-heap property where parent > children
     */
    private void siftUp(int index) {
        // Save the element to be sifted up
        E e = data.get(index);

        // Continue until reaching root or finding correct position
        while (index > 0) {
            // Get parent element
            E parentNode = data.get(parent(index));

            // If element is not greater than parent, we've found its position
            if (e.compareTo(parentNode) <= 0) break;

            // Move parent down to current position
            data.set(index, parentNode);

            // Move up to parent position
            index = parent(index);
        }
        // Place element at its final position
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
