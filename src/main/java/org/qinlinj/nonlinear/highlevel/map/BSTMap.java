package org.qinlinj.nonlinear.highlevel.map;

import java.util.NoSuchElementException;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
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
        return size;
    }

    /**
     * Check if the map is empty.
     *
     * @return true if the map contains no key-value pairs, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
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
        root = add(root, key, value);
    }

    public TreeNode add(TreeNode node, K key, V value) {
        if (node == null) {
            size++;
            return new TreeNode(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }

        return node;
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

    private TreeNode remove(TreeNode node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 要删除的节点就是 node
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
        }
    }

    /**
     * Update the value associated with the specified key.
     *
     * @param key      the key whose value is to be updated
     * @param newValue the new value to be associated with the key
     */
    @Override
    public void set(K key, V newValue) {
        TreeNode node = get(root, key);
        if (node == null) {
            throw new NoSuchElementException("There is no corresponding key ：" + key);
        }
        node.value = newValue;
    }

    /**
     * Retrieve the value associated with the specified key.
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V get(K key) {
        TreeNode node = get(root, key);
        return node == null ? null : node.value;
    }

    private TreeNode get(TreeNode node, K key) {
        if (node == null) return null;

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    /**
     * Check if the map contains the specified key.
     *
     * @param key the key to check for existence
     * @return true if the key exists in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        TreeNode node = get(root, key);
        return node != null;
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
