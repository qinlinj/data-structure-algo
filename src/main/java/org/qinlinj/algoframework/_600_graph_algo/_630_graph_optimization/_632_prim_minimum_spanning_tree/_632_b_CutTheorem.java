package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._632_prim_minimum_spanning_tree; /**
 * Cut Theorem for Minimum Spanning Trees
 * <p>
 * Knowledge Points:
 * 1. Cut Theorem Definition:
 * - A "cut" divides graph vertices into two non-empty, non-overlapping sets
 * - A "crossing edge" is an edge that connects vertices from different sides of the cut
 * - The minimum weight crossing edge across any cut must be part of the minimum spanning tree
 * <p>
 * 2. The Cut Theorem is the theoretical foundation for Prim's algorithm:
 * - It provides the justification for the greedy choice in each step
 * - It guarantees that selecting the minimum weight crossing edge is always optimal
 * <p>
 * 3. Proof Approach:
 * - Existence: For any cut, at least one crossing edge must be in the MST
 * - Optimality: If a crossing edge with minimum weight is not in the MST,
 * then the MST can be improved by including it
 * <p>
 * 4. Application in Prim's algorithm:
 * - Each step creates a cut between vertices in the tree and vertices outside
 * - The algorithm always selects the minimum weight crossing edge
 * - This ensures the final tree is a minimum spanning tree
 */

import java.util.*;

public class _632_b_CutTheorem {

    public static void main(String[] args) {
        System.out.println("Cut Theorem for Minimum Spanning Trees");
        System.out.println("=====================================");

        explainCutTheorem();
        demonstrateCutTheorem();
    }

    private static void explainCutTheorem() {
        System.out.println("\n1. UNDERSTANDING CUTS IN GRAPHS");
        System.out.println("------------------------------");
        System.out.println("A 'cut' in a graph is a partition of vertices into two non-empty,");
        System.out.println("non-overlapping sets. Edges that connect vertices from different");
        System.out.println("sets are called 'crossing edges'.");
        System.out.println("\nVisually, you can think of a cut as drawing a line through the");
        System.out.println("graph that separates some vertices from others, with the crossing");
        System.out.println("edges being those that the line intersects.");

        System.out.println("\n2. THE CUT THEOREM");
        System.out.println("-----------------");
        System.out.println("The Cut Theorem states:");
        System.out.println("For any cut in a graph, the crossing edge with minimum weight must");
        System.out.println("be included in the minimum spanning tree (MST).");

        System.out.println("\n3. WHY THE CUT THEOREM WORKS");
        System.out.println("---------------------------");
        System.out.println("The theorem can be proven by contradiction:");
        System.out.println("1. Assume we have an MST that doesn't include the minimum weight");
        System.out.println("   crossing edge (e) for some cut.");
        System.out.println("2. Adding edge (e) to the MST would create a cycle that must");
        System.out.println("   cross the cut at least once more via another edge (f).");
        System.out.println("3. Since (e) has less weight than (f), we could remove (f) and");
        System.out.println("   add (e) to get a tree with lower total weight.");
        System.out.println("4. This contradicts our assumption that we had an MST.");

        System.out.println("\n4. APPLICATION IN PRIM'S ALGORITHM");
        System.out.println("--------------------------------");
        System.out.println("Prim's algorithm applies the Cut Theorem at each step:");
        System.out.println("1. At any point, the algorithm has built a partial MST (T).");
        System.out.println("2. This creates a cut: vertices in T vs. vertices not in T.");
        System.out.println("3. According to the Cut Theorem, the minimum weight edge crossing");
        System.out.println("   this cut must be in the MST.");
        System.out.println("4. Prim's algorithm greedily selects this edge and adds its");
        System.out.println("   destination vertex to T.");
        System.out.println("5. This process repeats until all vertices are included in T.");
    }

