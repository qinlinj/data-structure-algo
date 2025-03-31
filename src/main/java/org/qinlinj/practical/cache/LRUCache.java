package org.qinlinj.practical.cache;

public class LRUCache<K, V> implements Cache<K, V> {
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
