package org.qinlinj.linear.linkedlist;

public class LinkedList<E> {
    // Dummy head node
    private final Node dummyHead;

    private int size;

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
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, index must >= 0 and <= size");

        Node prev = dummyHead;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    /**** Delete ****/
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, index must >= 0 and < size");
        }
        Node prev = dummyHead.next;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        size--;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        if (dummyHead.next == null)
            throw new IllegalArgumentException("removeElement failed, LinkedList is Empty");
        Node prev = dummyHead;
        Node curr = dummyHead.next;
        while (curr.next != null) {
            if (e.equals(curr.e)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        if (curr != null) {
            prev.next = curr.next;
            curr.next = null;
        }
    }

    /**** Retrieve ****/
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, index must >= 0 and < size");
        }
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        Node curr = dummyHead.next;
        for (int i = 0; i < size; i++) {
            if (curr.e == e) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public int find(E e) {
        Node curr = dummyHead.next;
        for (int i = 0; i < size; i++) {
            if (curr.e == e) {
                return i;
            }
            curr = curr.next;
        }
        return -1;
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
