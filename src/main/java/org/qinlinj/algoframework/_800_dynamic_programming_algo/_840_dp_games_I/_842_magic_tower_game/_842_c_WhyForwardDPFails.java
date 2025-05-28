package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

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
