package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

public class SingleSourcePath {
    private Graph g;

    private int source;

    private boolean[] visited;
    private int[] prevs;

    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;

        this.visited = new boolean[g.getV()];
        prevs = new int[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }
        dfs(source, source);
    }

    private void dfs(int v, int prev) {
        visited[v] = true;
        prevs[v] = prev;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
        }
    }
}
