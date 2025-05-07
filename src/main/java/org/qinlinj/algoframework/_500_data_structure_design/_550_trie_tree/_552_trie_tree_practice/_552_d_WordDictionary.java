package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._552_trie_tree_practice;

/**
 * DESIGN ADD AND SEARCH WORDS DATA STRUCTURE
 * <p>
 * Problem Summary:
 * - Design a data structure that supports adding new words and searching for strings
 * - Two main operations:
 * 1. addWord(word): Add a word to the data structure
 * 2. search(word): Search if the string exists in the data structure
 * - The search function includes a wildcard '.' that can match any character
 * <p>
 * Key Points:
 * - This problem demonstrates the pattern matching capability of Trie
 * - Wildcards require special handling during search traversal
 * - Perfect use case for the hasKeyWithPattern method in our TrieSet
 * - The '.' character can match any letter, requiring a branch in the search algorithm
 * - Time complexity: O(m) for adding a word where m is word length
 * - Time complexity: O(26^n) for searching a pattern with wildcards in worst case,
 * where n is the number of '.' characters
 */
public class _552_d_WordDictionary {

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        System.out.println("Design Add and Search Words Data Structure");
        System.out.println("======================================================");

        // Example from LeetCode problem
        WordDictionary wordDictionary = new WordDictionary();

        System.out.println("Adding words: bad, dad, mad");
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        System.out.println("Search 'pad': " + wordDictionary.search("pad"));      // false
        System.out.println("Search 'bad': " + wordDictionary.search("bad"));      // true
        System.out.println("Search '.ad': " + wordDictionary.search(".ad"));      // true
        System.out.println("Search 'b..': " + wordDictionary.search("b.."));      // true

        // Additional tests to demonstrate pattern matching
        System.out.println("\nAdditional Tests:");
        System.out.println("Search 'b.d': " + wordDictionary.search("b.d"));      // true
        System.out.println("Search '...': " + wordDictionary.search("..."));      // true
        System.out.println("Search 'b.': " + wordDictionary.search("b."));        // false
        System.out.println("Search '.a.': " + wordDictionary.search(".a."));      // true

        // Add a longer word
        System.out.println("\nAdding word: hello");
        wordDictionary.addWord("hello");

        System.out.println("Search 'h.l..': " + wordDictionary.search("h.l.."));  // true
        System.out.println("Search '....o': " + wordDictionary.search("....o"));  // true
        System.out.println("Search '...': " + wordDictionary.search("..."));      // still true, matches "bad", "dad", "mad"
    }

    /**
     * WordDictionary implementation using TrieSet with pattern matching
     */
    static class WordDictionary {
        private TrieSet set;

        /**
         * Initialize the WordDictionary
         */
        public WordDictionary() {
            set = new TrieSet();
        }

        /**
         * Add a word to the dictionary
         * Time complexity: O(word.length)
         */
        public void addWord(String word) {
            set.add(word);
        }

        /**
         * Search for a word in the dictionary, supporting '.' as wildcard
         * Time complexity: O(n) for exact match where n is word length
         * Time complexity: O(26^m * n) for pattern with wildcards in worst case
         * where m is the number of '.' characters and n is word length
         */
        public boolean search(String word) {
            return set.hasKeyWithPattern(word);
        }
    }

    /**
     * TrieSet implementation with pattern matching support
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

        // Check if the set contains any string that matches the given pattern
        public boolean hasKeyWithPattern(String pattern) {
            return hasKeyWithPattern(root, pattern, 0);
        }

        // Recursive helper method to find pattern match
        private boolean hasKeyWithPattern(TrieNode node, String pattern, int i) {
            // Base case: reached the end of pattern
            if (i == pattern.length()) {
                return node.isEnd;
            }

            char c = pattern.charAt(i);

            // Case 1: Current character is wildcard '.'
            if (c == '.') {
                // Try all possible characters at this position
                for (int j = 0; j < 26; j++) {
                    if (node.children[j] != null &&
                            hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                        return true;
                    }
                }
                return false;
            }
            // Case 2: Current character is a regular letter
            else {
                int index = c - 'a';

                if (node.children[index] == null) {
                    return false;
                }

                return hasKeyWithPattern(node.children[index], pattern, i + 1);
            }
        }

        // TrieNode optimized for lowercase English letters (a-z)
        private static class TrieNode {
            boolean isEnd = false;
            TrieNode[] children = new TrieNode[26]; // a-z only
        }
    }
}