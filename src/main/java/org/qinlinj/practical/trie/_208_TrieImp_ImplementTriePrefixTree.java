package org.qinlinj.practical.trie;

import java.util.*;
// @formatter:off
/**
 * Trie (Prefix Tree) Implementation
 *
 * Concept and Principles:
 * A Trie is a tree-like data structure used to store a dynamic set of strings where the keys
 * are usually strings. Unlike a binary search tree, no node in the tree stores the key associated
 * with that node; instead, its position in the tree defines the key with which it is associated.
 * All the descendants of a node have a common prefix of the string associated with that node.
 *
 * Advantages of Trie:
 * 1. Efficient prefix search - O(m) time complexity where m is the length of the key.
 * 2. Space-efficient for common prefixes - if many keys share common prefixes, Trie saves space.
 * 3. Faster than hash tables for certain operations like prefix matching.
 * 4. No hash collisions, unlike HashMap.
 * 5. Alphabetical ordering of entries by key is possible, which isn't possible with hashing.
 *
 * Visual Example:
 * For a Trie containing words: "app", "apple", "application"
 *
 *                 root
 *                  |
 *                  a
 *                  |
 *                  p
 *                  |
 *                  p (isWord=true for "app")
 *                 / \
 *                l   l
 *                |   |
 *                e   i
 *           (isWord) |
 *                    c
 *                    |
 *                    a
 *                    |
 *                    t
 *                    |
 *                    i
 *                    |
 *                    o
 *                    |
 *                    n (isWord=true)
 */
public class _208_TrieImp_ImplementTriePrefixTree {
    class Trie {
        // Root node of the Trie
        private Node root;

        /**
         * Initialize the Trie with an empty root node.
         * Time Complexity: O(1)
         */
        public Trie() {
            this.root = new Node();
        }

        /**
         * Inserts a word into the Trie.
         *
         * Visualization Example:
         * Inserting "cat" into an empty Trie:
         * 1. Start at root
         * 2. Add 'c' as a child of root
         * 3. Add 'a' as a child of 'c'
         * 4. Add 't' as a child of 'a'
         * 5. Mark 't' node as end of word (isWord = true)
         *
         * Time Complexity: O(n) where n is the length of the word
         * Space Complexity: O(n) in worst case (if all characters are new)
         *
         * @param word the word to insert into the Trie
         */
        public void insert(String word) {
            Node curr = root;
            for (Character c : word.toCharArray()) {
                // If the current character doesn't exist in children, create a new node
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new Node());
                }
                // Move to the child node
                curr = curr.children.get(c);
            }
            // Mark the end of the word
            curr.isWord = true;
        }

        /**
         * Searches for a word in the Trie.
         * Returns true if the whole word is in the Trie, false otherwise.
         *
         * Visualization Example:
         * Searching for "cat" in a Trie with "cat", "car":
         * 1. Start at root
         * 2. Follow path to 'c'
         * 3. Follow path to 'a'
         * 4. Follow path to 't'
         * 5. Check if 't' node is marked as end of word (isWord = true)
         * 6. Return true (found "cat")
         *
         * Time Complexity: O(n) where n is the length of the word
         * Space Complexity: O(1) - no additional space required
         *
         * @param word the word to search for
         * @return true if the word exists in the Trie, false otherwise
         */
        public boolean search(String word) {
            Node curr = root;
            for (Character c : word.toCharArray()) {
                // If character doesn't exist in current path, word doesn't exist
                if (!curr.children.containsKey(c)) {
                    return false;
                }
                // Move to the next node
                curr = curr.children.get(c);
            }
            // Return whether current node marks the end of a word
            return curr.isWord;
        }

        /**
         * Checks if there is any word in the Trie that starts with the given prefix.
         *
         * Visualization Example:
         * Checking if prefix "ca" exists in a Trie with "cat", "car":
         * 1. Start at root
         * 2. Follow path to 'c'
         * 3. Follow path to 'a'
         * 4. Return true (found prefix "ca")
         *
         * Time Complexity: O(n) where n is the length of the prefix
         * Space Complexity: O(1) - no additional space required
         *
         * @param prefix the prefix to search for
         * @return true if any word with this prefix exists, false otherwise
         */
        public boolean startsWith(String prefix) {
            Node curr = root;
            for (Character c : prefix.toCharArray()) {
                // If character doesn't exist in current path, prefix doesn't exist
                if (!curr.children.containsKey(c)) {
                    return false;
                }
                // Move to the next node
                curr = curr.children.get(c);
            }
            // If we've traversed the entire prefix, it exists in the Trie
            return true;
        }

        class Node {
            public Map<Character, Node> children;
            public boolean isWord;

            public Node() {
                this.children = new HashMap<>();
                this.isWord = false;
            }
        }
    }
}
