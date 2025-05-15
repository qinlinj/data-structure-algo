package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II; /**
 * Word Ladder (LeetCode 127)
 * ------------------------
 * <p>
 * Summary:
 * This problem involves transforming one word to another through a sequence of valid words,
 * changing only one letter at a time. All intermediate words must exist in a given dictionary.
 * The goal is to find the length of the shortest transformation sequence or return 0 if no
 * such sequence exists.
 * <p>
 * Key Concepts:
 * 1. Graph traversal where words are nodes and one-letter differences are edges
 * 2. BFS to find the shortest path from beginWord to endWord
 * 3. Efficient generation and validation of next possible words
 * 4. Tracking visited words to avoid cycles
 * <p>
 * Approach:
 * - Create a word graph where each node is connected to words differing by one letter
 * - Use BFS to find the shortest path from beginWord to endWord
 * - Rather than pre-building the entire graph, generate possible transformations on-the-fly
 * - For each position in the word, try all 26 possible letters and check if the new word is valid
 * <p>
 * Time Complexity: O(L * N * 26) where:
 * - L is the length of each word
 * - N is the number of words in the dictionary
 * - 26 is the number of possible letter substitutions
 * <p>
 * Space Complexity: O(N) for the queue and visited set
 */

import java.util.*;

public class _753_i_WordLadder {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_i_WordLadder solution = new _753_i_WordLadder();

        // Example 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        System.out.println("Example 1 (BFS): " + solution.ladderLength(beginWord1, endWord1, wordList1));  // Expected: 5
        System.out.println("Example 1 (Bidirectional): " + solution.ladderLengthBidirectional(beginWord1, endWord1, wordList1));  // Expected: 5

        // Example 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");

        System.out.println("Example 2 (BFS): " + solution.ladderLength(beginWord2, endWord2, wordList2));  // Expected: 0
        System.out.println("Example 2 (Bidirectional): " + solution.ladderLengthBidirectional(beginWord2, endWord2, wordList2));  // Expected: 0

        // Additional example
        String beginWord3 = "abc";
        String endWord3 = "def";
        List<String> wordList3 = Arrays.asList("abe", "aef", "aef", "def");

        System.out.println("Additional (BFS): " + solution.ladderLength(beginWord3, endWord3, wordList3));  // Expected: 4
        System.out.println("Additional (Bidirectional): " + solution.ladderLengthBidirectional(beginWord3, endWord3, wordList3));  // Expected: 4
    }

    /**
     * Find the length of the shortest transformation sequence
     * @param beginWord The starting word
     * @param endWord The target word
     * @param wordList List of valid words for transformation
     * @return Length of the shortest transformation sequence or 0 if none exists
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to a set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);

        // If endWord is not in the dictionary, no transformation is possible
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Remove beginWord from the set (optional optimization)
        wordSet.remove(beginWord);

        // Queue for BFS
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        // Set to track visited words
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // Start with 1 (counting the beginWord itself)
        int level = 1;

        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all words at the current level
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // If we've reached the endWord, return the level
                if (currentWord.equals(endWord)) {
                    return level;
                }

                // Try changing each character of the word
                char[] wordChars = currentWord.toCharArray();

                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    // Try all 26 possible letters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue;  // Skip the original character
                        }

                        wordChars[j] = c;
                        String newWord = new String(wordChars);

                        // Check if the new word is valid and unvisited
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }

                    // Restore the original character for the next iteration
                    wordChars[j] = originalChar;
                }
            }

            // Move to the next level
            level++;
        }

        // If we've exhausted all possibilities and haven't found endWord
        return 0;
    }

    /**
     * Bidirectional BFS optimization
     * Start BFS from both beginWord and endWord, and meet in the middle
     */
    public int ladderLengthBidirectional(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to a set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);

        // If endWord is not in the dictionary, no transformation is possible
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Two sets for bidirectional BFS
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        // Set to track visited words
        Set<String> visited = new HashSet<>();

        // Start with 1 (counting the beginWord itself)
        int level = 1;

        // Bidirectional BFS
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller set for efficiency
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            // Next level set
            Set<String> nextLevelSet = new HashSet<>();

            // Process all words at the current level
            for (String currentWord : beginSet) {
                // Try changing each character of the word
                char[] wordChars = currentWord.toCharArray();

                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    // Try all 26 possible letters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue;  // Skip the original character
                        }

                        wordChars[j] = c;
                        String newWord = new String(wordChars);

                        // If the other set contains the new word, we've found a path
                        if (endSet.contains(newWord)) {
                            return level + 1;  // +1 because we're moving to the next level
                        }

                        // Check if the new word is valid and unvisited
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            nextLevelSet.add(newWord);
                            visited.add(newWord);
                        }
                    }

                    // Restore the original character for the next iteration
                    wordChars[j] = originalChar;
                }
            }

            // Move to the next level
            beginSet = nextLevelSet;
            level++;
        }

        // If we've exhausted all possibilities and haven't found a path
        return 0;
    }
}