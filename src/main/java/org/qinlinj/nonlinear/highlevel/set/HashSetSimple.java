package org.qinlinj.nonlinear.highlevel.set;

public class HashSetSimple<E> implements Set<E> {
    // Internal array to store elements
    E[] data;
    // Number of elements in the set
    int size;

    // Constructor: Initialize with default capacity
    public HashSetSimple() {
        // Create an array of Objects cast to generic type
        this.data = (E[]) new Object[10];
        this.size = 0;
    }

    // Generate hash index for an element
    private int hash(E e, int length) {
        // Use absolute value of hashCode and apply modulo
        return Math.abs(e.hashCode()) % length;
    }

    // Return current number of elements in the set
    @Override
    public int size() {
        return size;
    }

    // Check if the set is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Add element to the set
    @Override
    public void add(E e) {
        // Calculate hash index
        int index = hash(e, data.length);

        // Add element if the slot is empty
        if (data[index] == null) {
            data[index] = e;
            size++;

            // Resize array when full
            if (size == data.length) {
                resize(2 * data.length);
            }
        }
    }

    // Resize the internal array and rehash elements
    private void resize(int newCapacity) {
        // Create new array with increased capacity
        E[] newData = (E[]) new Object[newCapacity];

        // Rehash existing elements to new array
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                int newIndex = hash(data[i], newCapacity);
                newData[newIndex] = data[i];
            }
        }
        data = newData;
    }

    // Remove an element from the set
    @Override
    public void remove(E e) { // O(1) time complexity
        // Calculate hash index
        int index = hash(e, data.length);

        // Remove element if found
        if (data[index] != null) {
            data[index] = null;
            size--;
        }
    }

    // Check if an element exists in the set
    @Override
    public boolean contains(E e) { // O(1) time complexity
        // Calculate hash index and check if slot is not empty
        int index = hash(e, data.length);
        return data[index] != null;
    }
}