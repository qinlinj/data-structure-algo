package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._424_problem_decomposition_thinking_practice_I;

import java.util.*;

/**
 * Problem 894: All Possible Full Binary Trees
 * <p>
 * Description:
 * Given an integer n, return a list of all possible full binary trees with n nodes.
 * Each node of each tree in the answer must have Node.val == 0.
 * <p>
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach for tree construction
 * - A full binary tree must have an odd number of nodes
 * - Recursively constructs all possible combinations of left and right subtrees
 * - Uses dynamic programming (memoization) to avoid redundant calculations
 * - Generates all structurally unique trees with the given number of nodes
 * <p>
 * Time Complexity: O(2^n), but with memoization it reduces significantly
 * Space Complexity: O(n * Catalan(n)) for storing all possible trees
 */
public class _424_e_AllPossibleFullBinaryTrees {
    // Memoization to avoid redundant calculations
    private List<TreeNode>[] memo;

    public List<TreeNode> allPossibleFBT(int n) {
        // A full binary tree must have an odd number of nodes
        if (n % 2 == 0) {
            return new LinkedList<>();
        }

        // Initialize memoization array
        memo = new List[n + 1];

        return generateTrees(n);
    }

    /**
     * Generates all possible full binary trees with n nodes
     *
     * @param n The number of nodes
     * @return List of all possible full binary tree root nodes
     */
    private List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new LinkedList<>();

        // Base case: a tree with 1 node
        if (n == 1) {
            result.add(new TreeNode(0));
            return result;
        }

        // Check memoization cache
        if (memo[n] != null) {
            return memo[n];
        }

        // A full binary tree with n nodes can be constructed by:
        // - Making a root node (1 node)
        // - Distributing the remaining (n-1) nodes between left and right subtrees
        // - Both subtrees must have odd number of nodes to maintain the "full" property

        // Try all odd numbers for left subtree size
        for (int leftSize = 1; leftSize < n; leftSize += 2) {
            int rightSize = n - 1 - leftSize;

            // Generate all possible left and right subtrees
            List<TreeNode> leftTrees = generateTrees(leftSize);
            List<TreeNode> rightTrees = generateTrees(rightSize);

            // Create all possible combinations
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    // Create a new root with the current left and right subtrees
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        // Store in memoization cache
        memo[n] = result;
        return result;
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
