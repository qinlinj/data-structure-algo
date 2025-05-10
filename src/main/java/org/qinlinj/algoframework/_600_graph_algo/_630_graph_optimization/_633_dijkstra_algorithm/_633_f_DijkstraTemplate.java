package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._633_dijkstra_algorithm;

/**
 * Comprehensive Dijkstra's Algorithm Template
 * <p>
 * Knowledge Points:
 * 1. Complete and reusable implementation of Dijkstra's algorithm:
 * - Single-source to all destinations
 * - Single-source to single destination
 * - Path reconstruction
 * - Custom State class for advanced use cases
 * <p>
 * 2. Code Templates:
 * - Graph representation with adjacency list
 * - Priority queue-based implementation
 * - Distance tracking and path reconstruction
 * <p>
 * 3. Extensions:
 * - Support for additional constraints (e.g., limited stops)
 * - Handling for unreachable nodes
 * - Flexible graph interface for various applications
 * <p>
 * 4. Usage Patterns:
 * - How to adapt the template to different problem requirements
 * - How to extend the State class for complex scenarios
 */

import java.util.*;

public class _633_f_DijkstraTemplate {

    /**
     * Dijkstra's algorithm to find shortest paths from source to all other nodes
     *
     * @param graph The input weighted graph
     * @param src The source node
     * @return Array where distTo[i] is the shortest distance from src to node i
     */
    public static int[] dijkstraAllDestinations(Graph graph, int src) {
        int[] distTo = new int[graph.size()];
        Arrays.fill(distTo, -1);  // Use -1 to represent infinity

        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> a.distFromStart - b.distFromStart
        );

        pq.offer(new State(src, 0));

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;

            // Skip if this node has already been processed
            if (distTo[curNode] != -1) {
                continue;
            }

            // Mark this node as processed and record its distance
            distTo[curNode] = curDistFromStart;

            // Process all neighbors
            for (Edge edge : graph.neighbors(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDistFromStart + edge.weight;

                // Only add to queue if the node hasn't been processed
                if (distTo[nextNode] == -1) {
                    pq.offer(new State(nextNode, nextDist));
                }
            }
        }

