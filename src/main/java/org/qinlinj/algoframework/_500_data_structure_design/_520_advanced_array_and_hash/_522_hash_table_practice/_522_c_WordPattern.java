package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_c_WordPattern
 * <p>
 * LeetCode #290: Word Pattern
 * <p>
 * Problem:
 * Given a pattern string and a string s, determine if s follows the same pattern.
 * Here "follows" means a complete match where each letter in pattern maps to a non-empty
 * word in s, and there is a bidirectional mapping between letters and words.
 * <p>
 * Approach:
 * The solution uses a HashMap to track the bidirectional mapping between pattern characters
 * and words from the string s. For a valid pattern:
 * 1. Each pattern character must map to exactly one word
 * 2. Each word must map to exactly one pattern character
 * 3. The number of pattern characters must equal the number of words
 * <p>
 * Time Complexity: O(n) where n is the length of the input strings
 * Space Complexity: O(k) where k is the number of unique characters in pattern/words in s
 */

import java.util.*;

public class _522_c_WordPattern {

    public static void main(String[] args) {
        _522_c_WordPattern solution = new _522_c_WordPattern();

        // Test case 1
        String pattern1 = "abba";
        String s1 = "dog cat cat dog";
        boolean result1 = solution.wordPattern(pattern1, s1);
        System.out.println("Example 1: " + result1);
        // Expected output: true

        // Test case 2
        String pattern2 = "abba";
        String s2 = "dog cat cat fish";
        boolean result2 = solution.wordPattern(pattern2, s2);
        System.out.println("Example 2: " + result2);
        // Expected output: false

        // Test case 3
        String pattern3 = "aaaa";
        String s3 = "dog cat cat dog";
        boolean result3 = solution.wordPattern(pattern3, s3);
        System.out.println("Example 3: " + result3);
        // Expected output: false
    }

    public boolean wordPattern(String pattern, String s) {
        // Split s into words
        String[] words = s.split(" ");

        // Check if pattern length matches the number of words
        if (pattern.length() != words.length) {
            return false;
        }

        // Map to track pattern char to word mapping
        HashMap<Character, String> charToWord = new HashMap<>();
        // Set to track words that are already mapped
        HashSet<String> mappedWords = new HashSet<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // Check if this pattern char already has a mapping
            if (charToWord.containsKey(c)) {
                // Verify the mapping is consistent
                if (!charToWord.get(c).equals(word)) {
                    return false;
                }
            } else {
                // This is a new pattern char
                // Check if the word is already mapped to another char
                if (mappedWords.contains(word)) {
                    return false;
                }

                // Add the new mapping
                charToWord.put(c, word);
                mappedWords.add(word);
            }
        }

        return true;
    }

    /**
     * Alternative implementation using two maps to track bidirectional mapping
     */
    public boolean wordPattern2(String pattern, String s) {
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        // Map word -> pattern char
        HashMap<String, Character> wordToChar = new HashMap<>();
        // Set to track which pattern chars are already mapped
        HashSet<Character> mappedChars = new HashSet<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (wordToChar.containsKey(word)) {
                // Check if the word maps to the same pattern char
                if (wordToChar.get(word) != c) {
                    return false;
                }
            } else {
                // This is a new word
                // Check if the pattern char is already mapped
                if (mappedChars.contains(c)) {
                    return false;
                }

                // Add the new mapping
                wordToChar.put(word, c);
                mappedChars.add(c);
            }
        }

        return true;
    }
}