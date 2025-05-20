package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

public class _823_c_DeleteOperationForTwoStrings {
    /**
     * Main method to demonstrate the algorithm
     */
    public static void main(String[] args) {
        _823_c_DeleteOperationForTwoStrings solution = new _823_c_DeleteOperationForTwoStrings();

        // Example 1 from LeetCode
        String s1 = "sea";
        String s2 = "eat";

        int result = solution.minDistance(s1, s2);
        System.out.println("Minimum deletions required: " + result);

        // Detailed explanation
        System.out.println("\n--- Detailed Explanation ---");
        solution.explainWithExample(s1, s2);

        // Another example
        System.out.println("\n\n--- Another Example ---");
        s1 = "delete";
        s2 = "leet";
        result = solution.minDistance(s1, s2);
        System.out.println("Minimum deletions required: " + result);
        solution.explainWithExample(s1, s2);
    }

    /**
     * Calculate the minimum number of deletions required
     */
    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Find the length of the longest common subsequence
        int lcsLength = longestCommonSubsequence(s1, s2);

        // Calculate deletions:
        // (characters to remove from s1) + (characters to remove from s2)
        return (m - lcsLength) + (n - lcsLength);
    }

    /**
     * Calculate the length of the longest common subsequence (bottom-up DP approach)
     */
    private int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Define dp[i][j] = length of LCS for s1[0...i-1] and s2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Visual demonstration of the solution process
     */
    public void explainWithExample(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Step 1: Calculate LCS using the dp approach
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcsLength = dp[m][n];

        // Step 2: Reconstruct the LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        String lcsString = lcs.reverse().toString();

        // Step 3: Calculate the deletions
        int deletionsFromS1 = m - lcsLength;
        int deletionsFromS2 = n - lcsLength;
        int totalDeletions = deletionsFromS1 + deletionsFromS2;

        // Display the explanation
        System.out.println("Given strings:");
        System.out.println("s1 = \"" + s1 + "\" (length: " + m + ")");
        System.out.println("s2 = \"" + s2 + "\" (length: " + n + ")");

        System.out.println("\nStep 1: Find the Longest Common Subsequence (LCS)");
        System.out.println("LCS = \"" + lcsString + "\" (length: " + lcsLength + ")");

        System.out.println("\nStep 2: Calculate minimum deletions");
        System.out.println("Characters to delete from s1: " + deletionsFromS1 + " (s1.length - LCS.length)");
        System.out.println("Characters to delete from s2: " + deletionsFromS2 + " (s2.length - LCS.length)");
        System.out.println("Total deletions required: " + totalDeletions);

        // Show which characters are deleted and which remain
        System.out.println("\nVisual Representation:");

        // Mark characters in s1
        System.out.print("s1: ");
        int lcsIndex = 0;
        for (i = 0; i < m; i++) {
            char c = s1.charAt(i);
            if (lcsIndex < lcsString.length() && c == lcsString.charAt(lcsIndex)) {
                System.out.print("[" + c + "]"); // Keep
                lcsIndex++;
            } else {
                System.out.print("(" + c + ")"); // Delete
            }
        }

        // Reset LCS index for s2
        lcsIndex = 0;
        System.out.print("\ns2: ");
        for (j = 0; j < n; j++) {
            char c = s2.charAt(j);
            if (lcsIndex < lcsString.length() && c == lcsString.charAt(lcsIndex)) {
                System.out.print("[" + c + "]"); // Keep
                lcsIndex++;
            } else {
                System.out.print("(" + c + ")"); // Delete
            }
        }

        System.out.println("\n\nWhere: [x] = keep this character, (x) = delete this character");
    }
}
