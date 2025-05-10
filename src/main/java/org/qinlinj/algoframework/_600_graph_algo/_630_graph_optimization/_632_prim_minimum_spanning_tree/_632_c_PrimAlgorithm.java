package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._632_prim_minimum_spanning_tree; /**
 * Prim's Algorithm Implementation
 * <p>
 * Knowledge Points:
 * 1. Core Algorithm Steps:
 * - Start with a single vertex and grow the MST one vertex at a time
 * - Maintain a priority queue of "crossing edges" between vertices in and outside the MST
 * - Always select the minimum weight crossing edge that doesn't create a cycle
 * - Continue until all vertices are included in the MST
 * <p>
 * 2. Data Structures:
 * - Priority Queue: Stores crossing edges ordered by weight
 * - Boolean Array: Tracks which vertices are in the MST (similar to 'visited' in BFS)
 * - Adjacency List: Represents the graph with weights
 * <p>
 * 3. Implementation Details:
 * - The "cut" operation adds all edges from a newly added vertex to the priority queue
 * - Edges leading to vertices already in the MST are skipped to avoid cycles
 * - The process continues until the priority queue is empty or all vertices are included
 * <p>
 * 4. Avoiding Cycles:
 * - A cycle would form if we add an edge to a vertex already in the MST
 * - The boolean array efficiently prevents this by tracking vertices in the MST
 * <p>
 * 5. Time Complexity: O(E log E) where E is the number of edges
 * Space Complexity: O(V + E) where V is the number of vertices
 */

import java.util.*;

public class _632_c_PrimAlgorithm {

    /**
     * Build an adjacency list representation of a graph from edges
     *
     * @param n Number of vertices
     * @param edges Array of edges [from, to, weight]
     * @return Adjacency list representation of the graph
     */
    public static List<int[]>[] buildGraph(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n];

