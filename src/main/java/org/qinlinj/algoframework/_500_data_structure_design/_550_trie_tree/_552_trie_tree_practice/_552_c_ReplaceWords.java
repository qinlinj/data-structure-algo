package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._552_trie_tree_practice;

import java.util.*;

/**
 * REPLACE WORDS
 * <p>
 * Problem Summary:
 * - In English, roots can form longer derivative words
 * - Given a dictionary of root words and a sentence of space-separated words
 * - Replace all derivative words in the sentence with their shortest root
 * - If a word has multiple possible roots, use the shortest one
 * <p>
 * Key Points:
 * - This problem demonstrates the practical application of finding the shortest prefix
 * - Uses the Trie data structure to efficiently find the shortest root for each word
 * - Time complexity: O(n) where n is the total number of characters in the sentence
 * - Perfect use case for the shortestPrefixOf method in our TrieSet
 * - Input words are limited to lowercase letters, allowing for optimized implementation
 */
public class _552_c_ReplaceWords {

    /**
     * Solution for LeetCode 648 using our optimized TrieSet
     *
     * @param dict     List of root words
     * @param sentence Space-separated string of words
     * @return Modified sentence with derivatives replaced by roots
     */
    public static String replaceWords(List<String> dict, String sentence) {
        // First, build a TrieSet with all the root words
        TrieSet set = new TrieSet();
        for (String root : dict) {
            set.add(root);
        }

        // Process each word in the sentence
        StringBuilder result = new StringBuilder();
        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            // Find the shortest root that is a prefix of the current word
            String prefix = set.shortestPrefixOf(words[i]);

            // If a root is found, replace the word with the root
            if (!prefix.isEmpty()) {
                result.append(prefix);
            } else {
                // Otherwise, keep the original word
                result.append(words[i]);
            }

            // Add space between words, except for the last word
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        System.out.println("LeetCode 648: Replace Words");
        System.out.println("===========================");

        // Example from LeetCode problem
        List<String> dictionary = new ArrayList<>();
        dictionary.add("cat");
        dictionary.add("bat");
        dictionary.add("rat");

        String sentence = "the cattle was rattled by the battery";

        System.out.println("Dictionary: [cat, bat, rat]");
        System.out.println("Sentence: " + sentence);
        System.out.println("Result: " + replaceWords(dictionary, sentence));
        // Output should be: "the cat was rat by the bat"

        // Additional example
        List<String> dictionary2 = new ArrayList<>();
        dictionary2.add("a");
        dictionary2.add("b");
        dictionary2.add("c");

        String sentence2 = "aadsfasf absbs bbab cadsfafs";

        System.out.println("\nDictionary: [a, b, c]");
        System.out.println("Sentence: " + sentence2);
        System.out.println("Result: " + replaceWords(dictionary2, sentence2));
        // Output should be: "a a b c"
    }

    /**
     * Optimized TrieSet implementation for LeetCode 648
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

        // Find the shortest prefix of query that exists in the set
        public String shortestPrefixOf(String query) {
            TrieNode p = root;

            // Traverse the Trie character by character
            for (int i = 0; i < query.length(); i++) {
                // If we've reached a leaf node, return the current prefix
                if (p.isEnd) {
                    return query.substring(0, i);
                }

                // Get index for current character
                char c = query.charAt(i);
                int index = c - 'a';

                // If there's no path for this character, no prefix exists
                if (p.children[index] == null) {
                    return "";
                }

                // Continue down the path
                p = p.children[index];
            }

            // Check if the entire query is a word in the dictionary
            if (p.isEnd) {
                return query;
            }

            return "";
        }

        // TrieNode optimized for lowercase English letters (a-z)
        private static class TrieNode {
            boolean isEnd = false;
            TrieNode[] children = new TrieNode[26]; // a-z only
        }
    }
}