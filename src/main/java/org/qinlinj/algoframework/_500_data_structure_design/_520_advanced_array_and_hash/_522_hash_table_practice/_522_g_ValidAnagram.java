package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice;

/**
 * _522_g_ValidAnagram
 * <p>
 * LeetCode #242: Valid Anagram
 * <p>
 * Problem:
 * Given two strings s and t, determine if t is an anagram of s.
 * An anagram is a word formed by rearranging the letters of another word,
 * using all the original letters exactly once.
 * <p>
 * Approach:
 * An anagram must contain exactly the same characters with the same frequency.
 * We can encode each string by counting the occurrences of each character:
 * 1. Create a frequency array for each string
 * 2. Compare the frequency arrays - they must be identical for anagrams
 * <p>
 * Since we're told the strings contain only lowercase letters, we can use
 * a fixed-size array of length 26 (one for each lowercase letter).
 * <p>
 * Time Complexity: O(n) where n is the length of the strings
 * Space Complexity: O(1) because we use a fixed-size array of 26 elements
 */
public class _522_g_ValidAnagram {

    public static void main(String[] args) {
        _522_g_ValidAnagram solution = new _522_g_ValidAnagram();

        // Test case 1
        String s1 = "anagram";
        String t1 = "nagaram";
        boolean result1 = solution.isAnagram(s1, t1);
        System.out.println("Example 1: " + result1);
        // Expected output: true

        // Test case 2
        String s2 = "rat";
        String t2 = "car";
        boolean result2 = solution.isAnagram(s2, t2);
        System.out.println("Example 2: " + result2);
        // Expected output: false

        // Additional test case
        String s3 = "listen";
        String t3 = "silent";
        boolean result3 = solution.isAnagram(s3, t3);
        System.out.println("Additional example: " + result3);
        // Expected output: true
    }

    public boolean isAnagram(String s, String t) {
        // If lengths differ, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Create frequency arrays for both strings
        int[] sCount = getCharCount(s);
        int[] tCount = getCharCount(t);

        // Compare frequency arrays
        for (int i = 0; i < 26; i++) {
            if (sCount[i] != tCount[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Counts the frequency of each lowercase letter in a string
     */
    private int[] getCharCount(String str) {
        int[] count = new int[26]; // One slot for each lowercase letter

        for (char c : str.toCharArray()) {
            // Map 'a' to 0, 'b' to 1, etc.
            count[c - 'a']++;
        }

        return count;
    }
}