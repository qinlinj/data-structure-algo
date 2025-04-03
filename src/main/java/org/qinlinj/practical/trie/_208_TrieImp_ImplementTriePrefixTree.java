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

        public void insert(String word) {
            Node curr = root;
            for (Character o : word.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    curr.children.put(o, new Node());
                }
                curr = curr.children.get(o);
            }
            curr.isWord = true;
        }

        public boolean search(String word) {
            Node curr = root;
            for (Character o : word.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    return false;
                }
                curr = curr.children.get(o);
            }
            return curr.isWord;
        }

        public boolean startsWith(String prefix) {
            Node curr = root;
            for (Character o : prefix.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    return false;
                }
                curr = curr.children.get(o);
            }
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
