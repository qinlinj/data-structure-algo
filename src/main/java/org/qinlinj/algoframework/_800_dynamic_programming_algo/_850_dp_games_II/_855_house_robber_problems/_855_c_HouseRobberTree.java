package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._855_house_robber_problems;

import java.util.*;

/**
 * HOUSE ROBBER III - BINARY TREE DYNAMIC PROGRAMMING
 * <p>
 * PROBLEM SUMMARY:
 * Houses are arranged in a binary tree structure. You cannot rob two directly connected houses.
 * Find the maximum amount of money you can rob.
 * <p>
 * KEY INSIGHTS:
 * 1. Tree DP: Each node has two states - robbed or not robbed
 * 2. If we rob current node, we cannot rob its children
 * 3. If we don't rob current node, we can choose optimally from children
 * <p>
 * TWO APPROACHES:
 * 1. Top-down with memoization: Store results for each node
 * 2. Bottom-up optimal: Return array [not_rob, rob] for each subtree
 * <p>
 * APPROACH 2 ADVANTAGES:
 * - No extra space for memoization
 * - Single traversal
 * - Cleaner code using post-order traversal
 * <p>
 * TIME COMPLEXITY: O(n) where n is number of nodes
 * SPACE COMPLEXITY: O(h) where h is height of tree (recursion stack)
 */

// Definition for a binary tree node
class TreeNode {
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

public class _855_c_HouseRobberTree {
    // Approach 1: Top-down with memoization
    private Map<TreeNode, Integer> memo = new HashMap<>();

    // Helper method to build test trees
    public static TreeNode buildTree1() {
        // Tree:     3
        //          / \
        //         2   3
        //          \   \
        //           3   1
        // Expected: 7 (rob nodes with values 3, 3, 1)
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        return root;
    }

    public static TreeNode buildTree2() {
        // Tree:     3
        //          / \
        //         4   5
        //        / \   \
        //       1   3   1
        // Expected: 9 (rob nodes with values 4, 5)
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        return root;
    }

    public static TreeNode buildTree3() {
        // Tree:     5
        //          / \
        //         1   2
        //            / \
        //           3   4
        // Expected: 9 (rob nodes 5, 3, 4)
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        return root;
    }

    // Helper method for tree visualization (level-order)
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    System.out.print(node.val + " ");
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    System.out.print("null ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int robWithMemo(TreeNode root) {
        if (root == null) return 0;

        // Check memoization
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        // Option 1: Rob current node, skip children, rob grandchildren
        int robCurrent = root.val;
        if (root.left != null) {
            robCurrent += robWithMemo(root.left.left) + robWithMemo(root.left.right);
        }
        if (root.right != null) {
            robCurrent += robWithMemo(root.right.left) + robWithMemo(root.right.right);
        }

        // Option 2: Don't rob current node, rob children optimally
        int skipCurrent = robWithMemo(root.left) + robWithMemo(root.right);

        int result = Math.max(robCurrent, skipCurrent);
        memo.put(root, result);
        return result;
    }

    // Approach 2: Optimal bottom-up approach (recommended)
    public int rob(TreeNode root) {
        int[] result = dpOptimal(root);
        return Math.max(result[0], result[1]);
    }

    /**
     * Returns array of size 2:
     * result[0] = maximum money if we DON'T rob current node
     * result[1] = maximum money if we DO rob current node
     */
    private int[] dpOptimal(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        // Get optimal results from left and right subtrees
        int[] left = dpOptimal(root.left);
        int[] right = dpOptimal(root.right);

        // If we rob current node, we cannot rob children
        int robCurrent = root.val + left[0] + right[0];

        // If we don't rob current node, we take optimal from children
        int skipCurrent = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{skipCurrent, robCurrent};
    }
}
