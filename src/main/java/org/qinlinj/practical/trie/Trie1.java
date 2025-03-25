package org.qinlinj.practical.trie;

import java.util.*;

public class Trie1 {
    private Node root;

    public Trie1() {
        this.root = new Node('/');
    }

    public void add(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) {
            int index = containsChar(curr.children, c);
            if (index == -1) {
                curr.children.add(new Node(c));
                index = curr.children.size() - 1;
            }
            curr = curr.children.get(index);
        }

        curr.isWord = true;
    }

    private int containsChar(List<Node> children, Character c) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).c == c) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) {
            int index = containsChar(curr.children, c);
            if (index == -1) {
                return false;
            }
            curr = curr.children.get(index);
        }
        return curr.isWord;
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