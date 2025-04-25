package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._325_prefix_sum_technique;

/**
 * 2D Prefix Sum Matrix Technique
 * <p>
 * Key Points:
 * 1. 2D prefix sum extends the 1D prefix sum concept to matrices
 * 2. For a matrix[m][n], prefixSum[i][j] stores the sum of all elements in submatrix (0,0) to (i-1,j-1)
 * 3. Submatrix sum queries from (row1,col1) to (row2,col2) can be calculated in O(1) time using:
 * prefixSum[row2+1][col2+1] - prefixSum[row1][col2+1] - prefixSum[row2+1][col1] + prefixSum[row1][col1]
 * 4. This technique optimizes multiple submatrix sum queries from O(m*n) to O(1) per query
 * 5. The 2D prefix sum matrix requires O(m*n) space and O(m*n) preprocessing time
 * 6. Limitations:
 * - Original matrix must be immutable (changes require recalculation)
 * - Only works for operations with inverse operations (like sum, product)
 * - Not applicable for non-invertible operations (like max, min)
 */
public class PrefixSumMatrix {
}
