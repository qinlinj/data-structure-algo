package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._322_2d_array_traversal;

/**
 * Matrix Rotation Techniques
 * =========================
 * <p>
 * This class demonstrates techniques for rotating a matrix in-place.
 * While many matrix operations seem complex with the usual approach,
 * they can often be simplified by combining basic transformations.
 * <p>
 * Key Techniques:
 * 1. Clockwise 90° Rotation:
 * - Transpose the matrix along the main diagonal (top-left to bottom-right)
 * - Reverse each row
 * <p>
 * 2. Counter-clockwise 90° Rotation:
 * - Transpose the matrix along the anti-diagonal (bottom-left to top-right)
 * - Reverse each row
 * <p>
 * These techniques demonstrate how complex matrix operations can be broken down
 * into simpler transformations, illustrating that sometimes the most elegant
 * solution is not the most intuitive one.
 * <p>
 * Similar principles apply to other problems like:
 * - Reversing words in a string in-place
 * - Rotating a linked list
 */
public class MatrixRotationTechniques {
    /**
     * Rotates a matrix 90 degrees clockwise in-place.
     * <p>
     * The algorithm works in two steps:
     * 1. Transpose the matrix (reflect along the main diagonal)
     * 2. Reverse each row
     * <p>
     * Time Complexity: O(n²) where n is the side length of the matrix
     * Space Complexity: O(1) - in-place operation
     *
     * @param matrix an n×n square matrix
     */
    public void rotateClockwise(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix along the main diagonal
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Swap matrix[i][j] with matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    /**
     * Rotates a matrix 90 degrees counter-clockwise in-place.
     * <p>
     * The algorithm works in two steps:
     * 1. Transpose the matrix along the anti-diagonal
     * 2. Reverse each row
     * <p>
     * Time Complexity: O(n²) where n is the side length of the matrix
     * Space Complexity: O(1) - in-place operation
     *
     * @param matrix an n×n square matrix
     */
    public void rotateCounterClockwise(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix along the anti-diagonal
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                // Swap matrix[i][j] with matrix[n-j-1][n-i-1]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    /**
     * Helper method to reverse an array in-place.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param arr array to reverse
     */
    private void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            // Swap arr[i] and arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
