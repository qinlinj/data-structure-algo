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
}
