package org.qinlinj.nonlinear.graph.weighted;

import org.qinlinj.nonlinear.graph.dfs.ConnectedComponentsAnalyzer;

import java.util.*;

public class Prim {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        ConnectedComponentsAnalyzer cc = new ConnectedComponentsAnalyzer(g);
        if (cc.getCcCount() > 1) return;

        // Prim
        boolean[] visited = new boolean[g.getV()];

        visited[0] = true;

        for (int i = 0; i < g.getV() - 1; i++) { // O(V)
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < g.getV(); v++) { // O(V)
                if (visited[v]) {
                    for (int w : g.adj(v)) { // O(E)
                        if (!visited[w] && g.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new WeightedEdge(v, w, g.getWeight(v, w));
                        }
                    }
                }
            }
            result.add(minEdge);

            int v = minEdge.getV();
            int w = minEdge.getW();
            int newV = visited[v] ? w : v;
            visited[newV] = true;
        }
    }

    public List<WeightedEdge> getResult() {
        return result;
    }
}
