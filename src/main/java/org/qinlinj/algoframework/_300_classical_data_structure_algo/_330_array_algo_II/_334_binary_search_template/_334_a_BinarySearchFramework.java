package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * Binary Search Framework
 * <p>
 * This class demonstrates the fundamental framework of binary search algorithms.
 * Key points about the binary search approach:
 * <p>
 * 1. Binary search is an efficient algorithm for finding a target value in a sorted array.
 * 2. The core idea is to divide the search interval in half repeatedly.
 * 3. The time complexity is O(log n), where n is the number of elements in the array.
 * 4. When implementing, use mid = left + (right - left) / 2 to prevent integer overflow.
 * 5. For clarity, use "else if" for all conditions rather than "else" to make the logic explicit.
 * 6. Binary search variants include:
 * - Standard binary search (finding if a target exists)
 * - Left bound binary search (finding the leftmost occurrence of target)
 * - Right bound binary search (finding the rightmost occurrence of target)
 * <p>
 * The search interval concept is critical - it can be implemented as:
 * - Closed interval [left, right]
 * - Half-open interval [left, right)
 * <p>
 * This choice affects:
 * - How you initialize variables (particularly right)
 * - Your loop condition (< or <=)
 * - How you update left and right
 * - The final return value and boundary checks
 */
public class _334_a_BinarySearchFramework {

    public static void main(String[] args) {
        System.out.println("Binary Search Framework - see specific implementations in other classes.");
    }

    /**
     * Generic binary search framework (template)
     * This template shows the general structure - implementations will vary
     * based on the specific use case.
     */
    public int binarySearch(int[] nums, int target) {
        // Define the search space boundaries
        int left = 0, right = nums.length - 1;  // or nums.length for half-open interval

        // Continue while there are elements to search
        while (left <= right) {  // or left < right for half-open interval
            // Calculate middle index (prevent integer overflow)
            int mid = left + (right - left) / 2;

            // Three possible conditions
            if (nums[mid] == target) {
                // Target found - action depends on variant
                return mid;  // for standard search
                // Or adjust boundaries for leftmost/rightmost search
            } else if (nums[mid] < target) {
                // Target is in right half
                left = mid + 1;
            } else if (nums[mid] > target) {
                // Target is in left half
                right = mid - 1;  // or mid for half-open interval
            }
        }

        // Return appropriate value based on search variant
        return -1;  // for standard search
        // For bound searches, additional checks may be needed
    }
}