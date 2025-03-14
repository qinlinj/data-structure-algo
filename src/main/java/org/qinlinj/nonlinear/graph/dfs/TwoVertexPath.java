package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class TwoVertexPath {
    private Graph g;

    private int source;
    private int target;

    private boolean[] visited;
    private int[] prevs;

    private List<Integer> res;

    public TwoVertexPath(Graph g, int source, int target) {
        this.g = g;
        this.source = source;
        this.target = target;

        this.res = new ArrayList<>();

        this.visited = new boolean[g.getV()];
        prevs = new int[g.getV()];

        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }

        dfs(source, source);

        path();
    }

    private void path() {
    }

    private void dfs(int source, int source1) {

    }
}
