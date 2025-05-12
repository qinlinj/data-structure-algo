package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._722_ball_box_enumeration_model; /**
 * SUBSETS FROM BOX PERSPECTIVE
 * <p>
 * This class implements the solution for generating all subsets using the "box perspective"
 * approach of the box-ball model.
 * <p>
 * Key insights:
 * 1. In the subset problem, we can view it as having n positions (boxes),
 * where each position chooses whether to include a specific element
 * 2. The boxes are associated with specific elements, and each box decides
 * whether to include its associated element
 * 3. We use the 'start' parameter to avoid duplicates by enforcing that
 * we only consider elements after the previously considered one
 * <p>
 * Implementation details:
 * - We collect a subset at each node of the backtracking tree
 * - The 'start' parameter ensures we only look at elements not yet considered
 * - Time complexity: O(2^n) where n is the array length
 * - Space complexity: O(n) for the recursion stack
 */

import java.util.*;

public class _722_e_SubsetsBoxPerspective {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the current subset being built
    private LinkedList<Integer> track = new LinkedList<>();

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _722_e_SubsetsBoxPerspective solution = new _722_e_SubsetsBoxPerspective();
        int[] nums = {1, 2, 3};
        List<List<Integer>> allSubsets = solution.subsets(nums);

        System.out.println("All subsets of [1,2,3] using box perspective:");
        for (List<Integer> subset : allSubsets) {
            System.out.println(subset);
        }

        System.out.println("\nExplanation:");
        System.out.println("1. We iterate through positions, each associated with a specific element");
        System.out.println("2. For each position, we decide whether to include its element");
        System.out.println("3. The 'start' parameter ensures we only consider elements not yet considered");
        System.out.println("4. Each node in the recursion tree represents a valid subset");
        System.out.println("5. This is the standard approach to generating subsets");
    }

    /**
     * Main function to generate all subsets
     * @param nums An array of integers
     * @return All possible subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    /**
     * Core backtracking function using box perspective
     * @param nums Input array
     * @param start Starting index to consider
     */
    private void backtrack(int[] nums, int start) {
        // Each node in the tree represents a valid subset
        res.add(new LinkedList<>(track));

        // For each position starting from 'start', decide whether to include its element
        for (int i = start; i < nums.length; i++) {
            // Choose to include this element
            track.addLast(nums[i]);

            // Recursively build subsets with elements after the current one
            backtrack(nums, i + 1);

            // Unchoose (backtrack)
            track.removeLast();
        }
    }
}