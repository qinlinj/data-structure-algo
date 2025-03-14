package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

public class BipartiteGraphDetection {
    private Graph g;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartition = true;

    public BipartiteGraphDetection(Graph g) {
        this.g = g;
    }
}
