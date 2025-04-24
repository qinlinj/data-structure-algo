package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

import java.util.*;

/**
 * Sort the Matrix Diagonally (LeetCode 1329)
 * ========================================
 * <p>
 * This class implements a solution to sort the elements in each diagonal of a matrix.
 * <p>
 * Problem:
 * A matrix diagonal is a diagonal line from the top row or leftmost column that extends
 * to the bottom-right of the matrix. Given an m x n integer matrix, sort all elements in
 * each diagonal in ascending order and return the resulting matrix.
 * <p>
 * Examples:
 * - Input: [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * <p>
 * - Input: [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 * <p>
 * Key Insight:
 * All elements in the same diagonal have the same difference between their row and column indices.
 * For example, mat[i][j] and mat[i+1][j+1] are in the same diagonal and both have (i-j) as their diagonal ID.
 * <p>
 * Approach:
 * 1. Collect all elements in each diagonal using the difference between row and column indices as a diagonal ID.
 * 2. Sort each diagonal.
 * 3. Put the sorted elements back into the matrix.
 * <p>
 * Time Complexity: O(m*n*log(min(m,n))) where m and n are the dimensions of the matrix
 * Space Complexity: O(m*n) for storing the diagonals
 */
public class _323_f_SortMatrixDiagonally {
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
}
