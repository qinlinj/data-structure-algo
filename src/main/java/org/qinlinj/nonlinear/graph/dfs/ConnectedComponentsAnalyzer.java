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

    private void dfs(int v, int ccCount) {
    }
}
