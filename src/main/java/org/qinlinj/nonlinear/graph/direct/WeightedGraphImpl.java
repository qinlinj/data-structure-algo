package org.qinlinj.nonlinear.graph.direct;

public class WeightedGraphImpl {
    private GraphImpl g;

    private int[] res;
    private boolean hasCycle = false;

    public WeightedGraphImpl(GraphImpl g) {
        if (!g.isDirected()) {
            throw new IllegalArgumentException("error");
        }
        this.g = g;

        int[] indegrees = new int[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            indegrees[v] = g.indegree(v);
        }
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }
}
