package org.qinlinj.leetcode.editor.en;

// [1480] Running Sum of 1d Array
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
    //leetcode submit region end(Prohibit modification and deletion)


}





//Given an array nums. We define a running sum of an array as runningSum[i] = 
//sum(nums[0]…nums[i]). 
//
// Return the running sum of nums. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,4]
//Output: [1,3,6,10]
//Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4]. 
//
// Example 2: 
//
// 
//Input: nums = [1,1,1,1,1]
//Output: [1,2,3,4,5]
//Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+
//1+1+1]. 
//
// Example 3: 
//
// 
//Input: nums = [3,1,2,10,1]
//Output: [3,4,6,16,17]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 1000 
// -10^6 <= nums[i] <= 10^6 
// 
//
// Related TopicsArray | Prefix Sum 
//
// 👍 8178, 👎 355bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
