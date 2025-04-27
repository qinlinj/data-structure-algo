package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search Applications - Finding Peak Elements
 * <p>
 * Key Concepts:
 * 1. Using binary search to find a peak element in an unsorted array
 * 2. Leveraging the local trend (increasing or decreasing) to narrow the search
 * 3. Simplified approach focusing only on mid and mid+1 elements
 * <p>
 * This class implements LeetCode 162: Find Peak Element
 * Problem: Find any peak element in an array where a peak is strictly greater than its neighbors
 * <p>
 * The key insight is that we don't need to compare mid with both left and right neighbors.
 * Instead, we can simply compare mid with mid+1 to determine the trend and narrow our search.
 */
public class _336_i_FindPeakElement {

    /**
     * Why this works:
     * <p>
     * 1. If nums[mid] > nums[mid+1], we're on a downward slope, which means:
     * - Either mid is a peak (if nums[mid-1] < nums[mid])
     * - Or there's a peak to the left of mid
     * <p>
     * 2. If nums[mid] < nums[mid+1], we're on an upward slope, which means:
     * - There must be a peak to the right of mid
     * <p>
     * Since the array is guaranteed to have a peak (boundaries are -∞),
     * this approach will always find a peak.
     */

    public static void main(String[] args) {
        _336_i_FindPeakElement solution = new _336_i_FindPeakElement();

        // Example 1
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Example 1: " + solution.findPeakElement(nums1)); // Expected: 2

        // Example 2
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        int result2 = solution.findPeakElement(nums2);
        System.out.println("Example 2: " + result2); // Expected: 1 or 5
        System.out.println("Verification: nums[" + result2 + "] = " + nums2[result2] + " is a peak");

        // Edge case - single element
        int[] nums3 = {1};
        System.out.println("Example 3: " + solution.findPeakElement(nums3)); // Expected: 0

        // Edge case - two elements
        int[] nums4 = {3, 2};
        System.out.println("Example 4: " + solution.findPeakElement(nums4)); // Expected: 0

        int[] nums5 = {1, 3};
        System.out.println("Example 5: " + solution.findPeakElement(nums5)); // Expected: 1
    }

    /**
     * Finds any peak element in the array
     * A peak element is strictly greater than its adjacent elements
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param nums Array where nums[i] != nums[i+1] for all valid i
     * @return Index of any peak element
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return 0;  // Single element is always a peak
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare with the next element to determine the trend
            if (nums[mid] > nums[mid + 1]) {
                // Downward trend - peak is at mid or to the left
                // nums[mid] might be the peak, so include it in the search range
                right = mid;
            } else {
                // Upward trend - peak is to the right
                // nums[mid] cannot be the peak, so exclude it
                left = mid + 1;
            }
        }

        // When left == right, we've found a peak
        return left;
    }
}
