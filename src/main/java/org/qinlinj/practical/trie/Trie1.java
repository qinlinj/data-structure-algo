package org.qinlinj.practical.trie;

import java.util.*;
// @formatter:off
/**
 * Trie (Prefix Tree) Data Structure Implementation Using Array Lists
 *
 * This implementation uses ArrayList instead of HashMap to store child nodes.
 *
 * Advantages of this implementation:
 * 1. Potentially lower memory overhead for small character sets
 * 2. Better cache locality in some cases
 * 3. Avoids hash collisions entirely
 * 4. May perform better when the branching factor is low (few children per node)
 *
 * Disadvantages compared to HashMap implementation:
 * 1. O(k) lookup time where k is the number of children, vs O(1) for HashMap
 * 2. Linear search through children is required to find a specific character
 * 3. Less efficient for large alphabets or dense tries
 */
public class Trie1 {
    private Node root;

    /**
     * Constructor initializes the trie with a root node containing a special character '/'
     * The root character is arbitrary and serves as a marker for the start of the trie
     */
    public Trie1() {
        this.root = new Node('/');
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("big");
        trie.add("pat");
        trie.add("bigger");
        trie.add("dog");
        trie.add("door");

        System.out.println(trie.contains("biggere"));
    }

    /**
     * Adds a word to the trie
     *
     * Time Complexity: O(L*k) where:
     * - L is the length of the word
     * - k is the average number of children per node
     *
     * Space Complexity: O(L) in worst case if no shared prefixes
     *
     * @param word The word to add to the trie
     */
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