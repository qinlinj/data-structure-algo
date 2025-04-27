package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * ===============================================================================
 * BINARY SEARCH TYPES COMPARISON:
 * ===============================================================================
 * <p>
 * 1. STANDARD BINARY SEARCH:
 * - Purpose: Find any occurrence of the target in a sorted array
 * - Key behavior: Return immediately when target is found
 * - When target equals mid: return mid
 * - Return value: Any valid index of target or -1 if not found
 * - Problem types it solves:
 * * Check if element exists in sorted array
 * * Find any occurrence of a value
 * * Implement sets/maps with O(log n) lookup
 * * Quick existence checks in sorted data
 * <p>
 * 2. LEFT-BOUND BINARY SEARCH:
 * - Purpose: Find the leftmost/first occurrence of target
 * - Key behavior: Continue searching left side even when target is found
 * - When target equals mid: right = mid - 1
 * - Return value: Leftmost index of target or -1 if not found
 * - When target not found: Returns index of smallest element > target
 * - Problem types it solves:
 * * Find first occurrence in array with duplicates
 * * Find insertion position for maintaining sorted order
 * * Implement lower_bound function (smallest element >= target)
 * * Find element not smaller than target (ceiling function)
 * * Count elements smaller than target
 * * Find the smallest element that satisfies a condition
 * <p>
 * 3. RIGHT-BOUND BINARY SEARCH:
 * - Purpose: Find the rightmost/last occurrence of target
 * - Key behavior: Continue searching right side even when target is found
 * - When target equals mid: left = mid + 1
 * - Return value: Rightmost index of target or -1 if not found
 * - When target not found: Returns index of largest element < target
 * - Problem types it solves:
 * * Find last occurrence in array with duplicates
 * * Implement upper_bound function (smallest element > target)
 * * Find element not greater than target (floor function)
 * * Count elements less than or equal to target
 * * Find the largest element that satisfies a condition
 * <p>
 * 4. APPLICATIONS USING COMBINATIONS:
 * - Find number of occurrences of an element (right_bound - left_bound + 1)
 * - Find range of indices for a target (first and last position)
 * - Binary search on answer (min/max problems using binary search)
 * - Search in rotated sorted arrays
 * - Find peak in mountain arrays
 * - Find kth smallest element using binary search on value
 * - Find median of two sorted arrays
 * <p>
 * ===============================================================================
 * UNIFIED BINARY SEARCH FRAMEWORK:
 * ===============================================================================
 * <p>
 * 1. All implementations use a closed interval [left, right]
 * <p>
 * 2. All implementations share the same initialization and loop structure:
 * - Initialize left = 0, right = nums.length - 1
 * - Loop condition: while (left <= right)
 * - Mid calculation: mid = left + (right - left) / 2
 * <p>
 * 3. The only difference is how we handle nums[mid] == target:
 * - Standard search: return immediately
 * - Left-bound search: move right boundary to mid-1
 * - Right-bound search: move left boundary to mid+1
 * <p>
 * 4. Different return logic:
 * - Standard search: return -1 if not found
 * - Left-bound search: check nums[left] == target
 * - Right-bound search: check nums[right] == target
 */
public class _334_f_BinarySearchUnified {

    // Search type constants
    public static final int STANDARD = 0;
    public static final int LEFT_BOUND = 1;
    public static final int RIGHT_BOUND = 2;

    /**
     * Unified binary search implementation that can perform all three types of search
     *
     * @param nums       Sorted array to search in
     * @param target     Value to search for
     * @param searchType Type of search: STANDARD, LEFT_BOUND, or RIGHT_BOUND
     * @return Index based on search type or -1 if not found
     */
    public static int binarySearch(int[] nums, int target, int searchType) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // Initialize boundaries for closed interval [left, right]
        int left = 0;
        int right = nums.length - 1;

