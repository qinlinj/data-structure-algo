package org.qinlinj.practical.cache;

public class LRUCache<K, V> {
    private class Node {
        K key;
        V value;
        Node next;
        Node prev;
    }
}
