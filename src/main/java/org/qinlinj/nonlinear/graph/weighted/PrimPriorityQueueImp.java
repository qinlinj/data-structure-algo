package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;
import org.qinlinj.nonlinear.graph.dfs.ConnectedComponentsAnalyzer;

// @formatter:off
/**
 * Optimized Prim's Algorithm Implementation
 *
 * Concept and Principles:
 * This is an optimized version of Prim's algorithm that uses a priority queue to efficiently find
 * the minimum weight edge at each step. Like the basic version, it finds the Minimum Spanning Tree (MST)
 * of a connected weighted undirected graph.
 *
 * Key Optimizations Compared to Basic Prim:
 * 1. Priority Queue: Uses a min-heap to automatically keep track of the minimum weight edge
 *    - Eliminates the need to scan all vertices and edges in each iteration
 *    - Reduces time complexity from O(V²) to O(E log E)
 * 2. Cut Property: Only considers crossing edges from visited to unvisited vertices
 *    - Avoids processing unnecessary edges
 * 3. Lazy Deletion: Simply skips edges where both endpoints are already in the MST
 *    - Avoids the overhead of removing outdated edges from the priority queue
 *
 * Algorithm Steps:
 * 1. Start with any vertex (here vertex 0) and mark it as visited
 * 2. Add all edges from the starting vertex to the priority queue
 * 3. While the priority queue is not empty:
 *    a. Extract the minimum weight edge from the priority queue
 *    b. If both endpoints are already visited, skip this edge (lazy deletion)
 *    c. Otherwise, add the edge to the MST
 *    d. Mark the newly added vertex as visited
 *    e. Add all edges from the new vertex to unvisited vertices to the priority queue
 *
 * Detailed Example Visualization:
 * Consider the following graph (same as in the basic Prim example):
 *
 *     A --- 10 --- B
 *     |           /|
 *     |          / |
 *     5         2  7
 *     |        /   |
 *     |       /    |
 *     C --- 3 --- D
 *
 * Step-by-step execution:
 *
 * 1. Start at vertex A (mark A as visited)
 *    - Add edges from A to priority queue: (A-B, 10) and (A-C, 5)
 *    - PQ: [(A-C, 5), (A-B, 10)]
 *    - Visited: {A}
 *    - MST: {}
 *
 * 2. Extract minimum edge (A-C, 5) from PQ
 *    - Edge connects visited A to unvisited C, so add to MST
 *    - Mark C as visited
 *    - Add edges from C to priority queue: (C-D, 3)
 *    - PQ: [(C-D, 3), (A-B, 10)]
 *    - Visited: {A, C}
 *    - MST: {(A-C, 5)}
 *
 * 3. Extract minimum edge (C-D, 3) from PQ
 *    - Edge connects visited C to unvisited D, so add to MST
 *    - Mark D as visited
 *    - Add edges from D to priority queue: (D-B, 2), (D-C, 3)
 *      Note: (D-C, 3) is added even though C is visited (lazy approach)
 *    - PQ: [(D-B, 2), (A-B, 10), (D-C, 3)]
 *    - Visited: {A, C, D}
 *    - MST: {(A-C, 5), (C-D, 3)}
 *
 * 4. Extract minimum edge (D-B, 2) from PQ
 *    - Edge connects visited D to unvisited B, so add to MST
 *    - Mark B as visited
 *    - Add edges from B to priority queue: (B-A, 10), (B-D, 7)
 *      Note: Both are added even though A and D are visited (lazy approach)
 *    - PQ: [(D-C, 3), (A-B, 10), (B-A, 10), (B-D, 7)]
 *    - Visited: {A, C, D, B}
 *    - MST: {(A-C, 5), (C-D, 3), (D-B, 2)}
 *
 * 5. Extract minimum edge (D-C, 3) from PQ
 *    - Both D and C are already visited, so skip this edge
 *    - PQ: [(B-D, 7), (A-B, 10), (B-A, 10)]
 *
 * 6. Continue extracting edges from PQ, but all remaining edges connect
 *    vertices that are already in the MST, so they are all skipped
 *
 * Final MST: {(A-C, 5), (C-D, 3), (D-B, 2)} with total weight 10
 *
 * Time Complexity: O(E log E) or O(E log V)
 * - Each edge is added to the priority queue at most once: O(E)
 * - Each extraction from the priority queue takes O(log E) time
 * - Since E can be at most V², this is effectively O(E log V)
 *
 * Space Complexity: O(E) for the priority queue storage
 */
public class PrimPriorityQueueImp {
    private WeightedAdjSet g;           // The weighted graph
    private List<WeightedEdge> result;  // Edges in the minimum spanning tree

    /**
     * Constructs a minimum spanning tree using optimized Prim's algorithm with a priority queue.
     *
     * @param g Weighted undirected graph
     *
     * Time Complexity: O(E log E) where E is the number of edges
     *   - Checking connectivity: O(V + E)
     *   - Main algorithm: Each edge can be added to PQ once, and each extraction takes O(log E)
     *   - Total: O(E log E) which is much better than O(V²) for sparse graphs
     *
     * Space Complexity: O(E) for the priority queue and O(V) for the visited array
     */
    public PrimPriorityQueueImp(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        // Check if the graph is connected (Prim's algorithm requires a connected graph)
        ConnectedComponentsAnalyzer cc = new ConnectedComponentsAnalyzer(g);
        if (cc.getCcCount() > 1) return;  // If not connected, return without computing MST

        // Implementation of optimized Prim's algorithm
        boolean[] visited = new boolean[g.getV()];  // Track visited vertices
        visited[0] = true;  // Start from vertex 0

        // Priority queue automatically keeps the minimum weight edge at the top
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();

        // Add all edges from the starting vertex to the priority queue
        for (int w : g.adj(0)) {
            pq.add(new WeightedEdge(0, w, g.getWeight(0, w)));
        }

        // Process edges until the priority queue is empty
        while (!pq.isEmpty()) {  // O(E) iterations in worst case
            // Extract the minimum weight edge from the priority queue
            WeightedEdge minEdge = pq.poll();  // O(log E)

            // Skip if both endpoints are already in the MST (lazy deletion approach)
            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                continue;
            }

            // Add the edge to the MST
            result.add(minEdge);

            // Determine which endpoint is the new vertex to be added to the MST
            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;

            // Add all edges from the new vertex to unvisited vertices
            for (int w : g.adj(newV)) {
                if (!visited[w]) {
                    pq.add(new WeightedEdge(newV, w, g.getWeight(newV, w)));
                }
            }
        }
    }

    /**
     * Main method to demonstrate the optimized Prim's algorithm.
     *
     * @param args Command line arguments
     *
     * Example Output (depending on the graph in prim.txt):
     * WeightedEdge{v=0, w=2, weight=5}
     * WeightedEdge{v=2, w=3, weight=3}
     * WeightedEdge{v=3, w=1, weight=2}
     */
    public static void main(String[] args) {
        WeightedAdjSet adjSet = new WeightedAdjSet("data/prim.txt");
        PrimPriorityQueueImp prim = new PrimPriorityQueueImp(adjSet);

        List<WeightedEdge> res = prim.getResult();
        for (WeightedEdge edge : res) {
            System.out.println(edge);
        }
    }

    /**
     * Returns the edges in the minimum spanning tree.
     *
     * @return List of weighted edges in the MST
     *
     * Time Complexity: O(1) - constant time operation
     */
    public List<WeightedEdge> getResult() {
        return result;
    }
}