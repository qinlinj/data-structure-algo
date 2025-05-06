package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._542_dynamic_segment_tree;

/**
 * LeetCode 307: Range Sum Query - Mutable Implementation
 * <p>
 * This class demonstrates the practical application of a Dynamic Segment Tree
 * by implementing LeetCode problem 307 - a range sum query system with mutable elements.
 * <p>
 * Key features:
 * 1. Uses the Dynamic Segment Tree for efficient range sum queries
 * 2. Supports O(log n) updates to single elements
 * 3. Supports O(log n) range sum queries
 * 4. Only creates nodes as needed, optimizing memory usage
 * <p>
 * Problem statement:
 * - Design a data structure that can efficiently update elements and calculate
 * the sum of elements in a given range
 * - The update(index, val) function modifies the array by updating the element at index to val
 * - The sumRange(left, right) function returns the sum of elements in range [left, right]
 * <p>
 * Example:
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2);   // return 9 (sum of 1+3+5)
 * numArray.update(1, 2);     // change array to [1, 2, 5]
 * numArray.sumRange(0, 2);   // return 8 (sum of 1+2+5)
 * <p>
 * This implementation showcases how a Dynamic Segment Tree provides an efficient
 * solution for problems requiring both element updates and range queries.
 */

public class _542_b_NumArray {
    // The underlying dynamic segment tree
    private final _542_a_DynamicSegmentTree segmentTree;

    /**
     * Initializes the object with the integer array nums
     *
     * @param nums The input array
     */
    public _542_b_NumArray(int[] nums) {
        // Initialize the dynamic segment tree with range [0, nums.length-1]
        // Using sum function as merger and 0 as the default value
        this.segmentTree = new _542_a_DynamicSegmentTree(0, nums.length - 1, (a, b) -> a + b, 0);

        // Populate the segment tree with values from nums
        // This is needed because our dynamic segment tree only initializes with default values
        for (int i = 0; i < nums.length; i++) {
            segmentTree.update(i, nums[i]);
        }
    }

    /**
     * Example usage of NumArray implementation
     */
    public static void main(String[] args) {
        // Example from LeetCode problem description
        int[] nums = {1, 3, 5};
        _542_b_NumArray numArray = new _542_b_NumArray(nums);

        System.out.println("Initial array: [1, 3, 5]");
        System.out.println("Sum of range [0, 2]: " + numArray.sumRange(0, 2)); // Should be 9

        numArray.update(1, 2); // Update array to [1, 2, 5]
        System.out.println("After updating index 1 to 2, array is now: [1, 2, 5]");
        System.out.println("Sum of range [0, 2]: " + numArray.sumRange(0, 2)); // Should be 8

        // Additional examples
        int[] largerArray = {2, 4, 6, 8, 10};
        _542_b_NumArray numArray2 = new _542_b_NumArray(largerArray);

        System.out.println("\nInitial larger array: [2, 4, 6, 8, 10]");
        System.out.println("Sum of range [1, 3]: " + numArray2.sumRange(1, 3)); // Should be 18 (4+6+8)

        numArray2.update(2, 7); // Update array to [2, 4, 7, 8, 10]
        numArray2.update(4, 5); // Update array to [2, 4, 7, 8, 5]

        System.out.println("After updates, array is now: [2, 4, 7, 8, 5]");
        System.out.println("Sum of range [0, 4]: " + numArray2.sumRange(0, 4)); // Should be 26
        System.out.println("Sum of range [2, 4]: " + numArray2.sumRange(2, 4)); // Should be 20
    }

    /**
     * Updates the value at given index
     *
     * @param index The index to update
     * @param val   The new value
     */
    public void update(int index, int val) {
        segmentTree.update(index, val);
    }

    /**
     * Returns the sum of values in range [left, right]
     *
     * @param left  Start of range (inclusive)
     * @param right End of range (inclusive)
     * @return Sum of elements in the range
     */
    public int sumRange(int left, int right) {
        return segmentTree.query(left, right);
    }
}