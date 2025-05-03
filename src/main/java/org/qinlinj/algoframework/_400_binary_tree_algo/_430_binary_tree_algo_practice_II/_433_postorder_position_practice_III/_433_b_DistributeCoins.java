package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._433_postorder_position_practice_III;

/**
 * Problem 979: Distribute Coins in Binary Tree (Medium)
 * <p>
 * Problem Description:
 * Given a binary tree with n nodes, where each node has node.val coins (possibly zero).
 * The total number of coins in the tree is exactly n. In one move, we can choose two adjacent
 * nodes and move one coin from one node to another. The goal is to make every node have exactly
 * one coin, and calculate the minimum number of moves required.
 * <p>
 * Key Concepts:
 * - Post-order traversal application: This problem demonstrates the usefulness of the post-order
 * position for gathering information from subtrees.
 * - Problem simplification: Instead of tracking both incoming and outgoing coins, we can simplify
 * the problem by allowing nodes to have negative coins temporarily and only allowing coins to
 * move from child to parent. This makes the logic much cleaner.
 * - Balance equation: For a node to be balanced (have exactly 1 coin), it needs to move
 * (node.val - 1) coins to/from its parent.
 * - Move counting: The total number of moves required is the sum of absolute values of coin
 * movements through each edge.
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */

class _433_b_DistributeCoins {
    // Result counter for minimum moves needed
    private int res = 0;

    // Main method for testing
    public static void main(String[] args) {
        _433_b_DistributeCoins solution = new _433_b_DistributeCoins();

        // Example 1: [3,0,0]
        TreeNode example1 = solution.new TreeNode(3);
        example1.left = solution.new TreeNode(0);
        example1.right = solution.new TreeNode(0);

        System.out.println("Example 1 Output: " + solution.distributeCoins(example1));
        // Expected: 2

        // Reset result counter
        solution.res = 0;

        // Example 2: [0,3,0]
        TreeNode example2 = solution.new TreeNode(0);
        example2.left = solution.new TreeNode(3);
        example2.right = solution.new TreeNode(0);

        System.out.println("Example 2 Output: " + solution.distributeCoins(example2));
        // Expected: 3
    }

    public int distributeCoins(TreeNode root) {
        // Call helper method to calculate excess/deficit coins
        getRest(root);
        return res;
    }

    /**
     * Helper method that calculates the excess coins in a subtree
     *
     * @param root The current root node
     * @return The number of excess coins that need to move up through this node
     * (negative number means deficit, requiring coins to move down)
     */
    private int getRest(TreeNode root) {
        // Base case: null node has no excess coins
        if (root == null) {
            return 0;
        }

        // Process left and right subtrees (post-order traversal)
        int left = getRest(root.left);
        int right = getRest(root.right);

        // Post-order position: calculate moves needed for this subtree
        // The number of moves is the sum of absolute coin movements
        res += Math.abs(left) + Math.abs(right);

        // Return the excess coins from this subtree to be passed up
        // Current node needs 1 coin, so excess is (current value - 1) plus
        // any excess from children
        return left + right + (root.val - 1);
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