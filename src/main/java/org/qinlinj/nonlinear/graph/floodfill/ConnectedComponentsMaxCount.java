package org.qinlinj.nonlinear.graph.floodfill;

import org.qinlinj.nonlinear.graph.Graph;

public class ConnectedComponentsMaxCount {
    private Graph g;

    private boolean[] visited;
    private int maxVertexNum = 0;

    public ConnectedComponentsMaxCount(Graph g) {
        this.g = g;
    }
}
