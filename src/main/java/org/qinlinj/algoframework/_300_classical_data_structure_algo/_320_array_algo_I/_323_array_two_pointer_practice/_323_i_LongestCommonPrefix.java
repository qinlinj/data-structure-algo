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
}
