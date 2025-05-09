package org.qinlinj.algoframework._600_graph_algo._620_union_find._622_union_find_practice;

import java.util.*;

/**
 * LeetCode 947: Most Stones Removed with Same Row or Column
 * <p>
 * Problem Description:
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point
 * may have at most one stone.
 * <p>
 * A stone can be removed if it shares either the same row or the same column as another stone
 * that has not been removed.
 * <p>
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
 * return the largest possible number of stones that can be removed.
 * <p>
 * This problem showcases an advanced application of Union-Find:
 * 1. Stones sharing a row or column can be considered "connected"
 * 2. Each connected component can be reduced to a single stone
 * 3. The maximum number of removals equals total stones minus number of connected components
 * <p>
 * Key Insights:
 * - We need to represent rows and columns as nodes in our Union-Find structure
 * - Stones are connections between rows and columns
 * - After all connections, the number of connected components tells us how many stones must remain
 * - We can use a clever encoding scheme to handle the row and column indices in a single Union-Find structure
 * <p>
 * Time Complexity: O(n) where n is the number of stones
 * Space Complexity: O(n)
 */
public class _622_f_MostStonesRemoved {

    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        _622_f_MostStonesRemoved solution = new _622_f_MostStonesRemoved();

        // Example 1
        int[][] stones1 = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println("Example 1 - Stones: " + Arrays.deepToString(stones1));
        System.out.println("Maximum removable stones: " + solution.removeStones(stones1));
        // Expected: 5 (leave only one stone)

        // Example 2
        int[][] stones2 = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
        System.out.println("\nExample 2 - Stones: " + Arrays.deepToString(stones2));
        System.out.println("Maximum removable stones: " + solution.removeStones(stones2));
        // Expected: 3 (leave two stones)

        // Example 3
        int[][] stones3 = {{0, 0}};
        System.out.println("\nExample 3 - Stones: " + Arrays.deepToString(stones3));
        System.out.println("Maximum removable stones: " + solution.removeStones(stones3));
        // Expected: 0 (can't remove any stones)

        // Visual explanation of Example 1
        System.out.println("\nVisual Explanation of Example 1:");
        System.out.println("Initial stones:");
        System.out.println("  0 1 2");
        System.out.println("0 O O .");
        System.out.println("1 O . O");
        System.out.println("2 . O O");
        System.out.println("\nStones can be removed in this order:");
        System.out.println("1. Remove (2,2) - connected to (2,1)");
        System.out.println("2. Remove (2,1) - connected to (0,1)");
        System.out.println("3. Remove (1,2) - connected to (1,0)");
        System.out.println("4. Remove (1,0) - connected to (0,0)");
        System.out.println("5. Remove (0,1) - connected to (0,0)");
        System.out.println("Final remaining stone: (0,0)");
    }

    /**
     * Calculates the maximum number of stones that can be removed
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;

        // Create mappings for efficient point handling
        Map<Integer, Integer> pointToId = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int encodedPoint = encodePoint(stones[i]);
            pointToId.put(encodedPoint, i);
        }

        // Track rows and columns with stones
        Map<Integer, List<Integer>> rowToPoints = new HashMap<>();
        Map<Integer, List<Integer>> colToPoints = new HashMap<>();

        // Populate the row and column maps
        for (int[] stone : stones) {
            int x = stone[0], y = stone[1];
            int encodedPoint = encodePoint(stone);

            // Add point to its row's list
            rowToPoints.putIfAbsent(x, new ArrayList<>());
            rowToPoints.get(x).add(encodedPoint);

            // Add point to its column's list
            colToPoints.putIfAbsent(y, new ArrayList<>());
            colToPoints.get(y).add(encodedPoint);
        }

        // Initialize Union-Find data structure
        UnionFind uf = new UnionFind(n);

        // Connect stones in the same column
        for (List<Integer> points : colToPoints.values()) {
            if (points.size() > 1) {
                int firstId = pointToId.get(points.get(0));
                for (int i = 1; i < points.size(); i++) {
                    int otherId = pointToId.get(points.get(i));
                    uf.union(firstId, otherId);
                }
            }
        }

        // Connect stones in the same row
        for (List<Integer> points : rowToPoints.values()) {
            if (points.size() > 1) {
                int firstId = pointToId.get(points.get(0));
                for (int i = 1; i < points.size(); i++) {
                    int otherId = pointToId.get(points.get(i));
                    uf.union(firstId, otherId);
                }
            }
        }

        // The maximum number of stones that can be removed is total stones - number of connected components
        return n - uf.count();
    }

    /**
     * Encodes a 2D point into a single integer for efficient mapping
     * This avoids collisions by scaling one coordinate to ensure uniqueness
     */
    private int encodePoint(int[] point) {
        // Using a scaling factor of 10000 ensures no conflicts for coordinates < 10000
        return point[0] * 10000 + point[1];
    }

    /**
     * Alternative solution with a direct approach using a custom Union-Find implementation
     */
    public int removeStonesAlternative(int[][] stones) {
        // We'll use a custom UnionFind that directly maps coordinates
        DSU dsu = new DSU();

        // Connect each stone's row and column
        for (int[] stone : stones) {
            // We'll use negative values for columns to distinguish them from rows
            dsu.union(stone[0], ~stone[1]);
        }

        // Count the number of connected components
        Set<Integer> components = new HashSet<>();
        for (int[] stone : stones) {
            components.add(dsu.find(stone[0]));
        }

        // Return total stones - number of components
        return stones.length - components.size();
    }

    /**
     * Custom DSU (Disjoint Set Union) implementation for the alternative solution
     * Directly handles both row and column indices without encoding
     */
    class DSU {
        Map<Integer, Integer> parent = new HashMap<>();

        int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                return x;
            }

            if (parent.get(x) != x) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        void union(int x, int y) {
            parent.put(find(x), find(y));
        }
    }

    /**
     * Standard Union-Find implementation
     */
    class UnionFind {
        private int count;
        private int[] parent;

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) return;

            parent[rootQ] = rootP;
            count--;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int count() {
            return count;
        }
    }
}