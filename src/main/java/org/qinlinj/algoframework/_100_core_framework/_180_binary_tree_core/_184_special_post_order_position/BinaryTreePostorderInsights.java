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

    // EFFICIENT APPROACH: O(N) time complexity
    // Using post-order position to calculate diameter and depth simultaneously
    public int diameterOfBinaryTreeEfficient(TreeNode root) {
        int[] maxDiameter = new int[1];
        maxDepth(root, maxDiameter);
        return maxDiameter[0];
    }

    private int maxDepth(TreeNode root, int[] maxDiameter) {
        if (root == null) {
            return 0;
        }

        // Recursively calculate depths of left and right subtrees
        int leftDepth = maxDepth(root.left, maxDiameter);
        int rightDepth = maxDepth(root.right, maxDiameter);

        // POST-ORDER POSITION
        // Now that we have information from both subtrees, calculate the diameter
        // passing through this node
        int diameter = leftDepth + rightDepth;
        maxDiameter[0] = Math.max(maxDiameter[0], diameter);

        // Return the depth of this subtree to its parent
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // =====================================================
    // EXAMPLE 4: BALANCED BINARY TREE (LEETCODE 110)
    // ANOTHER POST-ORDER APPLICATION
    // =====================================================

    public boolean isBalanced(TreeNode root) {
        return checkBalanced(root) != -1;
    }

    // Returns the height of the tree if balanced, -1 if not balanced
    private int checkBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Process left and right subtrees
        int leftHeight = checkBalanced(root.left);
        if (leftHeight == -1) return -1;  // Short-circuit if left subtree is unbalanced

        int rightHeight = checkBalanced(root.right);
        if (rightHeight == -1) return -1;  // Short-circuit if right subtree is unbalanced

        // POST-ORDER POSITION
        // Now that we have heights from both subtrees, check if this node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;  // This subtree is not balanced
        }

        // Return the height of this subtree to its parent
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // =====================================================
    // EXAMPLE 5: LOWEST COMMON ANCESTOR (LEETCODE 236)
    // ADVANCED POST-ORDER APPLICATION
    // =====================================================

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        // Look for p and q in left and right subtrees
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

        // POST-ORDER POSITION
        // Now we can make a decision based on what we found in the subtrees

        // Case 1: Found p in one subtree and q in the other
        if (leftResult != null && rightResult != null) {
            return root;  // This is the LCA
        }

        // Case 2: Found either p or q in one subtree, nothing in the other
        // Or found the LCA in one subtree
        return leftResult != null ? leftResult : rightResult;
    }

    // =====================================================
    // VISUALIZATION AND COMPARISON HELPERS
    // =====================================================

    public void visualizeIneffcientVsEfficient(TreeNode root) {
        System.out.println("=== COMPARISON OF APPROACHES FOR TREE DIAMETER ===");

        System.out.println("\nINEFFICIENT APPROACH (Separate maxDepth calls):");
        long startTime1 = System.nanoTime();
        int diameter1 = diameterOfBinaryTreeInefficient(root);
        long endTime1 = System.nanoTime();
        System.out.println("Result: " + diameter1);
        System.out.println("Time taken: " + (endTime1 - startTime1) + " ns");

        System.out.println("\nEFFICIENT APPROACH (Post-order optimization):");
        long startTime2 = System.nanoTime();
        int diameter2 = diameterOfBinaryTreeEfficient(root);
        long endTime2 = System.nanoTime();
        System.out.println("Result: " + diameter2);
        System.out.println("Time taken: " + (endTime2 - startTime2) + " ns");

        System.out.println("\nSpeed improvement: " +
                ((double) (endTime1 - startTime1) / (endTime2 - startTime2)) + "x");
    }

    // =====================================================
    // EXAMPLE 6: DEMONSTRATING POSITION CAPABILITIES
    // =====================================================

    public void demonstratePositionCapabilities(TreeNode root) {
        positionDemo(root, "Root");
    }

    private int positionDemo(TreeNode root, String path) {
        if (root == null) {
            return 0;
        }

        // PRE-ORDER POSITION
        System.out.println("\nPRE-ORDER at " + path + " (Node " + root.val + "):");
        System.out.println("- Can access: Current node value = " + root.val);
        System.out.println("- Can access: Path from root = " + path);
        System.out.println("- CANNOT YET access: Info about left or right subtrees");

        // Process left subtree with updated path
        int leftSum = positionDemo(root.left, path + " → Left");

        // IN-ORDER POSITION
        System.out.println("\nIN-ORDER at " + path + " (Node " + root.val + "):");
        System.out.println("- Can access: Current node value = " + root.val);
        System.out.println("- Can access: Path from root = " + path);
        System.out.println("- Can access: Left subtree sum = " + leftSum);
        System.out.println("- CANNOT YET access: Info about right subtree");

        // Process right subtree with updated path
        int rightSum = positionDemo(root.right, path + " → Right");

        // POST-ORDER POSITION
        int totalSum = leftSum + rightSum + root.val;
        System.out.println("\nPOST-ORDER at " + path + " (Node " + root.val + "):");
        System.out.println("- Can access: Current node value = " + root.val);
        System.out.println("- Can access: Path from root = " + path);
        System.out.println("- Can access: Left subtree sum = " + leftSum);
        System.out.println("- Can access: Right subtree sum = " + rightSum);
        System.out.println("- Can access: Total subtree sum = " + totalSum);

        return totalSum;
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
