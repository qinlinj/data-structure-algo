package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._816_space_compression;

import java.util.*;

/**
 * Handling Base Cases in Space Optimization
 * <p>
 * Summary:
 * - When performing space optimization in DP problems, we must carefully handle base cases
 * <p>
 * - Base cases from the 2D array need to be correctly "projected" onto the 1D array
 * <p>
 * - Key insights for handling base cases:
 * 1. Identify which base cases from the 2D version map to which positions in the 1D array
 * 2. Check for conflicts or overlaps when mapping
 * 3. Initialize the 1D array to properly represent base cases
 * <p>
 * - For the Longest Palindromic Subsequence example:
 * - The base case in 2D DP is dp[i][i] = 1 for all i (diagonal elements)
 * - When projected to 1D, this means initializing all elements in the 1D array to 1
 * <p>
 * - The mapped base cases should be consistent with how we're accessing elements during computation
 * <p>
 * - Base case handling is often the easiest part of space optimization, but must be done correctly
 * to ensure the algorithm works properly
 */
public class _816_d_HandlingBaseCases {

    /**
     * Original 2D DP solution for Longest Palindromic Subsequence,
     * showing explicit base case handling
     */
    public static int lps2D(String s) {
        int n = s.length();
        // Create 2D DP array
        int[][] dp = new int[n][n];

        // Explicitly handle base cases
        System.out.println("Setting up base cases in 2D array:");
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;  // Each character forms a palindrome of length 1
            System.out.println("dp[" + i + "][" + i + "] = 1  (Character '" + s.charAt(i) + "')");
        }

        // State transition logic (omitted for brevity)
        // ...

