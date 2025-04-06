package org.qinlinj.nonlinear.graph.direct;

import java.util.*;
import org.qinlinj.nonlinear.graph.Graph;

/**
 * Topological Sort using Depth-First Search
 * <p>
 * Concept and Principles:
 * - Topological sorting is an ordering of vertices in a directed acyclic graph (DAG)
 * such that for every directed edge (u,v), vertex u comes before vertex v in the ordering.
 * - This implementation uses DFS-based approach:
 * 1. Perform DFS traversal of the graph
 * 2. When backtracking from a vertex (after exploring all its neighbors),
 * add the vertex to the result
 * 3. Reverse the result to get the topological ordering
 * 4. Detect cycles during DFS to ensure the graph is a DAG
 * <p>
 * Advantages:
 * - Efficient: O(V+E) time complexity where V is number of vertices and E is number of edges
 * - Intuitive: Uses the natural recursion of DFS to determine dependencies
 * - Memory efficient: Uses O(V) extra space for tracking visited vertices
 * - Integrated cycle detection: Can detect if the graph contains cycles (topological sort only works on DAGs)
 * <p>
 * Visualization Example:
 * Consider a directed graph representing build dependencies:
 * <p>
 * 0 --> 1 --> 3
 * |     |
 * v     v
 * 2 --> 4
 * <p>
 * DFS execution:
 * 1. Start DFS at vertex 0:
 * - Mark 0 as visited and on current path
 * - Explore neighbor 1:
 * - Mark 1 as visited and on current path
 * - Explore neighbor 3:
 * - Mark 3 as visited and on current path
 * - No neighbors to explore
 * - Mark 3 as not on current path
 * - Add 3 to result: res=[3]
 * - Explore neighbor 4:
 * - Mark 4 as visited and on current path
 * - No neighbors to explore
 * - Mark 4 as not on current path
 * - Add 4 to result: res=[3,4]
 * - Mark 1 as not on current path
 * - Add 1 to result: res=[3,4,1]
 * - Explore neighbor 2:
 * - Mark 2 as visited and on current path
 * - Explore neighbor 4 (already visited, not on current path)
 * - Mark 2 as not on current path
 * - Add 2 to result: res=[3,4,1,2]
 * - Mark 0 as not on current path
 * - Add 0 to result: res=[3,4,1,2,0]
 * <p>
 * 2. Final result (after reversing): [0,2,1,4,3] - A valid topological ordering
 * <p>
 * In this implementation, the result is built in reverse order during DFS,
 * so no explicit reversal step is needed.
 */
public class TopologySortDFS {
    private Graph g;
    private boolean hasCycle = false;

    private boolean[] visited;
    private boolean[] isOnPath;

    private int[] res;
    private int index;

    public TopologySortDFS(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];
        this.res = new int[g.getV()];
        this.index = this.res.length - 1;

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);
        TopologySortDFS graphDFS = new TopologySortDFS(g);
        System.out.println(graphDFS.isHasCycle());
        System.out.println(Arrays.toString(graphDFS.getRes()));
    }

    private boolean dfs(int v) {
        visited[v] = true;
        isOnPath[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w)) return true;
            } else {
                if (isOnPath[w]) return true;
            }
        }
        isOnPath[v] = false;
        res[index--] = v;
        return false;
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }
}
