package org.qinlinj.nonlinear.graph.weighted;

import org.qinlinj.nonlinear.graph.dfs.ConnectedComponentsAnalyzer;

import java.util.*;

public class Prim1 {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim1(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        ConnectedComponentsAnalyzer cc = new ConnectedComponentsAnalyzer(g);
        if (cc.getCcCount() > 1) return;

        // Prim
        boolean[] visited = new boolean[g.getV()];

        visited[0] = true;

        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();

        for (int w : g.adj(0)) {
            pq.add(new WeightedEdge(0, w, g.getWeight(0, w)));
        }

        while (!pq.isEmpty()) { // O(E)
            WeightedEdge minEdge = pq.poll(); // O(logE)
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                continue;
            }

            result.add(minEdge);

            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;

            for (int w : g.adj(newV)) {
                if (!visited[w]) {
                    pq.add(new WeightedEdge(newV, w, g.getWeight(newV, w)));
                }
            }
        }

    }

    public static void main(String[] args) {
        WeightedAdjSet adjSet = new WeightedAdjSet("data/prim.txt");
        Prim1 prim = new Prim1(adjSet);

        List<WeightedEdge> res = prim.getResult();
        for (WeightedEdge edge : res) {
            System.out.println(edge);
        }
    }

    public List<WeightedEdge> getResult() {
        return result;
    }
}
