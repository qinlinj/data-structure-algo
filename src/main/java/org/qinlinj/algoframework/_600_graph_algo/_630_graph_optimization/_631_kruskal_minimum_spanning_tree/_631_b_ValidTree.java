package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._631_kruskal_minimum_spanning_tree;

/**
 * Graph Valid Tree Problem
 * <p>
 * Knowledge Points:
 * 1. A graph is a tree if and only if it satisfies two conditions:
 * - It has n-1 edges (where n is the number of nodes)
 * - It is connected (there is a path between any two nodes)
 * - Alternatively: it has no cycles and is connected
 * <p>
 * 2. Using Union-Find to detect cycles:
 * - If adding an edge connects two nodes that are already connected, a cycle is formed
 * - If we process all edges without forming cycles and end with one connected component,
 * the graph is a valid tree
 * <p>
 * 3. This problem demonstrates an important application of Union-Find: cycle detection
 * which is also a key component in Kruskal's minimum spanning tree algorithm
 * <p>
 * 4. Time complexity: O(E α(n)) where E is the number of edges and α is the inverse Ackermann function
 * Space complexity: O(V) for the Union-Find data structure
 */
public class _631_b_ValidTree {

    // Example usage
    public static void main(String[] args) {
        _631_b_ValidTree solution = new _631_b_ValidTree();

        // Example 1: Valid tree
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println("Example 1 is a valid tree: " + solution.validTree(n1, edges1));

        // Example 2: Invalid tree (contains cycle)
        int n2 = 5;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println("Example 2 is a valid tree: " + solution.validTree(n2, edges2));
    }

    public boolean validTree(int n, int[][] edges) {
        // A valid tree with n nodes must have exactly n-1 edges
        if (edges.length != n - 1) {
            return false;
        }

        // Initialize Union-Find data structure
        UnionFind uf = new UnionFind(n);

        // Process each edge
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // If nodes are already connected, adding this edge would create a cycle
            if (uf.connected(u, v)) {
                return false;
            }

            // Union the two nodes
            uf.union(u, v);
        }

        // At this point, we've verified:
        // 1. The graph has exactly n-1 edges
        // 2. Adding each edge doesn't create a cycle

        // A graph is a valid tree if there's only one connected component
        return uf.count() == 1;
    }

    // Union-Find implementation for this problem
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
                parent[x] = parent[parent[x]]; // Path compression
                x = parent[x];
            }
            return x;
        }

        public int count() {
            return count;
        }
    }
}