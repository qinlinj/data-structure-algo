package org.qinlinj.nonlinear.tree.bplustree;

import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * B+ Tree Implementation
 * ======================
 *
 * CONCEPT AND PRINCIPLES:
 * A B+ tree is a balanced tree data structure that maintains sorted data and allows for efficient
 * insertion, deletion, and search operations. Unlike a standard B-tree, a B+ tree has these key properties:
 *
 * 1. All keys are stored in the leaf nodes (internal nodes only store routing keys)
 * 2. Leaf nodes are linked together in a linked list (for efficient range queries)
 * 3. Every leaf node contains between ceil(t/2) and t keys (except during transient states)
 * 4. Every internal node contains between ceil(t/2) and t children (except root)
 * 5. All leaf nodes are at the same level (perfect balance)
 *
 * VISUALIZATION OF A B+ TREE (t=3):
 *                  [10, 20]
 *                 /    |    \
 *                /     |     \
 *         [3, 7]     [15]    [25, 30]
 *           |         |        |
 * [3, 7] <-> [10, 15] <-> [20, 25, 30]  (leaf nodes with links)
 *
 * Note: In the leaf level, all keys are present and linked in order.
 *
 * ADVANTAGES OVER B-TREES:
 * 1. Better for range queries due to leaf node links
 * 2. More efficient disk I/O for large datasets
 * 3. Higher fanout since internal nodes only store routing keys
 * 4. More predictable performance (all leaf nodes are at same level)
 * 5. Better for sequential access and range queries
 * 6. Simpler rebalancing operations in some cases
 *
 * APPLICATIONS:
 * - Database indexing (most RDBMS use B+ trees as their primary indexing method)
 * - File systems (like NTFS, ext4, etc.)
 * - Range queries in databases
 * - Efficient ordered data storage and retrieval
 *
 * @param <K> The key type (must be comparable)
 * @param <V> The value type
 */
public class BPlusTree<K extends Comparable<K>, V> {

    /** Maximum number of children for internal nodes and keys for leaf nodes */
    private final int t;

    /** Root node of the B+ tree */
    private Node root;

    /** Height of the tree */
    private int height;

    /** First leaf node for sequential access */
    private LeafNode firstLeaf;

    /**
     * Constructs a new empty B+ tree with the specified order.
     *
     * Time Complexity: O(1)
     *
     * @param t the order of the B+ tree
     */
    public BPlusTree(int t) {
        if (t < 3) {
            throw new IllegalArgumentException("Order must be at least 3");
        }
        this.t = t;
        this.root = new LeafNode();
        this.height = 1;
        this.firstLeaf = (LeafNode) root;
    }

    /**
     * Searches for a key in the B+ tree.
     *
     * Time Complexity: O(log n) where n is the total number of keys in the tree
     *
     * @param key the key to search for
     * @return the value associated with the key, or null if not found
     */
    public V search(K key) {
        return root.search(key);
    }

/**
 * Inserts a key-value pair into the B+ tree.
 *
 * Time Complexity: O(log n) where n is the total number of keys in the tree
 *
 * EXAMPLE:
 * Inserting (12, "value") into this B+ tree:
 *                  [10, 20]
 *                 /    |    \
 *         [3, 7]     [15]    [25, 30]
 *           |         |        |
 * [3, 7] <-> [10, 15] <-> [20, 25, 30]
 *
 * Step 1: Start at root and traverse to leaf node containing [10, 15]
 * Step 2: Insert key 12 into leaf node, now [10, 12, 15]
 * Step 3: No split needed, tree remains unchanged structurally
 *
 * @param key the key to insert
 * @param value the value to associate with the key
 */
public void insert(K key, V value) {
    Node newNode = root.insert(key, value);

    // If root was split, create new root
    if (newNode != null) {
        InternalNode newRoot = new InternalNode();

        // First routing key
        K firstKey;
        if (newNode instanceof LeafNode) {
            firstKey = ((LeafNode) newNode).keys.get(0);
        } else {
            firstKey = ((InternalNode) newNode).keys.get(0);
            ((InternalNode) newNode).keys.remove(0);
        }

        newRoot.keys.add(firstKey);
        newRoot.children.add(root);
        newRoot.children.add(newNode);

        root = newRoot;
        height++;
    }

    // Update firstLeaf pointer if needed
    if (firstLeaf.keys.isEmpty() && firstLeaf.next != null) {
        firstLeaf = firstLeaf.next;
        firstLeaf.prev = null;
    }
}

