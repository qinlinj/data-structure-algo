package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class GraphDFS {
    private Graph g;

    private List<Integer> res;
    private boolean[] visited;

    public GraphDFS(Graph g) {
        this.g = g;
        if (g == null) return;

        this.res = new ArrayList<>();
        this.visited = new boolean[g.getV()];
        // 遍历图中每个顶点
        for (int v = 0; v < g.getV(); v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        visited[v] = true;

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            res.add(curr);

            for (int w : g.adj(curr)) { // 升序排列
                if (!visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                }
            }
        }
    }
}
