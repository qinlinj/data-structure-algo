package org.qinlinj.nonlinear.highlevel.set;

public class HashSetLinkedListImplementation<E> implements Set<E> {
    private Node<E>[] data;
    private int size;

    public HashSetLinkedListImplementation() {
        this.size = 0;
        this.data = new Node[10];
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
        int index = hash(e, data.length);
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
