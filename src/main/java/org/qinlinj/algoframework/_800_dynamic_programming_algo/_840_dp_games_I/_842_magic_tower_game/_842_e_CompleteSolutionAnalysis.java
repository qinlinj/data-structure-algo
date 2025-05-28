package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game; /**
 * COMPLETE DUNGEON GAME SOLUTION WITH COMPREHENSIVE ANALYSIS
 * <p>
 * Problem Summary:
 * - Knight must travel from top-left to bottom-right to rescue princess
 * - Can only move right or down
 * - Health must remain > 0 throughout the journey
 * - Find minimum initial health required
 * <p>
 * Solution Approach:
 * 1. Use reverse DP: work backwards from destination
 * 2. dp(i,j) = minimum health needed at (i,j) to reach the end successfully
 * 3. State transition: dp(i,j) = max(1, min(dp(i+1,j), dp(i,j+1)) - grid[i][j])
 * <p>
 * Time Complexity: O(m*n) where m,n are grid dimensions
 * Space Complexity: O(m*n) for memoization
 * <p>
 * Key Insights:
 * - Forward DP fails due to insufficient state information
 * - Reverse DP provides complete information for optimal decisions
 * - Health constraint (>0) is critical for correctness
 */

import java.util.*;

public class _842_e_CompleteSolutionAnalysis {

    /**
     * Main method to run all demonstrations
     */
    public static void main(String[] args) {
        TestSuite testSuite = new TestSuite();
        testSuite.runAllTests();

        ConceptDemo.demonstrateKeyInsights();

        System.out.println("=== SUMMARY ===");
        System.out.println("The Dungeon Game problem teaches us:");
        System.out.println("1. Sometimes reverse thinking provides better state information");
        System.out.println("2. Constraints can fundamentally change solution approach");
        System.out.println("3. DP state definition is crucial for correctness");
        System.out.println("4. Memoization transforms exponential to polynomial time");
        System.out.println("5. Both recursive and iterative DP have their merits");
    }

    /**
     * Main solution class for the Dungeon Game problem
     */
    public static class DungeonGameSolver {
        private int[][] memo;

        /**
         * Calculates minimum initial health needed for knight to rescue princess
         * @param dungeon m x n grid representing the dungeon
         * @return minimum initial health points required
         */
        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon == null || dungeon.length == 0) return 1;

            int m = dungeon.length;
            int n = dungeon[0].length;

