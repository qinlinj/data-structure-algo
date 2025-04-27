package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search in 2D Arrays - Matrix Reshaping
 * <p>
 * Key Concepts:
 * 1. Mapping between multi-dimensional and one-dimensional coordinates
 * 2. Converting a 2D matrix to a different shape while preserving element order
 * <p>
 * Common techniques:
 * - Converting from 2D to 1D: index = row * cols + col
 * - Converting from 1D to 2D: row = index / cols, col = index % cols
 * - This mapping works for any multi-dimensional array
 * <p>
 * This class implements LeetCode 566: Reshape the Matrix
 * Problem: Reshape an m x n matrix into a new r x c matrix while preserving element order
 */
public class _336_a_ReshapeMatrix {

    public static void main(String[] args) {
        _336_a_ReshapeMatrix solution = new _336_a_ReshapeMatrix();

        // Example 1: Reshape 2x2 to 1x4
        int[][] mat1 = {{1, 2}, {3, 4}};
        int r1 = 1, c1 = 4;

        int[][] result1 = solution.matrixReshape(mat1, r1, c1);
        System.out.println("Example 1 (2x2 → 1x4):");
        printMatrix(result1);

        // Example 2: Reshaping not possible
        int[][] mat2 = {{1, 2}, {3, 4}};
        int r2 = 2, c2 = 4;

        int[][] result2 = solution.matrixReshape(mat2, r2, c2);
        System.out.println("\nExample 2 (Invalid reshape):");
        printMatrix(result2);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    /**
     * Reshapes a matrix to the specified dimensions
     * Time Complexity: O(m*n) where m*n is the total number of elements
     * Space Complexity: O(m*n) for the result matrix
     *
     * @param mat Original matrix
     * @param r   Number of rows in reshaped matrix
     * @param c   Number of columns in reshaped matrix
     * @return Reshaped matrix or original if reshaping is not possible
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;

        // Check if reshaping is possible
        if (r * c != m * n) {
            return mat;
        }

        int[][] result = new int[r][c];

        // Transfer elements using 1D index mapping
        for (int i = 0; i < m * n; i++) {
            // Get value from original matrix using 1D index
            int value = get(mat, i);
            // Set value in new matrix using same 1D index
            set(result, i, value);
        }

        return result;
    }

    /**
     * Gets a value from a matrix using a 1D index
     *
     * @param matrix Input matrix
     * @param index  1D index (0-based)
     * @return Element at the calculated position
     */
    private int get(int[][] matrix, int index) {
        int n = matrix[0].length; // Number of columns

        // Convert 1D index to 2D coordinates
        int row = index / n;
        int col = index % n;

        return matrix[row][col];
    }

    /**
     * Sets a value in a matrix using a 1D index
     *
     * @param matrix Matrix to modify
     * @param index  1D index (0-based)
     * @param value  Value to set
     */
    private void set(int[][] matrix, int index, int value) {
        int n = matrix[0].length; // Number of columns

        // Convert 1D index to 2D coordinates
        int row = index / n;
        int col = index % n;

        matrix[row][col] = value;
    }

    /**
     * More efficient implementation without get/set helper methods
     */
    public int[][] matrixReshapeEfficient(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;

        // Check if reshaping is possible
        if (r * c != m * n) {
            return mat;
        }

        int[][] result = new int[r][c];

        // Flattened index
        int count = 0;

        // Copy elements to new shape
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Calculate target position in reshaped matrix
                result[count / c][count % c] = mat[i][j];
                count++;
            }
        }

        return result;
    }
}
