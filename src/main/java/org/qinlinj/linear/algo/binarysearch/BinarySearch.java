package org.qinlinj.linear.algo.binarysearch;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] data = new int[]{1, 3, 5, 6, 8, 22, 22, 22, 22, 22, 45, 77};
        System.out.println(bs.lastLesserEqualTargetElement(data, 23));
    }

    public int firstTargetElement_ql(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left + 1) / 2;
            if (target == nums[mid]) {
                if (mid != 0 && nums[mid - 1] == target) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    public int firstTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == 0 || nums[mid - 1] != target) return mid;
                else right = mid - 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int firstGreaterEqualTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            } else { // target > nums[mid]
                left = mid + 1;
            }
        }
        return -1;
    }

    public int lastTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                } else left = mid + 1;
            } else if (target > nums[mid]) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public int lastLesserEqualTargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target >= nums[mid]) {
                if (mid == nums.length - 1 || nums[mid + 1] > target) {
                    return mid;
                } else left = mid + 1;
            } else right = mid - 1;
        }
        return -1;
    }

}
