package org.qinlinj.linear.algo.binarysearch.practice;

public class _704_BinarySearch {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid])
                right = mid;
            else
                left = mid;
        }
        // 循环结束后：left + 1 == right
        // 需要后处理，因为在循环中，还有两个个元素没有处理
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid])
                right = mid - 1;
            else
                left = mid;
        }
        // 循环结束后：left == right
        // 需要后处理，因为在循环中，还有一个元素没有处理
        if (nums[left] == target) return left;
        return -1;
    }

    public int search3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid])
                left = mid + 1;
            else
                right = mid;
        }
        // 循环结束后：left == right
        // 需要后处理，因为在循环中，还有一个元素没有处理
        if (nums[left] == target) return left;
        return -1;
    }
}
