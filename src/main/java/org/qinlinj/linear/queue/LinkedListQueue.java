package org.qinlinj.linear.queue;

/**
 * A queue implementation using a singly linked list.
 * This implementation provides dynamic sizing and efficient
 * enqueue and dequeue operations.
 *
 * @param <E> the type of elements stored in the queue
 */
public class LinkedListQueue<E> implements Queue<E> {
    // Reference to the first node in the queue
    private Node head;

    // Reference to the last node in the queue
    private Node tail;

    // Current number of elements in the queue
    private int size;

    /**
     * Constructor to create an empty queue.
     * Initializes head and tail to null, and size to 0.
     */
    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the current number of elements
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param e the element to be added
     */
    @Override
    public void enqueue(E e) {
        // Create a new node with the given element
        Node newNode = new Node(e);

        if (tail == null) {
            // If queue is empty, new node becomes both head and tail
            tail = newNode;
            head = tail;
        } else {
            // Add new node to the end of the queue
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Dequeue error, the queue is empty");
        }

        // Save the node to be removed
        Node delNode = head;

        // Move head to the next node
        head = head.next;

        // Disconnect the removed node
        delNode.next = null;

        // If queue becomes empty after removal
        if (head == null) {
            tail = null;
        }

        size--;
        return delNode.e;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new RuntimeException("GetFront error, no element");
        }
        return head.e;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string showing the elements in the queue
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListQueue: \ntail <- | ");

        Node curr = head;
        while (curr != null) {
            sb.append(curr.e + " <- ");
            curr = curr.next;
        }

        sb.append("null");
        sb.append(" | <- top");
        return sb.toString();
    }

    /**
     * Internal class representing a node in the linked list queue.
     */
    class Node {
        // Element stored in the node
        private E e;

        // Reference to the next node
        private Node next;

        /**
         * Constructor with element and next node.
         *
         * @param e    the element to store
         * @param next the next node in the queue
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