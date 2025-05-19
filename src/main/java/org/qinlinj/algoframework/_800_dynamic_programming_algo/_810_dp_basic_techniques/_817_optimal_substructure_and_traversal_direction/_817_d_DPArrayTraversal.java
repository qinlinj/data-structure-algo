package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * DP ARRAY TRAVERSAL DIRECTION IN DYNAMIC PROGRAMMING
 * <p>
 * Key Points:
 * <p>
 * 1. The traversal direction of DP arrays can vary based on problem-specific dependencies:
 * - Forward traversal (i from 0 to n)
 * - Backward traversal (i from n to 0)
 * - Diagonal traversal
 * <p>
 * 2. Two key principles determine the correct traversal direction:
 * - Every state must be calculated before it's needed by other states
 * - The final result's location must be computed by the end of traversal
 * <p>
 * 3. The traversal direction depends on:
 * - Location of base cases in the DP array
 * - The state transition equation (which states a given state depends on)
 * - The location of the final answer in the DP array
 * <p>
 * 4. Examples:
 * - Edit Distance: Base cases are at dp[0][j] and dp[i][0],
 * and dp[i][j] depends on values to its left, top, and top-left.
 * Therefore, forward traversal works.
 * - Palindrome Subsequence: Base cases are on the diagonal,
 * and dp[i][j] depends on values to its right, bottom, and bottom-right.
 * Therefore, either diagonal or backward traversal works.
 * <p>
 * 5. Some problems may have multiple valid traversal methods that all produce
 * correct results.
 */
public class _817_d_DPArrayTraversal {

    /**
     * Example 1: Edit Distance Problem using forward traversal
     * Base cases: dp[0][j] and dp[i][0]
     * Transition: dp[i][j] depends on dp[i-1][j-1], dp[i-1][j], dp[i][j-1]
     * Final answer: dp[m][n]
     */
    public static int editDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base cases - first row and column
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Cost to convert s1[0...i-1] to empty string
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Cost to convert empty string to s2[0...j-1]
        }

        // Forward traversal - from top-left to bottom-right
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            Math.min(dp[i - 1][j], dp[i][j - 1]),
                            dp[i - 1][j - 1]
                    );
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Example 2: Longest Palindromic Subsequence using backward traversal
     * Base cases: dp[i][i] = 1 for all i (diagonal)
     * Transition: dp[i][j] depends on dp[i+1][j], dp[i][j-1], dp[i+1][j-1]
     * Final answer: dp[0][n-1]
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: every single character is a palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Backward traversal - from bottom-right to top-left
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

    /**
     * Example 2 Alternative: Longest Palindromic Subsequence using diagonal traversal
     * This produces the same result as the backward traversal method
     */
    public static int longestPalindromeSubseqDiagonal(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: every single character is a palindrome of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Diagonal traversal - by length of substrings
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    /**
     * Helper method to visualize the state dependencies in Edit Distance problem
     * Shows which cells each dp[i][j] depends on
     */
    public static void visualizeEditDistanceDependencies() {
        System.out.println("Edit Distance DP Dependencies:");
        System.out.println("dp[i][j] depends on:");
        System.out.println("  - dp[i-1][j-1] (top-left)");
        System.out.println("  - dp[i-1][j]   (top)");
        System.out.println("  - dp[i][j-1]   (left)");
        System.out.println("\nTraversal must ensure these cells are calculated first.");
        System.out.println("Forward traversal (top to bottom, left to right) works!");
    }

    /**
     * Helper method to visualize the state dependencies in Palindrome Subsequence problem
     * Shows which cells each dp[i][j] depends on
     */
    public static void visualizePalindromeDependencies() {
        System.out.println("Palindrome Subsequence DP Dependencies:");
        System.out.println("dp[i][j] depends on:");
        System.out.println("  - dp[i+1][j-1] (bottom-left)");
        System.out.println("  - dp[i+1][j]   (bottom)");
        System.out.println("  - dp[i][j-1]   (left)");
        System.out.println("\nPossible traversal methods:");
        System.out.println("1. Bottom-up (i from n-1 to 0)");
        System.out.println("2. Diagonal (by increasing substring length)");
    }

    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";
        String palindrome = "bbbab";

        System.out.println("Edit Distance (Forward Traversal): " + editDistance(s1, s2));

        System.out.println("\nLongest Palindromic Subsequence (Backward Traversal): " +
                longestPalindromeSubseq(palindrome));

        System.out.println("Longest Palindromic Subsequence (Diagonal Traversal): " +
                longestPalindromeSubseqDiagonal(palindrome));

        System.out.println();
        visualizeEditDistanceDependencies();

        System.out.println();
        visualizePalindromeDependencies();
    }
}