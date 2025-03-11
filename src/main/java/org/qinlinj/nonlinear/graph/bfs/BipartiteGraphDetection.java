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
    private Graph g;               // The graph to be analyzed
    private boolean[] visited;     // Track visited vertices
    private int[] colors;          // Store the color of each vertex (-1: no color, 0: red, 1: blue)
    private boolean isBipartite = true;  // Flag indicating if graph is bipartite

    /**
     * Constructor runs the bipartite detection algorithm on the graph
     *
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     *
     * @param g The graph to analyze for bipartiteness
     */
    public BipartiteGraphDetection(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.colors = new int[g.getV()];
        Arrays.fill(this.colors, -1);  // Initialize all vertices with no color (-1)

        // For disconnected graphs, check each component
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    /**
     * Main method to demonstrate the bipartite detection algorithm
     *
     * Example output:
     * - isBipartite() will output true if the graph is bipartite, false otherwise
     */
    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        BipartiteGraphDetection detector = new BipartiteGraphDetection(g);
        System.out.println(detector.isBipartite());
    }

    /**
     * Modified BFS implementation that colors vertices and detects bipartite violations
     *
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     *
     * Visual Example for bipartite detection using BFS:
     * Consider this small graph:
     *    a --- b
     *    |     |
     *    c --- d
     *
     * Initial state (starting BFS from vertex 'a'):
     * - visited = [T,F,F,F] (assuming a=0, b=1, c=2, d=3)
     * - colors = [0,-1,-1,-1] (a is colored red (0))
     * - queue = [a]
     *
     * After processing vertex 'a':
     * - Neighbors 'b' and 'c' are added to queue
     * - visited = [T,T,T,F]
     * - colors = [0,1,1,-1] (b and c are colored blue (1))
     * - queue = [b,c]
     *
     * After processing vertex 'b':
     * - Neighbor 'd' is added to queue
     * - visited = [T,T,T,T]
     * - colors = [0,1,1,0] (d is colored red (0), opposite of b's color)
     * - queue = [c,d]
     *
     * Processing vertex 'c':
     * - Looking at neighbor 'a': already visited and has different color (good)
     * - Looking at neighbor 'd': already visited and has different color (good)
     *
     * Processing vertex 'd':
     * - Looking at neighbor 'b': already visited and has different color (good)
     * - Looking at neighbor 'c': already visited and has different color (good)
     *
     * All vertices can be properly colored, so the graph is bipartite.
     *
     * @param v The source vertex to start BFS from
     * @return true if the component containing v is bipartite, false otherwise
     */
    private boolean bfs(int v) {
        if (g == null) return true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        colors[v] = 0;  // Color the starting vertex as red (0)

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                // If w is not yet visited, color it with the opposite color of curr
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // Color vertex w with the opposite color of curr
                    // If curr is red (0), w is blue (1), and vice versa
                    colors[w] = 1 - colors[curr];

                    /* Visual example of coloring:
                     * If curr = a with color 0 (red) and w = b:
                     * - colors[b] = 1 - colors[a] = 1 - 0 = 1 (blue)
                     *
                     * If curr = b with color 1 (blue) and w = d:
                     * - colors[d] = 1 - colors[b] = 1 - 1 = 0 (red)
                     */
                } else if (colors[w] == colors[curr]) {
                    // If w has already been visited and has the same color as curr,
                    // then the graph is not bipartite
                    /* Visual example of bipartite violation:
                     * Consider an odd cycle:
                     *    a --- b
                     *    |     |
                     *    c --- d
                     *    |
                     *    e --- a
                     *
                     * If we've colored a=0, c=1, e=0,
                     * When we process edge e-a, we'll find a conflict:
                     * - curr = e with color 0
                     * - w = a also with color 0
                     * - This is a violation, so return false
                     */
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns whether the graph is bipartite
     *
     * Time Complexity: O(1) - constant time lookup of pre-computed result
     *
     * @return true if the graph is bipartite, false otherwise
     */
    public boolean isBipartite() {
        return isBipartite;
    }
}
