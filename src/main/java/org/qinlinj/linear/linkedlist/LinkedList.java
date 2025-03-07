package org.qinlinj.linear.linkedlist;

/**
 * A custom implementation of a singly linked list using a dummy head node.
 * This implementation provides dynamic data structure with various manipulation methods.
 *
 * @param <E> the type of elements stored in the linked list
 */
public class LinkedList<E> {
    // Dummy head node to simplify insertion and deletion operations
    private final Node dummyHead;

    // Current number of elements in the list
    private int size;

    /**
     * Constructor to create an empty linked list.
     * Initializes a dummy head node and sets size to 0.
     */
    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the current size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**** Create Operations ****/

    /**
     * Inserts an element at the specified index in the list.
     *
     * @param index the position to insert the new element
     * @param e     the element to be added
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed, index must be >= 0 and <= size");

        // Find the previous node
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // Insert new node
        prev.next = new Node(e, prev.next);
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
            throw new IllegalArgumentException("Remove failed, index must be >= 0 and < size");
        }

        // Find the previous node
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        // Remove the node
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        size--;
        return delNode.e;
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
     * @throws IllegalArgumentException if the list is empty
     */
    public void removeElement(E e) {
        if (isEmpty())
            throw new IllegalArgumentException("RemoveElement failed, LinkedList is empty");

        Node prev = dummyHead;
        Node curr = dummyHead.next;

        while (curr != null) {
            if (e.equals(curr.e)) {
                prev.next = curr.next;
                curr.next = null;
                size--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
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
            throw new IllegalArgumentException("Get failed, index must be >= 0 and < size");
        }

        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.e;
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
        Node curr = dummyHead.next;
        while (curr != null) {
            if (e.equals(curr.e)) {
                return true;
            }
            curr = curr.next;
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
        Node curr = dummyHead.next;
        for (int i = 0; i < size; i++) {
            if (e.equals(curr.e)) {
                return i;
            }
            curr = curr.next;
        }
        return -1;
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
            throw new IllegalArgumentException("Set failed, index must be >= 0 and < size");
        }

        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        curr.e = e;
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return a string showing the elements in the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = dummyHead.next;

        while (curr != null) {
            sb.append(curr.e);
            if (curr.next != null) {
                sb.append("->");
            }
            curr = curr.next;
        }

        return sb.toString();
    }

    // Nested Node class

    /**
     * Internal class representing a node in the linked list.
     */
    private class Node {
        // Element stored in the node
        E e;

        // Reference to the next node
        Node next;

        /**
         * Constructor with element and next node.
         *
         * @param e    the element to store
         * @param next the next node in the list
         */
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        /**
         * Constructor with only an element.
         *
         * @param e the element to store
         */
        public Node(E e) {
            this(e, null);
        }

        /**
         * Default constructor creating an empty node.
         */
        public Node() {
            this(null, null);
        }

        /**
         * Returns a string representation of the node's element.
         *
         * @return the string representation of the element
         */
        @Override
        public String toString() {
            return e == null ? "null" : e.toString();
        }
    }
}