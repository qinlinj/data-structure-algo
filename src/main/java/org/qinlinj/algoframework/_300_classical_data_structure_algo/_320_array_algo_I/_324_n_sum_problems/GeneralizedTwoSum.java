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
}
