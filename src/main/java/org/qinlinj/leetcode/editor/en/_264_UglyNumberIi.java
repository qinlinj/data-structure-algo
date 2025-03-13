package org.qinlinj.leetcode.editor.en;

// [264] Ugly Number II
public class _264_UglyNumberIi {
    public static void main(String[] args) {
        Solution solution = new _264_UglyNumberIi().new Solution();
        int n = 10;
        System.out.println(solution.nthUglyNumber(n));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int nthUglyNumber(int n) {
            int[] ugly = new int[n];
            ugly[0] = 1;

            int index2 = 0, index3 = 0, index5 = 0;

            for (int i = 1; i < n; i++) {
                int next2 = ugly[index2] * 2;
                int next3 = ugly[index3] * 3;
                int next5 = ugly[index5] * 5;

                int nextUgly = Math.min(next2, Math.min(next3, next5));
                ugly[i] = nextUgly;

                if (nextUgly == next2) {
                    index2++;
                }
                if (nextUgly == next3) {
                    index3++;
                }
                if (nextUgly == next5) {
                    index5++;
                }
            }

            return ugly[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}


//An ugly number is a positive integer whose prime factors are limited to 2, 3,
//and 5.
//
// Given an integer n, return the nᵗʰ ugly number.
//
//
// Example 1:
//
//
//Input: n = 10
//Output: 12
//Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10
//ugly numbers.
//
//
// Example 2:
//
//
//Input: n = 1
//Output: 1
//Explanation: 1 has no prime factors, therefore all of its prime factors are
//limited to 2, 3, and 5.
//
//
//
// Constraints:
//
//
// 1 <= n <= 1690
//
//
// Related TopicsHash Table | Math | Dynamic Programming | Heap (Priority Queue)
//
//
// 👍 6632, 👎 416bug 反馈 | 使用指南 | 更多配套插件
//
//
//
//
