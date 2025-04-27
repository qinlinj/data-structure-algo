package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * Binary Search Framework
 * <p>
 * This class introduces the basic template for binary search algorithms.
 * <p>
 * Key points:
 * 1. Binary search requires a sorted array
 * 2. The basic framework consists of:
 * - Setting left and right boundaries
 * - Finding the middle element
 * - Comparing target with middle element
 * - Adjusting search boundaries
 * 3. Binary search has O(log n) time complexity
 * 4. To prevent integer overflow when calculating mid:
 * - Use: mid = left + (right - left) / 2
 * - Instead of: mid = (left + right) / 2
 * 5. Always write out conditions explicitly with else-if statements
 * to better understand the logic and avoid errors
 */
public class _334_a_BinarySearchFramework {

    /**
     * Binary search template function
     * This is just a framework to show the structure
     */
    public int binarySearchTemplate(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // or nums.length depending on approach

        while (left <= right) { // or left < right depending on approach
            // Calculate middle to avoid integer overflow
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // Found target - behavior depends on specific algorithm
                return mid; // or continue searching
            } else if (nums[mid] < target) {
                // Target in right portion
                left = mid + 1;
            } else if (nums[mid] > target) {
                // Target in left portion
                right = mid - 1; // or mid depending on approach
            }
        }

        // Target not found or return appropriate boundary
        return -1; // or other value depending on algorithm
    }
}
