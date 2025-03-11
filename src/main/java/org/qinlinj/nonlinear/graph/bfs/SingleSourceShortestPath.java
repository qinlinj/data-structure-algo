package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * SingleSourceShortestPath - An implementation of the single-source shortest path algorithm using BFS
 * <p>
 * Concept and Principles:
 * - The single-source shortest path algorithm finds the shortest path from a source vertex to all other vertices in an unweighted graph
 * - For unweighted graphs, Breadth-First Search (BFS) is the optimal algorithm for finding shortest paths
 * - BFS explores vertices in layers, ensuring that vertices are discovered in order of their distance from the source
 * <p>
 * Advantages:
 * - Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * - Optimal for unweighted graphs: Guarantees the shortest path in terms of the number of edges
 * - Simple implementation: Using a queue for BFS traversal makes the algorithm intuitive and easy to implement
 * - Distance calculation: Provides distances from the source to all vertices in a single traversal
 * <p>
 * Visual Example:
 * Consider a graph with 7 vertices (0-6) connected as follows:
 *    0 -- 1 -- 3 -- 5
 *    |    |    |
 *    2 -- 4 -- 6
 * <p>
 * Running BFS from source=0:
 * - Level 0: Visit vertex 0, distance[0]=0
 * - Level 1: Visit vertices 1,2, distance[1]=distance[2]=1
 * - Level 2: Visit vertices 3,4, distance[3]=distance[4]=2
 * - Level 3: Visit vertices 5,6, distance[5]=distance[6]=3
 * <p>
 * The shortest path from 0 to 6 would be: 0 -> 1 -> 3 -> 6 or 0 -> 2 -> 4 -> 6 (both with distance 3)
 */
public class SingleSourceShortestPath {
    private Graph g;               // The graph to be traversed
    private boolean[] visited;     // Track visited vertices
    private int[] prevs;           // Store the previous vertex in the shortest path
    private int[] distance;        // Store the distance from source to each vertex
    private int source;            // Source vertex for the shortest path

    /**
     * Constructor initializes the algorithm and runs BFS
     * <p>
     * Time Complexity: O(V + E) - dominated by the BFS algorithm
     *
     * @param g      The graph to traverse
     * @param source The source vertex for finding shortest paths
     */
    public SingleSourceShortestPath(Graph g, int source) {
        this.g = g;
        this.source = source;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        this.distance = new int[g.getV()];
        Arrays.fill(this.prevs, -1);      // Initialize prevs with -1 (no predecessor)
        Arrays.fill(this.distance, -1);   // Initialize distances with -1 (unreachable)

        bfs(source);  // Run BFS from the source vertex
    }

    /**
     * Main method to demonstrate the algorithm
     * <p>
     * Example output:
     * - path(6) might output [0, 1, 3, 6] showing the shortest path from 0 to 6
     * - distance(6) would output 3, indicating the path length is 3 edges
     */
    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourceShortestPath graphBFS = new SingleSourceShortestPath(g, 0);
        System.out.println(graphBFS.path(2));
        System.out.println(graphBFS.distance(2));
    }

    /**
     * Breadth-First Search implementation to find shortest paths
     * <p>
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     * <p>
     * Visual Example for BFS on a graph with source=0:
     * <p>
     * Initial state:
     * - visited = [F,F,F,F,F,F,F]
     * - prevs = [-1,-1,-1,-1,-1,-1,-1]
     * - distance = [-1,-1,-1,-1,-1,-1,-1]
     * - queue = [0]
     * <p>
     * After processing vertex 0:
     * - visited = [T,F,F,F,F,F,F]
     * - prevs = [0,-1,-1,-1,-1,-1,-1]
     * - distance = [0,-1,-1,-1,-1,-1,-1]
     * - queue = [1,2]  (neighbors of 0)
     * <p>
     * After processing vertex 1:
     * - visited = [T,T,F,F,F,F,F]
     * - prevs = [0,0,-1,-1,-1,-1,-1]
     * - distance = [0,1,-1,-1,-1,-1,-1]
     * - queue = [2,3,4]  (added neighbors of 1)
     * <p>
     * And so on until all reachable vertices are visited
     *
     * @param v The source vertex to start BFS from
     */
    private void bfs(int v) {
        if (g == null) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // Initialize the source vertex
        prevs[v] = v;    // Source is its own predecessor for convenience
        distance[v] = 0; // Distance from source to itself is 0

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // Record the previous vertex in the shortest path
                    prevs[w] = curr;
                    // Set distance to w as distance to current vertex + 1
                    distance[w] = distance[curr] + 1;

                    /* Visual Example of BFS Step:
                     * If curr = 1 and w = 3:
                     * - Mark 3 as visited
                     * - Set prevs[3] = 1 (showing the path from source to 3 goes through 1)
                     * - Set distance[3] = distance[1] + 1 = 2 (indicating 3 is at distance 2 from source)
                     */
                }
            }
        }
    }

    /**
     * Checks if there is a path from the source to the target vertex
     * <p>
     * Time Complexity: O(1) - constant time lookup
     *
     * @param target The vertex to check connectivity to
     * @return true if target is reachable from source, false otherwise
     */
    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    /**
     * Validates that a vertex is within the graph's bounds
     * <p>
     * Time Complexity: O(1) - constant time operation
     *
     * @param v The vertex to validate
     * @throws IllegalArgumentException if vertex is out of bounds
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {  // Fixed the condition from && to ||
            throw new IllegalArgumentException("Vertex is invalid, out of range");
        }
    }

    /**
     * Finds the shortest path from source to target
     * <p>
     * Time Complexity: O(V) in worst case (path could include all vertices)
     * <p>
     * Visual Example:
     * For a path from 0 to 6 in our example graph:
     * - Start with target = 6
     * - prevs[6] might be 3, so add 6 to path and set target = 3
     * - prevs[3] might be 1, so add 3 to path and set target = 1
     * - prevs[1] = 0, so add 1 to path and set target = 0
     * - target == source, so add 0 to path
     * - After reversing: path = [0,1,3,6]
     *
     * @param target The destination vertex
     * @return List of vertices forming the shortest path from source to target
     */
    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        // 1. If there's no path from source to target, return empty list
        if (!isConnected(target)) {
            return res;
        }
        // 2. Reconstruct the path by following prevs backward from target
        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        // 3. Reverse the path to get correct order from source to target
        Collections.reverse(res);

        return res;
    }

    /**
     * Returns the shortest distance from source to target
     * <p>
     * Time Complexity: O(1) - constant time lookup in the distance array
     * <p>
     * Example:
     * - distance[6] = 3 means the shortest path from vertex 0 to vertex 6 has 3 edges
     *
     * @param target The destination vertex
     * @return The length of the shortest path, or -1 if unreachable
     */
    public int distance(int target) {
        validateVertex(target);
        return distance[target];
    }
}
