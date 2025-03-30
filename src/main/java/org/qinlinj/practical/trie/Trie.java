package org.qinlinj.practical.trie;

import java.util.*;
// @formatter:off
/**
 * Trie (Prefix Tree) Data Structure Implementation
 *
 * A Trie is an efficient information retrieval data structure specialized for string keys.
 * Advantages of Trie:
 * 1. Fast lookups - O(L) where L is the length of the word
 * 2. Space efficiency when storing many strings with common prefixes
 * 3. Excellent for prefix-based operations like autocomplete
 * 4. Faster than hash tables for certain string operations
 *
 * Use cases:
 * - Autocomplete suggestions
 * - Spell checkers
 * - IP routing (longest prefix matching)
 * - Predictive text input
 * - Word games (finding valid words from letters)
 */
public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("big");
        trie.add("pat");
        trie.add("bigger");
        trie.add("dog");
        trie.add("door");

        // Test if a word is contained in the trie
        // This will return false because "dogddd" was not added, only "dog" was
        System.out.println(trie.contains("dogddd"));

        // Visualization example of how the trie structure looks after adding these words:
        //
        //                  root
        //                /  |  \
        //               b   p   d
        //              /    |    \
        //             i     a     o
        //            /      |      \
        //           g*      t*      g*  o
        //          /                     \
        //         g                       r*
        //        /
        //       e
        //      /
        //     r*
        //
        // Note: * indicates a complete word (isWord = true)
        System.out.println(trie.contains("dogddd"));
    }

    /**
     * Adds a word to the trie
     *
     * Time Complexity: O(L) where L is the length of the word
     * Space Complexity: O(L) in worst case if no shared prefixes
     *
     * @param word The word to add to the trie
     */
    public void add(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node());
            }
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    /**
     * Checks if a word exists in the trie
     *
     * Time Complexity: O(L) where L is the length of the word
     * Space Complexity: O(1) since no additional space is needed
     *
     * @param word The word to check
     * @return true if the word exists in the trie, false otherwise
     */
    public boolean contains(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) { // O(n)
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.isWord;
    }

    /**
     * Node class for the Trie
     * Each node represents a character in the trie and has:
     * 1. A map of child nodes for subsequent characters
     * 2. A flag indicating if the node represents the end of a word
     */
    private class Node {
        Map<Character, Node> children;
        boolean isWord;

        Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }
}
