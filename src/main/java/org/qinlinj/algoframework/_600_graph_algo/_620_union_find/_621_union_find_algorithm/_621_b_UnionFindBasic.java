package org.qinlinj.algoframework._600_graph_algo._620_union_find._621_union_find_algorithm;

/**
 * Basic Implementation of Union-Find
 * <p>
 * This class demonstrates the basic forest-based implementation of the Union-Find data structure
 * that uses an array to represent a collection of trees (a forest).
 * <p>
 * In this implementation:
 * - Each element points to its parent
 * - Root nodes point to themselves
 * - Elements are in the same set if they have the same root
 * <p>
 * Time Complexity (without optimizations):
 * - find: O(N) in worst case
 * - union: O(N) in worst case (because it calls find)
 * - connected: O(N) in worst case (because it calls find)
 */
public class _621_b_UnionFindBasic {

    /**
     * Main method to demonstrate the basic Union-Find implementation
     */
    public static void main(String[] args) {
        System.out.println("BASIC UNION-FIND IMPLEMENTATION");
        System.out.println("==============================");

        // Create a Union-Find data structure with 10 elements
        UnionFind uf = new UnionFind(10);

        System.out.println("Initial state:");
        System.out.println("- Number of components: " + uf.count());
        System.out.println("- Are 0 and 1 connected? " + uf.connected(0, 1));
        System.out.println();

        // Connect some elements
        System.out.println("Connecting elements 0 and 1...");
        uf.union(0, 1);
        System.out.println("- Number of components: " + uf.count());
        System.out.println("- Are 0 and 1 connected? " + uf.connected(0, 1));
        System.out.println();

        System.out.println("Connecting elements 2 and 3...");
        uf.union(2, 3);
        System.out.println("- Number of components: " + uf.count());
        System.out.println();

        System.out.println("Connecting elements 0 and 2...");
        uf.union(0, 2);
        System.out.println("- Number of components: " + uf.count());
        System.out.println("- Are 1 and 3 connected? " + uf.connected(1, 3));
        System.out.println("  (They should be connected due to transitivity)");
        System.out.println();

        // Visual representation of the forest
        System.out.println("Visual representation of the resulting forest:");
        printForest(uf.parent);

        System.out.println("\nLimitations of the basic implementation:");
        System.out.println("1. Trees can become unbalanced, leading to O(N) operations");
        System.out.println("2. Without path compression, repeated operations are inefficient");
        System.out.println("3. For large datasets, this implementation may be too slow");
        System.out.println();
        System.out.println("These limitations are addressed in the optimized versions.");
    }

    /**
     * Helper method to visualize the forest structure
     */
    private static void printForest(int[] parent) {
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                // This is a root node
                System.out.println("Root node: " + i);
                printTree(parent, i, "  ");
            }
        }
    }

    /**
     * Helper method to print a tree in the forest
     */
    private static void printTree(int[] parent, int root, String indent) {
        for (int i = 0; i < parent.length; i++) {
            if (i != root && parent[i] == root) {
                System.out.println(indent + "|- " + i);
                printTree(parent, i, indent + "  ");
            }
        }
    }

    /**
     * Basic implementation of the Union-Find data structure
     */
    public static class UnionFind {
        // Number of connected components
        private int count;

        // parent[i] = parent of element i
        // If parent[i] = i, then i is a root node
        private int[] parent;

        /**
         * Initialize Union-Find data structure with n elements (0 to n-1)
         * Initially, each element is in its own component
         */
        public UnionFind(int n) {
            // Initially, there are n connected components
            this.count = n;

            // Initialize parent array
            parent = new int[n];

            // Initially, each element is its own parent (root)
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * Find the root of element x
         * This determines which component x belongs to
         */
        public int find(int x) {
            // Keep following parent pointers until we reach a root
            // (an element whose parent is itself)
            while (parent[x] != x) {
                x = parent[x];
            }
            return x;
        }

        /**
         * Connect elements p and q
         * This merges the components containing p and q
         */
        public void union(int p, int q) {
            // Find the roots of p and q
            int rootP = find(p);
            int rootQ = find(q);

            // If p and q are already in the same component, do nothing
            if (rootP == rootQ) {
                return;
            }

            // Merge the components by making one root the parent of the other
            parent[rootP] = rootQ;

            // Two components have been merged into one, so decrement count
            count--;
        }

        /**
         * Check if elements p and q are in the same component
         */
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        /**
         * Return the number of connected components
         */
        public int count() {
            return count;
        }
    }
}