package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * Standard Binary Search Implementation
 * <p>
 * This class implements the standard binary search algorithm to find a specific target value
 * in a sorted array. If the target exists, it returns the index; otherwise, it returns -1.
 * <p>
 * Key characteristics of this implementation:
 * 1. Search Interval: Closed interval [left, right]
 * 2. Loop Condition: while (left <= right)
 * 3. Target Found: Return immediately when found
 * 4. Boundary Updates:
 * - When nums[mid] < target: left = mid + 1
 * - When nums[mid] > target: right = mid - 1
 * <p>
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 * <p>
 * Limitations:
 * - This algorithm only returns one occurrence of the target.
 * - If multiple occurrences exist, there's no guarantee which one will be returned.
 * - When you need to find the leftmost or rightmost occurrence, use specialized binary search variants.
 */
public class _334_b_BinarySearchStandard {

    /**
     * Example usage of the standard binary search
     */
    public static void main(String[] args) {
        _334_b_BinarySearchStandard solution = new _334_b_BinarySearchStandard();

        // Example 1: Target exists in the array
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target1 = 6;
        int result1 = solution.search(nums1, target1);
        System.out.println("Example 1: Target " + target1 + " found at index " + result1);

        // Example 2: Target doesn't exist in the array
        int[] nums2 = {1, 3, 5, 7, 9};
        int target2 = 4;
        int result2 = solution.search(nums2, target2);
        System.out.println("Example 2: Target " + target2 + " found at index " + result2);

        // Example 3: Duplicate elements
        int[] nums3 = {1, 2, 2, 2, 3, 4, 5};
        int target3 = 2;
        int result3 = solution.search(nums3, target3);
        System.out.println("Example 3: Target " + target3 + " found at index " + result3
                + " (Note: This might not return the leftmost or rightmost occurrence)");
    }

    /**
     * Standard binary search implementation.
     * Searches for target in a sorted array and returns its index if found, -1 otherwise.
     *
     * @param nums   A sorted array of integers
     * @param target The value to search for
     * @return The index of target if found, -1 otherwise
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;  // Closed interval [left, right]

        while (left <= right) {  // Continue while the interval is not empty
            int mid = left + (right - left) / 2;  // Prevent integer overflow

            if (nums[mid] == target) {
                return mid;  // Target found, return index immediately
            } else if (nums[mid] < target) {
                left = mid + 1;  // Target is in the right half
            } else if (nums[mid] > target) {
                right = mid - 1;  // Target is in the left half
            }
        }

        return -1;  // Target not found in the array
    }
}
