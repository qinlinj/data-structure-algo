package org.qinlinj.leetcode.editor.en;

// [11] Container With Most Water
public class _11_ContainerWithMostWater {

    public static void main(String[] args) {
        Solution solution = new _11_ContainerWithMostWater().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            int left = 0, right = height.length - 1;

            int maxRes = 0;
            while (left < right) {
                if (height[left] > height[right]) {
                    maxRes = Math.max(height[right] * (right - left), maxRes);
                    right--;
                } else {
                    maxRes = Math.max(height[left] * (right - left), maxRes);
                    left++;
                }
            }

            return maxRes;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}


//You are given an integer array height of length n. There are n vertical lines
//drawn such that the two endpoints of the iᵗʰ line are (i, 0) and (i, height[i]).
// 
//
// Find two lines that together with the x-axis form a container, such that the 
//container contains the most water. 
//
// Return the maximum amount of water a container can store. 
//
// Notice that you may not slant the container. 
//
// 
// Example 1: 
// 
// 
//Input: height = [1,8,6,2,5,4,8,3,7]
//Output: 49
//Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,
//3,7]. In this case, the max area of water (blue section) the container can 
//contain is 49.
// 
//
// Example 2: 
//
// 
//Input: height = [1,1]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// n == height.length 
// 2 <= n <= 10⁵ 
// 0 <= height[i] <= 10⁴ 
// 
//
// Related TopicsArray | Two Pointers | Greedy 
//
// 👍 30973, 👎 1969bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
