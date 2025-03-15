package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GraphImpl implements Graph {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    private boolean isDirected;

    private int[] indegrees;
    private int[] outdegrees;

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
}
