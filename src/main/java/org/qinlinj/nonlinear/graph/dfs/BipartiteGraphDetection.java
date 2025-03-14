package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * BipartitionDetection - An implementation to determine if a graph is bipartite using DFS
 *
 * Concept and Principles:
 * - A bipartite graph is a graph whose vertices can be divided into two disjoint sets (colors)
 *   such that every edge connects vertices from different sets
 * - This algorithm uses a coloring approach with DFS to verify if the graph is bipartite
 * - If we can color all vertices using only two colors, with no adjacent vertices having the same color,
 *   then the graph is bipartite
 * - For disconnected graphs, we need to verify each connected component separately
 *
 * Advantages of DFS approach:
 * - Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * - Simpler recursive implementation compared to BFS
 * - Can process disconnected graphs by checking each component
 * - Early termination: Stops as soon as a bipartite violation is detected
 *
 * Applications:
 * - Matching problems (e.g., job assignments)
 * - Scheduling conflicts
 * - Chemical compound analysis
 * - Network flow optimization
 *
 * Visual Example:
 * Bipartite graph example:
 *    0 --- 1
 *    |     |
 *    2 --- 3
 *
 * Can be colored as:
 *    0(R) --- 1(B)
 *    |         |
 *    2(B) --- 3(R)
 *
 * Non-bipartite graph example (odd-length cycle):
 *    0 --- 1
 *    |     |
 *    2 --- 3
 *    |
 *    4 --- 0
 *
 * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
 * Space Complexity: O(V) for the visited array, colors array, and recursion stack
 */
public class BipartiteGraphDetection {
    private Graph g;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartition = true;

    /**
     * Constructor runs the bipartite detection algorithm on the graph
     *
     * Algorithm steps:
     * 1. Initialize visited array and colors array
     * 2. For each unvisited vertex, run DFS
     * 3. If any DFS call returns false, the graph is not bipartite
     *
     * @param g The graph to analyze for bipartiteness
     *
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     * Space Complexity: O(V) for the visited array, colors array, and recursion stack
     */
    public BipartiteGraphDetection(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.colors = new int[g.getV()];
        Arrays.fill(colors, -1);
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        BipartiteGraphDetection graphDFS = new BipartiteGraphDetection(g);
        System.out.println(graphDFS.isBipartition());
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }
}
