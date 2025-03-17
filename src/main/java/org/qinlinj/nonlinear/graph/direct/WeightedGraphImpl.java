package org.qinlinj.nonlinear.graph.direct;

import java.util.*;

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

        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < g.getV(); v++) {
            if (indegrees[v] == 0) {
                queue.add(v);
            }
        }

        this.res = new int[g.getV()];
        int index = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            res[index++] = v;
            for (int w : g.adj(v)) {
                indegrees[w]--;
                if (indegrees[w] == 0) {
                    queue.add(w);
                }
            }
        }
        if (index != g.getV()) {
            hasCycle = true;
        }
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }
}
