package org.qinlinj.practical.cache;

import java.util.*;

public class LRUCacheLinkedHashMapImp<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCacheLinkedHashMapImp(int capacity, int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCacheLinkedHashMapImp<Integer, Integer> cache =
                new LRUCacheLinkedHashMapImp<>(3, 3, 0.75F);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3));
        cache.put(2, 5);
        cache.put(5, 6);
        System.out.println(cache.get(4));
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (size() > capacity) {
            return true;
        }
        return false;
    }
}
