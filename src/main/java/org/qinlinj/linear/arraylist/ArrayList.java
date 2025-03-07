package org.qinlinj.linear.arraylist;

/**
 * A dynamic array implementation that provides resizable array-like functionality.
 * This class allows dynamic addition, removal, and manipulation of elements.
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayList<E> {
    // Internal array to store elements
    private E[] data;

    // Current capacity of the array
    private int capacity;

    // Number of elements currently in the array
    private int size;

    /**
     * Constructor with specified initial capacity.
     *
     * @param capacity the initial capacity of the array
     */
    public ArrayList(int capacity) {
        // Create a new array of Objects and cast to generic array
        this.data = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * Constructor that creates an ArrayList from an existing array.
     *
     * @param arr the input array to copy elements from
     */
    public ArrayList(E[] arr) {
        // Create a new array with the same length as input array
        this.data = (E[]) new Object[arr.length];
        // Copy elements from input array
        System.arraycopy(arr, 0, data, 0, arr.length);
        this.size = arr.length;
        this.capacity = arr.length;
    }

    /**
     * Default constructor with a default initial capacity of 15.
     */
    public ArrayList() {
        this(15);
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the current capacity of the list.
     *
     * @return the current capacity of the list
     */
    public int getCapacity() {
        return capacity;
    }

    /**** Create Operations ****/

    /**
     * Adds a new element at the specified index.
     *
     * @param index the position to insert the new element
     * @param e     the element to be added
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public void add(int index, E e) {
        // Validate index
        if (index > size || index < 0) {
            throw new IllegalArgumentException("Add failed. Index must be between 0 and size.");
        }

        // Resize if array is full
        if (size == data.length) {
            resize(size * 2);
        }

        // Shift elements to make space for new element
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        // Insert new element
        data[index] = e;
        size++;
    }

    /**
     * Adds an element at the beginning of the list.
     *
     * @param e the element to be added
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * Adds an element at the end of the list.
     *
     * @param e the element to be added
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * Resizes the internal array to the new capacity.
     *
     * @param newCapacity the new capacity for the array
     */
    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
        capacity = newCapacity;
    }

    /**** Update Operations ****/

    /**
     * Replaces the element at the specified index.
     *
     * @param index the index of the element to replace
     * @param e     the new element
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index must be between 0 and size-1.");
        }
        data[index] = e;
    }

    /**** Retrieve Operations ****/

    /**
     * Returns the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index must be between 0 and size-1.");
        }
        return data[index];
    }

    /**
     * Returns the first element in the list.
     *
     * @return the first element
     * @throws IllegalArgumentException if the list is empty
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * Returns the last element in the list.
     *
     * @return the last element
     * @throws IllegalArgumentException if the list is empty
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param e the element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            // Fixed bug: was incorrectly comparing with index instead of element
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the index of the first occurrence of the specified element.
     *
     * @param e the element to search for
     * @return the index of the element, or -1 if not found
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            // Fixed bug: was incorrectly comparing with index instead of element
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**** Delete Operations ****/

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index must be between 0 and size-1.");
        }

        E res = data[index];

        // Shift elements to fill the gap
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        size--;
        data[size] = null; // Help garbage collection

        // Resize down if the array is too sparse
        // Prevent resizing to zero or below
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return res;
    }

    /**
     * Removes the first element in the list.
     *
     * @return the removed first element
     * @throws IllegalArgumentException if the list is empty
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * Removes the last element in the list.
     *
     * @return the removed last element
     * @throws IllegalArgumentException if the list is empty
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * Removes the first occurrence of the specified element.
     *
     * @param e the element to remove
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string showing the list's size, capacity, and elements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}