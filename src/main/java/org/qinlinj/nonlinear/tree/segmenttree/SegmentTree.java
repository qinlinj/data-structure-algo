package org.qinlinj.nonlinear.tree.segmenttree;

// @formatter:off
/**
 * Segment Tree Implementation with Lazy Propagation
 *
 * CONCEPT AND PRINCIPLES:
 * A Segment Tree is a tree data structure used for storing information about intervals or segments.
 * It allows querying which segments contain a given point and updating segments in logarithmic time.
 *
 * Key advantages of Segment Trees:
 * 1. Efficient range queries - O(log n) time complexity
 * 2. Efficient range updates - O(log n) with lazy propagation
 * 3. Versatile - can be adapted for various problems (sum, min, max, gcd, etc.)
 * 4. Space efficient - requires O(n) space for n elements
 *
 * Basic operations:
 * - Build: Construct the segment tree from an array - O(n)
 * - Query: Find result for a range [l, r] - O(log n)
 * - Update: Update a single element or a range - O(log n)
 *
 * Lazy Propagation:
 * This optimization delays the propagation of updates to children nodes until necessary.
 * Without lazy propagation, updating a range would require O(n log n) time.
 * With lazy propagation, range updates can be performed in O(log n) time.
 */
public class SegmentTree {
    // The array that stores the segment tree nodes
    private int[] tree;

    // The array that stores lazy updates
    private int[] lazy;

    // The original array
    private int[] array;

    // Size of the original array
    private int n;

    /**
     * Constructor to initialize the segment tree with given array
     * Time Complexity: O(n) where n is the size of the input array
     *
     * @param arr the input array
     */
    public SegmentTree(int[] arr) {
        this.array = arr;
        this.n = arr.length;

        // Height of segment tree
        int height = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        // Maximum size of segment tree
        int maxSize = 2 * (int) Math.pow(2, height) - 1;

        // Allocate memory for segment tree and lazy tree
        tree = new int[maxSize];
        lazy = new int[maxSize];

        // Build the segment tree
        build(0, 0, n - 1);
    }
}
