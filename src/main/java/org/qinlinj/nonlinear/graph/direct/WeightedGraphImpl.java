package org.qinlinj.nonlinear.graph.direct;

import java.util.*;

public class WeightedGraphImpl {
    private int V; //
    private int E; //
    private TreeMap<Integer, Integer>[] adj;

    private boolean isDirected;

    private int[] indegrees;
    private int[] outdegrees;

    public WeightedGraphImpl(TreeMap<Integer, Integer>[] adj) {
        this.adj = adj;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("V %d invalid", v));
        }
    }
}
