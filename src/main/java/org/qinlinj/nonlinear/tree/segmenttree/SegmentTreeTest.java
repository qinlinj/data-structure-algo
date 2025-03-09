package org.qinlinj.nonlinear.tree.segmenttree;

/**
 * Test class for SegmentTree that demonstrates lazy propagation tags
 * This class focuses on showing lazy tags in the tree visualization
 */
public class SegmentTreeTest {
    public static void main(String[] args) {
        // Create a test array
        int[] arr = {1, 3, 5, 7, 9, 11};
        System.out.println("Original Array: [1, 3, 5, 7, 9, 11]");

        // Build segment tree
        SegmentTree segTree = new SegmentTree(arr);
        System.out.println("\nAfter building the segment tree:");
        segTree.printTree();

        // Apply a range update but DO NOT query immediately after
        // This ensures lazy tags remain in the tree
        int l1 = 1, r1 = 3, val = 2;
        System.out.println("\nUpdating range [" + l1 + ", " + r1 + "] by adding " + val + " to each element");
        segTree.updateRange(l1, r1, val);

        // Print the tree directly after update to see lazy tags
        System.out.println("\nTree after update (before any query):");
        segTree.printTree();

        // For clarity, also show the simple tree representation which should show lazy tags
        System.out.println("\nSimple tree representation with lazy tags:");
        segTree.printTreeSimple();

        // Apply another update to a different range, overlapping with the first
        int l2 = 0, r2 = 5, val2 = 3;
        System.out.println("\nUpdating another range [" + l2 + ", " + r2 + "] by adding " + val2 + " to each element");
        segTree.updateRange(l2, r2, val2);

        // Print again to see accumulated lazy tags
        System.out.println("\nTree after second update (before any query):");
        segTree.printTree();
        segTree.printTreeSimple();

        // Now perform a query which will trigger pushDown and clear lazy tags
        System.out.println("\nPerforming query for range [1, 3]: " + segTree.queryRange(1, 3));

        // Print after query to show that lazy tags are cleared
        System.out.println("\nTree after query (lazy tags should be cleared from queried nodes):");
        segTree.printTree();
        segTree.printTreeSimple();

        // Test update of a non-overlapping range to keep some lazy tags
        int l3 = 4, r3 = 5, val3 = 4;
        System.out.println("\nUpdating non-overlapping range [" + l3 + ", " + r3 + "] by adding " + val3);
        segTree.updateRange(l3, r3, val3);

        // Now some nodes should have lazy tags and some shouldn't
        System.out.println("\nFinal tree with mixed lazy tag state:");
        segTree.printTree();
        segTree.printTreeSimple();
    }
}