package org.qinlinj.nonlinear.graph.weighted;

import org.qinlinj.nonlinear.graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("V %d invalid", v));
        }
    }

    @Override
    public int getE() {
        return 0;
    }

    @Override
    public int getV() {
        return 0;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return false;
    }

    @Override
    public Collection<Integer> adj(int v) {
        return List.of();
    }

    @Override
    public int degree(int v) {
        return 0;
    }
}
