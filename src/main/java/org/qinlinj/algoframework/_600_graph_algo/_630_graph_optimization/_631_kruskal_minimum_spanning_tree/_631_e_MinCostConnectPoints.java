package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._631_kruskal_minimum_spanning_tree; /**
 * Min Cost to Connect All Points
 * (LeetCode 1584: Min Cost to Connect All Points)
 * <p>
 * Knowledge Points:
 * 1. This is another application of the Minimum Spanning Tree problem:
 * - Each point in the 2D plane is a vertex in the graph
 * - The Manhattan distance between any two points forms an edge weight
 * - The goal is to find the minimum total Manhattan distance to connect all points
 * <p>
 * 2. Manhattan distance: |x1 - x2| + |y1 - y2|
 * <p>
 * 3. Implementation approach:
 * - Generate all possible edges between points with their Manhattan distances
 * - Apply Kruskal's algorithm to find the MST
 * - Use point indices from the input array to represent the points when using Union-Find
 * <p>
 * 4. Complete graph consideration:
 * - For n points, there are n(n-1)/2 possible edges
 * - This can be memory-intensive for large n
 * <p>
 * 5. Time Complexity: O(n² log n) where n is the number of points
 * - O(n²) for generating all edges
 * - O(n² log n²) = O(n² log n) for sorting edges
 * Space Complexity: O(n²) for storing all edges
 */

import java.util.*;

public class _631_e_MinCostConnectPoints {

    // Example usage
    public static void main(String[] args) {
        _631_e_MinCostConnectPoints solution = new _631_e_MinCostConnectPoints();

        // Example 1
        int[][] points1 = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        int result1 = solution.minCostConnectPoints(points1);
        System.out.println("Example 1 - Minimum cost: " + result1);
        // Expected output: 20

        // Example 2
        int[][] points2 = {{3, 12}, {-2, 5}, {-4, 1}};
        int result2 = solution.minCostConnectPoints(points2);
        System.out.println("Example 2 - Minimum cost: " + result2);
        // Expected output: 18

        // Example 3
        int[][] points3 = {{0, 0}, {1, 1}, {1, 0}, {-1, 1}};
        int result3 = solution.minCostConnectPoints(points3);
        System.out.println("Example 3 - Minimum cost: " + result3);
        // Expected output: 4
    }

    /**
     * Find the minimum cost to connect all points
     *
     * @param points array of 2D points where points[i] = [xi, yi]
     * @return minimum cost to connect all points (sum of Manhattan distances)
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        // Generate all edges with their Manhattan distances
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];

                // Calculate Manhattan distance
                int distance = Math.abs(xi - xj) + Math.abs(yi - yj);

                // Store edge as [from_index, to_index, distance]
                edges.add(new int[]{i, j, distance});
            }
        }

        // Sort edges by distance (ascending)
        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        // Apply Kruskal's algorithm
        UnionFind uf = new UnionFind(n);
        int totalCost = 0;
        int edgesAdded = 0;

        for (int[] edge : edges) {
            int point1 = edge[0];
            int point2 = edge[1];
            int distance = edge[2];

            // If adding this edge doesn't create a cycle
            if (!uf.connected(point1, point2)) {
                uf.union(point1, point2);
                totalCost += distance;
                edgesAdded++;

                // MST of n points will have exactly n-1 edges
                if (edgesAdded == n - 1) {
                    break;
                }
            }
        }

        return totalCost;
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