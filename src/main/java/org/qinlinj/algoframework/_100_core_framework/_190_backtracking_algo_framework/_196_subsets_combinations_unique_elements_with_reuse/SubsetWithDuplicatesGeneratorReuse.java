package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._196_subsets_combinations_unique_elements_with_reuse;

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
public class SubsetWithDuplicatesGeneratorReuse {
}
