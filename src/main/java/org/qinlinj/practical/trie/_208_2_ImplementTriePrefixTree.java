package org.qinlinj.practical.trie;

import java.util.*;

public class _208_2_ImplementTriePrefixTree {
    private Node root;

    public _208_2_ImplementTriePrefixTree() {
        root = new Node();
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
