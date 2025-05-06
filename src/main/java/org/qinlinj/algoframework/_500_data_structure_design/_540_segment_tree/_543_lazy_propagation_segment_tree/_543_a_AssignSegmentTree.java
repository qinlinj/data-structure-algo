package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._543_lazy_propagation_segment_tree;

/**
 * Segment Tree with Lazy Propagation for Range Assignment
 * <p>
 * This class implements a dynamic segment tree with lazy propagation for efficient
 * range assignment operations. It supports assigning values to entire ranges in O(log n)
 * time, addressing one of the limitations of basic segment trees.
 * <p>
 * Key features:
 * 1. O(log n) time complexity for both range assignment and range queries
 * 2. Lazy propagation technique defers updates until nodes are actually accessed
 * 3. Dynamic node creation for memory efficiency with sparse data
 * 4. Specialized for sum aggregation in range queries
 * <p>
 * Lazy Propagation Concept:
 * - When a range is assigned a value, the change is recorded at the highest possible nodes
 * using lazy tags (hasLazyAssign and lazyAssign fields)
 * - Updates are not immediately propagated to child nodes
 * - When a node with lazy tags is accessed later, the lazy updates are pushed down
 * to its children just-in-time (using pushDown method)
 * - This approach avoids unnecessary updates to nodes that may never be accessed
 * <p>
 * Applications:
 * - Problems requiring frequent range updates and queries
 * - Scenarios where entire ranges need to be set to the same value
 * - When both single-point updates and range updates are needed
 */

public class _543_a_AssignSegmentTree {
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
    public _543_a_AssignSegmentTree(int start, int end, int defaultValue) {
        this.defaultValue = defaultValue;
        int initSum = (end - start + 1) * defaultValue;
        this.root = new SegmentNode(start, end, defaultValue);
    }

    /**
     * Example usage of the assign segment tree
     */
    public static void main(String[] args) {
        // Create a segment tree for range [0, 9] with initial values of 0
        _543_a_AssignSegmentTree tree = new _543_a_AssignSegmentTree(0, 9, 0);
        System.out.println("Initial array: [0,0,0,0,0,0,0,0,0,0]");

        // Assign 7 to the range [2, 5]
        tree.rangeUpdate(2, 5, 7);
        System.out.println("After assigning 7 to range [2, 5]: [0,0,7,7,7,7,0,0,0,0]");

        // Query the sum of the entire range
        int sum = tree.query(0, 9);
        System.out.println("Sum of range [0, 9]: " + sum + " (expected: 28)");  // 7 * 4 = 28

        // Assign 5 to the range [0, 3]
        tree.rangeUpdate(0, 3, 5);
        System.out.println("After assigning 5 to range [0, 3]: [5,5,5,5,7,7,0,0,0,0]");

        // Query the sum of range [0, 5]
        sum = tree.query(0, 5);
        System.out.println("Sum of range [0, 5]: " + sum + " (expected: 34)");  // 5 * 4 + 7 * 2 = 34

        // Assign 2 to the range [5, 9]
        tree.rangeUpdate(5, 9, 2);
        System.out.println("After assigning 2 to range [5, 9]: [5,5,5,5,7,2,2,2,2,2]");

        // Query the sum of the entire range
        sum = tree.query(0, 9);
        System.out.println("Sum of range [0, 9]: " + sum + " (expected: 37)");  // 5 * 4 + 7 * 1 + 2 * 5 = 37

        // Query a single element
        int value = tree.query(5, 5);
        System.out.println("Value at index 5: " + value + " (expected: 2)");

        // Query a smaller range
        sum = tree.query(1, 2);
        System.out.println("Sum of range [1, 2]: " + sum + " (expected: 10)");  // 5 + 5 = 10
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
     * Pushes lazy assignment tags down to child nodes
     * This is a key part of the lazy propagation technique
     *
     * @param node Current node with lazy tags to push down
     */
    private void pushDown(SegmentNode node) {
        if (!node.hasLazyAssign) {
            // No lazy assignment, nothing to push down
            return;
        }

        initChildrenIfNeeded(node);

        // Apply lazy assignment to left child
        node.left.lazyAssign = node.lazyAssign;
        node.left.hasLazyAssign = true;
        node.left.sum = (node.left.r - node.left.l + 1) * node.lazyAssign;

        // Apply lazy assignment to right child
        node.right.lazyAssign = node.lazyAssign;
        node.right.hasLazyAssign = true;
        node.right.sum = (node.right.r - node.right.l + 1) * node.lazyAssign;

        // Clear the lazy assignment from current node
        node.hasLazyAssign = false;
    }

    /**
     * Updates a single element by assigning it a new value
     *
     * @param index The index of the element to update
     * @param val   The new value to assign
     */
    public void assign(int index, int val) {
        // Single point assignment is just a special case of range assignment
        rangeUpdate(index, index, val);
    }

    /**
     * Assigns a value to all elements in a range
     *
     * @param qL  Start of the range (inclusive)
     * @param qR  End of the range (inclusive)
     * @param val The value to assign to each element in the range
     */
    public void rangeUpdate(int qL, int qR, int val) {
        _rangeUpdate(root, qL, qR, val);
    }

    /**
     * Recursive helper for range assignment with lazy propagation
     *
     * @param node Current node
     * @param qL   Start of the range to update
     * @param qR   End of the range to update
     * @param val  Value to assign
     */
    private void _rangeUpdate(SegmentNode node, int qL, int qR, int val) {
        // Range validation
        if (node.r < qL || node.l > qR) {
            throw new IllegalArgumentException("Invalid update range");
        }

        // Case 1: Current node range is completely contained in the update range
        if (qL <= node.l && node.r <= qR) {
            // Set the sum for the entire range and mark with lazy tag
            node.sum = (node.r - node.l + 1) * val;
            node.lazyAssign = val;
            node.hasLazyAssign = true;
            return;
        }

        // Case 2: Partial overlap, need to split and recurse
        initChildrenIfNeeded(node);
        // Push any existing lazy assignments down before updating children
        pushDown(node);

        int mid = node.l + (node.r - node.l) / 2;

        // Update left child if range overlaps
        if (qL <= mid) {
            _rangeUpdate(node.left, qL, Math.min(mid, qR), val);
        }

        // Update right child if range overlaps
        if (qR > mid) {
            _rangeUpdate(node.right, Math.max(mid + 1, qL), qR, val);
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
        // Push any existing lazy assignments down before querying children
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

        // Flag indicating if this node has a pending assignment operation
        boolean hasLazyAssign;
        // The value to be assigned to all elements in the range
        int lazyAssign;

        public SegmentNode(int l, int r, int value) {
            this.l = l;
            this.r = r;
            this.sum = value * (r - l + 1); // Initial sum is value * range length
            this.hasLazyAssign = false;
        }
    }
}