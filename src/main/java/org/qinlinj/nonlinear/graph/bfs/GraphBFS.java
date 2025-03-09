package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphBFS {
    private Graph g;
    private boolean[] visited;
    private List<Integer> res;

    public GraphBFS(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.res = new ArrayList<>();

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) bfs(v);
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println(graphBFS.getRes());
    }

    private void bfs(int v) {
        if (g == null) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res.add(curr);

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }
}

