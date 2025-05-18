package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._816_space_compression;

import java.util.*;

/**
 * Conclusion and Additional Examples of Space Optimization
 * <p>
 * Summary:
 * - Space optimization is a powerful technique to reduce memory usage in dynamic programming solutions
 * <p>
 * - Key benefits:
 * 1. Reduces space complexity from O(n²) to O(n) for many 2D DP problems
 * 2. Makes solutions viable for problems with very large inputs
 * 3. Can improve cache locality for better performance
 * <p>
 * - Primary applications:
 * 1. Problems where state transitions only depend on adjacent states
 * 2. Scenarios with tight memory constraints
 * 3. Classic DP problems like string matching, path finding, and optimization
 * <p>
 * - Best practices:
 * 1. Start with a clear, correct standard DP solution
 * 2. Analyze the dependencies in the state transition equation
 * 3. Determine if space optimization is necessary and applicable
 * 4. Implement the optimization with careful attention to order and temporary variables
 * 5. Add detailed comments to explain the optimization
 * <p>
 * - While space optimization makes code more complex, it's a valuable tool in a programmer's arsenal
 * for solving large-scale dynamic programming problems efficiently
 */
public class _816_f_ConclusionAndExamples {

    /**
     * Example 1: Edit Distance with space optimization
     * <p>
     * Problem: Given two strings word1 and word2, return the minimum number of operations
     * required to convert word1 to word2 (insert, delete, replace)
     * <p>
     * Standard 2D DP solution uses dp[i][j] to represent the edit distance between
     * the first i characters of word1 and the first j characters of word2
     * <p>
     * Space-optimized version reduces space from O(m*n) to O(n)
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // If one of the strings is empty, the edit distance is the length of the other string
        if (m == 0) return n;
        if (n == 0) return m;

        // Create 1D array (we only need to keep track of one row at a time)
        int[] dp = new int[n + 1];

        // Initialize base case: converting empty string to word2[0...j]
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        // Fill the DP array
        for (int i = 1; i <= m; i++) {
            // Save the previous value of dp[0] (which represents dp[i-1][0])
            int prev = dp[0];

            // Update dp[0] for the current row (cost of deleting i characters from word1)
            dp[0] = i;

            for (int j = 1; j <= n; j++) {
                // Save the current value of dp[j] before updating it
                int temp = dp[j];

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // If characters match, no additional cost
                    dp[j] = prev;
                } else {
                    // If characters don't match, take the minimum of:
                    // 1. Replace: dp[i-1][j-1] + 1 (stored in prev)
                    // 2. Delete: dp[i-1][j] + 1 (stored in dp[j])
                    // 3. Insert: dp[i][j-1] + 1 (stored in dp[j-1])
                    dp[j] = Math.min(prev, Math.min(dp[j], dp[j - 1])) + 1;
                }

                // Update prev for the next iteration
                prev = temp;
            }
        }

        return dp[n];
    }

    /**
     * Example 2: Longest Common Subsequence with space optimization
     * <p>
     * Problem: Given two strings text1 and text2, return the length of their longest common subsequence
     * <p>
     * Standard 2D DP solution uses dp[i][j] to represent the LCS length between
     * the first i characters of text1 and the first j characters of text2
     * <p>
     * Space-optimized version reduces space from O(m*n) to O(n)
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        // Ensure text2 is the shorter string to minimize space usage
        if (text1.length() < text2.length()) {
            return longestCommonSubsequence(text2, text1);
        }

        int m = text1.length();
        int n = text2.length();

        // Create 1D array (we only need to keep track of one row at a time)
        int[] dp = new int[n + 1];

        // Base case: LCS of any string with empty string is 0
        // dp array is initialized to 0 by default, so no explicit initialization needed

        // Fill the DP array
        for (int i = 1; i <= m; i++) {
            // Save the value of dp[0] from the previous row
            // Since dp[0] is always 0 (LCS with empty string), we don't actually need this
            // int prev = dp[0];
            int prev = 0;

            for (int j = 1; j <= n; j++) {
                // Save the current value of dp[j] before updating it
                int temp = dp[j];

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // If characters match, extend the LCS
                    dp[j] = prev + 1;
                } else {
                    // If characters don't match, take the maximum of:
                    // 1. LCS without current char from text1: dp[i-1][j] (stored in dp[j])
                    // 2. LCS without current char from text2: dp[i][j-1] (stored in dp[j-1])
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                // Update prev for the next iteration
                prev = temp;
            }
        }

        return dp[n];
    }

    /**
     * Example 3: 0/1 Knapsack Problem with space optimization
     * <p>
     * Problem: Given weights and values of n items, put these items in a knapsack
     * of capacity W to get the maximum total value in the knapsack
     * <p>
     * Standard 2D DP solution uses dp[i][w] to represent the maximum value that can
     * be obtained using the first i items with a knapsack of capacity w
     * <p>
     * Space-optimized version reduces space from O(n*W) to O(W)
     */
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // Create 1D array
        int[] dp = new int[capacity + 1];

