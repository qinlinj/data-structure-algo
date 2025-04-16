package org.qinlinj.leetcode.editor.en;

// [73] Set Matrix Zeroes
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

    //leetcode submit region begin(Prohibit modification and deletion)
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
    //leetcode submit region end(Prohibit modification and deletion)


}


//Given an m x n integer matrix matrix, if an element is 0, set its entire row
//and column to 0's. 
//
// You must do it in place. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//Output: [[1,0,1],[0,0,0],[1,0,1]]
// 
//
// Example 2: 
// 
// 
//Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -2³¹ <= matrix[i][j] <= 2³¹ - 1 
// 
//
// 
// Follow up: 
//
// 
// A straightforward solution using O(mn) space is probably a bad idea. 
// A simple improvement uses O(m + n) space, but still not the best solution. 
// Could you devise a constant space solution? 
// 
//
// Related TopicsArray | Hash Table | Matrix 
//
// 👍 15490, 👎 787bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
