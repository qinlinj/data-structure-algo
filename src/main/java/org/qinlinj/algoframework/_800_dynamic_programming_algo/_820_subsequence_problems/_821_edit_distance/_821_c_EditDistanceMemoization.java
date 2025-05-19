package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._821_edit_distance;

/**
 * EDIT DISTANCE - MEMOIZATION OPTIMIZATION
 * <p>
 * Key Points:
 * <p>
 * 1. The recursive solution to the Edit Distance problem suffers from overlapping subproblems,
 * leading to exponential time complexity.
 * <p>
 * 2. We can identify overlapping subproblems by looking at the recursive framework:
 * - dp(i, j) calls dp(i-1, j-1), dp(i, j-1), and dp(i-1, j)
 * - This creates multiple paths to reach the same state, causing redundant calculations
 * <p>
 * 3. Memoization technique:
 * - Cache results of previously solved subproblems in a memo table
 * - Before recursive computation, check if the result is already in the memo table
 * - After computation, store the result in the memo table for future reference
 * <p>
 * 4. The memoization approach:
 * - Maintains the intuitive top-down structure of the recursive solution
 * - Eliminates redundant calculations by storing and reusing results
 * - Improves time complexity from O(3^(m+n)) to O(m×n)
 * - Requires O(m×n) space for the memo table
 * <p>
 * 5. This is also called "top-down dynamic programming" since it works from the original
 * problem (top) down to the base cases.
 */
public class _821_c_EditDistanceMemoization {

    // Import necessary for Arrays.fill
    static java.util.Arrays Arrays;

    /**
     * Optimized solution using memoization to avoid redundant calculations
     */
    public static int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        // Initialize memoization table with -1 (indicating not calculated yet)
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Start recursive calculation from the end of both strings
        return dp(s1, m - 1, s2, n - 1, memo);
    }

    /**
     * Recursive function with memoization
     * Returns minimum edit distance between s1[0...i] and s2[0...j]
     */
    private static int dp(String s1, int i, String s2, int j, int[][] memo) {
        // Base cases
        if (i == -1) return j + 1;  // Need to insert j+1 characters
        if (j == -1) return i + 1;  // Need to delete i+1 characters

        // Check if already calculated
        if (i >= 0 && j >= 0 && memo[i][j] != -1) {
            return memo[i][j];
        }

        // Calculate the result
        int result;
        if (s1.charAt(i) == s2.charAt(j)) {
            // Characters match, no operation needed
            result = dp(s1, i - 1, s2, j - 1, memo);
        } else {
            // Try all three operations and choose the minimum
            result = 1 + min(
                    dp(s1, i, s2, j - 1, memo),      // Insert
                    dp(s1, i - 1, s2, j, memo),      // Delete
                    dp(s1, i - 1, s2, j - 1, memo)   // Replace
            );
        }

        // Store result in memo table (only if both indices are valid)
        if (i >= 0 && j >= 0) {
            memo[i][j] = result;
        }

        return result;
    }

    /**
     * Helper function to find minimum of three integers
     */
    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * Visualize the memoization process with a simple example
     */
    public static void visualizeMemoization() {
        String s1 = "horse";
        String s2 = "ros";
        int m = s1.length(), n = s2.length();

        // Create a small visualization of the memo table
        System.out.println("Memoization Table Visualization (simplified):");
        System.out.println("For s1 = \"" + s1 + "\" and s2 = \"" + s2 + "\"");

        String[][] memo = new String[m][n];
        for (String[] row : memo) {
            Arrays.fill(row, "?");
        }

        // Simulate some filled values
        memo[0][0] = "2"; // Transform "h" to "r"
        memo[1][0] = "2"; // Transform "ho" to "r"
        memo[0][1] = "2"; // Transform "h" to "ro"

        // Print table
        System.out.println("\nMemo table (? indicates not yet calculated):");
        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.print(s2.charAt(j) + " ");
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            System.out.print(s1.charAt(i) + "   ");
            for (int j = 0; j < n; j++) {
                System.out.print(memo[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nAs the algorithm progresses, more cells will be filled.");
        System.out.println("When we need a value that's already calculated, we can");
        System.out.println("retrieve it from the memo table instead of recalculating.");
    }

    /**
     * Demonstrates the memoized solution with performance comparison
     */
    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";

        // For comparison, implement a simplified recursive version without memoization
        // Note: This is just for demonstration; in practice, it would be very slow for larger inputs
        long startTimeRecursive = System.nanoTime();
        int recursiveResult = _821_b_EditDistanceApproach.minDistance(s1, s2);
        long endTimeRecursive = System.nanoTime();

        // Measure time for memoized version
        long startTimeMemo = System.nanoTime();
        int memoResult = minDistance(s1, s2);
        long endTimeMemo = System.nanoTime();

        // Calculate elapsed time
        double recursiveTimeMs = (endTimeRecursive - startTimeRecursive) / 1_000_000.0;
        double memoTimeMs = (endTimeMemo - startTimeMemo) / 1_000_000.0;

        System.out.println("Edit distance between \"" + s1 + "\" and \"" + s2 + "\": " + memoResult);
        System.out.println("\nPerformance Comparison:");
        System.out.println("Recursive approach time: " + recursiveTimeMs + " ms");
        System.out.println("Memoized approach time: " + memoTimeMs + " ms");
        System.out.println("Speedup factor: " + (recursiveTimeMs / memoTimeMs) + "x");

        System.out.println("\n----------\n");
        visualizeMemoization();
    }
}

