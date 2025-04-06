package org.qinlinj.nonlinear.graph.direct;

import java.util.*;

// @formatter:off
/**
 * Topological Sort using Breadth-First Search (Kahn's Algorithm)
 *
 * Concept and Principles:
 * - Topological sorting is an ordering of vertices in a directed acyclic graph (DAG)
 *   such that for every directed edge (u,v), vertex u comes before vertex v in the ordering.
 * - This implementation uses Kahn's algorithm which is based on BFS:
 *   1. Calculate in-degree (number of incoming edges) for each vertex
 *   2. Enqueue all vertices with in-degree of 0 (no dependencies)
 *   3. While queue is not empty:
 *      a. Dequeue a vertex and add it to result
 *      b. Reduce in-degree of all its neighbors by 1
 *      c. If any neighbor's in-degree becomes 0, enqueue it
 *   4. If the result doesn't include all vertices, the graph has a cycle
 *
 * Advantages:
 * - Efficient: O(V+E) time complexity where V is number of vertices and E is number of edges
 * - Intuitive: Process vertices with no dependencies first, then remove their outgoing edges
 * - Cycle detection: Can detect if the graph contains cycles (topological sort only works on DAGs)
 * - Flexible: Can handle disconnected graphs naturally
 *
 * Visualization Example:
 * Consider a directed graph representing course prerequisites:
 *
 *    0 --> 1 --> 3
 *    |     |
 *    v     v
 *    2 --> 4
 *
 * Initial in-degrees: [0,1,1,1,2]
 * Initial queue: [0] (vertices with in-degree 0)
 *
 * Steps:
 * 1. Dequeue 0, add to result: res=[0]
 *    - Reduce in-degree of neighbors 1 and 2: [0,0,0,1,2]
 *    - Add 1, 2 to queue: queue=[1,2]
 * 2. Dequeue 1, add to result: res=[0,1]
 *    - Reduce in-degree of neighbors 3 and 4: [0,0,0,0,1]
 *    - Add 3 to queue: queue=[2,3]
 * 3. Dequeue 2, add to result: res=[0,1,2]
 *    - Reduce in-degree of neighbor 4: [0,0,0,0,0]
 *    - Add 4 to queue: queue=[3,4]
 * 4. Dequeue 3, add to result: res=[0,1,2,3]
 *    - No neighbors to process
 * 5. Dequeue 4, add to result: res=[0,1,2,3,4]
 *    - No neighbors to process
 *
 * Final result: [0,1,2,3,4] - A valid topological ordering
 */
public class TopologySortBFS {
    private GraphImpl g;

    private int[] res;
    private boolean hasCycle = false;

    public TopologySortBFS(GraphImpl g) {
        if (!g.isDirected()) {
            throw new IllegalArgumentException("error");
        }
        this.g = g;

        int[] indegrees = new int[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            indegrees[v] = g.indegree(v);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < g.getV(); v++) {
            if (indegrees[v] == 0) {
                queue.add(v);
            }
        }

        this.res = new int[g.getV()];
        int index = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            res[index++] = v;
            for (int w : g.adj(v)) {
                indegrees[w]--;
                if (indegrees[w] == 0) {
                    queue.add(w);
                }
            }
        }
        if (index != g.getV()) {
            hasCycle = true;
        }
    }

    public static void main(String[] args) {
        GraphImpl g = new GraphImpl("data/directedgraph-dfs.txt", true);
        TopologySortBFS bfs = new TopologySortBFS(g);
        System.out.println(bfs.isHasCycle());
        System.out.println(Arrays.toString(bfs.getRes()));
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int[] getRes() {
        return res;
    }
}
