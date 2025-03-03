package org.qinlinj.nonlinear.highlevel.map;

public interface Map<K, V> {
    /**
     * Get the number of key-value pairs stored in the map.
     *
     * @return the size of the map (number of entries)
     */
    int size();

    /**
     * Check if the map is empty.
     *
     * @return true if the map contains no key-value pairs, false otherwise
     */
    boolean isEmpty();

    /**
     * Add a new key-value pair to the map.
     * If the key already exists, it will be updated with the new value.
     *
     * @param key   the key to be added
     * @param value the value associated with the key
     */
    void add(K key, V value);

    /**
     * Remove the key-value pair associated with the specified key.
     *
     * @param key the key to be removed
     * @return the value previously associated with the key, or null if not found
     */
    V remove(K key);

    /**
     * Update the value associated with the specified key.
     *
     * @param key      the key whose value is to be updated
     * @param newValue the new value to be associated with the key
     */
    void set(K key, V newValue);

    /**
     * Retrieve the value associated with the specified key.
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    V get(K key);

    /**
     * Check if the map contains the specified key.
     *
     * @param key the key to check for existence
     * @return true if the key exists in the map, false otherwise
     */
    boolean containsKey(K key);
}