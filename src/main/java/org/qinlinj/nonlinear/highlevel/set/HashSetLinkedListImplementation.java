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
public class HashSetLinkedListImplementation<E> implements Set<E> {
    // Array of linked list nodes to store elements
    private Node<E>[] data;

    // Number of elements in the set
    private int size;

    // Threshold for resizing the hash set
    private double loadFactor = 0.75;

    /**
     * Default constructor initializes the hash set with a default capacity.
     */
    public HashSetLinkedListImplementation() {
        // Initial capacity of 10, can grow dynamically
        this.size = 0;
        this.data = new Node[10];
    }

    /**
     * Constructor that allows custom load factor.
     *
     * @param loadFactor threshold for resizing the hash set
     */
    public HashSetLinkedListImplementation(int loadFactor) {
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

    @Override
    public void add(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) {
            data[index] = new Node(e);
        } else {
            Node<E> prev = null;
            Node<E> curr = data[index];
            while (curr != null) {
                if (e.equals(curr.e)) {
                    return;
                }
                prev = curr;
                curr = curr.next;
            }
            prev.next = new Node(e);
        }
        size++;
        if (size >= data.length * loadFactor) {
            resize(2 * data.length);
        }
    }

    private void resize(int newCapacity) {
        Node[] newData = new Node[newCapacity];
        for (int i = 0; i < data.length; i++) {
            Node<E> curr = data[i];
            while (curr != null) {
                E e = curr.e;
                int newIndex = hash(e, newCapacity);
                Node head = newData[newIndex];
                newData[newIndex] = new Node(e);
                if (head != null) {
                    newData[newIndex].next = head;
                }
                curr = curr.next;
            }
        }
        data = newData;
    }

    @Override
    public void remove(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) {
            return;
        }
        Node<E> prev = null;
        Node<E> curr = data[index];
        while (curr != null) {
            if (e.equals(curr.e)) {
                if (prev == null) {
                    data[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                curr.next = null;
                size--;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    @Override
    public boolean contains(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) return false;
        Node<E> curr = data[index];
        while (curr != null) {
            if (e.equals(curr.e)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    private class Node<E> {
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }
}
