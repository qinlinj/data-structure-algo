package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

public class TopologySortDFS {
    private Graph g;
    private boolean hasCycle = false;

    private boolean[] visited;
    private boolean[] isOnPath;

    private int[] res;
    private int index;

    public TopologySortDFS(Graph g) {
        this.g = g;
    }
}
