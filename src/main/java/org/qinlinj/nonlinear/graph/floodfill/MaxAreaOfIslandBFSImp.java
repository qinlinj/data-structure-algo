package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

/**
 * BFS Max Area of Island - Queue-based Flood Fill Algorithm
 * <p>
 * Concept and Principles:
 * - A flood fill algorithm is used to determine connected regions in a 2D grid.
 * - This implementation finds islands (connected land cells) in a grid where:
 * - 1 represents land
 * - 0 represents water
 * - Unlike the previous DFS implementations, this solution uses Breadth-First Search (BFS)
 * with a queue to explore the island level by level.
 * - We mark visited cells in-place by changing their values from 1 to 0.
 * <p>
 * Advantages:
 * - Level-Order Traversal: Explores cells in order of their distance from the starting point
 * - Shortest Path Property: If path finding were needed, BFS would find the shortest path
 * - Balanced Exploration: More evenly distributed memory usage throughout execution
 * - No Recursion: Avoids stack overflow issues for large islands
 * - Better for Visualization: Provides a natural animation effect if visualized
 * <p>
 * Visualization Example:
 * Consider a small 3x3 grid:
 * <p>
 * 1 1 0
 * 1 0 0
 * 0 0 1
 * <p>
 * BFS exploration from position (0,0):
 * 1. Enqueue (0,0) and mark as visited (0)
 * 2. Dequeue (0,0), increase area count to 1
 * 3. Check neighbors: (0,1) and (1,0) are land
 * - Enqueue (0,1) and (1,0) and mark as visited
 * 4. Dequeue (0,1), increase area count to 2
 * 5. No unvisited land neighbors
 * 6. Dequeue (1,0), increase area count to 3
 * 7. No unvisited land neighbors
 * 8. Queue empty, area of first island: 3
 * <p>
 * The key difference from DFS is the order of exploration - BFS explores all neighbors
 * at the current level before moving to the next level.
 * <p>
 * The maximum island area is 3.
 */
public class MaxAreaOfIslandBFSImp {
    private int rows;
    private int cols;

    private int[][] grid;

    private Set<Integer>[] graph;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        MaxAreaOfIslandBFSImp maxAreaOfIsland = new MaxAreaOfIslandBFSImp();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    private int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;

        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;

        int res = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    int currOnes = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{row, col});
                    grid[row][col] = 0;
                    while (!queue.isEmpty()) {
                        int[] curr = queue.remove();
                        int currRow = curr[0], currCol = curr[1];
                        currOnes++;
                        for (int[] dir : directions) {
                            int nextRow = currRow + dir[0];
                            int nextCol = currCol + dir[1];
                            if (inArea(nextRow, nextCol)
                                    && grid[nextRow][nextCol] == 1) {
                                queue.add(new int[]{nextRow, nextCol});
                                grid[nextRow][nextCol] = 0;
                            }
                        }
                    }
                    res = Math.max(res, currOnes);
                }
            }
        }

        return res;
    }


    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
