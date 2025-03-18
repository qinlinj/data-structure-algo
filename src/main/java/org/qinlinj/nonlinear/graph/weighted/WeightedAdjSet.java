package org.qinlinj.nonlinear.graph.weighted;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class WeightedAdjSet implements Graph {
    @Override
    public int getE() {
        return 0;
    }

    @Override
    public int getV() {
        return 0;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return false;
    }

    @Override
    public Collection<Integer> adj(int v) {
        return List.of();
    }

    @Override
    public int degree(int v) {
        return 0;
    }
}
