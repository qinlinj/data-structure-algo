package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._327_difference_array;

/**
 * Extended Explanation of Difference Arrays and Their Limitations
 * <p>
 * This class addresses two important questions about difference arrays:
 * 1. How to handle extremely large ranges (e.g., [0, 10^9])
 * 2. How to combine prefix sums and difference arrays for both efficient updates and queries
 */
public class DifferenceArrayExtended {

    /**
     * Main problems with difference arrays and their solutions
     */
    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("LIMITATIONS OF DIFFERENCE ARRAYS AND ADVANCED SOLUTIONS");
        System.out.println("===============================================================");

        System.out.println("\n1. Handling Very Large Ranges ([0, 10^9])");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem: Creating a difference array of length 10^9 is impractical.");
        System.out.println("Solutions:");

        System.out.println("  a) Sparse Array Implementation:");
        System.out.println("     - Instead of an array, use a HashMap<Integer, Integer>");
        System.out.println("     - Only store positions where changes occur");
        System.out.println("     - Time complexity: O(k log k) where k is number of operations");
        System.out.println("     - Space complexity: O(k) instead of O(10^9)");

        System.out.println("  b) Coordinate Compression:");
        System.out.println("     - Track only the positions mentioned in the input");
        System.out.println("     - Map these positions to a smaller range");
        System.out.println("     - Reduces space from O(10^9) to O(k) where k is unique positions");

        System.out.println("  c) Segment Tree or Binary Indexed Tree (Fenwick Tree):");
        System.out.println("     - Advanced data structures for range queries/updates");
        System.out.println("     - Support O(log n) operations on both updates and queries");

        System.out.println("\n2. Combining Fast Updates and Fast Queries");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Problem: Difference arrays are great for updates but not queries.");
        System.out.println("         Prefix sums are great for queries but not updates.");
        System.out.println("Solutions:");

        System.out.println("  a) Segment Tree:");
        System.out.println("     - Supports both range updates and range queries in O(log n)");
        System.out.println("     - More complex to implement but very versatile");
        System.out.println("     - Can handle sum, min, max, and other queries");

        System.out.println("  b) Lazy Propagation Segment Tree:");
        System.out.println("     - Extension of segment tree that defers updates");
        System.out.println("     - Optimizes multiple updates on the same range");

        System.out.println("  c) Binary Indexed Tree with Range Updates:");
        System.out.println("     - Simpler than segment tree but less versatile");
        System.out.println("     - Can handle sum queries with range updates");

