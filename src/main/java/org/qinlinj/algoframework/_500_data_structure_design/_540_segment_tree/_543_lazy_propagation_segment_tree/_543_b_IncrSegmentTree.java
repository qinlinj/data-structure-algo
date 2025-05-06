package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._543_lazy_propagation_segment_tree;

/**
 * Segment Tree with Lazy Propagation for Range Addition
 * <p>
 * This class implements a dynamic segment tree with lazy propagation for efficient
 * range addition operations. It supports adding values to entire ranges in O(log n)
 * time complexity.
 * <p>
 * Key features:
 * 1. O(log n) time complexity for both range additions and range queries
 * 2. Lazy propagation technique defers updates until nodes are actually accessed
 * 3. Dynamic node creation for memory efficiency with sparse data
 * 4. Specialized for sum aggregation in range queries
 * <p>
 * Lazy Propagation for Addition:
 * - When a range addition is performed, the change is recorded at the highest possible nodes
 * using the lazyAdd field (initially 0 for no pending updates)
 * - Actual propagation to child nodes is deferred until those nodes need to be accessed
 * - The pushDown method propagates lazy updates when a node is accessed
 * <p>
 * Differences from Range Assignment:
 * - Addition operations are cumulative, while assignment operations override previous values
 * - The lazy update logic differs to account for this cumulative nature
 * - The implementation is simpler as we only need one lazy field (no boolean flag)
 * <p>
 * Applications:
 * - Range increment/decrement operations (e.g., add a value to all elements in a range)
 * - Problems requiring both range updates and range queries
 * - Scenarios where multiple updates to the same range need to accumulate
 */

public class _543_b_IncrSegmentTree {
    // Root node of the segment tree
    private final SegmentNode root;
    // Default value for uninitialized segments
    private final int defaultValue;
    /**
     * Constructs a segment tree for a given range with a default value
     *
     * @param start        Start of the range (inclusive)
     * @param end          End of the range (inclusive)
     * @param defaultValue Default value for all elements
     */
    public _543_b_IncrSegmentTree(int start, int end, int defaultValue) {
        this.defaultValue = defaultValue;
        int initSum = (end - start + 1) * defaultValue;
        this.root = new SegmentNode(start, end, defaultValue);
    }

    /**
     * Example usage of the increment segment tree
     */
    public static void main(String[] args) {
        // Create a segment tree for range [0, 9] with initial values of 0
        _543_b_IncrSegmentTree tree = new _543_b_IncrSegmentTree(0, 9, 0);
        System.out.println("Initial array: [0,0,0,0,0,0,0,0,0,0]");

        // Add 7 to the range [2, 5]
        tree.rangeAdd(2, 5, 7);
        System.out.println("After adding 7 to range [2, 5]: [0,0,7,7,7,7,0,0,0,0]");

        // Query the sum of the entire range
        int sum = tree.query(0, 9);
        System.out.println("Sum of range [0, 9]: " + sum + " (expected: 28)");  // 7 * 4 = 28

        // Add 5 to the range [0, 3]
        tree.rangeAdd(0, 3, 5);
        System.out.println("After adding 5 to range [0, 3]: [5,5,12,12,7,7,0,0,0,0]");

        // Query the sum of range [0, 5]
        sum = tree.query(0, 5);
        System.out.println("Sum of range [0, 5]: " + sum + " (expected: 48)");  // 5*2 + 12*2 + 7*2 = 48

        // Add 2 to the range [5, 9]
        tree.rangeAdd(5, 9, 2);
        System.out.println("After adding 2 to range [5, 9]: [5,5,12,12,7,9,2,2,2,2]");

        // Query the sum of the entire range
        sum = tree.query(0, 9);
        System.out.println("Sum of range [0, 9]: " + sum + " (expected: 58)");  // 5*2 + 12*2 + 7 + 9 + 2*4 = 58

        // Query a single element
        int value = tree.query(5, 5);
        System.out.println("Value at index 5: " + value + " (expected: 9)");

        // Query a smaller range
        sum = tree.query(1, 2);
        System.out.println("Sum of range [1, 2]: " + sum + " (expected: 17)");  // 5 + 12 = 17
    }

    /**
     * Initializes child nodes if they don't exist yet
     * This enables dynamic node creation for memory efficiency
     *
     * @param node Current node that might need children created
     */
    private void initChildrenIfNeeded(SegmentNode node) {
        if (node.l == node.r) {
            // Leaf node, no need to create children
            return;
        }

        if (node.left == null || node.right == null) {
            int mid = node.l + (node.r - node.l) / 2;

            if (node.left == null) {
                // Create left child with default value
                node.left = new SegmentNode(node.l, mid, defaultValue);
            }

            if (node.right == null) {
                // Create right child with default value
                node.right = new SegmentNode(mid + 1, node.r, defaultValue);
            }
        }
    }

