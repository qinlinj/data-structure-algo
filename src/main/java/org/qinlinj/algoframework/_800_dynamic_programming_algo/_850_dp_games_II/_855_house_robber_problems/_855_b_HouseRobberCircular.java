package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._855_house_robber_problems;

/**
 * HOUSE ROBBER II - CIRCULAR ARRAY DYNAMIC PROGRAMMING
 * <p>
 * PROBLEM SUMMARY:
 * Houses are arranged in a circle, meaning the first and last houses are adjacent.
 * You cannot rob two adjacent houses. Find the maximum amount you can rob.
 * <p>
 * KEY INSIGHTS:
 * 1. Circular constraint: First and last houses cannot both be robbed
 * 2. Three possible scenarios:
 * - Rob neither first nor last house
 * - Rob first house, cannot rob last house
 * - Rob last house, cannot rob first house
 * 3. We only need to compare scenarios 2 and 3 (they cover scenario 1)
 * <p>
 * SOLUTION APPROACH:
 * 1. Case 1: Rob houses from index 0 to n-2 (include first, exclude last)
 * 2. Case 2: Rob houses from index 1 to n-1 (exclude first, include last)
 * 3. Return maximum of both cases
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(1)
 */

public class _855_b_HouseRobberCircular {
}
