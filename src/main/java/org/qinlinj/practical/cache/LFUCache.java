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

        putUsedCount(key, usedCount + 1);

        return value;
    }

    private void putUsedCount(K key, int count) {
        if (!usedCountToKeys.containsKey(count)) {
            usedCountToKeys.put(count, new LinkedHashSet<>());
        }
        usedCountToKeys.get(count).add(key);
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }

        if (cache.size() == capacity) {
            K removeKey = usedCountToKeys.get(minUsedCount).iterator().next();
            usedCountToKeys.get(minUsedCount).remove(removeKey);
            cache.remove(removeKey);
            keyToUsedCount.remove(removeKey);
        }

        cache.put(key, value);
        keyToUsedCount.put(key, 1);

        minUsedCount = 1;
        putUsedCount(key, minUsedCount);
    }
}
