package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// @formatter:off
/**
 * Graph Implementation
 *
 * This class implements a graph data structure using adjacency lists.
 *
 * Advantages of Adjacency List representation:
 * 1. Space Efficient: Only stores the edges that exist (O(V+E) space complexity)
 * 2. Fast iteration over all edges: Efficiently access all neighbors of a vertex
 * 3. Suitable for sparse graphs: When E << V², adjacency lists are much more space-efficient
 * 4. Flexible: Can easily handle both directed and undirected graphs
 *
 * Concept and Principles:
 * A graph G = (V, E) consists of vertices V and edges E.
 * - In this implementation, each vertex is represented by an integer from 0 to V-1.
 * - Edges are stored in TreeSet adjacency lists for each vertex.
 * - For directed graphs, an edge (u, v) is stored only in the adjacency list of u.
 * - For undirected graphs, an edge (u, v) is stored in both adjacency lists of u and v.
 * - TreeSet is used for fast lookups (O(log V)) and to maintain sorted order of adjacent vertices.
 *
 * Visual representation of a graph with the sample input:
 *
 * Input:
 * 5 5
 * 0 1
 * 1 2
 * 1 3
 * 2 4
 * 3 2
 *
 * As a directed graph:
 *      0 → 1 → 2 → 4
 *          ↓   ↑
 *          3 →
 *
 * As an undirected graph:
 *      0 — 1 — 2 — 4
 *          |   |
 *          3 —
 */
public class GraphImpl implements Graph {
    private int V; // Number of vertices
    private int E; // Number of edges
    private TreeSet<Integer>[] adj; // Adjacency lists using TreeSet for each vertex

    private boolean isDirected; // Whether the graph is directed or undirected

    private int[] indegrees;  // Array to store in-degrees of vertices (for directed graphs)
    private int[] outdegrees; // Array to store out-degrees of vertices (for directed graphs)

    /**
     * Constructs a graph by reading from a file
     *
     * Time Complexity: O(E * log V) where E is the number of edges and V is the number of vertices
     *
     * The file format should be:
     * - First line: "V E" (number of vertices and edges)
     * - Following E lines: "a b" (an edge from vertex a to vertex b)
     *
     * Example:
     * 5 5
     * 0 1
     * 1 2
     * 1 3
     * 2 4
     * 3 2
     *
     * Visual representation of the graph construction process:
     *
     * 1. Initialize with V=5, E=5, and empty adjacency lists:
     *    adj[0] = {}
     *    adj[1] = {}
     *    adj[2] = {}
     *    adj[3] = {}
     *    adj[4] = {}
     *
     * 2. Processing edge 0-1:
     *    adj[0] = {1}
     *    (If undirected: adj[1] = {0})
     *
     * 3. Processing edge 1-2:
     *    adj[1] = {0,2} (for undirected)
     *    adj[1] = {2} (for directed)
     *    (If undirected: adj[2] = {1})
     *
     * 4. And so on for each edge...
     *
     * @param fileName  The path to the file containing graph data
     * @param isDirected  Whether to create a directed graph
     */
    public GraphImpl(String fileName, boolean isDirected) {
        this.isDirected = isDirected;
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            // Initialize adjacency lists for each vertex
            this.adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }

            // Initialize in-degrees and out-degrees arrays
            this.indegrees = new int[V];
            this.outdegrees = new int[V];

            // Read and process each edge
            // Time Complexity: O(E) iterations, with O(log V) operations inside
            while ((line = reader.readLine()) != null) {
                arr = line.split(" ");
                int a = Integer.valueOf(arr[0]);
                validateVertex(a);
                int b = Integer.valueOf(arr[1]);
                validateVertex(b);

                // Check for self-loops
                if (a == b) {
                    throw new RuntimeException("Self-loop detected, which is not allowed");
                }

                // Check for parallel edges
                // Time Complexity: O(log V) for TreeSet's contains operation
                if (adj[a].contains(b)) {
                    throw new RuntimeException("Parallel edge detected, which is not allowed");
                }

                // Add edge a -> b
                adj[a].add(b);

                // Update degree information for directed graphs
                if (isDirected) {
                    outdegrees[a]++;
                    indegrees[b]++;
                }

                // For undirected graphs, also add edge b -> a
                if (!isDirected)
                    adj[b].add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to test the graph implementation
     *
     * This method creates a directed graph from a file and prints its string representation
     *
     * @param args  Command-line arguments (not used)
     */
    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl("data/graph.txt", true);
        System.out.println(graph);
    }

