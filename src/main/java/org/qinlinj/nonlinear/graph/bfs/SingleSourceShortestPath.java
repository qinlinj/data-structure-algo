package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class SingleSourceShortestPath {
    private Graph g;
    private boolean[] visited;
    private int[] prevs;
    private int[] distance;

    private int source;

    public SingleSourceShortestPath(Graph g, int source) {
        this.g = g;
        this.source = source;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        this.distance = new int[g.getV()];
        Arrays.fill(this.prevs, -1);
        Arrays.fill(this.distance, -1);

        bfs(source);
    }

    private void bfs(int v) {

    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("illegal vertex");
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

    public int distance(int target) {
        validateVertex(target);
        return distance[target];
    }

}
