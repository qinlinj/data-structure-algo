package org.qinlinj.nonlinear.graph.floodfill;

import org.qinlinj.nonlinear.graph.Graph;

public class ConnectedComponentsMaxCount {
    private Graph g;

    private boolean[] visited;
    private int maxVertexNum = 0;

    public ConnectedComponentsMaxCount(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                maxVertexNum = Math.max(dfs(v), maxVertexNum);
            }
        }
    }

    private int dfs(int v) {
    }
}
