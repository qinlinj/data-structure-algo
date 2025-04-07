package org.qinlinj.nonlinear.graph.weighted;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.qinlinj.nonlinear.graph.Graph;

/**
 * WeightedAdjSet Class
 * <p>
 * Concept and Principles:
 * This class implements a weighted undirected graph using an adjacency set representation.
 * In this implementation, each vertex maintains a TreeMap where:
 * - Keys are adjacent vertices
 * - Values are the weights of the corresponding edges
 * <p>
 * Advantages of Weighted Adjacency Set:
 * 1. Space Efficiency: Only stores connected vertices, optimal for sparse graphs
 * 2. Fast Edge Weight Lookup: O(log V) time complexity for finding edge weights
 * 3. Fast Adjacency Iteration: Quickly iterate through all neighbors of a vertex
 * 4. Ordered Neighbors: TreeMap automatically keeps neighbors sorted by vertex id
 * 5. Memory Optimization: Compared to adjacency matrix, much more space-efficient for sparse graphs
 * <p>
 * Example Visualization:
 * Consider a road network with 3 cities:
 * - Vertex 0: New York
 * - Vertex 1: Boston
 * - Vertex 2: Washington DC
 * <p>
 * With roads:
 * - New York to Boston: 215 miles (edge 0-1, weight 215)
 * - New York to Washington DC: 225 miles (edge 0-2, weight 225)
 * - Boston to Washington DC: 440 miles (edge 1-2, weight 440)
 * <p>
 * The adjacency set representation would be:
 * adj[0] = {(1,215), (2,225)}  // New York connected to Boston (215) and Washington DC (225)
 * adj[1] = {(0,215), (2,440)}  // Boston connected to New York (215) and Washington DC (440)
 * adj[2] = {(0,225), (1,440)}  // Washington DC connected to New York (225) and Boston (440)
 */
public class WeightedAdjSet implements Graph {
    private int V;
    private int E;
    private TreeMap<Integer, Integer>[] adj;

    public WeightedAdjSet(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.valueOf(arr[0]);
            this.E = Integer.valueOf(arr[1]);

            this.adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }

            while ((line = reader.readLine()) != null) { // O(E)
                arr = line.split(" ");
                int a = Integer.valueOf(arr[0]);
                validateVertex(a);
                int b = Integer.valueOf(arr[1]);
                validateVertex(b);

                if (a == b) {
                    throw new RuntimeException("There is a self-loop edge, error");
                }

                if (adj[a].containsKey(b)) { // O(logV)
                    throw new RuntimeException("There's a parallel edge. Wrong");
                }
                int weight = Integer.valueOf(arr[2]);
                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WeightedAdjSet adjList = new WeightedAdjSet("data/weighted-graph.txt");
        System.out.println(adjList);
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("V %d invalid", v));
        }
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

        return adj[v].containsKey(w);
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        return -1;
    }

    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);

        return adj[v].keySet();
    }

    @Override
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V nums = %d，E nums = %d \n", V, E));
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
