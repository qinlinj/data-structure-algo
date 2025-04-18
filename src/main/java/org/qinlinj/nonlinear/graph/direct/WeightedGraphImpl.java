package org.qinlinj.nonlinear.graph.direct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.qinlinj.nonlinear.graph.Graph;

// @formatter:off
/**
 * Weighted Graph Implementation
 *
 * Concept and Principles:
 * - A weighted graph is a graph where each edge has an associated weight or cost.
 * - This implementation uses an adjacency list representation with TreeMap to store edges and weights.
 * - Can represent both directed and undirected graphs.
 * - For directed graphs, we maintain in-degree and out-degree for each vertex.
 *
 * Advantages:
 * - Space Efficient: O(V+E) space complexity compared to O(V²) for adjacency matrix
 * - Fast Edge Lookup: O(log V) time for edge existence check and weight retrieval using TreeMap
 * - Flexible: Supports both directed and undirected graphs
 * - Memory Efficient: Only stores edges that actually exist
 * - Fast Iteration: Quickly iterate through all edges connected to a vertex
 * - Degree Tracking: Maintains in-degree and out-degree information for directed graphs
 *
 * Visualization Example:
 * Consider a weighted directed graph with 4 vertices:
 *
 *          3
 *     0 -------> 1
 *     |         /|
 *  5  |        / | 2
 *     |       /  |
 *     v      /   v
 *     2 <---/    3
 *        4
 *
 * This would be represented as:
 * 0: (1, 3), (2, 5)
 * 1: (2, 4), (3, 2)
 * 2:
 * 3:
 *
 * The adjacency list representation uses TreeMap arrays where:
 * - The array index is the source vertex
 * - Each TreeMap contains entries where:
 *   - Key: destination vertex
 *   - Value: edge weight
 */
public class WeightedGraphImpl implements Graph {
    // Number of vertices in the graph
    private int V;

    // Number of edges in the graph
    private int E;

    // Adjacency list representation using TreeMap for fast lookup
    // Each TreeMap maps: destination vertex -> edge weight
    private TreeMap<Integer, Integer>[] adj;

    // Flag indicating whether the graph is directed
    private boolean isDirected;

    // Array tracking the number of incoming edges for each vertex (for directed graphs)
    private int[] indegrees;

    // Array tracking the number of outgoing edges for each vertex (for directed graphs)
    private int[] outdegrees;

