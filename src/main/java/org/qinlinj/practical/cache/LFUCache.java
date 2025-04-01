package org.qinlinj.practical.cache;

import java.util.*;

public class LFUCache<K, V> implements Cache<K, V> {
    private Map<K, V> cache;

    private Map<K, Integer> keyToUsedCount;

    private Map<Integer, LinkedHashSet<K>> usedCountToKeys;

    private int capacity;

    private int minUsedCount;

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }
}
