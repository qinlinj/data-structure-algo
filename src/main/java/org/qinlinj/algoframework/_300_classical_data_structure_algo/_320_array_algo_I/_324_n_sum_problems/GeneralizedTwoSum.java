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
     * Demonstration of the Two Sum algorithms.
     */
    public static void main(String[] args) {
        GeneralizedTwoSum solution = new GeneralizedTwoSum();

        // Basic Two Sum example
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 9;

        System.out.println("Basic Two Sum Example:");
        System.out.println("Input: nums = [1, 3, 5, 6], target = 9");
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.print("Output: [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i]);
            if (i < result1.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println();

        // Generalized Two Sum example 1
        int[] nums2 = {1, 3, 1, 2, 2, 3};
        int target2 = 4;

        System.out.println("Generalized Two Sum Example 1:");
        System.out.println("Input: nums = [1, 3, 1, 2, 2, 3], target = 4");
        List<List<Integer>> result2 = solution.twoSumTarget(nums2, target2);
        System.out.println("Output: " + result2);
        System.out.println();

        // Generalized Two Sum example 2
        int[] nums3 = {1, 1, 1, 2, 2, 3, 3};
        int target3 = 4;

        System.out.println("Generalized Two Sum Example 2:");
        System.out.println("Input: nums = [1, 1, 1, 2, 2, 3, 3], target = 4");
        List<List<Integer>> result3 = solution.twoSumTarget(nums3, target3);
        System.out.println("Output: " + result3);
        System.out.println();

        // Generalized Two Sum example 3
        int[] nums4 = {-2, -1, 0, 1, 2, 3, 4, 5};
        int target4 = 3;

        System.out.println("Generalized Two Sum Example 3:");
        System.out.println("Input: nums = [-2, -1, 0, 1, 2, 3, 4, 5], target = 3");
        List<List<Integer>> result4 = solution.twoSumTarget(nums4, target4);
        System.out.println("Output: " + result4);
    }

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
