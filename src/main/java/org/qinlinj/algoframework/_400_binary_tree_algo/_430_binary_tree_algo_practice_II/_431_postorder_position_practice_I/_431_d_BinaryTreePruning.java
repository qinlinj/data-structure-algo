package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._431_postorder_position_practice_I;

/**
 * Problem 814: Binary Tree Pruning
 * <p>
 * Description:
 * Given the root of a binary tree, return the same tree where every subtree (of the given tree)
 * not containing a 1 has been removed.
 * <p>
 * A subtree of a node is the node with all its descendants (its own tree).
 * <p>
 * Key Concepts:
 * - Uses post-order traversal for bottom-up tree modification
 * - Demonstrates tree pruning using recursive return values
 * - Shows how to propagate modifications up the tree
 * - Efficiently removes subtrees by updating parent references
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _431_d_BinaryTreePruning {
    /**
     * Main function to prune the binary tree
     *
     * @param root Root of the binary tree
     * @return Root of the pruned tree
     */
    public TreeNode pruneTree(TreeNode root) {
        // Empty tree case
        if (root == null) {
            return null;
        }

        // Apply recursive pruning
        return containsOne(root) ? root : null;
    }

    /**
     * Helper function to check if a subtree contains at least one '1' node
     * while also pruning nodes that don't contain '1's
     *
     * @param node Root of the current subtree
     * @return true if the subtree contains at least one '1', false otherwise
     */
    private boolean containsOne(TreeNode node) {
        // Base case: null node doesn't contain any 1s
        if (node == null) {
            return false;
        }

        // Check and prune left subtree
        boolean leftContainsOne = containsOne(node.left);
        if (!leftContainsOne) {
            node.left = null; // Prune left subtree
        }

        // Check and prune right subtree
        boolean rightContainsOne = containsOne(node.right);
        if (!rightContainsOne) {
            node.right = null; // Prune right subtree
        }

        // Post-order position: determine if current subtree contains a 1
        // Either the current node is 1, or one of its subtrees contains a 1
        return node.val == 1 || leftContainsOne || rightContainsOne;
    }

    /**
     * Alternative implementation that combines checking and pruning in one function
     */
    public TreeNode pruneTreeAlternative(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Post-order traversal: first process children, then decide if current node should be pruned
        root.left = pruneTreeAlternative(root.left);
        root.right = pruneTreeAlternative(root.right);

        // Post-order position: check if this node is a leaf with value 0 (should be pruned)
        if (root.val == 0 && root.left == null && root.right == null) {
            return null; // Prune this node
        }

        // Otherwise, keep this node
        return root;
    }

    /**
     * Implementation that directly uses the post-order position
     * to decide whether to prune a node
     */
    public TreeNode pruneTreeSimplified(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Process left and right children
        root.left = pruneTreeSimplified(root.left);
        root.right = pruneTreeSimplified(root.right);

        // Post-order position: if this node has value 0 and no children, prune it
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }

        return root;
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
