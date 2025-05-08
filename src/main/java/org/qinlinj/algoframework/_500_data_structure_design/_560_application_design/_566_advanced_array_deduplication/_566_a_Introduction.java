package org.qinlinj.algoframework._500_data_structure_design._560_application_design._566_advanced_array_deduplication;

/**
 * REMOVING DUPLICATE LETTERS WHILE MAINTAINING LEXICOGRAPHICAL ORDER
 * <p>
 * This class introduces the problem of removing duplicate letters from a string
 * while maintaining the original relative order and achieving the smallest lexicographical result.
 * <p>
 * Problem Description (LeetCode 316):
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the lexicographically smallest among all possible results
 * while maintaining the relative order of characters in the original string.
 * <p>
 * Key Requirements:
 * 1. Remove all duplicate characters (each character appears exactly once)
 * 2. Maintain the relative order of characters from the original string
 * 3. Ensure the result has the smallest possible lexicographical order
 * <p>
 * Examples:
 * - Input: "bcabc" → Output: "abc"
 * - Input: "cbacdcbc" → Output: "acdb"
 * <p>
 * Prerequisites:
 * - Understanding of stacks and queues
 * - Knowledge of monotonic stack pattern
 * <p>
 * This problem represents one of the most challenging deduplication algorithms.
 * The solution combines deduplication with maintaining ordering constraints.
 */
public class _566_a_Introduction {

    /**
     * This method demonstrates a simple deduplication approach without
     * considering lexicographical ordering - just to introduce the concept.
     * <p>
     * Note: This does NOT solve the full problem, as it doesn't produce
     * the lexicographically smallest result.
     */
    public static String simpleDeduplication(String s) {
        // Using a boolean array to track characters we've seen
        boolean[] seen = new boolean[26]; // assuming lowercase English letters only
        StringBuilder result = new StringBuilder();

        // Iterate through each character
        for (char c : s.toCharArray()) {
            // If we haven't seen this character before, add it to result
            if (!seen[c - 'a']) {
                seen[c - 'a'] = true;
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Examples to show the difference between simple deduplication and the actual problem
        String s1 = "bcabc";
        String s2 = "cbacdcbc";

        System.out.println("Simple deduplication of '" + s1 + "': " + simpleDeduplication(s1));
        System.out.println("Required output (considering lexicographical order): abc");

        System.out.println("Simple deduplication of '" + s2 + "': " + simpleDeduplication(s2));
        System.out.println("Required output (considering lexicographical order): acdb");

        System.out.println("\nNote: The simple deduplication approach doesn't give the correct answer");
        System.out.println("for this problem as it doesn't consider the lexicographical ordering requirement.");
        System.out.println("See the other classes for the proper solution using a monotonic stack approach.");
    }
}