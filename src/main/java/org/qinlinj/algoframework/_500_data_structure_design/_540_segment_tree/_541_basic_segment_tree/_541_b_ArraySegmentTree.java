package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._541_basic_segment_tree; /**
 * Segment Tree Implementation (Array-based)
 * <p>
 * This class implements a segment tree data structure using an array-based approach.
 * Instead of explicitly creating tree nodes, this implementation stores the tree
 * structure in a fixed-size array.
 * <p>
 * Key features:
 * 1. O(log n) time complexity for both range queries and single element updates
 * 2. More memory efficient than node-based implementation (no pointers needed)
 * 3. Uses a 4*n sized array to ensure sufficient space for all nodes
 * 4. Similar to how binary heaps are implemented using arrays
 * <p>
 * Array indexing logic:
 * - For a node at index i:
 * - Left child is at index 2*i + 1
 * - Right child is at index 2*i + 2
 * - Parent is at index (i-1)/2
 * <p>
 * Memory allocation:
 * - For an input array of size n, the segment tree array needs 4*n space
 * - This accounts for the worst-case scenario where the tree is not a perfect binary tree
 * - The 4*n upper bound ensures we have enough space for all possible nodes
 * <p>
 * Advantages over node-based implementation:
 * - Better cache locality due to contiguous memory allocation
 * - Less memory overhead (no need to store pointers and node objects)
 * - Potentially faster in practice despite having the same theoretical complexity
 */

import java.util.function.*;

public class _541_b_ArraySegmentTree {
    // Array to store the segment tree
    private final int[] tree;
    // Size of the original input array
    private final int n;
    // Function used to combine values (e.g., sum, max, min)
    private final BinaryOperator<Integer> merger;

    /**
     * Constructs a segment tree from an array
     *
     * @param nums   The input array of integers
     * @param merger The function to merge/aggregate values (sum, min, max, etc.)
     */
    public _541_b_ArraySegmentTree(int[] nums, BinaryOperator<Integer> merger) {
        this.n = nums.length;
        this.merger = merger;
        // Allocate 4*n space for the segment tree array
        this.tree = new int[4 * n];
        build(nums, 0, n - 1, 0);
    }

    /**
     * Example usage of the array-based segment tree
     */
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};

        // Create a segment tree for sum queries
        _541_b_ArraySegmentTree sumTree = new _541_b_ArraySegmentTree(arr, (a, b) -> a + b);
        System.out.println("Sum query [1, 3]: " + sumTree.query(1, 3)); // 3 + 5 + 7 = 15

        // Update an element and query again
        sumTree.update(2, 10);
        System.out.println("Sum query [1, 3] after update: " + sumTree.query(1, 3)); // 3 + 10 + 7 = 20

        // Create a segment tree for min queries
        _541_b_ArraySegmentTree minTree = new _541_b_ArraySegmentTree(arr, Math::min);
        System.out.println("Min query [0, 4]: " + minTree.query(0, 4)); // min(1, 3, 5, 7, 9) = 1

        // Update an element and query again
        minTree.update(0, 10);
        System.out.println("Min query [0, 2] after update: " + minTree.query(0, 2)); // min(10, 3, 5) = 3
    }

    /**
     * Recursively builds the segment tree
     *
     * @param nums      The input array
     * @param l         Left boundary of current segment
     * @param r         Right boundary of current segment
     * @param rootIndex Current root index in the tree array
     */
    private void build(int[] nums, int l, int r, int rootIndex) {
        // Base case: leaf node (single element)
        if (l == r) {
            tree[rootIndex] = nums[l];
            return;
        }

        // Recursively build left and right subtrees
        int mid = l + (r - l) / 2;
        int leftRootIndex = leftChild(rootIndex);
        int rightRootIndex = rightChild(rootIndex);

        build(nums, l, mid, leftRootIndex);
        build(nums, mid + 1, r, rightRootIndex);

        // Post-order: Update current node's value after children are built
        tree[rootIndex] = merger.apply(tree[leftRootIndex], tree[rightRootIndex]);
    }

    /**
     * Updates a single element in the segment tree
     *
     * @param index The index of the element to update
     * @param value The new value
     */
    public void update(int index, int value) {
        update(0, n - 1, 0, index, value);
    }

    /**
     * Recursively updates a node and its ancestors
     *
     * @param l         Left boundary of current segment
     * @param r         Right boundary of current segment
     * @param rootIndex Current root index in the tree array
     * @param index     The index to update
     * @param value     The new value
     */
    private void update(int l, int r, int rootIndex, int index, int value) {
        // Base case: reached the leaf node for the target index
        if (l == r) {
            tree[rootIndex] = value;
            return;
        }

        int mid = l + (r - l) / 2;
        if (index <= mid) {
            // Target is in the left subtree
            update(l, mid, leftChild(rootIndex), index, value);
        } else {
            // Target is in the right subtree
            update(mid + 1, r, rightChild(rootIndex), index, value);
        }

        // Post-order: Update current node's value after children are updated
        tree[rootIndex] = merger.apply(
                tree[leftChild(rootIndex)],
                tree[rightChild(rootIndex)]
        );
    }

    /**
     * Queries the aggregate value in a given range [qL, qR]
     *
     * @param qL Left boundary of query range
     * @param qR Right boundary of query range
     * @return Aggregate value in the range
     */
    public int query(int qL, int qR) {
        if (qL < 0 || qR >= n || qL > qR) {
            throw new IllegalArgumentException("Invalid range: [" + qL + ", " + qR + "]");
        }
        return query(0, n - 1, 0, qL, qR);
    }

    /**
     * Recursively queries the segment tree
     *
     * @param l         Left boundary of current segment
     * @param r         Right boundary of current segment
     * @param rootIndex Current root index in the tree array
     * @param qL        Left boundary of query range
     * @param qR        Right boundary of query range
     * @return Aggregate value in the range
     */
    private int query(int l, int r, int rootIndex, int qL, int qR) {
        // Case 1: Current node's range exactly matches the query range
        if (qL == l && r == qR) {
            return tree[rootIndex];
        }

        int mid = l + (r - l) / 2;
        int leftRootIndex = leftChild(rootIndex);
        int rightRootIndex = rightChild(rootIndex);

        // Case 2: Query range is entirely in the left subtree
        if (qR <= mid) {
            return query(l, mid, leftRootIndex, qL, qR);
        }
        // Case 3: Query range is entirely in the right subtree
        else if (qL > mid) {
            return query(mid + 1, r, rightRootIndex, qL, qR);
        }
        // Case 4: Query range spans both subtrees, split and merge results
        else {
            return merger.apply(
                    query(l, mid, leftRootIndex, qL, mid),
                    query(mid + 1, r, rightRootIndex, mid + 1, qR)
            );
        }
    }

    /**
     * Gets the index of the left child of a node
     */
    private int leftChild(int pos) {
        return 2 * pos + 1;
    }

    /**
     * Gets the index of the right child of a node
     */
    private int rightChild(int pos) {
        return 2 * pos + 2;
    }
}