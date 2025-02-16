package org.qinlinj.linear.algo.binarysearch;

public class BasicBinarySearch {
    public static void main(String[] args) {
        BasicBinarySearch bs = new BasicBinarySearch();
        int[] data = new int[]{1, 3, 5, 6, 8, 22, 45, 77};
        System.out.println(bs.containsRecursion(data, 77));
    }

    public boolean contains(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (right >= left) {
            mid = left + (right - left) / 2;
            // >>> 无符号右移
            // int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public boolean containsRecursion(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        return contains(nums, 0, nums.length - 1, target);
    }

    public boolean contains(int[] nums, int left, int right, int target) {
        if (left > right) return false;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return true;
        else if (nums[mid] > target) {
            return contains(nums, left, mid - 1, target);
        } else return contains(nums, mid + 1, right, target);
    }
}
