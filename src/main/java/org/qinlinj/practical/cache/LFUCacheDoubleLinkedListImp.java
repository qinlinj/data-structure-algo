package org.qinlinj.practical.cache;

import java.util.*;

class Node {
    int key, val, count;
    Node next, prev;

    Node() {
    }

    Node(int key, int val, int count) {
        this.key = key;
        this.val = val;
        this.count = count;
    }
}

class DoubleLinkedList {
    private Node head;
    private Node tail;

    DoubleLinkedList() {
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    Node remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        return node;
    }

    void add(Node node) {
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }

    Node popFirst() {
        if (isEmpty()) return null;
        return remove(this.head.next);
    }

    boolean isEmpty() {
        return this.head.next == this.tail;
    }
}

public class LFUCacheDoubleLinkedListImp {
    private Map<Integer, Node> keyToNode;
    private Map<Integer, DoubleLinkedList> usedCountToKeys;

    private int capacity;
    private int minUsedCount;

    public LFUCacheDoubleLinkedListImp(int capacity) {
        keyToNode = new HashMap<>();
        usedCountToKeys = new HashMap<>();

        this.capacity = capacity;
        minUsedCount = 0;
    }

    public int get(int key) {
        if (capacity == 0) return -1;

        Node node = keyToNode.get(key);
        if (node == null) return -1;

        int usedCount = node.count;
        usedCountToKeys.get(usedCount).remove(node);
        node.count = usedCount + 1;

        if (usedCount == minUsedCount
                && usedCountToKeys.get(usedCount).isEmpty()) {
            minUsedCount++;
        }

        putUsedCount(node, usedCount + 1);

        return node.val;
    }

    private void putUsedCount(Node node, int count) {

    }

    public void put(int key, int value) {

    }
}