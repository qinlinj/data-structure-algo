package org.qinlinj.leetcode.editor.en;

// [189] Rotate Array
public class _189_RotateArray {

    public static void main(String[] args) {
        Solution solution = new _189_RotateArray().new Solution();
        // put your test code here
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7};
        solution.rotate(data, 3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int count = 0;
            for (int start = 0; count < n; start++) {
                int curr = start, currVal = nums[start];
                do {
                    int next = (curr + k) % n;
                    int tmp = nums[next];
                    nums[next] = currVal;
                    curr = next;
                    currVal = tmp;
                    // Use counters to avoid reprocessing elements!
                    count++;
                } while (curr != start);
            }
//            System.out.println(Arrays.toString(nums));
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}


//Given an integer array nums, rotate the array to the right by k steps, where
//k is non-negative. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,4,5,6,7], k = 3
//Output: [5,6,7,1,2,3,4]
//Explanation:
//rotate 1 steps to the right: [7,1,2,3,4,5,6]
//rotate 2 steps to the right: [6,7,1,2,3,4,5]
//rotate 3 steps to the right: [5,6,7,1,2,3,4]
// 
//
// Example 2: 
//
// 
//Input: nums = [-1,-100,3,99], k = 2
//Output: [3,99,-1,-100]
//Explanation: 
//rotate 1 steps to the right: [99,-1,-100,3]
//rotate 2 steps to the right: [3,99,-1,-100]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 0 <= k <= 10⁵ 
// 
//
// 
// Follow up: 
//
// 
// Try to come up with as many solutions as you can. There are at least three 
//different ways to solve this problem. 
// Could you do it in-place with O(1) extra space? 
// 
//
// Related TopicsArray | Math | Two Pointers 
//
// 👍 19265, 👎 2090bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
