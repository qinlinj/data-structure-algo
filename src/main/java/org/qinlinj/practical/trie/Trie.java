package org.qinlinj.practical.trie;

import java.util.*;

public class Trie {
    private class Node {
        Map<Character, Node> children;
        boolean isWord;

        Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }
}
