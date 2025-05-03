package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._431_postorder_position_practice_I;

/**
 * Problem 1325: Remove Leaf Nodes with a Given Value
 * <p>
 * Description:
 * Given a binary tree root and an integer target, remove all the leaf nodes with value target.
 * <p>
 * Note that once you remove a leaf node with value target, if its parent becomes a leaf node
 * and has the value target, it should also be removed (recursively).
 * <p>
 * Key Concepts:
 * - Uses post-order traversal for bottom-up node removal
 * - Demonstrates recursive leaf node identification and removal
 * - Shows the power of return values in modifying tree structure
 * - Implements iterative pruning through recursive structure
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _431_e_RemoveLeafNodesWithGivenValue {
    /**
     * Main function to remove leaf nodes with target value
     *
     * @param root   Root of the binary tree
     * @param target Target value for removal
     * @return Root of the modified tree
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // Base case: empty tree
        if (root == null) {
            return null;
        }

        // Post-order traversal: process children first
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        // Post-order position: check if current node is a leaf with target value
        if (root.left == null && root.right == null && root.val == target) {
            return null; // Remove this leaf node
        }

        // Keep this node
        return root;
    }

    /**
     * Alternative implementation that explicitly tracks whether a node should be removed
     */
    public TreeNode removeLeafNodesAlternative(TreeNode root, int target) {
        return processNode(root, target);
    }

    /**
     * Helper function to determine if a node should be removed
     *
     * @param node   Current node
     * @param target Target value for removal
     * @return Updated node or null if removed
     */
    private TreeNode processNode(TreeNode node, int target) {
        if (node == null) {
            return null;
        }

        // Process children
        node.left = processNode(node.left, target);
        node.right = processNode(node.right, target);

        // Check if node is now a leaf (after potentially removing its children)
        boolean isLeaf = node.left == null && node.right == null;

        // Remove if it's a leaf with target value
        if (isLeaf && node.val == target) {
            return null;
        }

        return node;
    }

    /**
     * Implementation with a helper class to track removal status
     */
    public TreeNode removeLeafNodesWithHelper(TreeNode root, int target) {
        RemovalResult result = processNodeWithStatus(root, target);
        return result.node;
    }

    /**
     * Helper function using RemovalResult class
     */
    private RemovalResult processNodeWithStatus(TreeNode node, int target) {
        if (node == null) {
            return new RemovalResult(null, false);
        }

        // Process children
        RemovalResult leftResult = processNodeWithStatus(node.left, target);
        RemovalResult rightResult = processNodeWithStatus(node.right, target);

        // Update child references
        node.left = leftResult.node;
        node.right = rightResult.node;

        // Check if node is now a leaf with target value
        if (node.left == null && node.right == null && node.val == target) {
            return new RemovalResult(null, true);
        }

        return new RemovalResult(node, false);
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
     * Helper class to track node and its removal status
     */
    private class RemovalResult {
        TreeNode node;
        boolean isRemoved;

        RemovalResult(TreeNode node, boolean isRemoved) {
            this.node = node;
            this.isRemoved = isRemoved;
        }
    }
}
