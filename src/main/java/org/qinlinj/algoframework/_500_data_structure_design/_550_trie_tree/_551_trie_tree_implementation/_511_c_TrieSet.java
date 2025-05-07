package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._551_trie_tree_implementation;

import java.util.*;

/**
 * TRIESET - SET IMPLEMENTATION USING TRIEMAP
 * <p>
 * Key Points:
 * - TrieSet is a set data structure optimized for string elements
 * - Implemented as a wrapper around TrieMap where values are just placeholders
 * - Inherits all the prefix-based operations from TrieMap
 * - Common operations:
 * 1. add(key): Add an element to the set
 * 2. remove(key): Remove an element from the set
 * 3. contains(key): Check if an element exists in the set
 * - Prefix operations:
 * 1. hasKeyWithPrefix(prefix): Check if any element starts with prefix
 * 2. keysWithPrefix(prefix): Get all elements that start with prefix
 * 3. shortestPrefixOf(query): Find shortest element that is a prefix of query
 * 4. longestPrefixOf(query): Find longest element that is a prefix of query
 * - Pattern matching:
 * 1. keysWithPattern(pattern): Find elements matching pattern ('.' = any char)
 * 2. hasKeyWithPattern(pattern): Check if any element matches pattern
 */
public class _511_c_TrieSet {
    // Underlying TrieMap, keys are the elements of the set,
    // values are just placeholders (Object type following Java standard library convention)
    private final _511_b_TrieMap<Object> map = new _511_b_TrieMap<>();

    /**
     * Basic Operations: Add
     */

    // Add an element to the set
    public void add(String key) {
        map.put(key, new Object());
    }

    /**
     * Basic Operations: Remove
     */

    // Remove an element from the set
    public void remove(String key) {
        map.remove(key);
    }

    /**
     * Basic Operations: Query
     */

    // Check if an element exists in the set
    public boolean contains(String key) {
        return map.containsKey(key);
    }

    /**
     * Prefix Operations
     */

    // Find the shortest element that is a prefix of query
    public String shortestPrefixOf(String query) {
        return map.shortestPrefixOf(query);
    }

    // Find the longest element that is a prefix of query
    public String longestPrefixOf(String query) {
        return map.longestPrefixOf(query);
    }

    // Get all elements that start with the given prefix
    public List<String> keysWithPrefix(String prefix) {
        return map.keysWithPrefix(prefix);
    }

    // Check if any element starts with the given prefix
    public boolean hasKeyWithPrefix(String prefix) {
        return map.hasKeyWithPrefix(prefix);
    }

    /**
     * Pattern Matching Operations
     */

    // Find all elements that match the pattern ('.' matches any character)
    public List<String> keysWithPattern(String pattern) {
        return map.keysWithPattern(pattern);
    }

    // Check if any element matches the pattern
    public boolean hasKeyWithPattern(String pattern) {
        return map.hasKeyWithPattern(pattern);
    }

    /**
     * Size Operation
     */

    // Get number of elements in the set
    public int size() {
        return map.size();
    }
}