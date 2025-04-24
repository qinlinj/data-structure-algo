package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Longest Common Prefix (LeetCode 14)
 * =================================
 * <p>
 * This class implements a solution to find the longest common prefix
 * among an array of strings.
 * <p>
 * Problem:
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * <p>
 * Examples:
 * - Input: ["flower","flow","flight"] -> Output: "fl"
 * - Input: ["dog","racecar","car"] -> Output: ""
 * <p>
 * Approach:
 * Treat the strings as a 2D character array, where each string is a row.
 * Iterate through the columns and check if all characters in each column are the same.
 * As soon as we find a mismatch or reach the end of a string, return the prefix found so far.
 * <p>
 * Time Complexity: O(S) where S is the sum of all characters in all strings
 * Space Complexity: O(1) excluding the output string
 */

public class _323_i_LongestCommonPrefix {

    /**
     * Demonstration of the longest common prefix algorithms.
     */
    public static void main(String[] args) {
        _323_i_LongestCommonPrefix solution = new _323_i_LongestCommonPrefix();

        // Example 1
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("Example 1:");
        System.out.println("Input: [\"flower\", \"flow\", \"flight\"]");
        System.out.println("Output: \"" + solution.longestCommonPrefix(strs1) + "\"");
        System.out.println();

        // Example 2
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("Example 2:");
        System.out.println("Input: [\"dog\", \"racecar\", \"car\"]");
        System.out.println("Output: \"" + solution.longestCommonPrefix(strs2) + "\"");
        System.out.println();

        // Example 3: Empty array
        String[] strs3 = {};
        System.out.println("Example 3 (Empty array):");
        System.out.println("Output: \"" + solution.longestCommonPrefix(strs3) + "\"");
        System.out.println();

        // Example 4: Single string
        String[] strs4 = {"alone"};
        System.out.println("Example 4 (Single string):");
        System.out.println("Input: [\"alone\"]");
        System.out.println("Output: \"" + solution.longestCommonPrefix(strs4) + "\"");
        System.out.println();

        // Example 5: Strings of different lengths
        String[] strs5 = {"ab", "a"};
        System.out.println("Example 5 (Strings of different lengths):");
        System.out.println("Input: [\"ab\", \"a\"]");
        System.out.println("Output: \"" + solution.longestCommonPrefix(strs5) + "\"");
    }

    /**
     * Finds the longest common prefix among an array of strings.
     *
     * @param strs Array of strings
     * @return The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        // Edge case: empty array
        if (strs == null || strs.length == 0) {
            return "";
        }

        int m = strs.length;
        // Use the first string's length as reference
        int n = strs[0].length();

        // Iterate through each column (character position)
        for (int col = 0; col < n; col++) {
            // Compare this character across all strings
            for (int row = 1; row < m; row++) {
                String currentStr = strs[row];
                String previousStr = strs[row - 1];

                // If we've reached the end of any string or found a mismatch
                if (col >= currentStr.length() || col >= previousStr.length() ||
                        currentStr.charAt(col) != previousStr.charAt(col)) {
                    // Return the prefix found so far
                    return strs[0].substring(0, col);
                }
            }
        }

        // If we've checked all characters in the first string without finding a mismatch
        return strs[0];
    }

    /**
     * Alternative implementation that may be more intuitive for some.
     * Here we compare all strings with the first string character by character.
     */
    public String longestCommonPrefixAlt(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String firstStr = strs[0];

        for (int i = 0; i < firstStr.length(); i++) {
            char c = firstStr.charAt(i);

            // Check this character against the same position in all other strings
            for (int j = 1; j < strs.length; j++) {
                // If we've reached the end of another string or found a mismatch
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return firstStr.substring(0, i);
                }
            }
        }

        // If we've checked the entire first string without finding a mismatch
        return firstStr;
    }

    /**
     * Another approach using the "divide and conquer" technique.
     * This method recursively finds the longest common prefix between parts of the array.
     */
    public String longestCommonPrefixDivideAndConquer(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        return longestCommonPrefixHelper(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefixHelper(String[] strs, int start, int end) {
        // Base case: single string
        if (start == end) {
            return strs[start];
        }

        // Recursively find the longest common prefix for each half
        int mid = (start + end) / 2;
        String leftPrefix = longestCommonPrefixHelper(strs, start, mid);
        String rightPrefix = longestCommonPrefixHelper(strs, mid + 1, end);

        // Combine results by finding the common prefix between the two halves
        return commonPrefix(leftPrefix, rightPrefix);
    }

    private String commonPrefix(String left, String right) {
        int minLength = Math.min(left.length(), right.length());

        for (int i = 0; i < minLength; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }

        // If one string is a prefix of the other
        return left.substring(0, minLength);
    }
}
