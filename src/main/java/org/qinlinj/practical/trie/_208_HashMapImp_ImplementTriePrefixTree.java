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
public class _208_HashMapImp_ImplementTriePrefixTree {
    private Node root;

    public _208_HashMapImp_ImplementTriePrefixTree() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) { // O(n)
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node());
            }
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) { // O(n)
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;
        for (Character c : prefix.toCharArray()) { // O(n)
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }

    private class Node {
        Map<Character, Node> children;
        boolean isWord;

        Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }
}
