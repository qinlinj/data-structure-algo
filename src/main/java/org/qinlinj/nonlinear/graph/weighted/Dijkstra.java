package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

// @formatter:off
/**
 * Dijkstra's Algorithm Implementation
 *
 * Concept and Principles:
 * Dijkstra's algorithm is a greedy algorithm that solves the single-source shortest path problem
 * for a weighted graph with non-negative edge weights. It computes the shortest path from a source
 * vertex to all other vertices in the graph.
 *
 * Advantages of Dijkstra's Algorithm:
 * 1. Optimal Solution: Guarantees the shortest path from the source to all other vertices
 * 2. Efficiency: More efficient than algorithms like Bellman-Ford for graphs with non-negative weights
 * 3. Versatility: Widely used in network routing protocols, GPS navigation, and many other applications
 * 4. Early Termination: Can stop once the destination vertex is reached if only interested in a specific path
 *
 * Limitations:
 * 1. Cannot handle negative edge weights (use Bellman-Ford instead for that scenario)
 * 2. This implementation has O(V²) time complexity; can be improved to O(E log V) with a priority queue
 *
 * Algorithm Steps:
 * 1. Initialize distances from source to all vertices as infinite, and distance to source as 0
 * 2. Initialize all vertices as unvisited
 * 3. Repeat until all vertices are visited:
 *    a. Select the unvisited vertex with the minimum distance value
 *    b. Mark the selected vertex as visited
 *    c. Update distances to all adjacent unvisited vertices if a shorter path is found
 *
 * Detailed Example Visualization:
 * Consider the following weighted graph:
 *
 *     A --- 4 --- B
 *     |           |
 *     2           3
 *     |           |
 *     C --- 1 --- D --- 2 --- E
 *     |                       |
 *     3                       1
 *     |                       |
 *     F ---------- 5 --------- G
 *
 * Starting from vertex A, find the shortest path to all other vertices.
 *
 * Step 1: Initialize
 *   - Set distance[A] = 0, all other distances to infinity
 *   - All vertices are unvisited: {A, B, C, D, E, F, G}
 *   - Current distances: [A:0, B:∞, C:∞, D:∞, E:∞, F:∞, G:∞]
 *
 * Step 2: Select A (min distance vertex)
 *   - Mark A as visited
 *   - Update distances to neighbors of A:
 *     * distance[B] = min(∞, 0+4) = 4
 *     * distance[C] = min(∞, 0+2) = 2
 *   - Visited: {A}
 *   - Current distances: [A:0, B:4, C:2, D:∞, E:∞, F:∞, G:∞]
 *
 * Step 3: Select C (min distance vertex)
 *   - Mark C as visited
 *   - Update distances to neighbors of C:
 *     * distance[D] = min(∞, 2+1) = 3
 *     * distance[F] = min(∞, 2+3) = 5
 *   - Visited: {A, C}
 *   - Current distances: [A:0, B:4, C:2, D:3, E:∞, F:5, G:∞]
 *
 * Step 4: Select D (min distance vertex)
 *   - Mark D as visited
 *   - Update distances to neighbors of D:
 *     * distance[E] = min(∞, 3+2) = 5
 *   - Visited: {A, C, D}
 *   - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:∞]
 *
 * Step 5: Select B (min distance vertex)
 *   - Mark B as visited
 *   - Update distances to neighbors of B:
 *     * distance[D] = min(3, 4+3) = 3 (no change)
 *   - Visited: {A, C, D, B}
 *   - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:∞]
 *
 * Step 6: Select E and F (tied for min distance)
 *   - Let's select E first
 *   - Mark E as visited
 *   - Update distances to neighbors of E:
 *     * distance[G] = min(∞, 5+1) = 6
 *   - Visited: {A, C, D, B, E}
 *   - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 *
 * Step 7: Select F (min distance vertex)
 *   - Mark F as visited
 *   - Update distances to neighbors of F:
 *     * distance[G] = min(6, 5+5) = 6 (no change)
 *   - Visited: {A, C, D, B, E, F}
 *   - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 *
 * Step 8: Select G (last vertex)
 *   - Mark G as visited
 *   - No more unvisited vertices, algorithm terminates
 *   - Visited: {A, C, D, B, E, F, G}
 *   - Final distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 *
 * Result: The shortest distance from A to each vertex is:
 *   - A to A: 0
 *   - A to B: 4
 *   - A to C: 2
 *   - A to D: 3
 *   - A to E: 5
 *   - A to F: 5
 *   - A to G: 6
 *
 * Time Complexity: O(V²) where V is the number of vertices
 *   - The main loop runs at most V times
 *   - Finding the minimum distance vertex takes O(V) time
 *   - Processing each vertex's neighbors takes O(V) time in total
 *
 * Space Complexity: O(V) for the distance and visited arrays
 */
public class Dijkstra {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    /**
     * Constructs a Dijkstra shortest path calculator from a source vertex.
     *
     * @param g The weighted graph
     * @param source The source vertex from which to compute shortest paths
     *
     * Time Complexity: O(V²) where V is the number of vertices
     *   - The main loop runs at most V times
     *   - Finding minimum distance vertex takes O(V) time
     *   - Relaxing edges from each vertex takes O(E) time in total, which is at most O(V²)
     *
     * Space Complexity: O(V) for the distance and visited arrays
     */
    public Dijkstra(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];

        while (true) {
            int curDis = Integer.MAX_VALUE;
            int curr = -1;
            for (int v = 0; v < g.getV(); v++) { // O(V)
                if (!visited[v] && distance[v] < curDis) {
                    curDis = distance[v];
                    curr = v;
                }
            }
            if (curr == -1) break;

            visited[curr] = true;

            for (int w : g.adj(curr)) { // O(E)
                if (!visited[w]) {
                    if (distance[curr] + g.getWeight(curr, w) < distance[w]) {
                        distance[w] = distance[curr] + g.getWeight(curr, w);
                    }
                }
            }
        }
    }

    /**
     * Main method to demonstrate Dijkstra's algorithm.
     *
     * @param args Command line arguments
     *
     * Example Output (depending on the graph in dijkstra.txt):
     * The shortest distance from vertex 0 to vertex 1 is: X
     */
    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("data/dijkstra.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        System.out.println(dijkstra.minDistanceTo(1));
    }

    /**
     * Returns the minimum distance from the source vertex to the specified vertex.
     *
     * @param v The destination vertex
     * @return The shortest distance from source to v, or Integer.MAX_VALUE if no path exists
     *
     * Time Complexity: O(1) - constant time lookup in the distance array
     *
     * Example:
     * Dijkstra dijkstra = new Dijkstra(graph, 0);
     * int shortestPath = dijkstra.minDistanceTo(5);
     * // Returns the shortest distance from vertex 0 to vertex 5
     */
    public int minDistanceTo(int v) {
        validateVertex(v);
        return distance[v];
    }

    /**
     * Validates that the given vertex exists in the graph.
     *
     * @param v Vertex to validate
     * @throws IllegalArgumentException if vertex is invalid
     *
     * Time Complexity: O(1) - constant time operation
     */
    public void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {
            throw new IllegalArgumentException(String.format("Vertex %d invalid", v));
        }
    }
}
