package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._821_edit_distance;

/**
 * EDIT DISTANCE - DYNAMIC PROGRAMMING TABULATION
 * <p>
 * Key Points:
 * <p>
 * 1. Bottom-up Tabulation approach:
 * - Instead of starting from the original problem and recursing down (top-down),
 * we start from the base cases and build up to the original problem
 * - We use a DP table to store intermediate results
 * - Tabulation eliminates recursion stack overhead
 * <p>
 * 2. DP Table Definition:
 * - dp[i][j] represents the minimum edit distance between s1[0...i-1] and s2[0...j-1]
 * - Indices are shifted by 1 compared to the recursive definition to handle base cases easily
 * - dp[0][0] = 0 (empty string to empty string)
 * - dp[i][0] = i (deleting i characters from s1)
 * - dp[0][j] = j (inserting j characters from s2)
 * <p>
 * 3. State Transition:
 * - If s1[i-1] == s2[j-1]: dp[i][j] = dp[i-1][j-1] (no operation needed)
 * - Else: dp[i][j] = 1 + min(
 * dp[i-1][j],     // Delete from s1
 * dp[i][j-1],     // Insert into s1
 * dp[i-1][j-1]    // Replace character in s1
 * )
 * <p>
 * 4. Benefits over memoization:
 * - More space-efficient (no recursion stack)
 * - Usually faster due to better memory locality
 * - Clearer iteration pattern
 * <p>
 * 5. Time Complexity: O(m×n)
 * Space Complexity: O(m×n)
 */
public class _821_d_EditDistanceTabulation {

    /**
     * Dynamic Programming solution using tabulation (bottom-up approach)
     */
    public static int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create DP table - each cell represents edit distance between prefixes
        int[][] dp = new int[m + 1][n + 1];

        // Base cases: empty string to another string requires
        // insertions/deletions equal to the other string's length
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;  // Delete i characters from s1
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;  // Insert j characters from s2
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, no operation needed
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Try all three operations and take the minimum
                    dp[i][j] = 1 + Math.min(
                            Math.min(
                                    dp[i - 1][j],    // Delete
                                    dp[i][j - 1]),   // Insert
                            dp[i - 1][j - 1]     // Replace
                    );
                }
            }
        }

        // The bottom-right cell contains the answer for the entire strings
        return dp[m][n];
    }

    /**
     * Prints the DP table for a small example to visualize the algorithm
     */
    public static void printDPTable(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the DP table
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

        // Print table header
        System.out.println("DP Table for Edit Distance between \"" + s1 + "\" and \"" + s2 + "\":");
        System.out.print("     ");
        System.out.print("\" \"  ");
        for (int j = 0; j < n; j++) {
            System.out.print("\"" + s2.charAt(j) + "\"  ");
        }
        System.out.println();

        // Print table rows
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("\" \"  ");
            } else {
                System.out.print("\"" + s1.charAt(i - 1) + "\"  ");
            }
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + "    ");
            }
            System.out.println();
        }
    }

    /**
     * Shows step-by-step explanation of how to read the DP table
     */
    public static void explainDPTable(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialize and fill the DP table (same as above)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

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

        System.out.println("\nTable Explanation:");
        System.out.println("1. dp[0][0] = 0: Distance between empty strings is 0.");
        System.out.println("2. dp[0][j] = j: Distance from empty string to \"" + s2.substring(0, Math.min(n, 3)) + "...\" requires j insertions.");
        System.out.println("3. dp[i][0] = i: Distance from \"" + s1.substring(0, Math.min(m, 3)) + "...\" to empty string requires i deletions.");

        // Explain a few specific cells
        if (m >= 1 && n >= 1) {
            char c1 = s1.charAt(0);
            char c2 = s2.charAt(0);
            if (c1 == c2) {
                System.out.println("4. dp[1][1] = " + dp[1][1] + ": First characters \"" + c1 + "\" and \"" + c2 + "\" match, so cost is the same as dp[0][0].");
            } else {
                System.out.println("4. dp[1][1] = " + dp[1][1] + ": First characters \"" + c1 + "\" and \"" + c2 + "\" don't match, so add 1 to the minimum of dp[0][1], dp[1][0], and dp[0][0].");
            }
        }

        System.out.println("5. Final answer dp[" + m + "][" + n + "] = " + dp[m][n] + " is the edit distance between the entire strings.");
    }

    /**
     * Demonstrates the tabulation approach with performance comparison
     */
    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";

        // Print DP table for visualization
        printDPTable(s1, s2);
        explainDPTable(s1, s2);

        // Measure performance
        long startTime = System.nanoTime();
        int result = minDistance(s1, s2);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("\nEdit distance between \"" + s1 + "\" and \"" + s2 + "\": " + result);
        System.out.println("Computation time: " + timeMs + " ms");

        // Try with a larger example
        s1 = "intention";
        s2 = "execution";
        startTime = System.nanoTime();
        result = minDistance(s1, s2);
        endTime = System.nanoTime();
        timeMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("\nEdit distance between \"" + s1 + "\" and \"" + s2 + "\": " + result);
        System.out.println("Computation time: " + timeMs + " ms");

        System.out.println("\nComparison with recursive and memoized approaches:");
        System.out.println("- Recursive: Exponential time complexity O(3^(m+n))");
        System.out.println("- Memoized: O(m×n) time, but with recursion overhead");
        System.out.println("- Tabulation: O(m×n) time, more efficient memory access patterns");
    }
}

