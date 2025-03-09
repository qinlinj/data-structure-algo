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

    /**
     * A recursive function that builds the segment tree
     * Time Complexity: O(n) where n is the size of the input array
     *
     * @param node current node index in the segment tree
     * @param start start index of the segment represented by current node
     * @param end end index of the segment represented by current node
     *
     * Example: For array [1, 3, 5, 7, 9, 11]
     * Initial call: build(0, 0, 5)
     *
     * Tree construction visualization:
     *
     *                  [0-5]:36
     *                 /       \
     *         [0-2]:9         [3-5]:27
     *         /     \         /     \
     *    [0-1]:4   [2]:5  [3-4]:16  [5]:11
     *    /    \           /    \
     * [0]:1  [1]:3     [3]:7  [4]:9
     */
    private void build(int node, int start, int end) {
        // If leaf node, store the value from original array
        if (start == end) {
            tree[node] = array[start];
            return;
        }

        // Otherwise, recursively build left and right children
        int mid = (start + end) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);

        // Internal node stores the sum of its children
        tree[node] = tree[leftChild] + tree[rightChild];

        /*
         * Current state of tree for example array [1, 3, 5, 7, 9, 11]:
         * tree[0] = 36 (sum of entire array)
         * tree[1] = 9 (sum of [1, 3, 5])
         * tree[2] = 27 (sum of [7, 9, 11])
         * tree[3] = 4 (sum of [1, 3])
         * tree[4] = 5 (sum of [5])
         * tree[5] = 16 (sum of [7, 9])
         * tree[6] = 11 (sum of [11])
         * tree[7] = 1 (value of [1])
         * tree[8] = 3 (value of [3])
         * ... and so on
         */
    }
}
