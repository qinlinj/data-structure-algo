package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._432_postorder_position_practice_II;

/**
 * Problem 865: Smallest Subtree with All the Deepest Nodes
 * <p>
 * Description:
 * Given the root of a binary tree, return the smallest subtree such that it contains
 * all the deepest nodes in the original tree.
 * <p>
 * A node is the deepest if it has the largest depth possible in the entire tree.
 * The subtree of a node is the tree consisting of that node and all of its descendants.
 * <p>
 * Key Concepts:
 * - Uses post-order traversal to track both node depths and lowest common ancestors
 * - Combines multiple return values using a helper class
 * - Efficiently computes the lowest common ancestor of all deepest nodes
 * - Uses a bottom-up approach to propagate depth information
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _432_b_SmallestSubtreeWithDeepestNodes {
    /**
     * Main function to find the smallest subtree containing all deepest nodes
     *
     * @param root Root of the binary tree
     * @return Root of the smallest subtree containing all deepest nodes
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return deepestNodes(root).node;
    }

    /**
     * Helper function to find both the deepest nodes and their LCA
     *
     * @param node Current node being processed
     * @return Result containing the subtree node and its maximum depth
     */
    private Result deepestNodes(TreeNode node) {
        // Base case: null node has depth 0 and no subtree
        if (node == null) {
            return new Result(null, 0);
        }

        // Recursively process left and right subtrees
        Result leftResult = deepestNodes(node.left);
        Result rightResult = deepestNodes(node.right);

        // Post-order position: decide the deepest subtree

        // If left and right subtrees have the same depth,
        // the current node is the LCA of the deepest nodes
        if (leftResult.depth == rightResult.depth) {
            return new Result(node, leftResult.depth + 1);
        }

        // If left subtree is deeper, return it with updated depth
        if (leftResult.depth > rightResult.depth) {
            return new Result(leftResult.node, leftResult.depth + 1);
        }

        // Otherwise, right subtree is deeper
        return new Result(rightResult.node, rightResult.depth + 1);
    }

    /**
     * Alternative implementation using separate tracking of depth and LCA
     */
    public TreeNode subtreeWithAllDeepestAlternative(TreeNode root) {
        if (root == null) {
            return null;
        }

        // First find the maximum depth
        int maxDepth = findMaxDepth(root);

        // Then find the LCA of all nodes at that depth
        return findLCA(root, 1, maxDepth);
    }

    /**
     * Helper function to find the maximum depth of the tree
     */
    private int findMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = findMaxDepth(node.left);
        int rightDepth = findMaxDepth(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * Helper function to find the LCA of all nodes at a specific depth
     * Returns null if no nodes at that depth in this subtree,
     * returns the single deepest node if only one exists,
     * otherwise returns the LCA of all deepest nodes
     */
    private TreeNode findLCA(TreeNode node, int currentDepth, int targetDepth) {
        if (node == null) {
            return null;
        }

        // If this is a deepest node, return it
        if (currentDepth == targetDepth) {
            return node;
        }

        // Find deepest nodes in left and right subtrees
        TreeNode leftLCA = findLCA(node.left, currentDepth + 1, targetDepth);
        TreeNode rightLCA = findLCA(node.right, currentDepth + 1, targetDepth);

        // If both subtrees have deepest nodes, this node is the LCA
        if (leftLCA != null && rightLCA != null) {
            return node;
        }

        // Return whichever subtree has deepest nodes
        return leftLCA != null ? leftLCA : rightLCA;
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

    /**
     * Helper class to store both depth and node information
     * This allows us to return multiple values from our recursive function
     */
    private class Result {
        TreeNode node;  // Subtree containing all deepest nodes
        int depth;      // Maximum depth of the subtree

        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}