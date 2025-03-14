package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

public class CycleDetection {
    private Graph g;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph g) {
        this.g = g;
    }
}
