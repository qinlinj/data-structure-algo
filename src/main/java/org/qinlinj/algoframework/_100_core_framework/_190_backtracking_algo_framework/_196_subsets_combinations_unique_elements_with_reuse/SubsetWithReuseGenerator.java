package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._196_subsets_combinations_unique_elements_with_reuse;

import java.util.*;

/**
 * SUMMARY OF COMBINATION SUM WITH REUSABLE ELEMENTS
 * <p>
 * This class demonstrates how to find all combinations that sum to a target value
 * where each element in the input array can be used multiple times.
 * <p>
 * Key Concepts:
 * 1. Combination Sum Problem (LeetCode 39): Given an array of distinct integers and a target sum,
 * find all unique combinations where the chosen numbers sum to the target.
 * Each number can be used multiple times.
 * <p>
 * 2. Element Reuse Strategy:
 * - In standard combination/subset problems, we pass (i+1) to the next recursive call to prevent reuse
 * - For element reuse, we pass (i) instead of (i+1) to allow the same element to be chosen again
 * - This creates an additional branch in the decision tree where the same element can be selected repeatedly
 * <p>
 * 3. Backtracking Approach:
 * - Uses a recursive tree traversal strategy with elements potentially reused
 * - Each valid path (sum equals target) represents a valid combination
 * - Additional base cases required to prevent infinite recursion
 * - Early termination when sum exceeds target
 * <p>
 * 4. Implementation Strategy:
 * - Use a "track" list to record the current combination being built
 * - Maintain a running sum to efficiently check against the target
 * - Pass the current index (i) instead of (i+1) for recursive calls to allow element reuse
 * - Add combinations to result when sum equals target
 * - Use early termination when sum exceeds target
 * <p>
 * Time Complexity: Exponential, as each element can be selected multiple times
 * Space Complexity: O(target/min(candidates)) for the maximum recursion depth
 */
public class SubsetWithReuseGenerator {
    /**
     * Example usage of the combination sum algorithm.
     */
    public static void main(String[] args) {
        SubsetWithReuseGenerator generator = new SubsetWithReuseGenerator();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> combinations = generator.combinationSum(candidates, target);

        System.out.println("All combinations from " + java.util.Arrays.toString(candidates) +
                " that sum to " + target + ":");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }

        // Expected output:
        // [2, 2, 3]
        // [7]

        // Example with smaller numbers to demonstrate multiple reuse
        int[] candidates2 = {1, 2, 3};
        int target2 = 5;

        List<List<Integer>> combinations2 = generator.combinationSum(candidates2, target2);

        System.out.println("\nAll combinations from " + java.util.Arrays.toString(candidates2) +
                " that sum to " + target2 + ":");
        for (List<Integer> combination : combinations2) {
            System.out.println(combination);
        }

        // Expected output:
        // [1, 1, 1, 1, 1]
        // [1, 1, 1, 2]
        // [1, 1, 3]
        // [1, 2, 2]
        // [2, 3]
    }

    /**
     * Finds all combinations of numbers in candidates that sum to target.
     * Each number in candidates may be used multiple times.
     *
     * @param candidates Array of distinct integers
     * @param target     The target sum to achieve
     * @return All unique combinations that sum to target
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        // List to track the current combination being built
        LinkedList<Integer> track = new LinkedList<>();
        // Track the current sum for efficiency
        int trackSum = 0;

        backtrack(candidates, 0, target, trackSum, track, result);
        return result;
    }

    /**
     * Backtracking function to explore all possible combinations.
     *
     * @param nums       Array of candidate numbers
     * @param start      The starting index to consider for this recursive call
     * @param target     The target sum to achieve
     * @param currentSum The current sum of elements in track
     * @param track      The current combination being built
     * @param result     The list to store all valid combinations
     */
    private void backtrack(int[] nums, int start, int target, int currentSum,
                           LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case 1: Found a valid combination
        if (currentSum == target) {
            result.add(new LinkedList<>(track));
            return;
        }

        // Base case 2: Sum exceeds target - no need to explore further
        if (currentSum > target) {
            return;
        }

        // Standard backtracking framework
        for (int i = start; i < nums.length; i++) {
            // Make a choice
            track.addLast(nums[i]);
            currentSum += nums[i];

            // Explore further - KEY DIFFERENCE: pass 'i' instead of 'i+1' to allow reuse
            // This allows the same element to be used multiple times
            backtrack(nums, i, target, currentSum, track, result);

            // Undo the choice
            track.removeLast();
            currentSum -= nums[i];
        }
    }

    /**
     * Optimized version with sorting and additional pruning.
     */
    public List<List<Integer>> combinationSumOptimized(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();

        // Sort array to enable early termination
        java.util.Arrays.sort(candidates);

        backtrackOptimized(candidates, 0, target, 0, track, result);
        return result;
    }

    private void backtrackOptimized(int[] nums, int start, int target, int currentSum,
                                    LinkedList<Integer> track, List<List<Integer>> result) {
        if (currentSum == target) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // Early termination - if current number already makes sum exceed target
            // Since array is sorted, all subsequent numbers will also exceed
            if (currentSum + nums[i] > target) {
                break;
            }

            track.addLast(nums[i]);
            backtrackOptimized(nums, i, target, currentSum + nums[i], track, result);
            track.removeLast();
        }
    }
}
