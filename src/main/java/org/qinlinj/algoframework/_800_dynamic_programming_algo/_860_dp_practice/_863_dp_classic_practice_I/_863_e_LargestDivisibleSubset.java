package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

/**
 * LeetCode 368. Largest Divisible Subset - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair (Si, Sj) satisfies: Si % Sj == 0 or Sj % Si == 0.
 * <p>
 * KEY CONCEPTS:
 * 1. Transform into Longest Increasing Subsequence (LIS) problem
 * 2. Key insight: After sorting, if a > b > c and a%b==0, b%c==0, then a%c==0
 * 3. Sorting ensures we only need to check divisibility with the largest element
 * 4. State Definition: dp[i] = largest divisible subset ending at nums[i]
 * 5. State Transition: For each j < i, if nums[i] % nums[j] == 0,
 * then dp[i] can extend dp[j]
 * <p>
 * TIME COMPLEXITY: O(n²)
 * SPACE COMPLEXITY: O(n²) for storing subsets, O(n) for length tracking
 * <p>
 * EXAMPLE:
 * Input: [1,2,4,8]
 * Output: [1,2,4,8] (each element divides the next)
 */


public class _863_e_LargestDivisibleSubset {
}
