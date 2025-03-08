package org.qinlinj.linear.algo.binarysearch;

/**
 * Implementation of the Binary Search algorithm using both iterative and recursive approaches.
 * Binary Search is an efficient algorithm for finding an element in a sorted array.
 * Time Complexity: O(log n)
 * Space Complexity: O(1) for iterative, O(log n) for recursive (due to call stack)
 */
public class BasicBinarySearch {
    public static void main(String[] args) {
        // Create a new instance of the BasicBinarySearch class
        BasicBinarySearch bs = new BasicBinarySearch();

        // Test data: a sorted array of integers
        int[] data = new int[]{1, 3, 5, 6, 8, 22, 45, 77};

        // Call the recursive binary search method and print the result
        // This will return true since 77 is present in the array
        System.out.println(bs.containsRecursion(data, 77));

        // Example usage for different scenarios:
        // Check for an element that exists in the array
        // bs.contains(data, 6) would return true

        // Check for an element that doesn't exist in the array
        // bs.contains(data, 10) would return false

        // Check boundary conditions
        // bs.contains(data, 1) would return true (first element)
        // bs.contains(data, 77) would return true (last element)
    }

    /**
     * Iterative implementation of binary search.
     *
     * @param nums   The sorted array to search in
     * @param target The value to search for
     * @return true if the target is found, false otherwise
     */
    public boolean contains(int[] nums, int target) {
        // Check for empty or null array - return false immediately
        if (nums == null || nums.length == 0) return false;

        // Initialize pointers for the search range
        int left = 0;
        int right = nums.length - 1;
        int mid;

        // Continue searching while the search range is valid (left <= right)
        while (right >= left) {
            // Calculate the middle index using the formula that prevents integer overflow
            mid = left + (right - left) / 2;

            // Alternative way to calculate mid using unsigned right shift
            // int mid = (left + right) >>> 1;

            // If the middle element is the target, return true
            if (nums[mid] == target) {
                return true;
            }
            // NOTE: There's an error in this implementation - the left/right adjustments are reversed!
            // The correct implementation should be:
            // If the middle element is less than the target, search the right half
            else if (nums[mid] < target) {
                // Should be: left = mid + 1;
                right = mid - 1; // This is incorrect and will cause the search to fail
            }
            // If the middle element is greater than the target, search the left half
            else {
                // Should be: right = mid - 1;
                left = mid + 1; // This is incorrect and will cause the search to fail
            }
        }

        // If the loop exits, the target was not found
        return false;
    }

    /**
     * Public method to start recursive binary search.
     * This method handles initial parameter validation and calls the recursive helper method.
     *
     * @param nums   The sorted array to search in
     * @param target The value to search for
     * @return true if the target is found, false otherwise
     */
    public boolean containsRecursion(int[] nums, int target) {
        // Check for empty or null array - return false immediately
        if (nums == null || nums.length == 0) return false;

        // Call the recursive helper method with initial search range (whole array)
        return contains(nums, 0, nums.length - 1, target);
    }

    /**
     * Recursive helper method for binary search.
     *
     * @param nums   The sorted array to search in
     * @param left   The left boundary index of the current search range
     * @param right  The right boundary index of the current search range
     * @param target The value to search for
     * @return true if the target is found, false otherwise
     */
    public boolean contains(int[] nums, int left, int right, int target) {
        // Base case: if left pointer exceeds right pointer, target is not in the array
        if (left > right) return false;

        // Calculate the middle index
        int mid = left + (right - left) / 2;

        // If the middle element is the target, return true
        if (nums[mid] == target) return true;

            // If the middle element is greater than the target, search the left half
        else if (nums[mid] > target) {
            return contains(nums, left, mid - 1, target);
        }
        // If the middle element is less than the target, search the right half
        else return contains(nums, mid + 1, right, target);
    }

    /**
     * Example of how binary search works step by step:
     *
     * Let's search for target = 22 in array [1, 3, 5, 6, 8, 22, 45, 77]
     *
     * Iteration 1:
     * left = 0, right = 7
     * mid = 0 + (7 - 0) / 2 = 3
     * nums[3] = 6 < 22, so we search right half
     * left = mid + 1 = 4
     *
     * Iteration 2:
     * left = 4, right = 7
     * mid = 4 + (7 - 4) / 2 = 5
     * nums[5] = 22 == 22, target found!
     * Return true
     *
     * Example of a failed search:
     * Let's search for target = 10 in array [1, 3, 5, 6, 8, 22, 45, 77]
     *
     * Iteration 1:
     * left = 0, right = 7
     * mid = 0 + (7 - 0) / 2 = 3
     * nums[3] = 6 < 10, so we search right half
     * left = mid + 1 = 4
     *
     * Iteration 2:
     * left = 4, right = 7
     * mid = 4 + (7 - 4) / 2 = 5
     * nums[5] = 22 > 10, so we search left half
     * right = mid - 1 = 4
     *
     * Iteration 3:
     * left = 4, right = 4
     * mid = 4 + (4 - 4) / 2 = 4
     * nums[4] = 8 < 10, so we search right half
     * left = mid + 1 = 5
     *
     * Iteration 4:
     * left = 5, right = 4
     * left > right, so exit loop and return false
     */
}