package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

/**
 * Summary of Island Problems in Algorithms
 * <p>
 * This class summarizes all the island-related problems we've covered and provides a comparison
 * of the different approaches used to solve them.
 * <p>
 * Common Island Problems:
 * <p>
 * 1. Number of Islands (LeetCode 200)
 * - Count the number of islands in a grid
 * - Basic DFS/BFS approach: Flood fill to mark visited islands
 * <p>
 * 2. Closed Islands (LeetCode 1254)
 * - Count islands that don't touch the boundary
 * - Approach: First flood islands connected to boundary, then count remaining islands
 * <p>
 * 3. Max Area of Island (LeetCode 695)
 * - Find the maximum area of any island
 * - Approach: Use DFS with a return value to keep track of area
 * <p>
 * 4. Count Sub-Islands (LeetCode 1905)
 * - Count islands in grid2 that are completely contained within islands in grid1
 * - Approach: First eliminate islands in grid2 that cannot be sub-islands, then count
 * <p>
 * 5. Number of Distinct Islands (LeetCode 694)
 * - Count islands with distinct shapes
 * - Approach: Use path signature from DFS to encode island shapes
 * <p>
 * Common Techniques:
 * 1. DFS/BFS for traversing connected cells
 * 2. Marking visited cells by modifying the grid
 * 3. Using direction arrays for cleaner code
 * 4. Encoding patterns for more complex problems
 * <p>
 * Time & Space Complexity:
 * - All solutions have O(m*n) time complexity where m and n are grid dimensions
 * - Space complexity is typically O(m*n) in worst case for recursion stack
 */

public class _731_g_IslandProblemsSummary {

    /**
     * Compares the different approaches used in island problems
     */
    public static void compareApproaches() {
        System.out.println("===== Island Problems Summary =====\n");

        System.out.println("1. Number of Islands (LC 200)");
        System.out.println("   - Basic problem: Count all islands");
        System.out.println("   - Use DFS to mark visited islands\n");

        System.out.println("2. Closed Islands (LC 1254)");
        System.out.println("   - Variant: Only count islands not touching boundary");
        System.out.println("   - First flood boundary islands, then count remaining\n");

        System.out.println("3. Max Area of Island (LC 695)");
        System.out.println("   - Variant: Find largest island by area");
        System.out.println("   - DFS returns size of each island\n");

        System.out.println("4. Count Sub-Islands (LC 1905)");
        System.out.println("   - Advanced: Islands in grid2 contained within grid1");
        System.out.println("   - Eliminate non-sub-islands first, then count\n");

        System.out.println("5. Number of Distinct Islands (LC 694)");
        System.out.println("   - Most complex: Count unique island shapes");
        System.out.println("   - Create path signatures using DFS traversal order\n");
    }

    /**
     * Common pattern across all island problems
     */
    public static void commonPattern() {
        System.out.println("===== Common Pattern =====\n");

        System.out.println("All island problems follow this general approach:");
        System.out.println("1. Iterate through each cell in the grid");
        System.out.println("2. When finding an unvisited island cell:");
        System.out.println("   a. Process it according to problem requirements");
        System.out.println("   b. Use DFS/BFS to mark all connected cells as visited");
        System.out.println("3. Continue until entire grid is processed\n");

        System.out.println("DFS is typically preferred for simplicity, but BFS works too");
        System.out.println("The grid itself can be used as a visited array by modifying values");
    }

    /**
     * Main method with examples
     */
    public static void main(String[] args) {
        compareApproaches();
        System.out.println("\n");
        commonPattern();

        System.out.println("\n===== Key Takeaways =====\n");
        System.out.println("1. Island problems are excellent examples of graph traversal in disguise");
        System.out.println("2. The FloodFill algorithm (DFS/BFS) is the core technique");
        System.out.println("3. More complex problems require creative adaptations of the basic approach");
        System.out.println("4. Pattern recognition across similar problems leads to faster solutions");
    }
}