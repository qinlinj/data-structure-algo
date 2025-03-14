package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

public class ConnectedComponents {
    private Graph g;

    private boolean[] visited;

    private int ccCount = 0;

    public ConnectedComponents(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                ccCount++;
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }
}