        // Initialize adjacency lists
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add edges to the graph (undirected)
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            // Add edges in both directions for an undirected graph
            graph[from].add(new int[]{from, to, weight});
            graph[to].add(new int[]{to, from, weight});
        }

        return graph;
    }

    public static void main(String[] args) {
        System.out.println("Prim's Algorithm Implementation");
        System.out.println("==============================");

        // Example graph represented as edges [from, to, weight]
        int[][] edges = {
                {0, 1, 4},
                {0, 2, 3},
                {1, 2, 1},
                {1, 3, 2},
                {2, 3, 4},
                {2, 4, 5},
                {3, 4, 7},
                {3, 5, 6},
                {4, 5, 2}
        };

        int n = 6; // Number of vertices (0-5)

        System.out.println("\nSample Graph:");
        System.out.println("Vertices: " + n);
        System.out.println("Edges:");
        for (int[] edge : edges) {
            System.out.println(edge[0] + " -- " + edge[2] + " --> " + edge[1]);
        }

        // Build the graph
        List<int[]>[] graph = buildGraph(n, edges);

        System.out.println("\nExecuting Prim's Algorithm...");

        // Step-by-step execution of Prim's algorithm
        stepByStepPrim(graph);

        // Run the complete algorithm
        Prim prim = new Prim(graph);

        System.out.println("\nFinal Results:");
        System.out.println("MST Total Weight: " + prim.weightSum());
        System.out.println("All Vertices Connected: " + prim.allConnected());
    }

    /**
     * Demonstrate the step-by-step execution of Prim's algorithm
     *
     * @param graph The input graph as an adjacency list
     */
    private static void stepByStepPrim(List<int[]>[] graph) {
        int n = graph.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[] inMST = new boolean[n];
        List<int[]> mstEdges = new ArrayList<>();
        int totalWeight = 0;

        System.out.println("\nStep-by-Step Execution:");

        // Start from vertex 0
        System.out.println("\nStep 1: Starting from vertex 0");
        inMST[0] = true;

        // Add all edges from vertex 0 to the priority queue
        System.out.println("Adding edges from vertex 0 to the priority queue:");
        for (int[] edge : graph[0]) {
            System.out.println("  Edge: " + edge[0] + " -- " + edge[2] + " --> " + edge[1]);
            pq.offer(edge);
        }

        // Main algorithm loop
        int step = 2;
        while (!pq.isEmpty() && mstEdges.size() < n - 1) {
            // Extract the minimum weight crossing edge
            int[] edge = pq.poll();
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            System.out.println("\nStep " + step + ": Examining edge " + from + " -- " + weight + " --> " + to);

            // Skip if the destination vertex is already in the MST
            if (inMST[to]) {
                System.out.println("  Skipping: Vertex " + to + " is already in the MST (would create a cycle)");
                continue;
            }

            // Add the edge to the MST
            System.out.println("  Adding edge to MST: " + from + " -- " + weight + " --> " + to);
            mstEdges.add(edge);
            totalWeight += weight;
            inMST[to] = true;

            // Add new crossing edges
            System.out.println("  Adding edges from vertex " + to + " to the priority queue:");
            for (int[] nextEdge : graph[to]) {
                int nextTo = nextEdge[1];
                if (!inMST[nextTo]) {
                    System.out.println("    Edge: " + nextEdge[0] + " -- " + nextEdge[2] + " --> " + nextEdge[1]);
                    pq.offer(nextEdge);
                }
            }

            step++;
        }

        System.out.println("\nMST Edges:");
        for (int[] edge : mstEdges) {
            System.out.println(edge[0] + " -- " + edge[2] + " --> " + edge[1]);
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }

    // Core implementation of Prim's algorithm
    static class Prim {
        // Priority queue to store crossing edges
        private PriorityQueue<int[]> pq;

        // Tracks which vertices are in the MST
        private boolean[] inMST;

        // Total weight of the MST
        private int weightSum = 0;

        // Graph representation (adjacency list)
        // Each edge is represented as int[]{from, to, weight}
        private List<int[]>[] graph;

        /**
         * Constructor that executes Prim's algorithm
         *
         * @param graph Adjacency list representation of the graph
         */
        public Prim(List<int[]>[] graph) {
            this.graph = graph;

            // Initialize priority queue with edge weight as the ordering criteria
            this.pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

            // Number of vertices in the graph
            int n = graph.length;
            this.inMST = new boolean[n];

            // Start from vertex 0 (arbitrary choice)
            inMST[0] = true;

            // Add all edges from the starting vertex to the priority queue
            cut(0);

            // Main algorithm loop
            while (!pq.isEmpty()) {
                // Extract the minimum weight crossing edge
                int[] edge = pq.poll();
                int to = edge[1];
                int weight = edge[2];

                // Skip if the destination vertex is already in the MST (would create a cycle)
                if (inMST[to]) {
                    continue;
                }

                // Add the edge to the MST
                weightSum += weight;
                inMST[to] = true;

                // Add new crossing edges from the newly added vertex
                cut(to);
            }
        }

        /**
         * Add all crossing edges from vertex s to the priority queue
         *
         * @param s The vertex whose edges are to be considered
         */
        private void cut(int s) {
            // Examine each edge from vertex s
            for (int[] edge : graph[s]) {
                int to = edge[1];

                // Skip if the destination is already in the MST (would create a cycle)
                if (inMST[to]) {
                    continue;
                }

                // Add this crossing edge to the priority queue
                pq.offer(edge);
            }
        }

        /**
         * Get the total weight of the MST
         *
         * @return Sum of weights of all edges in the MST
         */
        public int weightSum() {
            return weightSum;
        }

        /**
         * Check if the MST includes all vertices in the graph
         *
         * @return true if all vertices are in the MST, false otherwise
         */
        public boolean allConnected() {
            for (boolean vertexInMST : inMST) {
                if (!vertexInMST) {
                    return false;
                }
            }
            return true;
        }
    }
}