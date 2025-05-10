package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._633_dijkstra_algorithm; /**
 * Point-to-Point Shortest Path with Dijkstra's Algorithm
 * <p>
 * Knowledge Points:
 * 1. Optimization for Point-to-Point Problems:
 * - When finding shortest path between specific source and destination nodes
 * - Can terminate early when destination node is processed
 * - Maintains the same correctness guarantees as the full algorithm
 * <p>
 * 2. Implementation Modification:
 * - Add a check after processing each node to see if it's the destination
 * - Return immediately when destination is reached with its shortest distance
 * - No need to compute shortest paths to all other nodes
 * <p>
 * 3. Efficiency Gain:
 * - May process significantly fewer nodes in large graphs
 * - Theoretical complexity remains O(E log E) in worst case
 * - Practical performance often much better, especially in large graphs
 * <p>
 * 4. Applications:
 * - Navigation systems (finding route between two locations)
 * - Network routing (sending data from one node to another)
 * - Any scenario where only a specific path is needed, not all paths
 */

import java.util.*;

public class _633_d_DijkstraPointToPoint {

    /**
     * Optimized Dijkstra's algorithm for point-to-point shortest path
     *
     * @param graph The input weighted graph
     * @param src The source node
     * @param dst The destination node
     * @return The shortest distance from src to dst, or -1 if unreachable
     */
    public static int dijkstra(Graph graph, int src, int dst) {
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
            if (distTo[curNode] != -1) {
                continue;
            }

            // Record the shortest distance to this node
            distTo[curNode] = curDistFromStart;

            // If we've reached the destination, return the distance
            // This is the key optimization for point-to-point shortest path
            if (curNode == dst) {
                return curDistFromStart;
            }

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

        // If we've exhausted the queue and haven't reached dst, it's unreachable
        return -1;
    }

    public static void main(String[] args) {
        // Create a simple example graph
        SimpleGraph graph = new SimpleGraph(6);

        // Add edges: (from, to, weight)
        graph.addEdge(0, 1, 7);
        graph.addEdge(0, 2, 9);
        graph.addEdge(0, 5, 14);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 11);
        graph.addEdge(2, 5, 2);
        graph.addEdge(3, 4, 6);
        graph.addEdge(4, 5, 9);

        // Define source and destination nodes
        int src = 0;
        int dst = 4;

        // Run the point-to-point Dijkstra's algorithm
        int shortestDistance = dijkstra(graph, src, dst);

        // Print result
        if (shortestDistance != -1) {
            System.out.println("Shortest distance from node " + src + " to node " + dst + ": " + shortestDistance);
        } else {
            System.out.println("No path exists from node " + src + " to node " + dst);
        }

        // Demonstrate algorithm efficiency with early termination
        compareStandardAndPointToPoint(graph, src, dst);

        // Show path reconstruction
        demonstratePathReconstruction(graph, src, dst);
    }

    /**
     * Compare standard Dijkstra's algorithm with the point-to-point optimization
     */
    private static void compareStandardAndPointToPoint(Graph graph, int src, int dst) {
        System.out.println("\nComparing Standard Dijkstra vs. Point-to-Point Optimization:");
        System.out.println("----------------------------------------------------------");

        // Track nodes processed in standard algorithm
        int standardNodesProcessed = countNodesProcessed(graph, src, false, -1);

        // Track nodes processed in point-to-point algorithm
        int pointToPointNodesProcessed = countNodesProcessed(graph, src, true, dst);

        System.out.println("Standard Dijkstra processed: " + standardNodesProcessed + " nodes");
        System.out.println("Point-to-Point Dijkstra processed: " + pointToPointNodesProcessed + " nodes");
        System.out.println("Efficiency gain: " + (standardNodesProcessed - pointToPointNodesProcessed) +
                " fewer nodes processed (" +
                (100 - (pointToPointNodesProcessed * 100.0 / standardNodesProcessed)) +
                "% reduction)");

        System.out.println("\nThis efficiency gain becomes more significant in larger graphs.");
    }

    /**
     * Count the number of nodes processed by the algorithm
     */
    private static int countNodesProcessed(Graph graph, int src, boolean earlyTermination, int dst) {
        int[] distTo = new int[graph.size()];
        Arrays.fill(distTo, -1);

        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> a.distFromStart - b.distFromStart
        );

        pq.offer(new State(src, 0));
        int nodesProcessed = 0;

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;

            if (distTo[curNode] != -1) {
                continue;
            }

            distTo[curNode] = curDistFromStart;
            nodesProcessed++;

            // Early termination if requested and destination reached
            if (earlyTermination && curNode == dst) {
                break;
            }

            for (Edge edge : graph.neighbors(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDistFromStart + edge.weight;

                if (distTo[nextNode] == -1) {
                    pq.offer(new State(nextNode, nextDist));
                }
            }
        }

        return nodesProcessed;
    }

    /**
     * Demonstrate how to reconstruct the shortest path
     */
    private static void demonstratePathReconstruction(Graph graph, int src, int dst) {
        System.out.println("\nReconstructing the Shortest Path:");
        System.out.println("--------------------------------");

        // Array to store the shortest distance from src to each node
        int[] distTo = new int[graph.size()];
        Arrays.fill(distTo, -1);

        // Array to store the previous node in the shortest path
        int[] prevNode = new int[graph.size()];
        Arrays.fill(prevNode, -1);

        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> a.distFromStart - b.distFromStart
        );

        pq.offer(new State(src, 0));

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;

            if (distTo[curNode] != -1) {
                continue;
            }

            distTo[curNode] = curDistFromStart;

            if (curNode == dst) {
                break;
            }

            for (Edge edge : graph.neighbors(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDistFromStart + edge.weight;

                if (distTo[nextNode] == -1) {
                    pq.offer(new State(nextNode, nextDist));
                    prevNode[nextNode] = curNode;  // Record the previous node
                }
            }
        }

        // If destination is not reachable
        if (distTo[dst] == -1) {
            System.out.println("No path exists from node " + src + " to node " + dst);
            return;
        }

        // Reconstruct the path
        List<Integer> path = new ArrayList<>();
        for (int at = dst; at != -1; at = prevNode[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        // Print the path
        System.out.println("Shortest path from " + src + " to " + dst + ":");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\nTotal distance: " + distTo[dst]);
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