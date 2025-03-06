package org.qinlinj.nonlinear.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class AdjSet implements Graph {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public AdjSet(String fileName) {
        try {
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
                adj[a].add(b);
                adj[b].add(a);
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

    /**
     * Get the degree of the specified vertex
     *
     * @param v the vertex
     * @return the degree of the vertex
     */
    @Override
    public int degree(int v) {
        return 0;
    }

    /**
     * Get all adjacent vertices of the specified vertex
     *
     * @param v the vertex
     * @return a collection of adjacent vertices
     */
    @Override
    public Collection<Integer> adj(int v) {
        return List.of();
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
}