    /**
     * Pushes lazy addition values down to child nodes
     * This is a key part of the lazy propagation technique
     *
     * @param node Current node with lazy addition to push down
     */
    private void pushDown(SegmentNode node) {
        if (node.lazyAdd == 0) {
            // No lazy addition, nothing to push down
            return;
        }

        initChildrenIfNeeded(node);

        // Apply lazy addition to left child
        node.left.lazyAdd += node.lazyAdd;
        node.left.sum += (node.left.r - node.left.l + 1) * node.lazyAdd;

        // Apply lazy addition to right child
        node.right.lazyAdd += node.lazyAdd;
        node.right.sum += (node.right.r - node.right.l + 1) * node.lazyAdd;

        // Clear the lazy addition from current node
        node.lazyAdd = 0;
    }

    /**
     * Adds a value to a single element
     *
     * @param index The index of the element to update
     * @param delta The value to add (can be negative for subtraction)
     */
    public void add(int index, int delta) {
        // Single point addition is just a special case of range addition
        rangeAdd(index, index, delta);
    }

    /**
     * Adds a value to all elements in a range
     *
     * @param qL    Start of the range (inclusive)
     * @param qR    End of the range (inclusive)
     * @param delta The value to add to each element (can be negative)
     */
    public void rangeAdd(int qL, int qR, int delta) {
        _rangeAdd(root, qL, qR, delta);
    }

    /**
     * Recursive helper for range addition with lazy propagation
     *
     * @param node  Current node
     * @param qL    Start of the range to update
     * @param qR    End of the range to update
     * @param delta Value to add
     */
    private void _rangeAdd(SegmentNode node, int qL, int qR, int delta) {
        // Range validation
        if (node.r < qL || node.l > qR) {
            throw new IllegalArgumentException("Invalid update range");
        }

        // Case 1: Current node range is completely contained in the update range
        if (qL <= node.l && node.r <= qR) {
            // Update the sum for the entire range and add to lazy value
            node.sum += (node.r - node.l + 1) * delta;
            node.lazyAdd += delta;
            return;
        }

        // Case 2: Partial overlap, need to split and recurse
        initChildrenIfNeeded(node);
        // Push any existing lazy additions down before updating children
        pushDown(node);

        int mid = node.l + (node.r - node.l) / 2;

        // Update left child if range overlaps
        if (qL <= mid) {
            _rangeAdd(node.left, qL, Math.min(mid, qR), delta);
        }

        // Update right child if range overlaps
        if (qR > mid) {
            _rangeAdd(node.right, Math.max(mid + 1, qL), qR, delta);
        }

        // Update current node's sum based on children
        node.sum = node.left.sum + node.right.sum;
    }

    /**
     * Queries the sum of elements in a range
     *
     * @param qL Start of the range (inclusive)
     * @param qR End of the range (inclusive)
     * @return Sum of all elements in the range
     */
    public int query(int qL, int qR) {
        return _query(root, qL, qR);
    }

    /**
     * Recursive helper for range sum queries
     *
     * @param node Current node
     * @param qL   Start of the query range
     * @param qR   End of the query range
     * @return Sum of elements in the range
     */
    private int _query(SegmentNode node, int qL, int qR) {
        // Range validation
        if (node.r < qL || node.l > qR) {
            throw new IllegalArgumentException("Invalid query range");
        }

        // Case 1: Current node range is completely contained in the query range
        if (qL <= node.l && node.r <= qR) {
            return node.sum;
        }

        // Case 2: Partial overlap, need to split and recurse
        initChildrenIfNeeded(node);
        // Push any existing lazy additions down before querying children
        pushDown(node);

        int mid = node.l + (node.r - node.l) / 2;

        // Case 2.1: Query range is entirely in left child
        if (qR <= mid) {
            return _query(node.left, qL, qR);
        }
        // Case 2.2: Query range is entirely in right child
        else if (qL > mid) {
            return _query(node.right, qL, qR);
        }
        // Case 2.3: Query range spans both children
        else {
            return _query(node.left, qL, mid) + _query(node.right, mid + 1, qR);
        }
    }

    class SegmentNode {
        // The range this node represents [l, r]
        int l, r;
        // The sum of all elements in the range [l, r]
        int sum;
        // Child nodes
        SegmentNode left, right;

        // Pending addition to propagate to children (0 means no pending update)
        int lazyAdd;

        public SegmentNode(int l, int r, int value) {
            this.l = l;
            this.r = r;
            this.sum = value * (r - l + 1); // Initial sum is value * range length
            this.lazyAdd = 0;
        }
    }
}