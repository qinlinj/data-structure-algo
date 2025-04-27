package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * _334_f_BinarySearchUnified.java
 * <p>
 * This class provides a unified framework for all three types of binary search:
 * - Standard binary search: Find any occurrence of target
 * - Left-bound binary search: Find leftmost occurrence of target
 * - Right-bound binary search: Find rightmost occurrence of target
 * <p>
 * UNIFIED BINARY SEARCH FRAMEWORK:
 * ------------------------------
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
}
