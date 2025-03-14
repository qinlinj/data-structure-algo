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

    private void dfs(int source, int source1) {
    }
}
