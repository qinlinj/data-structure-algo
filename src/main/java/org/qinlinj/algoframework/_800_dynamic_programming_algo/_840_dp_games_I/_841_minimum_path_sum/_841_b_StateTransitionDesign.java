package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

/**
 * STATE TRANSITION AND DP FUNCTION DESIGN
 * <p>
 * KNOWLEDGE POINTS:
 * 1. State Definition: dp(i,j) = minimum path sum from (0,0) to (i,j)
 * 2. State Transition: dp(i,j) = min(dp(i-1,j), dp(i,j-1)) + grid[i][j]
 * 3. Base Case: dp(0,0) = grid[0][0]
 * 4. Boundary Conditions: Handle out-of-bounds indices
 * 5. Problem Decomposition: Break down complex problem into simpler subproblems
 * <p>
 * CORE INSIGHT:
 * To reach position (i,j) with minimum cost, we must come from either:
 * - Position (i-1,j) with minimum cost, then move down
 * - Position (i,j-1) with minimum cost, then move right
 * We choose whichever gives us the smaller total cost.
 * <p>
 * MATHEMATICAL FORMULATION:
 * dp(i,j) = grid[i][j] + min(dp(i-1,j), dp(i,j-1))
 * where dp(i,j) represents minimum path sum to reach cell (i,j)
 */
public class _841_b_StateTransitionDesign {

    // Test method
    public static void main(String[] args) {
        _841_b_StateTransitionDesign solution = new _841_b_StateTransitionDesign();

        // Small test case to demonstrate transitions
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        System.out.println("Grid:");
        for (int[] row : grid) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        solution.visualizeStateTransitions();
        solution.analyzeOverlappingSubproblems();

        // For small grid, show the recursive trace
        int[][] smallGrid = {{1, 2}, {3, 4}};
        System.out.println("\nSmall example with trace:");
        int result = solution.minPathSumWithLogging(smallGrid);
        System.out.println("Final result: " + result);
    }

    /**
     * Basic recursive solution (without memoization)
     * This demonstrates the core logic and state transition
     * <p>
     * Time Complexity: O(2^(m+n)) - exponential due to overlapping subproblems
     * Space Complexity: O(m+n) - recursion stack depth
     */
    public int minPathSumRecursive(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Calculate minimum path sum from top-left to bottom-right
        return dp(grid, m - 1, n - 1);
    }

    /**
     * Core DP function definition:
     * dp(grid, i, j) = minimum path sum from (0,0) to (i,j)
     */
    private int dp(int[][] grid, int i, int j) {
        // Base case: reached the starting point
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        // Boundary condition: if indices are out of bounds,
        // return a very large value to ensure it won't be chosen in min()
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // State transition: choose the minimum path from top or left
        // and add current cell value
        return Math.min(
                dp(grid, i - 1, j),  // came from above
                dp(grid, i, j - 1)   // came from left
        ) + grid[i][j];
    }

    /**
     * Demonstrates the state transition with detailed logging
     */
    public int minPathSumWithLogging(int[][] grid) {
        System.out.println("=== TRACING STATE TRANSITIONS ===");
        return dpWithLogging(grid, grid.length - 1, grid[0].length - 1, 0);
    }

    private int dpWithLogging(int[][] grid, int i, int j, int depth) {
        // Indentation for visualization
        String indent = "  ".repeat(depth);
        System.out.println(indent + "dp(" + i + "," + j + ")");

        // Base case
        if (i == 0 && j == 0) {
            System.out.println(indent + "  Base case: grid[0][0] = " + grid[0][0]);
            return grid[0][0];
        }

        // Boundary condition
        if (i < 0 || j < 0) {
            System.out.println(indent + "  Out of bounds: returning MAX_VALUE");
            return Integer.MAX_VALUE;
        }

        // Recursive calls
        System.out.println(indent + "  Exploring paths from (" + i + "," + j + "):");
        int fromTop = dpWithLogging(grid, i - 1, j, depth + 1);
        int fromLeft = dpWithLogging(grid, i, j - 1, depth + 1);

        int result = Math.min(fromTop, fromLeft) + grid[i][j];
        System.out.println(indent + "  Result for (" + i + "," + j + "): " +
                "min(" + fromTop + "," + fromLeft + ") + " + grid[i][j] + " = " + result);

        return result;
    }

    /**
     * Analyzes the overlapping subproblems
     */
    public void analyzeOverlappingSubproblems() {
        System.out.println("\n=== OVERLAPPING SUBPROBLEMS ANALYSIS ===");
        System.out.println("Consider reaching dp(i-1, j-1) from dp(i,j):");
        System.out.println("Path 1: dp(i,j) → dp(i-1,j) → dp(i-1,j-1)");
        System.out.println("Path 2: dp(i,j) → dp(i,j-1) → dp(i-1,j-1)");
        System.out.println("dp(i-1,j-1) will be calculated multiple times!");
        System.out.println("This indicates we need memoization for optimization.");
    }

    /**
     * Visual representation of state transitions
     */
    public void visualizeStateTransitions() {
        System.out.println("\n=== STATE TRANSITION VISUALIZATION ===");
        System.out.println("For any cell (i,j), we can reach it from:");
        System.out.println("     ↓");
        System.out.println("(i-1,j)");
        System.out.println("     ↓");
        System.out.println("(i,j-1) → (i,j)");
        System.out.println("         ↑");
        System.out.println("    came from left");
        System.out.println("\nTransition: dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]");
    }
}