        // Base case: with 0 capacity, max value is 0
        // dp array is initialized to 0 by default, so no explicit initialization needed

        // Fill the DP array
        for (int i = 0; i < n; i++) {
            // We need to iterate from right to left to avoid using an item multiple times
            for (int w = capacity; w >= weights[i]; w--) {
                // dp[w] represents dp[i-1][w] (max value without current item)
                // dp[w - weights[i]] + values[i] represents max value with current item
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }

        return dp[capacity];
    }

    /**
     * Demonstrates when space optimization is particularly valuable
     */
    public static void whenToUseSpaceOptimization() {
        System.out.println("When to Use Space Optimization");
        System.out.println("=============================");

        System.out.println("1. When memory is a constraint:");
        System.out.println("   - Large input sizes that would cause memory issues");
        System.out.println("   - Resource-constrained environments (embedded systems, etc.)");

        System.out.println("\n2. When the problem has a structure that allows for it:");
        System.out.println("   - State transitions only depend on adjacent states");
        System.out.println("   - Only need to access the most recent row/column");

        System.out.println("\n3. Common problem types that can benefit:");
        System.out.println("   - String matching/editing (Edit Distance, LCS)");
        System.out.println("   - Path problems (Minimum Path Sum)");
        System.out.println("   - Optimization problems (Knapsack)");
        System.out.println("   - Sequence problems (Longest Increasing Subsequence)");
    }

    /**
     * Summarizes key takeaways about space optimization
     */
    public static void summarizeKeyTakeaways() {
        System.out.println("\nKey Takeaways About Space Optimization");
        System.out.println("====================================");

        System.out.println("1. Progressive optimization approach:");
        System.out.println("   - Start with the recursive solution (clear but inefficient)");
        System.out.println("   - Add memoization to eliminate redundant calculations");
        System.out.println("   - Convert to iterative solution with tabulation");
        System.out.println("   - Apply space optimization as the final step");

        System.out.println("\n2. Understanding dependencies is crucial:");
        System.out.println("   - Analyze exactly which previous states are needed for each calculation");
        System.out.println("   - Temporary variables (like 'prev') are often necessary to preserve values");
        System.out.println("   - The traversal order must respect dependencies");

        System.out.println("\n3. Readability vs. Efficiency tradeoff:");
        System.out.println("   - Space-optimized code is less readable and harder to maintain");
        System.out.println("   - Add clear comments explaining the optimization");
        System.out.println("   - Balance the need for optimization with code clarity");

        System.out.println("\n4. Not all DP problems can be space-optimized effectively:");
        System.out.println("   - Some problems require access to states that are not adjacent");
        System.out.println("   - Some optimizations may complicate the code too much for minimal gain");

        System.out.println("\n5. The final word on space optimization:");
        System.out.println("   - It's an advanced technique that completes your DP toolkit");
        System.out.println("   - Master the fundamentals first before applying these optimizations");
        System.out.println("   - Use when necessary, but don't prematurely optimize");
    }

    /**
     * Main method to test the space-optimized DP solutions
     */
    public static void main(String[] args) {
        System.out.println("Space Optimization in Dynamic Programming: Conclusion and Examples");
        System.out.println("==============================================================");

        // Test Edit Distance
        String word1 = "intention";
        String word2 = "execution";
        System.out.println("\nEdit Distance Example:");
        System.out.println("Converting \"" + word1 + "\" to \"" + word2 + "\"");
        System.out.println("Minimum operations required: " + minDistance(word1, word2));

        // Test Longest Common Subsequence
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("\nLongest Common Subsequence Example:");
        System.out.println("LCS between \"" + text1 + "\" and \"" + text2 + "\"");
        System.out.println("Length of LCS: " + longestCommonSubsequence(text1, text2));

        // Test Knapsack
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 8;
        System.out.println("\n0/1 Knapsack Example:");
        System.out.println("Weights: " + Arrays.toString(weights));
        System.out.println("Values: " + Arrays.toString(values));
        System.out.println("Capacity: " + capacity);
        System.out.println("Maximum value: " + knapsack(weights, values, capacity));

        // Print recommendations and takeaways
        whenToUseSpaceOptimization();
        summarizeKeyTakeaways();

        System.out.println("\nConclusion:");
        System.out.println("Space optimization is a powerful technique for reducing memory usage");
        System.out.println("in dynamic programming solutions. While it makes code more complex,");
        System.out.println("it's an essential tool for tackling large-scale problems efficiently.");
        System.out.println("As with any optimization, apply it judiciously and prioritize correctness first.");
    }
}
