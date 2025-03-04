package org.qinlinj.nonlinear.highlevel.map;

public class BSTMap<K, V> implements Map<K, V> {
    private TreeNode root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Get the number of key-value pairs stored in the map.
     *
     * @return the size of the map (number of entries)
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Check if the map is empty.
     *
     * @return true if the map contains no key-value pairs, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Add a new key-value pair to the map.
     * If the key already exists, it will be updated with the new value.
     *
     * @param key   the key to be added
     * @param value the value associated with the key
     */
    @Override
    public void add(K key, V value) {

    }

    /**
     * Remove the key-value pair associated with the specified key.
     *
     * @param key the key to be removed
     * @return the value previously associated with the key, or null if not found
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * Update the value associated with the specified key.
     *
     * @param key      the key whose value is to be updated
     * @param newValue the new value to be associated with the key
     */
    @Override
    public void set(K key, V newValue) {

    }

    /**
     * Retrieve the value associated with the specified key.
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V get(K key) {
        return null;
    }

    /**
     * Check if the map contains the specified key.
     *
     * @param key the key to check for existence
     * @return true if the key exists in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        return false;
    }

    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
