package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

/**
 * 980. Unique Paths III
 * <p>
 * Problem Summary:
 * In a grid with obstacles, find all possible paths from the starting square to the ending square,
 * where every non-obstacle square must be visited exactly once.
 * - 1 represents starting square (exactly one)
 * - 2 represents ending square (exactly one)
 * - 0 represents empty square we can walk on
 * - -1 represents obstacles we cannot walk on
 * <p>
 * Key Insights:
 * - This is a classic DFS/backtracking problem
 * - We need to track visited cells to avoid revisiting
 * - The path is only valid if ALL empty cells (including start) have been visited before reaching the end
 * - We can explore in 4 directions (up, down, left, right)
 * <p>
 * Time Complexity: O(4^(m*n)), where m and n are the grid dimensions
 * Space Complexity: O(m*n) for visited grid and recursion stack
 */
class _741_b_UniquePathsIII {

    int res = 0;
    boolean[][] visited;
    int visitedCount = 0;
    int totalCount = 0;

    // Directions: right, left, down, up
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        visited = new boolean[m][n];

        int startI = 0, startJ = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    startI = i;
                    startJ = j;
                }
                if (grid[i][j] == 1 || grid[i][j] == 0) {
                    totalCount++;
                }
            }
        }

        dfs(grid, startI, startJ);

        return res;
    }

    // DFS algorithm framework
    void dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        // Pruning: out of bounds
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        // Pruning: obstacle or already visited
        if (grid[i][j] == -1 || visited[i][j]) {
            return;
        }

        // Reached end point
        if (grid[i][j] == 2) {
            if (visitedCount == totalCount) {
                res++;
            }
            return;
        }

        visited[i][j] = true;
        visitedCount++;

        // Explore all 4 directions
        for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1]);
        }

        // Backtrack
        visited[i][j] = false;
        visitedCount--;
    }
}