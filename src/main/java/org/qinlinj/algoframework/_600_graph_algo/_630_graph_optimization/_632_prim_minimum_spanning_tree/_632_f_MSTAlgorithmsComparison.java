package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._632_prim_minimum_spanning_tree; /**
 * Comparison of Kruskal's and Prim's Algorithms for Minimum Spanning Trees
 * <p>
 * Knowledge Points:
 * 1. Core Differences:
 * - Kruskal's: Sorts all edges by weight, then builds MST by adding edges that don't create cycles
 * - Prim's: Starts from a single vertex and grows MST by adding minimum weight crossing edges
 * <p>
 * 2. Implementation Details:
 * - Kruskal's uses Union-Find data structure to efficiently detect cycles
 * - Prim's uses a priority queue and boolean array to track vertices in the MST
 * <p>
 * 3. Performance Characteristics:
 * - Kruskal's: O(E log E) time complexity, dominated by sorting
 * - Prim's: O(E log E) with binary heap, can be optimized to O(E + V log V) with Fibonacci heap
 * - Kruskal's is typically faster for sparse graphs, Prim's for dense graphs
 * <p>
 * 4. Space Complexity:
 * - Kruskal's: O(V + E) for Union-Find and edge storage
 * - Prim's: O(V + E) for priority queue and adjacency list
 * <p>
 * 5. Algorithmic Approach:
 * - Kruskal's follows a global approach (considers all edges)
 * - Prim's follows a local approach (grows from a single vertex)
 */

import java.util.*;

public class _632_f_MSTAlgorithmsComparison {

    public static void main(String[] args) {
        System.out.println("Comparison of Kruskal's and Prim's Algorithms");
        System.out.println("============================================");

        // Create a sample graph for demonstration
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
        int vertices = 6;

        // Compare algorithms on the same graph
        compareAlgorithms(vertices, edges);

        // Compare performance on different graph types
        comparePerformance();

        // Summarize the differences
        summarizeDifferences();
    }

    /**
     * Compare Kruskal's and Prim's algorithms on the same graph
     */
    private static void compareAlgorithms(int vertices, int[][] edges) {
        System.out.println("\n1. ALGORITHM BEHAVIOR COMPARISON");
        System.out.println("-------------------------------");

        System.out.println("Sample Graph:");
        for (int[] edge : edges) {
            System.out.println(edge[0] + " -- " + edge[2] + " --> " + edge[1]);
        }

        System.out.println("\nKruskal's Algorithm Execution:");

        // Create a list of edges for Kruskal's
        List<int[]> edgeList = new ArrayList<>();
        for (int[] edge : edges) {
            edgeList.add(edge);
        }

        // Sort edges by weight
        Collections.sort(edgeList, (a, b) -> a[2] - b[2]);

        System.out.println("Sorted edges (by weight):");
        for (int[] edge : edgeList) {
            System.out.println(edge[0] + " -- " + edge[2] + " --> " + edge[1]);
        }

        // Execute Kruskal's algorithm
        List<int[]> kruskalMST = kruskalMST(vertices, edgeList);

        System.out.println("\nKruskal's MST:");
        int kruskalWeight = 0;
        for (int[] edge : kruskalMST) {
            System.out.println(edge[0] + " -- " + edge[2] + " --> " + edge[1]);
            kruskalWeight += edge[2];
        }
        System.out.println("Total weight: " + kruskalWeight);

        System.out.println("\nPrim's Algorithm Execution:");

        // Build adjacency list for Prim's
        List<int[]>[] graph = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph[from].add(new int[]{from, to, weight});
            graph[to].add(new int[]{to, from, weight});
        }

        // Execute Prim's algorithm
        List<int[]> primMST = primMST(graph);

        System.out.println("Prim's MST:");
        int primWeight = 0;
        for (int[] edge : primMST) {
            System.out.println(edge[0] + " -- " + edge[2] + " --> " + edge[1]);
            primWeight += edge[2];
        }
        System.out.println("Total weight: " + primWeight);

        System.out.println("\nResult Comparison:");
        System.out.println("Both algorithms found MSTs with weight: " + kruskalWeight);

