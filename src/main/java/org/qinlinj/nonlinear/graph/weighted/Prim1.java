package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

public class Prim1 {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim1(WeightedAdjSet g) {
        this.g = g;
    }

    public List<WeightedEdge> getResult() {
        return result;
    }
}
