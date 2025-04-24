package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._324_n_sum_problems;

import java.util.*;

/**
 * Generalized Two Sum Solutions
 * ============================
 * <p>
 * This class implements multiple variations of the Two Sum problem.
 * <p>
 * Problem Variations:
 * 1. Basic Two Sum: Find one pair of elements that sum to a target
 * 2. All Pairs Two Sum: Find all unique pairs of elements that sum to a target
 * <p>
 * Both problems are solved using the two-pointer technique for efficiency,
 * which requires the array to be sorted first.
 * <p>
 * Time Complexity: O(n log n) due to the sorting operation
 * Space Complexity: O(1) for the algorithm itself (excluding output space)
 */
public class GeneralizedTwoSum {
    /**
     * Basic Two Sum: Find one pair of elements that sum to the target.
     * Assumes there is exactly one solution.
     *
     * @param nums   Array of integers
     * @param target Target sum
     * @return Array containing the two elements that sum to target
     */
    public int[] twoSum(int[] nums, int target) {
        // Sort the array
        Arrays.sort(nums);

        // Use two pointers approaching from both ends
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int sum = nums[lo] + nums[hi];

            if (sum < target) {
                lo++;
            } else if (sum > target) {
                hi--;
            } else {
                // Found the pair
                return new int[]{nums[lo], nums[hi]};
            }
        }

        // No solution found
        return new int[]{};
    }

    /**
     * Generalized Two Sum: Find all unique pairs of elements that sum to the target.
     *
     * @param nums   Array of integers
     * @param target Target sum
     * @return List of all unique pairs that sum to target
     */
    public List<List<Integer>> twoSumTarget(int[] nums, int target) {
        // Sort the array
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int sum = nums[lo] + nums[hi];

            // Save the current values for skipping duplicates later
            int left = nums[lo], right = nums[hi];

            if (sum < target) {
                // Skip all duplicates from the left
                while (lo < hi && nums[lo] == left) lo++;
            } else if (sum > target) {
                // Skip all duplicates from the right
                while (lo < hi && nums[hi] == right) hi--;
            } else {
                // Found a valid pair
                result.add(Arrays.asList(left, right));

                // Skip all duplicates from both sides
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            }
        }

        return result;
    }

    /**
     * Alternative implementation without duplicate skipping in the sum < target
     * and sum > target branches. This version is more straightforward but less efficient.
     */
    public List<List<Integer>> twoSumTargetSimple(int[] nums, int target) {
        // Sort the array
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int sum = nums[lo] + nums[hi];

            if (sum < target) {
                lo++;
            } else if (sum > target) {
                hi--;
            } else {
                // Found a valid pair
                result.add(Arrays.asList(nums[lo], nums[hi]));

                // Skip all duplicates to avoid adding the same pair again
                int left = nums[lo], right = nums[hi];
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            }
        }

        return result;
    }
}
