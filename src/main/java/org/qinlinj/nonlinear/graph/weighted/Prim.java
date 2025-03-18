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

            }
            result.add(minEdge);
            
        }
    }
}
