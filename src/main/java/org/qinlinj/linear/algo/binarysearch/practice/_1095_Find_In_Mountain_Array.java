package org.qinlinj.linear.algo.binarysearch.practice;

public class _1095_Find_In_Mountain_Array {
    public int findInMountainArray(int target, int[] nums) {
        int peakIndex = searchPeakIndex(nums);
        int index = binarySearchFrontPart(nums, 0, peakIndex, target);
        if (index != -1) {
            return index;
        }
        return binarySearchLatterPart(nums, peakIndex, nums.length - 1, target);
    }

    private int searchPeakIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int binarySearchFrontPart(int[] nums, int left, int peakIndex, int target) {
        while (left < peakIndex) {
            int mid = left + (peakIndex - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                peakIndex = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }
    
    private int binarySearchLatterPart(int[] nums, int peakIndex, int right, int target) {
        while (peakIndex < right) {
            int mid = peakIndex + (right - peakIndex) / 2;
            if (target < nums[mid]) {
                peakIndex = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[peakIndex] == target) return peakIndex;
        return -1;
    }
}
