package org.qinlinj.practical.trie;

// @formatter:off
/**
 * Trie (Prefix Tree) Implementation using Character Arrays
 *
 * Concept and Principles:
 * A Trie is a tree-like data structure used for efficient retrieval of keys in a dataset of strings.
 * Each node in the Trie represents a single character, and paths from the root to leaf nodes
 * represent complete words stored in the Trie. This implementation uses character arrays instead
 * of HashMaps for storing children nodes.
 *
 * Advantages of Array-based Trie:
 * 1. More memory-efficient than HashMap-based Trie for lowercase English alphabets (fixed size of 26 characters)
 * 2. Faster access time - O(1) direct array indexing vs. O(1) average but slower HashMap lookup
 * 3. Consistent performance without HashMap's load factor considerations
 * 4. Lower memory overhead per node (no need to store character keys)
 * 5. Better cache locality due to contiguous memory allocation
 *
 * Limitations:
 * 1. Only supports lowercase English letters (a-z)
 * 2. Fixed array size regardless of actual character usage (may waste space)
 * 3. Not suitable for large character sets (e.g., Unicode)
 *
 * Visual Example:
 * For a Trie containing words: "cat", "car"
 *
 *                 root
 *                  |
 *                  c (index 2 in children array)
 *                  |
 *                  a (index 0 in children array)
 *                 / \
 *                t   r
 *          (index 19) (index 17)
 *       (isWord=true) (isWord=true)
 *
 * In array representation:
 * root.children = [null,null,Node_c,null,...,null] (size 26)
 * Node_c.children = [Node_a,null,null,...,null] (size 26)
 * Node_a.children = [null,...,null,Node_r,null,Node_t,...,null] (size 26)
 */
public class _208_TrieCharImp_ImplementTriePrefixTree {
    private Node root;

    public _208_TrieCharImp_ImplementTriePrefixTree() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) { // O(n)
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new Node();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) { // O(n)
            if (curr.children[c - 'a'] == null) {
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;
        for (char c : prefix.toCharArray()) { // O(n)
            if (curr.children[c - 'a'] == null) {
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return true;
    }

    private class Node {
        Node[] children;
        boolean isWord;

        Node() {
            children = new Node[26];
            isWord = false;
        }
    }
}
