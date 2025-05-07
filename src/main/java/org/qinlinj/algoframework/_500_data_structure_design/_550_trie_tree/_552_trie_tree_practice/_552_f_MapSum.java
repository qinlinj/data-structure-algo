package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._552_trie_tree_practice;

import java.util.*;

/**
 * MAP SUM PAIRS
 * <p>
 * Problem Summary:
 * - Design a data structure that supports the following operations:
 * 1. insert(key, val): Insert or replace a key-value pair in the map
 * 2. sum(prefix): Return the sum of all values with keys starting with the given prefix
 * <p>
 * Key Points:
 * - Perfect application of the TrieMap data structure
 * - Efficiently combines key-value storage with prefix-based operations
 * - Time complexity: O(k) for insertion where k is key length
 * - Time complexity: O(n) for sum operation where n is the number of keys with the given prefix
 * - Demonstrates how Trie can be extended to handle numeric values and aggregation operations
 * - The problem specifically requires counting the sum of values, showing Trie's versatility beyond
 * simple string storage
 */
public class _552_f_MapSum {

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        System.out.println("Map Sum Pairs");
        System.out.println("=============");

        // Example from LeetCode problem
        MapSum mapSum = new MapSum();

        System.out.println("Insert 'apple' with value 3");
        mapSum.insert("apple", 3);

        System.out.println("Sum for prefix 'ap': " + mapSum.sum("ap"));  // Returns 3

        System.out.println("Insert 'app' with value 2");
        mapSum.insert("app", 2);

        System.out.println("Sum for prefix 'ap': " + mapSum.sum("ap"));  // Returns 5 (apple + app = 3 + 2)

        // Additional tests
        System.out.println("\nAdditional Tests:");

        System.out.println("Insert 'apricot' with value 5");
        mapSum.insert("apricot", 5);

        System.out.println("Sum for prefix 'ap': " + mapSum.sum("ap"));  // Returns 10 (apple + app + apricot = 3 + 2 + 5)
        System.out.println("Sum for prefix 'apr': " + mapSum.sum("apr")); // Returns 5 (only apricot)

        System.out.println("Insert 'apple' with value 10 (updating value)");
        mapSum.insert("apple", 10);

        System.out.println("Sum for prefix 'ap': " + mapSum.sum("ap"));  // Returns 17 (updated apple + app + apricot = 10 + 2 + 5)

        System.out.println("Sum for prefix 'b': " + mapSum.sum("b"));    // Returns 0 (no keys with prefix 'b')
    }

    /**
     * MapSum implementation using TrieMap
     */
    static class MapSum {
        private TrieMap map;

        /**
         * Initialize the MapSum object
         */
        public MapSum() {
            map = new TrieMap();
        }

        /**
         * Insert or replace the key-value pair in the map
         * Time complexity: O(key.length)
         */
        public void insert(String key, int val) {
            map.put(key, val);
        }

        /**
         * Return the sum of values whose keys start with the given prefix
         * Time complexity: O(n) where n is the number of keys with prefix
         */
        public int sum(String prefix) {
            // Get all keys with the given prefix
            List<String> keys = map.keysWithPrefix(prefix);

            // Sum up all the values
            int sum = 0;
            for (String key : keys) {
                sum += map.get(key);
            }

            return sum;
        }
    }

    /**
     * TrieMap implementation for the MapSum problem
     */
    static class TrieMap {
        private TrieNode root = new TrieNode();

        // Add or update a key-value pair
        public void put(String key, int val) {
            TrieNode p = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int index = c - 'a';

                if (p.children[index] == null) {
                    p.children[index] = new TrieNode();
                }
                p = p.children[index];
            }
            p.val = val;
        }

        // Get the value for a key, return null if key doesn't exist
        public Integer get(String key) {
            TrieNode p = getNode(key);
            return p == null ? null : p.val;
        }

        // Get all keys with given prefix
        public List<String> keysWithPrefix(String prefix) {
            List<String> result = new ArrayList<>();
            TrieNode p = getNode(prefix);

            if (p == null) {
                return result;
            }

            // Collect all words with the prefix
            collectKeys(p, new StringBuilder(prefix), result);
            return result;
        }

        // Helper method to collect all keys
        private void collectKeys(TrieNode node, StringBuilder prefix, List<String> result) {
            if (node.val != null) {
                result.add(prefix.toString());
            }

            for (char c = 0; c < 26; c++) {
                if (node.children[c] != null) {
                    prefix.append((char) (c + 'a'));
                    collectKeys(node.children[c], prefix, result);
                    prefix.deleteCharAt(prefix.length() - 1);
                }
            }
        }

        // Helper method to find the node for a key
        private TrieNode getNode(String key) {
            TrieNode p = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int index = c - 'a';

                if (p.children[index] == null) {
                    return null;
                }
                p = p.children[index];
            }
            return p;
        }

        // TrieNode optimized for lowercase English letters (a-z)
        private static class TrieNode {
            Integer val = null;
            TrieNode[] children = new TrieNode[26];
        }
    }
}