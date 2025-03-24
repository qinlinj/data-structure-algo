package org.qinlinj.practical.trie;

import java.util.*;

public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node();
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
