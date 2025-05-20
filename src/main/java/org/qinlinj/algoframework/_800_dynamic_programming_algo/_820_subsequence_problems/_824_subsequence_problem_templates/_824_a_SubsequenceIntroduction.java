package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

/**
 * Introduction to Subsequence Problems
 * <p>
 * Key Concepts:
 * 1. Subsequence problems are generally more difficult than substring or subarray problems
 * because subsequences don't need to be contiguous.
 * 2. Many subsequence problems involve finding the "longest" subsequence with certain properties.
 * 3. Most subsequence problems can be solved efficiently using dynamic programming,
 * typically with O(n²) time complexity.
 * 4. Subsequence problems often require careful definition of DP arrays and state transitions.
 * <p>
 * This class introduces the general approach to subsequence problems, highlighting their
 * importance, difficulty, and the two main DP templates used to solve them.
 */
public class _824_a_SubsequenceIntroduction {

    /**
     * A simple method to demonstrate what a subsequence is compared to a substring
     */
    public static void explainSubsequenceVsSubstring(String s) {
        System.out.println("Original string: " + s);

        // Example of a substring (contiguous)
        String substring = s.substring(1, 4);
        System.out.println("A substring: " + substring);

        // Example of a subsequence (not necessarily contiguous)
        StringBuilder subsequence = new StringBuilder();
        for (int i = 0; i < s.length(); i += 2) {
            subsequence.append(s.charAt(i));
        }
        System.out.println("A subsequence: " + subsequence.toString());

        System.out.println("\nKey differences:");
        System.out.println("- Substrings are contiguous portions of the original string");
        System.out.println("- Subsequences maintain relative order but can skip characters");
        System.out.println("- Number of possible substrings: O(n²)");
        System.out.println("- Number of possible subsequences: O(2ⁿ)");
    }

    /**
     * Demonstrate the two main DP templates for subsequence problems
     */
    public static void showDPTemplates() {
        System.out.println("\nTwo main DP templates for subsequence problems:");

        System.out.println("\n1. One-dimensional DP template:");
        System.out.println("   Used for: Single-sequence problems like Longest Increasing Subsequence");
        System.out.println("   DP definition: dp[i] represents the optimal solution ending at index i");
        System.out.println("   Template code:");
        System.out.println("   ```");
        System.out.println("   int n = array.length;");
        System.out.println("   int[] dp = new int[n];");
        System.out.println("   // Initialize dp array (often dp[i] = 1 for LIS-like problems)");
        System.out.println("   ");
        System.out.println("   for (int i = 1; i < n; i++) {");
        System.out.println("       for (int j = 0; j < i; j++) {");
        System.out.println("           if (some condition about array[i] and array[j])");
        System.out.println("               dp[i] = Math.max(dp[i], dp[j] + 1);");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println("   ```");

        System.out.println("\n2. Two-dimensional DP template:");
        System.out.println("   Used for: Two-sequence problems like Longest Common Subsequence");
        System.out.println("   or single-sequence range problems like Longest Palindromic Subsequence");
        System.out.println("   DP definition: dp[i][j] represents the optimal solution for a specific range or two sequences");
        System.out.println("   Template code:");
        System.out.println("   ```");
        System.out.println("   int n = array.length;");
        System.out.println("   int[][] dp = new int[n][n];");
        System.out.println("   // Initialize dp array with base cases");
        System.out.println("   ");
        System.out.println("   for (int i = n-1; i >= 0; i--) {");
        System.out.println("       for (int j = i+1; j < n; j++) {");
        System.out.println("           if (some condition about array[i] and array[j])");
        System.out.println("               dp[i][j] = dp[i+1][j-1] + 2;  // or some other transition");
        System.out.println("           else");
        System.out.println("               dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println("   ```");
    }

    /**
     * Main method to demonstrate the concepts
     */
    public static void main(String[] args) {
        System.out.println("=== INTRODUCTION TO SUBSEQUENCE PROBLEMS ===\n");
        System.out.println("Subsequence problems are common algorithmic challenges that typically involve:");
        System.out.println("- Finding the longest subsequence with specific properties");
        System.out.println("- Using dynamic programming for efficient solutions");
        System.out.println("- Carefully defining state transitions\n");

        // Show the difference between substring and subsequence
        explainSubsequenceVsSubstring("algorithm");

        // Show the two main templates
        showDPTemplates();

        System.out.println("\nKey Insight: When you see a problem about finding the longest subsequence");
        System.out.println("with certain properties, immediately think about using dynamic programming");
        System.out.println("with one of these two templates!");
    }
}