package org.qinlinj.leetcode.editor.en;

// [378] Kth Smallest Element in a Sorted Matrix
public class _378_KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        Solution solution = new _378_KthSmallestElementInASortedMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int left = matrix[0][0];
            int right = matrix[n - 1][n - 1];

            while (left < right) {
                int mid = left + (right - left) / 2;
                int count = countLessOrEqual(matrix, mid);

                if (count < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        private int countLessOrEqual(int[][] matrix, int target) {
            int n = matrix.length;
            int count = 0;
            int row = 0;
            int col = n - 1;

            while (row < n && col >= 0) {
                if (matrix[row][col] <= target) {
                    count += col + 1;
                    row++;
                } else {
                    col--;
                }
            }

            return count;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    // "Flat" arrangement
    class Solution0 {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;

            int row = (k - 1) / n;
            int col = (k - 1) % n;

            return matrix[row][col];
        }
    }
}


//Given an n x n matrix where each of the rows and columns is sorted in
//ascending order, return the kᵗʰ smallest element in the matrix. 
//
// Note that it is the kᵗʰ smallest element in the sorted order, not the kᵗʰ 
//distinct element. 
//
// You must find a solution with a memory complexity better than O(n²). 
//
// 
// Example 1: 
//
// 
//Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//Output: 13
//Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and 
//the 8ᵗʰ smallest number is 13
// 
//
// Example 2: 
//
// 
//Input: matrix = [[-5]], k = 1
//Output: -5
// 
//
// 
// Constraints: 
//
// 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// All the rows and columns of matrix are guaranteed to be sorted in non-
//decreasing order. 
// 1 <= k <= n² 
// 
//
// 
// Follow up: 
//
// 
// Could you solve the problem with a constant memory (i.e., O(1) memory 
//complexity)? 
// Could you solve the problem in O(n) time complexity? The solution may be too 
//advanced for an interview but you may find reading this paper fun. 
// 
//
// Related TopicsArray | Binary Search | Sorting | Heap (Priority Queue) | 
//Matrix 
//
// 👍 10138, 👎 370bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
