package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class SingleSourcePath {
    private Graph g;
    private boolean[] visited;
    private int[] prevs;

    private int source;

    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        Arrays.fill(this.prevs, -1);

        bfs(source);
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourcePath graphBFS = new SingleSourcePath(g, 0);
        System.out.println(graphBFS.path(6));
    }

    private void bfs(int source) {
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("illegal vertex，out of scope");
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

