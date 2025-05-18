package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._816_space_compression;

import java.util.*;

/**
 * Space Optimization Technique: 2D to 1D Array Transformation
 * <p>
 * Summary:
 * - This class demonstrates how to optimize the space complexity of 2D dynamic programming problems
 * by transforming a 2D array into a 1D array
 * <p>
 * - The key concept is "projection" - mapping a 2D array onto a 1D array by eliminating one dimension
 * <p>
 * - Challenge: When projecting, multiple cells from the 2D array map to the same cell in the 1D array,
 * potentially causing values to be overwritten before they're needed
 * <p>
 * - Solution techniques:
 * 1. Carefully analyze the order of computation
 * 2. Use temporary variables to store values that might be overwritten
 * 3. Adjust the iteration order if necessary
 * <p>
 * - This optimization typically reduces space complexity from O(n²) to O(n)
 * <p>
 * - While this optimization makes the code less readable, it can be necessary for
 * problems with tight memory constraints or very large inputs
 */
public class _816_c_TwoDToOneDTransformation {

    /**
     * The original 2D dynamic programming solution for Longest Palindromic Subsequence
     * Time Complexity: O(n²)
     * Space Complexity: O(n²)
     */
    public static int lps2D(String s) {
        int n = s.length();
        // Create 2D DP array
        int[][] dp = new int[n][n];

        // Base case: single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // State transition equation
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // The result is the LPS length for the entire string
        return dp[0][n - 1];
    }

    /**
     * Space-optimized 1D dynamic programming solution for Longest Palindromic Subsequence
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     */
    public static int lps1D(String s) {
        int n = s.length();

        // Create 1D DP array
        int[] dp = new int[n];

        // Base case: single characters are palindromes of length 1
        Arrays.fill(dp, 1);

        // Fill the DP array
        for (int i = n - 2; i >= 0; i--) {
            // Store dp[i+1][j-1] before it gets overwritten
            int prev = 0;  // represents dp[i+1][j-1] initially

            for (int j = i + 1; j < n; j++) {
                // Store the current dp[j] (which represents dp[i+1][j])
                // before it gets overwritten
                int temp = dp[j];

                // State transition equation
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = prev + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                // Update prev for the next iteration
                // Now prev will hold the original dp[i+1][j]
                // which becomes dp[i+1][j-1] for the next j
                prev = temp;
            }
        }

        // The result is the LPS length for the entire string
        return dp[n - 1];
    }

