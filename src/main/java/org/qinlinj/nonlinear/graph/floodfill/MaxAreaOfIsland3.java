package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

public class MaxAreaOfIsland3 {
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
        MaxAreaOfIsland3 maxAreaOfIsland = new MaxAreaOfIsland3();
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
