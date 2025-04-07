package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;
import org.qinlinj.nonlinear.graph.dfs.ConnectedComponentsAnalyzer;

// @formatter:off
/**
 * Prim's Algorithm Implementation
 *
 * Concept and Principles:
 * Prim's algorithm is a greedy algorithm used to find the Minimum Spanning Tree (MST) of a connected weighted undirected graph.
 * An MST is a subset of edges that connects all vertices together with the minimum possible total edge weight,
 * without creating any cycles.
 *
 * Advantages of Prim's Algorithm:
 * 1. Guaranteed Optimality: Always finds the minimum spanning tree if the graph is connected
 * 2. Simplicity: Conceptually straightforward to understand and implement
 * 3. Efficient for Dense Graphs: Performs well when the graph has many edges
 * 4. Single-Source: Can start from any vertex and grow the MST incrementally
 *
 * Algorithm Steps:
 * 1. Start with any vertex and mark it as visited
 * 2. Repeatedly find the minimum weight edge that connects a visited vertex to an unvisited vertex
 * 3. Add this edge to the MST and mark the unvisited vertex as visited
 * 4. Repeat until all vertices are visited (resulting in V-1 edges for V vertices)
 *
 * Example Visualization:
 * Consider a network of cities that need to be connected with roads of minimum total length:
 *
 *     A --- 10 --- B
 *     |           /|
 *     |          / |
 *     5         2  7
 *     |        /   |
 *     |       /    |
 *     C --- 3 --- D
 *
 * Prim's algorithm starting from A:
 * 1. Start at A (mark A as visited)
 * 2. Find the minimum edge connecting A to unvisited: A-C with weight 5
 * 3. Add A-C to MST, mark C as visited
 * 4. Find the minimum edge connecting {A,C} to unvisited: C-D with weight 3
 * 5. Add C-D to MST, mark D as visited
 * 6. Find the minimum edge connecting {A,C,D} to unvisited: D-B with weight 2
 * 7. Add D-B to MST, mark B as visited
 *
 * Final MST: A-C (5) + C-D (3) + D-B (2) = Total cost: 10
 *
 * Time Complexity of this implementation: O(V^2 + E) = O(V^2) in worst case
 * More efficient implementations using priority queues can achieve O(E log V)
 */
public class Prim {
    private WeightedAdjSet g;
    private List<WeightedEdge> result;

    public Prim(WeightedAdjSet g) {
        this.g = g;
        this.result = new ArrayList<>();

        ConnectedComponentsAnalyzer cc = new ConnectedComponentsAnalyzer(g);
        if (cc.getCcCount() > 1) return;

        // Prim
        boolean[] visited = new boolean[g.getV()];

        visited[0] = true;

        for (int i = 0; i < g.getV() - 1; i++) { // O(V)
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < g.getV(); v++) { // O(V)
                if (visited[v]) {
                    for (int w : g.adj(v)) { // O(E)
                        if (!visited[w] && g.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new WeightedEdge(v, w, g.getWeight(v, w));
                        }
                    }
                }
            }
            result.add(minEdge);

            int v = minEdge.getV();
            int w = minEdge.getW();
            int newV = visited[v] ? w : v;
            visited[newV] = true;
        }
    }

    public static void main(String[] args) {
        WeightedAdjSet adjSet = new WeightedAdjSet("data/prim.txt");
        Prim prim = new Prim(adjSet);

        List<WeightedEdge> res = prim.getResult();
        for (WeightedEdge edge : res) {
            System.out.println(edge);
        }
    }

    public List<WeightedEdge> getResult() {
        return result;
    }
}
