package org.qinlinj.practical.trie;

import java.util.*;

public class Trie1 {
    private Node root;

    public Trie1() {
        this.root = new Node('/');
    }

    public void add(String word) {

    }

    private int containsChar(List<Node> children, Character c) {
        return 0;
    }

    public boolean contains(String word) {
        return false;
    }

    private class Node {
        List<Node> children;
        boolean isWord;
        private Character c;

        public Node(Character c) {
            this.c = c;
            this.children = new ArrayList<>();
            this.isWord = false;
        }
    }
}