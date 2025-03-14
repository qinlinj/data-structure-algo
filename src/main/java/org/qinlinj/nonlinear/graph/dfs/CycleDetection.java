package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

/**
 * CycleDetection - A class to detect cycles in an undirected graph using DFS.
 * <p>
 * Cycle detection identifies whether a graph contains any cycles (closed paths).
 * A cycle exists when there is a path that starts and ends at the same vertex,
 * with at least three vertices involved.
 * <p>
 * Advantages and Applications:
 * 1. Circuit Analysis: Detecting feedback loops in electrical circuits
 * 2. Dependency Resolution: Finding circular dependencies in software packages
 * 3. Deadlock Detection: Identifying potential deadlocks in resource allocation
 * 4. Network Topology: Ensuring redundant paths in network design
 * 5. Constraint Satisfaction: Validating that constraints don't have circular dependencies
 * <p>
 * Principles and Algorithm:
 * - For undirected graphs, a cycle exists if, during DFS traversal, we find an already
 * visited vertex that is not the parent/previous vertex in our traversal
 * - We track both visited vertices and the previous vertex in our traversal
 * - When we encounter an already visited vertex, we check if it's the previous vertex:
 * - If it IS the previous vertex, this is just backtracking (not a cycle)
 * - If it is NOT the previous vertex, we've found a cycle
 * <p>
 * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V) for the visited array and recursion stack
 */
public class CycleDetection {
    private Graph g;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph g) {
        this.g = g;
        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        CycleDetection graphDFS = new CycleDetection(g);
        System.out.println(graphDFS.hasCycle());
    }

    /**
     * Depth-First Search to detect cycles in the graph.
     * <p>
     * Visual Example for dfs(0, 0):
     * Graph:
     * 0 -- 1
     * |    |
     * 2 -- 3
     * <p>
     * Steps:
     * 1. Mark vertex 0 as visited
     * 2. For neighbors of 0 (1 and 2):
     * a. If not visited, call dfs(neighbor, 0)
     * b. For neighbor 1: dfs(1, 0)
     * - Mark 1 as visited
     * - For neighbors of 1 (0 and 3):
     * * 0 is visited but it's the previous vertex (not a cycle)
     * * 3 is not visited, call dfs(3, 1)
     * ~ Mark 3 as visited
     * ~ For neighbors of 3 (1 and 2):
     * # 1 is visited but it's the previous vertex (not a cycle)
     * # 2 is not visited, call dfs(2, 3)
     * > Mark 2 as visited
     * > For neighbors of 2 (0 and 3):
     * + 0 is visited and NOT the previous vertex (cycle detected!)
     * + hasCycle is set to true
     *
     * @param v    Current vertex being processed
     * @param prev Previous vertex in the DFS traversal
     *             <p>
     *             Time Complexity: O(V + E) - Each vertex and edge is processed once
     *             Space Complexity: O(V) - Due to recursion stack in worst case
     */
    private void dfs(int v, int prev) {
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            } else {

                if (w != prev) {
                    hasCycle = true;
                }
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
