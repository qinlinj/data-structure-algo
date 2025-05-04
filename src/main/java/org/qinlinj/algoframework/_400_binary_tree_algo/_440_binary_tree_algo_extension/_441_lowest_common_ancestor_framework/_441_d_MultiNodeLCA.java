package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._441_lowest_common_ancestor_framework;

import java.util.*;

/**
 * Lowest Common Ancestor (LCA) for Multiple Nodes
 * ---------------------------------------------------------
 * This class demonstrates finding the LCA of multiple nodes in a binary tree
 * (LeetCode #1676: Lowest Common Ancestor of a Binary Tree IV).
 * <p>
 * Key points:
 * 1. Extension of the standard LCA problem to handle multiple target nodes
 * 2. Uses a HashSet to store and check for target values
 * 3. Core algorithm logic remains similar to the standard LCA solution
 * 4. The solution can find the lowest node that is an ancestor of all target nodes
 */
public class _441_d_MultiNodeLCA {

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
        _441_d_MultiNodeLCA solution = new _441_d_MultiNodeLCA();

        // Create nodes array with multiple target nodes
        TreeNode[] nodes = new TreeNode[3];
        nodes[0] = root.left.right.left;  // Node with value 7
        nodes[1] = root.left.left;        // Node with value 6
        nodes[2] = root.left.right.right; // Node with value 4

        // Find LCA
        TreeNode lca = solution.lowestCommonAncestor(root, nodes);

        System.out.print("LCA of nodes with values [");
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].val);
            if (i < nodes.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] is: " + lca.val);
        // Should output: LCA of nodes with values [7, 6, 4] is: 5

        // Try the optimized version
        solution.lca = null; // Reset the lca variable
        TreeNode lcaOpt = solution.lowestCommonAncestorOptimized(root, nodes);
        System.out.println("Optimized result: " + lcaOpt.val);
        // Should also output: Optimized result: 5
    }

    /**
     * Solution for LeetCode #1676: Lowest Common Ancestor of a Binary Tree IV
     * Find the LCA of multiple nodes represented by the nodes array
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        // Convert nodes array to a HashSet of values for efficient lookups
        HashSet<Integer> values = new HashSet<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
        }

        // Call helper function to find LCA
        return find(root, values);
    }

    /**
     * Helper function to find the LCA of multiple nodes
     *
     * @param root   Root of the tree to search
     * @param values Set of target values to find
     * @return The lowest common ancestor node
     */
    private TreeNode find(TreeNode root, HashSet<Integer> values) {
        if (root == null) {
            return null;
        }

        // Pre-order position
        if (values.contains(root.val)) {
            // If current node is one of the targets, it could be the LCA
            return root;
        }

        // Search left and right subtrees
        TreeNode left = find(root.left, values);
        TreeNode right = find(root.right, values);

        // Post-order position
        if (left != null && right != null) {
            // If both subtrees contain target nodes, current node is the LCA
            return root;
        }

        // If only one subtree contains targets, return that result
        return left != null ? left : right;
    }

    public TreeNode lowestCommonAncestorOptimized(TreeNode root, TreeNode[] nodes) {
        // Convert nodes array to a HashSet of values
        HashSet<Integer> values = new HashSet<>();
        for (TreeNode node : nodes) {
            values.add(node.val);
        }

        // Call optimized helper function
        findOptimized(root, values);
        return lca;
    }

    private TreeNode findOptimized(TreeNode root, HashSet<Integer> values) {
        if (root == null) {
            return null;
        }

        // If already found LCA, no need to continue searching
        if (lca != null) {
            return null;
        }

        // Pre-order position
        if (values.contains(root.val)) {
            return root;
        }

        // Search subtrees
        TreeNode left = findOptimized(root.left, values);
        TreeNode right = findOptimized(root.right, values);

        // Post-order position
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