package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._433_postorder_position_practice_III;

/**
 * Problem 968: Binary Tree Cameras (Hard)
 * <p>
 * Problem Description:
 * Given a binary tree, install cameras on the nodes such that each camera can monitor its parent,
 * itself, and its direct children. Calculate the minimum number of cameras needed to monitor all nodes.
 * <p>
 * Key Concepts:
 * - Post-order traversal advantage: At the post-order position, we can receive information from subtrees
 * and also receive information from parent nodes through function parameters.
 * - Node state enumeration: We identify three possible states for a node:
 * 1. Uncover: Node is not in any monitoring area
 * 2. Cover: Node is within monitoring range of a nearby node
 * 3. Set: Node has a camera installed
 * - Bottom-up camera installation strategy: To minimize camera count, we install cameras from bottom to top,
 * placing them at parent nodes of leaf nodes, then every two layers (since each camera covers three levels).
 * - Decision criteria: A node needs a camera when any of its children are in an uncover state.
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */

class _433_a_BinaryTreeCamera {
    // Result counter for minimum cameras needed
    private int res = 0;

    // Main method for testing
    public static void main(String[] args) {
        _433_a_BinaryTreeCamera solution = new _433_a_BinaryTreeCamera();

        // Example 1: [0,0,null,0,0]
        TreeNode example1 = solution.new TreeNode(0);
        example1.left = solution.new TreeNode(0);
        example1.left.left = solution.new TreeNode(0);
        example1.left.right = solution.new TreeNode(0);

        System.out.println("Example 1 Output: " + solution.minCameraCover(example1));
        // Expected: 1

        // Reset result counter
        solution.res = 0;

        // Example 2: [0,0,null,0,null,0,null,null,0]
        TreeNode example2 = solution.new TreeNode(0);
        example2.left = solution.new TreeNode(0);
        example2.left.left = solution.new TreeNode(0);
        example2.left.left.right = solution.new TreeNode(0);
        example2.left.left.right.right = solution.new TreeNode(0);

        System.out.println("Example 2 Output: " + solution.minCameraCover(example2));
        // Expected: 2
    }

    public int minCameraCover(TreeNode root) {
        // Call helper method with root node
        // The boolean parameter indicates whether this node has a parent
        int rootState = setCamera(root, false);

        // If root is uncovered after processing, we need to add a camera
        // This handles the special case where root is a leaf or only has covered children
        if (rootState == 0) {
            res++;
        }

        return res;
    }

    /**
     * Helper method to set cameras optimally in a binary tree
     *
     * @param root      The current root node
     * @param hasParent Whether the current node has a parent
     * @return Status code representing node state:
     * -1: Node is null (no node)
     * 0: Node is not covered
     * 1: Node is covered (by a camera in a neighboring node)
     * 2: Node has a camera
     */
    private int setCamera(TreeNode root, boolean hasParent) {
        // Base case: null node
        if (root == null) {
            return -1;
        }

        // Process left and right subtrees (post-order traversal)
        int left = setCamera(root.left, true);
        int right = setCamera(root.right, true);

        // Case 1: Current node is a leaf node
        if (left == -1 && right == -1) {
            if (hasParent) {
                // If it has a parent, let the parent cover it
                return 0; // Return uncovered
            } else {
                // No parent to cover it, so we must place a camera here
                res++;
                return 2; // Return camera placed
            }
        }

        // Case 2: At least one child is uncovered
        // Must place a camera at current node
        if (left == 0 || right == 0) {
            res++;
            return 2; // Return camera placed
        }

        // Case 3: At least one child has a camera
        // Current node is already covered
        if (left == 2 || right == 2) {
            return 1; // Return covered
        }

        // Case 4: Both children are covered but don't have cameras
        if (hasParent) {
            // If it has a parent, let the parent cover it
            return 0; // Return uncovered
        } else {
            // No parent to cover it, must place a camera here
            res++;
            return 2; // Return camera placed
        }
    }

    // TreeNode definition
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