package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class ConnectedComponentsAnalyzer {
    private Graph g;

    private int[] visited;

    private int ccCount = 0;

    public ConnectedComponentsAnalyzer(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new int[g.getV()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < g.getV(); v++) {
            if (visited[v] == -1) {
                ccCount++;
                dfs(v, ccCount);
            }
        }
    }

    private void dfs(int v, int ccId) {
        visited[v] = ccId;
        for (int w : g.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccId);
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[ccCount];
        // Arrays.fill(res, new ArrayList<>());
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < g.getV(); v++) {
            int cc = visited[v];
            res[cc - 1].add(v);
        }

        return res;
    }

    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return visited[v] == visited[w];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
        }
    }
}
