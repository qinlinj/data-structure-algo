package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

public class Dijkstra2 {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    private int[] prevs;

    public Dijkstra2(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];
        prevs = new int[g.getV()];
        Arrays.fill(prevs, -1);

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
                        prevs[w] = curr;
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
        List<Integer> res = new ArrayList<>();
        if (!isConnected(target)) {
            return res;
        }
        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        Collections.reverse(res);

        return res;
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