    /**
     * Demonstrates how the 1D optimization works by showing the state of the array
     * during computation of a simple example
     */
    public static void demonstrateLPS1DExample() {
        String s = "babad";
        int n = s.length();

        System.out.println("Step-by-Step Space Optimization for LPS of \"" + s + "\":");
        System.out.println("===========================================================");

        // Base case
        int[] dp = new int[n];
        Arrays.fill(dp, 1);  // All diagonal elements are 1

        System.out.println("Initial 1D array (base case - all single chars are palindromes of length 1):");
        printArray(dp, s);

        // Visualize the 2D to 1D mapping
        System.out.println("\nVisualizing 2D to 1D mapping:");
        System.out.println("Each row of the 2D array is mapped to the same 1D array:");
        visualize2DTo1DMapping(s);

        // Fill the DP array with detailed explanation
        for (int i = n - 2; i >= 0; i--) {
            System.out.println("\nProcessing row i = " + i + " (character '" + s.charAt(i) + "'):");

            int prev = 0;  // Represents dp[i+1][i] initially (which is outside the valid range)

            for (int j = i + 1; j < n; j++) {
                System.out.println("\n  Calculating dp[" + i + "][" + j + "] ('" + s.charAt(i) + "' to '" + s.charAt(j) + "'):");
                System.out.println("  Current 1D array represents row " + (i + 1) + " of the 2D array.");
                System.out.println("  dp[" + j + "] in 1D array = dp[" + (i + 1) + "][" + j + "] in 2D array = " + dp[j]);
                System.out.println("  dp[" + (j - 1) + "] in 1D array = dp[" + i + "][" + (j - 1) + "] in 2D array = " + dp[j - 1]);
                System.out.println("  prev stores dp[" + (i + 1) + "][" + (j - 1) + "] from 2D array = " + prev);

                // Store the current dp[j] before it gets overwritten
                int temp = dp[j];

                // State transition equation
                if (s.charAt(i) == s.charAt(j)) {
                    System.out.println("  Characters match! dp[" + i + "][" + j + "] = dp[" + (i + 1) + "][" + (j - 1) + "] + 2 = " + prev + " + 2 = " + (prev + 2));
                    dp[j] = prev + 2;
                } else {
                    System.out.println("  Characters don't match! dp[" + i + "][" + j + "] = max(dp[" + (i + 1) + "][" + j + "], dp[" + i + "][" + (j - 1) + "]) = max(" + dp[j] + ", " + dp[j - 1] + ") = " + Math.max(dp[j], dp[j - 1]));
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                // Update prev for the next iteration
                prev = temp;

                System.out.println("  1D array after this update:");
                printArray(dp, s);
                System.out.println("  'prev' is now updated to: " + prev + " (original dp[" + (i + 1) + "][" + j + "])");
            }

            System.out.println("\n1D array after processing row i = " + i + ":");
            printArray(dp, s);
            System.out.println("This row now represents row " + i + " of the 2D array.");
        }

        System.out.println("\nFinal result: The longest palindromic subsequence has length " + dp[n - 1]);
    }

    /**
     * Helper method to print a 1D array with characters as column headers
     */
    private static void printArray(int[] dp, String s) {
        System.out.print("Index: ");
        for (int j = 0; j < dp.length; j++) {
            System.out.printf("%3d ", j);
        }
        System.out.println();

        System.out.print("Char:  ");
        for (int j = 0; j < dp.length; j++) {
            System.out.printf("%3c ", s.charAt(j));
        }
        System.out.println();

        System.out.print("Value: ");
        for (int j = 0; j < dp.length; j++) {
            System.out.printf("%3d ", dp[j]);
        }
        System.out.println();
    }

    /**
     * Visualizes how a 2D array is mapped to a 1D array
     */
    private static void visualize2DTo1DMapping(String s) {
        int n = s.length();

        // Print the 2D array structure
        System.out.println("2D DP array (conceptually):");
        System.out.println("   |  " + String.join("  ", s.split("")));
        System.out.println("---+" + "-".repeat(3 * n));

        for (int i = 0; i < n; i++) {
            System.out.print(" " + s.charAt(i) + " |");
            for (int j = 0; j < n; j++) {
                if (j < i) {
                    System.out.print("   ");
                } else if (j == i) {
                    System.out.print(" 1 ");
                } else {
                    System.out.print(" ? ");
                }
            }
            System.out.println();
        }

        // Show how multiple rows map to one 1D array
        System.out.println("\nMapping to 1D array:");
        System.out.println("Each row above is stored in the same 1D array:");
        System.out.println("1D:  [" + String.join(", ", s.split("")) + "]");
        System.out.println("     (Indexes 0 to " + (n - 1) + ")");

        System.out.println("\nKey insight: When processing row i, the 1D array contains values from row i+1");
        System.out.println("Challenge: We need dp[i+1][j-1] for the calculation, but it would be overwritten");
        System.out.println("Solution: Use a 'prev' variable to store dp[i+1][j-1] before it gets overwritten");
    }

    /**
     * Demonstrates the mapping of indexes between 2D and 1D arrays
     */
    public static void demonstrateIndexMapping() {
        System.out.println("\nIndex Mapping Between 2D and 1D Arrays:");
        System.out.println("=========================================");

        System.out.println("When calculating dp[i][j] in space-optimized version:");
        System.out.println("- dp[j] in 1D array corresponds to dp[i+1][j] in 2D array (before update)");
        System.out.println("- dp[j-1] in 1D array corresponds to dp[i][j-1] in 2D array (after update for j-1)");
        System.out.println("- We need to use a temporary variable to preserve dp[i+1][j-1]");
    }

    public static void main(String[] args) {
        System.out.println("Space Optimization Technique: 2D to 1D Array Transformation");
        System.out.println("==========================================================");

        // Compare the results of 2D and 1D implementations
        String[] testCases = {"bbbab", "cbbd", "racecar", "aaa"};

        System.out.println("Comparing 2D and 1D Implementations:");
        System.out.println("------------------------------------");

        for (String s : testCases) {
            int result2D = lps2D(s);
            int result1D = lps1D(s);

            System.out.printf("String: %s, 2D result: %d, 1D result: %d, Match: %b%n",
                    s, result2D, result1D, result2D == result1D);
        }

        // Demonstrate the index mapping
        demonstrateIndexMapping();

        // Detailed walkthrough of the 1D optimization
        System.out.println("\n\nDetailed Walkthrough of Space Optimization:");
        demonstrateLPS1DExample();
    }
}
