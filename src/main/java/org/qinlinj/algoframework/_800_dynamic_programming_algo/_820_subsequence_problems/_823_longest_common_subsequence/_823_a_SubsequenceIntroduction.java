package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

/**
 * Introduction to Subsequence Problems and Dynamic Programming
 * <p>
 * Key Concepts:
 * - Algorithm problems are often best solved by breaking them down into smaller subproblems
 * - For subsequence problems, we typically focus on individual characters in the strings
 * - Dynamic Programming is a powerful technique for solving subsequence problems
 * - This is a common pattern: use two pointers (i and j) to traverse two strings, which often
 * leads to a dynamic programming solution
 * <p>
 * This class introduces the approach to solving subsequence problems, particularly focusing on
 * the Longest Common Subsequence (LCS) and related problems. It demonstrates how to break down
 * complex string problems into manageable subproblems.
 */
public class _823_a_SubsequenceIntroduction {

    /**
     * A simple method to demonstrate the concept of breaking down a problem
     * This is just a conceptual example, not an actual algorithm implementation
     */
    public static void demonstrateSubsequenceApproach(String s1, String s2) {
        System.out.println("Analyzing strings: \"" + s1 + "\" and \"" + s2 + "\"");
        System.out.println("When solving subsequence problems, we typically:");
        System.out.println("1. Focus on individual characters (not entire strings)");
        System.out.println("2. Use two pointers (i, j) to traverse both strings");
        System.out.println("3. Define a recurrence relation based on character comparisons");
        System.out.println("4. Use dynamic programming to avoid recalculating subproblems");

        // Display a simple visualization of comparing characters
        System.out.println("\nSimple visualization of character comparison:");
        int i = 0, j = 0;
        System.out.println("Comparing s1[" + i + "]=" + s1.charAt(i) +
                " and s2[" + j + "]=" + s2.charAt(j));

        if (s1.charAt(i) == s2.charAt(j)) {
            System.out.println("Characters match: This character is likely part of the common subsequence");
        } else {
            System.out.println("Characters don't match: Need to decide which character to skip");
            System.out.println("Option 1: Skip s1[" + i + "] and compare s1[" + (i + 1) + "] with s2[" + j + "]");
            System.out.println("Option 2: Skip s2[" + j + "] and compare s1[" + i + "] with s2[" + (j + 1) + "]");
        }
    }

    /**
     * Main method to demonstrate the concepts
     */
    public static void main(String[] args) {
        String s1 = "zabcde";
        String s2 = "acez";

        System.out.println("Subsequence Problems Introduction\n");
        System.out.println("For subsequence problems involving two strings, we typically use");
        System.out.println("dynamic programming with two pointers moving through the strings.\n");

        demonstrateSubsequenceApproach(s1, s2);

        System.out.println("\nThis approach can be applied to various subsequence problems:");
        System.out.println("1. Longest Common Subsequence (LCS)");
        System.out.println("2. Minimum Deletion Operations to Make Strings Equal");
        System.out.println("3. Minimum ASCII Delete Sum for Two Strings");
    }
}
