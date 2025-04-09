package org.qinlinj.leetcode.editor.en;

// [283] Move Zeroes
public class _283_MoveZeroes {

    public static void main(String[] args) {
        Solution solution = new _283_MoveZeroes().new Solution();
        // put your test code here
        int[] data = new int[]{1, 2, 0, 3, 1, 0, 0, 5};
        solution.moveZeroes(data);
        for (int datum : data) {
            System.out.println(datum);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            int slow = 0, fast = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[fast] != 0) {
                    nums[slow] = nums[fast];
                    slow++;
                }
                fast++;
            }
            for (; slow < nums.length; slow++) {
                nums[slow] = 0;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}


//Given an integer array nums, move all 0's to the end of it while maintaining
//the relative order of the non-zero elements. 
//
// Note that you must do this in-place without making a copy of the array. 
//
// 
// Example 1: 
// Input: nums = [0,1,0,3,12]
//Output: [1,3,12,0,0]
// 
// Example 2: 
// Input: nums = [0]
//Output: [0]
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//Follow up: Could you minimize the total number of operations done?
//
// Related TopicsArray | Two Pointers 
//
// 👍 17737, 👎 517bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
