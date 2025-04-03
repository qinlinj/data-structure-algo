package org.qinlinj.practical.trie;

// @formatter:off
public class _208_TrieCharImp_ImplementTriePrefixTree {
    private Node root;

    public _208_TrieCharImp_ImplementTriePrefixTree() {
        root = new Node();
    }

    public void insert(String word) {

    }

    public boolean search(String word) {

    }

    public boolean startsWith(String prefix) {

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
