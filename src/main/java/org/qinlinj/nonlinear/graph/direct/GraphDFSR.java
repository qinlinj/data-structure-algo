package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class GraphDFSR {
    private Graph g;

    private List<Integer> res;
    private boolean[] visited;

    public GraphDFSR(Graph g) {
        this.g = g;

        if (g == null) return;

        this.res = new ArrayList<>();
        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
    }
}
