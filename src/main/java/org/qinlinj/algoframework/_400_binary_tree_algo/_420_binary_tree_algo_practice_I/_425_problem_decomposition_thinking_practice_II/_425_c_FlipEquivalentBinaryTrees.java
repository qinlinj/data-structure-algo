package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._425_problem_decomposition_thinking_practice_II;

/**
 * Problem 951: Flip Equivalent Binary Trees
 * <p>
 * Description:
 * For a binary tree T, we can define a flip operation as choosing any node and swapping
 * its left and right children. Two binary trees are flip equivalent if we can convert
 * one tree to the other by performing some number of flip operations.
 * <p>
 * Given the roots of two binary trees root1 and root2, return true if the two trees are
 * flip equivalent, and false otherwise.
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach for checking flip equivalence
 * - Recursively examines pairs of nodes with two possibilities:
 * 1. No flip needed - match node.left with node.left and node.right with node.right
 * 2. Flip needed - match node.left with node.right and node.right with node.left
 * - Only one of the two possibilities needs to be true for flip equivalence
 * <p>
 * Time Complexity: O(min(n1, n2)), where n1 and n2 are the number of nodes in the trees
 * Space Complexity: O(min(h1, h2)), where h1 and h2 are the heights of the trees
 */
public class _425_c_FlipEquivalentBinaryTrees {
    /**
     * Determines if two binary trees are flip equivalent
     *
     * @param root1 Root of the first tree
     * @param root2 Root of the second tree
     * @return True if the trees are flip equivalent, false otherwise
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // Case 1: Both nodes are null - they are flip equivalent (empty trees match)
        if (root1 == null && root2 == null) {
            return true;
        }

        // Case 2: One node is null but the other isn't - not flip equivalent
        if (root1 == null || root2 == null) {
            return false;
        }

        // Case 3: Nodes' values must match regardless of flipping
        if (root1.val != root2.val) {
            return false;
        }

        // Case 4: Check both flip possibilities:
        // 1. No flip needed - check if corresponding subtrees are flip equivalent
        boolean noFlip = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);

        // 2. Flip needed - check if the flipped subtrees are flip equivalent
        boolean withFlip = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);

        // Trees are flip equivalent if either possibility is true
        return noFlip || withFlip;
    }

    /**
     * Alternative implementation that normalizes the trees before comparing
     * This approach is less efficient but demonstrates another way to think about the problem
     */
    public boolean flipEquivByNormalizing(TreeNode root1, TreeNode root2) {
        // First, normalize both trees by ensuring left child's value is less than right child's
        // This creates a canonical representation
        normalizeTree(root1);
        normalizeTree(root2);

        // Then compare the normalized trees for exact equivalence
        return isSameTree(root1, root2);
    }

    /**
     * Normalizes a tree by ensuring that for any node:
     * - If both children exist and left.val > right.val, flip them
     * - Apply recursively to all subtrees
     */
    private void normalizeTree(TreeNode root) {
        if (root == null) {
            return;
        }

        // Normalize subtrees first (post-order traversal)
        normalizeTree(root.left);
        normalizeTree(root.right);

        // If both children exist and left.val > right.val, flip them
        if (root.left != null && root.right != null && root.left.val > root.right.val) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
    }

    /**
     * Helper method to check if two trees are identical
     */
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}