package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._335_binary_search_framework;

/**
 * Binary Search Core Framework - Summary
 * <p>
 * This file summarizes key concepts about generalizing binary search beyond sorted arrays.
 * <p>
 * Binary search can be used to solve various problems efficiently when:
 * 1. You can identify a monotonic function f(x) of a variable x
 * 2. You want to find a value of x where f(x) meets a specific target constraint
 * <p>
 * Binary Search Framework:
 * 1. Identify the variable x, function f(x), and target value
 * 2. Determine the search range for x (min and max possible values)
 * 3. Decide whether to search for left boundary or right boundary
 * 4. Implement binary search algorithm accordingly
 * <p>
 * The article discusses several examples demonstrating this framework:
 * - Finding elements in sorted arrays (basic binary search)
 * - Koko eating bananas (finding minimum eating speed)
 * - Shipping packages within days (finding minimum capacity)
 * - Splitting array into subarrays (minimizing maximum subarray sum)
 * <p>
 * This class organizes these concepts into separate examples.
 */

// Main class with basic binary search framework
public class _335_a_BinarySearchCoreFramework {

    /**
     * Basic left boundary binary search template
     * Used when we need to find the minimum valid value
     */
    public static int binarySearchLeftBound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // right is exclusive

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // When target is found, move right boundary to find leftmost occurrence
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid;
            }
        }

        return left;
    }

    /**
     * Basic right boundary binary search template
     * Used when we need to find the maximum valid value
     */
    public static int binarySearchRightBound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // right is exclusive

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // When target is found, move left boundary to find rightmost occurrence
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid;
            }
        }

        return left - 1;
    }

    /**
     * The generalized binary search framework for optimization problems
     */
    public static int binarySearchFramework(int min, int max, Function<Integer, Integer> f, int target) {
        int left = min;
        int right = max + 1; // Making it exclusive

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f.apply(mid) <= target) {
                // Search for left boundary (minimize x where f(x) <= target)
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        // Example of basic binary search
        int[] sortedArray = {1, 2, 3, 3, 3, 5, 7};
        int target = 3;

        int leftBound = binarySearchLeftBound(sortedArray, target);
        int rightBound = binarySearchRightBound(sortedArray, target);

        System.out.println("Left bound of " + target + ": " + leftBound);
        System.out.println("Right bound of " + target + ": " + rightBound);
    }

    // Function interface for the generalized framework
    @FunctionalInterface
    interface Function<T, R> {
        R apply(T t);
    }
}
