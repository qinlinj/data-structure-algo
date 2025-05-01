package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._418_binary_search_tree_construction;

/**
 * Binary Search Tree - Complete Summary
 * <p>
 * This class provides a comprehensive summary of all key concepts related to Binary Search Trees
 * as covered in the original text, consolidating the knowledge points from previous classes.
 * <p>
 * Key summary points:
 * <p>
 * 1. BST Fundamentals:
 * - A Binary Search Tree is a binary tree where for each node:
 * * All nodes in the left subtree have values less than the node
 * * All nodes in the right subtree have values greater than the node
 * - This property enables efficient searching, insertion, and deletion operations
 * <p>
 * 2. BST Counting Problem (LeetCode 96):
 * - Counting unique BST structures with n nodes uses a recursive approach
 * - For each possible root value i from 1 to n:
 * * Count all possible left subtree structures with values [1...i-1]
 * * Count all possible right subtree structures with values [i+1...n]
 * * Multiply these counts (Cartesian product principle)
 * - Uses dynamic programming to avoid recalculating overlapping subproblems
 * - Critical base case: empty range [lo > hi] returns 1, not 0
 * <p>
 * 3. BST Generation Problem (LeetCode 95):
 * - Extension of counting problem to generate all possible structures
 * - Uses the same recursive divide-and-conquer approach
 * - Creates actual tree structures by combining all possible left and right subtrees
 * <p>
 * 4. Algorithm Design Patterns for BSTs:
 * - Root-centric thinking: determine action at root, then recurse
 * - Range-based recursion: define subproblems by value ranges
 * - Enumeration strategy: ensure complete coverage without duplication
 * - Dynamic programming: identify and eliminate overlapping subproblems
 * <p>
 * 5. Practice Strategies:
 * - Begin with basic operations, then progress to complex algorithms
 * - Master traversal techniques, especially inorder traversal
 * - Understand the mathematical relationship to Catalan numbers
 * <p>
 * 6. Implementation Techniques:
 * - Recursive solutions with proper base cases
 * - Memoization to optimize recursive solutions
 * - Bottom-up dynamic programming as an alternative approach
 */

import java.util.*;

public class _418_g_BSTCompleteSummary {

    public static void main(String[] args) {
        _418_g_BSTCompleteSummary summary = new _418_g_BSTCompleteSummary();
        summary.demonstrateAndVerify();

        // Key takeaways
        System.out.println("\nKey takeaways from BST Construction Theory:");
        System.out.println("1. BST problems often benefit from recursive divide-and-conquer approaches");
        System.out.println("2. The base case for empty ranges should return 1, not 0, for correct counting");
        System.out.println("3. Dynamic programming with memoization is essential for efficiency");
        System.out.println("4. The Cartesian product principle (left count × right count) is fundamental");
        System.out.println("5. Tree construction follows the same pattern as counting, but builds actual structures");
        System.out.println("6. The number of unique BSTs is given by the Catalan number sequence");
    }

    /**
     * Section 1: BST Validation - Fundamental property check
     */
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        return validate(node.left, min, node.val) &&
                validate(node.right, node.val, max);
    }

    /**
     * Section 2: Count unique BST structures (LeetCode 96)
     * Using recursion with memoization
     */
    public int numTrees(int n) {
        int[][] memo = new int[n + 1][n + 1];
        return countBSTs(1, n, memo);
    }

    private int countBSTs(int lo, int hi, int[][] memo) {
        // Important base case: empty range has 1 configuration (null)
        if (lo > hi) return 1;

        if (memo[lo][hi] != 0) return memo[lo][hi];

        int count = 0;
        for (int root = lo; root <= hi; root++) {
            int leftCount = countBSTs(lo, root - 1, memo);
            int rightCount = countBSTs(root + 1, hi, memo);
            count += leftCount * rightCount;
        }

        memo[lo][hi] = count;
        return count;
    }

    /**
     * Section 3: Generate all unique BSTs (LeetCode 95)
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int lo, int hi) {
        List<TreeNode> result = new ArrayList<>();

        // Important: add null for empty range to facilitate leaf node creation
        if (lo > hi) {
            result.add(null);
            return result;
        }

        // Try each value as root
        for (int root = lo; root <= hi; root++) {
            // Generate all possible left and right subtrees
            List<TreeNode> leftTrees = buildTrees(lo, root - 1);
            List<TreeNode> rightTrees = buildTrees(root + 1, hi);

            // Create all combinations (Cartesian product)
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode rootNode = new TreeNode(root);
                    rootNode.left = left;
                    rootNode.right = right;
                    result.add(rootNode);
                }
            }
        }

        return result;
    }

    /**
     * Section 4: Alternative bottom-up DP approach for counting BSTs
     * This avoids recursion entirely
     */
    public int numTreesBottomUp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // Empty tree has 1 configuration

        for (int i = 1; i <= n; i++) {
            for (int root = 1; root <= i; root++) {
                dp[i] += dp[root - 1] * dp[i - root];
            }
        }

        return dp[n];
    }

    /**
     * Section 5: Mathematical approach using Catalan numbers
     */
    public int numTreesWithCatalanFormula(int n) {
        long catalan = 1;

        for (int i = 1; i <= n; i++) {
            catalan = catalan * 2 * (2 * i - 1) / (i + 1);
        }

        return (int) catalan;
    }

    /**
     * Section 6: Utility methods for BST operations
     */

    // Insert a value into BST
    public TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }

        return root;
    }

    // Inorder traversal (produces sorted sequence for BST)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;

        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }

    // Build balanced BST from sorted array
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) return null;

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);

        return root;
    }

    /**
     * Section 7: Demonstration and verification
     */
    public void demonstrateAndVerify() {
        System.out.println("BST Summary and Verification:");

        // 1. Counting unique BSTs for small values
        System.out.println("\n1. Count of unique BSTs for n = 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            int count = numTrees(i);
            System.out.println("  n = " + i + ": " + count + " unique BSTs");
        }

        // 2. Verify different calculation methods give same results
        System.out.println("\n2. Verification of different calculation methods for n = 10:");
        int recursive = numTrees(10);
        int bottomUp = numTreesBottomUp(10);
        int formula = numTreesWithCatalanFormula(10);

        System.out.println("  Recursive with memoization: " + recursive);
        System.out.println("  Bottom-up DP: " + bottomUp);
        System.out.println("  Catalan formula: " + formula);
        System.out.println("  All methods match: " + (recursive == bottomUp && bottomUp == formula));

        // 3. Generate and count trees for n = 3
        System.out.println("\n3. Generation of all unique BSTs for n = 3:");
        List<TreeNode> trees = generateTrees(3);
        System.out.println("  Generated " + trees.size() + " unique BSTs");

        // 4. Basic BST operations
        System.out.println("\n4. Basic BST operations demonstration:");
        TreeNode bst = null;
        for (int val : new int[]{5, 3, 7, 2, 4, 6, 8}) {
            bst = insert(bst, val);
        }

        List<Integer> inorder = inorderTraversal(bst);
        System.out.println("  Inorder traversal of BST: " + inorder);
        System.out.println("  Is valid BST: " + isValidBST(bst));

        // 5. BST from sorted array
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedBST = sortedArrayToBST(sortedArray);
        System.out.println("\n5. Created balanced BST from sorted array");
        System.out.println("  Is valid BST: " + isValidBST(balancedBST));
        System.out.println("  Inorder traversal: " + inorderTraversal(balancedBST));
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
