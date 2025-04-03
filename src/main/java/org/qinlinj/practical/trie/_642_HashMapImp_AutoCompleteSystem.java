package org.qinlinj.practical.trie;

import java.util.*;

/**
 * Auto-Complete System Implementation
 * <p>
 * Concept and Principles:
 * This class implements an auto-complete system that suggests the most frequently used sentences
 * based on the characters typed so far. It uses a HashMap to store sentences and their usage frequencies.
 * When a user types a character, the system returns the top 3 matching sentences sorted by frequency
 * and lexicographical order (for ties).
 * <p>
 * Advantages:
 * 1. Simple implementation using HashMap for storage
 * 2. Efficient for small to medium datasets
 * 3. Easy to understand and maintain
 * 4. Dynamic updating of frequencies when sentences are confirmed
 * <p>
 * Limitations:
 * 1. Time complexity of O(n*k) for each input operation, where n is the number of sentences and k is the average sentence length
 * 2. Not as efficient as a Trie-based implementation for large datasets
 * 3. Performs a linear scan of all sentences for each character input
 * <p>
 * Visual Example:
 * Given sentences: ["i love you", "island", "iroman"] with frequencies [5, 3, 2]
 * <p>
 * User types 'i':
 * - System searches for sentences starting with "i"
 * - Finds: "i love you" (5), "island" (3), "iroman" (2)
 * - Returns: ["i love you", "island", "iroman"] (sorted by frequency)
 * <p>
 * User types 'l' (current input = "il"):
 * - System searches for sentences starting with "il"
 * - Finds only: "island" (3)
 * - Returns: ["island"]
 */
public class _642_HashMapImp_AutoCompleteSystem {
    // Map to store sentences and their usage frequencies
    private Map<String, Integer> map;

    // Current input sentence being built character by character
    private String currSentence = "";

    /**
     * Initialize the auto-complete system with historical sentences and their frequencies.
     * <p>
     * Time Complexity: O(n) where n is the number of input sentences
     * Space Complexity: O(n) to store all sentences and frequencies
     *
     * @param sentences array of historical sentences
     * @param times     array of usage frequencies corresponding to each sentence
     */
    public _642_HashMapImp_AutoCompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        // Initialize the map with historical sentences and their frequencies
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }

    /**
     * Process a character input and return the top 3 sentence suggestions.
     * Special character '#' indicates the end of a sentence, which updates its frequency.
     * <p>
     * Visual Example:
     * For map containing: {"hello": 2, "hi": 3, "hey": 1}
     * <p>
     * 1. User types 'h':
     * - currSentence becomes "h"
     * - Find sentences starting with "h": "hello" (2), "hi" (3), "hey" (1)
     * - Sort by frequency: "hi" (3), "hello" (2), "hey" (1)
     * - Return: ["hi", "hello", "hey"]
     * <p>
     * 2. User types 'e':
     * - currSentence becomes "he"
     * - Find sentences starting with "he": "hello" (2), "hey" (1)
     * - Sort by frequency: "hello" (2), "hey" (1)
     * - Return: ["hello", "hey"]
     * <p>
     * 3. User types '#':
     * - Add/update "he" in the map with frequency 1
     * - Reset currSentence to ""
     * - Return empty list
     * <p>
     * Time Complexity: O(n*k + m log m) where:
     * - n is the number of sentences
     * - k is the average length of sentences (for startsWith comparison)
     * - m is the number of matching sentences (for sorting)
     * Space Complexity: O(m) for storing matching sentences
     *
     * @param c character input from the user
     * @return List of top 3 sentence suggestions sorted by frequency and lexicographical order
     */
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();

        if (c == '#') {
            // End of sentence marker
            // Update the frequency of the current sentence in the map
            map.put(currSentence, map.getOrDefault(currSentence, 0) + 1);
            // Reset the current sentence
            currSentence = "";
        } else {
            // Append the character to the current sentence
            currSentence += c;

            // Find all sentences that start with the current input
            List<SentenceInfo> list = new ArrayList<>();
            for (String sentence : map.keySet()) { // O(n*k)
                if (sentence.startsWith(currSentence)) {
                    list.add(new SentenceInfo(sentence, map.get(sentence)));
                }
            }

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
         * @param time    the usage frequency
         */
        SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }
}