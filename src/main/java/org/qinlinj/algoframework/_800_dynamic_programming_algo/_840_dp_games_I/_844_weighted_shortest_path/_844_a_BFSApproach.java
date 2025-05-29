package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._844_weighted_shortest_path;

import java.util.*;

public class _844_a_BFSApproach {
    /**
     * Demonstrates why regular BFS doesn't work for weighted graphs
     * and why we need priority queue (Dijkstra's algorithm)
     */
    public static class WeightedGraphExample {

        /**
         * Example showing the difference between edge count and path weight
         * Graph: 0 -> 1 (weight: 100), 1 -> 2 (weight: 100), 0 -> 2 (weight: 500)
         * Direct path 0->2: 1 edge, weight 500
         * Indirect path 0->1->2: 2 edges, weight 200
         * The indirect path is cheaper despite having more edges!
         */
        public static void demonstrateWeightedGraphIssue() {
            System.out.println("=== Weighted Graph Path Comparison ===");
            System.out.println("Graph edges:");
            System.out.println("0 -> 1: weight 100");
            System.out.println("1 -> 2: weight 100");
            System.out.println("0 -> 2: weight 500");
            System.out.println();

            System.out.println("Path Analysis:");
            System.out.println("Direct path (0->2): 1 edge, total weight = 500");
            System.out.println("Indirect path (0->1->2): 2 edges, total weight = 200");
            System.out.println("Conclusion: Fewer edges ≠ Lower total weight!");
            System.out.println("This is why we need priority queue for weighted graphs.");
        }

        /**
         * Dijkstra-style approach for cheapest flights problem
         * Uses priority queue to process nodes with lowest cost first
         */
        public static int findCheapestFlightDijkstra(int n, int[][] flights, int src, int dst, int k) {
            // Build adjacency list
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] flight : flights) {
                graph.computeIfAbsent(flight[0], key -> new ArrayList<>())
                        .add(new int[]{flight[1], flight[2]});
            }

            // Priority queue: [city, cost, stops]
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(src, 0, 0));

            // Track visited nodes with minimum stops to avoid cycles
            Map<Integer, Integer> visited = new HashMap<>();

            while (!pq.isEmpty()) {
                Node current = pq.poll();

                if (current.city == dst) {
                    return current.cost;
                }

                if (current.stops > k) {
                    continue;
                }

                // Skip if we've visited this city with fewer or equal stops
                if (visited.containsKey(current.city) && visited.get(current.city) <= current.stops) {
                    continue;
                }
                visited.put(current.city, current.stops);

                // Explore neighbors
                if (graph.containsKey(current.city)) {
                    for (int[] neighbor : graph.get(current.city)) {
                        int nextCity = neighbor[0];
                        int price = neighbor[1];
                        pq.offer(new Node(nextCity, current.cost + price, current.stops + 1));
                    }
                }
            }

            return -1; // No path found
        }

        // Node class for priority queue
        static class Node implements Comparable<Node> {
            int city;
            int cost;
            int stops;

            Node(int city, int cost, int stops) {
                this.city = city;
                this.cost = cost;
                this.stops = stops;
            }

            @Override
            public int compareTo(Node other) {
                return Integer.compare(this.cost, other.cost);
            }
        }
    }
}
