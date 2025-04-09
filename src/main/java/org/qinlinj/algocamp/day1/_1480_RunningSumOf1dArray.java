package org.qinlinj.algocamp.day1;

public class _1480_RunningSumOf1dArray {
    public static void main(String[] args) {
        Solution solution = new _1480_RunningSumOf1dArray().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] runningSum(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return nums;
            }

            for (int i = 1; i < nums.length; i++) {
                nums[i] += nums[i - 1];
            }

            return nums;
        }
    }
}
