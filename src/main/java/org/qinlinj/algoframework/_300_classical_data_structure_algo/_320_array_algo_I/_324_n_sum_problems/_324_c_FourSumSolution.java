package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._324_n_sum_problems;

import java.util.*;

/**
 * Four Sum Solution (LeetCode 18)
 * =============================
 * <p>
 * This class implements a solution to the Four Sum problem by building on the Three Sum solution.
 * <p>
 * Problem:
 * Given an array of integers and a target value, find all unique quadruplets in the array
 * that sum to the target value.
 * <p>
 * Approach:
 * 1. Sort the array
 * 2. For each unique first element, call the Three Sum function on the remaining elements
 * with an adjusted target
 * 3. Each result from Three Sum combined with the current first element forms a quadruplet
 * 4. Skip duplicates to avoid duplicate quadruplets
 * <p>
 * Time Complexity: O(n³) where n is the length of the array
 * Space Complexity: O(1) excluding the output space
 */
public class _324_c_FourSumSolution {

    /**
     * Demonstration of the four sum algorithms.
     */
    public static void main(String[] args) {
        _324_c_FourSumSolution solution = new _324_c_FourSumSolution();

        // Example 1
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println("Example 1:");
        System.out.println("Input: nums = [1, 0, -1, 0, -2, 2], target = 0");
        List<List<Integer>> result1 = solution.fourSum(nums1, target1);
        System.out.println("Output: " + result1);
        System.out.println();

        // Example 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println("Example 2:");
        System.out.println("Input: nums = [2, 2, 2, 2, 2], target = 8");
        List<List<Integer>> result2 = solution.fourSum(nums2, target2);
        System.out.println("Output: " + result2);
        System.out.println();

        // Example 3 - Large numbers
        int[] nums3 = {1000000000, 1000000000, 1000000000, 1000000000};
        int target3 = -294967296;
        System.out.println("Example 3 (Large numbers):");
        System.out.println("Input: nums = [1000000000, 1000000000, 1000000000, 1000000000], target = -294967296");
        List<List<Integer>> result3 = solution.fourSum(nums3, target3);
        System.out.println("Output: " + result3);
        System.out.println();

        // Example 4 - Alternative implementation
        System.out.println("Example 4 (Alternative Implementation):");
        System.out.println("Input: nums = [1, 0, -1, 0, -2, 2], target = 0");
        List<List<Integer>> result4 = solution.fourSumDirect(nums1, target1);
        System.out.println("Output: " + result4);
    }

    /**
     * Finds all unique quadruplets in the array that sum to the target.
     *
     * @param nums   The input array
     * @param target The target sum
     * @return List of all unique quadruplets that sum to target
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // Sort the array first
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // Iterate through each element as the potential first element of quadruplet
        for (int i = 0; i < n; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Calculate target for the remaining three elements
            // Using long to prevent integer overflow
            long newTarget = (long) target - nums[i];

            // Find all triplets that sum to newTarget
            List<List<Integer>> triplets = threeSumTarget(nums, i + 1, newTarget);

            // Add the current element to each triplet to form quadruplets
            for (List<Integer> triplet : triplets) {
                triplet.add(nums[i]);
                result.add(triplet);
            }
        }

        return result;
    }

    /**
     * Finds all unique triplets in nums[start...] that sum to target.
     *
     * @param nums   The sorted input array
     * @param start  The starting index to consider
     * @param target The target sum
     * @return List of all unique triplets that sum to target
     */
    private List<List<Integer>> threeSumTarget(int[] nums, int start, long target) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // Iterate through each element as the potential first element of triplet
        for (int i = start; i < n; i++) {
            // Skip duplicates for the first element
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Calculate target for the remaining two elements
            long newTarget = target - nums[i];

            // Find all pairs that sum to newTarget
            List<List<Integer>> pairs = twoSumTarget(nums, i + 1, newTarget);

            // Add the current element to each pair to form triplets
            for (List<Integer> pair : pairs) {
                pair.add(nums[i]);
                result.add(pair);
            }
        }

        return result;
    }

    /**
     * Finds all unique pairs in nums[start...] that sum to target.
     *
     * @param nums   The sorted input array
     * @param start  The starting index to consider
     * @param target The target sum
     * @return List of all unique pairs that sum to target
     */
    private List<List<Integer>> twoSumTarget(int[] nums, int start, long target) {
        int lo = start, hi = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();

        while (lo < hi) {
            // Using long to prevent integer overflow
            long sum = (long) nums[lo] + nums[hi];
            int left = nums[lo], right = nums[hi];

            if (sum < target) {
                // Skip duplicates
                while (lo < hi && nums[lo] == left) lo++;
            } else if (sum > target) {
                // Skip duplicates
                while (lo < hi && nums[hi] == right) hi--;
            } else {
                // Found a pair
                List<Integer> pair = new ArrayList<>();
                pair.add(left);
                pair.add(right);
                result.add(pair);

                // Skip duplicates
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            }
        }

        return result;
    }

    /**
     * Alternative implementation that constructs quadruplets directly.
     * This can be useful for understanding the basic algorithm without the recursion.
     */
    public List<List<Integer>> fourSumDirect(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // Check if array is too small
        if (n < 4) return result;

        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Early termination if smallest possible sum exceeds target
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;

            // Early termination if largest possible sum is too small
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Early termination checks
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
