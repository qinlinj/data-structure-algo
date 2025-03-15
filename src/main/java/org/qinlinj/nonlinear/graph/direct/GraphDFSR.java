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

    public static void main(String[] args) {
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);
        GraphDFSR graphDFS = new GraphDFSR(g);
        System.out.println(graphDFS.getRes());
    }

    private void dfs(int v) {
        res.add(v);
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }
}
