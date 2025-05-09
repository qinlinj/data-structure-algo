package org.qinlinj.algoframework._600_graph_algo._620_union_find._621_union_find_algorithm;

/**
 * Weighted Union-Find Implementation
 * <p>
 * This class implements the Union-Find data structure with the weight balancing
 * optimization. By keeping track of the size ("weight") of each tree, we can
 * ensure that smaller trees are always attached to larger trees, which helps
 * maintain balance and prevents degeneration into long chains.
 * <p>
 * Time Complexity:
 * - find: O(log N) in the worst case
 * - union: O(log N) in the worst case
 * - connected: O(log N) in the worst case
 * <p>
 * This is a significant improvement over the O(N) complexity of the basic implementation.
 */
public class _621_c_UnionFindWeightBalanced {

    /**
     * Main method to demonstrate the weight-balanced Union-Find implementation
     */
    public static void main(String[] args) {
        System.out.println("WEIGHT-BALANCED UNION-FIND IMPLEMENTATION");
        System.out.println("========================================");

        // Create a example scenario to demonstrate weight balancing
        int n = 10;
        WeightedUnionFind uf = new WeightedUnionFind(n);

        System.out.println("Initial state: 10 singleton components");
        printState(uf, n);

        // Connect some elements
        System.out.println("\nPerforming unions with weight balancing...");

        // Create a tree rooted at 0 containing {0, 1, 2}
        uf.union(0, 1);
        uf.union(0, 2);

        // Create a tree rooted at 3 containing {3, 4}
        uf.union(3, 4);

        printState(uf, n);

        System.out.println("\nNow, when we union tree {0,1,2} with tree {3,4}:");
        System.out.println("Since tree {0,1,2} is larger, {3,4} will be attached to {0,1,2}");
        uf.union(0, 3);

        printState(uf, n);

        System.out.println("\nAdvantages of weight-balanced trees:");
        System.out.println("1. Prevents trees from becoming too deep");
        System.out.println("2. Guarantees log(n) height for all operations");
        System.out.println("3. Significant performance improvement for large datasets");
        System.out.println();

        System.out.println("Example of how balancing works:");
        System.out.println("- When a tree with size m merges with a tree of size n,");
        System.out.println("  the height increases only when m ≥ n");
        System.out.println("- This ensures that a node can only increase its depth by 1");
        System.out.println("  when its tree doubles in size");
        System.out.println("- This leads to a maximum tree height of log(n)");
        System.out.println();

        runPerformanceComparison();
    }

    /**
     * Helper method to print the current state of the Union-Find data structure
     */
    private static void printState(WeightedUnionFind uf, int n) {
        System.out.println("Connected components: " + uf.count());

        // Find all root nodes
        boolean[] isRoot = new boolean[n];
        int[] rootOf = new int[n];

        for (int i = 0; i < n; i++) {
            rootOf[i] = uf.find(i);
            isRoot[rootOf[i]] = true;
        }

        // Print each tree
        for (int i = 0; i < n; i++) {
            if (isRoot[i]) {
                System.out.println("Tree rooted at " + i + " with size " + uf.size(i) + ":");
                for (int j = 0; j < n; j++) {
                    if (rootOf[j] == i) {
                        System.out.print(j + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * Helper method to demonstrate the performance difference between
     * basic and weight-balanced Union-Find implementations
     */
    private static void runPerformanceComparison() {
        System.out.println("PERFORMANCE COMPARISON: BASIC VS. WEIGHTED");
        System.out.println("------------------------------------------");

        // Create a worst-case scenario for basic Union-Find:
        // Repeatedly connecting elements in a linear chain
        int n = 10000;

        // Basic Union-Find
        _621_b_UnionFindBasic.UnionFind basicUF = new _621_b_UnionFindBasic.UnionFind(n);

        // Weighted Union-Find
        WeightedUnionFind weightedUF = new WeightedUnionFind(n);

        // Time the operations for basic Union-Find
        long startTime = System.nanoTime();
        // Create a long chain, which is the worst case for basic UF
        for (int i = 1; i < n; i++) {
            basicUF.union(i - 1, i);
        }
        // Find the root of the last element
        basicUF.find(n - 1);
        long basicTime = System.nanoTime() - startTime;

        // Time the operations for weighted Union-Find
        startTime = System.nanoTime();
        // Same operations
        for (int i = 1; i < n; i++) {
            weightedUF.union(i - 1, i);
        }
        // Find the root of the last element
        weightedUF.find(n - 1);
        long weightedTime = System.nanoTime() - startTime;

        System.out.println("Time for basic Union-Find: " + basicTime / 1000000.0 + " ms");
        System.out.println("Time for weighted Union-Find: " + weightedTime / 1000000.0 + " ms");
        System.out.println("Speedup: " + (basicTime / (double) weightedTime) + "x");

        System.out.println("\nThis demonstrates how weight balancing significantly");
        System.out.println("improves performance for larger datasets, even without");
        System.out.println("path compression optimization.");
    }

    /**
     * Union-Find implementation with weight balancing
     */
    public static class WeightedUnionFind {
        // Number of connected components
        private int count;

        // parent[i] = parent of element i
        private int[] parent;

        // size[i] = number of elements in the tree rooted at i
        // We'll refer to this as the "weight" of the tree
        private int[] size;

        /**
         * Initialize Union-Find data structure with n elements (0 to n-1)
         */
        public WeightedUnionFind(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];

            // Initialize: each element is its own root with size 1
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * Find the root of element x
         */
        public int find(int x) {
            while (parent[x] != x) {
                x = parent[x];
            }
            return x;
        }

        /**
         * Connect elements p and q, ensuring the smaller tree
         * is attached to the larger tree for balance
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) {
                return;
            }

            // Always attach the smaller tree to the larger tree
            if (size[rootP] > size[rootQ]) {
                // rootP's tree is larger, make rootP the parent
                parent[rootQ] = rootP;
                // Update the size of rootP's tree
                size[rootP] += size[rootQ];
            } else {
                // rootQ's tree is larger (or the same size), make rootQ the parent
                parent[rootP] = rootQ;
                // Update the size of rootQ's tree
                size[rootQ] += size[rootP];
            }

            count--;
        }

        /**
         * Check if elements p and q are connected
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

        /**
         * Return the size of the tree containing element x
         */
        public int size(int x) {
            return size[find(x)];
        }
    }
}