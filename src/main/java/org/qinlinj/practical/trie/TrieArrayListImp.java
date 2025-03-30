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
public class TrieArrayListImp {
    // Root node of the trie
    private Node root;

    /**
     * Constructor initializes the trie with a root node containing a special character '/'
     * The root character is arbitrary and serves as a marker for the start of the trie
     */
    public TrieArrayListImp() {
        this.root = new Node('/');
    }

    /**
     * Demo of trie functionality
     * Note: There's a type mismatch in the main method - it creates a Trie object but should create Trie1
     */
    public static void main(String[] args) {
        TrieHashMapImp trie = new TrieHashMapImp();  // Note: This should be Trie1 trie = new Trie1();
        trie.add("big");
        trie.add("pat");
        trie.add("bigger");
        trie.add("dog");
        trie.add("door");

        // This will check if "biggere" exists in the trie (should return false)
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
        // Traverse through each character in the word
        for (Character c : word.toCharArray()) {
            // Search for the character in the current node's children
            int index = containsChar(curr.children, c);

            // If character isn't found, add a new node for it
            if (index == -1) {
                curr.children.add(new Node(c));
                // Get the index of the newly added node
                index = curr.children.size() - 1;
            }

            // Move to the next node
            curr = curr.children.get(index);
        }

        // Mark the last node as a complete word
        curr.isWord = true;
    }

    /**
     * Searches for a character in a list of nodes
     * This is a linear search through the children list
     *
     * Time Complexity: O(k) where k is the number of children
     *
     * @param children List of child nodes to search through
     * @param c Character to find
     * @return Index of the child node containing the character, or -1 if not found
     */
    private int containsChar(List<Node> children, Character c) {
        // Linear search through the children list
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).c == c) {
                return i;
            }
        }
        // Character not found
        return -1;
    }

    /**
     * Checks if a word exists in the trie
     *
     * Time Complexity: O(L*k) where:
     * - L is the length of the word
     * - k is the average number of children per node
     *
     * Space Complexity: O(1) since no additional space is needed
     *
     * @param word The word to check
     * @return true if the word exists in the trie, false otherwise
     */
    public boolean contains(String word) {
        Node curr = root;
        // Traverse through each character in the word
        for (Character c : word.toCharArray()) {
            // Search for the character in the current node's children
            int index = containsChar(curr.children, c);

            // If character isn't found, the word doesn't exist
            if (index == -1) {
                return false;
            }

            // Move to the next node
            curr = curr.children.get(index);
        }

        // The word exists only if we've reached a node marked as a complete word
        return curr.isWord;
    }

    /**
     * Node class for the Trie
     * Each node represents a character in the trie and contains:
     * 1. The character value itself
     * 2. A list of child nodes for subsequent characters
     * 3. A flag indicating if the node represents the end of a word
     */
    private class Node {
        // List of child nodes
        List<Node> children;

        // Indicates whether this node represents the end of a complete word
        boolean isWord;

        // The character stored in this node
        private Character c;

        /**
         * Constructor initializes a node with the given character
         *
         * @param c The character to store in this node
         */
        public Node(Character c) {
            this.c = c;
            this.children = new ArrayList<>();
            this.isWord = false;
        }
    }
}