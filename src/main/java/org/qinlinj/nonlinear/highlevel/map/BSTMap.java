package org.qinlinj.nonlinear.highlevel.map;

import java.util.NoSuchElementException;

/**
 * A Binary Search Tree implementation of the Map interface.
 * This class provides a map data structure using a binary search tree,
 * where elements are ordered based on their keys.
 *
 * @param <K> the type of keys stored in this map, must be comparable
 * @param <V> the type of values stored in this map
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    // The root node of the binary search tree
    private TreeNode root;

    // The number of key-value pairs in the map
    private int size;

    /**
     * Constructs an empty BSTMap.
     */
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

    /**
     * Helper method to recursively add a key-value pair to the BST.
     * If the key already exists, the value will be updated.
     *
     * @param node  the current node in the recursion
     * @param key   the key to be added
     * @param value the value associated with the key
     * @return the updated node after addition
     */
    private TreeNode add(TreeNode node, K key, V value) {
        // If we reach a null node, create a new node with the key-value pair
        if (node == null) {
            size++;
            return new TreeNode(key, value);
        }

        // Compare the key with the current node's key to determine the direction
        if (key.compareTo(node.key) < 0) {
            // Key is smaller, go to the left subtree
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            // Key is larger, go to the right subtree
            node.right = add(node.right, key, value);
        } else {
            // Key already exists, update its value
            node.value = value;
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
        // Find the value associated with the key before removing it
        V value = get(key);
        if (value != null) {
            root = remove(root, key);
        }
        return value;
    }

    /**
     * Helper method to recursively remove a key-value pair from the BST.
     *
     * @param node the current node in the recursion
     * @param key  the key to be removed
     * @return the updated node after removal
     */
    private TreeNode remove(TreeNode node, K key) {
        if (node == null) return null;

        // Compare the key with the current node's key to determine the direction
        if (key.compareTo(node.key) < 0) {
            // Key is smaller, go to the left subtree
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            // Key is larger, go to the right subtree
            node.right = remove(node.right, key);
            return node;
        } else {
            // Found the node to remove

            // Case 1: Node has no left child
            if (node.left == null) {
                TreeNode rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // Case 2: Node has no right child
            if (node.right == null) {
                TreeNode leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // Case 3: Node has both children
            // Find the minimum value in the right subtree (successor)
            TreeNode successor = minValue(node.right);

            // Replace the node with its successor
            successor.right = removeMin(node.right);
            successor.left = node.left;

            // Clean up references in the node to be removed
            node.left = null;
            node.right = null;

            return successor;
        }
    }

    /**
     * Find the node with the minimum key value in a subtree.
     *
     * @param node the root of the subtree
     * @return the node with the minimum key value
     */
    private TreeNode minValue(TreeNode node) {
        if (node.left == null) return node;
        return minValue(node.left);
    }

    /**
     * Remove the node with the minimum key from the tree.
     */
    private void removeMin() {
        root = removeMin(root);
    }

    /**
     * Helper method to recursively remove the node with the minimum key from a subtree.
     *
     * @param node the root of the subtree
     * @return the updated subtree after removal
     */
    private TreeNode removeMin(TreeNode node) {
        // If there's no left child, this node is the minimum
        if (node.left == null) {
            TreeNode rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        // Continue searching for the minimum in the left subtree
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * Update the value associated with the specified key.
     *
     * @param key      the key whose value is to be updated
     * @param newValue the new value to be associated with the key
     * @throws NoSuchElementException if the key is not found in the map
     */
    @Override
    public void set(K key, V newValue) {
        TreeNode node = get(root, key);
        if (node == null) {
            throw new NoSuchElementException("There is no corresponding key: " + key);
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

    /**
     * Helper method to recursively find a node by its key.
     *
     * @param node the current node in the recursion
     * @param key  the key to search for
     * @return the node containing the key, or null if not found
     */
    private TreeNode get(TreeNode node, K key) {
        if (node == null) return null;

        // Compare the key with the current node's key
        if (key.compareTo(node.key) == 0) {
            // Found the key
            return node;
        } else if (key.compareTo(node.key) < 0) {
            // Key is smaller, search in the left subtree
            return get(node.left, key);
        } else {
            // Key is larger, search in the right subtree
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

    /**
     * Inner class representing a node in the binary search tree.
     * Each node contains a key-value pair and references to its left and right children.
     */
    private class TreeNode {
        K key;           // The key stored in this node
        V value;         // The value associated with the key
        TreeNode left;   // Reference to the left child (smaller keys)
        TreeNode right;  // Reference to the right child (larger keys)

        /**
         * Constructs a new tree node with the specified key and value.
         *
         * @param key   the key to be stored in this node
         * @param value the value associated with the key
         */
        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}