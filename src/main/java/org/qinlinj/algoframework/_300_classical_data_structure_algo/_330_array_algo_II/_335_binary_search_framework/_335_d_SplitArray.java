package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._335_binary_search_framework;

/**
 * Example 3: Split Array Largest Sum (LeetCode 410)
 * <p>
 * Problem:
 * - Split a non-negative integer array into m non-empty continuous subarrays
 * - Minimize the largest sum among these m subarrays
 * <p>
 * Binary search solution:
 * - Variable x: Maximum allowed sum of any subarray
 * - Function f(x): The minimum number of subarrays needed so that no subarray sum exceeds x
 * - Target: The given m (number of subarrays)
 * - Goal: Find the minimum value x where f(x) <= m
 * <p>
 * This problem is conceptually identical to the "Shipping Packages" problem:
 * - Packages = array elements
 * - Ship capacity = maximum subarray sum
 * - Days = number of subarrays
 */
public class _335_d_SplitArray {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _335_d_SplitArray solution = new _335_d_SplitArray();

        // Example 1
        int[] nums1 = {7, 2, 5, 10, 8};
        int m1 = 2;
        System.out.println("Example 1: " + solution.splitArray(nums1, m1)); // Expected: 18

        // Example 2
        int[] nums2 = {1, 2, 3, 4, 5};
        int m2 = 2;
        System.out.println("Example 2: " + solution.splitArray(nums2, m2)); // Expected: 9

        // Example 3
        int[] nums3 = {1, 4, 4};
        int m3 = 3;
        System.out.println("Example 3: " + solution.splitArray(nums3, m3)); // Expected: 4
    }

    /**
     * Main function to solve the problem
     *
     * @param nums Array to be split
     * @param m    Number of subarrays
     * @return Minimized largest sum
     */
    public int splitArray(int[] nums, int m) {
        // Initialize search boundaries
        int left = 0;
        int right = 0;

        // Find the maximum element (minimum possible answer)
        // and the sum of all elements (maximum possible answer)
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        // Add 1 to right for exclusive bound
        right += 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countSubarrays(nums, mid) <= m) {
                // If we can split into <= m subarrays with max sum mid,
                // try to find a smaller maximum sum (search left)
                right = mid;
            } else {
                // Otherwise, we need a larger maximum sum (search right)
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Counts the minimum number of subarrays needed so that no subarray sum exceeds maxSum
     *
     * @param nums   Array to be split
     * @param maxSum Maximum allowed sum for any subarray
     * @return Minimum number of subarrays needed
     */
    private int countSubarrays(int[] nums, int maxSum) {
        int count = 1;
        int currentSum = 0;

        for (int num : nums) {
            // If adding this number would exceed maxSum, start a new subarray
            if (currentSum + num > maxSum) {
                count++;
                currentSum = 0;
            }
            currentSum += num;
        }

        return count;
    }
}