        // Check if the MSTs are the same
        boolean sameMST = haveSameEdges(kruskalMST, primMST, vertices);
        System.out.println("Do they have the same edges? " + (sameMST ? "Yes" : "No, but both are valid MSTs"));
    }

    /**
     * Execute Kruskal's algorithm
     */
    private static List<int[]> kruskalMST(int vertices, List<int[]> edges) {
        List<int[]> result = new ArrayList<>();

        // Union-Find data structure
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        // Helper function to find the root
        class UnionFind {
            int find(int x) {
                if (parent[x] != x) {
                    parent[x] = find(parent[x]); // Path compression
                }
                return parent[x];
            }

            void union(int x, int y) {
                parent[find(x)] = find(y);
            }
        }

        UnionFind uf = new UnionFind();

        // Process each edge in order of weight
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            // Check if adding the edge would create a cycle
            if (uf.find(from) != uf.find(to)) {
                uf.union(from, to);
                result.add(edge);

                // MST will have exactly (vertices-1) edges
                if (result.size() == vertices - 1) {
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Execute Prim's algorithm
     */
    private static List<int[]> primMST(List<int[]>[] graph) {
        List<int[]> result = new ArrayList<>();
        int vertices = graph.length;

        // Priority queue for edges
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        // Keep track of vertices in MST
        boolean[] inMST = new boolean[vertices];

        // Start from vertex 0
        inMST[0] = true;

        // Add all edges from vertex 0
        for (int[] edge : graph[0]) {
            pq.offer(edge);
        }

        while (!pq.isEmpty() && result.size() < vertices - 1) {
            int[] edge = pq.poll();
            int to = edge[1];

            // Skip if destination is already in MST
            if (inMST[to]) {
                continue;
            }

            // Add edge to MST
            result.add(edge);
            inMST[to] = true;

            // Add all edges from the newly added vertex
            for (int[] nextEdge : graph[to]) {
                if (!inMST[nextEdge[1]]) {
                    pq.offer(nextEdge);
                }
            }
        }

        return result;
    }

    /**
     * Check if two MSTs contain the same edges (ignoring order)
     */
    private static boolean haveSameEdges(List<int[]> mst1, List<int[]> mst2, int vertices) {
        // Create adjacency matrices for both MSTs
        boolean[][] adj1 = new boolean[vertices][vertices];
        boolean[][] adj2 = new boolean[vertices][vertices];

        for (int[] edge : mst1) {
            adj1[edge[0]][edge[1]] = true;
            adj1[edge[1]][edge[0]] = true;
        }

        for (int[] edge : mst2) {
            adj2[edge[0]][edge[1]] = true;
            adj2[edge[1]][edge[0]] = true;
        }

        // Compare adjacency matrices
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (adj1[i][j] != adj2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Compare performance characteristics of Kruskal's and Prim's algorithms
     */
    private static void comparePerformance() {
        System.out.println("\n2. PERFORMANCE COMPARISON");
        System.out.println("------------------------");

        System.out.println("Time Complexity:");
        System.out.println("- Kruskal's: O(E log E) dominated by sorting edges");
        System.out.println("- Prim's: O(E log E) with binary heap implementation");
        System.out.println("  Can be optimized to O(E + V log V) with Fibonacci heap");

        System.out.println("\nSpace Complexity:");
        System.out.println("- Kruskal's: O(V + E) for Union-Find and edge storage");
        System.out.println("- Prim's: O(V + E) for priority queue and adjacency list");

        System.out.println("\nPerformance Characteristics by Graph Type:");

        System.out.println("\nSparse Graphs (E ≈ V):");
        System.out.println("- Kruskal's typically performs better");
        System.out.println("- Sorting fewer edges is more efficient");
        System.out.println("- Union-Find operations are very fast");

        System.out.println("\nDense Graphs (E ≈ V²):");
        System.out.println("- Prim's typically performs better");
        System.out.println("- Avoids sorting all edges upfront");
        System.out.println("- Priority queue operations become more efficient");
    }

    /**
     * Summarize the key differences between Kruskal's and Prim's algorithms
     */
    private static void summarizeDifferences() {
        System.out.println("\n3. CONCEPTUAL DIFFERENCES");
        System.out.println("------------------------");

        System.out.println("Algorithmic Approach:");
        System.out.println("- Kruskal's: Global approach, considers all edges sorted by weight");
        System.out.println("  Builds forest of trees that gradually merge into one MST");
        System.out.println("- Prim's: Local approach, grows a single tree from a starting vertex");
        System.out.println("  Uses 'cut property' to find the next edge to add");

        System.out.println("\nData Structures:");
        System.out.println("- Kruskal's: Uses Union-Find to detect cycles");
        System.out.println("  Requires sorting all edges once");
        System.out.println("- Prim's: Uses priority queue and visited array");
        System.out.println("  Dynamically determines minimum weight edge at each step");

        System.out.println("\nGraph Representation:");
        System.out.println("- Kruskal's: Works well with edge list representation");
        System.out.println("- Prim's: Works well with adjacency list representation");

        System.out.println("\nPartial Results:");
        System.out.println("- Kruskal's: Intermediate result is a forest of subtrees");
        System.out.println("- Prim's: Intermediate result is always a single tree");

        System.out.println("\nImplementation Complexity:");
        System.out.println("- Kruskal's: Relatively simple to implement");
        System.out.println("  Requires Union-Find data structure");
        System.out.println("- Prim's: Similarly straightforward with priority queue");
        System.out.println("  More complex to optimize with advanced priority queue implementations");

        System.out.println("\nPractical Considerations:");
        System.out.println("- Kruskal's is typically preferred for sparse graphs");
        System.out.println("- Prim's is typically preferred for dense graphs");
        System.out.println("- Both produce a valid minimum spanning tree");
        System.out.println("- If multiple MSTs exist (edges with equal weights), the algorithms may");
        System.out.println("  produce different but equally valid minimum spanning trees");
    }
}