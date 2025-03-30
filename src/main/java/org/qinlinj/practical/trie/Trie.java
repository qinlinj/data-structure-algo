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

        System.out.println(trie.contains("dogddd"));
    }

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

    private class Node {
        Map<Character, Node> children;
        boolean isWord;

        Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }
}