        // Main binary search loop
        while (left <= right) {
            // Calculate middle index (avoid potential integer overflow)
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                // Target is in the right half for all search types
                left = mid + 1;
            } else if (nums[mid] > target) {
                // Target is in the left half for all search types
                right = mid - 1;
            } else {
                // nums[mid] == target
                // The behavior depends on search type
                switch (searchType) {
                    case STANDARD:
                        return mid;  // Standard: return immediately when found
                    case LEFT_BOUND:
                        right = mid - 1;  // Left bound: move right boundary
                        break;
                    case RIGHT_BOUND:
                        left = mid + 1;   // Right bound: move left boundary
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid search type");
                }
            }
        }

        // Return logic varies by search type
        switch (searchType) {
            case STANDARD:
                return -1;  // Standard: target not found

            case LEFT_BOUND:
                // Left bound: check if left index is valid and equals target
                if (left < 0 || left >= nums.length || nums[left] != target) {
                    return -1;
                }
                return left;

            case RIGHT_BOUND:
                // Right bound: check if right index is valid and equals target
                if (right < 0 || right >= nums.length || nums[right] != target) {
                    return -1;
                }
                return right;

            default:
                throw new IllegalArgumentException("Invalid search type");
        }
    }

    /**
     * Convenience method for standard binary search
     */
    public static int binarySearch(int[] nums, int target) {
        return binarySearch(nums, target, STANDARD);
    }

    /**
     * Convenience method for left-bound binary search
     */
    public static int leftBound(int[] nums, int target) {
        return binarySearch(nums, target, LEFT_BOUND);
    }

    /**
     * Convenience method for right-bound binary search
     */
    public static int rightBound(int[] nums, int target) {
        return binarySearch(nums, target, RIGHT_BOUND);
    }

    /**
     * Find the range (first and last occurrence) of target in a sorted array
     *
     * @param nums   Sorted array to search in
     * @param target Value to search for
     * @return Array with [first, last] indices or [-1, -1] if not found
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];

        result[0] = leftBound(nums, target);
        if (result[0] == -1) {
            result[1] = -1;
            return result;
        }

        result[1] = rightBound(nums, target);
        return result;
    }

    /**
     * Return information about return values when targets don't exist
     */
    private static void explainMissingTargetBehavior(int[] nums, int target) {
        // Skip if array is empty
        if (nums == null || nums.length == 0) {
            System.out.println("Array is empty");
            return;
        }

        int standardResult = binarySearch(nums, target);
        int leftResult = leftBound(nums, target);
        int rightResult = rightBound(nums, target);

        System.out.println("Target " + target + " not found in array");
        System.out.println("Standard search returns: " + standardResult);

        // Custom behavior explanation for left bound
        if (leftResult == -1) {
            // Target could be outside array bounds
            if (target < nums[0]) {
                System.out.println("Left bound would be before index 0 (target < all elements)");
            } else if (target > nums[nums.length - 1]) {
                System.out.println("Left bound would be after last index (target > all elements)");
            } else {
                // This case should actually never happen with proper implementation
                System.out.println("Target should be in range but not found");
            }
        } else {
            System.out.println("Left bound returns: " + leftResult +
                    " (index of smallest element >= target)");
        }

        // Custom behavior explanation for right bound
        if (rightResult == -1) {
            // Target could be outside array bounds
            if (target < nums[0]) {
                System.out.println("Right bound would be before index 0 (target < all elements)");
            } else if (target > nums[nums.length - 1]) {
                System.out.println("Right bound would be after last index (target > all elements)");
            } else {
                // This case should actually never happen with proper implementation
                System.out.println("Target should be in range but not found");
            }
        } else {
            System.out.println("Right bound returns: " + rightResult +
                    " (index of largest element <= target)");
        }
    }

    /**
     * Demonstrate unified binary search with examples
     */
    public static void main(String[] args) {
        // Example 1: Target exists multiple times
        int[] nums1 = {1, 2, 2, 2, 3, 4};
        int target1 = 2;
        System.out.println("Example 1: Target exists multiple times");
        System.out.println("Array: [1, 2, 2, 2, 3, 4], Target: " + target1);
        System.out.println("Standard search: " + binarySearch(nums1, target1));
        System.out.println("Left bound: " + leftBound(nums1, target1));
        System.out.println("Right bound: " + rightBound(nums1, target1));

        int[] range1 = searchRange(nums1, target1);
        System.out.println("Range: [" + range1[0] + ", " + range1[1] + "]");

        // Example 2: Target exists once
        int[] nums2 = {1, 3, 5, 7, 9};
        int target2 = 5;
        System.out.println("\nExample 2: Target exists once");
        System.out.println("Array: [1, 3, 5, 7, 9], Target: " + target2);
        System.out.println("Standard search: " + binarySearch(nums2, target2));
        System.out.println("Left bound: " + leftBound(nums2, target2));
        System.out.println("Right bound: " + rightBound(nums2, target2));

        // Example 3: Target doesn't exist but within range
        int[] nums3 = {1, 3, 5, 7, 9};
        int target3 = 6;
        System.out.println("\nExample 3: Target doesn't exist but within range");
        System.out.println("Array: [1, 3, 5, 7, 9], Target: " + target3);
        explainMissingTargetBehavior(nums3, target3);

        // Example 4: Target is smaller than all elements
        int target4 = 0;
        System.out.println("\nExample 4: Target is smaller than all elements");
        System.out.println("Array: [1, 3, 5, 7, 9], Target: " + target4);
        explainMissingTargetBehavior(nums3, target4);

        // Example 5: Target is larger than all elements
        int target5 = 10;
        System.out.println("\nExample 5: Target is larger than all elements");
        System.out.println("Array: [1, 3, 5, 7, 9], Target: " + target5);
        explainMissingTargetBehavior(nums3, target5);
    }
}
