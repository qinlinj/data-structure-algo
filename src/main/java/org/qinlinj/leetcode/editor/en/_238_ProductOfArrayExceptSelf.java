package org.qinlinj.leetcode.editor.en;

// [238] Product of Array Except Self
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
    //leetcode submit region end(Prohibit modification and deletion)


}


//Given an integer array nums, return an array answer such that answer[i] is
//equal to the product of all the elements of nums except nums[i]. 
//
// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit 
//integer. 
//
// You must write an algorithm that runs in O(n) time and without using the 
//division operation. 
//
// 
// Example 1: 
// Input: nums = [1,2,3,4]
//Output: [24,12,8,6]
// 
// Example 2: 
// Input: nums = [-1,1,0,-3,3]
//Output: [0,0,9,0,0]
// 
// 
// Constraints: 
//
// 
// 2 <= nums.length <= 10⁵ 
// -30 <= nums[i] <= 30 
// The input is generated such that answer[i] is guaranteed to fit in a 32-bit 
//integer. 
// 
//
// 
// Follow up: Can you solve the problem in O(1) extra space complexity? (The 
//output array does not count as extra space for space complexity analysis.) 
//
// Related TopicsArray | Prefix Sum 
//
// 👍 23944, 👎 1538bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
