package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * Class: Identifying Overlapping Subproblems
 * <p>
 * Key Points:
 * 1. Overlapping subproblems occur when the same subproblem is solved multiple times.
 * 2. Methods to identify overlapping subproblems:
 * a. Drawing the recursion tree to visually see repeated calculations
 * b. Analyzing the recursive framework to find multiple paths to the same state
 * 3. If multiple paths lead to the same state, overlapping subproblems exist.
 * 4. When overlapping subproblems are identified, memoization or tabulation can be used for optimization.
 */
public class _817_b_IdentifyingOverlappingSubproblems {

    // Example 1: Fibonacci sequence (classic example of overlapping subproblems)
    public int fibonacci(int n) {
        if (n <= 1) return n;
        // Two recursive calls that will create overlapping subproblems
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Example 2: Minimum Path Sum problem (has overlapping subproblems)
    public int minPathSum(int[][] grid, int i, int j) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // Analysis of recursive framework:
        // Multiple paths can reach state (i-1, j-1):
        // Path 1: (i,j) -> (i-1,j) -> (i-1,j-1)
        // Path 2: (i,j) -> (i,j-1) -> (i-1,j-1)
        // This indicates overlapping subproblems
        return Math.min(
                minPathSum(grid, i - 1, j),  // Path 1
                minPathSum(grid, i, j - 1)   // Path 2
        ) + grid[i][j];
    }

    // Example 3: Optimized Minimum Path Sum with memoization
    public int minPathSumMemoized(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][] memo = new Integer[m][n];
        return dpMinPath(grid, m - 1, n - 1, memo);
    }

    private int dpMinPath(int[][] grid, int i, int j, Integer[][] memo) {
        if (i == 0 && j == 0) return grid[0][0];
        if (i < 0 || j < 0) return Integer.MAX_VALUE;

        // If we've already calculated this state, return it
        if (memo[i][j] != null) return memo[i][j];

        // Calculate and store the result for this state
        memo[i][j] = Math.min(
                dpMinPath(grid, i - 1, j, memo),
                dpMinPath(grid, i, j - 1, memo)
        ) + grid[i][j];

        return memo[i][j];
    }

    // Example 4: Regular expression matching (complex example with overlapping subproblems)
    public boolean isMatch(String s, String p) {
        return dp(s, 0, p, 0);
    }

    private boolean dp(String s, int i, String p, int j) {
        int m = s.length(), n = p.length();

        // Base cases
        if (j == n) return i == m;
        if (i == m) {
            if ((n - j) % 2 == 1) return false;
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') return false;
            }
            return true;
        }

        // Recursive framework:
        // Multiple paths can reach state (i+2, j+2):
        // Path 1: (i,j) -> (i,j+2) -> (i+1,j+2) -> (i+2,j+2)
        // Path 2: (i,j) -> (i+1,j+1) -> (i+2,j+2)
        // This indicates overlapping subproblems

        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res = dp(s, i, p, j + 2) || dp(s, i + 1, p, j);
            } else {
                res = dp(s, i + 1, p, j + 1);
            }
        } else {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res = dp(s, i, p, j + 2);
            } else {
                res = false;
            }
        }

        return res;
    }
}
