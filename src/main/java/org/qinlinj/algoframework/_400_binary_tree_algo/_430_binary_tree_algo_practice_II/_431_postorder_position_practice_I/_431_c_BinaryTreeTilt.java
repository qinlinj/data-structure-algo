package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._431_postorder_position_practice_I;

/**
 * Problem 563: Binary Tree Tilt
 * <p>
 * Description:
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 * <p>
 * The tilt of a tree node is the absolute difference between the sum of all left subtree
 * node values and all right subtree node values. If a node doesn't have a left child, then
 * the sum of the left subtree node values is treated as 0. The rule is similar if the node
 * doesn't have a right child.
 * <p>
 * Key Concepts:
 * - Uses post-order traversal to calculate subtree sums and tilts
 * - Demonstrates how to compute multiple properties in a single traversal
 * - Maintains a global sum while returning subtree sums
 * - Shows efficient calculation of node properties during tree traversal
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _431_c_BinaryTreeTilt {
    // Total tilt of the tree
    private int totalTilt = 0;

    /**
     * Main function to find the sum of all tilts
     *
     * @param root Root of the binary tree
     * @return Sum of all node tilts
     */
    public int findTilt(TreeNode root) {
        // Reset total tilt for this calculation
        totalTilt = 0;

        // Calculate subtree sums, which also updates the total tilt
        calculateSum(root);

        return totalTilt;
    }

    /**
     * Helper function to calculate subtree sum and update tilt
     *
     * @param node Root of the current subtree
     * @return Sum of all values in the subtree
     */
    private int calculateSum(TreeNode node) {
        // Base case: null node contributes 0 to sum
        if (node == null) {
            return 0;
        }

        // Calculate sum of left and right subtrees
        int leftSum = calculateSum(node.left);
        int rightSum = calculateSum(node.right);

        // Post-order position: calculate and update tilt
        int nodeTilt = Math.abs(leftSum - rightSum);
        totalTilt += nodeTilt;

        // Return total sum of this subtree (including current node)
        return leftSum + rightSum + node.val;
    }

    /**
     * Alternative implementation without using a class field
     */
    public int findTiltAlternative(TreeNode root) {
        int[] totalTilt = {0}; // Use an array to simulate pass by reference
        calculateSumAndTilt(root, totalTilt);
        return totalTilt[0];
    }

    /**
     * Helper function for alternative implementation
     *
     * @param node      Current node
     * @param totalTilt Array to track total tilt (pass by reference)
     * @return Sum of the subtree
     */
    private int calculateSumAndTilt(TreeNode node, int[] totalTilt) {
        if (node == null) {
            return 0;
        }

        // Calculate sums of subtrees
        int leftSum = calculateSumAndTilt(node.left, totalTilt);
        int rightSum = calculateSumAndTilt(node.right, totalTilt);

        // Update total tilt
        totalTilt[0] += Math.abs(leftSum - rightSum);

        // Return subtree sum
        return leftSum + rightSum + node.val;
    }

    /**
     * Implementation using a helper class to track multiple values
     */
    public int findTiltWithHelper(TreeNode root) {
        NodeInfo info = calculateNodeInfo(root);
        return info.tiltSum;
    }

    /**
     * Helper function using NodeInfo class
     */
    private NodeInfo calculateNodeInfo(TreeNode node) {
        if (node == null) {
            return new NodeInfo(0, 0);
        }

        // Process left and right subtrees
        NodeInfo leftInfo = calculateNodeInfo(node.left);
        NodeInfo rightInfo = calculateNodeInfo(node.right);

        // Calculate current node's tilt
        int nodeTilt = Math.abs(leftInfo.sum - rightInfo.sum);

        // Calculate subtree sum
        int subtreeSum = node.val + leftInfo.sum + rightInfo.sum;

        // Calculate total tilt sum
        int totalTiltSum = nodeTilt + leftInfo.tiltSum + rightInfo.tiltSum;

        return new NodeInfo(subtreeSum, totalTiltSum);
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
     * Helper class to track both sum and tilt
     */
    private class NodeInfo {
        int sum;      // Sum of the subtree
        int tiltSum;  // Sum of tilts in the subtree

        NodeInfo(int sum, int tiltSum) {
            this.sum = sum;
            this.tiltSum = tiltSum;
        }
    }
}
