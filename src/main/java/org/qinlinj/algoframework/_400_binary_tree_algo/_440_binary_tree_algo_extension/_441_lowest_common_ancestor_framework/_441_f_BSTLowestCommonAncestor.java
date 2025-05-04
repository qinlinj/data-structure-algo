package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._441_lowest_common_ancestor_framework;

/**
 * Lowest Common Ancestor in Binary Search Trees
 * ---------------------------------------------------------
 * This class demonstrates finding the LCA in a Binary Search Tree (BST)
 * (LeetCode #235: Lowest Common Ancestor of a Binary Search Tree).
 * <p>
 * Key points:
 * 1. BSTs have the property that left child < parent < right child
 * 2. This property allows for a more efficient LCA algorithm
 * 3. If both target values are less than root, LCA must be in left subtree
 * 4. If both target values are greater than root, LCA must be in right subtree
 * 5. If one value is less and one is greater, root is the LCA
 * 6. Much more efficient than the standard LCA algorithm (no need to traverse entire tree)
 */
public class _441_f_BSTLowestCommonAncestor {

    // Create a sample BST for demonstration
    public static TreeNode createSampleBST() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = createSampleBST();
        _441_f_BSTLowestCommonAncestor solution = new _441_f_BSTLowestCommonAncestor();

        // Example 1: Nodes in different subtrees
        int p1 = 2, q1 = 8;
        TreeNode lca1 = solution.lowestCommonAncestor(root, new TreeNode(p1), new TreeNode(q1));
        System.out.println("LCA of " + p1 + " and " + q1 + " is: " + lca1.val);
        // Should output: LCA of 2 and 8 is: 6

        // Example 2: One node is in subtree of the other
        int p2 = 2, q2 = 4;
        TreeNode lca2 = solution.lowestCommonAncestor(root, new TreeNode(p2), new TreeNode(q2));
        System.out.println("LCA of " + p2 + " and " + q2 + " is: " + lca2.val);
        // Should output: LCA of 2 and 4 is: 2

        // Try the iterative version
        TreeNode lcaIter = solution.lowestCommonAncestorIterative(root, new TreeNode(p1), new TreeNode(q1));
        System.out.println("Iterative: LCA of " + p1 + " and " + q1 + " is: " + lcaIter.val);
        // Should output: Iterative: LCA of 2 and 8 is: 6
    }

    /**
     * Solution for LeetCode #235: Lowest Common Ancestor of a Binary Search Tree
     * Uses the BST property to efficiently find the LCA
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Ensure p.val is the smaller value and q.val is the larger one
        int val1 = Math.min(p.val, q.val);
        int val2 = Math.max(p.val, q.val);

        return find(root, val1, val2);
    }

    /**
     * Helper function to find the LCA in a BST
     *
     * @param root Root of the BST
     * @param val1 Smaller target value
     * @param val2 Larger target value
     * @return The LCA node
     */
    private TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        // If both values are less than root, LCA must be in left subtree
        if (root.val > val2) {
            return find(root.left, val1, val2);
        }

        // If both values are greater than root, LCA must be in right subtree
        if (root.val < val1) {
            return find(root.right, val1, val2);
        }

        // If val1 <= root.val <= val2, root is the LCA
        // This means one value is in left subtree and one is in right subtree,
        // or one of the values is root itself
        return root;
    }

    /**
     * Iterative version of the BST LCA algorithm
     * Same logic, but without recursion
     */
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        int val1 = Math.min(p.val, q.val);
        int val2 = Math.max(p.val, q.val);

        // Traverse down the tree
        while (root != null) {
            if (root.val > val2) {
                // Both values are less than root, go left
                root = root.left;
            } else if (root.val < val1) {
                // Both values are greater than root, go right
                root = root.right;
            } else {
                // Found the LCA
                return root;
            }
        }

        return null; // Should never reach here if p and q exist in the tree
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