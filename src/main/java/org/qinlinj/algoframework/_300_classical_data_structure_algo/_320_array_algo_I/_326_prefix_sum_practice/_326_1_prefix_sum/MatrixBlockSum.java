package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_1_prefix_sum;

/**
 * Matrix Block Sum (LeetCode 1314)
 * <p>
 * Key Points:
 * 1. Uses 2D prefix sum technique to efficiently compute matrix block sums
 * 2. For each position (i,j), calculates the sum of elements in the matrix within distance k
 * 3. The problem can be reduced to multiple submatrix sum queries using the NumMatrix class
 * 4. Time complexity: O(m*n) preprocessing + O(m*n) query operations = O(m*n)
 * 5. Space complexity: O(m*n) for the prefix sum matrix
 * 6. Uses Math.min/max to elegantly handle boundary conditions
 */
public class MatrixBlockSum {
    /**
     * Example usage
     */
    public static void main(String[] args) {
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k = 1;

        MatrixBlockSum solution = new MatrixBlockSum();
        int[][] result = solution.matrixBlockSum(mat, k);

        System.out.println("Original Matrix:");
        printMatrix(mat);

        System.out.println("\nBlock Sum Matrix (k=" + k + "):");
        printMatrix(result);
        // Expected: [[12,21,16],[27,45,33],[24,39,28]]
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.print("[");
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j]);
                if (j < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    /**
     * Calculates a matrix where each element is the sum of elements in the original matrix
     * that are at most k positions away (Manhattan distance)
     *
     * @param mat Original matrix
     * @param k   Distance parameter
     * @return Result matrix with block sums
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        NumMatrix numMatrix = new NumMatrix(mat);
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Calculate top-left corner coordinates
                int x1 = Math.max(i - k, 0);
                int y1 = Math.max(j - k, 0);

                // Calculate bottom-right corner coordinates
                int x2 = Math.min(i + k, m - 1);
                int y2 = Math.min(j + k, n - 1);

                // Use the NumMatrix helper to calculate the block sum
                res[i][j] = numMatrix.sumRegion(x1, y1, x2, y2);
            }
        }
        return res;
    }

    /**
     * Helper class for efficient submatrix sum queries using prefix sum technique
     */
    static class NumMatrix {
        // preSum[i][j] represents the sum of elements in submatrix from (0,0) to (i-1,j-1)
        private int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            if (m == 0 || n == 0) return;

            // Construct prefix sum matrix
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // Calculate the sum of submatrix (0,0) to (i-1,j-1)
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
                }
            }
        }

        /**
         * Calculate the sum of elements in submatrix from (x1,y1) to (x2,y2) inclusive
         */
        public int sumRegion(int x1, int y1, int x2, int y2) {
            // Apply inclusion-exclusion principle using prefix sums
            return preSum[x2 + 1][y2 + 1] - preSum[x1][y2 + 1] - preSum[x2 + 1][y1] + preSum[x1][y1];
        }
    }
}
