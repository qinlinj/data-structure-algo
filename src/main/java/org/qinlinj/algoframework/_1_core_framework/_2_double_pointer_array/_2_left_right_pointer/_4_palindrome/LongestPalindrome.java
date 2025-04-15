package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array._2_left_right_pointer._4_palindrome;

// @formatter:off
public class LongestPalindrome {
    /**
     * Finds the longest palindromic substring in the given string.
     *
     * @param s The input string
     * @return The longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // Initialize the result with an empty string
        String res = "";

        // Check each possible center position
        for (int i = 0; i < s.length(); i++) {
            // Find palindrome with single character as center (odd length)
            String s1 = palindrome(s, i, i);

            // Find palindrome with two characters as center (even length)
            String s2 = palindrome(s, i, i + 1);

            // Update result with the longest palindrome found
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }

        return res;
    }

    private String palindrome(String s, int i, int i1) {}
}
