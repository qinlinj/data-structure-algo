package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset;

import java.util.*;

/**
 * COMBINATIONS (WITHOUT DUPLICATES, NO REUSE)
 * <p>
 * This class implements the solution for generating all combinations of k elements from the range [1, n]
 * where each element can be used at most once.
 * <p>
 * Key points:
 * - Combination problems are equivalent to subset problems with a size constraint
 * - A combination of size k is simply a subset of size k
 * - We modify the backtracking approach to only collect nodes at the specified depth (k)
 * - Time complexity: O(C(n,k)) where C(n,k) is the binomial coefficient
 * - Space complexity: O(k) for the recursion stack and path
 * <p>
 * LeetCode Problem: 77. Combinations
 * Problem description: Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 */

public class _721_b_CombinationsWithoutDuplicates {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    private LinkedList<Integer> track = new LinkedList<>();

    // Example usage
    public static void main(String[] args) {
        _721_b_CombinationsWithoutDuplicates solution = new _721_b_CombinationsWithoutDuplicates();
        int n = 4;
        int k = 2;
        List<List<Integer>> result = solution.combine(n, k);

        System.out.println("All combinations of size " + k + " from range [1, " + n + "]:");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    /**
     * Main function to generate all combinations of k elements from range [1, n]
     *
     * @param n The upper bound of the range
     * @param k The size of each combination
     * @return All possible combinations
     */
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return res;
    }

    /**
     * Core backtracking function to explore the combination tree
     *
     * @param start Starting number to avoid duplicates
     * @param n     The upper bound
     * @param k     The desired size of combinations
     */
    private void backtrack(int start, int n, int k) {
        // Base case: reached the kth level, collect the combination
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }

        // Standard backtracking framework
        for (int i = start; i <= n; i++) {
            // Make a choice
            track.addLast(i);
            // Explore next level
            backtrack(i + 1, n, k);
            // Undo the choice
            track.removeLast();
        }
    }
}