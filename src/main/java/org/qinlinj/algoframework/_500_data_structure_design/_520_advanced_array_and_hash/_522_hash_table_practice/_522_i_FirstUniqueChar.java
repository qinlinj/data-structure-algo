package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice;

import java.util.*;

/**
 * _522_i_FirstUniqueChar
 * <p>
 * LeetCode #387: First Unique Character in a String
 * <p>
 * Problem:
 * Given a string s, find the first non-repeating character and return its index.
 * If it doesn't exist, return -1.
 * <p>
 * Approach:
 * Since we're working with lowercase English letters only, we can use a fixed-size
 * array of 26 characters as a frequency counter instead of a HashMap.
 * <p>
 * 1. Count the frequency of each character in the string
 * 2. Iterate through the string again to find the first character with count = 1
 * <p>
 * Using an array instead of a HashMap is more efficient for this specific case
 * since we're dealing with a limited character set.
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) since we use a fixed-size array of 26 characters
 */
public class _522_i_FirstUniqueChar {

    public static void main(String[] args) {
        _522_i_FirstUniqueChar solution = new _522_i_FirstUniqueChar();

        // Test case 1
        String s1 = "leetcode";
        int result1 = solution.firstUniqChar(s1);
        System.out.println("Example 1: " + result1);
        // Expected output: 0 (the first unique character is 'l' at index 0)

        // Test case 2
        String s2 = "loveleetcode";
        int result2 = solution.firstUniqChar(s2);
        System.out.println("Example 2: " + result2);
        // Expected output: 2 (the first unique character is 'v' at index 2)

        // Test case 3
        String s3 = "aabb";
        int result3 = solution.firstUniqChar(s3);
        System.out.println("Example 3: " + result3);
        // Expected output: -1 (there are no unique characters)
    }

    public int firstUniqChar(String s) {
        // Create frequency counter for lowercase English letters
        int[] charCount = new int[26];

        // First pass: count character frequencies
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Second pass: find the first character with frequency = 1
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charCount[c - 'a'] == 1) {
                return i;
            }
        }

        // No unique character found
        return -1;
    }

    /**
     * Alternative implementation using HashMap for non-English alphabets
     * This would be useful for Unicode characters or larger character sets
     */
    public int firstUniqCharWithHashMap(String s) {
        // Use HashMap to count occurrences
        HashMap<Character, Integer> count = new HashMap<>();

        // First pass: count character frequencies
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // Second pass: find the first character with count = 1
        for (int i = 0; i < s.length(); i++) {
            if (count.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}