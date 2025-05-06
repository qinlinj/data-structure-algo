package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._541_basic_segment_tree; /**
 * Segment Tree Implementation (Node-based)
 * <p>
 * This class implements a segment tree data structure using a node-based approach.
 * Segment trees are specialized data structures that allow for efficient range queries
 * and updates on an array of values.
 * <p>
 * Key features:
 * 1. O(log n) time complexity for both range queries and single element updates
 * 2. Supports flexible aggregate operations (sum, max, min, etc.) through a merger function
 * 3. Maintains the ability to dynamically update individual elements
 * 4. Efficiently answers range queries without scanning the entire range
 * <p>
 * Use cases:
 * - Range sum/min/max queries with element updates
 * - Finding range GCD or other aggregate functions
 * - Problems involving frequent range queries on a mutable array
 * <p>
 * Trade-offs:
 * - Requires O(n) extra space for the tree structure
 * - More complex implementation compared to prefix sum arrays
 * - Node-based implementation uses slightly more memory than array-based due to pointers
 */

import java.util.function.*;

public class _541_a_NodeSegmentTree {
    // Root node of the segment tree
    private final SegmentNode root;
    // Function used to combine values (e.g., sum, max, min)
    private final BinaryOperator<Integer> merger;
    /**
     * Constructs a segment tree from an array
     *
     * @param nums   The input array of integers
     * @param merger The function to merge/aggregate values (sum, min, max, etc.)
     */
    public _541_a_NodeSegmentTree(int[] nums, BinaryOperator<Integer> merger) {
        this.merger = merger;
        this.root = build(nums, 0, nums.length - 1);
    }

    /**
     * Example usage of the segment tree
     */
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};

        // Create a segment tree for sum queries
        _541_a_NodeSegmentTree sumTree = new _541_a_NodeSegmentTree(arr, (a, b) -> a + b);
        System.out.println("Sum query [1, 3]: " + sumTree.query(1, 3)); // 3 + 5 + 7 = 15

        // Update an element and query again
        sumTree.update(2, 10);
        System.out.println("Sum query [1, 3] after update: " + sumTree.query(1, 3)); // 3 + 10 + 7 = 20

        // Create a segment tree for max queries
        _541_a_NodeSegmentTree maxTree = new _541_a_NodeSegmentTree(arr, Math::max);
        System.out.println("Max query [1, 4]: " + maxTree.query(1, 4)); // max(3, 5, 7, 9) = 9

        // Update an element and query again
        maxTree.update(1, 12);
        System.out.println("Max query [0, 2] after update: " + maxTree.query(0, 2)); // max(1, 12, 5) = 12
    }

    /**
     * Recursively builds the segment tree
     *
     * @param nums The input array
     * @param l    Left boundary of current segment
     * @param r    Right boundary of current segment
     * @return Root node of the constructed segment tree
     */
    private SegmentNode build(int[] nums, int l, int r) {
        // Base case: leaf node (single element)
        if (l == r) {
            return new SegmentNode(nums[l], l, r);
        }

        // Recursively build left and right subtrees
        int mid = l + (r - l) / 2;
        SegmentNode left = build(nums, l, mid);
        SegmentNode right = build(nums, mid + 1, r);

        // Create parent node with merged value from children
        SegmentNode node = new SegmentNode(merger.apply(left.mergeVal, right.mergeVal), l, r);
        node.left = left;
        node.right = right;

        return node;
    }

    /**
     * Updates a single element in the segment tree
     *
     * @param index The index of the element to update
     * @param value The new value
     */
    public void update(int index, int value) {
        update(root, index, value);
    }

    /**
     * Recursively updates a node and its ancestors
     *
     * @param node  Current node
     * @param index The index to update
     * @param value The new value
     */
    private void update(SegmentNode node, int index, int value) {
        // Base case: reached the leaf node for the target index
        if (node.l == node.r) {
            node.mergeVal = value;
            return;
        }

        int mid = node.l + (node.r - node.l) / 2;
        if (index <= mid) {
            // Target is in the left subtree
            update(node.left, index, value);
        } else {
            // Target is in the right subtree
            update(node.right, index, value);
        }

        // Post-order: Update current node's value after children are updated
        node.mergeVal = merger.apply(node.left.mergeVal, node.right.mergeVal);
    }

    /**
     * Queries the aggregate value in a given range [qL, qR]
     *
     * @param qL Left boundary of query range
     * @param qR Right boundary of query range
     * @return Aggregate value in the range
     */
    public int query(int qL, int qR) {
        return query(root, qL, qR);
    }

    /**
     * Recursively queries the segment tree
     *
     * @param node Current node
     * @param qL   Left boundary of query range
     * @param qR   Right boundary of query range
     * @return Aggregate value in the range
     */
    private int query(SegmentNode node, int qL, int qR) {
        if (qL > qR) {
            throw new IllegalArgumentException("Invalid query range");
        }

        // Case 1: Current node's range exactly matches the query range
        if (node.l == qL && node.r == qR) {
            return node.mergeVal;
        }

        int mid = node.l + (node.r - node.l) / 2;

        // Case 2: Query range is entirely in the left subtree
        if (qR <= mid) {
            return query(node.left, qL, qR);
        }
        // Case 3: Query range is entirely in the right subtree
        else if (qL > mid) {
            return query(node.right, qL, qR);
        }
        // Case 4: Query range spans both subtrees, split and merge results
        else {
            return merger.apply(
                    query(node.left, qL, mid),
                    query(node.right, mid + 1, qR)
            );
        }
    }

    class SegmentNode {
        // The range this node represents [l, r]
        int l, r;
        // The aggregate value for the range [l, r]
        int mergeVal;
        // Child nodes
        SegmentNode left, right;

        public SegmentNode(int mergeVal, int l, int r) {
            this.mergeVal = mergeVal;
            this.l = l;
            this.r = r;
        }
    }
}