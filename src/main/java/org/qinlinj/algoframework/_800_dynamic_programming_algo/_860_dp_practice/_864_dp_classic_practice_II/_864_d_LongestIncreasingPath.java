package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._864_dp_classic_practice_II;

/**
 * LeetCode 329. Longest Increasing Path in a Matrix - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given an m x n integers matrix, return the length of the longest increasing path.
 * From each cell, you can move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary.
 * <p>
 * KEY CONCEPTS:
 * 1. DFS + Memoization approach (top-down DP)
 * 2. Unlike shortest path (BFS/Dijkstra), longest path requires exhaustive search
 * 3. No cycles possible due to strictly increasing constraint - no need for visited array
 * 4. State Definition: memo[i][j] = longest increasing path starting from (i,j)
 * 5. For each cell, try all 4 directions and take maximum
 * 6. Memoization prevents recomputation of overlapping subproblems
 * <p>
 * TIME COMPLEXITY: O(m × n) - each cell computed once due to memoization
 * SPACE COMPLEXITY: O(m × n) for memoization + O(m × n) for recursion stack worst case
 * <p>
 * EXAMPLE:
 * Matrix: [[9,9,4],[6,6,8],[2,1,1]]
 * Longest path: [1,2,6,9] with length 4
 */

public class _864_d_LongestIncreasingPath {
    private int[][] memo;
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up

    public static void main(String[] args) {
        _864_d_LongestIncreasingPath solution = new _864_d_LongestIncreasingPath();

        System.out.println("=== Longest Increasing Path in Matrix Tests ===");

        // Test case 1: Standard case
        int[][] matrix1 = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        System.out.println("Test Case 1:");
        System.out.println("Matrix:");
        printMatrix(matrix1);

        int result1 = solution.longestIncreasingPath(matrix1);
        int result1_alt = solution.longestIncreasingPathAlternative(matrix1);

        System.out.printf("Result (Main): %d\n", result1);
        System.out.printf("Result (Alternative): %d\n", result1_alt);
        System.out.println("Explanation: Longest path [1,2,6,9] has length 4\n");

        // Test case 2: Another example
        int[][] matrix2 = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };

        System.out.println("Test Case 2:");
        System.out.println("Matrix:");
        printMatrix(matrix2);

        int result2 = solution.longestIncreasingPath(matrix2);
        System.out.printf("Result: %d\n", result2);
        System.out.println("Explanation: Longest path [3,4,5,6] has length 4\n");

        // Test case 3: Single cell
        int[][] matrix3 = {{1}};

        System.out.println("Test Case 3:");
        System.out.println("Matrix:");
        printMatrix(matrix3);

        int result3 = solution.longestIncreasingPath(matrix3);
        System.out.printf("Result: %d\n", result3);
        System.out.println("Explanation: Single cell, path length is 1\n");

        // Test case 4: Decreasing values
        int[][] matrix4 = {
                {7, 6, 5},
                {8, 4, 3},
                {9, 2, 1}
        };

        System.out.println("Test Case 4:");
        System.out.println("Matrix:");
        printMatrix(matrix4);

        int result4 = solution.longestIncreasingPath(matrix4);
        System.out.printf("Result: %d\n", result4);
        System.out.println("Explanation: Longest path [1,2,3,4,5,6,7,8,9] has length 9\n");

        // Algorithm analysis
        System.out.println("=== Algorithm Analysis ===");
        System.out.println("Why DFS + Memoization works well here:");
        System.out.println("1. Longest path problem requires exhaustive search");
        System.out.println("2. Strictly increasing constraint prevents cycles");
        System.out.println("3. No need for visited array - can't revisit due to increasing constraint");
        System.out.println("4. Memoization saves results for overlapping subproblems");
        System.out.println("5. Each cell is computed exactly once");
        System.out.println();

        System.out.println("State transition logic:");
        System.out.println("memo[i][j] = 1 + max(memo[x][y]) for all valid neighbors (x,y)");
        System.out.println("where matrix[x][y] > matrix[i][j]");
        System.out.println();

        System.out.println("Why this differs from shortest path algorithms:");
        System.out.println("- Shortest path: Greedy approach works (Dijkstra, BFS)");
        System.out.println("- Longest path: Must explore all possibilities");
        System.out.println("- Converting to shortest path (negative weights) doesn't work");
        System.out.println("- First path to destination ≠ longest path");
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%2d ", val);
            }
            System.out.println();
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Initialize memoization array
        memo = new int[m][n];

        int maxLength = 0;

        // Try starting from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, i, j));
            }
        }

        return maxLength;
    }

    /**
     * DFS to find longest increasing path starting from (i, j)
     *
     * @param matrix The input matrix
     * @param i      Current row
     * @param j      Current column
     * @return Length of longest increasing path starting from (i,j)
     */
    private int dfs(int[][] matrix, int i, int j) {
        // Return cached result if already computed
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // At minimum, path length is 1 (the cell itself)
        int maxPath = 1;

        // Try all four directions
        for (int[] dir : directions) {
            int newI = i + dir[0];
            int newJ = j + dir[1];

            // Check bounds
            if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) {
                continue;
            }

            // Only move to strictly larger values
            if (matrix[newI][newJ] > matrix[i][j]) {
                maxPath = Math.max(maxPath, 1 + dfs(matrix, newI, newJ));
            }
        }

        // Cache and return result
        memo[i][j] = maxPath;
        return maxPath;
    }

    // Alternative implementation with clearer variable names
    public int longestIncreasingPathAlternative(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] cache = new int[rows][cols];
        int globalMax = 1;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int pathLength = findLongestPath(matrix, row, col, cache);
                globalMax = Math.max(globalMax, pathLength);
            }
        }

        return globalMax;
    }

    private int findLongestPath(int[][] matrix, int row, int col, int[][] cache) {
        if (cache[row][col] != 0) {
            return cache[row][col];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int currentMax = 1; // At least the current cell

        // Check all four neighbors
        int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] neighbor : neighbors) {
            int newRow = row + neighbor[0];
            int newCol = col + neighbor[1];

            if (isValid(newRow, newCol, rows, cols) &&
                    matrix[newRow][newCol] > matrix[row][col]) {

                int pathFromNeighbor = 1 + findLongestPath(matrix, newRow, newCol, cache);
                currentMax = Math.max(currentMax, pathFromNeighbor);
            }
        }

        cache[row][col] = currentMax;
        return currentMax;
    }

    private boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}