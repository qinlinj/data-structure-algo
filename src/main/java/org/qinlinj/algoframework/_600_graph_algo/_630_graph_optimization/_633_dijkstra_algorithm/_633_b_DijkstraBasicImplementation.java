package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._633_dijkstra_algorithm; /**
 * Basic Implementation of Dijkstra's Algorithm
 * <p>
 * Knowledge Points:
 * 1. State Class:
 * - Tracks the current node and distance from start
 * - Used as elements in the priority queue
 * <p>
 * 2. Core Algorithm Steps:
 * - Initialize distances to all nodes as infinity (or -1 for unused nodes)
 * - Use a priority queue to process nodes in order of increasing distance
 * - When a node is first dequeued, its current distance is the shortest possible
 * - Process all neighbors and potentially add them to the queue with updated distances
 * <p>
 * 3. Implementation Details:
 * - The distTo array serves both to track visited nodes and store shortest distances
 * - The priority queue may contain duplicate nodes with different distances
 * - Only the first occurrence of a node to be dequeued represents the shortest path
 * <p>
 * 4. Time Complexity: O(E log E)
 * Space Complexity: O(V + E)
 */

import java.util.*;

public class _633_b_DijkstraBasicImplementation {

    /**
     * Dijkstra's algorithm implementation
     *
     * @param graph The input weighted graph
     * @param src The source node
     * @return An array where distTo[i] is the shortest distance from src to node i
     */
    public static int[] dijkstra(Graph graph, int src) {
        // Array to store the shortest distance from src to each node
        int[] distTo = new int[graph.size()];

        // Initialize all distances as -1 (infinity)
        Arrays.fill(distTo, -1);

        // Priority queue ordered by distance (smallest first)
        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> a.distFromStart - b.distFromStart
        );

        // Add source node to the queue with distance 0
        pq.offer(new State(src, 0));

        // Process nodes from the queue
        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;

            // If this node has already been processed, skip it
            // (A node is processed when we find the shortest path to it)
            if (distTo[curNode] != -1) {
                continue;
            }

            // Record the shortest distance to this node
            distTo[curNode] = curDistFromStart;

            // Process all neighbors of the current node
            for (Edge edge : graph.neighbors(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDistFromStart + edge.weight;

                // If the neighbor hasn't been processed yet, add it to the queue
                if (distTo[nextNode] == -1) {
                    pq.offer(new State(nextNode, nextDist));
                }
            }
        }

        return distTo;
    }

    public static void main(String[] args) {
        // Create a simple example graph
        SimpleGraph graph = new SimpleGraph(5);

        // Add edges: (from, to, weight)
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 5);

        // Run Dijkstra's algorithm with node 0 as the source
        int src = 0;
        int[] shortestDistances = dijkstra(graph, src);

        // Print results
        System.out.println("Shortest distances from node " + src + ":");
        for (int i = 0; i < shortestDistances.length; i++) {
            System.out.println("To node " + i + ": " + shortestDistances[i]);
        }

        // Demonstrate the algorithm step by step
        demonstrateDijkstra(graph, src);
    }

    /**
     * Demonstrate Dijkstra's algorithm step by step
     */
    private static void demonstrateDijkstra(Graph graph, int src) {
        System.out.println("\nStep-by-Step Execution of Dijkstra's Algorithm:");
        System.out.println("----------------------------------------------");

        int[] distTo = new int[graph.size()];
        Arrays.fill(distTo, -1);

        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> a.distFromStart - b.distFromStart
        );

        pq.offer(new State(src, 0));

        System.out.println("\nInitial state: Source node = " + src);
        System.out.println("Priority Queue: [" + src + ", 0]");
        System.out.println("Distance array: " + Arrays.toString(distTo));

        int step = 1;

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;

            System.out.println("\nStep " + step + ": Dequeue node " + curNode +
                    " with distance " + curDistFromStart);

            if (distTo[curNode] != -1) {
                System.out.println("Node " + curNode + " has already been processed. Skipping.");
                continue;
            }

            distTo[curNode] = curDistFromStart;
            System.out.println("Setting distTo[" + curNode + "] = " + curDistFromStart);

            System.out.println("Processing neighbors of node " + curNode + ":");

            for (Edge edge : graph.neighbors(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDistFromStart + edge.weight;

                System.out.println("  Edge to node " + nextNode + " with weight " + edge.weight);
                System.out.println("  Potential new distance: " + nextDist);

                if (distTo[nextNode] == -1) {
                    System.out.println("  Adding node " + nextNode + " to queue with distance " + nextDist);
                    pq.offer(new State(nextNode, nextDist));
                } else {
                    System.out.println("  Node " + nextNode + " already processed with distance " +
                            distTo[nextNode] + ". Not adding to queue.");
                }
            }

            System.out.println("Current Priority Queue: " + printQueue(pq));
            System.out.println("Current Distance array: " + Arrays.toString(distTo));

            step++;
        }

        System.out.println("\nFinal distance array: " + Arrays.toString(distTo));
        System.out.println("Algorithm complete!");
    }

    /**
     * Helper method to print the contents of the priority queue
     */
    private static String printQueue(PriorityQueue<State> pq) {
        if (pq.isEmpty()) return "[]";

        PriorityQueue<State> copy = new PriorityQueue<>(pq);
        StringBuilder sb = new StringBuilder("[");

        while (!copy.isEmpty()) {
            State state = copy.poll();
            sb.append("(").append(state.node).append(", ").append(state.distFromStart).append(")");

            if (!copy.isEmpty()) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * This class represents the graph interface needed for Dijkstra's algorithm
     */
    public interface Graph {
        int size();

        Iterable<Edge> neighbors(int v);
    }

    /**
     * This class represents an edge in a weighted graph
     */
    public static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * State class to track a node and its current distance from the start
     */
    private static class State {
        // Current node ID
        int node;
        // Distance from source to this node
        int distFromStart;

        public State(int node, int distFromStart) {
            this.node = node;
            this.distFromStart = distFromStart;
        }
    }

    /**
     * A simple implementation of the Graph interface for demonstration
     */
    private static class SimpleGraph implements Graph {
        private List<Edge>[] adj;

        @SuppressWarnings("unchecked")
        public SimpleGraph(int size) {
            adj = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to, int weight) {
            adj[from].add(new Edge(from, to, weight));
        }

        @Override
        public int size() {
            return adj.length;
        }

        @Override
        public Iterable<Edge> neighbors(int v) {
            return adj[v];
        }
    }
}