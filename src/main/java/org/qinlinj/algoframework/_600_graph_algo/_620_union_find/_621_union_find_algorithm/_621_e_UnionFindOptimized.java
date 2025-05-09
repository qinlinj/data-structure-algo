package org.qinlinj.algoframework._600_graph_algo._620_union_find._621_union_find_algorithm;

/**
 * Optimized Union-Find Implementation
 * <p>
 * This class presents the fully optimized Union-Find data structure that combines:
 * 1. Path compression - flattens the tree structures for faster access
 * 2. Weight balancing (optional when using path compression)
 * <p>
 * This implementation achieves near-constant time complexity for all operations,
 * making it highly efficient for problems involving dynamic connectivity in large graphs.
 * <p>
 * Time Complexity:
 * - All operations (find, union, connected): Amortized O(α(n))
 * where α(n) is the inverse Ackermann function, which grows extremely slowly
 * and is less than 5 for all practical values of n (effectively constant time)
 */
public class _621_e_UnionFindOptimized {

    /**
     * Main method to demonstrate the fully optimized Union-Find implementation
     */
    public static void main(String[] args) {
        System.out.println("FULLY OPTIMIZED UNION-FIND IMPLEMENTATION");
        System.out.println("=======================================");

        // Demonstrate with a practical example: Detecting cycles in a graph
        demonstrateCycleDetection();

        // Show performance on large datasets
        comparePerformance();

        // Summarize the optimizations and their benefits
        summarizeOptimizations();
    }

    /**
     * Demonstrate using Union-Find to detect cycles in an undirected graph
     */
    private static void demonstrateCycleDetection() {
        System.out.println("\nDETECTING CYCLES IN AN UNDIRECTED GRAPH");
        System.out.println("----------------------------------------");
        System.out.println("One practical application of Union-Find is detecting cycles in graphs.");
        System.out.println("The algorithm is simple:");
        System.out.println("1. Initialize Union-Find with all vertices");
        System.out.println("2. For each edge (u,v):");
        System.out.println("   - If u and v are already connected, there's a cycle");
        System.out.println("   - Otherwise, union u and v");
        System.out.println();

        // Example graph 1: No cycles
        // Edges: (0,1), (1,2), (2,3), (3,4)
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};

        System.out.println("Example 1: Graph without cycles");
        System.out.println("Edges: (0,1), (1,2), (2,3), (3,4)");
        System.out.println("Visual representation: 0--1--2--3--4");

        boolean hasCycle1 = detectCycle(5, edges1);
        System.out.println("Contains cycle? " + hasCycle1);

