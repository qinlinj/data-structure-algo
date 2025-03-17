package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class TopologySortDFS {
    private Graph g;
    private boolean hasCycle = false;

    private boolean[] visited;
    private boolean[] isOnPath;

    private int[] res;
    private int index;

    public TopologySortDFS(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        this.res = new int[g.getV()];
        this.index = this.res.length - 1;

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);
        TopologySortDFS graphDFS = new TopologySortDFS(g);
        System.out.println(graphDFS.isHasCycle());
        System.out.println(Arrays.toString(graphDFS.getRes()));
    }

    private boolean dfs(int v) {
        visited[v] = true;
        isOnPath[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w)) return true;
            } else {
                if (isOnPath[w]) return true;
            }
        }
        isOnPath[v] = false;
        res[index--] = v;
        return false;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }
}
