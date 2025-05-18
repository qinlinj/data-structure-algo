package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._816_space_compression;

/**
 * Space Optimization in Dynamic Programming - Introduction
 * <p>
 * Summary:
 * - Dynamic programming solutions typically come in two forms:
 * 1. Recursive solutions with memoization
 * 2. Iterative solutions with tabulation
 * <p>
 * - Space optimization (also known as rolling array technique) is a technique to reduce space complexity
 * in dynamic programming solutions by only maintaining the necessary states instead of the entire DP array
 * <p>
 * - This optimization is most applicable to two-dimensional DP problems where the state transitions
 * only depend on adjacent states
 * <p>
 * - The main benefit is reducing space complexity from O(N²) to O(N)
 * <p>
 * - While this optimization is not always necessary to pass standard coding interviews,
 * understanding it provides deeper insight into DP optimization techniques
 * <p>
 * - The essential idea: "project" a 2D array onto a 1D array, keeping only the states needed for calculation
 */
public class _816_a_SpaceOptimizationIntro {

    public static void main(String[] args) {
        System.out.println("Space Optimization in Dynamic Programming");
        System.out.println("=========================================");

        // Demonstrate when space optimization is applicable
        System.out.println("Space optimization is applicable when:");
        System.out.println("1. We have a 2D dynamic programming problem");
        System.out.println("2. The state transitions only depend on adjacent states");
        System.out.println("3. We want to reduce space complexity from O(N²) to O(N)");

        System.out.println("\nExample problems that can use space optimization:");
        System.out.println("- Longest Palindromic Subsequence");
        System.out.println("- Longest Common Subsequence");
        System.out.println("- Edit Distance");
        System.out.println("- Minimum Path Sum");

        // Demonstrate the progression of DP solutions
        demonstrateDPProgression();
    }

    /**
     * Demonstrates the progression of DP solutions from recursive to space-optimized
     */
    private static void demonstrateDPProgression() {
        System.out.println("\nProgression of Dynamic Programming Solutions:");
        System.out.println("1. Recursive solution (intuitive but inefficient)");
        System.out.println("   - Easy to understand");
        System.out.println("   - Directly translates the problem definition");
        System.out.println("   - Often has exponential time complexity due to redundant calculations");

        System.out.println("\n2. Recursive solution with memoization");
        System.out.println("   - Caches results of subproblems");
        System.out.println("   - Avoids redundant calculations");
        System.out.println("   - Improves time complexity");
        System.out.println("   - Still has O(N) space complexity for the recursion stack");

        System.out.println("\n3. Iterative solution with tabulation");
        System.out.println("   - Eliminates recursion stack");
        System.out.println("   - Often has O(N²) space complexity for 2D problems");
        System.out.println("   - Bottom-up approach, building from base cases");

        System.out.println("\n4. Space-optimized iterative solution");
        System.out.println("   - Reduces space complexity to O(N)");
        System.out.println("   - Maintains only necessary states");
        System.out.println("   - More complex implementation");
        System.out.println("   - Less readable code");
    }

    /**
     * Generic example of a 2D dynamic programming solution
     */
    public static int twoDimensionalDP(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Create 2D DP array
        int[][] dp = new int[m][n];

        // Initialize base cases
        dp[0][0] = grid[0][0];

        // Fill first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Fill first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Fill rest of the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * Generic example of a space-optimized 1D dynamic programming solution
     */
    public static int spacedOptimizedDP(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Create 1D DP array (just one row)
        int[] dp = new int[n];

        // Initialize first element
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
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }

        return dp[n - 1];
    }
}
