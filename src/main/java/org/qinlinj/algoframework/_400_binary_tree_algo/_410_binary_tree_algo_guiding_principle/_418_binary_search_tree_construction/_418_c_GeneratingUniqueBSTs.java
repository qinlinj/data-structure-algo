package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._418_binary_search_tree_construction;

/**
 * Binary Search Tree Theory - Generating All Unique BSTs
 * <p>
 * This class implements the solution to LeetCode 95: Unique Binary Search Trees II
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 * <p>
 * Problem: Given an integer n, generate all structurally unique BST's (binary search trees)
 * that store values 1...n.
 * <p>
 * Key insights:
 * 1. This is an extension of the BST counting problem, but instead of just counting,
 * we need to construct and return all possible trees.
 * <p>
 * 2. The approach follows a similar recursive structure:
 * - For each number i from 1 to n, consider it as the root
 * - Recursively generate all possible left subtrees using values from 1 to i-1
 * - Recursively generate all possible right subtrees using values from i+1 to n
 * - Combine each left subtree with each right subtree to form complete BSTs
 * <p>
 * 3. A critical detail: when recursing on an empty range, return a list containing null
 * rather than an empty list, to ensure leaf nodes are properly created.
 * <p>
 * Time Complexity: O(n * Catalan(n)), where Catalan(n) is the number of unique BSTs
 * Space Complexity: O(n * Catalan(n)) for storing all the tree structures
 */

import java.util.*;

public class _418_c_GeneratingUniqueBSTs {

    public static void main(String[] args) {
        _418_c_GeneratingUniqueBSTs solution = new _418_c_GeneratingUniqueBSTs();

        // Generate all BSTs for n = 3
        List<TreeNode> trees = solution.generateTrees(3);

        System.out.println("Total number of unique BSTs for n = 3: " + trees.size());
        System.out.println("Pre-order traversals of each tree:");

        for (int i = 0; i < trees.size(); i++) {
            System.out.print("Tree " + (i + 1) + ": ");
            solution.printTree(trees.get(i));
            System.out.println();
        }

        // Verify the count matches what we expect from the counting problem
        System.out.println("\nVerification:");
        for (int i = 1; i <= 5; i++) {
            List<TreeNode> generatedTrees = solution.generateTrees(i);
            System.out.println("n = " + i + ": " + generatedTrees.size() + " trees");
        }
    }

    /**
     * Main function to generate all unique BSTs with values 1...n
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        // Generate trees for range [1, n]
        return buildTrees(1, n);
    }

    /**
     * Recursive function to build all possible BSTs with values in range [lo, hi]
     */
    private List<TreeNode> buildTrees(int lo, int hi) {
        List<TreeNode> result = new ArrayList<>();

        // Base case: empty range
        if (lo > hi) {
            // Important: Add null to the list instead of returning empty list
            // This is crucial for properly creating leaf nodes later
            result.add(null);
            return result;
        }

        // Try each value as the root node
        for (int rootVal = lo; rootVal <= hi; rootVal++) {
            // 1. Recursively generate all possible left subtrees
            List<TreeNode> leftTrees = buildTrees(lo, rootVal - 1);

            // 2. Recursively generate all possible right subtrees
            List<TreeNode> rightTrees = buildTrees(rootVal + 1, hi);

            // 3. Combine each left subtree with each right subtree
            for (TreeNode leftSubtree : leftTrees) {
                for (TreeNode rightSubtree : rightTrees) {
                    // Create a new root with the current value
                    TreeNode root = new TreeNode(rootVal);

                    // Attach left and right subtrees
                    root.left = leftSubtree;
                    root.right = rightSubtree;

                    // Add the constructed tree to our result list
                    result.add(root);
                }
            }
        }

        return result;
    }

    /**
     * Helper function to print a tree (in-order traversal)
     */
    private void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }

        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    public static class TreeNode {
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

        @Override
        public String toString() {
            return "Node(" + val + ")";
        }
    }
}