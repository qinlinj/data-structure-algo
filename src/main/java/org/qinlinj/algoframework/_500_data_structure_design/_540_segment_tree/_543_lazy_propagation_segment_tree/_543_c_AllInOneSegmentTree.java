package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._543_lazy_propagation_segment_tree;

/**
 * All-in-One Segment Tree Implementation
 * <p>
 * This class provides a comprehensive segment tree implementation that supports:
 * 1. Range assignment operations (setting all elements in a range to a specific value)
 * 2. Range addition operations (adding a value to all elements in a range)
 * 3. Different aggregation functions (sum, min, max)
 * 4. Dynamic node creation for memory efficiency
 * 5. Lazy propagation for efficient range operations
 * <p>
 * All operations (queries and updates) run in O(log n) time complexity, regardless
 * of the size of the range being updated or queried.
 * <p>
 * Handling Both Assignment and Addition:
 * - When both operations can be performed on the same tree, assignment takes precedence
 * - When an assignment is performed, any pending addition is discarded
 * - This implementation carefully handles the interaction between these operations
 * <p>
 * Aggregation Functions:
 * - The tree can be configured to compute sums, maximums, or minimums
 * - The aggregation type is specified at construction time
 * - The internal logic automatically adjusts based on the aggregation type
 * <p>
 * This is a production-ready implementation that can be used as a template for
 * competitive programming or real-world applications requiring efficient range
 * operations on arrays.
 */

public class _543_c_AllInOneSegmentTree {
    // Tree root and configuration
    private final SegmentNode root;
    private final int defaultValue;
    private final String type; // "sum", "max", or "min"
    /**
     * Constructs a new segment tree with specified range and aggregation type
     *
     * @param start        Start of the range (inclusive)
     * @param end          End of the range (inclusive)
     * @param defaultValue Default value for all elements
     * @param type         Aggregation type: "sum", "max", or "min"
     */
    public _543_c_AllInOneSegmentTree(int start, int end, int defaultValue, String type) {
        // Validate aggregation type
        if (type.equals("sum") || type.equals("max") || type.equals("min")) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Invalid type, must be sum, max, or min");
        }

