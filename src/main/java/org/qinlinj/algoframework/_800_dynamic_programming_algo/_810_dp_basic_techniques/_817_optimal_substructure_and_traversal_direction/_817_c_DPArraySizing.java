package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * DP ARRAY SIZING IN DYNAMIC PROGRAMMING
 * <p>
 * Key Points:
 * <p>
 * 1. The size of a DP array is often n+1 rather than n to handle base cases elegantly.
 * <p>
 * 2. When a recursive solution has base cases that use negative indices (like -1),
 * the iterative DP solution needs a way to represent these cases.
 * <p>
 * 3. By initializing the DP array with size n+1, we can:
 * - Use index 0 to represent "empty" or base cases
 * - Shift all other indices by +1 (dp[i+1][j+1] represents the state that dp(i,j) represented)
 * <p>
 * 4. This approach avoids negative indexing and provides a natural place for base cases.
 * <p>
 * 5. Example: In the Edit Distance problem, dp(s1, i, s2, j) calculates edit distance
 * between s1[0...i] and s2[0...j]. Base cases are when i=-1 or j=-1 (empty strings).
 * In the iterative solution, we use dp[i+1][j+1] to store this value, using indices 0
 * to represent empty strings.
 */
public class _817_c_DPArraySizing {

    /**
     * Example: Edit Distance Problem
     * Recursive solution with natural base cases at i=-1 or j=-1
     */
    public static int minDistanceRecursive(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // Calculate the edit distance between entire strings
        return dp(s1, m - 1, s2, n - 1);
    }

    /**
     * Definition: dp(s1, i, s2, j) is the edit distance between s1[0...i] and s2[0...j]
     */
    private static int dp(String s1, int i, String s2, int j) {
        // Base cases: when one string is empty, the distance is the length of the other
        if (i == -1) {
            return j + 1; // Need to insert j+1 characters
        }
        if (j == -1) {
            return i + 1; // Need to delete i+1 characters
        }

        // If characters match, no operation needed for these characters
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp(s1, i - 1, s2, j - 1);
        } else {
            // Take the minimum of three operations: insert, delete, replace
            return min(
                    dp(s1, i, s2, j - 1) + 1,     // Insert
                    dp(s1, i - 1, s2, j) + 1,     // Delete
                    dp(s1, i - 1, s2, j - 1) + 1  // Replace
            );
        }
    }

    /**
     * Helper function to find minimum of three values
     */
    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * Iterative DP solution with array sized m+1 by n+1
     * Definition: dp[i+1][j+1] is the edit distance between s1[0...i] and s2[0...j]
     */
    public static int minDistanceIterative(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        // Initialize DP array with size (m+1) x (n+1)
        // This lets us use index 0 for the empty string base case
        int[][] dp = new int[m + 1][n + 1];

        // Base cases:
        // dp[i][0] = edit distance between s1[0...i-1] and empty string = i deletes
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        // dp[0][j] = edit distance between empty string and s2[0...j-1] = j inserts
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Note the -1 offset when accessing string characters
                // dp[i][j] represents s1[0...i-1] and s2[0...j-1]
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = min(
                            dp[i][j - 1] + 1,      // Insert
                            dp[i - 1][j] + 1,      // Delete
                            dp[i - 1][j - 1] + 1   // Replace
                    );
                }
            }
        }

        return dp[m][n]; // Final answer
    }

    /**
     * Alternative approach with DP array sized exactly m x n
     * This would require special handling for base cases
     */
    public static int minDistanceIterativeAlternative(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        // Edge cases
        if (m == 0) return n;
        if (n == 0) return m;

        // Initialize DP array with size m x n
        int[][] dp = new int[m][n];

        // Initialize first row and column
        // Different base case handling than the m+1 x n+1 version
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 0 : 1;

        for (int i = 1; i < m; i++) {
            dp[i][0] = s1.charAt(i) == s2.charAt(0) ? dp[i - 1][0] : dp[i - 1][0] + 1;
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = s1.charAt(0) == s2.charAt(j) ? dp[0][j - 1] : dp[0][j - 1] + 1;
        }

        // Fill DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i][j - 1] + 1,     // Insert
                            dp[i - 1][j] + 1,     // Delete
                            dp[i - 1][j - 1] + 1  // Replace
                    );
                }
            }
        }

        return dp[m - 1][n - 1]; // Final answer
    }

    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";

        System.out.println("Edit Distance (Recursive): " + minDistanceRecursive(s1, s2));
        System.out.println("Edit Distance (Iterative with m+1 x n+1 array): " + minDistanceIterative(s1, s2));
        System.out.println("Edit Distance (Iterative with m x n array): " + minDistanceIterativeAlternative(s1, s2));

        // Explain the advantage of m+1 x n+1 sizing
        System.out.println("\nAdvantage of (m+1) x (n+1) DP array:");
        System.out.println("1. Natural handling of base cases (empty strings)");
        System.out.println("2. Avoids special case handling for first row/column");
        System.out.println("3. More closely matches the recursive definition where i=-1 or j=-1 are valid states");
    }
}