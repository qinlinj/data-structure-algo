package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class BipartiteGraphDetection {
    private Graph g;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartition = true;

    public BipartiteGraphDetection(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.colors = new int[g.getV()];
        Arrays.fill(colors, -1);
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int i) {
    }
}
