package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._542_dynamic_segment_tree; /**
 * Dynamic Segment Tree Implementation
 * <p>
 * This class implements a dynamic segment tree that creates nodes on-demand
 * using a technique called "dynamic allocation" or "lazy creation". This approach
 * optimizes memory usage for sparse data or large ranges.
 * <p>
 * Key features:
 * 1. Memory efficiency for sparse data or large ranges
 * 2. No need to initialize with an array, just specify the range boundaries
 * 3. Nodes are created only when needed (during update operations)
 * 4. Maintains O(log n) time complexity for both queries and updates
 * 5. Significantly reduces memory usage when dealing with large ranges
 * <p>
 * Use cases:
 * - Range queries on very large intervals (e.g., 10^9 range)
 * - Sparse data where most elements have the same default value
 * - Problems where the range is large but only a few positions are updated
 * <p>
 * Advantages over standard segment tree:
 * - Memory usage scales with the number of updates, not with the size of the range
 * - Can handle ranges too large for traditional segment trees
 * - No need to pre-allocate memory for the entire range
 */

import java.util.function.*;

public class _542_a_DynamicSegmentTree {
    // Root node of the segment tree
    private final SegmentNode root;
    // Function used to combine values (e.g., sum, max, min)
    private final BinaryOperator<Integer> merger;
    // Default value for uninitialized segments
    private final int defaultValue;
    /**
     * Constructs a dynamic segment tree for a given range
     *
     * @param start        Start of the range
     * @param end          End of the range
     * @param merger       Function to merge/aggregate values
     * @param defaultValue Default value for uninitialized segments
     */
    public _542_a_DynamicSegmentTree(int start, int end, BinaryOperator<Integer> merger, int defaultValue) {
        this.merger = merger;
        this.defaultValue = defaultValue;
        // Only create the root node initially, with the default value
        this.root = new SegmentNode(start, end, defaultValue);
    }

    /**
     * Example usage of the dynamic segment tree
     */
    public static void main(String[] args) {
        // Create a dynamic segment tree for sum queries in range [0, 1000000]
        // with a default value of 0
        _542_a_DynamicSegmentTree tree = new _542_a_DynamicSegmentTree(0, 1000000, Integer::sum, 0);

        System.out.println("Initial sum of range [10, 20]: " + tree.query(10, 20));  // Should be 0

        // Update a few elements
        tree.update(15, 5);
        tree.update(18, 10);

        System.out.println("Sum of range [10, 20] after updates: " + tree.query(10, 20));  // Should be 15
        System.out.println("Sum of range [15, 18] after updates: " + tree.query(15, 18));  // Should be 15

        // Update an existing element
        tree.update(15, 8);

        System.out.println("Sum of range [10, 20] after modifying 15: " + tree.query(10, 20));  // Should be 18

        // Example with a large range - notice we can efficiently handle a huge range
        // without allocating memory for all positions
        _542_a_DynamicSegmentTree largeTree = new _542_a_DynamicSegmentTree(0, 1_000_000_000, Integer::sum, 0);

        largeTree.update(123456789, 42);
        largeTree.update(987654321, 13);

        System.out.println("Sum of specific elements in large range: " +
                largeTree.query(123456789, 987654321));  // Should be 55
    }

    /**
     * Initializes child nodes if they don't exist yet
     * This is the key method that enables "dynamic allocation"
     *
     * @param node Current node that might need children created
     */
    private void initChildrenIfNeeded(SegmentNode node) {
        if (node.l == node.r) {
            // Leaf node, no need to create children
            return;
        }

        int mid = node.l + (node.r - node.l) / 2;

        // Create left child if it doesn't exist
        if (node.left == null) {
            node.left = new SegmentNode(node.l, mid, defaultValue);
        }

        // Create right child if it doesn't exist
        if (node.right == null) {
            node.right = new SegmentNode(mid + 1, node.r, defaultValue);
        }
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
     * Creates nodes dynamically as needed
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

        // Dynamically create children if needed
        initChildrenIfNeeded(node);

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
        if (qL > qR) {
            throw new IllegalArgumentException("Invalid query range");
        }
        return query(root, qL, qR);
    }

    /**
     * Recursively queries the segment tree
     * Handles the case where nodes might not exist yet
     *
     * @param node Current node
     * @param qL   Left boundary of query range
     * @param qR   Right boundary of query range
     * @return Aggregate value in the range
     */
    private int query(SegmentNode node, int qL, int qR) {
        // Out of bounds check
        if (node.r < qL || node.l > qR) {
            throw new IllegalArgumentException("Invalid query range");
        }

        // Complete overlap: current node range is entirely within query range
        if (qL <= node.l && node.r <= qR) {
            return node.mergeVal;
        }

        // Check if we need to initialize children
        initChildrenIfNeeded(node);

        int mid = node.l + (node.r - node.l) / 2;

        // Case 1: Query range is entirely in the left subtree
        if (qR <= mid) {
            return query(node.left, qL, qR);
        }
        // Case 2: Query range is entirely in the right subtree
        else if (qL > mid) {
            return query(node.right, qL, qR);
        }
        // Case 3: Query range spans both subtrees, split and merge results
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

        public SegmentNode(int l, int r, int mergeVal) {
            this.l = l;
            this.r = r;
            this.mergeVal = mergeVal;
        }
    }
}