package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * Class: DP Array Traversal Direction
 * <p>
 * Key Points:
 * 1. The traversal direction of a DP array depends on two principles:
 * a. During traversal, the states needed must already be calculated.
 * b. After traversal, the position storing the result must be calculated.
 * 2. Different traversal directions:
 * a. Forward traversal: top-left to bottom-right
 * b. Reverse traversal: bottom-right to top-left
 * c. Diagonal traversal: for certain problems
 * 3. The choice of traversal direction depends on:
 * a. Location of base cases
 * b. Location of the final result
 * c. Dependencies between states in the state transition equation
 * 4. Sometimes multiple traversal directions can yield correct results.
 */
public class _817_d_DPArrayTraversal {

    // Example 1: Edit Distance - Forward Traversal
    // Base cases are dp[0][j] and dp[i][0]
    // Final result is at dp[m][n]
    // dp[i][j] depends on dp[i-1][j], dp[i][j-1], and dp[i-1][j-1]
    public int editDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialize base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        // Forward traversal (top-left to bottom-right)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j], dp[i][j - 1]),
                            dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[m][n];
    }

    // Example 2: Longest Palindromic Subsequence - Reverse Traversal
    // Base cases are dp[i][i] (diagonals)
    // Final result is at dp[0][n-1]
    // dp[i][j] depends on dp[i+1][j], dp[i][j-1], and dp[i+1][j-1]
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Reverse traversal (bottom-up)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    // Example 3: Alternative traversal for Longest Palindromic Subsequence - Diagonal
    public int longestPalindromeSubseqDiagonal(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Diagonal traversal
        // l is the length of the substring
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    // Example 4: Matrix Chain Multiplication - Another example of diagonal traversal
    public int matrixChainMultiplication(int[] dims) {
        int n = dims.length - 1; // Number of matrices
        int[][] dp = new int[n][n];

        // Base case: Cost is 0 when multiplying a single matrix
        // (implicitly set by initializing dp with zeros)

        // Diagonal traversal for increasing chain lengths
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;

                // Try different partition points
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }
}