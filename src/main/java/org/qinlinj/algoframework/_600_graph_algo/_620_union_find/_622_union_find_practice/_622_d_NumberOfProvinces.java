package org.qinlinj.algoframework._600_graph_algo._620_union_find._622_union_find_practice;

import java.util.*;

/**
 * LeetCode 547: Number of Provinces
 * <p>
 * Problem Description:
 * There are n cities. Some of them are connected, while some are not. If city a is connected
 * directly with city b, and city b is connected directly with city c, then city a is connected
 * indirectly with city c.
 * <p>
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * <p>
 * Given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city
 * are directly connected, and isConnected[i][j] = 0 otherwise, return the total number of provinces.
 * <p>
 * This problem is essentially asking for the number of connected components in an undirected graph,
 * which is a perfect application for Union-Find.
 * <p>
 * Key Insights:
 * - Each city is a node in the graph
 * - The matrix represents the adjacency matrix of the graph
 * - A province is equivalent to a connected component
 * - Union-Find is highly efficient at tracking and counting connected components
 * <p>
 * Time Complexity: O(n²) where n is the number of cities
 * Space Complexity: O(n)
 */
public class _622_d_NumberOfProvinces {

    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        _622_d_NumberOfProvinces solution = new _622_d_NumberOfProvinces();

        // Example 1: 3 cities, 2 provinces
        int[][] isConnected1 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println("Example 1:");
        printMatrix(isConnected1);
        System.out.println("Number of provinces: " + solution.findCircleNum(isConnected1));
        // Expected: 2 (Cities 0 and 1 form one province, city 2 forms another)

        // Example 2: 3 cities, 3 provinces
        int[][] isConnected2 = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        System.out.println("\nExample 2:");
        printMatrix(isConnected2);
        System.out.println("Number of provinces: " + solution.findCircleNum(isConnected2));
        // Expected: 3 (Each city forms its own province since there are no connections)

        // Example 3: 5 cities, complex connections
        int[][] isConnected3 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 0, 1}
        };
        System.out.println("\nExample 3:");
        printMatrix(isConnected3);
        System.out.println("Number of provinces: " + solution.findCircleNum(isConnected3));
        // Expected: 2 (Cities 0,1 form one province, cities 2,3,4 form another)
    }

    /**
     * Helper method to print a matrix
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Counts the number of provinces using Union-Find
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);

        // Process the adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // If cities i and j are connected, union them
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        // The number of connected components equals the number of provinces
        return uf.count();
    }

    /**
     * Union-Find implementation optimized for this problem
     */
    class UnionFind {
        private int count;  // Number of connected components
        private int[] parent;  // Parent pointers

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];

            // Initialize: each city is in its own province
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) return;

            // Merge provinces
            parent[rootQ] = rootP;
            count--;
        }

        public int count() {
            return count;
        }
    }
}