        return dp[0][n - 1];
    }

    /**
     * Space-optimized 1D solution for Longest Palindromic Subsequence,
     * focusing on base case handling
     */
    public static int lps1D(String s) {
        int n = s.length();
        // Create 1D DP array
        int[] dp = new int[n];

        // Handle base cases
        System.out.println("Setting up base cases in 1D array:");
        Arrays.fill(dp, 1);  // All single characters form palindromes of length 1
        System.out.println("Initialized all elements in dp[] to 1");

        // State transition logic (omitted for brevity)
        // ...

        return dp[n - 1];
    }

    /**
     * Demonstrates base case handling for the Edit Distance problem
     */
    public static void demonstrateEditDistanceBaseCases() {
        System.out.println("\nExample: Base Case Handling for Edit Distance Problem");
        System.out.println("====================================================");

        System.out.println("2D version base cases:");
        System.out.println("- dp[i][0] = i (cost of converting i characters to empty string)");
        System.out.println("- dp[0][j] = j (cost of converting empty string to j characters)");

        System.out.println("\nVisualizing 2D base cases for \"horse\" and \"ros\":");
        String word1 = "horse";
        String word2 = "ros";
        int m = word1.length();
        int n = word2.length();

        // Create and initialize 2D DP array
        int[][] dp2D = new int[m + 1][n + 1];

        // Print header
        System.out.print("      ");
        System.out.print("   "); // For the empty string column
        for (char c : word2.toCharArray()) {
            System.out.printf(" %c  ", c);
        }
        System.out.println();

        // Print the 2D array
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("   ");
            } else {
                System.out.printf(" %c ", word1.charAt(i - 1));
            }

            for (int j = 0; j <= n; j++) {
                // Base cases
                if (i == 0) {
                    dp2D[i][j] = j;
                } else if (j == 0) {
                    dp2D[i][j] = i;
                }
                System.out.printf(" %d  ", dp2D[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n1D space-optimized version base cases:");
        System.out.println("- Initial dp[j] = j for all j from 0 to n");
        System.out.println("- Before processing each row i, set dp[0] = i");

        // Demonstrate 1D array initialization
        int[] dp1D = new int[n + 1];
        for (int j = 0; j <= n; j++) {
            dp1D[j] = j;
        }

        System.out.print("Initial 1D array: [");
        for (int j = 0; j <= n; j++) {
            System.out.print(dp1D[j]);
            if (j < n) System.out.print(", ");
        }
        System.out.println("]");

        // Show how dp[0] gets updated for each row
        for (int i = 1; i <= m; i++) {
            dp1D[0] = i;
            System.out.print("After setting dp[0] = " + i + ": [");
            for (int j = 0; j <= n; j++) {
                System.out.print(dp1D[j]);
                if (j < n) System.out.print(", ");
            }
            System.out.println("]");

            // Would update the rest of dp1D here in actual implementation
        }
    }

    /**
     * Demonstrates base case handling for the Fibonacci sequence
     */
    public static void demonstrateFibonacciBaseCases() {
        System.out.println("\nExample: Base Case Handling for Fibonacci Sequence");
        System.out.println("================================================");

        System.out.println("Original recursive definition:");
        System.out.println("- fib(0) = 0");
        System.out.println("- fib(1) = 1");
        System.out.println("- fib(n) = fib(n-1) + fib(n-2) for n > 1");

        System.out.println("\nNaive 1D DP implementation with array:");
        int n = 10;
        int[] fib = new int[n + 1];
        fib[0] = 0;  // Base case
        fib[1] = 1;  // Base case

        System.out.println("Initialize: fib[0] = 0, fib[1] = 1");
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            System.out.println("fib[" + i + "] = fib[" + (i - 1) + "] + fib[" + (i - 2) + "] = " + fib[i - 1] + " + " + fib[i - 2] + " = " + fib[i]);
        }

        System.out.println("\nSpace-optimized version with only 2 variables:");
        int a = 0;  // fib(0)
        int b = 1;  // fib(1)

        System.out.println("Initialize: a = 0, b = 1");
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            System.out.println("fib(" + i + ") = a + b = " + a + " + " + b + " = " + temp);
            a = b;
            b = temp;
            System.out.println("Update: a = " + a + ", b = " + b);
        }

        System.out.println("\nFinal result: fib(" + n + ") = " + b);
    }

    /**
     * Demonstrates general principles for mapping base cases from 2D to 1D
     */
    public static void demonstrateBaseCaseMapping() {
        System.out.println("\nGeneral Principles for Mapping Base Cases from 2D to 1D");
        System.out.println("========================================================");

        System.out.println("1. Identify all base cases in the 2D version");
        System.out.println("   - Typically these are boundary conditions (i=0, j=0, i=j, etc.)");
        System.out.println("   - They're often set before the main DP computation begins");

        System.out.println("\n2. Determine how these map to the 1D array");
        System.out.println("   - If base cases are on the diagonal (i=j), they may all map to the same array");
        System.out.println("   - If base cases are on boundaries (i=0 or j=0), you may need special handling");

        System.out.println("\n3. Initialize the 1D array accordingly");
        System.out.println("   - Sometimes a simple Arrays.fill() is sufficient");
        System.out.println("   - Other times, you may need a loop to set specific values");

        System.out.println("\n4. Update base cases during computation if necessary");
        System.out.println("   - Some problems require updating base cases for each iteration");
        System.out.println("   - Example: In edit distance, dp[0] is updated for each row i");
    }

    public static void main(String[] args) {
        System.out.println("Handling Base Cases in Space Optimization");
        System.out.println("=========================================");

        // Example string for LPS
        String s = "babad";

        System.out.println("\nBase Case Handling for Longest Palindromic Subsequence of \"" + s + "\":");
        System.out.println("----------------------------------------------------------------");

        // Show the base cases for the 2D version
        lps2D(s);

        // Show the base cases for the 1D version
        lps1D(s);

        // Demonstrate base case handling in different problems
        demonstrateEditDistanceBaseCases();
        demonstrateFibonacciBaseCases();

        // Show general principles
        demonstrateBaseCaseMapping();
    }
}