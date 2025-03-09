package org.qinlinj.nonlinear.highlevel.set;

/**
 * Hash Set implementation using Separate Chaining (Linked List).
 * <p>
 * Key Characteristics:
 * - Uses an array of linked lists to handle hash collisions
 * - Supports dynamic resizing based on load factor
 * - Provides efficient insertion, deletion, and lookup operations
 *
 * @param <E> the type of elements in the set
 */
public class HashSetSeparateChainingImplementation<E> implements Set<E> {
    // Array of linked list nodes to store elements
    private Node<E>[] data;

    // Number of elements in the set
    private int size;

    // Threshold for resizing the hash set
    private double loadFactor = 0.75;

    /**
     * Default constructor initializes the hash set with a default capacity.
     */
    public HashSetSeparateChainingImplementation() {
        // Initial capacity of 10, can grow dynamically
        this.size = 0;
        this.data = new Node[10];
    }

    /**
     * Constructor that allows custom load factor.
     *
     * @param loadFactor threshold for resizing the hash set
     */
    public HashSetSeparateChainingImplementation(int loadFactor) {
        this();
        this.loadFactor = loadFactor;
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return current size of the set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the set is empty.
     *
     * @return true if set contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Generates a hash index for an element.
     *
     * @param e      element to hash
     * @param length current array length
     * @return hash index within array bounds
     */
    public int hash(E e, int length) {
        // Use absolute value to handle negative hash codes
        // Ensure index is within array bounds
        return Math.abs(e.hashCode()) % length;
    }

    /**
     * Adds an element to the set.
     * <p>
     * Uses separate chaining to handle collisions:
     * - If no collision, create a new head node
     * - If collision, traverse the linked list
     * - If element exists, do nothing
     * - If element doesn't exist, append to the end
     *
     * @param e element to add
     */
    @Override
    public void add(E e) {
        // Calculate hash index
        int index = hash(e, data.length);

        // If no elements at this index, create new node
        if (data[index] == null) {
            data[index] = new Node(e);
        } else {
            // Handle collision using linked list
            Node<E> prev = null;
            Node<E> curr = data[index];

            // Traverse the linked list
            while (curr != null) {
                // If element already exists, skip
                if (e.equals(curr.e)) {
                    return;
                }
                prev = curr;
                curr = curr.next;
            }

            // Append new element at the end of the list
            prev.next = new Node(e);
        }

        // Increment size and resize if needed
        size++;
        if (size >= data.length * loadFactor) {
            resize(2 * data.length);
        }
    }


    /**
     * Resizes the internal array and rehashes all elements.
     *
     * @param newCapacity new size of the internal array
     */
    private void resize(int newCapacity) {
        // Create new array with increased capacity
        Node[] newData = new Node[newCapacity];

        // Rehash all existing elements
        for (int i = 0; i < data.length; i++) {
            Node<E> curr = data[i];

            // Traverse each linked list
            while (curr != null) {
                E e = curr.e;

                // Recalculate hash for new array size
                int newIndex = hash(e, newCapacity);

                // Prepend to new linked list
                Node head = newData[newIndex];
                newData[newIndex] = new Node(e);
                if (head != null) {
                    newData[newIndex].next = head;
                }

                curr = curr.next;
            }
        }

        // Update internal array
        data = newData;
    }

    /**
     * Removes an element from the set.
     * <p>
     * Handles removal by:
     * - Finding the correct linked list
     * - Removing the element from the list
     *
     * @param e element to remove
     */
    @Override
    public void remove(E e) {
        // Calculate hash index
        int index = hash(e, data.length);

        // If no elements at this index, return
        if (data[index] == null) {
            return;
        }

        // Traverse the linked list
        Node<E> prev = null;
        Node<E> curr = data[index];

        while (curr != null) {
            if (e.equals(curr.e)) {
                // Remove from head of list
                if (prev == null) {
                    data[index] = curr.next;
                } else {
                    // Remove from middle/end of list
                    prev.next = curr.next;
                }

                // Help garbage collection
                curr.next = null;
                size--;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    /**
     * Checks if an element exists in the set.
     *
     * @param e element to search for
     * @return true if element is in the set, false otherwise
     */
    @Override
    public boolean contains(E e) {
        // Calculate hash index
        int index = hash(e, data.length);

        // If no elements at this index, return false
        if (data[index] == null) return false;

        // Traverse the linked list
        Node<E> curr = data[index];
        while (curr != null) {
            if (e.equals(curr.e)) {
                return true;
            }
            curr = curr.next;
        }

        return false;
    }

    /**
     * Internal Node class for linked list implementation.
     * <p>
     * Supports creating nodes with:
     * - Element and next node
     * - Only element (next is null)
     * - Empty node
     */
    private class Node<E> {
        // Stored element
        E e;
        // Reference to next node
        Node next;

        /**
         * Full constructor with element and next node.
         *
         * @param e    element to store
         * @param next next node in the list
         */
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        /**
         * Constructor with only element.
         *
         * @param e element to store
         */
        public Node(E e) {
            this(e, null);
        }

        /**
         * Default constructor creates an empty node.
         */
        public Node() {
            this(null, null);
        }
    }
}
