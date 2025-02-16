package org.qinlinj.linear.algo.binarysearch.practice;

public class _35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }
        if (target > nums[nums.length - 1]) return nums.length;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                if (mid != 0 && nums[mid - 1] >= target) {
                    right = mid - 1;
                } else return mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public int searchInsert_dm(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else right = mid;
        }
        return left;
    }
}
