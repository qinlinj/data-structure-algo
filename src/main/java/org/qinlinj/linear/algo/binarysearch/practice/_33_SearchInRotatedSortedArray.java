package org.qinlinj.linear.algo.binarysearch.practice;

public class _33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                // left is ordered
                if (target >= nums[left] && target < nums[mid]) {
                    // search from left
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // right is ordered
                if (target <= nums[right] && target > nums[mid]) {
                    // search from right
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
