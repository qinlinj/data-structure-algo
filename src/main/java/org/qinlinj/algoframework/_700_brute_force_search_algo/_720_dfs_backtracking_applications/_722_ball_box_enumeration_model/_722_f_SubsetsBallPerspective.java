package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._722_ball_box_enumeration_model; /**
 * SUBSETS FROM BALL PERSPECTIVE
 * <p>
 * This class implements the solution for generating all subsets using the "ball perspective"
 * approach of the box-ball model.
 * <p>
 * Key insights:
 * 1. In this approach, we think of each element (ball) deciding whether to be in the subset or not
 * 2. For each element, we make a binary choice: include it or exclude it
 * 3. This creates a binary tree of decisions, with 2^n leaf nodes (all possible subsets)
 * 4. Unlike the box perspective, we don't need the 'start' parameter
 * <p>
 * Implementation details:
 * - We process elements in order, each making a binary decision
 * - We collect subsets at the leaf nodes (when all elements have been processed)
 * - Time complexity: O(2^n) where n is the array length
 * - Space complexity: O(n) for the recursion stack
 */

import java.util.*;

public class _722_f_SubsetsBallPerspective {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> track = new ArrayList<>();

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _722_f_SubsetsBallPerspective solution = new _722_f_SubsetsBallPerspective();
        int[] nums = {1, 2, 3};

        System.out.println("All subsets of [1,2,3] using ball perspective:");
        List<List<Integer>> allSubsets = solution.subsets(nums);
        for (List<Integer> subset : allSubsets) {
            System.out.println(subset);
        }

        System.out.println("\nExplanation:");
        System.out.println("1. We iterate through elements, each making a binary decision");
        System.out.println("2. For each element, we branch into two paths: include or exclude");
        System.out.println("3. This creates a binary decision tree with 2^n leaf nodes");
        System.out.println("4. We collect subsets at the leaf nodes (when all elements have been processed)");
        System.out.println("5. This explains why there are 2^n subsets - each element has 2 choices");

        System.out.println("\nComparison note:");
        System.out.println("- Although mathematically equivalent to the box perspective,");
        System.out.println("  this ball perspective approach creates a different recursion tree");
        System.out.println("- The box perspective collects subsets at each node");
        System.out.println("- The ball perspective typically collects subsets at leaf nodes,");
        System.out.println("  though this can be modified as shown in the alternative implementation");
    }

    /**
     * Main function to generate all subsets using ball perspective
     * @param nums An array of integers
     * @return All possible subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    /**
     * Core backtracking function using ball perspective
     * @param nums Input array
     * @param index Current element to process
     */
    private void backtrack(int[] nums, int index) {
        // Base case: all elements have made their decision
        if (index == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }

        // Decision 1: Include the current element
        track.add(nums[index]);
        backtrack(nums, index + 1);

        // Decision 2: Exclude the current element
        track.remove(track.size() - 1);
        backtrack(nums, index + 1);
    }

    /**
     * Alternative implementation that collects subsets at each node
     */
    public List<List<Integer>> subsetsAlternative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrackAlternative(nums, 0, path, result);
        return result;
    }

    private void backtrackAlternative(int[] nums, int index, List<Integer> path, List<List<Integer>> result) {
        // Collect subset at current node
        result.add(new ArrayList<>(path));

        // Try including each remaining element
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            backtrackAlternative(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}