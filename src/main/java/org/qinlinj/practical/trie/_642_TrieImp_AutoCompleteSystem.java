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
    private TrieNode root;
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
        for (char c : s.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.times += times;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            insert(currSentence, 1);
            currSentence = "";
        } else {
            currSentence += c;

            List<SentenceInfo> list = lookup(currSentence);

            Collections.sort(list, new Comparator<SentenceInfo>() {
                @Override
                public int compare(SentenceInfo o1, SentenceInfo o2) {
                    return o1.time == o2.time ?
                            o1.content.compareTo(o2.content) :
                            o2.time - o1.time;
                }
            });
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).content);
            }
        }
        return res;
    }

    public List<SentenceInfo> lookup(String s) {
        List<SentenceInfo> list = new ArrayList<>();

        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return list;
            }
            curr = curr.children.get(c);
        }

        dfs(curr, s, list);

        return list;
    }

    private void dfs(TrieNode curr, String s, List<SentenceInfo> list) {
        if (curr.times > 0) {
            list.add(new SentenceInfo(s, curr.times));
        }

        for (char c : curr.children.keySet()) {
            dfs(curr.children.get(c), s + c, list);
        }
    }

    private class SentenceInfo {
        String content;
        int time;

        SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }

    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int times = 0;
    }
}
