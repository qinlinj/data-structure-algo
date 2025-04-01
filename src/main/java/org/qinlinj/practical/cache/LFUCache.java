package org.qinlinj.practical.cache;

import java.util.*;

public class LFUCache<K, V> implements Cache<K, V> {
    private Map<K, V> cache;

    private Map<K, Integer> keyToUsedCount;

    private Map<Integer, LinkedHashSet<K>> usedCountToKeys;

    private int capacity;

    private int minUsedCount;

    public LFUCache() {
        cache = new HashMap<>();
        keyToUsedCount = new HashMap<>();
        usedCountToKeys = new HashMap<>();

        this.capacity = capacity;
        minUsedCount = 0;
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        if (value == null) return null;

        int usedCount = keyToUsedCount.get(key);
        usedCountToKeys.get(usedCount).remove(key);
        keyToUsedCount.put(key, usedCount + 1);

        if (usedCount == minUsedCount
                && usedCountToKeys.get(usedCount).size() == 0) {
            minUsedCount++;
        }
        
        return value;
    }

    @Override
    public void put(K key, V value) {

    }
}
