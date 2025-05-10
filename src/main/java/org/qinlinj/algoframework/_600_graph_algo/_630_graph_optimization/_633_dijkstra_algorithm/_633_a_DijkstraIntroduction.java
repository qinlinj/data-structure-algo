package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._633_dijkstra_algorithm;

/**
 * Introduction to Dijkstra's Algorithm
 * <p>
 * Knowledge Points:
 * 1. Dijkstra's algorithm is used to find the shortest path from a source node to all other nodes
 * in a weighted graph.
 * <p>
 * 2. Core characteristics:
 * - It's essentially a BFS algorithm with a greedy approach
 * - Cannot handle graphs with negative weight edges (greedy approach fails)
 * - Uses a priority queue instead of a regular queue to prioritize nodes with smaller distances
 * <p>
 * 3. Key differences from standard BFS:
 * - Uses priority queue instead of regular queue
 * - Tracks distances rather than just visited/not visited
 * - May process a node's neighbors multiple times, but only updates distance if shorter
 * <p>
 * 4. Time Complexity: O(E log E) where E is the number of edges
 * Space Complexity: O(V + E) where V is the number of vertices
 */
public class _633_a_DijkstraIntroduction {

    public static void main(String[] args) {
        System.out.println("Introduction to Dijkstra's Algorithm");
        System.out.println("====================================");

        explainDijkstra();
        compareWithBFS();
        discussComplexity();
        discussLimitations();
    }

    private static void explainDijkstra() {
        System.out.println("\n1. WHAT IS DIJKSTRA'S ALGORITHM?");
        System.out.println("-------------------------------");
        System.out.println("Dijkstra's algorithm is used to find the shortest path from a source node to all");
        System.out.println("other nodes in a weighted graph. The algorithm maintains a distance array that");
        System.out.println("records the minimum distance from the source to each node.");

        System.out.println("\nCore idea:");
        System.out.println("- Start at the source node with distance 0");
        System.out.println("- Maintain a priority queue of nodes ordered by their current distance from source");
        System.out.println("- Always process the node with the smallest current distance next (greedy approach)");
        System.out.println("- For each neighbor of the current node, calculate potential new distances");
        System.out.println("- If a shorter path is found, update the distance and add to the queue");
        System.out.println("- Continue until the queue is empty or all nodes are processed");
    }

    private static void compareWithBFS() {
        System.out.println("\n2. COMPARISON WITH STANDARD BFS");
        System.out.println("------------------------------");
        System.out.println("Dijkstra's algorithm is essentially a modified BFS with two key differences:");

        System.out.println("\nDifference 1: Queue Type");
        System.out.println("- Standard BFS: Uses a regular queue (FIFO)");
        System.out.println("- Dijkstra: Uses a priority queue (smallest distance first)");

        System.out.println("\nDifference 2: Node Tracking");
        System.out.println("- Standard BFS: Uses a visited array to track visited nodes");
        System.out.println("- Dijkstra: Uses a distTo array to track minimum distances");
        System.out.println("  * This array serves both to check if a node has been visited and");
        System.out.println("    to store the shortest distance found so far");

        System.out.println("\nDifference 3: Node Processing");
        System.out.println("- Standard BFS: Each node is processed exactly once");
        System.out.println("- Dijkstra: Nodes can be added to the queue multiple times with different distances,");
        System.out.println("  but are processed (finalized) only once when they're dequeued with the minimum distance");
    }

    private static void discussComplexity() {
        System.out.println("\n3. TIME AND SPACE COMPLEXITY");
        System.out.println("----------------------------");
        System.out.println("Time Complexity: O(E log E)");
        System.out.println("- In the worst case, the priority queue may contain up to O(E) elements");
        System.out.println("  (since a node can be added multiple times, based on its in-degree)");
        System.out.println("- Each priority queue operation takes O(log E) time");
        System.out.println("- Therefore, the total time complexity is O(E log E)");
        System.out.println("\nNote: Some implementations achieve O(E log V) by using more advanced");
        System.out.println("data structures that allow updating priorities in the queue.");

        System.out.println("\nSpace Complexity: O(V + E)");
        System.out.println("- O(V) for the distance array");
        System.out.println("- O(E) for the priority queue in the worst case");
    }

    private static void discussLimitations() {
        System.out.println("\n4. LIMITATIONS AND CONSIDERATIONS");
        System.out.println("---------------------------------");
        System.out.println("Negative Edge Weights:");
        System.out.println("- Dijkstra's algorithm doesn't work with negative edge weights");
        System.out.println("- Why? The greedy approach assumes that adding more edges to a path");
        System.out.println("  will increase the total weight");
        System.out.println("- With negative weights, this assumption fails as going through a");
        System.out.println("  negative edge could make a longer path shorter");

        System.out.println("\nAlternatives for Negative Weights:");
        System.out.println("- Bellman-Ford algorithm: Can handle negative weights (but not negative cycles)");
        System.out.println("- Floyd-Warshall algorithm: For all-pairs shortest paths with negative weights");

        System.out.println("\nOptimizations:");
        System.out.println("- For point-to-point shortest path (rather than single-source to all nodes),");
        System.out.println("  the algorithm can terminate early when the destination is processed");
        System.out.println("- For dense graphs, using an array instead of a priority queue can be faster");
    }

    /**
     * This class represents the basic graph structure interface needed for Dijkstra's algorithm.
     * In a real implementation, this would be more comprehensive.
     */
    public interface Graph {
        int size();

        Iterable<Edge> neighbors(int v);
    }

    /**
     * This class represents an edge in a weighted graph.
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
}