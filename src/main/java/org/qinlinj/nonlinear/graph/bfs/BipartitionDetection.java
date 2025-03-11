package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class BipartitionDetection {
    private Graph g;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartition = true;

    public BipartitionDetection(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.colors = new int[g.getV()];
        Arrays.fill(this.colors, -1);

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        BipartitionDetection graphBFS = new BipartitionDetection(g);
        System.out.println(graphBFS.isBipartition());
    }

    private boolean bfs(int v) {
        if (g == null) return true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        colors[v] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[curr];
                } else if (colors[w] == colors[curr]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }
}
