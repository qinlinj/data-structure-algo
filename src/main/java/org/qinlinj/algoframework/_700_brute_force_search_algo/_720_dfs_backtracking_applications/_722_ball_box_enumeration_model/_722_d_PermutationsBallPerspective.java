package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._722_ball_box_enumeration_model; /**
 * PERMUTATIONS FROM BALL PERSPECTIVE
 * <p>
 * This class implements the solution for generating all permutations using the "ball perspective"
 * approach of the box-ball model.
 * <p>
 * Key insights:
 * 1. In this approach, we think of the problem as each ball (element) choosing its box (position)
 * 2. We iterate through each ball and decide which available position to place it in
 * 3. This is a less common but equally valid approach to permutation generation
 * 4. We need to track both which positions are filled and how many elements have been placed
 * <p>
 * Implementation details:
 * - A boolean[] used array tracks which positions have been filled
 * - A count variable tracks how many elements have been placed
 * - Time complexity: O(n!) where n is the array length
 * - Space complexity: O(n) for the recursion stack and used array
 */

import java.util.*;

public class _722_d_PermutationsBallPerspective {

    private List<List<Integer>> res;
    // Track which positions (boxes) are filled
    private boolean[] used;
    // Track how many elements have been placed
    private int count;

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _722_d_PermutationsBallPerspective solution = new _722_d_PermutationsBallPerspective();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = solution.permute(nums);

        System.out.println("Permutations of [1,2,3] using ball perspective:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }

        System.out.println("\nExplanation:");
        System.out.println("1. We iterate through elements and for each element,");
        System.out.println("   we try placing it in each available position");
        System.out.println("2. The 'used' array now tracks which positions are filled");
        System.out.println("3. We need a 'count' variable to track how many elements we've placed");
        System.out.println("4. This approach is less intuitive but demonstrates the ball perspective");

        // Optimized version note
        System.out.println("\nOptimization Note:");
        System.out.println("The implementation can be further optimized. For example:");
        System.out.println("- We can use swapIndex directly instead of finding originalIndex each time");
        System.out.println("- We can return when count == nums.length - 1 as the last element's position is determined");
    }

    /**
     * Main function to generate all permutations using ball perspective
     * @param nums An array of integers
     * @return All possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        used = new boolean[nums.length];
        count = 0;

        backtrack(nums);
        return res;
    }

    /**
     * Core backtracking function using ball perspective
     * @param nums Input array (modified during backtracking)
     */
    private void backtrack(int[] nums) {
        // Base case: all elements have been placed
        if (count == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) {
                temp.add(num);
            }
            res.add(temp);
            return;
        }

        // Find the first available element to place
        int originalIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                originalIndex = i;
                break;
            }
        }

        // For the current element, try each available position
        for (int swapIndex = originalIndex; swapIndex < nums.length; swapIndex++) {
            if (used[swapIndex]) {
                continue;
            }

            // Choose: place element at originalIndex in position swapIndex
            swap(nums, originalIndex, swapIndex);
            used[swapIndex] = true;
            count++;

            // Recursively place the next element
            backtrack(nums);

            // Unchoose: restore the original state
            count--;
            used[swapIndex] = false;
            swap(nums, originalIndex, swapIndex);
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