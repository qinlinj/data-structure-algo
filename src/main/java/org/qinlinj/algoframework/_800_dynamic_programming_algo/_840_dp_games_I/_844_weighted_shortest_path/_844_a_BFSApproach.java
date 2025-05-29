package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._844_weighted_shortest_path;

public class _844_a_BFSApproach {
    /**
     * Demonstrates why regular BFS doesn't work for weighted graphs
     * and why we need priority queue (Dijkstra's algorithm)
     */
    public static class WeightedGraphExample {

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
