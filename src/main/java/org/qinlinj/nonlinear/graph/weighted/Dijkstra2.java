package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

public class Dijkstra2 {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    public Dijkstra2(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) { // O(V)
            int curr = pq.poll().v; // O(logV)
            if (visited[curr]) continue;

            visited[curr] = true;

            for (int w : g.adj(curr)) { // O(E)
                if (!visited[w]) {
                    if (distance[curr] + g.getWeight(curr, w) < distance[w]) {
                        distance[w] = distance[curr] + g.getWeight(curr, w);
                        pq.add(new Pair(w, distance[w]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("data/dijkstra.txt");
        Dijkstra2 dijkstra = new Dijkstra2(g, 0);
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

    public boolean isConnected(int v) {
        validateVertex(v);
        return visited[v];
    }
    
    public Collection<Integer> path(int target) {

    }

    private class Pair implements Comparable<Pair> {
        int v;
        int dis;

        public Pair(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Pair o) {
            return dis - o.dis;
        }
    }
}
