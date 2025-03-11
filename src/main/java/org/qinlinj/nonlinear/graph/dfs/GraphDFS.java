package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * GraphDFS - An implementation of iterative Depth-First Search for graph traversal
 *
 * Concept and Principles:
 * - Depth-First Search (DFS) is an algorithm for traversing or searching tree or graph data structures
 * - It explores as far as possible along each branch before backtracking
 * - This implementation uses an iterative approach with a stack instead of recursion
 * - For disconnected graphs, the algorithm ensures all vertices are visited by initiating DFS from each unvisited vertex
 *
 * Advantages:
 * - Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * - Space Complexity: O(V) for storing visited status and the stack
 * - Complete: Guaranteed to visit all vertices reachable from the starting vertices
 * - Can handle disconnected graphs by running DFS from each unvisited vertex
 *
 * Visual Example:
 * Consider a graph:
 *    1 --- 2 --- 3
 *    |     |
 *    4 --- 5     6
 *
 * DFS traversal starting from vertex 1 might proceed as:
 * - Visit 1, push neighbors (2,4) onto stack
 * - Visit 4 (top of stack), push neighbor 5 onto stack
 * - Visit 5, push neighbor 2 onto stack
 * - Visit 2, push neighbor 3 onto stack
 * - Visit 3, no unvisited neighbors
 * - Stack is empty, traversal of this component complete
 * - Since vertex 6 is unvisited and disconnected, start a new DFS from 6
 *
 * Result: [1, 4, 5, 2, 3, 6]
 */
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

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.getRes());
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

    public List<Integer> getRes() {
        return res;
    }
}
