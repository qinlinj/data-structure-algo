package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class WeightedGraphImpl implements Graph {
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

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int getV() {
        return V;
    }

    @Override
    public int getE() {
        return E;
    }
    
}
