package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

/**
 * WHY FORWARD DP DEFINITION FAILS
 * <p>
 * Key Learning Points:
 * 1. Wrong DP definition: "minimum health needed to reach (i,j) from start"
 * 2. Problem: Insufficient information for state transitions
 * 3. We know minimum health to REACH a cell, but not health REMAINING at cell
 * 4. Cannot make optimal decisions without knowing remaining health
 * <p>
 * Critical Insight:
 * - Forward thinking lacks necessary information for decision making
 * - Need to know not just "how to get there" but "what state we're in when we arrive"
 * - This motivates the reverse DP approach
 * <p>
 * Example Problem:
 * Two paths reach the same cell with same minimum initial health requirement,
 * but different remaining health - algorithm cannot distinguish between them.
 */

public class _842_c_WhyForwardDPFails {
    /**
     * Demonstrates why forward DP definition fails
     * <p>
     * Wrong definition: dp(i,j) = minimum initial health to reach cell (i,j)
     * Problem: This doesn't tell us the health remaining when we reach (i,j)
     * <p>
     * In the example below, both paths to reach (2,2) require initial health = 1,
     * but they leave us with different remaining health values.
     */

    private static final int[][] PROBLEM_GRID = {
            {-3, 5},
            {1, -4}
    };
}
