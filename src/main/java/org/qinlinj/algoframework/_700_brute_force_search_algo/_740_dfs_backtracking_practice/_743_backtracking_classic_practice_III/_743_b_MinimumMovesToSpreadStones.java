package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._743_backtracking_classic_practice_III;

import java.util.*;

/**
 * 2850. Minimum Moves to Spread Stones Over Grid
 * <p>
 * Problem Summary:
 * Given a 3x3 grid where each cell contains a non-negative integer representing the number of stones,
 * move stones until each cell has exactly one stone. In one move, you can move a stone from its
 * current cell to an adjacent cell that shares at least one edge. Return the minimum number of moves.
 * <p>
 * Key Insights:
 * - The problem involves resource allocation across a grid
 * - While BFS might seem intuitive, it doesn't work well for this resource allocation problem
 * - Backtracking is suitable due to the small problem size (3x3 grid with 9 stones)
 * - We can divide cells into two categories:
 * 1. Cells with more than 1 stone (source)
 * 2. Cells with 0 stones (destination)
 * - For each source cell, we need to decide which destination cells to allocate stones to
 * <p>
 * Time Complexity: O(m!), where m is the number of empty cells (worst case)
 * Space Complexity: O(m) for recursion stack and tracking allocations
 */
class _743_b_MinimumMovesToSpreadStones {

    // Track minimum moves during backtracking
    int minMove = Integer.MAX_VALUE;
    // Track current moves during exploration
    int move = 0;
    // Track number of empty cells remaining
    int emptyCount = 0;

    public int minimumMoves(int[][] grid) {
        // Lists to track cells with excess stones and empty cells
        List<int[]> redundant = new ArrayList<>();
        List<int[]> empty = new ArrayList<>();

        // Identify source and destination cells
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1) {
                    // Cell has extra stones to distribute
                    redundant.add(new int[]{i, j});
                } else if (grid[i][j] == 0) {
                    // Cell needs a stone
                    empty.add(new int[]{i, j});
                    emptyCount++;
                }
            }
        }

        // Start backtracking to find optimal allocation
        backtrack(grid, redundant, empty);

        return minMove;
    }

    void backtrack(int[][] grid, List<int[]> redundant, List<int[]> empty) {
        // Base case: all empty cells filled
        if (emptyCount == 0) {
            // Update minimum moves found so far
            minMove = Math.min(minMove, move);
            return;
        }

        // Try moving stones from each redundant cell to each empty cell
        for (int[] r : redundant) {
            int srcX = r[0], srcY = r[1];

            // Skip if this cell no longer has extra stones
            if (grid[srcX][srcY] <= 1) {
                continue;
            }

            for (int[] e : empty) {
                int destX = e[0], destY = e[1];

                // Skip if this cell is already filled
                if (grid[destX][destY] != 0) {
                    continue;
                }

                // Calculate Manhattan distance (number of moves needed)
                int step = Math.abs(srcX - destX) + Math.abs(srcY - destY);

                // Make choice: move a stone
                grid[destX][destY] = 1;  // Add stone to destination
                grid[srcX][srcY]--;      // Remove stone from source
                move += step;            // Increment move count
                emptyCount--;            // Decrement empty cell count

                // Recursively explore rest of the allocation
                backtrack(grid, redundant, empty);

                // Undo choice for backtracking
                grid[destX][destY] = 0;
                grid[srcX][srcY]++;
                move -= step;
                emptyCount++;
            }
        }
    }

    /**
     * Failed BFS approach - included for educational purposes
     * This approach doesn't work because BFS doesn't make globally optimal decisions
     * for resource allocation problems like this.
     */
    static class FailedBFSApproach {
        public int minimumMoves(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            Queue<State> q = new LinkedList<>();

            // Add all cells with extra stones to the queue
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] > 1) {
                        q.offer(new State(i, j, i, j));
                    }
                }
            }

            int steps = 0;
            int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

            while (!q.isEmpty()) {
                State cur = q.poll();
                int srcX = cur.srcX, srcY = cur.srcY;
                int curX = cur.curX, curY = cur.curY;

                for (int[] dir : dirs) {
                    int nextX = curX + dir[0], nextY = curY + dir[1];
                    // Skip invalid cells
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                        continue;
                    }

                    if (grid[srcX][srcY] == 1) {
                        // No more stones to allocate from this source
                        break;
                    }

                    // Check if next cell needs a stone
                    if (grid[nextX][nextY] == 0) {
                        // Allocate a stone
                        grid[nextX][nextY] = 1;
                        grid[srcX][srcY]--;
                        steps += Math.abs(srcX - nextX) + Math.abs(srcY - nextY);
                    }

                    // Continue exploring if more stones to allocate
                    if (grid[srcX][srcY] > 1) {
                        q.offer(new State(srcX, srcY, nextX, nextY));
                    }
                }
            }

            return steps;
        }

        class State {
            int srcX, srcY;
            int curX, curY;

            public State(int srcX, int srcY, int curX, int curY) {
                this.srcX = srcX;
                this.srcY = srcY;
                this.curX = curX;
                this.curY = curY;
            }
        }
    }
}