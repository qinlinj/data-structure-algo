package org.qinlinj.nonlinear.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Graph implementation using Adjacency Set representation (TreeSet)
 * <p>
 * This class represents an undirected graph using TreeSets for each vertex's adjacency list.
 * Using TreeSet instead of LinkedList or ArrayList provides O(log V) lookup time for edge existence.
 * <p>
 * Adjacency Set Visualization:
 * <p>
 * For a graph with vertices 0, 1, 2, 3 and edges (0,1), (0,2), (1,2), (2,3):
 * <p>
 * 0: {1, 2}      [TreeSet provides ordered elements]
 * 1: {0, 2}
 * 2: {0, 1, 3}
 * 3: {2}
 * <p>
 * Space Complexity: O(V + E) where V is number of vertices and E is number of edges
 */
public class AdjSet implements Graph {
    private int V; // Number of vertices
    private int E; // Number of edges
    private TreeSet<Integer>[] adj; // Adjacency sets (using TreeSet for O(log V) operations)

    /**
     * Constructs a graph by reading from a file
     * <p>
     * Example file format:
     * 4 5    (4 vertices, 5 edges)
     * 0 1    (edge between vertex 0 and 1)
     * 0 2    (edge between vertex 0 and 2)
     * ...
     * <p>
     * Time Complexity: O(E log V) where:
     * - E is the number of edges to process
     * - Each edge insertion takes O(log V) time with TreeSet
     *
     * @param fileName path to the file containing graph data
     */
    public AdjSet(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            // Initialize the adjacency sets for each vertex
            this.adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }

            // Read each edge and add to the adjacency sets
            // Time Complexity: O(E) iterations * O(log V) per operation = O(E log V)
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
                // Time Complexity: O(log V) for contains operation in TreeSet
                if (adj[a].contains(b)) { // O(log V)
                    throw new RuntimeException("Parallel edge detected, which is not allowed");
                }

                // Add the edge to the adjacency sets (for both vertices as this is an undirected graph)
                adj[a].add(b); // O(log V)
                adj[b].add(a); // O(log V)
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
        AdjSet adjList = new AdjSet("data/graph.txt");
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
     * Get the degree of the specified vertex
     *
     * @param v the vertex
     * @return the degree of the vertex
     */
    @Override
    public int degree(int v) {
        return adj(v).size();
    }

    /**
     * Get all adjacent vertices of the specified vertex
     *
     * @param v the vertex
     * @return a collection of adjacent vertices
     */
    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);

        return adj[v];
    }

    /**
     * Determine if there is an edge between two specified vertices
     *
     * @param v first vertex
     * @param w second vertex
     * @return true if an edge exists, false otherwise
     */
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return adj[v].contains(w);
    }

    @Override
    public int getV() {
        return V;
    }

    @Override
    public int getE() {
        return E;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d，%d \n", V, E));
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
