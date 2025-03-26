package org.qinlinj.practical.cache;

import java.util.*;

public class LocalCache<K, V> implements Cache<K, V> {
    private Map<K, V> cache;

    public LocalCache() {
        cache = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }
}
