package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

public class TopologySortDFS {
    private Graph g;
    private boolean hasCycle = false;

    private boolean[] visited;
    private boolean[] isOnPath;

    private int[] res;
    private int index;

    public TopologySortDFS(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        this.res = new int[g.getV()];
        this.index = this.res.length - 1;

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {

            }
        }
    }
}
