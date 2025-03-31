package org.qinlinj.practical.cache;

import java.util.*;

public class LRUCache<K, V> implements Cache<K, V> {
    private Map<K, Node> cache;
    private int capacity;

    private Node head;
    private Node tail;

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }

    private class Node {
        K key;
        V value;
        Node next;
        Node prev;
    }
}
