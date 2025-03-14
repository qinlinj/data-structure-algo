package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

public class CycleDetection {
    private Graph g;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph g) {
        this.g = g;
        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int v1) {
    }


}
