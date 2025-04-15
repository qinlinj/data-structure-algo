package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array._2_left_right_pointer._4_palindrome;

// @formatter:off
/**
 * Finds the longest palindromic substring in a given string.
 *
 * This solution uses the "expand around center" approach, checking all possible
 * palindrome centers (both single character and between characters) and expanding
 * outward to find the longest palindrome.
 *
 * Visual example:
 * For string: "babad"
 *
 * Checking centers:
 * - Center at 'b' (i=0): "b" (palindrome of length 1)
 * - Center between 'b' and 'a' (i=0,i+1): "" (not a palindrome)
 * - Center at 'a' (i=1): "a" (palindrome of length 1)
 * - Center between 'a' and 'b' (i=1,i+1): "" (not a palindrome)
 * - Center at 'b' (i=2): "bab" (palindrome of length 3) ← longest so far
 * - Center between 'b' and 'a' (i=2,i+1): "" (not a palindrome)
 * - Center at 'a' (i=3): "aba" (palindrome of length 3) - same length as previous
 * - Center between 'a' and 'd' (i=3,i+1): "" (not a palindrome)
 * - Center at 'd' (i=4): "d" (palindrome of length 1)
 *
 * Result: "bab" (or "aba" depending on implementation details)
 *
 * Time Complexity: O(n²) where n is the length of the string
 * Space Complexity: O(1) excluding the space for the result
 */
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

    /**
     * Expands around center to find the longest palindrome.
     *
     * @param s The input string
     * @param l The left position of the center
     * @param r The right position of the center
     * @return The longest palindromic substring centered at positions l and r
     */
    String palindrome(String s, int l, int r) {
        // Expand outward from center as long as characters match
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // Expand the potential palindrome by moving outward in both directions
            l--;
            r++;
        }

        // At this point, the palindrome is s[l+1:r-1]
        // Note: substring takes start (inclusive) and end (exclusive) indices
        return s.substring(l + 1, r);
    }
}
