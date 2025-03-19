package org.qinlinj.nonlinear.graph.weighted;

public class Dijkstra {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    public Dijkstra(WeightedAdjSet g) {
        this.g = g;
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
