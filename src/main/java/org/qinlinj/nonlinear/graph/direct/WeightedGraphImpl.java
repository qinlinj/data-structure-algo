package org.qinlinj.nonlinear.graph.direct;

public class WeightedGraphImpl {
    private GraphImpl g;

    private int[] res;
    private boolean hasCycle = false;

    public WeightedGraphImpl(GraphImpl g) {
        this.g = g;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }
}
