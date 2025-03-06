package org.qinlinj.nonlinear.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class AdjList implements Graph {

    private int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public AdjList(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            this.adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
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

                if (adj[a].contains(b)) { // O(V)
                    throw new RuntimeException("");
                }
                adj[a].add(b);
                adj[b].add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int a) {

    }

    /**
     * Get the number of edges in the graph
     *
     * @return the number of edges
     */
    @Override
    public int getE() {
        return 0;
    }

    /**
     * Get the number of vertices in the graph
     *
     * @return the number of vertices
     */
    @Override
    public int getV() {
        return 0;
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
        return false;
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
     * Get the degree of the specified vertex
     *
     * @param v the vertex
     * @return the degree of the vertex
     */
    @Override
    public int degree(int v) {
        return 0;
    }
}