    /**
     * Constructor that initializes the weighted graph from a file.
     *
     * The file format should be:
     * - First line: [number of vertices] [number of edges]
     * - Subsequent lines: [source vertex] [destination vertex] [weight]
     *
     * Time Complexity: O(E log V) where E is the number of edges and V is the number of vertices
     * Space Complexity: O(V + E) for storing the graph structure
     *
     * @param fileName path to the file containing the graph data
     * @param isDirected flag indicating whether the graph is directed
     */
    public WeightedGraphImpl(String fileName, boolean isDirected) {
        this.isDirected = isDirected;
        try {
            // Open the file and read the first line containing vertices and edges count
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            // Initialize the adjacency list
            this.adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }

            // Initialize the degree tracking arrays
            this.indegrees = new int[V];
            this.outdegrees = new int[V];

            // Read each edge from the file
            while ((line = reader.readLine()) != null) { // O(E)
                arr = line.split(" ");
                int a = Integer.valueOf(arr[0]); // Source vertex
                validateVertex(a);
                int b = Integer.valueOf(arr[1]); // Destination vertex
                validateVertex(b);

                // Self-loops are not allowed
                if (a == b) {
                    throw new RuntimeException("Self-loops are not allowed");
                }

                // Duplicate edges are not allowed
                if (adj[a].containsKey(b)) { // O(log V)
                    throw new RuntimeException("Parallel edges are not allowed");
                }

                // Get the edge weight and add to adjacency list
                int weight = Integer.valueOf(arr[2]);
                adj[a].put(b, weight); // O(log V)

                // Update degree information for directed graphs
                if (isDirected) {
                    outdegrees[a]++;
                    indegrees[b]++;
                }

                // For undirected graphs, add the reverse edge with the same weight
                if (!isDirected) adj[b].put(a, weight); // O(log V)
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to demonstrate the weighted graph implementation.
     *
     * Time Complexity: Depends on the graph size, same as constructor
     * Space Complexity: Depends on the graph size, same as constructor
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a weighted directed graph from a file
        WeightedGraphImpl adjList = new WeightedGraphImpl("data/weighted-graph.txt", true);
        // Print the graph structure
        System.out.println(adjList);
    }

    /**
     * Validates that a vertex identifier is within the valid range.
     *
     * Time Complexity: O(1) - constant time check
     * Space Complexity: O(1) - no additional space used
     *
     * @param v The vertex to validate
     * @throws IllegalArgumentException if the vertex is invalid
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("Vertex %d is invalid", v));
        }
    }

    /**
     * Returns whether the graph is directed.
     *
     * Time Complexity: O(1) - constant time lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @return true if the graph is directed, false otherwise
     */
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * Time Complexity: O(1) - constant time lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @return the number of vertices
     */
    @Override
    public int getV() {
        return V;
    }

    /**
     * Returns the number of edges in the graph.
     *
     * Time Complexity: O(1) - constant time lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @return the number of edges
     */
    @Override
    public int getE() {
        return E;
    }

    /**
     * Returns the weight of the edge between two vertices.
     *
     * Time Complexity: O(log V) due to TreeMap lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @param v source vertex
     * @param w destination vertex
     * @return the weight of the edge, or -1 if the edge doesn't exist
     */
    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        return -1;
    }

    /**
     * Get the degree of the specified vertex.
     * In a weighted graph context, this would typically be the sum of weights,
     * but this implementation returns 0 as it's not fully implemented.
     *
     * Time Complexity: O(1) - constant time
     * Space Complexity: O(1) - no additional space used
     *
     * @param v the vertex
     * @return the degree of the vertex
     */
    @Override
    public int degree(int v) {
        return 0; // Note: This method is not fully implemented
    }

    /**
     * Get all adjacent vertices of the specified vertex.
     *
     * Time Complexity: O(1) - constant time to return the keyset reference
     * Space Complexity: O(1) - no additional space used
     *
     * @param v the vertex
     * @return a collection of adjacent vertices
     */
    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
    }

    /**
     * Determine if there is an edge between two specified vertices.
     *
     * Time Complexity: O(log V) due to TreeMap lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @param v first vertex
     * @param w second vertex
     * @return true if an edge exists, false otherwise
     */
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    /**
     * Returns the in-degree of a vertex (number of incoming edges).
     * Only applicable for directed graphs.
     *
     * Time Complexity: O(1) - constant time lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @param v the vertex
     * @return the in-degree of the vertex
     * @throws RuntimeException if the graph is not directed
     */
    public int indegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("In-degree is only defined for directed graphs");
        }
        validateVertex(v);
        return indegrees[v];
    }

    /**
     * Returns the out-degree of a vertex (number of outgoing edges).
     * Only applicable for directed graphs.
     *
     * Time Complexity: O(1) - constant time lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @param v the vertex
     * @return the out-degree of the vertex
     * @throws RuntimeException if the graph is not directed
     */
    public int outdegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("Out-degree is only defined for directed graphs");
        }
        validateVertex(v);
        return outdegrees[v];
    }

    /**
     * Returns a string representation of the graph.
     *
     * Time Complexity: O(V + E) to iterate through all vertices and edges
     * Space Complexity: O(V + E) for the string builder
     *
     * @return a string representation of the graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d, isDirected = %b \n", V, E, isDirected));

        // For each vertex, append its adjacency list
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            Map<Integer, Integer> adjMap = adj[v];

            // For each edge, append the destination and weight
            for (Map.Entry<Integer, Integer> entry : adjMap.entrySet()) {
                sb.append("(" + entry.getKey() + ", " + entry.getValue() + ")");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}