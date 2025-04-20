package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._184_special_post_order_position;

/**
 * THE SPECIAL PROPERTIES OF POST-ORDER TRAVERSAL
 * ==============================================
 * <p>
 * COMPARATIVE POWER OF TRAVERSAL POSITIONS:
 * <p>
 * 1. PRE-ORDER Position:
 * - Can only access data passed down from parent nodes via function parameters
 * - Often used for operations that don't require information from subtrees
 * - Common for top-down operations and default code placement
 * <p>
 * 2. IN-ORDER Position:
 * - Can access both parameter data and information returned from the left subtree
 * - Particularly useful in Binary Search Trees (BST) - equivalent to traversing a sorted array
 * - Limited application in general binary trees
 * <p>
 * 3. POST-ORDER Position:
 * - Most powerful - can access parameter data AND information from BOTH left and right subtrees
 * - Essential for problems requiring complete subtree information
 * - Enables bottom-up calculations where parent nodes need data from their children
 * - Often the only position where certain problems can be solved efficiently
 * <p>
 * IMPORTANT INSIGHT:
 * When you encounter problems related to subtrees, you'll likely need to:
 * 1. Define an appropriate recursive function with a meaningful return value
 * 2. Place your core logic in the post-order position
 * <p>
 * This pattern appears in many tree problems including calculating tree diameters,
 * determining if a tree is balanced, and finding paths with specific properties.
 */
public class BinaryTreePostorderInsights {
    // =====================================================
    // EXAMPLE 1: PRINTING NODE LEVELS (PRE-ORDER CAPABLE)
    // =====================================================
    public void printNodeLevels(TreeNode root) {
        traverse(root, 1);
    }

    private void traverse(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        // PRE-ORDER POSITION
        // We can easily print the level here because it's passed down from the parent
        System.out.println("Node " + root.val + " is at level " + level);

        // Pass updated level to children
        traverse(root.left, level + 1);
        traverse(root.right, level + 1);
    }

    // =====================================================
    // EXAMPLE 2: PRINTING SUBTREE NODE COUNTS (REQUIRES POST-ORDER)
    // =====================================================
    public void printSubtreeNodeCounts(TreeNode root) {
        count(root);
    }

    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // First calculate counts for subtrees
        int leftCount = count(root.left);
        int rightCount = count(root.right);

        // POST-ORDER POSITION
        // We can only print this information AFTER we've processed both subtrees
        System.out.println("Node " + root.val + " has " + leftCount +
                " nodes in its left subtree and " + rightCount +
                " nodes in its right subtree");

        // Return total count of this subtree to parent
        return leftCount + rightCount + 1;
    }

    // =====================================================
    // EXAMPLE 3: TREE DIAMETER (LEETCODE 543)
    // DEMONSTRATING POST-ORDER OPTIMIZATION
    // =====================================================
    // INEFFICIENT APPROACH: O(N²) time complexity
    // Using separate traversal and maxDepth functions
    public int diameterOfBinaryTreeInefficient(TreeNode root) {
        // External variable to track maximum diameter
        int[] maxDiameter = new int[1];
        traverseForDiameter(root, maxDiameter);
        return maxDiameter[0];
    }

    private void traverseForDiameter(TreeNode root, int[] maxDiameter) {
        if (root == null) {
            return;
        }

        // For each node, calculate its diameter by finding depths of left and right subtrees
        int leftDepth = maxDepthSimple(root.left);
        int rightDepth = maxDepthSimple(root.right);
        int diameter = leftDepth + rightDepth;

        // Update global maximum
        maxDiameter[0] = Math.max(maxDiameter[0], diameter);

        // Continue traversal
        traverseForDiameter(root.left, maxDiameter);
        traverseForDiameter(root.right, maxDiameter);
    }

    private int maxDepthSimple(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepthSimple(root.left), maxDepthSimple(root.right));
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        // Helper constructor for creating trees easily
        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }
}
