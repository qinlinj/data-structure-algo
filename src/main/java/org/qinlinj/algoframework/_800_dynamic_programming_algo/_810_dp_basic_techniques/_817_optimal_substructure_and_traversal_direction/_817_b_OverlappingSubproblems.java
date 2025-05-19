package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * IDENTIFYING OVERLAPPING SUBPROBLEMS IN DYNAMIC PROGRAMMING
 * <p>
 * Key Points:
 * <p>
 * 1. Overlapping subproblems occur when the same subproblem needs to be solved multiple times
 * in a recursive solution.
 * <p>
 * 2. Techniques to identify overlapping subproblems:
 * - Drawing the recursion tree to visualize repeated calculations
 * - Analyzing the recursive framework of the solution
 * <p>
 * 3. Analyzing the recursive framework:
 * - Strip away the code details to focus on state transitions
 * - If there are multiple paths to reach the same state, overlapping subproblems exist
 * <p>
 * 4. Examples:
 * - Fibonacci sequence: F(5) requires calculating F(3) multiple times
 * - Minimum path sum: Multiple paths can lead to the same cell (i,j)
 * - Regular expression matching: Multiple paths can lead to the same state (i,j)
 * <p>
 * 5. When overlapping subproblems are identified, memoization (top-down) or tabulation (bottom-up)
 * can be used to optimize the solution.
 */
public class _817_b_OverlappingSubproblems {

    /**
     * Example 1: Fibonacci Sequence
     * Classic example of overlapping subproblems.
     * Without memoization, F(5) will recalculate F(3) multiple times.
     */
    public static int fibonacci(int n) {
        // Base cases
        if (n <= 1) return n;

        // Recursive case with overlapping subproblems
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Example 1 (Optimized): Fibonacci with memoization
     * Avoids recalculating the same subproblems.
     */
    public static int fibonacciMemo(int n, Integer[] memo) {
        // Base cases
        if (n <= 1) return n;

        // If already calculated, return from memo
        if (memo[n] != null) return memo[n];

        // Calculate and store in memo
        memo[n] = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        return memo[n];
    }

    /**
     * Example 2: Minimum Path Sum
     * Recursive solution with overlapping subproblems.
     * The abstract recursive framework is:
     * dp(i,j) = min(dp(i-1,j), dp(i,j-1)) + grid[i][j]
     */
    public static int minPathSum(int[][] grid, int i, int j) {
        // Base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // Recursive case with overlapping subproblems
        return Math.min(
                minPathSum(grid, i - 1, j),
                minPathSum(grid, i, j - 1)
        ) + grid[i][j];
    }

    /**
     * Example 2 (Optimized): Minimum Path Sum with memoization
     */
    public static int minPathSumMemo(int[][] grid, int i, int j, Integer[][] memo) {
        // Base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // If already calculated, return from memo
        if (memo[i][j] != null) return memo[i][j];

        // Calculate and store in memo
        memo[i][j] = Math.min(
                minPathSumMemo(grid, i - 1, j, memo),
                minPathSumMemo(grid, i, j - 1, memo)
        ) + grid[i][j];

        return memo[i][j];
    }

    /**
     * Example 3: Abstract framework of regular expression matching
     * Simplified version to demonstrate the state transition framework
     * Actual implementation would be more complex
     */
    public static void demonstrateRegexFramework() {
        System.out.println("Regular Expression Matching Framework:");
        System.out.println("boolean dp(String s, int i, String p, int j) {");
        System.out.println("    dp(s, i, p, j + 2);     // #1");
        System.out.println("    dp(s, i + 1, p, j);     // #2");
        System.out.println("    dp(s, i + 1, p, j + 1); // #3");
        System.out.println("}");
        System.out.println("Multiple paths to reach (i+2, j+2):");
        System.out.println("Path 1: (i,j) -> #1 -> #2 -> #2");
        System.out.println("Path 2: (i,j) -> #3 -> #3");
        System.out.println("This proves overlapping subproblems exist!");
    }

    public static void main(String[] args) {
        // Example 1: Fibonacci
        int n = 10;
        System.out.println("Fibonacci(" + n + ") without memo: " + fibonacci(n));
        System.out.println("Fibonacci(" + n + ") with memo: " + fibonacciMemo(n, new Integer[n + 1]));

        // Example 2: Minimum Path Sum
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int m = grid.length;
        n = grid[0].length;
        System.out.println("Minimum Path Sum without memo: " + minPathSum(grid, m - 1, n - 1));
        System.out.println("Minimum Path Sum with memo: " + minPathSumMemo(grid, m - 1, n - 1, new Integer[m][n]));

        // Example 3: Regular Expression Framework
        demonstrateRegexFramework();
    }
}

