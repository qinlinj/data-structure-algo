package org.qinlinj.linear.algo.binarysearch.practice;

public class _34_FindFirstAndLastPositionOfElementInSortedArray {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[]{-1, -1};
            }
            return new int[]{searchFirstIndex(nums, target), searchLastIndex(nums, target)};
        }

        public int searchFirstIndex(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    if (mid != 0 && nums[mid] == nums[mid - 1]) {
                        right = mid - 1;
                    } else return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else right = mid - 1;
            }
            return -1;
        }

        public int searchLastIndex(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    if (mid != nums.length - 1 && nums[mid] == nums[mid + 1]) {
                        left = mid + 1;
                    } else return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else right = mid - 1;
            }
            return -1;
        }
    }
}
