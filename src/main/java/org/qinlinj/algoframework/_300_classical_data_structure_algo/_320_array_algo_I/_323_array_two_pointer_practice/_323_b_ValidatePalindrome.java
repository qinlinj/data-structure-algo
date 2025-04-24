package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;


/**
 * Validate Palindrome (LeetCode 125)
 * =================================
 * <p>
 * This class implements a solution to validate if a string is a palindrome.
 * <p>
 * Problem:
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 * <p>
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * <p>
 * Examples:
 * - "A man, a plan, a canal: Panama" -> true (becomes "amanaplanacanalpanama")
 * - "race a car" -> false (becomes "raceacar")
 * - " " -> true (becomes "", an empty string is a palindrome)
 * <p>
 * Approach:
 * 1. Filter the string to keep only alphanumeric characters, and convert all to lowercase
 * 2. Use two pointers (left and right) that move toward each other
 * 3. Compare characters at both pointers; if any differ, it's not a palindrome
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for creating a filtered string
 */
public class _323_b_ValidatePalindrome {
}
