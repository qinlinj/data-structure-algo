package org.qinlinj.algoframework._600_graph_algo._620_union_find._621_union_find_algorithm;

/**
 * Union-Find with Path Compression
 * <p>
 * This class implements the Union-Find data structure with the path compression
 * optimization. Path compression flattens the tree structure during find operations,
 * making subsequent operations more efficient by bringing all nodes closer to the root.
 * <p>
 * Two approaches for path compression are implemented:
 * 1. Two-pass method (recursive implementation)
 * 2. One-pass method (iterative implementation)
 * <p>
 * Time Complexity:
 * - With path compression, all operations have amortized time complexity of nearly O(1)
 * - When combined with the weight balancing from previous implementations,
 * the amortized complexity is effectively constant for practical purposes
 */
public class _621_d_UnionFindPathCompression {

    /**
     * Main method to demonstrate path compression in Union-Find
     */
    public static void main(String[] args) {
        System.out.println("UNION-FIND WITH PATH COMPRESSION");
        System.out.println("================================");

        demonstrateOnePassCompression();

        System.out.println("\n");

        demonstrateTwoPassCompression();

        System.out.println("\n");

        compareCompressionMethods();
    }

    /**
     * Demonstrate one-pass (iterative) path compression
     */
    private static void demonstrateOnePassCompression() {
        System.out.println("\nMETHOD 1: ONE-PASS PATH COMPRESSION");
        System.out.println("-----------------------------------");
        System.out.println("The one-pass method compresses paths incrementally by");
        System.out.println("making each node point to its grandparent during traversal.");
        System.out.println();

        PathCompressedUF1 uf = new PathCompressedUF1(10);

        // Create a chain of nodes to demonstrate compression
        System.out.println("Creating a chain of nodes: 0←1←2←3←4←5←6←7←8←9");
        for (int i = 9; i > 0; i--) {
            uf.parent[i] = i - 1;
        }

        // Print initial state
        System.out.println("Initial parent array: " + arrayToString(uf.parent));

        System.out.println("\nFinding root of node 9...");
        int root = uf.find(9);
        System.out.println("Root found: " + root);

        System.out.println("Parent array after one-pass compression: " + arrayToString(uf.parent));
        System.out.println("Notice how the path has been partially compressed.");
        System.out.println("With repeated operations, the tree will become increasingly flat.");
    }

    /**
     * Demonstrate two-pass (recursive) path compression
     */
    private static void demonstrateTwoPassCompression() {
        System.out.println("\nMETHOD 2: TWO-PASS PATH COMPRESSION");
        System.out.println("-----------------------------------");
        System.out.println("The two-pass method compresses paths completely by");
        System.out.println("making every node on the path point directly to the root.");
        System.out.println();

        PathCompressedUF2 uf = new PathCompressedUF2(10);

        // Create a chain of nodes to demonstrate compression
        System.out.println("Creating a chain of nodes: 0←1←2←3←4←5←6←7←8←9");
        for (int i = 9; i > 0; i--) {
            uf.parent[i] = i - 1;
        }

        // Print initial state
        System.out.println("Initial parent array: " + arrayToString(uf.parent));

        System.out.println("\nFinding root of node 9...");
        int root = uf.find(9);
        System.out.println("Root found: " + root);

        System.out.println("Parent array after two-pass compression: " + arrayToString(uf.parent));
        System.out.println("Notice how the entire path has been completely flattened.");
        System.out.println("All nodes now point directly to the root, giving O(1) access time.");
    }

    /**
     * Compare the performance of both path compression methods
     */
    private static void compareCompressionMethods() {
        System.out.println("\nPERFORMANCE COMPARISON OF PATH COMPRESSION METHODS");
        System.out.println("------------------------------------------------");

        int n = 1000000;
        int operations = 1000000;

        // One-pass method
        PathCompressedUF1 uf1 = new PathCompressedUF1(n);
        long startTime = System.nanoTime();
        // Perform random unions and finds
        java.util.Random random = new java.util.Random(42);
        for (int i = 0; i < operations; i++) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            if (i % 2 == 0) {
                uf1.union(p, q);
            } else {
                uf1.connected(p, q);
            }
        }
        long onePassTime = System.nanoTime() - startTime;

        // Two-pass method
        PathCompressedUF2 uf2 = new PathCompressedUF2(n);
        startTime = System.nanoTime();
        // Reset random seed for identical sequence
        random = new java.util.Random(42);
        for (int i = 0; i < operations; i++) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            if (i % 2 == 0) {
                uf2.union(p, q);
            } else {
                uf2.connected(p, q);
            }
        }
        long twoPassTime = System.nanoTime() - startTime;

        System.out.println("One-pass method time: " + onePassTime / 1000000.0 + " ms");
        System.out.println("Two-pass method time: " + twoPassTime / 1000000.0 + " ms");
        System.out.println();

        if (onePassTime < twoPassTime) {
            System.out.println("One-pass method was faster by a factor of: " +
                    (twoPassTime / (double) onePassTime));
            System.out.println("The one-pass method might be faster in practice due to less recursion overhead.");
        } else {
            System.out.println("Two-pass method was faster by a factor of: " +
                    (onePassTime / (double) twoPassTime));
            System.out.println("The two-pass method often leads to more complete compression,");
            System.out.println("which can improve performance for subsequent operations.");
        }

        System.out.println("\nKey insights about path compression:");
        System.out.println("1. Both methods significantly improve performance");
        System.out.println("2. With path compression, operations approach constant time complexity");
        System.out.println("3. Two-pass provides more thorough compression but has recursion overhead");
        System.out.println("4. One-pass is simpler and often good enough for practical applications");
        System.out.println("5. When combined with weight balancing, the algorithm becomes extremely efficient");
    }

    /**
     * Helper method to convert an array to a readable string
     */
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Union-Find implementation with path compression (method 1)
     * This implementation uses the one-pass compression approach
     */
    public static class PathCompressedUF1 {
        private int count;
        private int[] parent;

        /**
         * Initialize Union-Find data structure with n elements
         */
        public PathCompressedUF1(int n) {
            this.count = n;
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * Find the root of element x with path compression
         * This is the one-pass (iterative) method that compresses the path
         * by connecting each node directly to its grandparent
         */
        public int find(int x) {
            while (parent[x] != x) {
                // Path compression: make x point to its grandparent
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        /**
         * Connect elements p and q
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) {
                return;
            }

            // Attach one root to the other
            parent[rootP] = rootQ;
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

    /**
     * Union-Find implementation with path compression (method 2)
     * This implementation uses the two-pass compression approach
     */
    public static class PathCompressedUF2 {
        private int count;
        private int[] parent;

        /**
         * Initialize Union-Find data structure with n elements
         */
        public PathCompressedUF2(int n) {
            this.count = n;
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * Find the root of element x with path compression
         * This is the two-pass (recursive) method that compresses the path
         * by making every node on the path point directly to the root
         */
        public int find(int x) {
            if (parent[x] != x) {
                // Recursively find the root and update parent[x]
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * Connect elements p and q
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) {
                return;
            }

            // Attach one root to the other
            parent[rootP] = rootQ;
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