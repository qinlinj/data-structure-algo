package org.qinlinj.practical.trie;

import java.util.*;

public class _208_1_ImplementTriePrefixTree {
    class Trie {
        private Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node curr = root;
            for (Character o : word.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    curr.children.put(o, new Node());
                }
                curr = curr.children.get(o);
            }
            curr.isWord = true;
        }

        public boolean search(String word) {
            Node curr = root;
            for (Character o : word.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    return false;
                }
                curr = curr.children.get(o);
            }
            return curr.isWord;
        }

        public boolean startsWith(String prefix) {
            Node curr = root;
            for (Character o : prefix.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    return false;
                }
                curr = curr.children.get(o);
            }
            return true;
        }

        class Node {
            public Map<Character, Node> children;
            public boolean isWord;

            public Node() {
                this.children = new HashMap<>();
                this.isWord = false;
            }
        }
    }
}
