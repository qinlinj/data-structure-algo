package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.Arrays;

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

    
}
