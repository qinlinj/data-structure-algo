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
    private Map<String, Integer> map;

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


    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            map.put(currSentence, map.getOrDefault(currSentence, 0) + 1);
            currSentence = "";
        } else {
            currSentence += c;

            List<SentenceInfo> list = new ArrayList<>();
            for (String sentence : map.keySet()) { // O(n*k)
                if (sentence.startsWith(currSentence)) {
                    list.add(new SentenceInfo(sentence, map.get(sentence)));
                }
            }

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

    private class SentenceInfo {
        String content;
        int time;

        SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }
}