            // Initialize memoization table
            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return dp(dungeon, 0, 0);
        }

        /**
         * DP helper function with memoization
         * @param dungeon the grid
         * @param i current row index
         * @param j current column index
         * @return minimum health needed at position (i,j) to reach destination
         */
        private int dp(int[][] dungeon, int i, int j) {
            int m = dungeon.length;
            int n = dungeon[0].length;

            // Base case: reached the princess (bottom-right corner)
            if (i == m - 1 && j == n - 1) {
                // Need enough health to survive this room and have ≥1 health after
                return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
            }

            // Out of bounds - return max value to avoid this path
            if (i == m || j == n) {
                return Integer.MAX_VALUE;
            }

            // Return cached result if already computed
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            // Calculate minimum health needed from both possible next moves
            int healthNeededFromRight = dp(dungeon, i, j + 1);     // Move right
            int healthNeededFromDown = dp(dungeon, i + 1, j);      // Move down

            // Choose the path requiring minimum health
            int minHealthFromNextStep = Math.min(healthNeededFromRight, healthNeededFromDown);

            // Calculate health needed at current position
            int healthNeededHere = minHealthFromNextStep - dungeon[i][j];

            // Health must be at least 1 (cannot be dead)
            int result = Math.max(1, healthNeededHere);

            // Cache and return result
            memo[i][j] = result;
            return result;
        }

        /**
         * Alternative iterative solution for comparison
         */
        public int calculateMinimumHPIterative(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;

            // Create DP table with extra row/column for boundary handling
            int[][] dp = new int[m + 1][n + 1];

            // Initialize boundary values to max (invalid paths)
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            // Set base cases: virtual cells adjacent to destination
            dp[m][n - 1] = dp[m - 1][n] = 1;

            // Fill DP table from bottom-right to top-left
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int minHealthNeeded = Math.min(dp[i + 1][j], dp[i][j + 1]);
                    dp[i][j] = Math.max(1, minHealthNeeded - dungeon[i][j]);
                }
            }

            return dp[0][0];
        }
    }

    /**
     * Comprehensive test suite and analysis
     */
    public static class TestSuite {
        private DungeonGameSolver solver = new DungeonGameSolver();

        public void runAllTests() {
            System.out.println("=== DUNGEON GAME COMPLETE SOLUTION ANALYSIS ===\n");

            testBasicExample();
            testEdgeCases();
            testPerformanceComparison();
            analyzeAlgorithmComplexity();
        }

        private void testBasicExample() {
            System.out.println("1. BASIC EXAMPLE TEST");
            System.out.println("-------------------");

            int[][] dungeon = {
                    {-2, -3, 3},
                    {-5, -10, 1},
                    {10, 30, -5}
            };

            System.out.println("Grid:");
            printGrid(dungeon);

            int result1 = solver.calculateMinimumHP(dungeon);
            int result2 = solver.calculateMinimumHPIterative(dungeon);

            System.out.println("Recursive solution: " + result1);
            System.out.println("Iterative solution: " + result2);
            System.out.println("Expected: 7");
            System.out.println("Test passed: " + (result1 == 7 && result2 == 7));

            // Trace the optimal path
            traceOptimalPath(dungeon, result1);
            System.out.println();
        }

        private void testEdgeCases() {
            System.out.println("2. EDGE CASES TEST");
            System.out.println("------------------");

            // Single cell with positive value
            testCase("Single positive cell", new int[][]{{5}}, 1);

            // Single cell with negative value
            testCase("Single negative cell", new int[][]{{-3}}, 4);

            // All positive values
            testCase("All positive", new int[][]{{1, 2}, {3, 4}}, 1);

            // All negative values
            testCase("All negative", new int[][]{{-1, -2}, {-3, -4}}, 7);

            // Mixed with zeros
            testCase("Mixed with zeros", new int[][]{{0, -3}, {1, 0}}, 4);

            System.out.println();
        }

        private void testCase(String name, int[][] grid, int expected) {
            int result = solver.calculateMinimumHP(grid);
            System.out.printf("%-20s: Result=%d, Expected=%d, %s\n",
                    name, result, expected, result == expected ? "✓" : "✗");
        }

        private void testPerformanceComparison() {
            System.out.println("3. PERFORMANCE COMPARISON");
            System.out.println("-------------------------");

            // Create larger test grid
            int size = 100;
            int[][] largeGrid = createTestGrid(size, size);

            // Time recursive approach
            long start1 = System.nanoTime();
            int result1 = solver.calculateMinimumHP(largeGrid);
            long time1 = System.nanoTime() - start1;

            // Time iterative approach
            long start2 = System.nanoTime();
            int result2 = solver.calculateMinimumHPIterative(largeGrid);
            long time2 = System.nanoTime() - start2;

            System.out.printf("Grid size: %dx%d\n", size, size);
            System.out.printf("Recursive time: %.2f ms (result: %d)\n", time1 / 1_000_000.0, result1);
            System.out.printf("Iterative time: %.2f ms (result: %d)\n", time2 / 1_000_000.0, result2);
            System.out.printf("Results match: %s\n", result1 == result2 ? "✓" : "✗");
            System.out.println();
        }

        private void analyzeAlgorithmComplexity() {
            System.out.println("4. ALGORITHM COMPLEXITY ANALYSIS");
            System.out.println("--------------------------------");

            System.out.println("Time Complexity: O(m × n)");
            System.out.println("- Each cell is computed exactly once due to memoization");
            System.out.println("- Total number of subproblems: m × n");
            System.out.println("- Each subproblem takes O(1) time");

            System.out.println("\nSpace Complexity: O(m × n)");
            System.out.println("- Memoization table: m × n entries");
            System.out.println("- Recursion stack depth: O(m + n) in worst case");
            System.out.println("- Iterative version uses O(m × n) for DP table");

            System.out.println("\nAlgorithm Properties:");
            System.out.println("- Optimal substructure: ✓");
            System.out.println("- Overlapping subproblems: ✓");
            System.out.println("- Greedy choice property: ✗ (need to explore all paths)");
            System.out.println();
        }

        private void traceOptimalPath(int[][] dungeon, int minHealth) {
            System.out.println("\nOptimal Path Trace:");
            System.out.println("Starting health: " + minHealth);

            // Simulate the path (this is for demonstration)
            int health = minHealth;
            int i = 0, j = 0;

            System.out.printf("Position (%d,%d): health %d -> %d\n",
                    i, j, health, health + dungeon[i][j]);
            health += dungeon[i][j];

            // This is a simplified trace - in practice, you'd need to reconstruct
            // the actual optimal path by following the DP decisions
            System.out.println("(Path reconstruction would require storing decisions...)");
        }

        private int[][] createTestGrid(int rows, int cols) {
            int[][] grid = new int[rows][cols];
            java.util.Random rand = new java.util.Random(42); // Fixed seed for reproducibility

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    grid[i][j] = rand.nextInt(21) - 10; // Random values from -10 to 10
                }
            }
            return grid;
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

    /**
     * Educational demonstration of key concepts
     */
    public static class ConceptDemo {

        public static void demonstrateKeyInsights() {
            System.out.println("5. KEY ALGORITHMIC INSIGHTS");
            System.out.println("===========================");

            explainReverseThinking();
            explainStateTransition();
            explainHealthConstraint();
            compareWithRelatedProblems();
        }

        private static void explainReverseThinking() {
            System.out.println("A. Why Reverse DP Works:");
            System.out.println("------------------------");
            System.out.println("Forward approach: \"How much health to GET to cell (i,j)?\"");
            System.out.println("- Problem: Don't know remaining health upon arrival");
            System.out.println("- Cannot make optimal decisions for next moves");

            System.out.println("\nReverse approach: \"How much health NEEDED at cell (i,j)?\"");
            System.out.println("- Advantage: Know exact requirements for all future states");
            System.out.println("- Can make optimal decisions with complete information");
            System.out.println("- Natural base case at destination");
            System.out.println();
        }

        private static void explainStateTransition() {
            System.out.println("B. State Transition Logic:");
            System.out.println("--------------------------");
            System.out.println("dp(i,j) = max(1, min(dp(i+1,j), dp(i,j+1)) - grid[i][j])");
            System.out.println();
            System.out.println("Breaking it down:");
            System.out.println("1. min(dp(i+1,j), dp(i,j+1)): Choose better next move");
            System.out.println("2. - grid[i][j]: Account for current room's effect");
            System.out.println("3. max(1, result): Ensure health never drops to 0");
            System.out.println();
        }

        private static void explainHealthConstraint() {
            System.out.println("C. Health Constraint Importance:");
            System.out.println("--------------------------------");
            System.out.println("Why max(1, calculated_health)?");
            System.out.println("- Knight dies if health ≤ 0");
            System.out.println("- Must maintain health > 0 at ALL times");
            System.out.println("- Even if future rooms give lots of health");
            System.out.println("- This constraint is what makes the problem non-trivial");
            System.out.println();
        }

        private static void compareWithRelatedProblems() {
            System.out.println("D. Comparison with Related Problems:");
            System.out.println("-----------------------------------");
            System.out.println("Minimum Path Sum:");
            System.out.println("- Goal: Minimize sum of path values");
            System.out.println("- No health constraint");
            System.out.println("- Forward DP works fine");

            System.out.println("\nMaximum Path Sum:");
            System.out.println("- Goal: Maximize sum of path values");
            System.out.println("- No health constraint");
            System.out.println("- Forward DP works fine");

            System.out.println("\nDungeon Game:");
            System.out.println("- Goal: Minimize initial health with survival constraint");
            System.out.println("- Health must stay > 0 throughout");
            System.out.println("- Requires reverse DP due to constraint");
            System.out.println();
        }
    }
}