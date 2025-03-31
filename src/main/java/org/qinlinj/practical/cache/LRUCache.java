package org.qinlinj.practical.cache;

import java.util.*;

public class LRUCache<K, V> implements Cache<K, V> {
    private Map<K, Node> cache;
    private int capacity;

    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;

        cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) return null;
        moveNodeToHead(node);
        return node.value;
    }

    private void moveNodeToHead(Node node) {
        removeNode(node);

        addNodeToHead(node);
    }

    private void addNodeToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        Node preNode = node.prev;
        Node nextNode = node.next;

        preNode.next = nextNode;
        nextNode.prev = preNode;

        node.prev = null;
        node.next = null;
    }


    @Override
    public void put(K key, V value) {
        Node node = cache.get(key);
        if (node == null) {

        } else {

        }
    }

    private class Node {
        K key;
        V value;
        Node next;
        Node prev;
    }
}
