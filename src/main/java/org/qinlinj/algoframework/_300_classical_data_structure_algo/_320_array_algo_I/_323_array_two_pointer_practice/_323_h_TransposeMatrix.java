package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Transpose Matrix (LeetCode 867)
 * =============================
 * <p>
 * This class implements a solution to transpose a matrix.
 * <p>
 * Problem:
 * Given an m x n matrix, return its transpose. The transpose of a matrix
 * is the matrix flipped over its main diagonal, switching the matrix's
 * row and column indices.
 * <p>
 * Examples:
 * - Input: [[1,2,3],[4,5,6],[7,8,9]] -> Output: [[1,4,7],[2,5,8],[3,6,9]]
 * - Input: [[1,2,3],[4,5,6]] -> Output: [[1,4],[2,5],[3,6]]
 * <p>
 * Approach:
 * Create a new matrix with dimensions swapped (n×m instead of m×n).
 * For each element at position (i, j) in the original matrix,
 * place it at position (j, i) in the new matrix.
 * <p>
 * Note: This operation cannot be done in-place when the matrix dimensions
 * are different (non-square), as the resulting matrix will have different dimensions.
 * <p>
 * Time Complexity: O(m*n) where m and n are the dimensions of the matrix
 * Space Complexity: O(m*n) for the transposed matrix
 */

public class _323_h_TransposeMatrix {

    /**
     * Demonstration of the transpose operation.
     */
    public static void main(String[] args) {
        _323_h_TransposeMatrix solution = new _323_h_TransposeMatrix();

        // Example 1: Square matrix
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Example 1 (Square matrix):");
        System.out.println("Original matrix:");
        solution.printMatrix(matrix1);

        int[][] transposed1 = solution.transpose(matrix1);
        System.out.println("Transposed matrix:");
        solution.printMatrix(transposed1);

        // In-place transpose for square matrix (for educational purposes)
        int[][] matrixCopy = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            System.arraycopy(matrix1[i], 0, matrixCopy[i], 0, matrix1[i].length);
        }

        System.out.println("In-place transpose (only for square matrices):");
        solution.transposeInPlace(matrixCopy);
        solution.printMatrix(matrixCopy);

        // Example 2: Non-square matrix
        int[][] matrix2 = {
                {1, 2, 3},
                {4, 5, 6}
        };

        System.out.println("Example 2 (Non-square matrix):");
        System.out.println("Original matrix:");
        solution.printMatrix(matrix2);

        int[][] transposed2 = solution.transpose(matrix2);
        System.out.println("Transposed matrix:");
        solution.printMatrix(transposed2);

        // Example 3: Another non-square matrix
        int[][] matrix3 = {
                {1, 2},
                {3, 4},
                {5, 6}
        };

        System.out.println("Example 3 (Another non-square matrix):");
        System.out.println("Original matrix:");
        solution.printMatrix(matrix3);

        int[][] transposed3 = solution.transpose(matrix3);
        System.out.println("Transposed matrix:");
        solution.printMatrix(transposed3);
    }

    /**
     * Returns the transpose of the given matrix.
     *
     * @param matrix The input matrix
     * @return The transposed matrix
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;    // Number of rows
        int n = matrix[0].length; // Number of columns

        // Create a new matrix with dimensions swapped
        int[][] result = new int[n][m];

        // Fill the transposed matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // The element at (i, j) in the original matrix
                // goes to position (j, i) in the transposed matrix
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    /**
     * Special case for square matrices where an in-place transposition is possible.
     * This is provided for educational purposes, although the problem does not require it.
     *
     * @param matrix The input square matrix
     */
    public void transposeInPlace(int[][] matrix) {
        int n = matrix.length; // Assuming it's a square matrix

        // Only swap the upper triangular elements with the lower triangular elements
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Swap matrix[i][j] with matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * Prints a matrix for visualization.
     *
     * @param matrix The matrix to print
     */
    public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println();
    }
}
