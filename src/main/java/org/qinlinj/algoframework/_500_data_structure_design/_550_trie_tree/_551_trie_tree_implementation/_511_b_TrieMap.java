package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._551_trie_tree_implementation;

import java.util.*;

/**
 * TRIEMAP - COMPLETE IMPLEMENTATION
 * <p>
 * Key Points:
 * - TrieMap is a key-value data structure optimized for string keys
 * - Time complexity for most operations is O(k) where k is key length
 * - In Trie, "branches" store the characters, "nodes" store the values
 * - Core operations:
 * 1. put(key, value): Insert or update a key-value pair
 * 2. get(key): Retrieve value for a given key
 * 3. remove(key): Delete a key and its value
 * 4. containsKey(key): Check if a key exists
 * - Prefix operations:
 * 1. hasKeyWithPrefix(prefix): Check if any key starts with prefix
 * 2. keysWithPrefix(prefix): Get all keys that start with prefix
 * 3. shortestPrefixOf(query): Find shortest key that is a prefix of query
 * 4. longestPrefixOf(query): Find longest key that is a prefix of query
 * - Pattern matching:
 * 1. keysWithPattern(pattern): Find keys matching pattern ('.' = any char)
 * 2. hasKeyWithPattern(pattern): Check if any key matches pattern
 */
public class _511_b_TrieMap<V> {
    // ASCII code count - defines the size of the children array
    private static final int R = 256;

    // Number of key-value pairs in the map
    private int size = 0;

    // Root node of the Trie
    private TrieNode<V> root = null;

    /**
     * Basic Operations: Add, Update
     */

    // Add or update a key-value pair
    public void put(String key, V val) {
        if (!containsKey(key)) {
            // New key-value pair
            size++;
        }
        // Use helper function and store its return value
        root = put(root, key, val, 0);
    }

