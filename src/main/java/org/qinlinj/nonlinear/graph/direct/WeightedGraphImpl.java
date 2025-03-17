package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

public class WeightedGraphImpl implements Graph {
    private int V; //
    private int E; //
    private TreeMap<Integer, Integer>[] adj;

    private boolean isDirected;

    private int[] indegrees;
    private int[] outdegrees;

    public WeightedGraphImpl(TreeMap<Integer, Integer>[] adj) {
        this.adj = adj;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("V %d invalid", v));
        }
    }

    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int getV() {
        return V;
    }

    @Override
    public int getE() {
        return E;
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        return -1;
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
        validateVertex(v);

        return adj[v].keySet();
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

        return adj[v].containsKey(w);
    }

    public int indegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("error");
        }
        validateVertex(v);
        return indegrees[v];
    }

    public int outdegree(int v) {
        if (!isDirected) {
            throw new RuntimeException("error");
        }
        validateVertex(v);
        return outdegrees[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d，E = %d，isDirected = %b \n", V, E, isDirected));
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            Map<Integer, Integer> adjMap = adj[v];
            for (Map.Entry<Integer, Integer> entry : adjMap.entrySet()) {
                sb.append("(" + entry.getKey() + ", " + entry.getValue() + ")");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
