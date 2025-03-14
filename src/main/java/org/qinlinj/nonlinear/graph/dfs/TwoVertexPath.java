package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class TwoVertexPath {
    private Graph g;

    private int source;
    private int target;

    private boolean[] visited;
    private int[] prevs;

    private List<Integer> res;

    public TwoVertexPath(Graph g, int source, int target) {
        this.g = g;
        this.source = source;
        this.target = target;

        this.res = new ArrayList<>();

        this.visited = new boolean[g.getV()];
        prevs = new int[g.getV()];

        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }

        dfs(source, source);

        path();
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        TwoVertexPath graphDFS = new TwoVertexPath(g, 0, 6);
        System.out.println(graphDFS.getRes());
    }

    private boolean dfs(int v, int prev) {
        visited[v] = true;
        prevs[v] = prev;
        if (v == target) return true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
        }
    }

    private List<Integer> path() {
        if (!isConnected()) {
            return res;
        }

        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];
        }
        res.add(source);

        Collections.reverse(res);

        return res;
    }

    public List<Integer> getRes() {
        return res;
    }
}
