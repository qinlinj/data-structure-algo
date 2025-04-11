package org.qinlinj.algocamp.day2;

public class _31_NextPermutation {
    class Solution {
        public void nextPermutation(int[] nums) {
            // Step 1: Find the first pair of elements in ascending order from back to front
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
            }

            if (i >= 0) {
                // Step 2: Find the first element greater than nums[i] from the back
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }

                // Step 3：swap these two elements
                swap(nums, i, j);
            }

            reverse(nums, i + 1, nums.length - 1);
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                swap(nums, start++, end--);
            }
        }
    }
}
