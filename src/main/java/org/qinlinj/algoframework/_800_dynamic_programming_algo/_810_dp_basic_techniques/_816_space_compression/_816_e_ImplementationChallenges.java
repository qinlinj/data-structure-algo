package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._816_space_compression;

import java.util.*;

/**
 * Challenges and Implementation of Space Optimization
 * <p>
 * Summary:
 * - Space optimization makes code less readable but can significantly reduce memory usage
 * <p>
 * - Main implementation challenges:
 * 1. Preserving values that would be overwritten but are needed for future calculations
 * 2. Correctly tracking which 1D array index corresponds to which 2D array position
 * 3. Maintaining proper iteration order to ensure dependencies are respected
 * 4. Handling the base cases correctly when projecting from 2D to 1D
 * <p>
 * - Key implementation techniques:
 * 1. Use temporary variables to store values before they're overwritten
 * 2. Carefully analyze the state transition equation to understand dependencies
 * 3. Choose the right dimension to eliminate (usually eliminate the i dimension)
 * 4. Ensure the traversal order aligns with the dependencies
 * <p>
 * - The 'pre' variable in the Longest Palindromic Subsequence example is crucial:
 * It preserves dp[i+1][j-1] which would otherwise be overwritten by dp[i][j-1]
 * <p>
 * - This optimization is an advanced technique that should be applied after
 * first implementing and understanding the standard DP solution
 */
public class _816_e_ImplementationChallenges {

    /**
     * Example: Complete implementation of Longest Palindromic Subsequence
     * with detailed comments explaining the space optimization
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();

        // Initialize 1D array with base cases (all single chars are palindromes of length 1)
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        // Process the DP array in correct order (bottom-up)
        for (int i = n - 2; i >= 0; i--) {
            // 'pre' is used to store dp[i+1][j-1] before it gets overwritten
            // Initially, it's 0 because dp[i+1][i] is outside the valid range
            int pre = 0;

            for (int j = i + 1; j < n; j++) {
                // Store the current value of dp[j] (which represents dp[i+1][j])
                // before it gets overwritten with the new value for dp[i][j]
                int temp = dp[j];

                // State transition equation
                if (s.charAt(i) == s.charAt(j)) {
                    // If chars match, use the value from dp[i+1][j-1] which is stored in 'pre'
                    dp[j] = pre + 2;
                } else {
                    // If chars don't match, take the max between dp[i+1][j] (current dp[j])
                    // and dp[i][j-1] (current dp[j-1])
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                // Update 'pre' for the next iteration.
                // Now 'pre' holds the original value of dp[i+1][j],
                // which will be dp[i+1][j-1] for the next j
                pre = temp;
            }
        }

        // The answer is stored in dp[n-1], which corresponds to dp[0][n-1] in the 2D version
        return dp[n - 1];
    }

    /**
     * Another example: Minimum Path Sum with space optimization
     * Original problem: Given a grid, find minimum path sum from top-left to bottom-right
     */
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;    // Number of rows
        int n = grid[0].length; // Number of columns

        // 2D DP solution would use dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]

        // Space-optimized solution using 1D array
        int[] dp = new int[n];

        // Initialize the first element
        dp[0] = grid[0][0];

        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        // Process remaining rows
        for (int i = 1; i < m; i++) {
            // Update first element of current row
            dp[0] += grid[i][0];

            // Update rest of the row
            for (int j = 1; j < n; j++) {
                // dp[j] represents dp[i-1][j] (from previous row)
                // dp[j-1] represents dp[i][j-1] (from current row)
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }

        // The answer is stored in dp[n-1]
        return dp[n - 1];
    }

