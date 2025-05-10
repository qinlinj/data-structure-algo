package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._631_kruskal_minimum_spanning_tree;

/**
 * Union-Find Data Structure (Disjoint Set)
 * <p>
 * Knowledge Points:
 * 1. Union-Find is a data structure that efficiently keeps track of elements partitioned into disjoint sets
 * 2. Key operations:
 * - find(x): Returns the representative (root) of the set containing element x
 * - union(p,q): Merges the sets containing elements p and q
 * - connected(p,q): Checks if p and q are in the same set
 * 3. Optimizations:
 * - Path compression: Flattens the structure of the tree when finding the root
 * - Union by size/rank: Attaches smaller trees to larger ones to minimize tree height
 * 4. Applications:
 * - Finding connected components in a graph
 * - Detecting cycles in undirected graphs
 * - Building Minimum Spanning Trees (Kruskal's algorithm)
 * 5. Time complexity:
 * - Nearly O(1) for all operations with path compression and union by rank
 */
public class _631_a_UnionFind {
    // Number of connected components
    private int count;
    // Array storing the parent of each node
    private int[] parent;
    // Array storing the size (weight) of each tree
    private int[] size;

    /**
     * Initialize a Union-Find data structure with n elements (0 to n-1)
     */
    public _631_a_UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Each element is initially its own parent
            size[i] = 1;    // Each set initially has size 1
        }
    }

    // Example usage demonstrating Union-Find
    public static void main(String[] args) {
        int n = 10;
        _631_a_UnionFind uf = new _631_a_UnionFind(n);

        // Connect some elements
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(7, 8);
        uf.union(1, 4);

        // Check connectivity
        System.out.println("Are 1 and 5 connected? " + uf.connected(1, 5)); // Should be true
        System.out.println("Are 1 and 8 connected? " + uf.connected(1, 8)); // Should be false
        System.out.println("Are 6 and 8 connected? " + uf.connected(6, 8)); // Should be true

        // Number of connected components
        System.out.println("Number of connected components: " + uf.count());
    }

    /**
     * Merge the sets containing elements p and q
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        // If p and q are already in the same set, do nothing
        if (rootP == rootQ)
            return;

        // Union by size: attach smaller tree to larger tree for better balance
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }

        // Two components are merged into one
        count--;
    }

    /**
     * Check if elements p and q are in the same set
     */
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    /**
     * Find the root (representative) of the set containing element x
     * Uses path compression for efficiency
     */
    public int find(int x) {
        while (parent[x] != x) {
            // Path compression: Make every other node in path point to its grandparent
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    /**
     * Get the number of connected components
     */
    public int count() {
        return count;
    }
}