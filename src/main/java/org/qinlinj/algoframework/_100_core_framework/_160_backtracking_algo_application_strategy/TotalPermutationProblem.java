package org.qinlinj.algoframework._100_core_framework._160_backtracking_algo_application_strategy;

// @formatter:off
import java.util.*; /**
 * Backtracking Algorithm Framework and Explanation
 * -----------------------------------------------
 *
 * Overview:
 * Backtracking is essentially a depth-first search (DFS) algorithm applied to a decision tree.
 * It systematically explores all possible solutions by building candidates incrementally and
 * abandoning a candidate (backtracking) when it determines the candidate cannot lead to a valid solution.
 *
 * Core Concepts:
 * 1. Path: Choices already made in the current exploration.
 * 2. Choice List: Available choices at the current decision point.
 * 3. Termination Condition: Criteria to identify when a complete solution is found.
 *
 * The Algorithm Framework:
 * - At each decision point, try each available choice
 * - Make a choice and mark it as used
 * - Recursively explore with this choice added to the path
 * - Undo the choice (backtrack) and try the next option
 *
 * Visualization of a Decision Tree:
 * - Each node represents a state in the solution construction
 * - Each branch represents a choice
 * - Leaf nodes represent either complete solutions or dead-ends
 *
 * Time Complexity:
 * - Generally high (often exponential or factorial)
 * - Unlike dynamic programming, backtracking doesn't benefit from overlapping subproblems
 * - For permutation problems: O(n!)
 *
 * Common Pattern in Code:
 * ```
 * void backtrack(path, choices) {
 *     if (terminationCondition) {
 *         saveResult(path);
 *         return;
 *     }
 *
 *     for (choice in choices) {
 *         // Make choice
 *         add choice to path;
 *         mark choice as used;
 *
 *         // Recursive exploration
 *         backtrack(path, choices);
 *
 *         // Undo choice (backtrack)
 *         remove choice from path;
 *         mark choice as unused;
 *     }
 * }
 * ```
 *
 * This class demonstrates backtracking algorithm using the classic permutation problem as an example.
 */
public class TotalPermutationProblem {
    // ========== PERMUTATION EXAMPLE ==========
    /**
     * Generate all permutations of the given array of integers
     * This demonstrates the core backtracking framework
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Track the current path (partial permutation being built)
        List<Integer> path = new ArrayList<>();

        // Keep track of which elements have been used
        boolean[] used = new boolean[nums.length];

        backtrackPermute(nums, path, used, result);
        return result;
    }

    /**
     * Backtracking function for permutation problem
     *
     * Path: Elements currently in 'path'
     * Choice List: Unused elements in 'nums' (tracked by 'used' array)
     * Termination Condition: When path.size() == nums.length
     */
    private static void backtrackPermute(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> result) {
        // Termination condition: path contains all elements
        if (path.size() == nums.length) {
            // Save a copy of the current permutation
            result.add(new ArrayList<>(path));
            return;
        }

        // Try each possible next choice
        for (int i = 0; i < nums.length; i++) {
            // Skip if this element is already used
            if (used[i]) {
                continue;
            }

            // Make choice
            path.add(nums[i]);
            used[i] = true;

            // Explore next level of decision tree
            backtrackPermute(nums, path, used, result);

            // Undo choice (backtrack)
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
