package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class BipartiteGraphDetection {
    private Graph g;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartition = true;

    public BipartiteGraphDetection(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.colors = new int[g.getV()];
        Arrays.fill(colors, -1);
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        BipartiteGraphDetection graphDFS = new BipartiteGraphDetection(g);
        System.out.println(graphDFS.isBipartition());
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }
}
