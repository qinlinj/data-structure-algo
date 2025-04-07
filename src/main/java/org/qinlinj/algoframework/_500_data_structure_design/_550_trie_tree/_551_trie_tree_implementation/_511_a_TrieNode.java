package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._551_trie_tree_implementation;

/**
 * TRIE NODE - CORE COMPONENT
 * <p>
 * Key Points:
 * - A TrieNode is the fundamental building block of a Trie (prefix tree)
 * - Each node stores:
 * 1. A value (can be null if not an end of key)
 * 2. An array of children nodes (one for each possible character)
 * - The array size is set to 256 to accommodate all ASCII characters
 * - Nodes with non-null values represent the end of a key in the Trie
 * - Nodes with null values serve as intermediate steps in the Trie path
 * - The tree structure allows for efficient prefix-based operations
 */
public class _511_a_TrieNode<V> {
    // Value stored at this node, null if this node is not the end of a key
    V val = null;

    // Array of child nodes, one for each possible character (ASCII)
    // This allows O(1) access to any child node
    @SuppressWarnings("unchecked")
    _511_a_TrieNode<V>[] children = new _511_a_TrieNode[256];

    // Default constructor creates an empty node
    public _511_a_TrieNode() {
        // Initialize with empty children array and null value
    }
}