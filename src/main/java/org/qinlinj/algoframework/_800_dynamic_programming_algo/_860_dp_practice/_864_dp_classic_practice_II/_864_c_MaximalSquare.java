package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._864_dp_classic_practice_II;

/**
 * LeetCode 221. Maximal Square - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given an m x n binary matrix filled with 0's and 1's, find the largest
 * square containing only 1's and return its area.
 * <p>
 * KEY CONCEPTS:
 * 1. State Definition: dp[i][j] = side length of largest square with bottom-right corner at (i,j)
 * 2. Key Observation: A square can only be formed if:
 * - Current cell is '1'
 * - Left, top, and top-left diagonal neighbors all have squares
 * 3. State Transition (Bucket Effect):
 * dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
 * 4. The minimum determines the constraint (weakest link principle)
 * 5. Final answer: max(dp[i][j])² (area = side length squared)
 * <p>
 * TIME COMPLEXITY: O(m × n)
 * SPACE COMPLEXITY: O(m × n), can be optimized to O(min(m,n))
 * <p>
 * VISUALIZATION:
 * Matrix:  dp array:
 * 1 0 1    1 0 1
 * 1 1 1 -> 1 1 1
 * 1 1 1    1 2 2
 * <p>
 * Largest square has side length 2, so area = 4
 */

public class _864_c_MaximalSquare {

    public static void main(String[] args) {
        _864_c_MaximalSquare solution = new _864_c_MaximalSquare();

        System.out.println("=== Maximal Square Tests ===");

        // Test case 1: Standard case
        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println("Test Case 1:");
        System.out.println("Matrix:");
        printMatrix(matrix1);

        int result1_std = solution.maximalSquare(matrix1);
        int result1_opt = solution.maximalSquareOptimized(matrix1);
        int result1_alt = solution.maximalSquareAlternative(matrix1);

        System.out.printf("Result (Standard DP): %d\n", result1_std);
        System.out.printf("Result (Optimized): %d\n", result1_opt);
        System.out.printf("Result (Alternative): %d\n", result1_alt);
        System.out.println("Explanation: Largest square has side length 2, area = 4\n");

        // Test case 2: Small matrix
        char[][] matrix2 = {
                {'0', '1'},
                {'1', '0'}
        };

        System.out.println("Test Case 2:");
        System.out.println("Matrix:");
        printMatrix(matrix2);

        int result2 = solution.maximalSquare(matrix2);
        System.out.printf("Result: %d\n", result2);
        System.out.println("Explanation: Largest square has side length 1, area = 1\n");

        // Test case 3: All zeros
        char[][] matrix3 = {
                {'0'}
        };

        System.out.println("Test Case 3:");
        System.out.println("Matrix:");
        printMatrix(matrix3);

        int result3 = solution.maximalSquare(matrix3);
        System.out.printf("Result: %d\n", result3);
        System.out.println("Explanation: No squares possible, area = 0\n");

        // Test case 4: Perfect square
        char[][] matrix4 = {
                {'1', '1', '1'},
                {'1', '1', '1'},
                {'1', '1', '1'}
        };

        System.out.println("Test Case 4:");
        System.out.println("Matrix:");
        printMatrix(matrix4);

        int result4 = solution.maximalSquare(matrix4);
        System.out.printf("Result: %d\n", result4);
        System.out.println("Explanation: Perfect 3x3 square, area = 9\n");

        // Demonstrate DP table construction
        System.out.println("=== DP Table Visualization ===");
        System.out.println("For matrix1, the DP table progression:");
        System.out.println("Original Matrix:    DP Table (side lengths):");
        System.out.println("1 0 1 0 0           1 0 1 0 0");
        System.out.println("1 0 1 1 1           1 0 1 1 1");
        System.out.println("1 1 1 1 1    -->    1 1 1 2 2");
        System.out.println("1 0 0 1 0           1 0 0 1 0");
        System.out.println();
        System.out.println("Key insight: dp[2][4] = min(dp[1][4], dp[2][3], dp[1][3]) + 1");
        System.out.println("                     = min(1, 1, 1) + 1 = 2");
        System.out.println("Maximum side length = 2, so area = 4");

        System.out.println("\n=== Algorithm Analysis ===");
        System.out.println("Why the 'bucket effect' formula works:");
        System.out.println("- To form a square of size k at position (i,j):");
        System.out.println("  • Need square of size k-1 at (i-1,j)   [top]");
        System.out.println("  • Need square of size k-1 at (i,j-1)   [left]");
        System.out.println("  • Need square of size k-1 at (i-1,j-1) [diagonal]");
        System.out.println("- The smallest of these determines the constraint");
        System.out.println("- Hence: dp[i][j] = min(top, left, diagonal) + 1");
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Approach 1: Standard 2D DP
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // dp[i][j] = side length of largest square with bottom-right at (i,j)
        int[][] dp = new int[m][n];
        int maxSideLength = 0;

        // Base case: first row and first column
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
            maxSideLength = Math.max(maxSideLength, dp[i][0]);
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j] - '0';
            maxSideLength = Math.max(maxSideLength, dp[0][j]);
        }

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0; // Can't form square with '0'
                } else {
                    // Bucket effect: limited by the smallest neighbor square
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j], dp[i][j - 1]),  // left and top
                            dp[i - 1][j - 1]                        // diagonal
                    ) + 1;

                    maxSideLength = Math.max(maxSideLength, dp[i][j]);
                }
            }
        }

        return maxSideLength * maxSideLength; // Return area
    }

    // Approach 2: Space-optimized using 1D array
    public int maximalSquareOptimized(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Only need previous row and current row
        int[] dp = new int[n];
        int maxSideLength = 0;
        int prevDiagonal = 0; // Store dp[i-1][j-1]

        // Process each row
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int temp = dp[j]; // Store current value before updating

                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    dp[j] = matrix[i][j] - '0';
                } else {
                    // dp[j] represents dp[i-1][j] (top)
                    // dp[j-1] represents dp[i][j-1] (left)
                    // prevDiagonal represents dp[i-1][j-1] (diagonal)
                    dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), prevDiagonal) + 1;
                }

                maxSideLength = Math.max(maxSideLength, dp[j]);
                prevDiagonal = temp; // Update diagonal for next iteration
            }
        }

        return maxSideLength * maxSideLength;
    }

    // Approach 3: Alternative implementation with clearer variable names
    public int maximalSquareAlternative(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] squareSize = new int[rows + 1][cols + 1];
        int maxSize = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    squareSize[i][j] = Math.min(
                            Math.min(squareSize[i - 1][j], squareSize[i][j - 1]),
                            squareSize[i - 1][j - 1]
                    ) + 1;

                    maxSize = Math.max(maxSize, squareSize[i][j]);
                }
                // If matrix[i-1][j-1] == '0', squareSize[i][j] remains 0 (default)
            }
        }

        return maxSize * maxSize;
    }
}