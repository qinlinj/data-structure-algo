package org.qinlinj.linear.linkedlist;

import java.util.NoSuchElementException;

/**
 * A custom implementation of a doubly-linked list.
 * This implementation provides dynamic data structure with bidirectional traversal
 * and efficient operations at both ends of the list.
 *
 * @param <E> the type of elements stored in the linked list
 */
public class DoubleLinkedList<E> {
    // Reference to the first node in the list
    private Node first;

    // Reference to the last node in the list
    private Node last;

    // Current number of elements in the list
    private int size;

    /**
     * Constructor to create an empty doubly-linked list.
     * Initializes first and last to null, and size to 0.
     */
    public DoubleLinkedList() {
        first = last = null;
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

    /**
     * Efficiently retrieves a node at a given index.
     * Optimizes traversal by choosing the shorter path (from first or last).
     *
     * @param index the index of the node to retrieve
     * @return the node at the specified index, or null if index is invalid
     */
    private Node node(int index) {
        if (index < 0 || index >= size)
            return null;

        Node curr = null;
        // Optimize traversal by choosing the shorter path
        if (index < size / 2) {
            // Start from the first node and move forward
            curr = first;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            // Start from the last node and move backward
            curr = last;
            for (int i = 0; i < size - index - 1; i++) {
                curr = curr.prev;
            }
        }
        return curr;
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

        if (index == size) {
            // Adding at the end
            addLast(e);
        } else if (index == 0) {
            // Adding at the beginning
            addFirst(e);
        } else {
            // Adding in the middle
            Node oldNode = node(index);
            Node prev = oldNode.prev;

            // Create new node with correct prev and next references
            Node newNode = new Node(prev, e, oldNode);

            // Update surrounding nodes' references
            oldNode.prev = newNode;
            prev.next = newNode;

            size++;
        }
    }

    /**
     * Adds an element at the beginning of the list.
     *
     * @param e the element to be added
     */
    public void addFirst(E e) {
        Node newNode = new Node(e);
        if (first == null) {
            // If list is empty, new node is both first and last
            last = newNode;
        } else {
            // Link existing first node
            newNode.next = first;
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    /**
     * Adds an element at the end of the list.
     *
     * @param e the element to be added
     */
    public void addLast(E e) {
        Node newNode = new Node(e);
        if (last == null) {
            // If list is empty, new node is both first and last
            first = newNode;
        } else {
            // Link existing last node
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**** Delete Operations ****/

    /**
     * Removes and returns the first element in the list.
     *
     * @return the removed first element
     * @throws NoSuchElementException if the list is empty
     */
    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("Cannot remove from an empty list");
        }
        E e = first.e;
        Node next = first.next;
        if (next == null) {
            // List had only one element
            first = null;
            last = null;
        } else {
            // Clear references to disconnect the first node
            first.next = null;
            next.prev = null;
            first = next;
        }
        size--;
        return e;
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return the removed last element
     * @throws NoSuchElementException if the list is empty
     */
    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException("Cannot remove from an empty list");
        }
        E e = last.e;
        Node prev = last.prev;
        if (prev == null) {
            // List had only one element
            last = null;
            first = null;
        } else {
            // Clear references to disconnect the last node
            last.prev = null;
            prev.next = null;
            last = prev;
        }
        size--;
        return e;
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed, index must be >= 0 and < size");

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }

        // Remove from the middle
        Node delNode = node(index);
        E e = delNode.e;

        Node prev = delNode.prev;
        Node next = delNode.next;

        // Relink surrounding nodes
        prev.next = next;
        next.prev = prev;

        // Clear node references
        delNode.next = null;
        delNode.prev = null;

        size--;
        return e;
    }

    /**
     * Removes the first occurrence of the specified element.
     *
     * @param e the element to remove
     */
    public void removeElement(E e) {
        Node curr = first;
        while (curr != null) {
            if (e.equals(curr.e)) {
                // Handle different cases based on node position
                if (curr == first) {
                    removeFirst();
                } else if (curr == last) {
                    removeLast();
                } else {
                    // Remove from the middle
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                    curr.next = null;
                    curr.prev = null;
                    size--;
                }
                return;
            }
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
        Node node = node(index);
        if (node == null) {
            throw new IllegalArgumentException("Get failed, index must be >= 0 and < size");
        }
        return node.e;
    }

    /**
     * Returns the first node in the list.
     *
     * @return the first node
     */
    public Node getFirst() {
        return first;
    }

    /**
     * Returns the last node in the list.
     *
     * @return the last node
     */
    public Node getLast() {
        return last;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param e the element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(E e) {
        Node curr = first;
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
        Node curr = first;
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
        Node node = node(index);
        if (node == null) {
            throw new IllegalArgumentException("Set failed, index must be >= 0 and < size");
        }
        node.e = e;
    }

    /**
     * Returns a string representation of the list.
     *
     * @return a string showing the elements in the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = first;
        while (curr != null) {
            sb.append(curr);
            if (curr.next != null) {
                sb.append("->");
            }
            curr = curr.next;
        }
        sb.append("->null");
        return sb.toString();
    }

    /**
     * Internal class representing a node in the doubly-linked list.
     */
    private class Node {
        // Element stored in the node
        E e;

        // Reference to the next node
        Node next;

        // Reference to the previous node
        Node prev;

        /**
         * Constructor with previous node, element, and next node.
         *
         * @param prev the previous node
         * @param e    the element to store
         * @param next the next node
         */
        public Node(Node prev, E e, Node next) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        /**
         * Constructor with only an element.
         *
         * @param e the element to store
         */
        public Node(E e) {
            this(null, e, null);
        }

        /**
         * Returns a string representation of the node's element.
         *
         * @return the string representation of the element
         */
        @Override
        public String toString() {
            return e.toString();
        }
    }
}