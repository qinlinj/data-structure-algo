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

    public static void main(String[] args) {
        _842_d_CorrectReverseDPApproach solution = new _842_d_CorrectReverseDPApproach();
        solution.demonstrateReverseDPLogic();

        // Test with the example
        int[][] test = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println("\nTesting both implementations:");
        System.out.println("Recursive result: " + solution.calculateMinimumHP(test));
        System.out.println("Iterative result: " + solution.calculateMinimumHPIterative(test));
    }

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

    /**
     * Demonstrates the reverse DP logic with step-by-step calculation
     */
    public void demonstrateReverseDPLogic() {
        int[][] example = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };

        System.out.println("=== Reverse DP Step-by-Step Demonstration ===");
        System.out.println("Grid:");
        printGrid(example);

        System.out.println("\nReverse DP Calculation:");
        System.out.println("Starting from bottom-right, working backwards...");

        // Manual calculation for demonstration
        System.out.println("\n1. Base case dp(2,2):");
        System.out.println("   At (-5): need max(1, 0-(-5)) = max(1, 5) = 5");

        System.out.println("\n2. Calculate dp(2,1):");
        System.out.println("   At (30): need max(1, 5-30) = max(1, -25) = 1");

        System.out.println("\n3. Calculate dp(1,2):");
        System.out.println("   At (1): need max(1, 5-1) = max(1, 4) = 4");

        System.out.println("\n4. Calculate dp(2,0):");
        System.out.println("   At (10): need max(1, 1-10) = max(1, -9) = 1");

        System.out.println("\n5. Calculate dp(1,1):");
        System.out.println("   At (-10): min(dp(1,2), dp(2,1)) = min(4, 1) = 1");
        System.out.println("   Need max(1, 1-(-10)) = max(1, 11) = 11");

        System.out.println("\n6. Calculate dp(1,0):");
        System.out.println("   At (-5): min(dp(1,1), dp(2,0)) = min(11, 1) = 1");
        System.out.println("   Need max(1, 1-(-5)) = max(1, 6) = 6");

        System.out.println("\n7. Calculate dp(0,2):");
        System.out.println("   At (3): need max(1, 4-3) = max(1, 1) = 1");

        System.out.println("\n8. Calculate dp(0,1):");
        System.out.println("   At (-3): min(dp(0,2), dp(1,1)) = min(1, 11) = 1");
        System.out.println("   Need max(1, 1-(-3)) = max(1, 4) = 4");

        System.out.println("\n9. Calculate dp(0,0) - Final Answer:");
        System.out.println("   At (-2): min(dp(0,1), dp(1,0)) = min(4, 6) = 4");
        System.out.println("   Need max(1, 4-(-2)) = max(1, 6) = 6");

        int result = calculateMinimumHP(example);
        System.out.println("\nActual calculation result: " + result);
    }

    /**
     * Iterative DP version for comparison
     */
    public int calculateMinimumHPIterative(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // DP table: dp[i][j] = min health needed at (i,j) to reach destination
        int[][] dp = new int[m + 1][n + 1];

        // Initialize boundaries with max value (invalid paths)
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // Base case: positions adjacent to destination
        dp[m][n - 1] = dp[m - 1][n] = 1;

        // Fill table from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minHealthNeeded = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(1, minHealthNeeded - dungeon[i][j]);
            }
        }

        return dp[0][0];
    }

    private void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.printf("%4d ", val);
            }
            System.out.println();
        }
    }
}