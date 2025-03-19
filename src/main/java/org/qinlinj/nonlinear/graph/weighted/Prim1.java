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
    }

    public List<WeightedEdge> getResult() {
        return result;
    }
}
