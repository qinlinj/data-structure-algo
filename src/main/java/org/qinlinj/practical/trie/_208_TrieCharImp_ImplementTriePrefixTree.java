package org.qinlinj.practical.trie;

// @formatter:off
/**
 * Trie (Prefix Tree) Implementation using Character Arrays
 *
 * Concept and Principles:
 * A Trie is a tree-like data structure used for efficient retrieval of keys in a dataset of strings.
 * Each node in the Trie represents a single character, and paths from the root to leaf nodes
 * represent complete words stored in the Trie. This implementation uses character arrays instead
 * of HashMaps for storing children nodes.
 *
 * Advantages of Array-based Trie:
 * 1. More memory-efficient than HashMap-based Trie for lowercase English alphabets (fixed size of 26 characters)
 * 2. Faster access time - O(1) direct array indexing vs. O(1) average but slower HashMap lookup
 * 3. Consistent performance without HashMap's load factor considerations
 * 4. Lower memory overhead per node (no need to store character keys)
 * 5. Better cache locality due to contiguous memory allocation
 *
 * Limitations:
 * 1. Only supports lowercase English letters (a-z)
 * 2. Fixed array size regardless of actual character usage (may waste space)
 * 3. Not suitable for large character sets (e.g., Unicode)
 *
 * Visual Example:
 * For a Trie containing words: "cat", "car"
 *
 *                 root
 *                  |
 *                  c (index 2 in children array)
 *                  |
 *                  a (index 0 in children array)
 *                 / \
 *                t   r
 *          (index 19) (index 17)
 *       (isWord=true) (isWord=true)
 *
 * In array representation:
 * root.children = [null,null,Node_c,null,...,null] (size 26)
 * Node_c.children = [Node_a,null,null,...,null] (size 26)
 * Node_a.children = [null,...,null,Node_r,null,Node_t,...,null] (size 26)
 */
public class _208_TrieCharImp_ImplementTriePrefixTree {
    // Root node of the Trie
    private Node root;

    /**
     * Initialize the Trie with an empty root node.
     * Time Complexity: O(1)
     */
    public _208_TrieCharImp_ImplementTriePrefixTree() {
        root = new Node();
    }

    /**
     * Inserts a word into the Trie.
     *
     * Visualization Example:
     * Inserting "dog" into an empty Trie:
     * 1. Start at root
     * 2. Calculate index for 'd': 'd' - 'a' = 3
     * 3. Create new node at root.children[3]
     * 4. Calculate index for 'o': 'o' - 'a' = 14
     * 5. Create new node at d_node.children[14]
     * 6. Calculate index for 'g': 'g' - 'a' = 6
     * 7. Create new node at o_node.children[6]
     * 8. Mark g_node as end of word (isWord = true)
     *
     * Time Complexity: O(n) where n is the length of the word
     * Space Complexity: O(n) in worst case (if all characters are new)
     *
     * @param word the word to insert into the Trie (lowercase letters only)
     */
    public void insert(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) { // O(n)
            // Calculate array index by subtracting 'a' from character
            // For example: 'a' - 'a' = 0, 'b' - 'a' = 1, 'z' - 'a' = 25
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new Node();
            }
            // Move to the child node
            curr = curr.children[c - 'a'];
        }
        // Mark the end of the word
        curr.isWord = true;
    }

    /**
     * Searches for a word in the Trie.
     * Returns true if the whole word is in the Trie, false otherwise.
     *
     * Visualization Example:
     * Searching for "dog" in a Trie with "dog", "dot":
     * 1. Start at root
     * 2. Navigate to index 'd' - 'a' = 3
     * 3. Navigate to index 'o' - 'a' = 14
     * 4. Navigate to index 'g' - 'a' = 6
     * 5. Check if g_node is marked as end of word (isWord = true)
     * 6. Return true (found "dog")
     *
     * Time Complexity: O(n) where n is the length of the word
     * Space Complexity: O(1) - no additional space required
     *
     * @param word the word to search for (lowercase letters only)
     * @return true if the word exists in the Trie, false otherwise
     */
    public boolean search(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) { // O(n)
            // If node doesn't exist at calculated index, word doesn't exist
            if (curr.children[c - 'a'] == null) {
                return false;
            }
            // Move to the next node
            curr = curr.children[c - 'a'];
        }
        // Return whether current node marks the end of a word
        return curr.isWord;
    }

    /**
     * Checks if there is any word in the Trie that starts with the given prefix.
     *
     * Visualization Example:
     * Checking if prefix "do" exists in a Trie with "dog", "dot":
     * 1. Start at root
     * 2. Navigate to index 'd' - 'a' = 3
     * 3. Navigate to index 'o' - 'a' = 14
     * 4. Return true (found prefix "do")
     *
     * Time Complexity: O(n) where n is the length of the prefix
     * Space Complexity: O(1) - no additional space required
     *
     * @param prefix the prefix to search for (lowercase letters only)
     * @return true if any word with this prefix exists, false otherwise
     */
    public boolean startsWith(String prefix) {
        Node curr = root;
        for (char c : prefix.toCharArray()) { // O(n)
            // If node doesn't exist at calculated index, prefix doesn't exist
            if (curr.children[c - 'a'] == null) {
                return false;
            }
            // Move to the next node
            curr = curr.children[c - 'a'];
        }
        // If we've traversed the entire prefix, it exists in the Trie
        return true;
    }

    /**
     * Node class for the Trie data structure.
     * Each node contains an array of children nodes (size 26 for lowercase letters)
     * and a flag indicating if it's the end of a word.
     */
    private class Node {
        // Array to store children nodes, indexed by character (a=0, b=1, ..., z=25)
        Node[] children;
        // Flag to indicate if this node represents the end of a word
        boolean isWord;

        /**
         * Initialize a new Node with an empty children array of size 26
         * (one position for each lowercase letter) and isWord set to false.
         */
        Node() {
            children = new Node[26];
            isWord = false;
        }
    }
}