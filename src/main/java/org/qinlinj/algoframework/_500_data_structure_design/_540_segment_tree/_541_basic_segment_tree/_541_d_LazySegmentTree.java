package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._541_basic_segment_tree;

/**
 * Segment Tree with Lazy Propagation
 * <p>
 * This class implements an enhanced version of the segment tree that supports
 * efficient range updates using the lazy propagation technique.
 * <p>
 * Key features:
 * 1. O(log n) time complexity for both range queries and range updates
 * 2. Supports updating entire ranges of elements at once
 * 3. Uses lazy propagation to defer updates until they're actually needed
 * 4. Addresses one of the main limitations of the basic segment tree
 * <p>
 * Lazy Propagation Principle:
 * - When a range update is performed, instead of updating all nodes in the range immediately,
 * we mark those nodes as "lazy" and store the pending update
 * - When a node with pending updates is accessed later, we apply the update and propagate
 * the laziness to its children
 * - This way, we only update nodes that are actually needed for query operations
 * <p>
 * Common applications:
 * - Range addition/subtraction queries
 * - Range assignment operations
 * - Problems requiring efficient range updates and queries
 */

public class _541_d_LazySegmentTree {
    // Array to store the segment tree
    private final int[] tree;
    // Array to store lazy values for each node
    private final int[] lazy;
    // Size of the original input array
    private final int n;

    /**
     * Constructs a segment tree with lazy propagation from an array
     *
     * @param nums The input array of integers
     */
    public _541_d_LazySegmentTree(int[] nums) {
        this.n = nums.length;
        // Allocate 4*n space for the segment tree array
        this.tree = new int[4 * n];
        // Allocate space for lazy propagation values
        this.lazy = new int[4 * n];
        build(nums, 0, n - 1, 0);
    }

    /**
     * Example usage of lazy segment tree
     */
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        _541_d_LazySegmentTree lazyTree = new _541_d_LazySegmentTree(arr);

        System.out.println("Initial array: [1, 3, 5, 7, 9]");
        System.out.println("Sum of range [1, 3]: " + lazyTree.queryRange(1, 3)); // 3 + 5 + 7 = 15

        // Perform a range update - add 10 to elements in range [0, 2]
        System.out.println("\nAdding 10 to range [0, 2]...");
        lazyTree.updateRange(0, 2, 10);

        // Query after range update
        System.out.println("Sum of range [0, 4] after update: " + lazyTree.queryRange(0, 4)); // (1+10) + (3+10) + (5+10) + 7 + 9 = 55
        System.out.println("Sum of range [1, 3] after update: " + lazyTree.queryRange(1, 3)); // (3+10) + (5+10) + 7 = 35

        // Perform another range update - add 5 to elements in range [2, 4]
        System.out.println("\nAdding 5 to range [2, 4]...");
        lazyTree.updateRange(2, 4, 5);

        // Query after the second range update
        System.out.println("Sum of range [0, 4] after second update: " + lazyTree.queryRange(0, 4)); // (1+10) + (3+10) + (5+10+5) + (7+5) + (9+5) = 70
        System.out.println("Sum of range [1, 3] after second update: " + lazyTree.queryRange(1, 3)); // (3+10) + (5+10+5) + (7+5) = 40

        // Query a specific element (effectively getting the updated value at position 2)
        System.out.println("\nValue at index 2 after updates: " + lazyTree.queryRange(2, 2)); // 5+10+5 = 20
    }

    /**
     * Recursively builds the segment tree
     *
     * @param nums  The input array
     * @param start Left boundary of current segment
     * @param end   Right boundary of current segment
     * @param index Current index in the tree array
     */
    private void build(int[] nums, int start, int end, int index) {
        // Base case: leaf node (single element)
        if (start == end) {
            tree[index] = nums[start];
            return;
        }

        // Recursively build left and right subtrees
        int mid = start + (end - start) / 2;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        build(nums, start, mid, leftIndex);
        build(nums, mid + 1, end, rightIndex);

        // Combine values from children
        tree[index] = tree[leftIndex] + tree[rightIndex];
    }

    /**
     * Updates a range of elements with a value
     *
     * @param left  Start of the range to update (inclusive)
     * @param right End of the range to update (inclusive)
     * @param value Value to add to each element in the range
     */
    public void updateRange(int left, int right, int value) {
        if (left < 0 || right >= n || left > right) {
            throw new IllegalArgumentException("Invalid range: [" + left + ", " + right + "]");
        }
        updateRange(0, n - 1, 0, left, right, value);
    }

    /**
     * Recursively updates a range in the segment tree using lazy propagation
     *
     * @param start Start of current segment
     * @param end   End of current segment
     * @param index Current index in tree array
     * @param left  Start of range to update
     * @param right End of range to update
     * @param value Value to add to each element in the range
     */
    private void updateRange(int start, int end, int index, int left, int right, int value) {
        // If there are pending lazy updates, apply them first
        if (lazy[index] != 0) {
            // Update current node
            tree[index] += lazy[index] * (end - start + 1);

            // If not a leaf node, propagate lazy update to children
            if (start != end) {
                lazy[2 * index + 1] += lazy[index];
                lazy[2 * index + 2] += lazy[index];
            }

            // Reset lazy value for current node
            lazy[index] = 0;
        }

        // Case 1: No overlap with the update range
        if (start > right || end < left) {
            return;
        }

        // Case 2: Complete overlap with the update range
        if (start >= left && end <= right) {
            // Update current node
            tree[index] += value * (end - start + 1);

            // If not a leaf node, mark children as lazy
            if (start != end) {
                lazy[2 * index + 1] += value;
                lazy[2 * index + 2] += value;
            }
            return;
        }

        // Case 3: Partial overlap, split and recurse
        int mid = start + (end - start) / 2;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        updateRange(start, mid, leftIndex, left, right, value);
        updateRange(mid + 1, end, rightIndex, left, right, value);

        // Update current node based on children
        tree[index] = tree[leftIndex] + tree[rightIndex];
    }

    /**
     * Queries the sum in a given range [left, right]
     *
     * @param left  Start of the query range (inclusive)
     * @param right End of the query range (inclusive)
     * @return Sum of all elements in the range
     */
    public int queryRange(int left, int right) {
        if (left < 0 || right >= n || left > right) {
            throw new IllegalArgumentException("Invalid range: [" + left + ", " + right + "]");
        }
        return queryRange(0, n - 1, 0, left, right);
    }

    /**
     * Recursively queries the segment tree, handling lazy propagation
     *
     * @param start Start of current segment
     * @param end   End of current segment
     * @param index Current index in tree array
     * @param left  Start of query range
     * @param right End of query range
     * @return Sum of elements in the query range
     */
    private int queryRange(int start, int end, int index, int left, int right) {
        // If there are pending lazy updates, apply them first
        if (lazy[index] != 0) {
            // Update current node
            tree[index] += lazy[index] * (end - start + 1);

            // If not a leaf node, propagate lazy update to children
            if (start != end) {
                lazy[2 * index + 1] += lazy[index];
                lazy[2 * index + 2] += lazy[index];
            }

            // Reset lazy value for current node
            lazy[index] = 0;
        }

        // Case 1: No overlap with the query range
        if (start > right || end < left) {
            return 0;
        }

        // Case 2: Complete overlap with the query range
        if (start >= left && end <= right) {
            return tree[index];
        }

        // Case 3: Partial overlap, split and recurse
        int mid = start + (end - start) / 2;
        int leftSum = queryRange(start, mid, 2 * index + 1, left, right);
        int rightSum = queryRange(mid + 1, end, 2 * index + 2, left, right);

        return leftSum + rightSum;
    }
}