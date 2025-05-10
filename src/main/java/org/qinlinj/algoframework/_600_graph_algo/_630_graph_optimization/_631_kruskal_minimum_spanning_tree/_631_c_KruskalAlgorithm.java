package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._631_kruskal_minimum_spanning_tree; /**
 * Kruskal's Minimum Spanning Tree Algorithm
 * <p>
 * Knowledge Points:
 * 1. Minimum Spanning Tree (MST) definition:
 * - A tree that spans all vertices of a graph with the minimum possible total edge weight
 * - Contains exactly n-1 edges for a graph with n nodes
 * - Has no cycles and is connected
 * <p>
 * 2. Kruskal's Algorithm:
 * - Uses a greedy approach to construct an MST
 * - Steps:
 * a. Sort all edges in non-decreasing order of weight
 * b. Initialize an empty MST
 * c. Process edges one by one in sorted order:
 * - If adding the edge doesn't create a cycle, add it to the MST
 * - Otherwise, discard the edge
 * d. Continue until n-1 edges are added (or all edges processed)
 * <p>
 * 3. Union-Find is used to efficiently detect cycles when adding edges
 * <p>
 * 4. Time Complexity: O(E log E) where E is the number of edges (dominated by the sorting step)
 * Space Complexity: O(V + E) where V is the number of vertices
 * <p>
 * 5. Applications:
 * - Network design (minimizing cable length)
 * - Cluster analysis
 * - Circuit design
 */

import java.util.*;

public class _631_c_KruskalAlgorithm {

    // Example usage of Kruskal's algorithm
    public static void main(String[] args) {
        _631_c_KruskalAlgorithm kruskal = new _631_c_KruskalAlgorithm();

        // Example: Find MST on a graph with 4 vertices
        int n = 4;
        int[][] edges = {
                {1, 2, 3},  // edge from 1 to 2 with weight 3
                {1, 3, 5},  // edge from 1 to 3 with weight 5
                {2, 3, 1},  // edge from 2 to 3 with weight 1
                {2, 4, 2},  // edge from 2 to 4 with weight 2
                {3, 4, 4}   // edge from 3 to 4 with weight 4
        };

        int mstWeight = kruskal.kruskalMST(n, edges);
        System.out.println("Total weight of MST: " + mstWeight);

        // Expected output: 6 (edges 2-3, 2-4, 1-2 are in the MST)
    }

    /**
     * Implementation of Kruskal's algorithm for finding MST
     *
     * @param n Number of vertices (1 to n)
     * @param edges Array of edges where each edge is [from, to, weight]
     * @return Total weight of the MST, or -1 if impossible to create MST
     */
    public int kruskalMST(int n, int[][] edges) {
        // Sort edges by weight
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        // Initialize Union-Find data structure
        UnionFind uf = new UnionFind(n + 1); // +1 because vertices are 1-indexed

        int mstWeight = 0;
        int edgesAdded = 0;

        // Process edges in order of increasing weight
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            // If adding this edge doesn't create a cycle
            if (!uf.connected(u, v)) {
                uf.union(u, v);
                mstWeight += weight;
                edgesAdded++;

                // MST will have exactly n-1 edges
                if (edgesAdded == n - 1) {
                    break;
                }
            }
        }

        // If we couldn't add n-1 edges, then the graph is not connected
        if (edgesAdded != n - 1) {
            return -1;
        }

        return mstWeight;
    }

    private class UnionFind {
        private int count;
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) return;

            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }

            count--;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public int count() {
            return count;
        }
    }
}