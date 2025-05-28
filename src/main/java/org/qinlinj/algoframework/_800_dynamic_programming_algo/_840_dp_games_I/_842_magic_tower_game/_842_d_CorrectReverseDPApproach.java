package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

/**
 * CORRECT REVERSE DP APPROACH
 * <p>
 * Key Insights:
 * 1. Reverse thinking: Start from destination (bottom-right) and work backwards
 * 2. Correct DP definition: dp(i,j) = minimum health needed AT cell (i,j) to reach the end
 * 3. State transition: Choose path that requires minimum health at current cell
 * 4. Base case: At destination, need enough health to survive the final room
 * <p>
 * Why This Works:
 * - We know exactly what health is required at future states
 * - Can make optimal decisions based on complete information
 * - State transitions have clear logical foundation
 * <p>
 * State Transition Logic:
 * - res = min(dp(i+1,j), dp(i,j+1)) - grid[i][j]
 * - dp(i,j) = max(1, res) // Health must be at least 1
 */

public class _842_d_CorrectReverseDPApproach {
    /**
     * Correct DP Solution using reverse thinking
     * <p>
     * Definition: dp(i,j) = minimum health needed when entering cell (i,j)
     * to successfully reach the bottom-right corner
     */

    private int[][] memo;

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // Initialize memoization array
        memo = new int[m][n];
        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }

        return dp(dungeon, 0, 0);
    }

    /**
     * DP function with memoization
     *
     * @param dungeon the grid
     * @param i       current row
     * @param j       current column
     * @return minimum health needed at (i,j) to reach destination
     */
    private int dp(int[][] dungeon, int i, int j) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // Base case: reached destination
        if (i == m - 1 && j == n - 1) {
            // Need enough health to survive this room and have at least 1 health remaining
            return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
        }

        // Out of bounds - return very large value to avoid this path
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }

        // Check memo to avoid recomputation
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // State transition: choose the path that requires minimum health
        int healthNeededFromRight = dp(dungeon, i, j + 1);
        int healthNeededFromDown = dp(dungeon, i + 1, j);

        // Choose the better option (minimum health required)
        int minHealthNeeded = Math.min(healthNeededFromRight, healthNeededFromDown);

        // Calculate health needed at current cell
        int healthNeededHere = minHealthNeeded - dungeon[i][j];

        // Health must be at least 1
        int result = Math.max(1, healthNeededHere);

        // Store in memo and return
        memo[i][j] = result;
        return result;
    }
}
