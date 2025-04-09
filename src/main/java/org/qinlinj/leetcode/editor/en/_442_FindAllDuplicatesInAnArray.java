package org.qinlinj.leetcode.editor.en;

import java.util.*;

// [442] Find All Duplicates in an Array
public class _442_FindAllDuplicatesInAnArray {

    public static void main(String[] args) {
        Solution solution = new _442_FindAllDuplicatesInAnArray().new Solution();
        // put your test code here
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(solution.findDuplicates(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDuplicates(int[] nums) {
            if (nums == null || nums.length < 2) {
                return new ArrayList<>();
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int index = nums[i];
                int absIndex = Math.abs(index);
                if (nums[absIndex - 1] < 0) {
                    list.add(-nums[absIndex - 1]);
                } else {
                    nums[absIndex - 1] = -nums[absIndex - 1];
                }
            }
            return list;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}


//Given an integer array nums of length n where all the integers of nums are in
//the range [1, n] and each integer appears at most twice, return an array of all 
//the integers that appears twice. 
//
// You must write an algorithm that runs in O(n) time and uses only constant 
//auxiliary space, excluding the space needed to store the output 
//
// 
// Example 1: 
// Input: nums = [4,3,2,7,8,2,3,1]
//Output: [2,3]
// 
// Example 2: 
// Input: nums = [1,1,2]
//Output: [1]
// 
// Example 3: 
// Input: nums = [1]
//Output: []
// 
// 
// Constraints: 
//
// 
// n == nums.length 
// 1 <= n <= 10⁵ 
// 1 <= nums[i] <= n 
// Each element in nums appears once or twice. 
// 
//
// Related TopicsArray | Hash Table 
//
// 👍 10734, 👎 422bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
