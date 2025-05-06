package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._532_tree_map_implementation; /**
 * TreeMap Implementation
 * <p>
 * This class implements a TreeMap data structure using a Binary Search Tree (BST).
 * A TreeMap is an ordered map that maintains its keys in sorted order.
 * <p>
 * Key features:
 * 1. Ordered Operations: All operations maintain keys in sorted order
 * 2. Log(n) Performance: put, get, remove operations have O(log n) time complexity
 * 3. Rank and Select: Supports order statistics with rank() and select() methods
 * - rank(key): Returns number of keys less than the given key
 * - select(i): Returns the key at the given index in the sorted order
 * 4. Navigation Methods: floorKey, ceilingKey, minKey, maxKey for BST traversal
 * <p>
 * Implementation Details:
 * - Uses a regular (non-balanced) BST structure
 * - Each TreeNode maintains a size field to track subtree size for rank/select operations
 * - Includes standard map operations: put, get, containsKey, remove
 * - Provides range query capabilities with keys(min, max) method
 */

import java.util.*;

public class _532_a_TreeMap<K extends Comparable<K>, V> {

    private TreeNode root = null;

    public _532_a_TreeMap() {
    }

    /**
     * Adds a key-value pair to the map, or updates the value if the key already exists
     * @return The previous value associated with the key, or null if the key is new
     */
    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        V oldVal = get(key);
        root = put(root, key, val);
        return oldVal;
    }

    // **** Add/Update Methods ****

    private TreeNode put(TreeNode node, K key, V val) {
        if (node == null) {
            // Key doesn't exist, create new node
            return new TreeNode(key, val);
        }
        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            // node.key > key, search in left subtree
            node.left = put(node.left, key, val);
        } else if (cmp < 0) {
            // node.key < key, search in right subtree
            node.right = put(node.right, key, val);
        } else {
            // node.key == key, update the value
            node.val = val;
        }

        // Update size of this node's subtree
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Removes a key from the map and returns its associated value
     * @return The value associated with the key, or null if the key doesn't exist
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (!containsKey(key)) {
            return null;
        }

        V deletedVal = get(key);
        root = remove(root, key);
        return deletedVal;
    }

    // **** Delete Methods ****

    private TreeNode remove(TreeNode node, K key) {
        int cmp = node.key.compareTo(key);
        if (cmp > 0) {
            // node.key > key, search in left subtree
            node.left = remove(node.left, key);
        } else if (cmp < 0) {
            // node.key < key, search in right subtree
            node.right = remove(node.right, key);
        } else {
            // node.key == key, found the node to remove

            // Case 1 & 2: Node has 0 or 1 child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Case 3: Node has 2 children
            // 3.1 Find the maximum node in left subtree
            TreeNode leftMax = maxNode(node.left);
            // 3.2 Remove the max node from left subtree
            node.left = removeMax(node.left);
            // 3.3 Replace current node with leftMax
            leftMax.left = node.left;
            leftMax.right = node.right;
            node = leftMax;
        }

        // Update size of this node's subtree
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Removes the minimum key from the map
     */
    public void removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMin(root);
    }

    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            // Found the leftmost (minimum) node
            return node.right;
        }
        node.left = removeMin(node.left);

        // Update size
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Removes the maximum key from the map
     */
    public void removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMax(root);
    }

    private TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            // Found the rightmost (maximum) node
            return node.left;
        }
        node.right = removeMax(node.right);

        // Update size
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Returns the value associated with the given key
     * @return The value associated with the key, or null if the key doesn't exist
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        TreeNode x = get(root, key);
        if (x == null) {
            // Key doesn't exist
            return null;
        }
        return x.val;
    }

    // **** Query Methods ****

    private TreeNode get(TreeNode node, K key) {
        if (node == null) {
            // Key doesn't exist
            return null;
        }

        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            // node.key > key, search in left subtree
            return get(node.left, key);
        }
        if (cmp < 0) {
            // node.key < key, search in right subtree
            return get(node.right, key);
        }
        // node.key == key, found the key
        return node;
    }

    /**
     * Returns the largest key less than or equal to the given key
     */
    public K floorKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        TreeNode x = floorKey(root, key);
        return x.key;
    }

    private TreeNode floorKey(TreeNode node, K key) {
        if (node == null) {
            // No floor key exists
            return null;
        }

        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            // node.key > key, search in left subtree
            return floorKey(node.left, key);
        }
        if (cmp < 0) {
            // node.key < key, search in right subtree
            // But first check if there's a better floor in right subtree
            TreeNode x = floorKey(node.right, key);
            if (x == null) {
                // No better floor in right subtree, current node is floor
                return node;
            }
            return x;
        }
        // node.key == key, perfect match
        return node;
    }

    /**
     * Returns the smallest key greater than or equal to the given key
     */
    public K ceilingKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        TreeNode x = ceilingKey(root, key);
        return x.key;
    }

    private TreeNode ceilingKey(TreeNode node, K key) {
        if (node == null) {
            // No ceiling key exists
            return null;
        }

        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            // node.key > key, search in left subtree
            // But first check if there's a better ceiling in left subtree
            TreeNode x = ceilingKey(node.left, key);
            if (x == null) {
                // No better ceiling in left subtree, current node is ceiling
                return node;
            }
            return x;
        }
        if (cmp < 0) {
            // node.key < key, search in right subtree
            return ceilingKey(node.right, key);
        }
        // node.key == key, perfect match
        return node;
    }

    /**
     * Returns the number of keys less than the given key
     * This is the rank operation from order statistics
     */
    public int rank(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return rank(root, key);
    }

    private int rank(TreeNode node, K key) {
        if (node == null) {
            return 0;
        }
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            // key < node.key, nothing from this node or right subtree counts
            return rank(node.left, key);
        } else if (cmp > 0) {
            // key > node.key, all keys in left subtree + this node + rank in right subtree
            return size(node.left) + 1 + rank(node.right, key);
        } else {
            // key == node.key, all keys in left subtree are smaller
            return size(node.left);
        }
    }

    /**
     * Returns the key at the given index in the sorted order (0-based)
     * This is the select operation from order statistics
     */
    public K select(int i) {
        if (i < 0 || i >= size()) {
            throw new IllegalArgumentException();
        }

        TreeNode x = select(root, i);
        return x.key;
    }

    private TreeNode select(TreeNode node, int i) {
        int n = size(node.left);

        if (n > i) {
            // Target index is in left subtree
            return select(node.left, i);
        } else if (n < i) {
            // Target index is in right subtree, adjust index
            return select(node.right, i - n - 1);
        } else {
            // i == n, current node is the one we're looking for
            return node;
        }
    }

    /**
     * Returns the largest key in the map
     */
    public K maxKey() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        TreeNode p = maxNode(root);
        return p.key;
    }

    private TreeNode maxNode(TreeNode p) {
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /**
     * Returns the smallest key in the map
     */
    public K minKey() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        TreeNode p = minNode(root);
        return p.key;
    }

    private TreeNode minNode(TreeNode p) {
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    /**
     * Checks if a key exists in the map
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return get(root, key) != null;
    }

    /**
     * Returns all keys in sorted order
     */
    public Iterable<K> keys() {
        if (isEmpty()) {
            return new LinkedList<>();
        }
        LinkedList<K> list = new LinkedList<>();
        traverse(root, list);
        return list;
    }

    // **** Utility Methods ****

    // Inorder traversal of the BST
    private void traverse(TreeNode node, LinkedList<K> list) {
        if (node == null) {
            return;
        }
        traverse(node.left, list);
        // Inorder: process current node after left subtree
        list.addLast(node.key);
        traverse(node.right, list);
    }

    /**
     * Returns all keys in the range [min, max] in sorted order
     */
    public Iterable<K> keys(K min, K max) {
        if (min == null) throw new IllegalArgumentException("min is null");
        if (max == null) throw new IllegalArgumentException("max is null");

        LinkedList<K> list = new LinkedList<>();
        traverse(root, list, min, max);
        return list;
    }

    // Inorder traversal with range constraints
    private void traverse(TreeNode node, LinkedList<K> list, K min, K max) {
        if (node == null) {
            return;
        }

        int cmpMin = min.compareTo(node.key);
        int cmpMax = max.compareTo(node.key);

        if (cmpMin < 0) {
            // min < node.key, check left subtree
            traverse(node.left, list, min, max);
        }

        // Process current node if it's in range
        if (cmpMin <= 0 && cmpMax >= 0) {
            list.addLast(node.key);
        }

        if (cmpMax > 0) {
            // max > node.key, check right subtree
            traverse(node.right, list, min, max);
        }
    }

    /**
     * Returns the number of key-value pairs in the map
     */
    public int size() {
        return size(root);
    }

    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    /**
     * Checks if the map is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    private class TreeNode {
        K key;
        V val;
        TreeNode left, right;
        // Tracks the number of nodes in the subtree rooted at this node
        int size;

        TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
            this.size = 1;
            left = right = null;
        }
    }
}