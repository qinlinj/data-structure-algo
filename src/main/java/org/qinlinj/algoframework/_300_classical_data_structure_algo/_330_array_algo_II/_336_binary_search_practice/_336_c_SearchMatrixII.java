package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search in 2D Arrays - Searching in Partially Sorted Matrix
 * <p>
 * Key Concepts:
 * 1. Using properties of a matrix sorted by both rows and columns
 * 2. Starting from a strategic corner point to efficiently search
 * 3. Eliminating rows or columns based on value comparisons
 * <p>
 * This class implements LeetCode 240: Search a 2D Matrix II
 * Problem: Search for a target value in a matrix with the following properties:
 * - Each row is sorted in ascending order from left to right
 * - Each column is sorted in ascending order from top to bottom
 * <p>
 * This is different from the previous problem because rows don't have the
 * "greater than previous row" property. Instead of binary search, we use a
 * more efficient approach by starting from the top-right corner.
 */
public class _336_c_SearchMatrixII {

    /**
     * Why this approach works:
     * <p>
     * The top-right corner (or bottom-left) is special because:
     * - Moving left decreases values
     * - Moving down increases values
     * <p>
     * If we pick top-left: both right and down increase values (ambiguous direction)
     * If we pick bottom-right: both left and up decrease values (ambiguous direction)
     * <p>
     * This allows us to eliminate one row or column in each step, achieving O(m+n) complexity.
     */

    public static void main(String[] args) {
        _336_c_SearchMatrixII solution = new _336_c_SearchMatrixII();

        // Example 1
        int[][] matrix1 = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target1 = 5;

        System.out.println("Example 1: Target = " + target1);
        System.out.println("Found: " + solution.searchMatrix(matrix1, target1)); // Expected: true

        // Example 2
        int[][] matrix2 = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target2 = 20;

        System.out.println("\nExample 2: Target = " + target2);
        System.out.println("Found: " + solution.searchMatrix(matrix2, target2)); // Expected: false
    }

    /**
     * Searches for a target value in a partially sorted matrix
     * Time Complexity: O(m + n) where m and n are the dimensions of the matrix
     * Space Complexity: O(1)
     *
     * @param matrix Partially sorted matrix
     * @param target Target value to find
     * @return true if target exists in matrix, false otherwise
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // Start from the top-right corner
        int row = 0;
        int col = n - 1;

        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                // Current value is too small, move down to increase values
                row++;
            } else {
                // Current value is too large, move left to decrease values
                col--;
            }
        }

        return false;
    }

    /**
     * Alternative implementation starting from bottom-left corner
     * This is equivalent to the first solution but traverses the matrix differently
     */
    public boolean searchMatrixFromBottomLeft(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // Start from the bottom-left corner
        int row = m - 1;
        int col = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                // Current value is too small, move right to increase values
                col++;
            } else {
                // Current value is too large, move up to decrease values
                row--;
            }
        }

        return false;
    }
}
