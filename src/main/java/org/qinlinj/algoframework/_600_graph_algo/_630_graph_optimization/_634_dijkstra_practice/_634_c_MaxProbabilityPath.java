package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._634_dijkstra_practice; /**
 * Path with Maximum Probability - Modified Dijkstra Algorithm
 * LeetCode 1514: https://leetcode.com/problems/path-with-maximum-probability/
 * <p>
 * Knowledge Points:
 * 1. Application of Dijkstra's algorithm to find a path with maximum probability
 * <p>
 * 2. Problem Translation:
 * - Nodes in the graph are the vertices
 * - Edges have weights representing success probabilities (between 0 and 1)
 * - Find the path from start to end with the highest overall probability
 * - Overall probability is the product of probabilities along the path
 * <p>
 * 3. Dijkstra's Algorithm Modification:
 * - Standard Dijkstra finds the minimum path sum
 * - For maximum probability, we need the maximum path product
 * - Invert the priority queue comparison to prioritize higher probabilities
 * - Each edge decreases the total probability (since probabilities are ≤ 1)
 * <p>
 * 4. Key Insight:
 * - Dijkstra's algorithm works for "optimal" paths, not just shortest paths
 * - It works when each added edge either consistently increases or decreases the path weight
 * - When edges consistently decrease the path value (as with probability products),
 * we can use Dijkstra to find the maximum value path
 * <p>
 * 5. Time Complexity: O(E log E) where E is the number of edges
 * Space Complexity: O(V + E) where V is the number of vertices
 */

import java.util.*;

public class _634_c_MaxProbabilityPath {

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        _634_c_MaxProbabilityPath solution = new _634_c_MaxProbabilityPath();

        // Example 1
        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb1 = {0.5, 0.5, 0.2};
        int start1 = 0, end1 = 2;
        System.out.println("Example 1: " + solution.maxProbability(n1, edges1, succProb1, start1, end1));
        // Expected output: 0.25000

        // Example 2
        int n2 = 3;
        int[][] edges2 = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb2 = {0.5, 0.5, 0.3};
        int start2 = 0, end2 = 2;
        System.out.println("Example 2: " + solution.maxProbability(n2, edges2, succProb2, start2, end2));
        // Expected output: 0.30000

        // Example 3
        int n3 = 3;
        int[][] edges3 = {{0, 1}};
        double[] succProb3 = {0.5};
        int start3 = 0, end3 = 2;
        System.out.println("Example 3: " + solution.maxProbability(n3, edges3, succProb3, start3, end3));
        // Expected output: 0.00000

