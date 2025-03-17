package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

public class MaxAreaOfIsland2 {
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
        MaxAreaOfIsland2 maxAreaOfIsland = new MaxAreaOfIsland2();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    public int MaxAreaOfIsland(int[][] grid) {
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

                    }
                }
                res = Math.max(res, currOnes);
            }
        }
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
