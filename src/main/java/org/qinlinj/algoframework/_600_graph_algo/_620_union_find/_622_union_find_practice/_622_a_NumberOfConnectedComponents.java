package org.qinlinj.algoframework._600_graph_algo._620_union_find._622_union_find_practice;

/**
 * LeetCode 323: Number of Connected Components in an Undirected Graph
 * <p>
 * Problem Description:
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 * <p>
 * This problem demonstrates one of the most basic applications of the Union-Find algorithm:
 * counting connected components in a graph.
 * <p>
 * Key Insights:
 * 1. Each connected component in a graph represents a disjoint set
 * 2. Union-Find is perfect for tracking these disjoint sets dynamically
 * 3. The algorithm is straightforward: initialize each node as its own component,
 * then process each edge by merging the components of its endpoints
 * 4. The final number of connected components is directly available from the Union-Find data structure
 * <p>
 * Time Complexity: O(E + V) where E is the number of edges and V is the number of vertices
 * Space Complexity: O(V)
 */
public class _622_a_NumberOfConnectedComponents {

    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        _622_a_NumberOfConnectedComponents solution = new _622_a_NumberOfConnectedComponents();

        // Example 1
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println("Example 1 - Number of connected components: " +
                solution.countComponents(n1, edges1));
        // Expected: 2 (Components: {0,1,2} and {3,4})

        // Example 2
        int n2 = 5;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println("Example 2 - Number of connected components: " +
                solution.countComponents(n2, edges2));
        // Expected: 1 (All nodes form a single component)

        // Example 3
        int n3 = 5;
        int[][] edges3 = {};
        System.out.println("Example 3 - Number of connected components: " +
                solution.countComponents(n3, edges3));
        // Expected: 5 (Each node is a separate component since there are no edges)
    }

    /**
     * Main solution function that uses Union-Find to count connected components
     */
    public int countComponents(int n, int[][] edges) {
        // Initialize the Union-Find data structure
        UnionFind uf = new UnionFind(n);

        // Process each edge by connecting its endpoints
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        // Return the number of connected components
        return uf.count();
    }

    /**
     * Union-Find implementation for tracking connected components
     */
    class UnionFind {
        // Number of connected components
        private int count;
        // Parent pointers for each element
        private int[] parent;
        // Size/rank of each component (for balancing)
        private int[] size;

        /**
         * Initialize Union-Find data structure with n elements
         */
        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];

            // Initially, each element is its own parent and has size 1
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * Find the root of the component containing element x
         * Uses path compression for efficiency
         */
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        /**
         * Connect elements p and q by merging their components
         * Uses size-based balancing for efficiency
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            // If already in the same component, nothing to do
            if (rootP == rootQ) return;

            // Merge smaller component into larger one for balance
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }

            // Decrement the component count since we've merged two components
            count--;
        }

        /**
         * Returns the number of connected components
         */
        public int count() {
            return count;
        }
    }
}