        // Visualize the solution for Example 1
        explainSolution(n1, edges1, succProb1, start1, end1);
    }

    /**
     * Helper method to visualize the solution process
     */
    private static void explainSolution(int n, int[][] edges, double[] succProb, int start, int end) {
        System.out.println("\nDetailed explanation for Example 1:");
        System.out.println("Graph with " + n + " nodes (0 to " + (n - 1) + ")");
        System.out.println("Edges with probabilities:");
        for (int i = 0; i < edges.length; i++) {
            System.out.println(edges[i][0] + " -- " + succProb[i] + " --> " + edges[i][1]);
        }
        System.out.println("Start: " + start + ", End: " + end);

        System.out.println("\nStep 1: Build the graph as an adjacency list");
        System.out.println("Node 0: [(1, 0.5), (2, 0.2)]");
        System.out.println("Node 1: [(0, 0.5), (2, 0.5)]");
        System.out.println("Node 2: [(0, 0.2), (1, 0.5)]");

        System.out.println("\nStep 2: Apply modified Dijkstra's algorithm from node " + start);
        System.out.println("Initialize probabilities: [-1, -1, -1]");
        System.out.println("Add source node to queue: [(0, 1.0)]");

        System.out.println("\nStep 3: Process node 0 with probability 1.0");
        System.out.println("Mark node 0 as visited with probability 1.0");
        System.out.println("Add neighbors to queue:");
        System.out.println("  Node 1 with probability 1.0 * 0.5 = 0.5");
        System.out.println("  Node 2 with probability 1.0 * 0.2 = 0.2");
        System.out.println("Priority queue: [(1, 0.5), (2, 0.2)]");
        System.out.println("Probabilities: [1.0, -1, -1]");

        System.out.println("\nStep 4: Process node 1 with probability 0.5");
        System.out.println("Mark node 1 as visited with probability 0.5");
        System.out.println("Add neighbor to queue:");
        System.out.println("  Node 2 with probability 0.5 * 0.5 = 0.25");
        System.out.println("Priority queue: [(2, 0.25), (2, 0.2)]");
        System.out.println("Probabilities: [1.0, 0.5, -1]");

        System.out.println("\nStep 5: Process node 2 with probability 0.25");
        System.out.println("We've reached the destination node 2!");
        System.out.println("Maximum probability: 0.25");
        System.out.println("Path: 0 -> 1 -> 2 with probability 0.5 * 0.5 = 0.25");

        System.out.println("\nAlternative path: 0 -> 2 with probability 0.2");
        System.out.println("This is worse than the path we found, so it's not optimal.");

        System.out.println("\nResult: The maximum probability to go from node 0 to node 2 is 0.25.");
    }

    /**
     * Finds the path with maximum probability from start to end
     *
     * @param n Number of nodes in the graph (labeled from 0 to n-1)
     * @param edges Array of edges [node1, node2] representing connections
     * @param succProb Array of probabilities for each edge
     * @param start Starting node
     * @param end Target node
     * @return The maximum probability of a path from start to end, or 0 if no path exists
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // Build the graph as an adjacency list
        List<double[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add edges to the graph (undirected)
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double prob = succProb[i];

            // Add edges in both directions (undirected graph)
            graph[from].add(new double[]{to, prob});
            graph[to].add(new double[]{from, prob});
        }

        // Apply modified Dijkstra's algorithm
        return dijkstra(graph, start, end);
    }

    /**
     * Modified Dijkstra's algorithm to find the path with maximum probability
     *
     * @param graph Adjacency list representation of the graph
     * @param src Source node
     * @param dst Destination node
     * @return Maximum probability of a path from src to dst, or 0 if no path exists
     */
    private double dijkstra(List<double[]>[] graph, int src, int dst) {
        int n = graph.length;

        // Track the maximum probability to reach each node
        double[] probTo = new double[n];
        Arrays.fill(probTo, -1); // -1 indicates unvisited

        // Priority queue to process nodes in order of DECREASING probability
        // (invert the normal ordering to find maximum instead of minimum)
        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.probFromStart, a.probFromStart)
        );

        // Start from the source with probability 1
        pq.offer(new State(src, 1.0));

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int node = state.node;
            double prob = state.probFromStart;

            // If we've reached the destination, return the probability
            if (node == dst) {
                return prob;
            }

            // Skip if we've found a better path to this node
            if (probTo[node] != -1) {
                continue;
            }

            // Mark this node as visited with current probability
            probTo[node] = prob;

            // Explore all neighbors
            for (double[] edge : graph[node]) {
                int nextNode = (int) edge[0];
                double edgeProb = edge[1];

                // Skip if we've already found the optimal path to this neighbor
                if (probTo[nextNode] != -1) {
                    continue;
                }

                // Calculate the probability to reach the next node
                double nextProb = prob * edgeProb;

                // Add the neighbor to the queue
                pq.offer(new State(nextNode, nextProb));
            }
        }

        // If we've exhausted the queue and haven't reached the destination,
        // there is no path from src to dst
        return 0.0;
    }

    /**
     * State class to track a node and its current probability from the start
     */
    static class State {
        // Current node ID
        int node;
        // Probability of successfully reaching this node from the start
        double probFromStart;

        public State(int node, double probFromStart) {
            this.node = node;
            this.probFromStart = probFromStart;
        }
    }
}