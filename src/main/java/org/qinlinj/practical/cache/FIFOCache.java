package org.qinlinj.practical.cache;

import java.util.*;

public class FIFOCache<K, V> implements Cache<K, V> {
    private Map<K, V> cache;
    private Queue<K> queue;
    private int capacity;

    public FIFOCache(int capacity) {
        cache = new HashMap<>(capacity);
        queue = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {

    }
}
