package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._441_lowest_common_ancestor_framework;

/**
 * Lowest Common Ancestor When Nodes May Be Missing
 * ---------------------------------------------------------
 * This class demonstrates finding the LCA when target nodes might not exist in the tree
 * (LeetCode #1644: Lowest Common Ancestor of a Binary Tree II).
 * <p>
 * Key points:
 * 1. Unlike standard LCA, we need to check if both p and q exist in the tree
 * 2. Need to perform a complete tree traversal to confirm existence of nodes
 * 3. Uses post-order traversal to check if targets exist before determining LCA
 * 4. If either node doesn't exist, return null (no LCA)
 * 5. Uses flags to track whether both nodes have been found during traversal
 */
public class _441_e_LCAWithMissingNodes {

    // Flags to track if p and q exist in the tree
    private boolean foundP = false;
    private boolean foundQ = false;

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
        _441_e_LCAWithMissingNodes solution = new _441_e_LCAWithMissingNodes();

        // Case 1: Both nodes exist
        int p1 = 5, q1 = 1;
        TreeNode lca1 = solution.lowestCommonAncestor(root, new TreeNode(p1), new TreeNode(q1));
        System.out.println("LCA of " + p1 + " and " + q1 + " is: " +
                (lca1 != null ? lca1.val : "null (not found)"));
        // Should output: LCA of 5 and 1 is: 3

        // Case 2: One node doesn't exist
        int p2 = 5, q2 = 9; // Node 9 doesn't exist in the tree
        TreeNode lca2 = solution.lowestCommonAncestor(root, new TreeNode(p2), new TreeNode(q2));
        System.out.println("LCA of " + p2 + " and " + q2 + " is: " +
                (lca2 != null ? lca2.val : "null (not found)"));
        // Should output: LCA of 5 and 9 is: null (not found)

        // Try the alternative implementation
        TreeNode lca3 = solution.lowestCommonAncestorAlt(root, new TreeNode(p1), new TreeNode(q1));
        System.out.println("Alt implementation: LCA of " + p1 + " and " + q1 + " is: " +
                (lca3 != null ? lca3.val : "null (not found)"));
        // Should output: Alt implementation: LCA of 5 and 1 is: 3
    }

    /**
     * Solution for LeetCode #1644: Lowest Common Ancestor of a Binary Tree II
     * Returns null if either p or q is not in the tree
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Reset flags in case of multiple calls
        foundP = false;
        foundQ = false;

        // Find the potential LCA
        TreeNode result = find(root, p.val, q.val);

        // If either node wasn't found, return null
        if (!foundP || !foundQ) {
            return null;
        }

        // Both p and q exist, return the LCA
        return result;
    }

    /**
     * Helper function to find the LCA of nodes with values val1 and val2
     * Also records if those nodes exist in the tree
     */
    private TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        // Search left and right subtrees
        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);

        // Post-order position
        // Check if current node is one of the targets
        if (root.val == val1) {
            foundP = true;
            return root;
        }
        if (root.val == val2) {
            foundQ = true;
            return root;
        }

        // If both subtrees found a target, current node is LCA
        if (left != null && right != null) {
            return root;
        }

        // Return whichever subtree found a target, or null if neither did
        return left != null ? left : right;
    }

    /**
     * Alternative implementation with the node checks in post-order
     * This ensures complete tree traversal, which is necessary when nodes may be missing
     */
    public TreeNode lowestCommonAncestorAlt(TreeNode root, TreeNode p, TreeNode q) {
        // Reset flags
        foundP = false;
        foundQ = false;

        // Find the potential LCA
        TreeNode result = findAlt(root, p.val, q.val);

        // If either node wasn't found, return null
        if (!foundP || !foundQ) {
            return null;
        }

        return result;
    }

    private TreeNode findAlt(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        // Search left and right subtrees
        TreeNode left = findAlt(root.left, val1, val2);
        TreeNode right = findAlt(root.right, val1, val2);

        // Post-order position - check current node
        if (root.val == val1) {
            foundP = true;
            return root;
        }
        if (root.val == val2) {
            foundQ = true;
            return root;
        }

        // If both subtrees found a target, current node is LCA
        if (left != null && right != null) {
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