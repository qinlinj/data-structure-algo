package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

public class Dijkstra {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    public Dijkstra(WeightedAdjSet g) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];

        while (true) {
            
        }
    }

    public static void main(String[] args) {
    }

    public int minDistanceTo(int v) {
        validateVertex(v);
        return distance[v];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {
            throw new IllegalArgumentException(String.format("Vertex %d invalid", v));
        }
    }
}
