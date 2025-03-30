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
    // Root node of the trie
    private Node root;

    /**
     * Constructor initializes an empty trie with just a root node
     */
    public Trie() {
        this.root = new Node();
    }

    /**
     * Demo of trie functionality
     */
    public static void main(String[] args) {
        // Create a new trie and add several words
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

        // Traverse through each character in the word
        for (Character c : word.toCharArray()) {
            // If the current character isn't in the children map, add a new node
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node());
            }
            // Move to the next node
            curr = curr.children.get(c);
        }

        // Mark the last node as a complete word
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

        // Traverse through each character in the word
        for (Character c : word.toCharArray()) {
            // If character isn't found in the trie, the word doesn't exist
            if (!curr.children.containsKey(c)) {
                return false;
            }
            // Move to the next node
            curr = curr.children.get(c);
        }

        // The word exists only if we've reached a node marked as a complete word
        return curr.isWord;
    }

    /**
     * Node class for the Trie
     * Each node represents a character in the trie and has:
     * 1. A map of child nodes for subsequent characters
     * 2. A flag indicating if the node represents the end of a word
     */
    private class Node {
        // Maps each character to its corresponding child node
        Map<Character, Node> children;

        // Indicates whether this node represents the end of a complete word
        boolean isWord;

        /**
         * Constructor initializes an empty node
         */
        Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }
}