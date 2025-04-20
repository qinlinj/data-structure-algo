package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._182_understanding_traversals;

/**
 * DEEPER UNDERSTANDING OF BINARY TREE TRAVERSALS
 * ==============================================
 * <p>
 * Beyond the Textbook Understanding:
 * Pre-order, in-order, and post-order are not merely three differently ordered lists.
 * They represent three SPECIAL TIMING POINTS during the traversal of each node:
 * <p>
 * - Pre-order position: Code executes IMMEDIATELY UPON ENTERING a node
 * - In-order position: Code executes AFTER traversing the left subtree but BEFORE traversing the right subtree
 * - Post-order position: Code executes JUST BEFORE LEAVING a node (after both subtrees are traversed)
 * <p>
 * Key Insights:
 * 1. Each node has its unique pre-order, in-order, and post-order positions
 * 2. N-ary trees don't have in-order positions because nodes can have multiple children
 * and don't have a unique moment of "switching from left to right"
 * 3. Binary tree algorithms boil down to injecting clever logic at these positions
 * 4. Post-order has special significance as it processes children before parents
 * <p>
 * The recursive traversal framework handles the movement between nodes;
 * you only need to decide what each individual node should do at each position.
 */
public class BinaryTreeTraversalInsights {
    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Definition for a singly linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