        this.defaultValue = defaultValue;
        int rootMergeValue = computeRangeValue(start, end, defaultValue);
        this.root = new SegmentNode(start, end, rootMergeValue);
    }

    /**
     * Example usage demonstrating all features of the segment tree
     */
    public static void main(String[] args) {
        // Create a sum segment tree
        _543_c_AllInOneSegmentTree sumTree = new _543_c_AllInOneSegmentTree(0, 9, 0, "sum");

        System.out.println("==== Sum Segment Tree ====");
        System.out.println("Initial array: [0,0,0,0,0,0,0,0,0,0]");

        // Test range update (assignment)
        sumTree.rangeUpdate(2, 5, 7);
        System.out.println("After assigning 7 to range [2, 5]: [0,0,7,7,7,7,0,0,0,0]");
        System.out.println("Sum of range [0, 9]: " + sumTree.query(0, 9));  // 28

        // Test range add
        sumTree.rangeAdd(0, 3, 5);
        System.out.println("After adding 5 to range [0, 3]: [5,5,12,12,7,7,0,0,0,0]");
        System.out.println("Sum of range [0, 5]: " + sumTree.query(0, 5));  // 48

        // Test interaction between assign and add
        sumTree.rangeUpdate(5, 9, 2);
        System.out.println("After assigning 2 to range [5, 9]: [5,5,12,12,7,2,2,2,2,2]");
        System.out.println("Sum of range [0, 9]: " + sumTree.query(0, 9));  // 51

        sumTree.rangeAdd(7, 9, 3);
        System.out.println("After adding 3 to range [7, 9]: [5,5,12,12,7,2,2,5,5,5]");
        System.out.println("Sum of range [6, 9]: " + sumTree.query(6, 9));  // 17

        // Create a max segment tree
        _543_c_AllInOneSegmentTree maxTree = new _543_c_AllInOneSegmentTree(0, 9, 0, "max");

        System.out.println("\n==== Max Segment Tree ====");
        System.out.println("Initial array: [0,0,0,0,0,0,0,0,0,0]");

        // Test range update and add
        maxTree.rangeUpdate(2, 5, 7);
        System.out.println("After assigning 7 to range [2, 5]: [0,0,7,7,7,7,0,0,0,0]");
        System.out.println("Max of range [0, 9]: " + maxTree.query(0, 9));  // 7

        maxTree.rangeAdd(0, 3, 5);
        System.out.println("After adding 5 to range [0, 3]: [5,5,12,12,7,7,0,0,0,0]");
        System.out.println("Max of range [0, 5]: " + maxTree.query(0, 5));  // 12

        // Create a min segment tree
        _543_c_AllInOneSegmentTree minTree = new _543_c_AllInOneSegmentTree(0, 9, 10, "min");

        System.out.println("\n==== Min Segment Tree ====");
        System.out.println("Initial array: [10,10,10,10,10,10,10,10,10,10]");

        // Test range update and add
        minTree.rangeUpdate(2, 5, 7);
        System.out.println("After assigning 7 to range [2, 5]: [10,10,7,7,7,7,10,10,10,10]");
        System.out.println("Min of range [0, 9]: " + minTree.query(0, 9));  // 7

        minTree.rangeAdd(6, 9, -5);
        System.out.println("After adding -5 to range [6, 9]: [10,10,7,7,7,7,5,5,5,5]");
        System.out.println("Min of range [0, 9]: " + minTree.query(0, 9));  // 5
    }

    /**
     * Computes the aggregate value for a range with all elements set to a given value
     *
     * @param l   Start of the range
     * @param r   End of the range
     * @param val Value for all elements in the range
     * @return Aggregate value according to the tree's type
     */
    private int computeRangeValue(int l, int r, int val) {
        if (type.equals("sum")) {
            // For sum, multiply value by range size
            return (r - l + 1) * val;
        } else {
            // For min or max, the value itself is the result
            return val;
        }
    }

    /**
     * Updates a merge value by adding a delta according to the tree's type
     *
     * @param node  Node containing the value to update
     * @param delta Value to add
     * @return Updated merge value
     */
    private int applyAddToValue(SegmentNode node, int delta) {
        if (type.equals("sum")) {
            // For sum, add (delta * range size) to the current sum
            return node.mergeValue + (node.r - node.l + 1) * delta;
        } else {
            // For min/max, simply add delta to the current value
            return node.mergeValue + delta;
        }
    }

    /**
     * Merges two values according to the tree's aggregation type
     *
     * @param leftVal  Left value
     * @param rightVal Right value
     * @return Merged result
     */
    private int merge(int leftVal, int rightVal) {
        if (type.equals("sum")) {
            return leftVal + rightVal;
        } else if (type.equals("max")) {
            return Math.max(leftVal, rightVal);
        } else if (type.equals("min")) {
            return Math.min(leftVal, rightVal);
        }
        throw new IllegalArgumentException("Invalid aggregation type");
    }

    /**
     * Creates child nodes if they don't exist yet
     *
     * @param node Current node
     */
    private void initChildrenIfNeeded(SegmentNode node) {
        if (node.l == node.r) {
            // Leaf node, no children needed
            return;
        }

        int mid = node.l + (node.r - node.l) / 2;

        if (node.left == null) {
            // Create left child
            int leftMergeValue = computeRangeValue(node.l, mid, defaultValue);
            node.left = new SegmentNode(node.l, mid, leftMergeValue);
        }

        if (node.right == null) {
            // Create right child
            int rightMergeValue = computeRangeValue(mid + 1, node.r, defaultValue);
            node.right = new SegmentNode(mid + 1, node.r, rightMergeValue);
        }
    }

    /**
     * Pushes lazy updates down to child nodes
     * Handles the interaction between assignment and addition operations
     *
     * @param node Current node with lazy updates to push down
     */
    private void pushDown(SegmentNode node) {
        // Initialize children if needed before pushing down updates
        initChildrenIfNeeded(node);

        // If there's an assignment operation, it takes precedence
        if (node.hasLazyAssign) {
            // Apply assignment to left child
            applyAssign(node.left, node.lazyAssign);

            // Apply assignment to right child
            applyAssign(node.right, node.lazyAssign);

            // Clear assignment flag and value
            node.hasLazyAssign = false;
            node.lazyAssign = 0;
        }

        // Then handle any addition operation (if it wasn't cleared by assignment)
        if (node.lazyAdd != 0) {
            // Apply addition to left child
            applyAdd(node.left, node.lazyAdd);

            // Apply addition to right child
            applyAdd(node.right, node.lazyAdd);

            // Clear addition value
            node.lazyAdd = 0;
        }
    }

    /**
     * Applies an assignment update to a node
     *
     * @param child Node to apply the assignment to
     * @param val   Value to assign
     */
    private void applyAssign(SegmentNode child, int val) {
        // Set assignment lazy tag
        child.hasLazyAssign = true;
        child.lazyAssign = val;

        // Clear any existing addition (assignment overrides addition)
        child.lazyAdd = 0;

        // Update merge value based on aggregation type
        child.mergeValue = computeRangeValue(child.l, child.r, val);
    }

    /**
     * Applies an addition update to a node
     *
     * @param child Node to apply the addition to
     * @param delta Value to add
     */
    private void applyAdd(SegmentNode child, int delta) {
        if (child.hasLazyAssign) {
            // If there's already an assignment, just update that value
            child.lazyAssign += delta;
            child.mergeValue = computeRangeValue(child.l, child.r, child.lazyAssign);
        } else {
            // Otherwise, update the addition value
            child.lazyAdd += delta;
            child.mergeValue = applyAddToValue(child, delta);
        }
    }

    /**
     * Updates a single element by assigning it a new value
     *
     * @param index The index of the element to update
     * @param val   The new value to assign
     */
    public void update(int index, int val) {
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
     * Recursive helper for range assignment
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
            // Apply assignment directly to this node
            node.hasLazyAssign = true;
            node.lazyAssign = val;
            node.lazyAdd = 0; // Clear any pending additions (assignment takes precedence)
            node.mergeValue = computeRangeValue(node.l, node.r, val);
            return;
        }

        // Case 2: Partial overlap, need to propagate updates to children
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

        // Update current node's merge value based on children
        node.mergeValue = merge(node.left.mergeValue, node.right.mergeValue);
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
     * Recursive helper for range addition
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
            if (node.hasLazyAssign) {
                // If there's already an assignment, just update that value
                node.lazyAssign += delta;
                node.mergeValue = computeRangeValue(node.l, node.r, node.lazyAssign);
            } else {
                // Otherwise, update the addition value
                node.lazyAdd += delta;
                node.mergeValue = applyAddToValue(node, delta);
            }
            return;
        }

        // Case 2: Partial overlap, need to propagate updates to children
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

        // Update current node's merge value based on children
        node.mergeValue = merge(node.left.mergeValue, node.right.mergeValue);
    }

    /**
     * Queries the aggregate value in a range
     *
     * @param qL Start of the range (inclusive)
     * @param qR End of the range (inclusive)
     * @return Aggregate value according to the tree's type
     */
    public int query(int qL, int qR) {
        return _query(root, qL, qR);
    }

    /**
     * Recursive helper for range queries
     *
     * @param node Current node
     * @param qL   Start of the query range
     * @param qR   End of the query range
     * @return Aggregate value for the range
     */
    private int _query(SegmentNode node, int qL, int qR) {
        // Range validation
        if (node.r < qL || node.l > qR) {
            throw new IllegalArgumentException("Invalid query range");
        }

        // Case 1: Current node range is completely contained in the query range
        if (qL <= node.l && node.r <= qR) {
            return node.mergeValue;
        }

        // Case 2: Partial overlap, need to propagate updates to children first
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
            int leftResult = _query(node.left, qL, mid);
            int rightResult = _query(node.right, mid + 1, qR);
            return merge(leftResult, rightResult);
        }
    }

    /**
     * Node class for the segment tree
     */
    static class SegmentNode {
        int l, r;               // Range boundaries [l, r]
        int mergeValue;         // Aggregated value for this range
        SegmentNode left, right; // Child nodes

        // Lazy update fields
        int lazyAdd;            // Pending addition value (0 = no pending addition)
        int lazyAssign;         // Value to assign (only used when hasLazyAssign is true)
        boolean hasLazyAssign;  // Flag indicating if there's a pending assignment

        public SegmentNode(int l, int r, int mergeValue) {
            this.l = l;
            this.r = r;
            this.mergeValue = mergeValue;
            this.lazyAdd = 0;
            this.lazyAssign = 0;
            this.hasLazyAssign = false;
        }
    }
}