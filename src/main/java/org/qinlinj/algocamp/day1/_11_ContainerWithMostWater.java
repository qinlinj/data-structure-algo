package org.qinlinj.algocamp.day1;

public class _11_ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new _11_ContainerWithMostWater().new Solution();
        // put your test code here

    }

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
}
