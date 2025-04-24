package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._324_n_sum_problems;

import java.util.*;

/**
 * Three Sum Solution (LeetCode 15)
 * ==============================
 * <p>
 * This class implements solutions to the Three Sum problem and its generalized variant.
 * <p>
 * Problem:
 * Given an array of integers, find all unique triplets in the array that give a sum of zero
 * (or a specified target value in the generalized version).
 * <p>
 * Approach:
 * 1. Sort the array (to handle duplicates efficiently)
 * 2. Iterate through each element as a potential first element of the triplet
 * 3. For each element, use the two-pointer technique to find pairs that complete the triplet
 * 4. Skip duplicate values to avoid duplicate triplets
 * <p>
 * Time Complexity: O(n²) where n is the length of the array
 * Space Complexity: O(1) excluding the output space
 */
public class ThreeSumSolution {

    /**
     * Demonstration of the three sum algorithms.
     */
    public static void main(String[] args) {
        ThreeSumSolution solution = new ThreeSumSolution();

        // Example 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Example 1:");
        System.out.println("Input: nums = [-1, 0, 1, 2, -1, -4]");
        List<List<Integer>> result1 = solution.threeSum(nums1);
        System.out.println("Output: " + result1);
        System.out.println();

        // Example 2
        int[] nums2 = {0, 1, 1};
        System.out.println("Example 2:");
        System.out.println("Input: nums = [0, 1, 1]");
        List<List<Integer>> result2 = solution.threeSum(nums2);
        System.out.println("Output: " + result2);
        System.out.println();

        // Example 3
        int[] nums3 = {0, 0, 0};
        System.out.println("Example 3:");
        System.out.println("Input: nums = [0, 0, 0]");
        List<List<Integer>> result3 = solution.threeSum(nums3);
        System.out.println("Output: " + result3);
        System.out.println();

        // Example 4 - Using generalized three sum
        int[] nums4 = {1, 2, 3, 4, 5, 6};
        int target4 = 10;
        System.out.println("Example 4 (Generalized Three Sum):");
        System.out.println("Input: nums = [1, 2, 3, 4, 5, 6], target = 10");
        List<List<Integer>> result4 = solution.threeSumTarget(nums4, target4);
        System.out.println("Output: " + result4);
        System.out.println();

        // Example 5 - Alternative implementation
        int[] nums5 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Example 5 (Alternative Implementation):");
        System.out.println("Input: nums = [-1, 0, 1, 2, -1, -4]");
        List<List<Integer>> result5 = solution.threeSumDirect(nums5);
        System.out.println("Output: " + result5);
    }

    /**
     * Finds all unique triplets in the array that sum to zero.
     *
     * @param nums The input array
     * @return List of all unique triplets that sum to zero
     */
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSumTarget(nums, 0);
    }

    /**
     * Generalized solution that finds all unique triplets in the array
     * that sum to the given target.
     *
     * @param nums   The input array
     * @param target The target sum
     * @return List of all unique triplets that sum to target
     */
    public List<List<Integer>> threeSumTarget(int[] nums, int target) {
        // Sort the array first
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // Iterate through each element as the potential first element of triplet
        for (int i = 0; i < n; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Calculate target for the remaining two elements
            int newTarget = target - nums[i];

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
    private List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
        int lo = start, hi = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();

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
     * Alternative implementation that constructs triplets directly.
     * This can be more intuitive for some readers.
     */
    public List<List<Integer>> threeSumDirect(int[] nums) {
        // Sort the array first
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // Iterate through each element as the potential first element of triplet
        for (int i = 0; i < n; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Use two pointers for the remaining elements
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    // Found a triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates
                    int currentLeft = nums[left];
                    int currentRight = nums[right];
                    while (left < right && nums[left] == currentLeft) left++;
                    while (left < right && nums[right] == currentRight) right--;
                }
            }
        }

        return result;
    }
}
