package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class SingleSourcePath {
    private Graph g;

    private int source;

    private boolean[] visited;
    private int[] prevs;

    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;

        this.visited = new boolean[g.getV()];
        prevs = new int[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }
        dfs(source, source);
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourcePath graphDFS = new SingleSourcePath(g, 0);
        System.out.println(graphDFS.path(6));
    }

    private void dfs(int v, int prev) {
        visited[v] = true;
        prevs[v] = prev;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
        }
    }

    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        if (!isConnected(target)) {
            return res;
        }
        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        Collections.reverse(res);

        return res;
    }
}
