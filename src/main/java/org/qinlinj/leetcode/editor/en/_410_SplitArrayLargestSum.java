package org.qinlinj.leetcode.editor.en;

// [410] Split Array Largest Sum
public class _410_SplitArrayLargestSum {
    public static void main(String[] args) {
        Solution solution = new _410_SplitArrayLargestSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int splitArray(int[] nums, int k) {
            int left = 0;
            int right = 0;
            for (int num : nums) {
                left = Math.max(left, num);
                right += num;
            }
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (getSum(nums, k, mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        private boolean getSum(int[] nums, int k, int mid) {
            int count = 1;
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                if (res + nums[i] > mid) {
                    res = nums[i];
                    count++;
                } else {
                    res += nums[i];
                }
            }
            return count <= k;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}


//Given an integer array nums and an integer k, split nums into k non-empty
//subarrays such that the largest sum of any subarray is minimized. 
//
// Return the minimized largest sum of the split. 
//
// A subarray is a contiguous part of the array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [7,2,5,10,8], k = 2
//Output: 18
//Explanation: There are four ways to split nums into two subarrays.
//The best way is to split it into [7,2,5] and [10,8], where the largest sum 
//among the two subarrays is only 18.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,3,4,5], k = 2
//Output: 9
//Explanation: There are four ways to split nums into two subarrays.
//The best way is to split it into [1,2,3] and [4,5], where the largest sum 
//among the two subarrays is only 9.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 10⁶ 
// 1 <= k <= min(50, nums.length) 
// 
//
// Related TopicsArray | Binary Search | Dynamic Programming | Greedy | Prefix 
//Sum 
//
// 👍 10300, 👎 239bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
