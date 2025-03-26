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

    public static void main(String[] args) {
        FIFOCache<Integer, Integer> cache = new FIFOCache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3));
        cache.put(4, 5);
        System.out.println(cache.get(4));
        cache.put(5, 6);
        System.out.println(cache.get(2));
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        V oldValue = cache.get(key);
        if (oldValue == null) {
            if (cache.size() == capacity) {
                K oldKey = queue.poll();
                cache.remove(oldKey);
            }
            queue.offer(key);
        }
        cache.put(key, value);
    }
}
