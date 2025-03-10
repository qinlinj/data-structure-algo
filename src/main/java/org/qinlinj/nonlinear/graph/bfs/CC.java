package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class CC {
    private Graph g;
    private int[] visited;
    private int ccCount;

    public CC(Graph g) {
        this.g = g;

        this.visited = new int[g.getV()];
        Arrays.fill(visited, -1);

        for (int v = 0; v < g.getV(); v++) {
            if (visited[v] == -1) {
                ccCount++;
                bfs(v, ccCount);
            }
        }
    }

    private void bfs(int v, int ccId) {
        if (g == null) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = ccId;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccId;
                }
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[ccCount];

        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < g.getV(); v++) {
            int cc = visited[v];
            res[cc - 1].add(v);
        }

        return res;
    }

    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return visited[v] == visited[w];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
        }
    }
}