    private static void demonstrateCutTheorem() {
        System.out.println("\nDEMONSTRATION OF THE CUT THEOREM");
        System.out.println("===============================");

        // Create a sample graph
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 7);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 2);

        System.out.println("Sample Graph:");
        printGraph(graph);

        System.out.println("\nDemonstrating Cut Theorem with a specific cut:");
        System.out.println("Cut: {0, 1, 2} and {3, 4, 5}");

        // Find crossing edges for this specific cut
        List<Graph.Edge> crossingEdges = findCrossingEdges(graph, new int[]{0, 1, 2}, new int[]{3, 4, 5});

        System.out.println("\nCrossing Edges:");
        Graph.Edge minEdge = null;
        for (Graph.Edge edge : crossingEdges) {
            System.out.println(edge);
            if (minEdge == null || edge.weight < minEdge.weight) {
                minEdge = edge;
            }
        }

        System.out.println("\nMinimum Weight Crossing Edge: " + minEdge);
        System.out.println("According to the Cut Theorem, this edge must be part of the MST.");

        // Run Prim's algorithm to verify
        System.out.println("\nVerifying with Prim's Algorithm:");
        List<Graph.Edge> mst = primMST(graph);

        System.out.println("\nEdges in the MST:");
        boolean contains = false;
        for (Graph.Edge edge : mst) {
            System.out.println(edge);
            if ((edge.source == minEdge.source && edge.destination == minEdge.destination) ||
                    (edge.source == minEdge.destination && edge.destination == minEdge.source)) {
                contains = true;
            }
        }

        System.out.println("\nDoes MST contain the minimum weight crossing edge? " + contains);
        System.out.println("This confirms the Cut Theorem!");
    }

    private static void printGraph(Graph graph) {
        for (int i = 0; i < graph.vertices; i++) {
            System.out.print("Vertex " + i + " connects to: ");
            List<Graph.Edge> edges = graph.adjList.get(i);
            for (int j = 0; j < edges.size(); j++) {
                Graph.Edge edge = edges.get(j);
                if (edge.source == i) { // Avoid printing each edge twice
                    System.out.print(edge.destination + "(" + edge.weight + ")");
                    if (j < edges.size() - 1) System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    private static List<Graph.Edge> findCrossingEdges(Graph graph, int[] setA, int[] setB) {
        Set<Integer> setASet = new HashSet<>();
        for (int vertex : setA) {
            setASet.add(vertex);
        }

        Set<Integer> setBSet = new HashSet<>();
        for (int vertex : setB) {
            setBSet.add(vertex);
        }

        List<Graph.Edge> crossingEdges = new ArrayList<>();

        // Check each edge
        for (int i = 0; i < graph.vertices; i++) {
            if (setASet.contains(i)) {
                for (Graph.Edge edge : graph.adjList.get(i)) {
                    if (setBSet.contains(edge.destination)) {
                        crossingEdges.add(edge);
                    }
                }
            }
        }

        return crossingEdges;
    }

    private static List<Graph.Edge> primMST(Graph graph) {
        // Implementation of Prim's algorithm
        boolean[] inMST = new boolean[graph.vertices];
        List<Graph.Edge> result = new ArrayList<>();

        // Use priority queue to get minimum weight edge
        PriorityQueue<Graph.Edge> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.weight, b.weight)
        );

        // Start from vertex 0
        inMST[0] = true;

        // Add all edges from vertex 0
        for (Graph.Edge edge : graph.adjList.get(0)) {
            pq.offer(edge);
        }

        while (!pq.isEmpty() && result.size() < graph.vertices - 1) {
            Graph.Edge edge = pq.poll();

            // If destination already in MST, skip
            if (inMST[edge.destination]) {
                continue;
            }

            // Add this edge to MST
            result.add(edge);
            inMST[edge.destination] = true;

            // Add all edges from the newly added vertex
            for (Graph.Edge nextEdge : graph.adjList.get(edge.destination)) {
                if (!inMST[nextEdge.destination]) {
                    pq.offer(nextEdge);
                }
            }
        }

        return result;
    }

    // Simple graph representation for demonstration
    static class Graph {
        int vertices;
        List<List<Edge>> adjList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int source, int destination, int weight) {
            Edge edge1 = new Edge(source, destination, weight);
            Edge edge2 = new Edge(destination, source, weight);
            adjList.get(source).add(edge1);
            adjList.get(destination).add(edge2);
        }

        static class Edge {
            int source;
            int destination;
            int weight;

            Edge(int source, int destination, int weight) {
                this.source = source;
                this.destination = destination;
                this.weight = weight;
            }

            @Override
            public String toString() {
                return source + " -- " + weight + " --> " + destination;
            }
        }
    }
}