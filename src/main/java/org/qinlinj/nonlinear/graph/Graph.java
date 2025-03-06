package org.qinlinj.nonlinear.graph;

import java.util.Collection;

public interface Graph {
    /**
     * Get the number of edges in the graph
     *
     * @return the number of edges
     */
    int getE();

    /**
     * Get the number of vertices in the graph
     *
     * @return the number of vertices
     */
    int getV();

    /**
     * Determine if there is an edge between two specified vertices
     *
     * @param v first vertex
     * @param w second vertex
     * @return true if an edge exists, false otherwise
     */
    boolean hasEdge(int v, int w);

    /**
     * Get all adjacent vertices of the specified vertex
     *
     * @param v the vertex
     * @return a collection of adjacent vertices
     */
    Collection<Integer> adj(int v);

    /**
     * Get the degree of the specified vertex
     *
     * @param v the vertex
     * @return the degree of the vertex
     */
    int degree(int v);
}