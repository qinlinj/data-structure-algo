package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._722_ball_box_enumeration_model; /**
 * PERMUTATIONS FROM BOX PERSPECTIVE
 * <p>
 * This class implements the solution for generating all permutations using the "box perspective"
 * approach of the box-ball model.
 * <p>
 * Key insights:
 * 1. In this approach, we think of the problem as placing n balls into n indexed boxes
 * 2. We iterate through each box (position) and decide which ball (element) to place in it
 * 3. This is the standard approach typically taught for backtracking algorithms
 * 4. We use a "used" array to track which elements have already been placed
 * <p>
 * Implementation details:
 * - A boolean[] used array tracks which elements have been used
 * - We use a LinkedList to track the current permutation path
 * - Time complexity: O(n!) where n is the array length
 * - Space complexity: O(n) for the recursion stack and used array
 */

import java.util.*;

public class _722_b_PermutationsBoxPerspective {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path (boxes filled so far)
    private LinkedList<Integer> track = new LinkedList<>();
    // Track which balls (elements) have been used
    private boolean[] used;

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _722_b_PermutationsBoxPerspective solution = new _722_b_PermutationsBoxPerspective();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = solution.permute(nums);

        System.out.println("Permutations of [1,2,3] using box perspective:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }

        System.out.println("\nExplanation:");
        System.out.println("1. We iterate through positions (boxes) and for each position,");
        System.out.println("   we try each available element (ball)");
        System.out.println("2. The 'used' array keeps track of which elements have been placed");
        System.out.println("3. This approach is intuitive but requires O(n) extra space for the 'used' array");
    }

    /**
     * Main function to generate all permutations
     * @param nums An array of integers
     * @return All possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    /**
     * Core backtracking function using box perspective
     * @param nums Input array
     */
    private void backtrack(int[] nums) {
        // Base case: all boxes filled (reached leaf node)
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        // For the current position (box), try each possible element (ball)
        for (int i = 0; i < nums.length; i++) {
            // Skip elements that have already been used
            if (used[i]) {
                continue;
            }

            // Choose this element for the current position
            used[i] = true;
            track.addLast(nums[i]);

            // Recursively fill the next position
            backtrack(nums);

            // Unchoose (backtrack)
            track.removeLast();
            used[i] = false;
        }
    }
}