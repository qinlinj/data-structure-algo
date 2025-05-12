package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._722_ball_box_enumeration_model; /**
 * OPTIMIZED PERMUTATIONS FROM BOX PERSPECTIVE USING SWAP
 * <p>
 * This class implements an optimized solution for generating all permutations using
 * the "box perspective" approach with swap operations.
 * <p>
 * Key insights:
 * 1. Still uses the box perspective, but optimizes space complexity
 * 2. Instead of a used[] array, we partition the array into:
 * - Elements in positions [0...start-1] have been chosen
 * - Elements in positions [start...n-1] are available to choose
 * 3. We use swap operations to maintain this partition
 * 4. This eliminates the need for extra space to track used elements
 * <p>
 * Implementation details:
 * - Uses array swapping instead of a used array
 * - No extra data structure needed to track the current path (it's in the array)
 * - Time complexity: O(n!) where n is the array length
 * - Space complexity: O(n) for the recursion stack only (better than the standard approach)
 */

import java.util.*;

public class _722_c_PermutationsBoxPerspectiveOptimized {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _722_c_PermutationsBoxPerspectiveOptimized solution = new _722_c_PermutationsBoxPerspectiveOptimized();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = solution.permute(nums);

        System.out.println("Permutations of [1,2,3] using optimized box perspective:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }

        System.out.println("\nExplanation:");
        System.out.println("1. We still iterate through positions, but use array swapping");
        System.out.println("2. The array is partitioned into: used elements [0...start-1]");
        System.out.println("   and available elements [start...n-1]");
        System.out.println("3. No extra 'used' array needed, improving space complexity");
        System.out.println("4. After a recursive call, we swap back to restore the array state");
    }

    /**
     * Main function to generate all permutations using swap optimization
     * @param nums An array of integers
     * @return All possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums, 0);
        return result;
    }

    /**
     * Core backtracking function using box perspective with swap optimization
     * @param nums Input array
     * @param start Current position to fill
     */
    private void backtrack(int[] nums, int start) {
        // Base case: all positions filled
        if (start == nums.length) {
            // Convert the current state of nums to a list and add to result
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }

        // For the current position (start), try each possible element
        for (int i = start; i < nums.length; i++) {
            // Choose: swap the element at i to the current position
            swap(nums, start, i);

            // Recursively fill the next position
            backtrack(nums, start + 1);

            // Unchoose: swap back to restore original order
            swap(nums, start, i);
        }
    }

    /**
     * Helper function to swap elements in an array
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}