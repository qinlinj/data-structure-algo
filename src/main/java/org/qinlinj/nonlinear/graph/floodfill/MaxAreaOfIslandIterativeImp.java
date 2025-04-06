package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

// @formatter:off
/**
 * Iterative Max Area of Island - Stack-based Flood Fill Algorithm
 *
 * Concept and Principles:
 * - A flood fill algorithm is used to determine connected regions in a 2D grid.
 * - This implementation finds islands (connected land cells) in a grid where:
 *   - 1 represents land
 *   - 0 represents water
 * - Unlike the recursive DFS approach, this implementation uses an explicit stack
 *   to perform the depth-first search iteratively.
 * - We mark visited cells in-place by changing their values from 1 to 0.
 *
 * Advantages:
 * - No Stack Overflow: Avoids the risk of stack overflow for very large islands that
 *   might exceed the recursion depth limit
 * - Memory Efficient: Works directly on the grid without additional visited array
 * - Explicit Control: Provides direct control over the traversal process
 * - Similar Performance: Maintains the same time complexity as recursive approaches
 * - Better for Production: Generally preferred in production environments where
 *   recursive solutions might pose risks
 *
 * Visualization Example:
 * Consider a small 3x3 grid:
 *
 *   1 1 0
 *   1 0 0
 *   0 0 1
 *
 * Iterative exploration from position (0,0):
 * 1. Push (0,0) onto stack and mark as visited (0)
 * 2. Pop (0,0), increase area count to 1
 * 3. Check neighbors: (0,1) and (1,0) are land
 *    - Push (0,1) and (1,0) to stack and mark as visited
 * 4. Pop (1,0), increase area count to 2
 * 5. No unvisited land neighbors
 * 6. Pop (0,1), increase area count to 3
 * 7. No unvisited land neighbors
 * 8. Stack empty, area of first island: 3
 *
 * After first island exploration, the grid becomes:
 *   0 0 0
 *   0 0 0
 *   0 0 1
 *
 * Similar process for position (2,2) results in an island of area 1.
 *
 * The maximum island area is therefore 3.
 */

public class MaxAreaOfIslandIterativeImp {
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
        MaxAreaOfIslandIterativeImp maxAreaOfIsland = new MaxAreaOfIslandIterativeImp();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    public int maxAreaOfIsland(int[][] grid) {
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
                    ArrayDeque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{row, col});
                    grid[row][col] = 0;
                    while (!stack.isEmpty()) {
                        int[] curr = stack.pop();
                        int currRow = curr[0], currCol = curr[1];
                        currOnes++;
                        for (int[] dir : directions) {
                            int nextRow = currRow + dir[0];
                            int nextCol = currCol + dir[1];
                            if (inArea(nextRow, nextCol)
                                    && grid[nextRow][nextCol] == 1) {
                                stack.push(new int[]{nextRow, nextCol});
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
