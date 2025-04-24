package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

import java.util.*;

public class _323_f_SortMatrixDiagonally {

    /**
     * Demonstration of the diagonal sorting algorithm.
     */
    public static void main(String[] args) {
        _323_f_SortMatrixDiagonally solution = new _323_f_SortMatrixDiagonally();

        // Example 1
        int[][] mat1 = {
                {3, 3, 1, 1},
                {2, 2, 1, 2},
                {1, 1, 1, 2}
        };

        System.out.println("Example 1:");
        System.out.println("Original matrix:");
        solution.printMatrix(mat1);

        int[][] sorted1 = solution.diagonalSort(mat1);
        System.out.println("After diagonal sorting:");
        solution.printMatrix(sorted1);

        // Example 2
        int[][] mat2 = {
                {11, 25, 66, 1, 69, 7},
                {23, 55, 17, 45, 15, 52},
                {75, 31, 36, 44, 58, 8},
                {22, 27, 33, 25, 68, 4},
                {84, 28, 14, 11, 5, 50}
        };

        System.out.println("Example 2:");
        System.out.println("Original matrix:");
        solution.printMatrix(mat2);

        int[][] sorted2 = solution.diagonalSort(mat2);
        System.out.println("After diagonal sorting:");
        solution.printMatrix(sorted2);

        // Example with alternative implementation
        int[][] mat3 = {
                {3, 3, 1, 1},
                {2, 2, 1, 2},
                {1, 1, 1, 2}
        };

        System.out.println("Example with alternative implementation:");
        System.out.println("Original matrix:");
        solution.printMatrix(mat3);

        int[][] sorted3 = solution.diagonalSortAlt(mat3);
        System.out.println("After diagonal sorting (alternative):");
        solution.printMatrix(sorted3);
    }

    /**
     * Sorts each diagonal of the matrix in ascending order.
     *
     * @param mat The input matrix
     * @return The matrix with each diagonal sorted
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Store all elements of each diagonal in a map
        // Key: Diagonal ID (row - column), Value: List of elements in that diagonal
        Map<Integer, List<Integer>> diagonals = new HashMap<>();

        // Collect diagonal elements
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // The diagonal ID is the difference between row and column indices
                int diagonalId = i - j;

                // Initialize the list if this is the first element in this diagonal
                diagonals.putIfAbsent(diagonalId, new ArrayList<>());

                // Add the current element to its diagonal list
                diagonals.get(diagonalId).add(mat[i][j]);
            }
        }

        // Sort each diagonal
        for (List<Integer> diagonal : diagonals.values()) {
            Collections.sort(diagonal);
        }

        // Fill the matrix with sorted elements
        // We'll use the same diagonal ID to place elements back
        // For each diagonal, we'll use an index to track which element to place next
        Map<Integer, Integer> indexes = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diagonalId = i - j;

                // Initialize the index for this diagonal if not already done
                indexes.putIfAbsent(diagonalId, 0);

                // Get the current index for this diagonal
                int index = indexes.get(diagonalId);

                // Place the next sorted element from this diagonal
                mat[i][j] = diagonals.get(diagonalId).get(index);

                // Increment the index for this diagonal
                indexes.put(diagonalId, index + 1);
            }
        }

        return mat;
    }

    /**
     * Alternative implementation that more closely matches the provided solution.
     * Uses reverse sorting and removes elements from the end of the list for efficiency.
     */
    public int[][] diagonalSortAlt(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // Store all elements of each diagonal
        HashMap<Integer, ArrayList<Integer>> diagonals = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // The diagonal ID is the difference between row and column indices
                int diagonalId = i - j;

                diagonals.putIfAbsent(diagonalId, new ArrayList<>());
                diagonals.get(diagonalId).add(mat[i][j]);
            }
        }

        // Sort each diagonal in reverse order for efficient removal from the end
        for (ArrayList<Integer> diagonal : diagonals.values()) {
            Collections.sort(diagonal, Collections.reverseOrder());
        }

        // Fill the matrix with sorted elements
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<Integer> diagonal = diagonals.get(i - j);
                // Remove from the end of the list (more efficient)
                mat[i][j] = diagonal.remove(diagonal.size() - 1);
            }
        }

        return mat;
    }

    /**
     * Prints a matrix for visualization.
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