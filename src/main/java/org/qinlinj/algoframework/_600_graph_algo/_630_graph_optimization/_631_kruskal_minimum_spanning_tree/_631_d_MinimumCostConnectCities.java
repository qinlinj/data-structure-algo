package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._631_kruskal_minimum_spanning_tree; /**
 * Minimum Cost to Connect All Cities
 * (LeetCode 1135: Connecting Cities With Minimum Cost)
 * <p>
 * Knowledge Points:
 * 1. This is a direct application of the Minimum Spanning Tree (MST) problem:
 * - Cities are nodes/vertices
 * - Connections between cities are edges with costs as weights
 * - We need to find a tree that connects all cities with minimum total cost
 * <p>
 * 2. Kruskal's algorithm is well-suited for this problem:
 * - Sort connections by cost (ascending)
 * - Greedily add connections that don't create cycles
 * - Use Union-Find to efficiently detect cycles
 * <p>
 * 3. Special considerations:
 * - Need to check if all cities are connected at the end
 * - In a valid MST with n cities, there should be exactly n-1 connections
 * <p>
 * 4. Time Complexity: O(E log E) where E is the number of connections (dominated by sorting)
 * Space Complexity: O(V + E) where V is the number of cities
 */

import java.util.*;

public class _631_d_MinimumCostConnectCities {

    // Example usage
    public static void main(String[] args) {
        _631_d_MinimumCostConnectCities solution = new _631_d_MinimumCostConnectCities();

        // Example: 3 cities with connections
        int n = 3;
        int[][] connections = {
                {1, 2, 5},  // Connection from city 1 to city 2 with cost 5
                {1, 3, 6},  // Connection from city 1 to city 3 with cost 6
                {2, 3, 1}   // Connection from city 2 to city 3 with cost 1
        };

        int minCost = solution.minimumCost(n, connections);
        System.out.println("Minimum cost to connect all cities: " + minCost);
        // Expected output: 6 (using connections [2,3,1] and [1,2,5])

        // Another example: 4 cities where not all can be connected
        int n2 = 4;
        int[][] connections2 = {
                {1, 2, 3},
                {3, 4, 4}
                // Missing connection between cities {1,2} and {3,4}
        };

        int minCost2 = solution.minimumCost(n2, connections2);
        System.out.println("Minimum cost for example 2: " + minCost2);
        // Expected output: -1 (impossible to connect all cities)
    }

    /**
     * Find the minimum cost to connect all cities
     *
     * @param n Number of cities (labeled from 1 to n)
     * @param connections Array where connections[i] = [city1, city2, cost]
     * @return Minimum cost to connect all cities, or -1 if not possible
     */
    public int minimumCost(int n, int[][] connections) {
        // Sort connections by cost (ascending)
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        // Initialize Union-Find data structure
        UnionFind uf = new UnionFind(n + 1); // +1 because cities are 1-indexed

        int totalCost = 0;
        int connectionsAdded = 0;

        // Process connections in order of increasing cost
        for (int[] connection : connections) {
            int city1 = connection[0];
            int city2 = connection[1];
            int cost = connection[2];

            // If adding this connection doesn't create a cycle
            if (!uf.connected(city1, city2)) {
                uf.union(city1, city2);
                totalCost += cost;
                connectionsAdded++;

                // MST of n cities will have exactly n-1 edges
                if (connectionsAdded == n - 1) {
                    break;
                }
            }
        }

        // Check if all cities are connected
        // Since cities are 1-indexed and node 0 is not used,
        // a fully connected graph should have 2 components (0 is one component)
        return uf.count() == 2 ? totalCost : -1;
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