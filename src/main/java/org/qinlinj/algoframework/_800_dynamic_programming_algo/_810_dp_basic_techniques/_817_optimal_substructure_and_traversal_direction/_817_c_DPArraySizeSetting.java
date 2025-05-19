package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * Class: DP Array Size Setting
 * <p>
 * Key Points:
 * 1. The size of the DP array often needs to be n+1 instead of n to handle base cases properly.
 * 2. This is commonly needed when base cases involve empty strings or collections (index -1).
 * 3. The index offset allows us to use index 0 for representing empty states.
 * 4. The definition of what dp[i][j] represents should be clear and consistent.
 */
public class _817_c_DPArraySizeSetting {

    // Example: Edit Distance problem 
    // Recursive solution (top-down)
    public int minDistanceRecursive(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // Calculate the minimum edit distance between s1 and s2
        return dp(s1, m - 1, s2, n - 1);
    }

    // dp function definition: minimum edit distance between s1[0..i] and s2[0..j]
    private int dp(String s1, int i, String s2, int j) {
        // Base cases: when one string is empty, the edit distance is the length of the other string
        if (i == -1) return j + 1; // Need to insert j+1 characters
        if (j == -1) return i + 1; // Need to delete i+1 characters

        // If characters match, no operation needed for these characters
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp(s1, i - 1, s2, j - 1);
        } else {
            // Choose the minimum cost operation: insert, delete, or replace
            return min(
                    dp(s1, i, s2, j - 1) + 1,     // Insert
                    dp(s1, i - 1, s2, j) + 1,     // Delete
                    dp(s1, i - 1, s2, j - 1) + 1  // Replace
            );
        }
    }

    // Iterative solution (bottom-up) with dp array size n+1
    public int minDistanceIterative(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        // dp[i][j] represents edit distance between s1[0..i-1] and s2[0..j-1]
        // Size is m+1 and n+1 to include the empty string cases
        int[][] dp = new int[m + 1][n + 1];

        // Base cases: when one string is empty
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;  // Delete i characters from s1
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;  // Insert j characters into s1

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];  // No operation needed
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,     // Delete
                            dp[i][j - 1] + 1,     // Insert
                            dp[i - 1][j - 1] + 1    // Replace
                    );
                }
            }
        }

        // The answer is stored at dp[m][n]
        return dp[m][n];
    }

    // Helper function to find minimum of three values
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    // Alternative: using dp array size n (less common, more complicated with base cases)
    public int minDistanceAlternative(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        // Need special handling for empty strings
        if (m == 0) return n;
        if (n == 0) return m;

        // dp[i][j] represents edit distance between s1[0..i] and s2[0..j]
        int[][] dp = new int[m][n];

        // Initialize first row and column (more complicated)
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 0 : 1;

        for (int i = 1; i < m; i++)
            dp[i][0] = s1.charAt(i) == s2.charAt(0) ? i : dp[i - 1][0] + 1;

        for (int j = 1; j < n; j++)
            dp[0][j] = s1.charAt(0) == s2.charAt(j) ? j : dp[0][j - 1] + 1;

        // Fill the rest of the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }

        // The answer is stored at dp[m-1][n-1]
        return dp[m - 1][n - 1];
    }
}