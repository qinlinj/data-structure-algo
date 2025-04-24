package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._324_n_sum_problems;

import java.util.*;

/**
 * Generalized N-Sum Solution
 * =========================
 * <p>
 * This class implements a recursive solution to the generalized n-Sum problem.
 * Given an array of integers and a target value, it can find all unique combinations
 * of n elements that sum to the target value.
 * <p>
 * The implementation follows a recursive approach:
 * - When n = 2, it uses the two-pointer technique (base case)
 * - When n > 2, it reduces the problem to (n-1)-Sum by fixing one element
 * <p>
 * This solution works for any value of n, from 2-Sum all the way to 100-Sum,
 * though the time complexity grows exponentially with n.
 * <p>
 * Time Complexity: O(N^(n-1)) where N is the length of the array and n is the number of elements
 * Space Complexity: O(n) for the recursion stack (excluding output space)
 */
public class _324_d_NSumSolution {

    /**
     * Demonstration of the n-Sum algorithms with various examples.
     */
    public static void main(String[] args) {
        _324_d_NSumSolution solution = new _324_d_NSumSolution();

        // 2-Sum example
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("2-Sum Example:");
        System.out.println("Input: nums = [2, 7, 11, 15], target = 9");
        List<List<Integer>> result1 = solution.twoSum(nums1, target1);
        System.out.println("Output: " + result1);
        System.out.println();

        // 3-Sum example
        int[] nums2 = {-1, 0, 1, 2, -1, -4};
        System.out.println("3-Sum Example:");
        System.out.println("Input: nums = [-1, 0, 1, 2, -1, -4]");
        List<List<Integer>> result2 = solution.threeSum(nums2);
        System.out.println("Output: " + result2);
        System.out.println();

        // 4-Sum example
        int[] nums3 = {1, 0, -1, 0, -2, 2};
        int target3 = 0;
        System.out.println("4-Sum Example:");
        System.out.println("Input: nums = [1, 0, -1, 0, -2, 2], target = 0");
        List<List<Integer>> result3 = solution.fourSum(nums3, target3);
        System.out.println("Output: " + result3);
        System.out.println();

        // 5-Sum example
        int[] nums4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target4 = 25;
        System.out.println("5-Sum Example:");
        System.out.println("Input: nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], target = 25");
        List<List<Integer>> result4 = solution.nSumTarget(nums4, 5, 0, target4);
        System.out.println("Output: " + result4);
        System.out.println();

        // 6-Sum example with optimized version
        int[] nums5 = {1, 1, 1, 2, 2, 2, 3, 3, 4, 5};
        int target5 = 12;
        System.out.println("6-Sum Example (Optimized):");
        System.out.println("Input: nums = [1, 1, 1, 2, 2, 2, 3, 3, 4, 5], target = 12");
        List<List<Integer>> result5 = solution.nSumTargetOptimized(nums5, 6, 0, target5);
        System.out.println("Output: " + result5);

        // Note: A 100-Sum example would be impractical to show due to the exponential time complexity,
        // but the function is capable of handling it for suitably small arrays
    }

    /**
     * Wrapper function for Two Sum problem.
     *
     * @param nums   The input array
     * @param target The target sum
     * @return List of all unique pairs that sum to target
     */
    public List<List<Integer>> twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSumTarget(nums, 2, 0, target);
    }

    /**
     * Wrapper function for Three Sum problem.
     *
     * @param nums The input array
     * @return List of all unique triplets that sum to zero
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSumTarget(nums, 3, 0, 0);
    }

    /**
     * Wrapper function for Four Sum problem.
     *
     * @param nums   The input array
     * @param target The target sum
     * @return List of all unique quadruplets that sum to target
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSumTarget(nums, 4, 0, target);
    }

    /**
     * Generalized n-Sum function that finds all unique combinations of n elements
     * in the array nums[start...] that sum to target.
     *
     * @param nums   The sorted input array
     * @param n      The number of elements to include in each combination
     * @param start  The starting index to consider
     * @param target The target sum
     * @return List of all unique combinations that sum to target
     */
    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
        int size = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // Base cases
        if (n < 2 || size < n) {
            return result;
        }

        // Base case: 2-Sum problem
        if (n == 2) {
            // Use two-pointer technique
            int lo = start, hi = size - 1;

            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];

                if (sum < target) {
                    // Skip duplicates
                    while (lo < hi && nums[lo] == left) lo++;
                } else if (sum > target) {
                    // Skip duplicates
                    while (lo < hi && nums[hi] == right) hi--;
                } else {
                    // Found a valid pair
                    List<Integer> pair = new ArrayList<>();
                    pair.add(left);
                    pair.add(right);
                    result.add(pair);

                    // Skip duplicates
                    while (lo < hi && nums[lo] == left) lo++;
                    while (lo < hi && nums[hi] == right) hi--;
                }
            }
        } else {
            // Recursive case: reduce to (n-1)-Sum
            for (int i = start; i < size; i++) {
                // Skip duplicates
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }

                // Recursively solve (n-1)-Sum with updated target
                List<List<Integer>> subResults = nSumTarget(
                        nums, n - 1, i + 1, target - nums[i]);

                // Combine current element with all (n-1)-Sum results
                for (List<Integer> subResult : subResults) {
                    List<Integer> combination = new ArrayList<>(subResult);
                    combination.add(nums[i]);
                    result.add(combination);
                }
            }
        }

        return result;
    }

    /**
     * Optimized version with early termination checks.
     * This can significantly improve performance for large arrays.
     */
    public List<List<Integer>> nSumTargetOptimized(int[] nums, int n, int start, long target) {
        int size = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // Base cases
        if (n < 2 || size < n) {
            return result;
        }

        // Base case: 2-Sum problem
        if (n == 2) {
            // Two-pointer technique (same as before)
            int lo = start, hi = size - 1;

            while (lo < hi) {
                long sum = (long) nums[lo] + nums[hi];  // Use long to prevent overflow
                int left = nums[lo], right = nums[hi];

                if (sum < target) {
                    while (lo < hi && nums[lo] == left) lo++;
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) hi--;
                } else {
                    result.add(Arrays.asList(left, right));
                    while (lo < hi && nums[lo] == left) lo++;
                    while (lo < hi && nums[hi] == right) hi--;
                }
            }
        } else {
            // Recursive case with optimizations
            for (int i = start; i <= size - n; i++) {
                // Skip duplicates
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }

                // Early termination: smallest possible sum > target
                if ((long) nums[i] * n > target) {
                    break;
                }

                // Early termination: largest possible sum < target
                if ((long) nums[i] + (long) (size - 1) * (n - 1) < target) {
                    continue;
                }

                // Recursively solve (n-1)-Sum
                List<List<Integer>> subResults = nSumTargetOptimized(
                        nums, n - 1, i + 1, target - nums[i]);

                for (List<Integer> subResult : subResults) {
                    List<Integer> combination = new ArrayList<>(subResult);
                    combination.add(nums[i]);
                    result.add(combination);
                }
            }
        }

        return result;
    }
}