    /**
     * Validates if a vertex index is within the valid range
     *
     * Time Complexity: O(1)
     *
     * @param v  The vertex index to validate
     * @throws IllegalArgumentException if the vertex is invalid
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("Vertex %d is invalid", v));
        }
    }

    /**
     * Checks if the graph is directed
     *
     * Time Complexity: O(1)
     *
     * @return true if the graph is directed, false otherwise
     */
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Gets the number of vertices in the graph
     *
     * Time Complexity: O(1)
     *
     * @return the number of vertices
     */
    @Override
    public int getV() {
        return V;
    }

    /**
     * Gets the number of edges in the graph
     *
     * Time Complexity: O(1)
     *
     * @return the number of edges
     */
    @Override
    public int getE() {
        return E;
    }

    /**
     * Checks if there is an edge between two specified vertices
     *
     * Time Complexity: O(log V) due to TreeSet's contains operation
     *
     * Example:
     * For the sample graph, hasEdge(1, 2) returns true
     * hasEdge(0, 2) returns false
     *
     * @param v  The source vertex
     * @param w  The target vertex
     * @return true if there is an edge from v to w, false otherwise
     */
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return adj[v].contains(w);
    }

    /**
     * Gets all adjacent vertices of a specified vertex
     *
     * Time Complexity: O(1) to return the collection reference,
     * but iterating through all adjacent vertices would be O(degree(v))
     *
     * Example:
     * For the sample graph, adj(1) returns {2, 3} for directed
     * For undirected, adj(1) returns {0, 2, 3}
     *
     * @param v  The vertex whose adjacent vertices to get
     * @return a collection of vertices adjacent to v
     */
    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);

        return adj[v];
    }

    /**
     * Gets the degree of a specified vertex in an undirected graph
     *
     * Time Complexity: O(1) - just returns the size of the adjacency list
     *
     * Example:
     * For the sample undirected graph, degree(1) returns 3 (adjacent to 0, 2, and 3)
     *
     * @param v  The vertex whose degree to get
     * @return the degree of vertex v
     * @throws RuntimeException if the graph is directed
     */
    @Override
    public int degree(int v) {
        if (isDirected) {
            throw new RuntimeException("Only undirected graphs can calculate vertex degree");
        }
        return adj(v).size();
    }

    /**
     * Gets the in-degree of a specified vertex in a directed graph
     *
     * Time Complexity: O(1) - direct array access
     *
     * Example:
     * For the sample directed graph, indegree(2) returns 2 (edges from 1 and 3)
     *
     * @param v  The vertex whose in-degree to get
     * @return the in-degree of vertex v
     * @throws RuntimeException if the graph is undirected
     */
    public int indegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("Only directed graphs can calculate vertex in-degree");
        }
        validateVertex(v);
        return indegrees[v];
    }

    /**
     * Gets the out-degree of a specified vertex in a directed graph
     *
     * Time Complexity: O(1) - direct array access
     *
     * Example:
     * For the sample directed graph, outdegree(1) returns 2 (edges to 2 and 3)
     *
     * @param v  The vertex whose out-degree to get
     * @return the out-degree of vertex v
     * @throws RuntimeException if the graph is undirected
     */
    public int outdegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("Only directed graphs can calculate vertex out-degree");
        }
        validateVertex(v);
        return outdegrees[v];
    }

    /**
     * Returns a string representation of the graph
     *
     * Time Complexity: O(V + E) to build the string
     *
     * The representation includes:
     * - Number of vertices and edges
     * - Whether the graph is directed
     * - Adjacency list for each vertex
     *
     * Example output for the sample directed graph:
     *
     * Vertices = 5, Edges = 5, isDirected = true
     * 0: 1
     * 1: 2 3
     * 2: 4
     * 3: 2
     * 4:
     *
     * @return a string representation of the graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Vertices = %d, Edges = %d, isDirected = %b \n", V, E, isDirected));
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            for (int w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}