        System.out.println("\n3. Practical Example: Sparse Difference Array");
        sparseArrayExample();
    }

    /**
     * Demonstrates a sparse implementation of the difference array technique using HashMap
     * Suitable for very large or sparse ranges
     */
    private static void sparseArrayExample() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Example of Sparse Difference Array Implementation:");

        // Operations to perform
        int[][] operations = {
                {1000000, 2000000, 5},   // Add 5 to range [1000000, 2000000]
                {1500000, 2500000, 10},  // Add 10 to range [1500000, 2500000]
                {2200000, 3000000, -3}   // Subtract 3 from range [2200000, 3000000]
        };

        // Points to query
        int[] queryPoints = {1000000, 1500000, 2000000, 2200000, 2500000, 3000000};

        // Create a sparse difference array using HashMap
        java.util.HashMap<Integer, Integer> diff = new java.util.HashMap<>();

        // Apply operations
        for (int[] op : operations) {
            int start = op[0];
            int end = op[1];
            int val = op[2];

            // Add val at start
            diff.put(start, diff.getOrDefault(start, 0) + val);

            // Subtract val at end+1
            diff.put(end + 1, diff.getOrDefault(end + 1, 0) - val);
        }

        // Sort the keys (positions where changes occur)
        java.util.ArrayList<Integer> positions = new java.util.ArrayList<>(diff.keySet());
        java.util.Collections.sort(positions);

        System.out.println("\nPositions with changes:");
        for (int pos : positions) {
            System.out.println("Position " + pos + ": Change by " + diff.get(pos));
        }

        // Query specific points
        System.out.println("\nQuery results:");
        for (int point : queryPoints) {
            int value = 0;
            for (int pos : positions) {
                if (pos > point) {
                    break;
                }
                value += diff.get(pos);
            }
            System.out.println("Value at position " + point + ": " + value);
        }
    }

    /**
     * A brief overview of how a Segment Tree could implement both range updates and queries
     * This is pseudocode to illustrate the concept
     */
    static class SegmentTree {
        int[] tree;    // The actual tree array
        int[] lazy;    // Lazy propagation array
        int n;         // Size of the original array

        public SegmentTree(int size) {
            n = size;
            // Tree size is approximately 4n
            tree = new int[4 * n];
            lazy = new int[4 * n];
        }

        /**
         * Range update operation - adds val to all elements in range [left, right]
         * Time complexity: O(log n)
         */
        public void rangeUpdate(int node, int start, int end, int left, int right, int val) {
            // Apply pending lazy updates
            if (lazy[node] != 0) {
                tree[node] += (end - start + 1) * lazy[node];

                if (start != end) { // Not a leaf node
                    lazy[2 * node + 1] += lazy[node];
                    lazy[2 * node + 2] += lazy[node];
                }

                lazy[node] = 0;
            }

            // No overlap
            if (start > right || end < left) {
                return;
            }

            // Complete overlap
            if (start >= left && end <= right) {
                tree[node] += (end - start + 1) * val;

                if (start != end) { // Not a leaf node
                    lazy[2 * node + 1] += val;
                    lazy[2 * node + 2] += val;
                }

                return;
            }

            // Partial overlap - recurse to children
            int mid = (start + end) / 2;
            rangeUpdate(2 * node + 1, start, mid, left, right, val);
            rangeUpdate(2 * node + 2, mid + 1, end, left, right, val);

            // Update current node from children
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }

        /**
         * Range query operation - returns sum of elements in range [left, right]
         * Time complexity: O(log n)
         */
        public int rangeQuery(int node, int start, int end, int left, int right) {
            // No overlap
            if (start > right || end < left) {
                return 0;
            }

            // Apply pending lazy updates
            if (lazy[node] != 0) {
                tree[node] += (end - start + 1) * lazy[node];

                if (start != end) { // Not a leaf node
                    lazy[2 * node + 1] += lazy[node];
                    lazy[2 * node + 2] += lazy[node];
                }

                lazy[node] = 0;
            }

            // Complete overlap
            if (start >= left && end <= right) {
                return tree[node];
            }

            // Partial overlap - recurse to children
            int mid = (start + end) / 2;
            int leftSum = rangeQuery(2 * node + 1, start, mid, left, right);
            int rightSum = rangeQuery(2 * node + 2, mid + 1, end, left, right);

            return leftSum + rightSum;
        }
    }

    /**
     * Key Takeaways:
     *
     * 1. Difference arrays are extremely efficient for range updates but have limitations:
     *    - They require O(n) space for the entire range
     *    - They don't support efficient range queries without reconstruction
     *
     * 2. For very large ranges, consider:
     *    - Sparse implementations using hash maps
     *    - Coordinate compression techniques
     *    - Only storing points where changes occur
     *
     * 3. For both efficient updates and queries, consider advanced data structures:
     *    - Segment Trees: Versatile but complex, O(log n) for both operations
     *    - Binary Indexed Trees: Simpler but less flexible, O(log n) for both operations
     *    - Lazy Propagation: Optimizes multiple updates on the same range
     *
     * 4. The ultimate solution for both problems is typically a Segment Tree:
     *    - Can handle very large ranges with reasonable space (O(k log n) where k is operations)
     *    - Supports both range updates and range queries efficiently
     *    - Can be extended for various query types (sum, min, max, etc.)
     */
}