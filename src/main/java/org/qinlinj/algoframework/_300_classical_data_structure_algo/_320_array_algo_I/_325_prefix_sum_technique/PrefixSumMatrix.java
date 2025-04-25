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
    /**
     * Implementation of LeetCode 304: Range Sum Query 2D - Immutable
     * Uses the 2D prefix sum technique to efficiently answer submatrix sum queries
     */
    static class NumMatrix {
        // 2D prefix sum array
        private int[][] prefixSum;

        /**
         * Initialize the 2D prefix sum matrix from the input matrix
         * Time complexity: O(m*n) where m is rows and n is columns
         * Space complexity: O(m*n)
         */
        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;

            int m = matrix.length;
            int n = matrix[0].length;

            // prefixSum[i][j] stores sum of submatrix from (0,0) to (i-1,j-1)
            prefixSum = new int[m + 1][n + 1];

            // Build the prefix sum matrix
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // The key formula: include current cell and adjacent sums, subtract the double-counted area
                    prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        /**
         * Returns the sum of elements in the submatrix from (row1,col1) to (row2,col2) inclusive
         * Time complexity: O(1)
         * Space complexity: O(1)
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            // The key insight: use inclusion-exclusion principle with the prefix sums
            return prefixSum[row2 + 1][col2 + 1] - prefixSum[row1][col2 + 1] - prefixSum[row2 + 1][col1] + prefixSum[row1][col1];
        }
    }
}
