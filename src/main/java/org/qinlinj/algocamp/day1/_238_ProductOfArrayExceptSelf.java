package org.qinlinj.algocamp.day1;

public class _238_ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        Solution solution = new _238_ProductOfArrayExceptSelf().new Solution();
        // put your test code here
        int[] data = new int[]{-1, 1, 0, -3, 3};
        solution.productExceptSelf(data);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int[] output = new int[nums.length];
            output[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                output[i] = nums[i - 1] * output[i - 1];
            }

            int currRightProduct = 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                output[i] = currRightProduct * output[i];
                currRightProduct = currRightProduct * nums[i];
            }
            return output;
        }
    }
}
