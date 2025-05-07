package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._552_trie_tree_practice;

/**
 * IMPLEMENT TRIE (PREFIX TREE)
 * <p>
 * Problem Summary:
 * - Implement a Trie (prefix tree) with the following operations:
 * 1. insert(word): Insert a string into the trie
 * 2. search(word): Return true if the string exists in the trie
 * 3. startsWith(prefix): Return true if there is any string with given prefix
 * <p>
 * Key Points:
 * - This is a fundamental Trie implementation problem
 * - Demonstrates direct application of a TrieSet data structure
 * - The problem specifically requires three basic operations:
 * a. Word insertion (add to set)
 * b. Exact word search (contains check)
 * c. Prefix existence check (hasKeyWithPrefix)
 * - All operations have O(k) time complexity where k is word length
 * - The constraint of lowercase English letters allows for optimization
 */
public class _552_b_ImplementTrie {

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        System.out.println("LeetCode 208: Implement Trie (Prefix Tree)");
        System.out.println("==========================================");

        // Example from LeetCode problem
        Trie trie = new Trie();

        System.out.println("Insert 'apple'");
        trie.insert("apple");

        System.out.println("Search 'apple': " + trie.search("apple"));   // returns true
        System.out.println("Search 'app': " + trie.search("app"));       // returns false
        System.out.println("StartsWith 'app': " + trie.startsWith("app")); // returns true

        System.out.println("Insert 'app'");
        trie.insert("app");

        System.out.println("Search 'app': " + trie.search("app"));       // returns true

        // Additional tests
        System.out.println("\nAdditional Tests:");
        System.out.println("StartsWith 'b': " + trie.startsWith("b"));   // returns false

        System.out.println("Insert 'banana'");
        trie.insert("banana");

        System.out.println("StartsWith 'b': " + trie.startsWith("b"));   // returns true
        System.out.println("Search 'ban': " + trie.search("ban"));       // returns false
    }

    /**
     * Trie implementation for LeetCode 208
     * Uses our optimized TrieSet as the underlying data structure
     */
    static class Trie {
        // Underlying TrieSet to handle all operations
        private TrieSet set;

        /**
         * Initialize the Trie object
         */
        public Trie() {
            set = new TrieSet();
        }

        /**
         * Insert a word into the trie
         * Time complexity: O(word.length)
         */
        public void insert(String word) {
            set.add(word);
        }

        /**
         * Check if the word exists in the trie
         * Returns true if the word exists, false otherwise
         * Time complexity: O(word.length)
         */
        public boolean search(String word) {
            return set.contains(word);
        }

        /**
         * Check if there is any word in the trie that starts with the given prefix
         * Returns true if such word exists, false otherwise
         * Time complexity: O(prefix.length)
         */
        public boolean startsWith(String prefix) {
            return set.hasKeyWithPrefix(prefix);
        }
    }

    /**
     * Optimized TrieSet implementation for lowercase English letters
     */
    static class TrieSet {
        private TrieNode root = new TrieNode();

        // Add a word to the set
        public void add(String key) {
            TrieNode p = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                int index = c - 'a';

                if (p.children[index] == null) {
                    p.children[index] = new TrieNode();
                }
                p = p.children[index];
            }
            p.isEnd = true;
        }

        // Check if a word exists in the set
        public boolean contains(String key) {
            TrieNode p = getNode(key);
            return p != null && p.isEnd;
        }

        // Check if any word with the given prefix exists
        public boolean hasKeyWithPrefix(String prefix) {
            return getNode(prefix) != null;
        }

        // Helper method to find the node for a given key
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
            boolean isEnd = false;
            TrieNode[] children = new TrieNode[26]; // a-z only
        }
    }
}