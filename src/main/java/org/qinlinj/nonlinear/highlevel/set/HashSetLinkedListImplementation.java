package org.qinlinj.nonlinear.highlevel.set;

public class HashSetLinkedListImplementation<E> implements Set<E> {
    int size;
    Node[] items;

    public HashSetLinkedListImplementation() {
        this.size = 0;
        this.items = new Node[10];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int hash(E e, int length) {
        // Use absolute value to handle negative hash codes
        // Ensure index is within array bounds
        return Math.abs(e.hashCode()) % length;
    }

    @Override
    public void add(E e) {
        int index = hash(e, items.length);
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
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