    /**
     * Analyze why certain traversal orders are necessary for space optimization
     */
    public static void analyzeTraversalOrder() {
        System.out.println("Importance of Traversal Order in Space Optimization");
        System.out.println("===================================================");

        System.out.println("1. For Longest Palindromic Subsequence:");
        System.out.println("   - We traverse i from n-2 down to 0 (bottom-up)");
        System.out.println("   - For each i, we traverse j from i+1 to n-1 (left to right)");
        System.out.println("   - This order ensures all dependencies are computed before needed");
        System.out.println("   - The 'pre' variable captures dp[i+1][j-1] before it's overwritten");

        System.out.println("\n2. For Minimum Path Sum:");
        System.out.println("   - We traverse i from 1 to m-1 (top to bottom)");
        System.out.println("   - For each i, we traverse j from 1 to n-1 (left to right)");
        System.out.println("   - This allows dp[j] to represent dp[i-1][j] and dp[j-1] to represent dp[i][j-1]");

        System.out.println("\nKey insight: The traversal order must ensure that:");
        System.out.println("1. All dependencies are computed before they're needed");
        System.out.println("2. Values in the 1D array correctly represent their 2D counterparts");
        System.out.println("3. Values aren't overwritten before they're used (or are saved if needed)");
    }

    /**
     * Analyze the tradeoff between readability and efficiency
     */
    public static void analyzeReadabilityTradeoff() {
        System.out.println("\nTradeoff Between Readability and Efficiency");
        System.out.println("==========================================");

        System.out.println("Standard 2D DP Solution Advantages:");
        System.out.println("- Directly translates the recurrence relation");
        System.out.println("- Easier to understand and maintain");
        System.out.println("- Less prone to subtle bugs");
        System.out.println("- Good for initial implementation and problem-solving");

        System.out.println("\nSpace-Optimized 1D Solution Advantages:");
        System.out.println("- Reduces space complexity from O(n²) to O(n)");
        System.out.println("- Critical for problems with large inputs or memory constraints");
        System.out.println("- Can improve cache locality in some cases");

        System.out.println("\nRecommended Approach:");
        System.out.println("1. First implement the standard 2D solution");
        System.out.println("2. Understand the dependencies fully");
        System.out.println("3. Apply space optimization if necessary");
        System.out.println("4. Add detailed comments to explain the optimization");
    }

    /**
     * Demonstrates common pitfalls and solutions in space optimization
     */
    public static void commonPitfalls() {
        System.out.println("\nCommon Pitfalls and Solutions in Space Optimization");
        System.out.println("=================================================");

        System.out.println("Pitfall 1: Forgetting to preserve overwritten values");
        System.out.println("Solution: Use temporary variables (like 'pre') to store values before they're overwritten");

        System.out.println("\nPitfall 2: Incorrect traversal order");
        System.out.println("Solution: Carefully analyze dependencies and ensure the traversal order respects them");

        System.out.println("\nPitfall 3: Confusion about which 1D index corresponds to which 2D position");
        System.out.println("Solution: Add clear comments and potentially visualize the mapping");

        System.out.println("\nPitfall 4: Incorrect base case initialization");
        System.out.println("Solution: Carefully project the base cases from 2D to 1D and initialize accordingly");

        System.out.println("\nPitfall 5: Premature optimization");
        System.out.println("Solution: First implement the standard solution, then optimize if necessary");
    }

    public static void main(String[] args) {
        System.out.println("Challenges and Implementation of Space Optimization");
        System.out.println("==================================================");

        // Test Longest Palindromic Subsequence
        String[] testCases = {"bbbab", "cbbd", "racecar", "aaa"};

        System.out.println("Testing Longest Palindromic Subsequence with Space Optimization:");
        for (String s : testCases) {
            int length = longestPalindromeSubseq(s);
            System.out.println("LPS of \"" + s + "\" has length " + length);
        }

        // Test Minimum Path Sum
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        System.out.println("\nTesting Minimum Path Sum with Space Optimization:");
        System.out.println("Minimum path sum: " + minPathSum(grid));

        // Analyze traversal order
        analyzeTraversalOrder();

        // Analyze readability tradeoff
        analyzeReadabilityTradeoff();

        // Discuss common pitfalls
        commonPitfalls();
    }
}
