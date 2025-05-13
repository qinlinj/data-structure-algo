package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

public class _731_e_CountSubIslands {
    /**
     * Main function to count the number of sub-islands
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        if (grid1 == null || grid2 == null || grid1.length == 0 || grid2.length == 0) {
            return 0;
        }

        int m = grid1.length;
        int n = grid1[0].length;

        // Step 1: Eliminate islands in grid2 that cannot be sub-islands
        // (islands that have land where grid1 has water)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If grid2 has land but grid1 has water, this island in grid2 cannot be a sub-island
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    // Sink this island in grid2
                    dfs(grid2, i, j);
                }
            }
        }
        return 0;
    }
}
