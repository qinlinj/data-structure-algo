package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search Applications - Peak Index in Mountain Array
 * <p>
 * Key Concepts:
 * 1. Using binary search to find the peak in a mountain array
 * 2. Leveraging the array's structure to efficiently locate the peak
 * 3. Applying the same approach as the Find Peak Element problem
 * <p>
 * This class implements LeetCode 852: Peak Index in Mountain Array
 * Problem: Find the peak index in a mountain array
 * <p>
 * A mountain array is an array that:
 * - Increases strictly until reaching a peak element
 * - Decreases strictly after the peak element
 * <p>
 * This problem is a simplified version of the Find Peak Element problem
 * because we're guaranteed exactly one peak.
 */
public class _336_j_PeakIndexInMountainArray {

    public static void main(String[] args) {
        _336_j_PeakIndexInMountainArray solution = new _336_j_PeakIndexInMountainArray();

        // Example 1
        int[] arr1 = {0, 1, 0};
        System.out.println("Example 1: " + solution.peakIndexInMountainArray(arr1)); // Expected: 1

        // Example 2
        int[] arr2 = {0, 2, 1, 0};
        System.out.println("Example 2: " + solution.peakIndexInMountainArray(arr2)); // Expected: 1

        // Example 3
        int[] arr3 = {0, 10, 5, 2};
        System.out.println("Example 3: " + solution.peakIndexInMountainArray(arr3)); // Expected: 1

        // Example 4 - larger mountain
        int[] arr4 = {1, 3, 5, 7, 9, 10, 12, 8, 6, 4, 2};
        System.out.println("Example 4: " + solution.peakIndexInMountainArray(arr4)); // Expected: 6

        // Example 5 - peak at different position
        int[] arr5 = {3, 5, 8, 9, 10, 5, 2, 1};
        System.out.println("Example 5: " + solution.peakIndexInMountainArray(arr5)); // Expected: 4
    }

    /**
     * Finds the peak index in a mountain array
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param arr Mountain array
     * @return Index of the peak element
     */
    public int peakIndexInMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            // Invalid input - a mountain array must have at least 3 elements
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare with the next element to determine the trend
            if (arr[mid] > arr[mid + 1]) {
                // Downward trend - peak is at mid or to the left
                right = mid;
            } else {
                // Upward trend - peak is to the right
                left = mid + 1;
            }
        }

        // When left == right, we've found the peak
        return left;
    }

    /**
     * Alternative implementation that explicitly checks both sides
     * This is more intuitive but functionally equivalent
     */
    public int peakIndexInMountainArrayAlternative(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if mid is the peak
            if (mid > 0 && mid < arr.length - 1) {
                if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    return mid;
                } else if (arr[mid] < arr[mid + 1]) {
                    // Upward trend - peak is to the right
                    left = mid + 1;
                } else {
                    // Downward trend - peak is to the left
                    right = mid - 1;
                }
            } else if (mid == 0) {
                // First element cannot be a peak in a mountain array
                left = mid + 1;
            } else {
                // Last element cannot be a peak in a mountain array
                right = mid - 1;
            }
        }

        return -1; // Should never reach here for valid mountain arrays
    }
}
