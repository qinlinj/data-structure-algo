package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._441_lowest_common_ancestor_framework;

/**
 * Standard Lowest Common Ancestor (LCA) Problem
 * ---------------------------------------------------------
 * This class demonstrates the solution to the standard LCA problem
 * (LeetCode #236: Lowest Common Ancestor of a Binary Tree).
 * <p>
 * Key points:
 * 1. The LCA is the node where paths to p and q from the root first diverge
 * 2. There are two cases to handle:
 * - Case 1: p and q are in different subtrees (LCA is their first common ancestor)
 * - Case 2: p or q is the LCA of the other (one node is in the subtree of the other)
 * 3. The algorithm uses post-order traversal to solve the problem
 * 4. Solution can be optimized to stop traversal once the LCA is found
 */
public class _441_c_StandardLCA {

    /**
     * Optimized version that stops traversal once LCA is found
     */
    TreeNode lca = null; // Class variable to store the LCA once found

    // Create a sample tree for demonstration
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = createSampleTree();
        _441_c_StandardLCA solution = new _441_c_StandardLCA();

        // Example 1: nodes in different subtrees
        TreeNode p1 = root.left.left; // Node with value 6
        TreeNode q1 = root.right.right; // Node with value 8
        TreeNode lca1 = solution.lowestCommonAncestor(root, p1, q1);
        System.out.println("LCA of " + p1.val + " and " + q1.val + " is: " + lca1.val);
        // Should output: LCA of 6 and 8 is: 3

        // Example 2: one node is ancestor of the other
        TreeNode p2 = root.left; // Node with value 5
        TreeNode q2 = root.left.right.right; // Node with value 4
        TreeNode lca2 = solution.lowestCommonAncestor(root, p2, q2);
        System.out.println("LCA of " + p2.val + " and " + q2.val + " is: " + lca2.val);
        // Should output: LCA of 5 and 4 is: 5

        // Reset the lca variable for the optimized version
        solution.lca = null;

        // Try the optimized version
        TreeNode lcaOpt = solution.lowestCommonAncestorOptimized(root, p1, q1);
        System.out.println("Optimized LCA of " + p1.val + " and " + q1.val + " is: " + lcaOpt.val);
        // Should output: Optimized LCA of 6 and 8 is: 3
    }

    /**
     * Solution for LeetCode #236: Lowest Common Ancestor of a Binary Tree
     * Assumes p and q exist in the tree.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p.val, q.val);
    }

    /**
     * Helper function to find the LCA of nodes with values val1 and val2
     */
    private TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        // Pre-order position
        if (root.val == val1 || root.val == val2) {
            // If current node is one of the targets, it could be the LCA
            return root;
        }

        // Search in left and right subtrees
        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);

        // Post-order position - determine if current node is LCA
        if (left != null && right != null) {
            // If both subtrees contain one of the targets, current node is LCA
            return root;
        }

        // If only one subtree contains a target, return that result
        return left != null ? left : right;
    }

    public TreeNode lowestCommonAncestorOptimized(TreeNode root, TreeNode p, TreeNode q) {
        findOptimized(root, p.val, q.val);
        return lca;
    }

    private TreeNode findOptimized(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        // If already found LCA, no need to continue searching
        if (lca != null) {
            return null;
        }

        if (root.val == val1 || root.val == val2) {
            return root;
        }

        TreeNode left = findOptimized(root.left, val1, val2);
        TreeNode right = findOptimized(root.right, val1, val2);

        if (left != null && right != null) {
            // Found the LCA, store it
            lca = root;
            return root;
        }

        return left != null ? left : right;
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}