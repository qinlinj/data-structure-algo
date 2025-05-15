package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._742_backtracking_classic_practice_II;

/**
 * 1219. Path with Maximum Gold
 * <p>
 * Problem Summary:
 * In a gold mine grid, each cell contains an integer representing the amount of gold in that cell.
 * You're allowed to start and stop collecting gold from any position that contains gold.
 * Rules for the miner:
 * - When entering a cell, collect all the gold.
 * - Move in any of four directions (up, down, left, right).
 * - Cannot enter a cell with 0 gold.
 * - Cannot enter the same cell more than once.
 * Return the maximum amount of gold you can collect.
 * <p>
 * Key Insights:
 * - This is a classic backtracking problem similar to island problems
 * - Need to try starting from any cell with gold
 * - Use DFS to explore all possible paths from each starting position
 * - Track visited cells to avoid cycles
 * - Keep track of the maximum gold collected across all paths
 * <p>
 * Time Complexity: O(4^k) where k is the number of cells with gold
 * Space Complexity: O(m*n) for the visited array and recursion stack
 */
class _742_a_PathWithMaximumGold {

    // Store maximum score
    int res = 0;
    // Track visited positions to avoid cycles
    boolean[][] onPath;
    int[][] grid;
    // Direction array to help explore the four adjacent cells
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        this.onPath = new boolean[m][n];
        this.grid = grid;

        // Try starting from each position that contains gold
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, 0, 0);
            }
        }
        return res;
    }

    // DFS from (i, j) to explore all paths, pathSum is the current gold collected
    void dfs(int i, int j, int pathSum, int stepNum) {
        // Out of bounds, return
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        // Cannot enter a cell with 0 gold
        if (grid[i][j] == 0) {
            return;
        }
        // Cannot revisit a cell
        if (onPath[i][j]) {
            return;
        }

        // Backtracking framework: make choice
        onPath[i][j] = true;
        pathSum += grid[i][j];
        // Update maximum gold collected
        res = Math.max(res, pathSum);

        // Explore all four directions
        for (int[] dir : dirs) {
            dfs(i + dir[0], j + dir[1], pathSum, stepNum + 1);
        }

        // Backtracking framework: undo choice
        onPath[i][j] = false;
        pathSum -= grid[i][j];
    }
}