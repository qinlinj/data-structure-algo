package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._192_combinations_unique_elements_no_reuse;

import java.util.*;

/**
 * SUMMARY OF COMBINATION GENERATION USING BACKTRACKING
 * <p>
 * This class demonstrates how to generate all k-sized combinations from numbers 1 to n
 * using the backtracking algorithm technique.
 * <p>
 * Key Concepts:
 * 1. Combination Problem (LeetCode 77): Given two integers n and k, return all possible
 * combinations of k numbers chosen from the range [1, n].
 * <p>
 * 2. Relationship to Subset Problem:
 * - A k-sized combination is essentially a k-sized subset
 * - While subset generation collects nodes at all levels of the recursion tree,
 * combination generation only collects nodes at a specific level (k)
 * - The core algorithm structure remains the same
 * <p>
 * 3. Backtracking Approach:
 * - Uses a recursive tree traversal strategy
 * - Each complete path to level k represents a valid combination
 * - The "start" parameter prevents duplicate combinations by maintaining ordering
 * - Only solutions with exactly k elements are collected
 * <p>
 * 4. Implementation Strategy:
 * - Use a "track" list to record the current path during recursion
 * - Use a "start" parameter to control which elements can be chosen next
 * - Add to result only when we reach exactly k elements (modified base case)
 * - Follow the standard backtracking pattern: choose → explore → unchoose
 * <p>
 * Time Complexity: O(k * C(n,k)) where C(n,k) is the binomial coefficient
 * Space Complexity: O(k) for recursion stack and tracking current combination
 */
public class CombinationGenerator {
    /**
     * Example usage of the combination generation algorithm.
     */
    public static void main(String[] args) {
        CombinationGenerator generator = new CombinationGenerator();
        int n = 3;
        int k = 2;

        List<List<Integer>> allCombinations = generator.combine(n, k);

        System.out.println("All " + k + "-sized combinations from range [1," + n + "]:");
        for (List<Integer> combination : allCombinations) {
            System.out.println(combination);
        }

        // Expected output for n=3, k=2:
        // [1, 2]
        // [1, 3]
        // [2, 3]
    }

    /**
     * Generates all possible k-sized combinations of numbers from 1 to n.
     *
     * @param n The upper bound of the range [1,n]
     * @param k The size of each combination
     * @return A list containing all possible k-sized combinations
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        // List to track the current combination being built
        LinkedList<Integer> track = new LinkedList<>();

        // Start backtracking from 1 (the first number in range)
        backtrack(1, n, k, track, result);
        return result;
    }

    /**
     * Backtracking function to explore the tree of all possible combinations.
     *
     * @param start  The starting number to consider for this recursive call
     * @param n      The upper bound of the range [1,n]
     * @param k      The required size of each combination
     * @param track  The current combination being built
     * @param result The list to store all found combinations
     */
    private void backtrack(int start, int n, int k, LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: if we've collected exactly k elements
        if (track.size() == k) {
            // We've reached level k, add the current combination to result
            result.add(new LinkedList<>(track));
            return;
        }

        // Standard backtracking framework
        for (int i = start; i <= n; i++) {
            // Make a choice: add current number to the combination
            track.addLast(i);

            // Explore further: recursive call with increased start index
            // This ensures we only consider numbers after the current one
            // which prevents duplicates and maintains order
            backtrack(i + 1, n, k, track, result);

            // Undo the choice: remove the last added number
            track.removeLast();
        }
    }

    /**
     * Optional optimization: We can prune branches that won't lead to valid solutions.
     * If the remaining numbers aren't enough to form a k-sized combination, we can stop.
     */
    private void backtrackWithPruning(int start, int n, int k, LinkedList<Integer> track, List<List<Integer>> result) {
        // If we've collected k elements, we have a valid combination
        if (track.size() == k) {
            result.add(new LinkedList<>(track));
            return;
        }

        // Pruning: If remaining numbers are not enough to form a combination of size k
        // (n-i+1) represents the remaining available numbers
        // (k-track.size()) represents how many more numbers we need
        for (int i = start; i <= n - (k - track.size()) + 1; i++) {
            track.addLast(i);
            backtrackWithPruning(i + 1, n, k, track, result);
            track.removeLast();
        }
    }
}
