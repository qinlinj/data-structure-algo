package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

public class MaxAreaOfIsland {
    private int rows;
    private int cols;

    private int[][] grid;

    private Set<Integer>[] graph;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;

        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;

        graph = constructGraph();

        this.visited = new boolean[graph.length];
        int res = 0;
        for (int v = 0; v < graph.length; v++) {
            int row = v / cols;
            int col = v % cols;
            if (!visited[v] && grid[row][col] == 1) {
                res = Math.max(dfs(v), res);
            }
        }
        return res;
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : graph[v]) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }

    private Set<Integer>[] constructGraph() {
        return null;
    }
}