    /**
     * Deletes a key from the B+ tree.
     *
     * Time Complexity: O(log n) where n is the total number of keys in the tree
     *
     * EXAMPLE:
     * Deleting key 15 from this B+ tree:
     *                  [10, 20]
     *                 /    |    \
     *         [3, 7]     [15]    [25, 30]
     *           |         |        |
     * [3, 7] <-> [10, 15] <-> [20, 25, 30]
     *
     * Step 1: Find leaf node containing key 15
     * Step 2: Remove key 15 from leaf, now [10]
     * Step 3: Handle underflow by borrowing or merging
     *
     * @param key the key to delete
     * @return true if the key was found and deleted, false otherwise
     */
    public boolean delete(K key) {
        boolean result = root.delete(key);

        // If root is an internal node with no keys, make its only child the new root
        if (result && root instanceof InternalNode && root.keys.isEmpty() && ((InternalNode) root).children.size() == 1) {
            root = ((InternalNode) root).children.get(0);
            height--;
        }

        // Update firstLeaf pointer if needed
        if (firstLeaf.keys.isEmpty()) {
            firstLeaf = firstLeaf.next;
            if (firstLeaf != null) {
                firstLeaf.prev = null;
            }
        }

        return result;
    }

    /**
     * Returns the height of the B+ tree.
     *
     * Time Complexity: O(1)
     *
     * @return the height of the tree
     */
    public int getHeight() {
        return height;
    }

    /**
     * Performs a range query, returning all key-value pairs with keys
     * between fromKey (inclusive) and toKey (inclusive).
     *
     * Time Complexity: O(log n + m) where n is the total number of keys and
     * m is the number of keys in the range
     *
     * EXAMPLE:
     * Range query from 12 to 25 in this B+ tree:
     *                  [10, 20]
     *                 /    |    \
     *         [3, 7]     [15]    [25, 30]
     *           |         |        |
     * [3, 7] <-> [10, 15] <-> [20, 25, 30]
     *
     * Step 1: Find leaf node containing fromKey (12)
     * Step 2: Traverse linked list of leaf nodes, collecting keys in range
     * Step 3: Stop when reaching toKey (25) or end of list
     *
     * Result: {15: value15, 20: value20, 25: value25}
     *
     * @param fromKey the lower bound of the range (inclusive)
     * @param toKey the upper bound of the range (inclusive)
     * @return a list of key-value pairs in the range
     */
    public List<KeyValuePair<K, V>> rangeQuery(K fromKey, K toKey) {
        List<KeyValuePair<K, V>> result = new ArrayList<>();

        // Find leaf node containing fromKey
        LeafNode leaf = findLeafNode(fromKey);
        if (leaf == null) {
            return result;
        }

        // Traverse leaf nodes and collect values in range
        while (leaf != null) {
            for (int i = 0; i < leaf.keys.size(); i++) {
                K key = leaf.keys.get(i);
                if (key.compareTo(fromKey) >= 0 && key.compareTo(toKey) <= 0) {
                    result.add(new KeyValuePair<>(key, leaf.values.get(i)));
                }
                if (key.compareTo(toKey) > 0) {
                    return result;
                }
            }
            leaf = leaf.next;
        }

        return result;
    }

    /**
     * Finds the leaf node that contains or would contain the given key.
     *
     * Time Complexity: O(log n) where n is the total number of keys
     *
     * @param key the key to search for
     * @return the leaf node containing or that would contain the key
     */
    private LeafNode findLeafNode(K key) {
        Node node = root;
        while (node instanceof InternalNode) {
            InternalNode internalNode = (InternalNode) node;
            int childIndex = 0;
            while (childIndex < internalNode.keys.size() && key.compareTo(internalNode.keys.get(childIndex)) >= 0) {
                childIndex++;
            }
            node = internalNode.children.get(childIndex);
        }
        return (LeafNode) node;
    }

    /**
     * Returns a list of all keys in the B+ tree in ascending order.
     *
     * Time Complexity: O(n) where n is the total number of keys
     *
     * @return a list of all keys in ascending order
     */
    public List<K> getAllKeys() {
        List<K> result = new ArrayList<>();
        LeafNode leaf = firstLeaf;

        while (leaf != null) {
            result.addAll(leaf.keys);
            leaf = leaf.next;
        }

        return result;
    }

