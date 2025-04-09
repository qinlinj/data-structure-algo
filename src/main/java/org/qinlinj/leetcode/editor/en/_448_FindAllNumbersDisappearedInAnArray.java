package org.qinlinj.leetcode.editor.en;

import java.util.*;

// [448] Find All Numbers Disappeared in an Array
public class _448_FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        Solution solution = new _448_FindAllNumbersDisappearedInAnArray().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int n = nums.length;

            for (int i = 0; i < n; i++) {
                int index = Math.abs(nums[i]) - 1;
                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }

            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) {
                    res.add(i + 1);
                }
            }

            return res;
        }
    }
    
    //leetcode submit region end(Prohibit modification and deletion)

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = (nums[i] - 1 + n) % n;
            nums[index] += n;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }
}

//Given an array nums of n integers where nums[i] is in the range [1, n],
//return an array of all the integers in the range [1, n] that do not appear in nums. 
//
// 
// Example 1: 
// Input: nums = [4,3,2,7,8,2,3,1]
//Output: [5,6]
// 
// Example 2: 
// Input: nums = [1,1]
//Output: [2]
// 
// 
// Constraints: 
//
// 
// n == nums.length 
// 1 <= n <= 10⁵ 
// 1 <= nums[i] <= n 
// 
//
// 
// Follow up: Could you do it without extra space and in O(n) runtime? You may 
//assume the returned list does not count as extra space. 
//
// Related TopicsArray | Hash Table 
//
// 👍 9746, 👎 515bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
