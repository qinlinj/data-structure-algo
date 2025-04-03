package org.qinlinj.practical.trie;

import java.util.*;

// @formatter:off
/**
 * Trie-based Auto-Complete System Implementation
 *
 * Concept and Principles:
 * This class implements an auto-complete system using a Trie data structure. It suggests the most
 * frequently used sentences based on characters typed so far. The Trie efficiently stores all
 * sentences and allows for quick prefix searches, which is ideal for auto-completion functionality.
 *
 * Advantages of Trie-based Implementation:
 * 1. Efficient prefix searching - O(m) time complexity where m is the length of the prefix
 * 2. Only explores the relevant parts of the Trie (unlike HashMap that scans all entries)
 * 3. Space-efficient for datasets with common prefixes
 * 4. Scalable for large datasets with many sentences
 * 5. Natural traversal for collecting all sentences with a given prefix
 *
 * Visual Example:
 * Given sentences: ["i love you", "island", "iroman"] with frequencies [5, 3, 2]
 *
 * The Trie would look like:
 *                root
 *                 |
 *                 i
 *           /     |     \
 *          s      r      ' '(space)
 *         /       |        \
 *        l        o         l
 *       /         |          \
 *      a          m           o
 *     /           |            \
 *    n            a             v
 *   /             |              \
 *  d(3)           n               e
 *                 |                \
 *                (2)                ' '(space)
 *                                    |
 *                                    y
 *                                    |
 *                                    o
 *                                    |
 *                                    u(5)
 *
 * Where the numbers in parentheses represent the frequency of complete sentences.
 */
public class _642_TrieImp_AutoCompleteSystem {
    // Root node of the Trie
    private TrieNode root;

    // Current input sentence being built character by character
    private String currSentence = "";

