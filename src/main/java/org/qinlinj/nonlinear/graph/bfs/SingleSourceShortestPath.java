package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class SingleSourceShortestPath {
    private Graph g;
    private boolean[] visited;
    private int[] prevs;
    private int[] distance;

    private int source;

    public SingleSourceShortestPath(Graph g, int source) {
        this.g = g;
        this.source = source;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        this.distance = new int[g.getV()];
        Arrays.fill(this.prevs, -1);
        Arrays.fill(this.distance, -1);

        bfs(source);
    }

    private void bfs(int v) {

    }
}
