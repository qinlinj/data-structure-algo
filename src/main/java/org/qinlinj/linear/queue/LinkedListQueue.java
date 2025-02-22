package org.qinlinj.linear.queue;

public class LinkedListQueue<E> implements Queue<E> {
    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        Node newNode = new Node(e);
        if (tail == null) {
            tail = newNode;
            head = tail;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("dequeue error, the queue is empty");
        }
        Node delNode = head;
        head = head.next;
        delNode.next = null;

        if (head == null) {
            tail = null;
        }
        size--;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) throw new RuntimeException("getFront error, no element");
        return head.e;
    }

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

    class Node {
        private E e;
        private Node next;

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