    /**
     * Initialize the auto-complete system with historical sentences and their frequencies.
     * Builds a Trie containing all provided sentences.
     *
     * Time Complexity: O(n*k) where n is the number of sentences and k is the average length
     * Space Complexity: O(n*k) for storing all characters of all sentences in the Trie
     *
     * @param sentences array of historical sentences
     * @param times array of usage frequencies corresponding to each sentence
     */
    public _642_TrieImp_AutoCompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        // Insert all historical sentences into the Trie with their frequencies
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    /**
     * Insert a sentence into the Trie with its frequency.
     * If the sentence already exists, the frequency is added to the existing value.
     *
     * Visualization Example:
     * Inserting "go" with frequency 2:
     * 1. Start at root
     * 2. Create/navigate to child 'g'
     * 3. Create/navigate to child 'o'
     * 4. Increment times at node 'o' by 2
     *
     * Time Complexity: O(k) where k is the length of the sentence
     * Space Complexity: O(k) for new nodes if sentence wasn't in the Trie
     *
     * @param s the sentence to insert
     * @param times the frequency of the sentence
     */
    public void insert(String s, int times) {
        TrieNode curr = root;
        // Traverse/build the Trie for each character in the sentence
        for (char c : s.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        // Increment the times counter at the end node
        curr.times += times;
    }

    /**
     * Process a character input and return the top 3 sentence suggestions.
     * Special character '#' indicates the end of a sentence, which adds it to the Trie.
     *
     * Visual Example:
     * For Trie containing: {"hello": 2, "hi": 3, "hey": 1}
     *
     * 1. User types 'h':
     *    - currSentence becomes "h"
     *    - lookup("h") finds all nodes in subtree starting with 'h'
     *    - Results: "hi" (3), "hello" (2), "hey" (1)
     *    - After sorting, returns: ["hi", "hello", "hey"]
     *
     * 2. User types 'e':
     *    - currSentence becomes "he"
     *    - lookup("he") finds all nodes in subtree starting with 'he'
     *    - Results: "hello" (2), "hey" (1)
     *    - After sorting, returns: ["hello", "hey"]
     *
     * 3. User types '#':
     *    - Insert "he" with frequency 1
     *    - Reset currSentence to ""
     *    - Return empty list
     *
     * Time Complexity: O(p + q log q) where:
     *   - p is the total number of characters in all matching sentences
     *   - q is the number of matching sentences (for sorting)
     * Space Complexity: O(p) for storing matching sentences
     *
     * @param c character input from the user
     * @return List of top 3 sentence suggestions sorted by frequency and lexicographical order
     */
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();

        if (c == '#') {
            // End of sentence marker - insert the current sentence with frequency 1
            insert(currSentence, 1);
            // Reset the current sentence
            currSentence = "";
        } else {
            // Append the character to the current sentence
            currSentence += c;

            // Find all sentences that start with the current input
            List<SentenceInfo> list = lookup(currSentence);

            // Sort matching sentences by frequency (descending) and lexicographical order (for ties)
            Collections.sort(list, new Comparator<SentenceInfo>() {
                @Override
                public int compare(SentenceInfo o1, SentenceInfo o2) {
                    return o1.time == o2.time ?
                            o1.content.compareTo(o2.content) : // If frequencies are equal, sort lexicographically
                            o2.time - o1.time;                 // Otherwise, sort by frequency (descending)
                }
            });

            // Return the top 3 (or fewer if less than 3 matches) suggestions
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).content);
            }
        }
        return res;
    }

    /**
     * Find all sentences in the Trie that start with the given prefix.
     *
     * Process:
     * 1. Navigate to the node representing the last character of the prefix
     * 2. Perform DFS from that node to find all complete sentences
     *
     * Time Complexity: O(m + p) where:
     *   - m is the length of the prefix
     *   - p is the total number of characters in all matching sentences
     * Space Complexity: O(p) for storing all matching sentences
     *
     * @param s the prefix to search for
     * @return List of SentenceInfo objects containing all matching sentences and their frequencies
     */
    public List<SentenceInfo> lookup(String s) {
        List<SentenceInfo> list = new ArrayList<>();

        // Navigate to the node representing the prefix
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                // Prefix not found in the Trie
                return list; // Return empty list
            }
            curr = curr.children.get(c);
        }

        // Perform DFS from the prefix node to find all complete sentences
        dfs(curr, s, list);

        return list;
    }

    /**
     * Depth-First Search to find all complete sentences from a given node.
     * Recursively explores all paths from the current node and adds complete sentences to the list.
     *
     * Visualization Example:
     * For Trie path "h" -> "e" -> "l" -> "l" -> "o" (times=2):
     * 1. Start DFS at "h" node with s="h"
     * 2. Recursive calls for all children: DFS("e" node, "he", list)
     * 3. Continue to: DFS("l" node, "hel", list)
     * 4. Continue to: DFS("l" node, "hell", list)
     * 5. Continue to: DFS("o" node, "hello", list)
     * 6. At "o" node, times=2 > 0, so add ("hello", 2) to list
     *
     * Time Complexity: O(p) where p is the total number of characters in all paths from the current node
     * Space Complexity: O(h) for recursion stack, where h is the maximum depth from current node
     *
     * @param curr the current TrieNode being explored
     * @param s the current sentence being built
     * @param list the list to store complete sentences and their frequencies
     */
    private void dfs(TrieNode curr, String s, List<SentenceInfo> list) {
        // If current node represents the end of a sentence (times > 0), add it to the list
        if (curr.times > 0) {
            list.add(new SentenceInfo(s, curr.times));
        }

        // Explore all children nodes recursively
        for (char c : curr.children.keySet()) {
            dfs(curr.children.get(c), s + c, list);
        }
    }

    /**
     * Helper class to store sentence information for sorting.
     * Combines a sentence with its usage frequency.
     */
    private class SentenceInfo {
        String content; // The sentence content
        int time;       // The usage frequency

        /**
         * Create a new SentenceInfo object.
         *
         * @param content the sentence text
         * @param time the usage frequency
         */
        SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }

    /**
     * TrieNode class for the Trie data structure.
     * Each node represents a character in the Trie and contains:
     * 1. A map of children nodes (next characters in sequences)
     * 2. A times counter to track how many times a sentence ending at this node occurs
     */
    private class TrieNode {
        // Map to store children nodes where key is the next character and value is the child node
        Map<Character, TrieNode> children = new HashMap<>();

        // Counter for sentence frequency (0 if this node is not the end of any sentence)
        int times = 0;
    }
}