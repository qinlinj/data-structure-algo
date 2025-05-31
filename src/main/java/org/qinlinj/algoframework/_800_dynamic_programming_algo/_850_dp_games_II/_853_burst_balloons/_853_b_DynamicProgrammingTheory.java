package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._853_burst_balloons;


/**
 * BURST BALLOONS PROBLEM - DYNAMIC PROGRAMMING THEORY
 * <p>
 * Problem Analysis:
 * - The challenge with DP approach is that subproblems are NOT independent
 * - Bursting a balloon affects its neighbors, creating dependencies
 * - Traditional DP requires independent subproblems
 * <p>
 * Key Innovation - Reverse Thinking:
 * - Instead of thinking "which balloon to burst first"
 * - Think "which balloon to burst LAST in a given range"
 * - This eliminates dependencies between subproblems
 * <p>
 * Problem Transformation:
 * - Add virtual balloons with value 1 at both ends
 * - Original: [3,1,5,8] → Enhanced: [1,3,1,5,8,1]
 * - This unifies all cases to always have 3 balloons for calculation
 * <p>
 * DP Definition:
 * - dp[i][j] = maximum coins from bursting all balloons between i and j (exclusive)
 * - Base case: dp[i][j] = 0 when j <= i+1 (no balloons between i and j)
 * - Final answer: dp[0][n+1]
 * <p>
 * State Transition:
 * - For each range (i,j), try each k as the LAST balloon to burst
 * - dp[i][j] = max(dp[i][k] + dp[k][j] + points[i]*points[k]*points[j])
 * - Where i < k < j
 * <p>
 * Why This Works:
 * - When k is the last balloon in range (i,j), ranges (i,k) and (k,j) are independent
 * - After all other balloons are burst, only i, k, j remain adjacent
 * - Bursting k gives exactly points[i]*points[k]*points[j]
 */

public class _853_b_DynamicProgrammingTheory {
}
