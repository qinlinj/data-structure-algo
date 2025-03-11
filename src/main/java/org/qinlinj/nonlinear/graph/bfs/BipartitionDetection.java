package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class BipartitionDetection {
    private Graph g;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartition = true;

    public BipartitionDetection(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.colors = new int[g.getV()];
        Arrays.fill(this.colors, -1);

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    private boolean bfs(int v) {
    }
}
