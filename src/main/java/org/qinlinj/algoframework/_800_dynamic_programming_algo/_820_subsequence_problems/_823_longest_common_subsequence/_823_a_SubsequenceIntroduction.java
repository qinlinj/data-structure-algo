package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

public class _823_a_SubsequenceIntroduction {
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
}
