package org.qinlinj.leetcode.editor.en;

import java.util.*;

// [228] Summary Ranges
public class _228_SummaryRanges {

    public static void main(String[] args) {
        Solution solution = new _228_SummaryRanges().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < nums.length - 1; i++) {
                int curr = i;
                while (curr < nums.length - 1 && nums[curr + 1] - nums[curr] == 1) {
                    curr++;
                }
                if (curr != i) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(nums[i]);
                    sb.append("->");
                    sb.append(nums[curr]);
                    list.add(sb.toString());
                } else {
                    list.add(String.valueOf(nums[i]));
                }
                i = curr;
                if (i == nums.length - 2) {
                    list.add(String.valueOf(nums[i + 1]));
                }
            }
            return list;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}


//You are given a sorted unique integer array nums.
//
// A range [a,b] is the set of all integers from a to b (inclusive). 
//
// Return the smallest sorted list of ranges that cover all the numbers in the 
//array exactly. That is, each element of nums is covered by exactly one of the 
//ranges, and there is no integer x such that x is in one of the ranges but not in 
//nums. 
//
// Each range [a,b] in the list should be output as: 
//
// 
// "a->b" if a != b 
// "a" if a == b 
// 
//
// 
// Example 1: 
//
// 
//Input: nums = [0,1,2,4,5,7]
//Output: ["0->2","4->5","7"]
//Explanation: The ranges are:
//[0,2] --> "0->2"
//[4,5] --> "4->5"
//[7,7] --> "7"
// 
//
// Example 2: 
//
// 
//Input: nums = [0,2,3,4,6,8,9]
//Output: ["0","2->4","6","8->9"]
//Explanation: The ranges are:
//[0,0] --> "0"
//[2,4] --> "2->4"
//[6,6] --> "6"
//[8,9] --> "8->9"
// 
//
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 20 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// All the values of nums are unique. 
// nums is sorted in ascending order. 
// 
//
// Related TopicsArray 
//
// 👍 4244, 👎 2306bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
