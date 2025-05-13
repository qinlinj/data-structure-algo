package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

import java.util.*;

public class _731_f_NumberOfDistinctIslands {
    /**
     * Main function to count the number of distinct islands
     */
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // HashSet to store unique island signatures
        HashSet<String> distinctIslands = new HashSet<>();

        // Traverse the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // Found a new island
                    StringBuilder sb = new StringBuilder();
                    // Generate the path signature using a starting direction (arbitrary value)
                    dfs(grid, i, j, sb, 'o'); // 'o' for origin
                    distinctIslands.add(sb.toString());
                }
            }
        }

        // The number of distinct islands is the size of the HashSet
        return distinctIslands.size();
    }
}