        return distTo;
    }

    /**
     * Dijkstra's algorithm to find shortest path from source to a specific destination
     *
     * @param graph The input weighted graph
     * @param src The source node
     * @param dst The destination node
     * @return The shortest distance from src to dst, or -1 if unreachable
     */
    public static int dijkstraPointToPoint(Graph graph, int src, int dst) {
        int[] distTo = new int[graph.size()];
        Arrays.fill(distTo, -1);

        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> a.distFromStart - b.distFromStart
        );

        pq.offer(new State(src, 0));

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;

            // Skip if this node has already been processed
            if (distTo[curNode] != -1) {
                continue;
            }

            // Mark this node as processed and record its distance
            distTo[curNode] = curDistFromStart;

            // If we've reached the destination, return the distance
            if (curNode == dst) {
                return curDistFromStart;
            }

            // Process all neighbors
            for (Edge edge : graph.neighbors(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDistFromStart + edge.weight;

                // Only add to queue if the node hasn't been processed
                if (distTo[nextNode] == -1) {
                    pq.offer(new State(nextNode, nextDist));
                }
            }
        }

        return -1;  // Destination is unreachable
    }

    /**
     * Dijkstra's algorithm with path reconstruction
     *
     * @param graph The input weighted graph
     * @param src The source node
     * @param dst The destination node
     * @return A DijkstraResult containing distance and path, or null if unreachable
     */
    public static DijkstraResult dijkstraWithPath(Graph graph, int src, int dst) {
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

            // Skip if this node has already been processed
            if (distTo[curNode] != -1) {
                continue;
            }

            // Mark this node as processed and record its distance
            distTo[curNode] = curDistFromStart;

            // If we've reached the destination, reconstruct the path and return
            if (curNode == dst) {
                return reconstructPath(distTo[dst], prevNode, src, dst);
            }

            // Process all neighbors
            for (Edge edge : graph.neighbors(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDistFromStart + edge.weight;

                // Only add to queue if the node hasn't been processed
                if (distTo[nextNode] == -1) {
                    prevNode[nextNode] = curNode;  // Record the previous node
                    pq.offer(new State(nextNode, nextDist));
                }
            }
        }

        return null;  // Destination is unreachable
    }

    /**
     * Helper method to reconstruct the path from source to destination
     */
    private static DijkstraResult reconstructPath(int distance, int[] prevNode, int src, int dst) {
        // Build the path from destination to source
        List<Integer> pathList = new ArrayList<>();
        for (int at = dst; at != -1; at = prevNode[at]) {
            pathList.add(at);
        }

        // Reverse to get path from source to destination
        Collections.reverse(pathList);

        // Convert list to array
        int[] path = new int[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            path[i] = pathList.get(i);
        }

        return new DijkstraResult(distance, path);
    }

    /**
     * Dijkstra's algorithm with a constraint on the maximum number of stops
     *
     * @param graph The input weighted graph
     * @param src The source node
     * @param dst The destination node
     * @param maxStops Maximum number of stops allowed
     * @return The shortest distance from src to dst with at most maxStops stops, or -1 if impossible
     */
    public static int dijkstraWithLimitedStops(Graph graph, int src, int dst, int maxStops) {
        // We need to track both node and number of stops
        // So we can't use a simple distTo array. Instead, use a more complex approach.
        int[][] distTo = new int[graph.size()][maxStops + 1];

        // Initialize all distances as infinity
        for (int i = 0; i < graph.size(); i++) {
            Arrays.fill(distTo[i], -1);
        }

        PriorityQueue<ConstrainedState> pq = new PriorityQueue<>(
                (a, b) -> a.distFromStart - b.distFromStart
        );

        pq.offer(new ConstrainedState(src, 0, 0));

        while (!pq.isEmpty()) {
            ConstrainedState state = pq.poll();
            int curNode = state.node;
            int curDistFromStart = state.distFromStart;
            int curStops = state.stops;

            // If we've reached the destination, return the distance
            if (curNode == dst) {
                return curDistFromStart;
            }

            // If we've used all allowed stops, skip processing neighbors
            if (curStops >= maxStops) {
                continue;
            }

            // Skip if we've found a better path to this node with the same or fewer stops
            if (distTo[curNode][curStops] != -1 && distTo[curNode][curStops] <= curDistFromStart) {
                continue;
            }

            // Record this distance
            distTo[curNode][curStops] = curDistFromStart;

            // Process all neighbors
            for (Edge edge : graph.neighbors(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDistFromStart + edge.weight;
                int nextStops = curStops + 1;

                // Check if we've already found a better path to nextNode with fewer or equal stops
                if (distTo[nextNode][nextStops] == -1 || nextDist < distTo[nextNode][nextStops]) {
                    pq.offer(new ConstrainedState(nextNode, nextDist, nextStops));
                }
            }
        }

        return -1;  // No valid path found
    }

    public static void main(String[] args) {
        // Create a sample graph for demonstration
        AdjListGraph graph = new AdjListGraph(6);

        // Add edges: (from, to, weight)
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 3, 2);
        graph.addEdge(4, 5, 5);

        System.out.println("Dijkstra's Algorithm Templates");
        System.out.println("=============================");

        // Demonstrate Template 1: Single-Source to All-Destinations
        demonstrateAllDestinations(graph, 0);

        // Demonstrate Template 2: Point-to-Point
        demonstratePointToPoint(graph, 0, 5);

        // Demonstrate Template 3: With Path Reconstruction
        demonstratePathReconstruction(graph, 0, 5);

        // Demonstrate Template 4: With Constraints (Limited Stops)
        demonstrateLimitedStops(graph, 0, 5, 3);
    }

    //-------------------------------------------------------------------------
    // TEMPLATE 1: Basic Dijkstra (Single-Source to All-Destinations)
    //-------------------------------------------------------------------------

    private static void demonstrateAllDestinations(Graph graph, int src) {
        System.out.println("\n1. SINGLE-SOURCE TO ALL-DESTINATIONS");
        System.out.println("-------------------------------------");

        int[] distances = dijkstraAllDestinations(graph, src);

        System.out.println("Shortest distances from node " + src + ":");
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] != -1) {
                System.out.println("To node " + i + ": " + distances[i]);
            } else {
                System.out.println("To node " + i + ": Unreachable");
            }
        }
    }

    //-------------------------------------------------------------------------
    // TEMPLATE 2: Point-to-Point Dijkstra
    //-------------------------------------------------------------------------

    private static void demonstratePointToPoint(Graph graph, int src, int dst) {
        System.out.println("\n2. POINT-TO-POINT SHORTEST PATH");
        System.out.println("--------------------------------");

        int distance = dijkstraPointToPoint(graph, src, dst);

        if (distance != -1) {
            System.out.println("Shortest distance from node " + src + " to node " + dst + ": " + distance);
        } else {
            System.out.println("No path exists from node " + src + " to node " + dst);
        }
    }

    //-------------------------------------------------------------------------
    // TEMPLATE 3: Dijkstra with Path Reconstruction
    //-------------------------------------------------------------------------

    private static void demonstratePathReconstruction(Graph graph, int src, int dst) {
        System.out.println("\n3. PATH RECONSTRUCTION");
        System.out.println("----------------------");

        DijkstraResult result = dijkstraWithPath(graph, src, dst);

        if (result != null) {
            System.out.println("Shortest distance from node " + src + " to node " + dst + ": " + result.distance);

            System.out.print("Path: ");
            for (int i = 0; i < result.path.length; i++) {
                System.out.print(result.path[i]);
                if (i < result.path.length - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        } else {
            System.out.println("No path exists from node " + src + " to node " + dst);
        }
    }

    private static void demonstrateLimitedStops(Graph graph, int src, int dst, int maxStops) {
        System.out.println("\n4. CONSTRAINED SHORTEST PATH (LIMITED STOPS)");
        System.out.println("-------------------------------------------");

        int distance = dijkstraWithLimitedStops(graph, src, dst, maxStops);

        if (distance != -1) {
            System.out.println("Shortest distance from node " + src + " to node " + dst +
                    " with at most " + maxStops + " stops: " + distance);
        } else {
            System.out.println("No path exists from node " + src + " to node " + dst +
                    " with at most " + maxStops + " stops");
        }

        // Try with fewer stops to show the difference
        int fewerStops = maxStops - 1;
        int distance2 = dijkstraWithLimitedStops(graph, src, dst, fewerStops);

        if (distance2 != -1) {
            System.out.println("Shortest distance with at most " + fewerStops + " stops: " + distance2);
        } else {
            System.out.println("No path exists with at most " + fewerStops + " stops");
        }
    }

    /**
     * Common graph interface that works with all Dijkstra implementations
     */
    public interface Graph {
        int size();

        Iterable<Edge> neighbors(int v);
    }

    //-------------------------------------------------------------------------
    // TEMPLATE 4: Dijkstra with Constraints (e.g., Limited Stops)
    //-------------------------------------------------------------------------

    /**
     * Edge class for weighted graphs
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

    //-------------------------------------------------------------------------
    // Demonstration and Usage Examples
    //-------------------------------------------------------------------------

    /**
     * Standard adjacent list graph implementation
     */
    public static class AdjListGraph implements Graph {
        private List<Edge>[] adj;

        @SuppressWarnings("unchecked")
        public AdjListGraph(int size) {
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

    /**
     * Basic State class for Dijkstra's algorithm
     */
    public static class State {
        int node;
        int distFromStart;

        public State(int node, int distFromStart) {
            this.node = node;
            this.distFromStart = distFromStart;
        }
    }

    /**
     * Extended State class with path tracking
     */
    public static class PathState extends State {
        int[] path;

        public PathState(int node, int distFromStart, int[] path) {
            super(node, distFromStart);
            this.path = path;
        }
    }

    /**
     * Extended State class with constraints (e.g., limited stops)
     */
    public static class ConstrainedState extends State {
        int stops;  // Number of stops made so far

        public ConstrainedState(int node, int distFromStart, int stops) {
            super(node, distFromStart);
            this.stops = stops;
        }
    }

    /**
     * Class to hold the result of Dijkstra's algorithm with path reconstruction
     */
    public static class DijkstraResult {
        public final int distance;
        public final int[] path;

        public DijkstraResult(int distance, int[] path) {
            this.distance = distance;
            this.path = path;
        }
    }
}