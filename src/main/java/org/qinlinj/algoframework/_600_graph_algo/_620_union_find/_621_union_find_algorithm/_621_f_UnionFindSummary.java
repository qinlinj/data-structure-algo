package org.qinlinj.algoframework._600_graph_algo._620_union_find._621_union_find_algorithm;

/**
 * Union-Find Algorithm: Comprehensive Summary
 * <p>
 * This class provides a complete overview of the Union-Find data structure,
 * including all optimizations, implementation strategies, complexity analysis,
 * and practical applications.
 * <p>
 * The Union-Find (Disjoint Set) is an efficient data structure for tracking
 * elements partitioned into disjoint sets, with operations to:
 * - Merge two sets (union)
 * - Determine if two elements are in the same set (find/connected)
 * - Count the number of disjoint sets (count)
 */
public class _621_f_UnionFindSummary {

    /**
     * Main method with comprehensive overview and examples
     */
    public static void main(String[] args) {
        System.out.println("UNION-FIND ALGORITHM: COMPREHENSIVE SUMMARY");
        System.out.println("===========================================");

        // Overview of the algorithm
        algorithmOverview();

        // Implementation evolution
        implementationEvolution();

        // Performance analysis
        performanceAnalysis();

        // Applications
        applications();

        // Practical example
        practicalExample();
    }

    /**
     * Provides an overview of the Union-Find algorithm
     */
    private static void algorithmOverview() {
        System.out.println("\n1. ALGORITHM OVERVIEW");
        System.out.println("---------------------");

        System.out.println("Union-Find is a data structure that tracks a set of elements partitioned");
        System.out.println("into disjoint (non-overlapping) subsets. It provides near-constant time");
        System.out.println("operations for two critical operations:");
        System.out.println("- Finding which subset an element belongs to");
        System.out.println("- Merging two subsets into a single subset");
        System.out.println();

        System.out.println("Core Operations:");
        System.out.println("- find(x):       Find the representative of the set containing x");
        System.out.println("- union(p, q):   Merge the sets containing elements p and q");
        System.out.println("- connected(p, q): Check if p and q are in the same set");
        System.out.println("- count():       Return the number of disjoint sets");
        System.out.println();

        System.out.println("The data structure represents sets using rooted trees, where");
        System.out.println("each element points to its parent, and the root of the tree");
        System.out.println("serves as the representative of the set.");
    }

    /**
     * Describes the evolution of Union-Find implementations
     */
    private static void implementationEvolution() {
        System.out.println("\n2. IMPLEMENTATION EVOLUTION");
        System.out.println("--------------------------");

        System.out.println("Basic Implementation:");
        System.out.println("- Simple forest structure using a parent array");
        System.out.println("- Each element points to its parent");
        System.out.println("- Root nodes point to themselves");
        System.out.println("- Time complexity: O(n) per operation in worst case");
        System.out.println();

        System.out.println("Weight Balancing Optimization:");
        System.out.println("- Keep track of the size of each tree");
        System.out.println("- Always attach smaller trees to larger trees");
        System.out.println("- Ensures trees don't become too deep");
        System.out.println("- Improves time complexity to O(log n) per operation");
        System.out.println();

        System.out.println("Path Compression Optimization:");
        System.out.println("- Flatten the tree structure during find operations");
        System.out.println("- Two methods:");
        System.out.println("  1. One-pass: Make nodes point to their grandparent (iterative)");
        System.out.println("  2. Two-pass: Make all nodes on a path point to the root (recursive)");
        System.out.println("- Dramatically improves amortized time complexity");
        System.out.println();

        System.out.println("Fully Optimized Implementation:");
        System.out.println("- Combines path compression and weight balancing");
        System.out.println("- Nearly constant-time operations in practice");
        System.out.println("- Theoretical complexity: O(α(n)) where α is the inverse");
        System.out.println("  Ackermann function, which grows extremely slowly");
    }

    /**
     * Provides performance analysis of different implementations
     */
    private static void performanceAnalysis() {
        System.out.println("\n3. PERFORMANCE ANALYSIS");
        System.out.println("----------------------");

        System.out.println("Time Complexity Comparison:");
        System.out.println("+-----------------------+--------------------+-------------------+");
        System.out.println("| Implementation        | Operation          | Time Complexity   |");
        System.out.println("+-----------------------+--------------------+-------------------+");
        System.out.println("| Basic                 | find, union        | O(n)              |");
        System.out.println("| Weight Balanced       | find, union        | O(log n)          |");
        System.out.println("| Path Compression      | find, union        | ~O(1) amortized   |");
        System.out.println("| Fully Optimized       | find, union        | O(α(n)) ≈ O(1)    |");
        System.out.println("+-----------------------+--------------------+-------------------+");
        System.out.println();

        System.out.println("Space Complexity:");
        System.out.println("- All implementations: O(n) where n is the number of elements");
        System.out.println("- Basic: One array (parent)");
        System.out.println("- Weight Balanced: Two arrays (parent, size)");
        System.out.println("- Path Compression: One array (parent)");
        System.out.println("- Fully Optimized: Two arrays (parent, size)");
        System.out.println();

        System.out.println("Practical Considerations:");
        System.out.println("- For small datasets (n < 1000), the difference between");
        System.out.println("  implementations may not be noticeable");
        System.out.println("- For large datasets, optimizations become crucial");
        System.out.println("- The recursive path compression method may cause stack overflow");
        System.out.println("  for very deep trees in languages with limited stack space");
    }

