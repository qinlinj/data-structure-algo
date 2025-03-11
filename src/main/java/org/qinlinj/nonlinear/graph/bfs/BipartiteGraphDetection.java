package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * BipartiteGraphDetection - An implementation to determine if a graph is bipartite using BFS
 *
 * Concept and Principles:
 * - A bipartite graph is a graph whose vertices can be divided into two disjoint sets (colors)
 *   such that every edge connects vertices from different sets
 * - This algorithm uses a coloring approach with BFS to verify if the graph is bipartite
 * - If we can color all vertices using only two colors, with no adjacent vertices having the same color,
 *   then the graph is bipartite
 * - For disconnected graphs, we need to verify each connected component separately
 *
 * Advantages:
 * - Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * - Can process disconnected graphs by checking each component
 * - Early termination: Stops as soon as a bipartite violation is detected
 * - Applications: Useful in matching problems, scheduling, and many other real-world scenarios
 *
 * Visual Example:
 * Bipartite graph example:
 *    a --- b
 *    |     |
 *    c --- d
 *
 * Can be colored as:
 *    a(R) --- b(B)
 *    |         |
 *    c(B) --- d(R)
 *
 * Non-bipartite graph example (odd-length cycle):
 *    a --- b
 *    |     |
 *    c --- d
 *    |
 *    e
 *
 * If we try to color this, vertices a, c, and e would need to have different colors,
 * which is impossible with only two colors.
 */
public class BipartiteGraphDetection {
    private Graph g;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartition = true;

    public BipartiteGraphDetection(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.colors = new int[g.getV()];
        Arrays.fill(this.colors, -1);

        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartition = false;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        BipartiteGraphDetection graphBFS = new BipartiteGraphDetection(g);
        System.out.println(graphBFS.isBipartition());
    }

    private boolean bfs(int v) {
        if (g == null) return true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        colors[v] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[curr];
                } else if (colors[w] == colors[curr]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartition() {
        return isBipartition;
    }
}
