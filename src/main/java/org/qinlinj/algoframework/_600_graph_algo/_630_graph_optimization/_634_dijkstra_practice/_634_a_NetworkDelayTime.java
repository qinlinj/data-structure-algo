package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._634_dijkstra_practice; /**
 * Network Delay Time - Dijkstra Algorithm Application
 * LeetCode 743: https://leetcode.com/problems/network-delay-time/
 * <p>
 * Knowledge Points:
 * 1. Application of Dijkstra's algorithm to find the time it takes for a signal
 * to reach all nodes in a network
 * <p>
 * 2. Problem Translation:
 * - Network nodes are vertices in a graph
 * - Signal travel times are edge weights
 * - The question asks for the minimum time for a signal from node k to reach all nodes
 * - This is equivalent to finding the maximum of all shortest paths from node k
 * <p>
 * 3. Implementation Details:
 * - Build an adjacency list representation of the graph from the input
 * - Apply Dijkstra's algorithm from the source node k
 * - Check if all nodes are reachable and find the maximum shortest path distance
 * <p>
 * 4. Time Complexity: O(E log E) where E is the number of edges
 * Space Complexity: O(V + E) where V is the number of vertices
 */

import java.util.*;

public class _634_a_NetworkDelayTime {

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        _634_a_NetworkDelayTime solution = new _634_a_NetworkDelayTime();

        // Example 1
        int[][] times1 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n1 = 4, k1 = 2;
        System.out.println("Example 1: " + solution.networkDelayTime(times1, n1, k1));
        // Expected output: 2

        // Example 2
        int[][] times2 = {{1, 2, 1}};
        int n2 = 2, k2 = 1;
        System.out.println("Example 2: " + solution.networkDelayTime(times2, n2, k2));
        // Expected output: 1

        // Example 3
        int[][] times3 = {{1, 2, 1}};
        int n3 = 2, k3 = 2;
        System.out.println("Example 3: " + solution.networkDelayTime(times3, n3, k3));
        // Expected output: -1

        // Example 4: More complex network
        int[][] times4 = {
                {1, 2, 9}, {1, 4, 2}, {2, 5, 1}, {4, 2, 4},
                {4, 5, 6}, {3, 2, 3}, {5, 3, 7}, {3, 1, 5}
        };
        int n4 = 5, k4 = 1;
        System.out.println("Example 4: " + solution.networkDelayTime(times4, n4, k4));
        // Expected output: 14

        // Visualization of the solution process
        explainSolution(times1, n1, k1);
    }

    /**
     * Helper method to visualize the solution process
     */
    private static void explainSolution(int[][] times, int n, int k) {
        System.out.println("\nDetailed explanation for Example 1:");
        System.out.println("Network: " + Arrays.deepToString(times));
        System.out.println("Nodes: " + n + ", Starting node: " + k);

        System.out.println("\nStep 1: Build the graph as an adjacency list");
        System.out.println("Node 1: []");
        System.out.println("Node 2: [(1,1), (3,1)]");
        System.out.println("Node 3: [(4,1)]");
        System.out.println("Node 4: []");

        System.out.println("\nStep 2: Apply Dijkstra's algorithm from node " + k);
        System.out.println("Initialize distances: [∞, ∞, 0, ∞, ∞]");
        System.out.println("Process node 2 with time 0");
        System.out.println("  Add neighbors to queue: [(1,1), (3,1)]");
        System.out.println("  Update distances: [∞, 1, 0, 1, ∞]");
        System.out.println("Process node 1 with time 1");
        System.out.println("  No neighbors to add");
        System.out.println("Process node 3 with time 1");
        System.out.println("  Add neighbor to queue: [(4,2)]");
        System.out.println("  Update distances: [∞, 1, 0, 1, 2]");
        System.out.println("Process node 4 with time 2");
        System.out.println("  No neighbors to add");
        System.out.println("Final distances: [∞, 1, 0, 1, 2]");

        System.out.println("\nStep 3: Find the maximum of all distances");
        System.out.println("Max time = max(1, 0, 1, 2) = 2");

        System.out.println("\nResult: It takes 2 time units for a signal from node 2 to reach all nodes.");
    }

    /**
     * Finds the minimum time it takes for a signal from node k to reach all nodes
     *
     * @param times Array of [source, target, time] representing the network connections
     * @param n Number of nodes in the network (nodes are labeled from 1 to n)
     * @param k Starting node where the signal is sent from
     * @return Minimum time for the signal to reach all nodes, or -1 if not possible
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // Create adjacency list representation of the graph
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        // Build the graph from the input
        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2]; // signal travel time
            graph[from].add(new int[]{to, weight});
        }

        // Apply Dijkstra's algorithm
        int[] timeTo = dijkstra(graph, k);

        // Find the maximum shortest path time (the bottleneck)
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (timeTo[i] == Integer.MAX_VALUE) {
                // Some node is unreachable
                return -1;
            }
            maxTime = Math.max(maxTime, timeTo[i]);
        }

        return maxTime;
    }

    /**
     * Dijkstra's algorithm implementation
     *
     * @param graph Adjacency list representation of the network
     * @param src Source node to start the signal from
     * @return Array where timeTo[i] is the minimum time for a signal from src to reach node i
     */
    private int[] dijkstra(List<int[]>[] graph, int src) {
        int n = graph.length - 1; // Number of nodes (excluding the 0th unused node)

        // Initialize distances array with infinity
        int[] timeTo = new int[graph.length];
        Arrays.fill(timeTo, Integer.MAX_VALUE);

        // Priority queue to process nodes in order of increasing time
        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.timeFromStart, b.timeFromStart)
        );

        // Start from the source node
        pq.offer(new State(src, 0));

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curTimeFromStart = state.timeFromStart;

            // Skip if we've already found a better path to this node
            if (timeTo[curNode] != Integer.MAX_VALUE) {
                continue;
            }

            // Record the time to reach this node
            timeTo[curNode] = curTimeFromStart;

            // Process all neighbors
            for (int[] neighbor : graph[curNode]) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                int nextTimeFromStart = curTimeFromStart + weight;

                // Only consider if we haven't found the optimal time yet
                if (timeTo[nextNode] == Integer.MAX_VALUE) {
                    pq.offer(new State(nextNode, nextTimeFromStart));
                }
            }
        }

        return timeTo;
    }

    /**
     * State class to track a node and its current distance from the start
     */
    static class State {
        // Current node ID
        int node;
        // Time from source to this node
        int timeFromStart;

        public State(int node, int timeFromStart) {
            this.node = node;
            this.timeFromStart = timeFromStart;
        }
    }
}