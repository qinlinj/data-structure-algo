package org.qinlinj.nonlinear.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Graph implementation using Adjacency Matrix representation
 * <p>
 * This class represents an undirected graph using an adjacency matrix.
 * <p>
 * Adjacency Matrix Visualization:
 * <p>
 * For a graph with vertices 0, 1, 2, 3 and edges (0,1), (0,2), (1,2), (2,3):
 * <p>
 * | 0 1 2 3
 * ---+--------
 * 0 | 0 1 1 0
 * 1 | 1 0 1 0
 * 2 | 1 1 0 1
 * 3 | 0 0 1 0
 * <p>
 * Where 1 indicates an edge between vertices and 0 indicates no edge.
 * <p>
 * Space Complexity: O(V²) - wastes space for sparse graphs
 */
public class AdjMatrix implements Graph {
    private int V; // Number of vertices
    private int E; // Number of edges
    private int[][] adj; // Adjacency matrix

    /**
     * Constructs a graph by reading from a file
     * <p>
     * Example file format:
     * 4 5    (4 vertices, 5 edges)
     * 0 1    (edge between vertex 0 and 1)
     * 0 2    (edge between vertex 0 and 2)
     * ...
     * <p>
     * Time Complexity: O(E) where E is number of edges
     *
     * @param fileName path to the file containing graph data
     */
    public AdjMatrix(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            // Initialize the adjacency matrix with zeros
            this.adj = new int[V][V];

            // Read each edge and mark it in the adjacency matrix
            while ((line = reader.readLine()) != null) {
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
                if (adj[a][b] == 1) {
                    throw new RuntimeException("Parallel edge detected, which is not allowed");
                }

                // Add the edge to the adjacency matrix (for both vertices as this is an undirected graph)
                adj[a][b] = 1;
                adj[b][a] = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("%d", v));
        }
    }

    @Override
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" = %d， = %d \n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(adj[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
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
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return adj[v][w] == 1;
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

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }
}