    // Helper method to recursively insert key[i..] into the Trie rooted at node
    private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
        if (node == null) {
            // If branch doesn't exist, create it
            node = new TrieNode<>();
        }
        if (i == key.length()) {
            // Key path is fully inserted, store value in the node
            node.val = val;
            return node;
        }
        char c = key.charAt(i);
        // Recursively insert into child node and update the reference
        node.children[c] = put(node.children[c], key, val, i + 1);
        return node;
    }

    /**
     * Basic Operations: Delete
     */

    // Remove a key and its value from the map
    public void remove(String key) {
        if (!containsKey(key)) {
            return;
        }
        // Use helper function and store its return value
        root = remove(root, key, 0);
        size--;
    }

    // Helper method to recursively remove key[i..] from the Trie rooted at node
    private TrieNode<V> remove(TrieNode<V> node, String key, int i) {
        if (node == null) {
            return null;
        }
        if (i == key.length()) {
            // Found the node for key, clear its value
            node.val = null;
        } else {
            char c = key.charAt(i);
            // Recursively remove from child node and update the reference
            node.children[c] = remove(node.children[c], key, i + 1);
        }

        // Post-order position: Clean up nodes if necessary
        if (node.val != null) {
            // If node has a value, it should be kept
            return node;
        }

        // Check if node has any children
        for (int c = 0; c < R; c++) {
            if (node.children[c] != null) {
                // If node has any child, it should be kept
                return node;
            }
        }
        // Node has no value and no children, it can be removed
        return null;
    }

    /**
     * Basic Operations: Lookup
     */

    // Get value for a key, return null if key doesn't exist
    public V get(String key) {
        // Start search from root
        TrieNode<V> x = getNode(root, key);
        if (x == null || x.val == null) {
            // Key doesn't exist or node has no value
            return null;
        }
        return x.val;
    }

    // Check if key exists in the map
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    /**
     * Prefix Operations
     */

    // Check if any key starts with the given prefix
    public boolean hasKeyWithPrefix(String prefix) {
        // If we can find a node for the prefix, it exists
        return getNode(root, prefix) != null;
    }

    // Find the shortest key that is a prefix of query
    public String shortestPrefixOf(String query) {
        TrieNode<V> p = root;
        // Search from root for query
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                // Can't continue search
                return "";
            }
            if (p.val != null) {
                // Found a key that is a prefix of query
                return query.substring(0, i);
            }
            // Continue search
            char c = query.charAt(i);
            p = p.children[c];
        }

        if (p != null && p.val != null) {
            // If query itself is a key
            return query;
        }
        return "";
    }

    // Find the longest key that is a prefix of query
    public String longestPrefixOf(String query) {
        TrieNode<V> p = root;
        // Track the maximum prefix length
        int maxLen = 0;

        // Search from root for query
        for (int i = 0; i < query.length(); i++) {
            if (p == null) {
                // Can't continue search
                break;
            }
            if (p.val != null) {
                // Found a key that is a prefix of query, update max length
                maxLen = i;
            }
            // Continue search
            char c = query.charAt(i);
            p = p.children[c];
        }

        if (p != null && p.val != null) {
            // If query itself is a key
            return query;
        }
        return query.substring(0, maxLen);
    }

    // Get all keys that start with the given prefix
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new LinkedList<>();
        // Find the node that matches the prefix
        TrieNode<V> x = getNode(root, prefix);
        if (x == null) {
            return res;
        }
        // DFS traverse the Trie rooted at x
        traverse(x, new StringBuilder(prefix), res);
        return res;
    }

    // Helper method to traverse the Trie and find all keys
    private void traverse(TrieNode<V> node, StringBuilder path, List<String> res) {
        if (node == null) {
            // Reached leaf node
            return;
        }

        if (node.val != null) {
            // Found a key, add to result list
            res.add(path.toString());
        }

        // Backtracking algorithm framework
        for (char c = 0; c < R; c++) {
            // Make choice
            path.append(c);
            traverse(node.children[c], path, res);
            // Undo choice
            path.deleteCharAt(path.length() - 1);
        }
    }

    /**
     * Pattern Matching Operations
     */

    // Find all keys that match the pattern ('.' matches any character)
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new LinkedList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    }

    // Helper method to find keys matching pattern[i..]
    private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
        if (node == null) {
            // Branch doesn't exist, pattern match fails
            return;
        }
        if (i == pattern.length()) {
            // Pattern is fully matched
            if (node.val != null) {
                // If node has a value, we found a matching key
                res.add(path.toString());
            }
            return;
        }
        char c = pattern.charAt(i);

        if (c == '.') {
            // Wildcard, can match any character
            // Multi-way tree (backtracking) traversal framework
            for (char j = 0; j < R; j++) {
                path.append(j);
                traverse(node.children[j], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            // Regular character
            path.append(c);
            traverse(node.children[c], path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    // Check if any key matches the pattern
    public boolean hasKeyWithPattern(String pattern) {
        // Start matching pattern[0..] from root
        return hasKeyWithPattern(root, pattern, 0);
    }

    // Helper method to check if any key matches pattern[i..]
    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i) {
        if (node == null) {
            // Branch doesn't exist, match fails
            return false;
        }
        if (i == pattern.length()) {
            // Pattern is fully matched, check if node has a value
            return node.val != null;
        }
        char c = pattern.charAt(i);
        // No wildcard
        if (c != '.') {
            // Match next character
            return hasKeyWithPattern(node.children[c], pattern, i + 1);
        }
        // Wildcard
        for (int j = 0; j < R; j++) {
            // Try all possible characters, return true if any matches
            if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                return true;
            }
        }
        // No match
        return false;
    }

    /**
     * Helper Methods
     */

    // Find the node for a key, return null if not found
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        // Search from node for key
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                // Can't continue search
                return null;
            }
            // Continue search
            char c = key.charAt(i);
            p = p.children[c];
        }
        return p;
    }

    // Get number of key-value pairs in the map
    public int size() {
        return size;
    }

    // Inner class to represent a node in the Trie
    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
    }
}