package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphBFS {
    private Graph g;
    private boolean[] visited;
    private List<Integer> res;

    public GraphBFS(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.res = new ArrayList<>();

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) bfs(v);
        }
    }
}