    /**
     * Describes practical applications of Union-Find
     */
    private static void applications() {
        System.out.println("\n4. PRACTICAL APPLICATIONS");
        System.out.println("-------------------------");

        System.out.println("Graph Algorithms:");
        System.out.println("- Detecting cycles in undirected graphs");
        System.out.println("- Kruskal's algorithm for Minimum Spanning Tree");
        System.out.println("- Finding connected components in a graph");
        System.out.println();

        System.out.println("Network Analysis:");
        System.out.println("- Social network clustering (friend circles)");
        System.out.println("- Network connectivity problems");
        System.out.println("- Percolation theory simulations");
        System.out.println();

        System.out.println("Image Processing:");
        System.out.println("- Connected component labeling");
        System.out.println("- Image segmentation");
        System.out.println();

        System.out.println("Other Applications:");
        System.out.println("- Least common ancestor in trees");
        System.out.println("- Online game matching (grouping players)");
        System.out.println("- Dynamic connectivity in large datasets");
        System.out.println("- Implementing efficient equivalence relations");
    }

    /**
     * Demonstrates a practical example of using Union-Find
     */
    private static void practicalExample() {
        System.out.println("\n5. PRACTICAL EXAMPLE: PERCOLATION");
        System.out.println("--------------------------------");

        System.out.println("Percolation is a model where a system 'percolates' if there");
        System.out.println("exists a path from top to bottom through open sites.");
        System.out.println();

        int n = 5; // 5x5 grid
        boolean[][] isOpen = new boolean[n][n];
        UnionFind uf = new UnionFind(n * n + 2); // +2 for virtual top and bottom sites

        System.out.println("Simulating a " + n + "x" + n + " percolation system:");
        System.out.println("- Grid starts with all sites blocked");
        System.out.println("- We'll open sites randomly until the system percolates");
        System.out.println("- Union-Find tracks connectivity between sites");
        System.out.println();

        // Virtual top site is at index n*n, bottom at n*n+1
        int virtualTop = n * n;
        int virtualBottom = n * n + 1;

        // Connect top row to virtual top and bottom row to virtual bottom
        for (int i = 0; i < n; i++) {
            uf.union(virtualTop, i);                // Connect virtual top to first row
            uf.union(virtualBottom, n * (n - 1) + i); // Connect virtual bottom to last row
        }

        // Simulate opening sites until percolation occurs
        java.util.Random random = new java.util.Random(42);
        int openSites = 0;

        while (!uf.connected(virtualTop, virtualBottom)) {
            // Choose a random site to open
            int row = random.nextInt(n);
            int col = random.nextInt(n);

            // Skip if already open
            if (isOpen[row][col]) continue;

            // Open this site
            isOpen[row][col] = true;
            openSites++;

            // Calculate 1D index for this site
            int index = row * n + col;

            // Connect to adjacent open sites
            // Above
            if (row > 0 && isOpen[row - 1][col]) {
                uf.union(index, (row - 1) * n + col);
            }
            // Below
            if (row < n - 1 && isOpen[row + 1][col]) {
                uf.union(index, (row + 1) * n + col);
            }
            // Left
            if (col > 0 && isOpen[row][col - 1]) {
                uf.union(index, row * n + col - 1);
            }
            // Right
            if (col < n - 1 && isOpen[row][col + 1]) {
                uf.union(index, row * n + col + 1);
            }
        }

        double threshold = openSites / (double) (n * n);

        System.out.println("Results:");
        System.out.println("- System percolates after opening " + openSites + " sites");
        System.out.println("- Percolation threshold: " + threshold);
        System.out.println("- Theoretical threshold for large grids: ~0.59");

        System.out.println("\nThis example demonstrates how Union-Find efficiently");
        System.out.println("tracks connectivity in dynamically changing systems,");
        System.out.println("making it possible to solve complex problems with");
        System.out.println("surprisingly simple and efficient code.");
    }

    /**
     * The final, optimized Union-Find implementation
     * Combines both path compression and weight balancing (optional)
     */
    public static class UnionFind {
        private int count;         // Number of connected components
        private int[] parent;      // Parent references
        private int[] size;        // Size of each component (optional with path compression)

        /**
         * Initialize with n elements, each in its own set
         */
        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            this.size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;     // Each element is its own root initially
                size[i] = 1;       // Each component has size 1 initially
            }
        }

        /**
         * Find the representative (root) of the set containing element x
         * Uses path compression for efficiency
         */
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        /**
         * Merge the sets containing elements p and q
         * Uses weight balancing for efficiency
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) return;  // Already in the same set

            // Weight balancing: attach smaller tree to larger tree
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }

            count--;  // One fewer connected component
        }

        /**
         * Check if elements p and q are in the same set
         */
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        /**
         * Return the number of disjoint sets
         */
        public int count() {
            return count;
        }

        /**
         * Get the size of the component containing element x
         */
        public int componentSize(int x) {
            return size[find(x)];
        }
    }
}