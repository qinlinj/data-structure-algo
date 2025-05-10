package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._632_prim_minimum_spanning_tree; /**
 * Min Cost to Connect All Points
 * (LeetCode 1584: Min Cost to Connect All Points)
 * <p>
 * Knowledge Points:
 * 1. This is another application of the Minimum Spanning Tree problem:
 * - Each point in the 2D plane is a vertex in the graph
 * - The Manhattan distance between any two points forms an edge weight
 * - The goal is to find the minimum total Manhattan distance to connect all points
 * <p>
 * 2. Manhattan distance: |x1 - x2| + |y1 - y2|
 * <p>
 * 3. Implementation approach with Prim's algorithm:
 * - Create an adjacency list representation with points indexed by their position in the input array
 * - Calculate Manhattan distances between all pairs of points for edge weights
 * - Apply Prim's algorithm to find the MST
 * <p>
 * 4. Optimization considerations:
 * - For n points, there are n(n-1)/2 possible edges (complete graph)
 * - Can generate edges on-demand to reduce memory usage
 * <p>
 * 5. Time Complexity: O(n²) where n is the number of points
 * Space Complexity: O(n²) for storing the graph
 */

import java.util.*;

public class _632_e_MinCostConnectPoints {

    /**
     * Example usage of the solution
     */
    public static void main(String[] args) {
        _632_e_MinCostConnectPoints solution = new _632_e_MinCostConnectPoints();

        // Example 1
        int[][] points1 = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        int result1 = solution.minCostConnectPoints(points1);
        System.out.println("Example 1 - Minimum cost: " + result1);
        System.out.println("Expected output: 20");

        // Example 2
        int[][] points2 = {{3, 12}, {-2, 5}, {-4, 1}};
        int result2 = solution.minCostConnectPoints(points2);
        System.out.println("\nExample 2 - Minimum cost: " + result2);
        System.out.println("Expected output: 18");

        // Example 3
        int[][] points3 = {{0, 0}, {1, 1}, {1, 0}, {-1, 1}};
        int result3 = solution.minCostConnectPoints(points3);
        System.out.println("\nExample 3 - Minimum cost: " + result3);
        System.out.println("Expected output: 4");

        // Example 4 - Edge case: single point
        int[][] points4 = {{0, 0}};
        int result4 = solution.minCostConnectPoints(points4);
        System.out.println("\nExample 4 - Minimum cost: " + result4);
        System.out.println("Expected output: 0");
    }

    /**
     * Find the minimum cost to connect all points
     *
     * @param points array of 2D points where points[i] = [xi, yi]
     * @return minimum cost to connect all points (sum of Manhattan distances)
     */
    public int minCostConnectPoints(int[][] points) {
        // Build the graph from the points
        List<int[]>[] graph = buildGraph(points);

        // Apply Prim's algorithm to find the MST
        return new Prim(graph).weightSum();
    }

    /**
     * Build an adjacency list representation of the graph from the points
     *
     * @param points array of 2D points
     * @return adjacency list representation of the graph
     */
    private List<int[]>[] buildGraph(int[][] points) {
        int n = points.length;
        List<int[]>[] graph = new LinkedList[n];

        // Initialize the adjacency list
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        // Create edges between all pairs of points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];

                // Calculate Manhattan distance
                int distance = Math.abs(xi - xj) + Math.abs(yi - yj);

                // Add edges in both directions (undirected graph)
                // We use indices i and j to represent the points
                graph[i].add(new int[]{i, j, distance});
                graph[j].add(new int[]{j, i, distance});
            }
        }

        return graph;
    }

    /**
     * Prim's algorithm implementation
     */
    private class Prim {
        // Priority queue for edges
        private PriorityQueue<int[]> pq;

        // Track which points are in the MST
        private boolean[] inMST;

        // Total cost of the MST
        private int weightSum = 0;

        // Graph representation
        private List<int[]>[] graph;

        /**
         * Constructor that executes Prim's algorithm
         *
         * @param graph Adjacency list representation of the graph
         */
        public Prim(List<int[]>[] graph) {
            this.graph = graph;

            // Priority queue ordered by edge weight
            this.pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

            // Number of points
            int n = graph.length;
            this.inMST = new boolean[n];

            // Start from point 0 (arbitrary starting point)
            inMST[0] = true;
            cut(0);

            // Main algorithm loop
            while (!pq.isEmpty()) {
                int[] edge = pq.poll();
                int to = edge[1];
                int weight = edge[2];

                // Skip if the destination point is already in the MST
                if (inMST[to]) {
                    continue;
                }

                // Add the edge to the MST
                weightSum += weight;
                inMST[to] = true;

                // Add new crossing edges
                cut(to);
            }
        }

        /**
         * Add all crossing edges from point s to the priority queue
         *
         * @param s The point whose edges are to be considered
         */
        private void cut(int s) {
            // Examine each edge from point s
            for (int[] edge : graph[s]) {
                int to = edge[1];

                // Skip if the destination is already in the MST
                if (inMST[to]) {
                    continue;
                }

                // Add this crossing edge to the priority queue
                pq.offer(edge);
            }
        }

        /**
         * Get the total cost of the MST
         *
         * @return Sum of costs of all edges in the MST
         */
        public int weightSum() {
            return weightSum;
        }
    }
}