    /**
     * Returns a string representation of the B+ tree.
     *
     * Time Complexity: O(n) where n is the total number of keys
     *
     * @return a string representation of the tree
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("B+ Tree (t=").append(t).append(", height=").append(height).append("):\n");

        // Print tree structure
        toString(root, 0, sb);

        // Print leaf node links
        sb.append("\nLeaf Nodes: ");
        LeafNode leaf = firstLeaf;
        while (leaf != null) {
            sb.append(leaf.keys).append(" → ");
            leaf = leaf.next;
        }
        sb.append("null");

        return sb.toString();
    }

    /**
     * Helper method to recursively build a string representation of the tree.
     *
     * Time Complexity: O(n) where n is the total number of keys
     *
     * @param node the current node
     * @param level the current level in the tree
     * @param sb the StringBuilder to append to
     */
    private void toString(Node node, int level, StringBuilder sb) {
        // Add line for current level
        sb.append(String.format("Level %d: ", level));

        // Print keys for this node
        sb.append("[");
        for (int i = 0; i < node.keys.size(); i++) {
            sb.append(node.keys.get(i));
            if (i < node.keys.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        // Print an indicator if this is a leaf
        if (node instanceof LeafNode) {
            sb.append(" (leaf)");
        }
        sb.append("\n");

        // Print children for internal nodes
        if (node instanceof InternalNode) {
            InternalNode internalNode = (InternalNode) node;
            for (int i = 0; i < internalNode.children.size(); i++) {
                toString(internalNode.children.get(i), level + 1, sb);
            }
        }
    }

    /**
     * Checks if the B+ tree is empty.
     *
     * Time Complexity: O(1)
     *
     * @return true if the tree contains no keys, false otherwise
     */
    public boolean isEmpty() {
        return firstLeaf.keys.isEmpty() && firstLeaf.next == null;
    }

    /**
     * Returns the total number of keys in the B+ tree.
     *
     * Time Complexity: O(n) where n is the total number of keys
     *
     * @return the number of keys
     */
    public int size() {
        int count = 0;
        LeafNode leaf = firstLeaf;

        while (leaf != null) {
            count += leaf.keys.size();
            leaf = leaf.next;
        }

        return count;
    }

    /**
     * Verifies that the B+ tree maintains its structural integrity.
     * This is useful for testing and debugging.
     *
     * Time Complexity: O(n) where n is the total number of keys
     *
     * @return true if the tree structure is valid, false otherwise
     */
    public boolean isValid() {
        // Check if tree is empty
        if (root == null) {
            return true;
        }

        // Check if height is consistent
        int leafLevel = computeHeight(root);
        if (leafLevel != height) {
            return false;
        }

        // Verify node properties recursively
        return isValidNode(root, null, null, leafLevel, 1);
    }

    /**
     * Computes the height of the tree by following the leftmost path to a leaf.
     *
     * Time Complexity: O(log n)
     *
     * @param node the root node
     * @return the height of the tree
     */
    private int computeHeight(Node node) {
        int h = 1;
        while (node instanceof InternalNode) {
            // Make sure the node has children before traversing down
            if (((InternalNode) node).children.isEmpty()) {
                break;
            }
            node = ((InternalNode) node).children.get(0);
            h++;
        }
        return h;
    }

    /**
     * Recursively verifies that a node and its subtree maintain the B+ tree properties.
     *
     * Time Complexity: O(n) where n is the total number of keys in the subtree
     *
     * @param node the current node
     * @param lowerBound the lower bound for keys in this node (can be null)
     * @param upperBound the upper bound for keys in this node (can be null)
     * @param expectedLeafLevel the expected level for all leaf nodes
     * @param currentLevel the current level in the tree
     * @return true if the subtree is valid, false otherwise
     */
    private boolean isValidNode(Node node, K lowerBound, K upperBound, int expectedLeafLevel, int currentLevel) {
        // Skip validation for empty nodes
        if (node.keys.isEmpty() && !(node instanceof LeafNode && node == firstLeaf)) {
            return false;
        }

        // Check if node has the right number of keys (except root)
        if (node != root && node.keys.size() < (t + 1) / 2 - 1) {
            return false;
        }

        // Check key ordering within the node
        for (int i = 1; i < node.keys.size(); i++) {
            if (node.keys.get(i - 1).compareTo(node.keys.get(i)) >= 0) {
                return false;
            }
        }

        // Check bounds constraints
        if (lowerBound != null && node.keys.size() > 0 && node.keys.get(0).compareTo(lowerBound) < 0) {
            return false;
        }
        if (upperBound != null && node.keys.size() > 0 && node.keys.get(node.keys.size() - 1).compareTo(upperBound) > 0) {
            return false;
        }

        // For leaf nodes, check if they are at the expected level
        if (node instanceof LeafNode) {
            return currentLevel == expectedLeafLevel;
        }

        // For internal nodes, recursively check children
        InternalNode internalNode = (InternalNode) node;

        // Skip validation for internal nodes with no children
        if (internalNode.children.isEmpty()) {
            return false;
        }

        // Check if number of children is correct
        if (internalNode.children.size() != internalNode.keys.size() + 1) {
            return false;
        }

        // Recursively check each child with appropriate bounds
        for (int i = 0; i < internalNode.children.size(); i++) {
            K childLowerBound = (i == 0) ? lowerBound : internalNode.keys.get(i - 1);
            K childUpperBound = (i == internalNode.keys.size()) ? upperBound : internalNode.keys.get(i);

            if (!isValidNode(internalNode.children.get(i), childLowerBound, childUpperBound,
                    expectedLeafLevel, currentLevel + 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper class to represent key-value pairs in range query results.
     *
     * @param <K> The key type
     * @param <V> The value type
     */
    public static class KeyValuePair<K, V> {
        private final K key;
        private final V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + ": " + value;
        }
    }

    /**
     * Base class for both internal and leaf nodes.
     */
    private abstract class Node {
        /** List of keys stored in this node */
        protected List<K> keys;

        /**
         * Constructs a new empty node.
         *
         * Time Complexity: O(1)
         */
        public Node() {
            this.keys = new ArrayList<>();
        }

        /**
         * Checks if this node is full.
         *
         * Time Complexity: O(1)
         *
         * @return true if the node is full, false otherwise
         */
        public boolean isFull() {
            return keys.size() >= t;
        }

        /**
         * Checks if this node is underflowing (has fewer than the minimum required keys).
         *
         * Time Complexity: O(1)
         *
         * @return true if the node is underflowing, false otherwise
         */
        public boolean isUnderflow() {
            return keys.size() < (t + 1) / 2;
        }

        /**
         * Abstract method to search for a key in this node or its subtree.
         *
         * @param key the key to search for
         * @return the value associated with the key, or null if not found
         */
        public abstract V search(K key);

        /**
         * Abstract method to insert a key-value pair into this node or its subtree.
         *
         * @param key the key to insert
         * @param value the value to associate with the key
         * @return a new node if this node was split, otherwise null
         */
        public abstract Node insert(K key, V value);

        /**
         * Abstract method to delete a key from this node or its subtree.
         *
         * @param key the key to delete
         * @return true if the key was found and deleted, false otherwise
         */
        public abstract boolean delete(K key);
    }

    /**
     * Internal node of the B+ tree. Contains routing keys and child pointers.
     */
    private class InternalNode extends Node {
        /** List of child node pointers */
        private List<Node> children;

        /**
         * Constructs a new empty internal node.
         *
         * Time Complexity: O(1)
         */
        public InternalNode() {
            super();
            this.children = new ArrayList<>();
        }

        /**
         * Gets the index of the child node that should contain the given key.
         *
         * Time Complexity: O(log t) using binary search, but practically O(t) with linear search
         *
         * @param key the key to locate
         * @return the index of the appropriate child
         */
        private int getChildIndex(K key) {
            int idx = 0;
            while (idx < keys.size() && key.compareTo(keys.get(idx)) >= 0) {
                idx++;
            }
            return idx;
        }

        /**
         * Searches for a key in this node's subtree.
         *
         * Time Complexity: O(log n) where n is the total number of keys in the tree
         *
         * EXAMPLE:
         * Searching for key 17 in this subtree:
         *                  [10, 20]
         *                 /    |    \
         *         [3, 7]     [15]    [25, 30]
         *
         * Step 1: Compare 17 with routing keys [10, 20]
         * Step 2: 10 < 17 < 20, so follow middle child
         * Step 3: Search in the child node with routing key [15]
         *
         * @param key the key to search for
         * @return the value associated with the key, or null if not found
         */
        @Override
        public V search(K key) {
            return children.get(getChildIndex(key)).search(key);
        }

        /**
         * Inserts a key-value pair into this node's subtree.
         *
         * Time Complexity: O(log n) where n is the total number of keys in the tree
         *
         * EXAMPLE:
         * Inserting (12, value) into this subtree:
         *                  [10, 20]
         *                 /    |    \
         *         [3, 7]     [15]    [25, 30]
         *
         * Step 1: Find appropriate child: 10 < 12 < 20, so middle child with [15]
         * Step 2: Recursively insert into that child
         * Step 3: If child splits, handle the split by adding new routing key and child pointer
         *
         * @param key the key to insert
         * @param value the value to associate with the key
         * @return a new node if this node was split, otherwise null
         */
        @Override
        public Node insert(K key, V value) {
            int childIndex = getChildIndex(key);
            Node childNode = children.get(childIndex);
            Node newChildNode = childNode.insert(key, value);

            // If child didn't split, we're done
            if (newChildNode == null) {
                return null;
            }

            // Handle child split
            if (newChildNode instanceof LeafNode) {
                K splitKey = ((LeafNode) newChildNode).keys.get(0);
                insertKeyAndChild(splitKey, newChildNode, childIndex + 1);
            } else {
                K splitKey = ((InternalNode) newChildNode).keys.get(0);
                insertKeyAndChild(splitKey, newChildNode, childIndex + 1);
                // Remove the first key from new internal node as it's now in parent
                ((InternalNode) newChildNode).keys.remove(0);
            }

            // Check if this node needs to split
            if (isFull()) {
                return split();
            }

            return null;
        }

        /**
         * Inserts a key and child pointer at the given positions.
         *
         * Time Complexity: O(t) due to list insertions
         *
         * @param key the key to insert
         * @param child the child node to insert
         * @param childIndex the index at which to insert the child
         */
        private void insertKeyAndChild(K key, Node child, int childIndex) {
            // Find the position to insert the key
            int keyIndex = childIndex - 1;
            if (keyIndex < 0) keyIndex = 0;
            if (keyIndex > keys.size()) keyIndex = keys.size();

            // Insert the key and child
            keys.add(keyIndex, key);
            children.add(childIndex, child);
        }

        /**
         * Splits this node into two nodes.
         *
         * Time Complexity: O(t)
         *
         * EXAMPLE:
         * Splitting this full internal node:
         * [10, 20, 30, 40, 50] with 6 children
         *
         * Step 1: Create new internal node
         * Step 2: Move half of keys and children to new node
         * Step 3: Return the new node to be inserted in parent
         *
         * Result:
         * Original node: [10, 20]
         * New node: [40, 50]
         * Middle key 30 moves up to parent
         *
         * @return the new node created from the split
         */
        private InternalNode split() {
            int mid = keys.size() / 2;
            InternalNode newNode = new InternalNode();

            // Copy the right half of keys to the new node (excluding the middle key)
            if (mid + 1 < keys.size()) {
                newNode.keys = new ArrayList<>(keys.subList(mid + 1, keys.size()));
            } else {
                newNode.keys = new ArrayList<>();
            }

            // Copy the right half of children to the new node
            if (mid + 1 < children.size()) {
                newNode.children = new ArrayList<>(children.subList(mid + 1, children.size()));
            } else {
                newNode.children = new ArrayList<>();
            }

            // Add the middle key as the first key of the new node (it will move up to the parent)
            if (mid < keys.size()) {
                K middleKey = keys.get(mid);
                newNode.keys.add(0, middleKey);
            }

            // Keep left half of keys in this node
            if (mid > 0) {
                keys = new ArrayList<>(keys.subList(0, mid));
            } else {
                keys.clear();
            }

            // Keep left half of children in this node
            if (mid + 1 < children.size()) {
                children = new ArrayList<>(children.subList(0, mid + 1));
            }

            return newNode;
        }

        /**
         * Deletes a key from this node's subtree.
         *
         * Time Complexity: O(log n) where n is the total number of keys in the tree
         *
         * EXAMPLE:
         * Deleting key 15 from this subtree:
         *                  [10, 20]
         *                 /    |    \
         *         [3, 7]     [15]    [25, 30]
         *
         * Step 1: Find child that contains key: 10 < 15 < 20, so middle child
         * Step 2: Recursively delete from that child
         * Step 3: If child underflows, handle by borrowing from sibling or merging
         *
         * @param key the key to delete
         * @return true if the key was found and deleted, false otherwise
         */
        @Override
        public boolean delete(K key) {
            int childIndex = getChildIndex(key);

            // Make sure childIndex is within bounds
            if (childIndex >= children.size()) {
                return false;
            }

            Node childNode = children.get(childIndex);
            boolean deleted = childNode.delete(key);

            // If key wasn't found, we're done
            if (!deleted) {
                return false;
            }

            // Check if child underflowed
            if (childNode.isUnderflow()) {
                handleChildUnderflow(childIndex);
            }

            // Update routing keys if necessary
            // Only update if childIndex > 0 (has a key before it), the child is a leaf node,
            // and the child has at least one key
            if (childIndex > 0 && childIndex - 1 < keys.size() &&
                    childNode instanceof LeafNode && !((LeafNode) childNode).keys.isEmpty()) {
                keys.set(childIndex - 1, ((LeafNode) childNode).keys.get(0));
            }

            return true;
        }

        /**
         * Handles the case when a child node underflows after deletion.
         *
         * Time Complexity: O(t)
         *
         * @param childIndex the index of the underflowing child
         */
        private void handleChildUnderflow(int childIndex) {
            Node child = children.get(childIndex);

            // Try to borrow from left sibling
            if (childIndex > 0) {
                Node leftSibling = children.get(childIndex - 1);
                if (leftSibling.keys.size() > (t + 1) / 2) {
                    borrowFromLeftSibling(childIndex, child, leftSibling);
                    return;
                }
            }

            // Try to borrow from right sibling
            if (childIndex < children.size() - 1) {
                Node rightSibling = children.get(childIndex + 1);
                if (rightSibling.keys.size() > (t + 1) / 2) {
                    borrowFromRightSibling(childIndex, child, rightSibling);
                    return;
                }
            }

            // Merge with a sibling
            if (childIndex > 0) {
                // Merge with left sibling
                mergeWithLeftSibling(childIndex);
            } else if (childIndex < children.size() - 1) {
                // Merge with right sibling
                mergeWithRightSibling(childIndex);
            }
        }

        /**
         * Borrows a key and child from the left sibling.
         *
         * Time Complexity: O(t)
         *
         * @param childIndex the index of the child
         * @param child the child node
         * @param leftSibling the left sibling node
         */
        private void borrowFromLeftSibling(int childIndex, Node child, Node leftSibling) {
            // Implementation depends on whether child is internal or leaf
            if (child instanceof InternalNode) {
                InternalNode internalChild = (InternalNode) child;
                InternalNode internalLeftSibling = (InternalNode) leftSibling;

                // Make sure left sibling has keys and children
                if (internalLeftSibling.keys.isEmpty() || internalLeftSibling.children.isEmpty()) {
                    return;
                }

                // Move parent key down to child
                internalChild.keys.add(0, keys.get(childIndex - 1));

                // Move last child of left sibling to first child of current child
                internalChild.children.add(0, internalLeftSibling.children.remove(internalLeftSibling.children.size() - 1));

                // Move last key of left sibling up to parent
                keys.set(childIndex - 1, internalLeftSibling.keys.remove(internalLeftSibling.keys.size() - 1));
            } else {
                LeafNode leafChild = (LeafNode) child;
                LeafNode leafLeftSibling = (LeafNode) leftSibling;

                // Make sure left sibling has keys and values
                if (leafLeftSibling.keys.isEmpty() || leafLeftSibling.values.isEmpty()) {
                    return;
                }

                // Move last key-value pair from left sibling to child
                K lastKey = leafLeftSibling.keys.remove(leafLeftSibling.keys.size() - 1);
                V lastValue = leafLeftSibling.values.remove(leafLeftSibling.values.size() - 1);

                leafChild.keys.add(0, lastKey);
                leafChild.values.add(0, lastValue);

                // Update parent routing key
                keys.set(childIndex - 1, leafChild.keys.get(0));
            }
        }

        /**
         * Borrows a key and child from the right sibling.
         *
         * Time Complexity: O(t)
         *
         * @param childIndex the index of the child
         * @param child the child node
         * @param rightSibling the right sibling node
         */
        private void borrowFromRightSibling(int childIndex, Node child, Node rightSibling) {
            // Implementation depends on whether child is internal or leaf
            if (child instanceof InternalNode) {
                InternalNode internalChild = (InternalNode) child;
                InternalNode internalRightSibling = (InternalNode) rightSibling;

                // Make sure right sibling has keys and children
                if (internalRightSibling.keys.isEmpty() || internalRightSibling.children.isEmpty()) {
                    return;
                }

                // Move parent key down to child
                internalChild.keys.add(keys.get(childIndex));

                // Move first child of right sibling to last child of current child
                internalChild.children.add(internalRightSibling.children.remove(0));

                // Move first key of right sibling up to parent
                keys.set(childIndex, internalRightSibling.keys.remove(0));
            } else {
                LeafNode leafChild = (LeafNode) child;
                LeafNode leafRightSibling = (LeafNode) rightSibling;

                // Make sure right sibling has keys and values
                if (leafRightSibling.keys.isEmpty() || leafRightSibling.values.isEmpty()) {
                    return;
                }

                // Move first key-value pair from right sibling to child
                K firstKey = leafRightSibling.keys.remove(0);
                V firstValue = leafRightSibling.values.remove(0);

                leafChild.keys.add(firstKey);
                leafChild.values.add(firstValue);

                // Update parent routing key
                keys.set(childIndex, leafRightSibling.keys.get(0));
            }
        }

        /**
         * Merges a child with its left sibling.
         *
         * Time Complexity: O(t)
         *
         * EXAMPLE:
         * Merging child at index 1 with its left sibling in this node:
         *                  [10, 20]
         *                 /    |    \
         *         [3, 7]     [15]    [25, 30]
         *
         * Result:
         *                  [20]
         *                 /    \
         *         [3, 7, 10, 15]    [25, 30]
         *
         * @param childIndex the index of the child to merge
         */
        private void mergeWithLeftSibling(int childIndex) {
            // Make sure indices are within bounds
            if (childIndex <= 0 || childIndex >= children.size() || childIndex - 1 >= keys.size()) {
                return;
            }

            Node child = children.get(childIndex);
            Node leftSibling = children.get(childIndex - 1);
            K parentKey = keys.remove(childIndex - 1);

            if (child instanceof InternalNode) {
                InternalNode internalChild = (InternalNode) child;
                InternalNode internalLeftSibling = (InternalNode) leftSibling;

                // Add parent key to left sibling
                internalLeftSibling.keys.add(parentKey);

                // Add all keys and children from child to left sibling
                internalLeftSibling.keys.addAll(internalChild.keys);
                internalLeftSibling.children.addAll(internalChild.children);
            } else {
                LeafNode leafChild = (LeafNode) child;
                LeafNode leafLeftSibling = (LeafNode) leftSibling;

                // Add all key-value pairs from child to left sibling
                leafLeftSibling.keys.addAll(leafChild.keys);
                leafLeftSibling.values.addAll(leafChild.values);

                // Update leaf node links
                leafLeftSibling.next = leafChild.next;
                if (leafChild.next != null) {
                    leafChild.next.prev = leafLeftSibling;
                }
            }

            // Remove the child node
            children.remove(childIndex);
        }

        /**
         * Merges a child with its right sibling.
         *
         * Time Complexity: O(t)
         *
         * @param childIndex the index of the child to merge
         */
        private void mergeWithRightSibling(int childIndex) {
            // Make sure indices are within bounds
            if (childIndex >= children.size() - 1 || childIndex >= keys.size()) {
                return;
            }

            Node child = children.get(childIndex);
            Node rightSibling = children.get(childIndex + 1);
            K parentKey = keys.remove(childIndex);

            if (child instanceof InternalNode) {
                InternalNode internalChild = (InternalNode) child;
                InternalNode internalRightSibling = (InternalNode) rightSibling;

                // Add parent key to child
                internalChild.keys.add(parentKey);

                // Add all keys and children from right sibling to child
                internalChild.keys.addAll(internalRightSibling.keys);
                internalChild.children.addAll(internalRightSibling.children);
            } else {
                LeafNode leafChild = (LeafNode) child;
                LeafNode leafRightSibling = (LeafNode) rightSibling;

                // Add all key-value pairs from right sibling to child
                leafChild.keys.addAll(leafRightSibling.keys);
                leafChild.values.addAll(leafRightSibling.values);

                // Update leaf node links
                leafChild.next = leafRightSibling.next;
                if (leafRightSibling.next != null) {
                    leafRightSibling.next.prev = leafChild;
                }
            }

            // Remove the right sibling
            children.remove(childIndex + 1);
        }
    }

    /**
     * Leaf node of the B+ tree. Contains keys and associated values.
     */
    private class LeafNode extends Node {
        /** List of values associated with the keys */
        private List<V> values;

        /** Pointer to the next leaf node (for range queries) */
        private LeafNode next;

        /** Pointer to the previous leaf node */
        private LeafNode prev;

        /**
         * Constructs a new empty leaf node.
         *
         * Time Complexity: O(1)
         */
        public LeafNode() {
            super();
            this.values = new ArrayList<>();
            this.next = null;
            this.prev = null;
        }

        /**
         * Gets the index of the given key in this leaf node.
         *
         * Time Complexity: O(log t) using binary search, but practically O(t) with linear search
         *
         * @param key the key to locate
         * @return the index of the key, or -1 if not found
         */
        private int getKeyIndex(K key) {
            for (int i = 0; i < keys.size(); i++) {
                if (keys.get(i).compareTo(key) == 0) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * Searches for a key in this leaf node.
         *
         * Time Complexity: O(t)
         *
         * EXAMPLE:
         * Searching for key 15 in this leaf node:
         * [10, 15, 20]
         *
         * Step 1: Scan through keys [10, 15, 20]
         * Step 2: Find 15 at index 1, return associated value
         *
         * @param key the key to search for
         * @return the value associated with the key, or null if not found
         */
        @Override
        public V search(K key) {
            int index = getKeyIndex(key);
            if (index != -1) {
                return values.get(index);
            }
            return null;
        }

        /**
         * Inserts a key-value pair into this leaf node.
         *
         * Time Complexity: O(t)
         *
         * EXAMPLE:
         * Inserting (12, value) into this leaf node:
         * [10, 15, 20]
         *
         * Step 1: Find position to insert (between 10 and 15)
         * Step 2: Insert key and value at index 1
         *
         * Result: [10, 12, 15, 20]
         *
         * If node becomes full and splits:
         * Original node: [10, 12]
         * New node: [15, 20]
         *
         * @param key the key to insert
         * @param value the value to associate with the key
         * @return a new node if this node was split, otherwise null
         */
        @Override
        public Node insert(K key, V value) {
            int index = 0;
            while (index < keys.size() && key.compareTo(keys.get(index)) > 0) {
                index++;
            }

            // If key already exists, update value
            if (index < keys.size() && key.compareTo(keys.get(index)) == 0) {
                values.set(index, value);
                return null;
            }

            // Insert key and value
            keys.add(index, key);
            values.add(index, value);

            // Check if node needs to split
            if (isFull()) {
                return split();
            }

            return null;
        }

        /**
         * Splits this leaf node into two nodes.
         *
         * Time Complexity: O(t)
         *
         * EXAMPLE:
         * Splitting this full leaf node:
         * [10, 20, 30, 40, 50]
         *
         * Step 1: Create new leaf node
         * Step 2: Move right half of keys and values to new node
         * Step 3: Update next/prev pointers
         *
         * Result:
         * Original node: [10, 20]
         * New node: [30, 40, 50]
         *
         * @return the new node created from the split
         */
        private LeafNode split() {
            int mid = (keys.size() + 1) / 2;
            LeafNode newNode = new LeafNode();

            // Copy the right half of keys and values to the new node
            if (mid < keys.size()) {
                newNode.keys = new ArrayList<>(keys.subList(mid, keys.size()));
                newNode.values = new ArrayList<>(values.subList(mid, values.size()));
            } else {
                newNode.keys = new ArrayList<>();
                newNode.values = new ArrayList<>();
            }

            // Keep the left half of keys and values in this node
            if (mid > 0) {
                keys = new ArrayList<>(keys.subList(0, mid));
                values = new ArrayList<>(values.subList(0, mid));
            }

            // Update leaf node links
            newNode.next = this.next;
            if (this.next != null) {
                this.next.prev = newNode;
            }
            this.next = newNode;
            newNode.prev = this;

            return newNode;
        }

        /**
         * Deletes a key from this leaf node.
         *
         * Time Complexity: O(t)
         *
         * EXAMPLE:
         * Deleting key 15 from this leaf node:
         * [10, 15, 20]
         *
         * Step 1: Find key 15 at index 1
         * Step 2: Remove key and associated value
         *
         * Result: [10, 20]
         *
         * @param key the key to delete
         * @return true if the key was found and deleted, false otherwise
         */
        @Override
        public boolean delete(K key) {
            int index = getKeyIndex(key);
            if (index == -1) {
                return false;
            }

            // Remove key and value
            keys.remove(index);
            values.remove(index);

            return true;
        }
    }
}