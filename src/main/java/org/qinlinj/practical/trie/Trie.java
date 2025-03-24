package org.qinlinj.practical.trie;

import java.util.*;

public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public void add(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) {

        }
    }

    public boolean contains(String word) {
        Node curr = root;
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
