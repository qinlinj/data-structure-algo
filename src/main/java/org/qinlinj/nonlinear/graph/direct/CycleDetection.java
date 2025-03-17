package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

public class CycleDetection {
    private Graph g;
    private boolean hasCycle = false;

    private boolean[] visited;
    private boolean[] isOnPath;

    public CycleDetection(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v) {
        visited[v] = true;
        isOnPath[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w)) return true;
            } else {
                if (isOnPath[w]) return true;
            }
        }
        isOnPath[v] = false;
        return false;
    }
}
