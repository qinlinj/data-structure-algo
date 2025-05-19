package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._821_edit_distance;

/**
 * EDIT DISTANCE PROBLEM INTRODUCTION
 * <p>
 * Key Points:
 * <p>
 * 1. The Edit Distance problem (LeetCode 72) calculates the minimum number of operations
 * required to transform one string into another.
 * <p>
 * 2. Three allowed operations:
 * - Insert a character
 * - Delete a character
 * - Replace a character
 * <p>
 * 3. Real-world applications:
 * - Text correction and proofreading
 * - DNA sequence similarity measurement
 * - Spell checking
 * - Natural language processing
 * <p>
 * 4. Example:
 * - Transform "horse" to "ros" requires 3 operations:
 * * Replace 'h' with 'r'
 * * Delete 'r'
 * * Delete 'e'
 * <p>
 * 5. The problem exemplifies how dynamic programming can efficiently solve
 * string manipulation problems with optimal solutions.
 */
public class _821_a_EditDistanceIntroduction {

    /**
     * Basic implementation of the edit distance calculation.
     * This provides a simple interface to solve the problem.
     */
    public static int minDistance(String word1, String word2) {
        // Delegate to one of our solution methods
        return minDistanceDP(word1, word2);
    }

    /**
     * Dynamic programming solution for the edit distance problem.
     * This will be elaborated in later classes.
     */
    private static int minDistanceDP(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            Math.min(dp[i - 1][j],     // Delete
                                    dp[i][j - 1]),     // Insert
                            dp[i - 1][j - 1]           // Replace
                    );
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Demonstrate the edit distance with examples from the problem statement
     */
    public static void main(String[] args) {
        // Example 1
        String word1 = "horse";
        String word2 = "ros";
        int distance1 = minDistance(word1, word2);
        System.out.println("Edit distance between \"" + word1 + "\" and \"" + word2 + "\": " + distance1);
        System.out.println("Operations:");
        System.out.println("1. Replace 'h' with 'r' -> \"rorse\"");
        System.out.println("2. Delete 'r' -> \"rose\"");
        System.out.println("3. Delete 'e' -> \"ros\"");

        // Example 2
        String word3 = "intention";
        String word4 = "execution";
        int distance2 = minDistance(word3, word4);
        System.out.println("\nEdit distance between \"" + word3 + "\" and \"" + word4 + "\": " + distance2);
        System.out.println("Operations:");
        System.out.println("1. Delete 't' -> \"inention\"");
        System.out.println("2. Replace 'i' with 'e' -> \"enention\"");
        System.out.println("3. Replace 'n' with 'x' -> \"exention\"");
        System.out.println("4. Replace 'n' with 'c' -> \"exection\"");
        System.out.println("5. Insert 'u' -> \"execution\"");
    }
}
