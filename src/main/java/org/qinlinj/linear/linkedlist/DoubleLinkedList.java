package org.qinlinj.linear.linkedlist;

import java.util.NoSuchElementException;

public class DoubleLinkedList<E> {
    // Dummy head node
    private Node first;

    private Node last;

    private int size;

    public DoubleLinkedList() {
        first = last = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node node(int index) {
        if (index < 0 || index >= size)
            return null;

        Node curr = null;
        if (index < size / 2) {
            curr = first;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = last;
            for (int i = 0; i < size - index - 1; i++) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    /**** Create ****/
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed, index must >= 0 and <= size");

        if (index == size) {
            addLast(e);
        } else if (index == 0) {
            addFirst(e);
        } else {
            Node oldNode = node(index);
            Node prev = oldNode.prev;

            Node newNode = new Node(prev, e, oldNode);

            oldNode.prev = newNode;

            prev.next = newNode;

            size++;
        }
    }

    public void addFirst(E e) {
        Node newNode = new Node(e);
        if (first == null) {
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    public void addLast(E e) {
        Node newNode = new Node(e);
        if (last == null) {
            first = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**** Delete ****/
    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        E e = first.e;
        Node next = first.next;
        if (next == null) {
            first = null;
            last = null;
        } else {
            first.next = null;
            next.prev = null;
            first = next;
        }
        size--;
        return e;
    }

    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        E e = last.e;
        Node prev = last.prev;
        if (prev == null) {
            last = null;
            first = null;
        } else {
            last.prev = null;
            prev.next = null;
            last = prev;
        }
        size--;
        return e;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("remove failed, index must >= 0 and < size");

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }

        Node delNode = node(index);
        E e = delNode.e;

        Node prev = delNode.prev;
        Node next = delNode.next;

        prev.next = next;
        next.prev = prev;

        delNode.next = null;
        delNode.prev = null;

        size--;
        return e;
    }

    public void removeElement(E e) {

    }

    /**** Retrieve ****/
    public E get(int index) {
        Node node = node(index);
        if (node == null) {
            throw new IllegalArgumentException("index failed, index must >= 0 and < size");
        }
        return node.e;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
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

    }

    // Nested Class
    private class Node {
        E e;
        Node next;
        Node prev;

        public Node(Node prev, E e, Node next) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }

        public Node(E e) {
            this(null, e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

}
