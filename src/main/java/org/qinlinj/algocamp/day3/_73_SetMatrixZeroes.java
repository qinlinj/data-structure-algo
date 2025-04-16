package org.qinlinj.algocamp.day3;

public class _73_SetMatrixZeroes {
    public static void main(String[] args) {
        Solution solution = new _73_SetMatrixZeroes().new Solution();
        // put your test code here
        int[][] data = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        solution.setZeroes(data);
    }

    class Solution {
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            boolean hasZero = false;

            // map 0 to the first column and first row
            for (int i = 0; i < m; i++) {
                // check if the first row contains 0
                if (matrix[i][0] == 0) hasZero = true;
                for (int j = 1; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }

            for (int i = m - 1; i >= 0; i--) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
                if (hasZero) {
                    matrix[i][0] = 0;
                }
            }
        }
    }
}
