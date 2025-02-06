package org.qinlinj.linear.linkedlist;

public class LinkedList<E> {
    // Dummy head node
    private final Node dummyHead;

    private final int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**** Create ****/
    public void add(int index, E e) {

    }

    public void addFirst() {

    }

    public void addLast() {

    }

    /**** Delete ****/
    public E remove(int index) {

    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size - 1);
    }

    public int removeElement(E e) {

    }

    /**** Retrieve ****/
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, index must >= 0 and < size")
        }
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.e;
    }

    public void getFirst() {
        get(0);
    }

    public void getLast() {
        get(size - 1);
    }

    public boolean contains(E e) {

    }

    public int find(E e) {

    }

    /**** Update ****/
    public void set(int index, E e) {

    }

    // toSting
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = dummyHead.next;
        while (curr.next != null) {
            sb.append(curr);
            sb.append("->");
            curr = curr.next;
        }
        sb.append("null");
        return sb.toString();
    }

    // Nested Class
    private class Node {
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

        @Override
        public String toString() {
            return e.toString();
        }
    }

}
