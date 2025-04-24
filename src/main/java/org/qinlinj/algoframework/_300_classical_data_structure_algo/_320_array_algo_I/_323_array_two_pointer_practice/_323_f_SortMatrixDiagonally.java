package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

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
 * <p>
 * public class _323_f_SortMatrixDiagonally {
 * }
