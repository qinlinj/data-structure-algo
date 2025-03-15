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
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    private boolean isDirected;

    private int[] indegrees;
    private int[] outdegrees;

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
    public GraphImpl(String fileName, boolean isDirected) throws IOException {
        BufferedReader reader
                = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        String[] arr = line.split(" ");
        this.V = Integer.valueOf(arr[0]);
        this.E = Integer.valueOf(arr[1]);

        this.adj = new TreeSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new TreeSet<>();
        }
        this.indegrees = new int[V];
        this.outdegrees = new int[V];

        while ((line = reader.readLine()) != null) { // O(E)
            arr = line.split(" ");
            int a = Integer.valueOf(arr[0]);
            validateVertex(a);
            int b = Integer.valueOf(arr[1]);
            validateVertex(b);

            if (a == b) {
                throw new RuntimeException("");
            }
            if (adj[a].contains(b)) { // O(logV)
                throw new RuntimeException("");
            }
            adj[a].add(b); // a -> b
            if (isDirected) {
                outdegrees[a]++;
                indegrees[b]++;
            }
            if (!isDirected)
                adj[b].add(a);
        }
    }

    public static void main(String[] args) throws IOException {
        GraphImpl graph = new GraphImpl("data/graph.txt", true);
        System.out.println(graph);
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("error"));
        }
    }

    /**
     * Get the number of edges in the graph
     *
     * @return the number of edges
     */
    @Override
    public int getV() {
        return V;
    }

    @Override
    public int getE() {
        return E;
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
     * Get the degree of the specified vertex
     *
     * @param v the vertex
     * @return the degree of the vertex
     */
    @Override
    public int degree(int v) {
        if (isDirected) {
            throw new RuntimeException("");
        }
        return adj(v).size();
    }

    public int indegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("");
        }
        validateVertex(v);
        return indegrees[v];
    }

    public int outdegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("");
        }
        validateVertex(v);
        return outdegrees[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V nums = %d，E nums = %d，isDirected = %b \n", V, E, isDirected));
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
