package org.qinlinj.practical.cache;

import java.util.*;

public class LRUCacheLinkedHashMapImp<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCacheLinkedHashMapImp(int capacity, int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (size() > capacity) {
            return true;
        }
        return false;
    }
}
