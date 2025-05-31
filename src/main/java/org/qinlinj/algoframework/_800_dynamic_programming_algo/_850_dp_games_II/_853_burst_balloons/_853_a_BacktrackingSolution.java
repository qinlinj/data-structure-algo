package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._853_burst_balloons;

/**
 * BURST BALLOONS PROBLEM - BACKTRACKING APPROACH
 * <p>
 * Problem Summary:
 * - Given n balloons with numbers, burst all balloons to maximize coins
 * - Bursting balloon i gives coins = nums[i-1] * nums[i] * nums[i+1]
 * - Boundaries are treated as balloons with value 1
 * <p>
 * Backtracking Approach:
 * - Exhaustively try all possible orders of bursting balloons
 * - This is essentially a permutation problem
 * - Time Complexity: O(n!) - factorial time, very inefficient
 * - Space Complexity: O(n) for recursion stack
 * <p>
 * Key Insights:
 * 1. For optimization problems, we need to exhaust all possibilities
 * 2. Backtracking is brute force enumeration
 * 3. This approach works but is too slow for large inputs (n ≤ 500)
 * <p>
 * Example:
 * Input: [3,1,5,8]
 * Process: [3,1,5,8] → [3,5,8] → [3,8] → [8] → []
 * Coins:   3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 = 15 + 120 + 24 + 8 = 167
 */
public class _853_a_BacktrackingSolution {
}
