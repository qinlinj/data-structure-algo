package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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


}
