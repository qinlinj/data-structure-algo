package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search Applications - Subsequence Detection
 * <p>
 * Key Concepts:
 * 1. Using binary search to efficiently check if one string is a subsequence of another
 * 2. Preprocessing the main string to create an index-based lookup table
 * 3. Binary searching for character positions to optimize subsequence checks
 * <p>
 * This class implements:
 * 1. LeetCode 392: Is Subsequence (basic solution)
 * 2. LeetCode 792: Number of Matching Subsequences (optimized binary search solution)
 * <p>
 * The basic solution is O(n) using dual pointers.
 * The binary search optimization is crucial when checking many subsequences
 * against the same string (improves from O(n*k) to O(m*log(n)) where k is the
 * number of strings to check and m is their total length).
 */
public class _336_d_IsSubsequence {

    public static void main(String[] args) {
        _336_d_IsSubsequence solution = new _336_d_IsSubsequence();

        // Basic subsequence examples
        System.out.println("Basic Subsequence Check:");
        System.out.println("'abc' is subsequence of 'ahbgdc': " +
                solution.isSubsequence("abc", "ahbgdc")); // Expected: true
        System.out.println("'axc' is subsequence of 'ahbgdc': " +
                solution.isSubsequence("axc", "ahbgdc")); // Expected: false

        // Multiple subsequence examples
        System.out.println("\nMultiple Subsequence Check:");
        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println("Number of subsequences in 'abcde': " +
                solution.numMatchingSubseq(s, words)); // Expected: 3

        String s2 = "dsahjpjauf";
        String[] words2 = {"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"};
        System.out.println("Number of subsequences in 'dsahjpjauf': " +
                solution.numMatchingSubseq(s2, words2)); // Expected: 2
    }

    /**
     * Basic approach: Checks if s is a subsequence of t using dual pointers
     * Time Complexity: O(n) where n is the length of t
     * Space Complexity: O(1)
     *
     * @param s String to check if it's a subsequence
     * @param t Main string
     * @return true if s is a subsequence of t, false otherwise
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        if (t.length() == 0) return false;

        int i = 0; // pointer for s
        int j = 0; // pointer for t

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++; // Found a match, move to next character in s
            }
            j++; // Always advance in t
        }

        // If we matched all characters in s
        return i == s.length();
    }

    /**
     * Advanced approach: Counts the number of strings in words that are subsequences of s
     * Uses binary search for efficient matching of multiple subsequences
     * Time Complexity: O(n + m*log(n)) where n is the length of s and m is total length of all words
     * Space Complexity: O(n) for the index map
     *
     * @param s     Main string
     * @param words Array of strings to check
     * @return Count of strings in words that are subsequences of s
     */
    public int numMatchingSubseq(String s, String[] words) {
        // Preprocess s - build index map for each character
        // charIndices[c] will contain all positions where character c appears in s
        @SuppressWarnings("unchecked")
        java.util.ArrayList<Integer>[] charIndices = new java.util.ArrayList[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charIndices[c - 'a'] == null) {
                charIndices[c - 'a'] = new java.util.ArrayList<>();
            }
            charIndices[c - 'a'].add(i);
        }

        int count = 0;

        // Check each word
        for (String word : words) {
            if (isSubsequenceWithBinarySearch(word, charIndices)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Helper method to check if a word is a subsequence using binary search
     *
     * @param word        Word to check
     * @param charIndices Preprocessed character indices from main string
     * @return true if word is a subsequence, false otherwise
     */
    private boolean isSubsequenceWithBinarySearch(String word, java.util.ArrayList<Integer>[] charIndices) {
        // Current position in the main string
        int currPos = -1;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // If character doesn't exist in main string
            if (charIndices[c - 'a'] == null) {
                return false;
            }

            // Find the next position of character c after currPos
            int nextPos = findNextPosition(charIndices[c - 'a'], currPos);

            // If no valid position found
            if (nextPos == -1) {
                return false;
            }

            // Update current position
            currPos = nextPos;
        }

        return true;
    }

    /**
     * Finds the next position of a character after a given position using binary search
     *
     * @param positions List of positions where a character appears
     * @param currPos   Current position in the main string
     * @return Next position after currPos, or -1 if none exists
     */
    private int findNextPosition(java.util.ArrayList<Integer> positions, int currPos) {
        // Binary search to find the first position greater than currPos
        int left = 0;
        int right = positions.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (positions.get(mid) <= currPos) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // If left is out of bounds, no valid position found
        if (left == positions.size()) {
            return -1;
        }

        return positions.get(left);
    }
}
