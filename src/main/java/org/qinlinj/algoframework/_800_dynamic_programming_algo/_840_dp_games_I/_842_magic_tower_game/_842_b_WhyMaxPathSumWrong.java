package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

/**
 * WHY MAXIMUM PATH SUM APPROACH IS WRONG
 * <p>
 * Key Learning Points:
 * 1. Intuitive but incorrect approach: maximize health potions collected
 * 2. Maximum path sum ≠ minimum initial health requirement
 * 3. Counterexample shows why this logic fails
 * 4. Focus should be on minimizing health loss, not maximizing gain
 * <p>
 * Important Insight:
 * - Getting the most health potions doesn't guarantee minimum initial health
 * - The timing and sequence of health changes matter more than total gain
 * - Need to consider worst-case scenarios along different paths
 * <p>
 * This demonstrates why careful problem analysis is crucial before coding.
 */

public class _842_b_WhyMaxPathSumWrong {

    /**
     * Counterexample demonstrating why maximum path sum approach fails
     * <p>
     * In this grid, the maximum path sum approach would suggest:
     * Path 1 (max sum): (1,1) -> (1,2) -> (2,2) with sum = 1 + 5 + 1 = 7
     * This requires initial health = 11 to survive the -10 demon
     * <p>
     * But the optimal path is:
     * Path 2 (optimal): (1,1) -> (2,1) -> (2,2) with sum = 1 + (-3) + 1 = -1
     * This only requires initial health = 1
     */
    private static final int[][] COUNTEREXAMPLE = {
            {1, -3, 3},
            {0, -5, 1},
            {0, 0, -3}
    };

    // Simulated paths to demonstrate the difference
    public static void demonstrateWrongApproach() {
        System.out.println("=== Why Maximum Path Sum Approach Fails ===");
        System.out.println("Grid:");
        printGrid(COUNTEREXAMPLE);

        System.out.println("\n--- Wrong Approach: Maximum Path Sum ---");
        simulateMaxSumPath();

        System.out.println("\n--- Correct Approach: Minimum Health Loss ---");
        simulateOptimalPath();

        System.out.println("\nConclusion:");
        System.out.println("- Maximum path sum = 7, but requires initial health = 11");
        System.out.println("- Optimal path sum = -1, but only requires initial health = 1");
        System.out.println("- Key insight: Focus on health survival, not health maximization");
    }

    private static void simulateMaxSumPath() {
        System.out.println("Path: (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2)");

        int health = 11; // Required initial health
        int[] pathValues = {1, -3, 3, 1, -3};
        String[] positions = {"(0,0)", "(0,1)", "(0,2)", "(1,2)", "(2,2)"};

        System.out.println("Health simulation:");
        for (int i = 0; i < pathValues.length; i++) {
            System.out.printf("At %s: health %d + %d = %d\n",
                    positions[i], health, pathValues[i], health + pathValues[i]);
            health += pathValues[i];
            if (health <= 0) {
                System.out.println("  ⚠️  Health dropped to " + health + " - DEATH!");
                return;
            }
        }
        System.out.println("Path sum: 7, Initial health needed: 11");
    }

    private static void simulateOptimalPath() {
        System.out.println("Path: (0,0) -> (1,0) -> (2,0) -> (2,1) -> (2,2)");

        int health = 1; // Minimal initial health
        int[] pathValues = {1, 0, 0, 0, -3};
        String[] positions = {"(0,0)", "(1,0)", "(2,0)", "(2,1)", "(2,2)"};

        System.out.println("Health simulation:");
        for (int i = 0; i < pathValues.length; i++) {
            System.out.printf("At %s: health %d + %d = %d\n",
                    positions[i], health, pathValues[i], health + pathValues[i]);
            health += pathValues[i];
            if (health <= 0) {
                System.out.println("  ⚠️  Health dropped to " + health + " - would need more initial health!");
            }
        }
        System.out.println("Path sum: -1, Initial health needed: 1");
    }

    private static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.printf("%4d ", grid[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        demonstrateWrongApproach();
    }
}