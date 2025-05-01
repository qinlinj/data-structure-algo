package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._418_binary_search_tree_construction;

import java.util.*;

/**
 * Binary Search Tree Theory - Common Algorithm Patterns
 * <p>
 * This class summarizes the general patterns and techniques used in BST algorithms,
 * especially focusing on the recursive approach demonstrated in the article.
 * <p>
 * Key patterns:
 * 1. Root-centric thinking:
 * - Many BST problems can be solved by determining what action to take at the root
 * - Then recursively solving the problem for left and right subtrees
 * <p>
 * 2. Range-based recursion:
 * - Using value ranges [lo, hi] to define subproblems
 * - This approach leverages the BST property effectively
 * <p>
 * 3. Enumeration strategy:
 * - For problems requiring enumeration of structures or possibilities
 * - Ensure complete coverage without duplication
 * <p>
 * 4. Dynamic programming optimization:
 * - Identifying and eliminating overlapping subproblems
 * - Using memoization to avoid redundant calculations
 * <p>
 * 5. Cartesian product combinations:
 * - When generating structures, combining all possible left subtrees with
 * all possible right subtrees (left count × right count)
 */
public class _418_d_BSTAlgorithmPatterns {

    public static void main(String[] args) {
        _418_d_BSTAlgorithmPatterns demo = new _418_d_BSTAlgorithmPatterns();

        // Demonstrate the importance of base cases
        demo.demonstrateBaseCase();

        // Verify count for different values of n
        System.out.println("\nCounting trees with memoization:");
        for (int i = 1; i <= 10; i++) {
            System.out.println("n = " + i + ": " + demo.countTrees(i));
        }
    }

    /**
     * Example 1: Root-centric thinking and range-based recursion
     * Problem: Insert a value into a BST
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Base case: if tree is empty, create a new node
        if (root == null) {
            return new TreeNode(val);
        }

        // Determine action at root: decide whether to go left or right
        if (val < root.val) {
            // Recursively insert in left subtree
            root.left = insertIntoBST(root.left, val);
        } else {
            // Recursively insert in right subtree
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    /**
     * Example 2: Memoization for overlapping subproblems
     * Simplified version of the BST counting problem
     */
    public int countTrees(int n) {
        // Memoization table
        Integer[][] memo = new Integer[n + 1][n + 1];
        return countTreesHelper(1, n, memo);
    }

    private int countTreesHelper(int start, int end, Integer[][] memo) {
        if (start > end) {
            return 1;
        }

        // Check if we've already solved this subproblem
        if (memo[start][end] != null) {
            return memo[start][end];
        }

        int count = 0;
        for (int i = start; i <= end; i++) {
            int leftCount = countTreesHelper(start, i - 1, memo);
            int rightCount = countTreesHelper(i + 1, end, memo);
            count += leftCount * rightCount;
        }

        // Store result in memo table
        memo[start][end] = count;
        return count;
    }

    /**
     * Example 3: Cartesian product combinations
     * Simplified version of generating all BSTs
     */
    public List<TreeNode> generateTreesInRange(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        if (start > end) {
            result.add(null); // Important for leaf node creation
            return result;
        }

        for (int i = start; i <= end; i++) {
            // Generate all possible left and right subtrees
            List<TreeNode> leftTrees = generateTreesInRange(start, i - 1);
            List<TreeNode> rightTrees = generateTreesInRange(i + 1, end);

            // Create all combinations (Cartesian product)
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        return result;
    }

    /**
     * Example 4: Demonstrating base case importance
     * Empty ranges returning 1 vs. 0 is crucial for correct counting
     */
    public void demonstrateBaseCase() {
        int[][] memo = new int[5][5];

        // Incorrect base case (returning 0 for empty range)
        System.out.println("With incorrect base case (returning 0):");
        System.out.println("Count for n=3: " + countWithIncorrectBase(1, 3, memo));

        // Reset memo
        memo = new int[5][5];

        // Correct base case (returning 1 for empty range)
        System.out.println("With correct base case (returning 1):");
        System.out.println("Count for n=3: " + countWithCorrectBase(1, 3, memo));
    }

    private int countWithIncorrectBase(int lo, int hi, int[][] memo) {
        // Incorrect base case
        if (lo > hi) return 0; // This is wrong!

        if (memo[lo][hi] != 0) return memo[lo][hi];

        int result = 0;
        for (int i = lo; i <= hi; i++) {
            int left = countWithIncorrectBase(lo, i - 1, memo);
            int right = countWithIncorrectBase(i + 1, hi, memo);
            // This will often be 0 because 0 * anything = 0
            result += (left == 0 ? 1 : left) * (right == 0 ? 1 : right);
        }

        memo[lo][hi] = result;
        return result;
    }

    private int countWithCorrectBase(int lo, int hi, int[][] memo) {
        // Correct base case
        if (lo > hi) return 1; // This is correct!

        if (memo[lo][hi] != 0) return memo[lo][hi];

        int result = 0;
        for (int i = lo; i <= hi; i++) {
            int left = countWithCorrectBase(lo, i - 1, memo);
            int right = countWithCorrectBase(i + 1, hi, memo);
            // Left and right will never be 0, so multiplication works correctly
            result += left * right;
        }

        memo[lo][hi] = result;
        return result;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
