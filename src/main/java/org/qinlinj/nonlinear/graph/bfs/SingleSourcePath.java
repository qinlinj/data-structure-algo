package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class SingleSourcePath {
    private Graph g;
    private boolean[] visited;
    private int[] prevs;

    private int source;

    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        Arrays.fill(this.prevs, -1);

        bfs(source);
    }

    private void bfs(int source) {
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("illegal vertex，out of scope");
        }
    }
}

