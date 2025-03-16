package org.qinlinj.nonlinear.graph.floodfill;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

public class ConnectedComponentsMaxCount {
    private Graph g;

    private boolean[] visited;
    private int maxVertexNum = 0;

    public ConnectedComponentsMaxCount(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                maxVertexNum = Math.max(dfs(v), maxVertexNum);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        ConnectedComponentsMaxCount graphDFS = new ConnectedComponentsMaxCount(g);
        System.out.println(graphDFS.getMaxVertexNum());
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }

    public int getMaxVertexNum() {
        return maxVertexNum;
    }
}
