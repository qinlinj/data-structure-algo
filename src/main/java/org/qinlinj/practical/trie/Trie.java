package org.qinlinj.practical.trie;

import java.util.*;

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
