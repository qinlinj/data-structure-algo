package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

/**
 * DUNGEON GAME PROBLEM INTRODUCTION
 * <p>
 * Key Concepts:
 * 1. Classic dungeon-style game where you need to rescue a princess
 * 2. Knight moves from top-left to bottom-right (only right or down moves)
 * 3. Rooms contain demons (negative values) or health potions (positive values)
 * 4. Goal: Find minimum initial health needed to complete the journey
 * 5. Health must always remain > 0 throughout the journey
 * <p>
 * Problem Analysis:
 * - This is NOT about finding maximum path sum
 * - Key insight: Minimize health loss, not maximize health gain
 * - Requires dynamic programming with reverse thinking
 * <p>
 * Example:
 * Grid: [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Answer: 7 (following path: right -> right -> down -> down)
 */

public class _842_a_DungeonGameIntroduction {

    /**
     * Problem Statement:
     * Given an m x n grid representing a dungeon, where:
     * - Negative values represent demons (health loss)
     * - Positive values represent health potions (health gain)
     * - Zero values represent empty rooms
     * <p>
     * Find the minimum initial health needed for a knight to travel
     * from top-left to bottom-right and rescue the princess.
     * <p>
     * Constraints:
     * - Knight can only move right or down
     * - Health must remain > 0 at all times
     * - m, n <= 200
     * - Grid values range from -1000 to 1000
     */

    // Example grid from the problem
    private static final int[][] EXAMPLE_GRID = {
            {-2, -3, 3},
            {-5, -10, 1},
            {10, 30, -5}
    };

    public static void demonstrateProblem() {
        System.out.println("=== Dungeon Game Problem ===");
        System.out.println("Grid:");
        printGrid(EXAMPLE_GRID);

        System.out.println("\nExplanation:");
        System.out.println("- Knight starts at top-left corner");
        System.out.println("- Must reach bottom-right corner to save princess");
        System.out.println("- Can only move right or down");
        System.out.println("- Health must stay > 0 throughout journey");
        System.out.println("- Find minimum initial health needed");

        System.out.println("\nOptimal path: right -> right -> down -> down");
        System.out.println("Health changes: 7 -> 5 -> 2 -> 5 -> 15 -> 45 -> 40");
        System.out.println("Minimum initial health needed: 7");
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.printf("%4d ", val);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        demonstrateProblem();
    }
}