        // Example graph 2: Has a cycle
        // Edges: (0,1), (1,2), (2,3), (3,0)
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};

        System.out.println("\nExample 2: Graph with a cycle");
        System.out.println("Edges: (0,1), (1,2), (2,3), (3,0)");
        System.out.println("Visual representation: 0--1");
        System.out.println("                       |  |");
        System.out.println("                       3--2");

        boolean hasCycle2 = detectCycle(4, edges2);
        System.out.println("Contains cycle? " + hasCycle2);
    }

    /**
     * Detect if an undirected graph contains a cycle using Union-Find
     */
    private static boolean detectCycle(int n, int[][] edges) {
        OptimizedUnionFind uf = new OptimizedUnionFind(n);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // If u and v are already connected, adding edge (u,v) creates a cycle
            if (uf.connected(u, v)) {
                return true;
            }

            // Otherwise, union them
            uf.union(u, v);
        }

        return false;
    }

    /**
     * Compare performance of different Union-Find implementations
     */
    private static void comparePerformance() {
        System.out.println("\nPERFORMANCE COMPARISON OF DIFFERENT IMPLEMENTATIONS");
        System.out.println("--------------------------------------------------");

        int n = 1000000;
        int operations = 500000;

        // Basic Union-Find
        _621_b_UnionFindBasic.UnionFind basicUF = new _621_b_UnionFindBasic.UnionFind(n);

        // Weight-balanced Union-Find
        _621_c_UnionFindWeightBalanced.WeightedUnionFind weightedUF =
                new _621_c_UnionFindWeightBalanced.WeightedUnionFind(n);

        // Path-compressed Union-Find
        _621_d_UnionFindPathCompression.PathCompressedUF2 pathCompressedUF =
                new _621_d_UnionFindPathCompression.PathCompressedUF2(n);

        // Fully optimized Union-Find
        OptimizedUnionFind optimizedUF = new OptimizedUnionFind(n);

        // Create a specific test scenario: a long chain
        System.out.println("Creating a chain of " + n / 2 + " elements...");
        for (int i = 1; i < n / 2; i++) {
            basicUF.union(i - 1, i);
            weightedUF.union(i - 1, i);
            pathCompressedUF.union(i - 1, i);
            optimizedUF.union(i - 1, i);
        }

        System.out.println("\nPerforming " + operations + " find operations on the last element...");

        // Measure basic UF performance
        long startTime = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            basicUF.find(n / 2 - 1);
        }
        long basicTime = System.nanoTime() - startTime;

        // Measure weighted UF performance
        startTime = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            weightedUF.find(n / 2 - 1);
        }
        long weightedTime = System.nanoTime() - startTime;

        // Measure path-compressed UF performance
        startTime = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            pathCompressedUF.find(n / 2 - 1);
        }
        long pathCompressedTime = System.nanoTime() - startTime;

        // Measure optimized UF performance
        startTime = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            optimizedUF.find(n / 2 - 1);
        }
        long optimizedTime = System.nanoTime() - startTime;

        System.out.println("\nResults (lower is better):");
        System.out.println("Basic Union-Find:            " + basicTime / 1000000.0 + " ms");
        System.out.println("Weight-balanced Union-Find:  " + weightedTime / 1000000.0 + " ms");
        System.out.println("Path-compressed Union-Find:  " + pathCompressedTime / 1000000.0 + " ms");
        System.out.println("Fully optimized Union-Find:  " + optimizedTime / 1000000.0 + " ms");

        System.out.println("\nSpeed improvement over basic implementation:");
        System.out.println("Weight-balanced:     " + (basicTime / (double) weightedTime) + "x");
        System.out.println("Path-compressed:     " + (basicTime / (double) pathCompressedTime) + "x");
        System.out.println("Fully optimized:     " + (basicTime / (double) optimizedTime) + "x");
    }

    /**
     * Summarize the optimizations and their benefits
     */
    private static void summarizeOptimizations() {
        System.out.println("\nSUMMARY OF UNION-FIND OPTIMIZATIONS");
        System.out.println("----------------------------------");

        System.out.println("1. Basic Implementation:");
        System.out.println("   - Uses a parent array to represent the forest");
        System.out.println("   - Simple but can degenerate to O(n) operations");
        System.out.println();

        System.out.println("2. Weight Balancing Optimization:");
        System.out.println("   - Keeps track of tree sizes");
        System.out.println("   - Always attaches smaller trees to larger ones");
        System.out.println("   - Guarantees O(log n) height and operation time");
        System.out.println();

        System.out.println("3. Path Compression Optimization:");
        System.out.println("   - Flattens trees during find operations");
        System.out.println("   - Two approaches: one-pass and two-pass");
        System.out.println("   - Brings amortized operation time to nearly constant");
        System.out.println();

        System.out.println("4. Combined Optimizations:");
        System.out.println("   - Using both weight balancing and path compression");
        System.out.println("   - Achieves O(α(n)) time complexity where α is the inverse Ackermann function");
        System.out.println("   - For all practical purposes, operations are constant time");
        System.out.println();

        System.out.println("PRACTICAL APPLICATIONS OF UNION-FIND:");
        System.out.println("- Detecting cycles in graphs");
        System.out.println("- Kruskal's algorithm for minimum spanning trees");
        System.out.println("- Network connectivity problems");
        System.out.println("- Least common ancestor in trees");
        System.out.println("- Image processing (connected component labeling)");
        System.out.println("- Social network analysis (friend circles)");
    }

    /**
     * Fully optimized Union-Find implementation with path compression
     */
    public static class OptimizedUnionFind {
        // Number of connected components
        private int count;

        // parent[i] = parent of element i
        private int[] parent;

        // Optional: size[i] = size of the tree rooted at i
        // This is optional when using path compression, but can still provide benefits
        private int[] size;

        /**
         * Initialize Union-Find data structure with n elements (0 to n-1)
         */
        public OptimizedUnionFind(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * Find the root of element x with path compression
         * Uses the two-pass (recursive) approach for complete path compression
         */
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * Connect elements p and q
         * Optionally uses weight balancing for additional optimization
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) {
                return;
            }

            // Optional weight balancing
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
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
    }
}