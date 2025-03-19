package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

public class Dijkstra {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    public Dijkstra(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];

        while (true) {
            int curDis = Integer.MAX_VALUE;
            int curr = -1;
            for (int v = 0; v < g.getV(); v++) { // O(V)
                if (!visited[v] && distance[v] < curDis) {
                    curDis = distance[v];
                    curr = v;
                }
            }
            if (curr == -1) break;

            visited[curr] = true;

            for (int w : g.adj(curr)) { // O(E)
                if (!visited[w]) {
                    if (distance[curr] + g.getWeight(curr, w) < distance[w]) {
                        distance[w] = distance[curr] + g.getWeight(curr, w);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("data/dijkstra.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        System.out.println(dijkstra.minDistanceTo(1));
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
