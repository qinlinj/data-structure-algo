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
}
