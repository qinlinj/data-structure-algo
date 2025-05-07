package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._552_trie_tree_practice;

import java.util.*;

/**
 * IMPLEMENT TRIE II (PREFIX TREE)
 * <p>
 * Problem Summary:
 * - Implement an enhanced Trie with the following operations:
 * 1. insert(word): Add a word to the trie, tracking insertion counts
 * 2. countWordsEqualTo(word): Return number of times a word was inserted
 * 3. countWordsStartingWith(prefix): Return number of words with the given prefix
 * 4. erase(word): Remove one occurrence of the word from the trie
 * <p>
 * Key Points:
 * - This problem extends the basic Trie to track frequency of insertions
 * - Perfect use case for the TrieMap where values are frequencies
 * - Demonstrates the versatility of Trie data structure for word counting
 * - The implementation must handle repeated insertions and deletions
 * - Time complexity: O(k) for all operations where k is word length
 * - Space complexity: O(n) where n is the total length of all inserted words
 */
public class _552_e_ImplementTrieII {

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        System.out.println("Implement Trie II (Prefix Tree)");
        System.out.println("===============================");

        Trie trie = new Trie();

        System.out.println("Insert 'apple'");
        trie.insert("apple");

        System.out.println("Count words equal to 'apple': " + trie.countWordsEqualTo("apple"));         // 1
        System.out.println("Count words starting with 'app': " + trie.countWordsStartingWith("app"));   // 1

        System.out.println("Insert 'apple' again");
        trie.insert("apple");

        System.out.println("Count words equal to 'apple': " + trie.countWordsEqualTo("apple"));         // 2

        System.out.println("Insert 'apps'");
        trie.insert("apps");

        System.out.println("Count words starting with 'app': " + trie.countWordsStartingWith("app"));   // 3

        System.out.println("Erase 'apple' once");
        trie.erase("apple");

        System.out.println("Count words equal to 'apple': " + trie.countWordsEqualTo("apple"));         // 1
        System.out.println("Count words starting with 'app': " + trie.countWordsStartingWith("app"));   // 2

        System.out.println("Erase 'apple' again");
        trie.erase("apple");

        System.out.println("Count words equal to 'apple': " + trie.countWordsEqualTo("apple"));         // 0
        System.out.println("Count words starting with 'app': " + trie.countWordsStartingWith("app"));   // 1
    }

    /**
     * Enhanced Trie implementation that tracks word frequencies
     */
    static class Trie {
        // Using TrieMap to store words with their frequencies
        private TrieMap map;

        /**
         * Initialize the Trie object
         */
        public Trie() {
            map = new TrieMap();
        }

        /**
         * Insert a word into the trie and increment its count
         * Time complexity: O(word.length)
         */
        public void insert(String word) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }

        /**
         * Return how many times a word was inserted
         * Time complexity: O(word.length)
         */
        public int countWordsEqualTo(String word) {
            if (!map.containsKey(word)) {
                return 0;
            }
            return map.get(word);
        }

        /**
         * Return the number of words that have the prefix
         * Time complexity: O(n) where n is the number of words with prefix
         */
        public int countWordsStartingWith(String prefix) {
            int count = 0;
            for (String key : map.keysWithPrefix(prefix)) {
                count += map.get(key);
            }
            return count;
        }

        /**
         * Delete one occurrence of the word from the trie
         * Time complexity: O(word.length)
         */
        public void erase(String word) {
            if (!map.containsKey(word)) {
                return;
            }

            int freq = map.get(word);
            if (freq == 1) {
                map.remove(word);
            } else {
                map.put(word, freq - 1);
            }
        }
    }

    /**
     * TrieMap implementation that supports frequency tracking
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

        // Check if key exists in the map
        public boolean containsKey(String key) {
            return get(key) != null;
        }

        // Remove a key from the map
        public void remove(String key) {
            remove(root, key, 0);
        }

        // Recursive helper method to remove a key
        private TrieNode remove(TrieNode node, String key, int i) {
            if (node == null) {
                return null;
            }

            if (i == key.length()) {
                // Found the node for key, clear its value
                node.val = null;
            } else {
                char c = key.charAt(i);
                int index = c - 'a';
                // Recursively remove from child node
                node.children[index] = remove(node.children[index], key, i + 1);
            }

            // Clean up empty nodes
            if (node.val != null) {
                return node; // Node has a value, keep it
            }

            for (int j = 0; j < 26; j++) {
                if (node.children[j] != null) {
                    return node; // Node has a child, keep it
                }
            }

            return null; // Node has no value and no children, remove it
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

        private static class TrieNode {
            // Value used to store frequency
            Integer val = null;
            TrieNode[] children = new TrieNode[26];
        }
    }
}