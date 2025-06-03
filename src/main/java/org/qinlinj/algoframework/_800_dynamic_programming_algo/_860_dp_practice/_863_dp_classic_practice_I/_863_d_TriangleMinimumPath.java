package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

// @formatter:off
/**
 * LeetCode 120. Triangle Minimum Path Sum - Dynamic Programming Solution
 *
 * PROBLEM SUMMARY:
 * Given a triangle array, return the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 *
 * KEY CONCEPTS:
 * 1. 2D Dynamic Programming with triangle structure
 * 2. State Definition: dp[i][j] = minimum path sum from top to triangle[i][j]
 * 3. State Transition: dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j]
 * 4. Adjacent constraint: from position j, you can move to j or j+1 in next row
 * 5. Boundary handling for leftmost and rightmost positions
 *
 * TIME COMPLEXITY: O(n²) where n is the number of rows
 * SPACE COMPLEXITY: O(n²), can be optimized to O(n)
 *
 * EXAMPLE:
 * Triangle: [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Visualization:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * Minimum path: 2 + 3 + 5 + 1 = 11
 */

public class _863_d_TriangleMinimumPath {
}
