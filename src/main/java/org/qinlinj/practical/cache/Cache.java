package org.qinlinj.practical.cache;

public interface Cache<K, V> {
    V get(K key);
    
}
