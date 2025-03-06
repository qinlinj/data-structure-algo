package org.qinlinj.nonlinear.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Graph implementation using Adjacency List representation
 * <p>
 * This class represents an undirected graph using adjacency lists.
 * <p>
 * Adjacency List Visualization:
 * <p>
 * For a graph with vertices 0, 1, 2, 3 and edges (0,1), (0,2), (1,2), (2,3):
 * <p>
 * 0 --> 1 --> 2
 * 1 --> 0 --> 2
 * 2 --> 0 --> 1 --> 3
 * 3 --> 2
 * <p>
 * Each row represents the adjacency list for a vertex.
 * <p>
 * Space Complexity: O(V + E) where V is number of vertices and E is number of edges
 */
public class AdjList implements Graph {
    private int V; // Number of vertices
    private int E; // Number of edges
    private LinkedList<Integer>[] adj; // Adjacency list

    /**
     * Constructs a graph by reading from a file
     * <p>
     * Example file format:
     * 4 5    (4 vertices, 5 edges)
     * 0 1    (edge between vertex 0 and 1)
     * 0 2    (edge between vertex 0 and 2)
     * ...
     * <p>
     * Time Complexity: O(E*V) where E is number of edges and V is number of vertices
     *
     * @param fileName path to the file containing graph data
     */
    public AdjList(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            // Initialize the adjacency list for each vertex
            this.adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }

            // Read each edge and add to the adjacency list
            // Time Complexity: O(E) iterations, with O(V) operations inside = O(E*V)
            while ((line = reader.readLine()) != null) { // O(E)
                arr = line.split(" ");
                int a = Integer.valueOf(arr[0]);
                validateVertex(a);
                int b = Integer.valueOf(arr[1]);
                validateVertex(b);

                // Check for self-loops (edges connecting a vertex to itself)
                // Self-loop example: (a)---(a)
                if (a == b) {
                    throw new RuntimeException("Self-loop detected, which is not allowed");
                }

                // Check for parallel edges (multiple edges between same vertices)
                // Parallel edges example: (a)====(b) (two or more edges between a and b)
                // Time Complexity: O(V) for contains operation in LinkedList
                if (adj[a].contains(b)) { // O(V)
                    throw new RuntimeException("Parallel edge detected, which is not allowed");
                }

                // Add the edge to the adjacency list (for both vertices as this is an undirected graph)
                adj[a].add(b);
                adj[b].add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to demonstrate graph creation and display
     * <p>
     * It reads a graph from "data/graph.txt" and prints its representation
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AdjList adjList = new AdjList("data/graph.txt");
        System.out.println(adjList);
    }

    /**
     * Validates if the vertex is within the valid range
     *
     * @param v vertex to validate
     * @throws IllegalArgumentException if the vertex is outside the valid range
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("Vertex %d is invalid", v));
        }
    }

    /**
     * Gets the number of vertices in the graph
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
     * @return the number of edges
     */
    @Override
    public int getE() {
        return E;
    }

    /**
     * Determines if there is an edge between two vertices
     * <p>
     * Example:
     * For vertices v=0 and w=1, checks if there's a path: 0 --- 1
     * <p>
     * Time Complexity: O(V) due to the contains operation in LinkedList
     *
     * @param v first vertex
     * @param w second vertex
     * @return true if there is an edge between v and w, false otherwise
     */
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return adj[v].contains(w);
    }

    /**
     * Gets all adjacent vertices of the specified vertex
     * <p>
     * Example: For vertex v=2 with edges to vertices 0, 1, and 3
     * adj(2) returns [0, 1, 3]
     * <p>
     * Time Complexity: O(1) - just returning the list reference
     *
     * @param v the vertex
     * @return a list of adjacent vertices
     */
    @Override
    public List<Integer> adj(int v) {
        validateVertex(v);

        return adj[v];
    }

    /**
     * Gets the degree of the specified vertex
     * (Number of edges connected to the vertex)
     * <p>
     * Example: For vertex v=2 with edges to vertices 0, 1, and 3
     * degree(2) returns 3
     *
     * @param v the vertex
     * @return the degree of the vertex
     */
    @Override
    public int degree(int v) {
        return adj(v).size();
    }

    /**
     * Returns a string representation of the graph
     * Shows the number of vertices, edges, and the adjacency list
     * <p>
     * Example output:
     * Vertices = 4, Edges = 4
     * 0: 1 2
     * 1: 0 2
     * 2: 0 1 3
     * 3: 2
     *
     * @return string representation of the graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Vertices = %d, Edges = %d \n", V, E));
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
