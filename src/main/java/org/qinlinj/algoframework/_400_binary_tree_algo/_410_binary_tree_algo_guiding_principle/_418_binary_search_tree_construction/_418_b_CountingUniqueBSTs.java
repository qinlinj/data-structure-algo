package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._418_binary_search_tree_construction;

/**
 * Binary Search Tree Theory - Counting Unique BSTs
 * <p>
 * This class implements the solution to LeetCode 96: Unique Binary Search Trees
 * https://leetcode.com/problems/unique-binary-search-trees/
 * <p>
 * Problem: Given an integer n, find how many structurally unique BSTs can be constructed
 * with nodes valued from 1 to n.
 * <p>
 * Key insights:
 * 1. This is an enumeration/counting problem requiring careful consideration to count each
 * possible BST exactly once.
 * <p>
 * 2. The core recursive approach:
 * - For each number i from 1 to n, consider it as the root
 * - Numbers less than i will form the left subtree
 * - Numbers greater than i will form the right subtree
 * - The total count is the sum of (left subtree count × right subtree count) for all possible roots
 * <p>
 * 3. This exhibits overlapping subproblems, making it a good candidate for dynamic programming
 * with memoization.
 * <p>
 * 4. The base case: when calculating trees for an empty range [lo > hi], return 1 (representing null)
 * - This is crucial since null is a valid subtree and counts as one possibility
 */
public class _418_b_CountingUniqueBSTs {

    public static void main(String[] args) {
        _418_b_CountingUniqueBSTs solution = new _418_b_CountingUniqueBSTs();

        // Test cases
        System.out.println("Number of unique BSTs with n = 1: " + solution.numTrees(1)); // Should be 1
        System.out.println("Number of unique BSTs with n = 2: " + solution.numTrees(2)); // Should be 2
        System.out.println("Number of unique BSTs with n = 3: " + solution.numTrees(3)); // Should be 5
        System.out.println("Number of unique BSTs with n = 4: " + solution.numTrees(4)); // Should be 14

        // With larger numbers, we see the exponential growth
        System.out.println("Number of unique BSTs with n = 10: " + solution.numTrees(10)); // Should be 16796

        // Note: This problem also has a mathematical solution using Catalan numbers
        // The nth Catalan number precisely gives the number of unique BSTs with n nodes
    }

    /**
     * Solution 1: Recursive approach with memoization
     * Time Complexity: O(n²)
     * Space Complexity: O(n²) for the memoization table
     */
    public int numTrees(int n) {
        // Initialize memoization table
        int[][] memo = new int[n + 1][n + 1];
        return countBSTs(1, n, memo);
    }

    /**
     * Counts the number of unique BSTs that can be formed using numbers in range [lo, hi]
     */
    private int countBSTs(int lo, int hi, int[][] memo) {
        // Base case: empty range represents null (1 possibility)
        if (lo > hi) {
            return 1;
        }

        // Check if we've already calculated this result
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int totalCount = 0;

        // Try each number as the root node
        for (int root = lo; root <= hi; root++) {
            // Count possible left subtrees (values less than root)
            int leftCount = countBSTs(lo, root - 1, memo);

            // Count possible right subtrees (values greater than root)
            int rightCount = countBSTs(root + 1, hi, memo);

            // Total combinations with this root = left subtree count × right subtree count
            totalCount += leftCount * rightCount;
        }

        // Save result in memo table
        memo[lo][hi] = totalCount;
        return totalCount;
    }

    /**
     * Alternative implementation matching the original code from the article
     */
    public int numTreesOriginal(int n) {
        int[][] memo = new int[n + 1][n + 1];
        return count(1, n, memo);
    }

    private int count(int lo, int hi, int[][] memo) {
        // Base case
        if (lo > hi) return 1;

        // Check memo
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = count(lo, mid - 1, memo);
            int right = count(mid + 1, hi, memo);
            res += left * right;
        }

        // Save to memo
        memo[lo][hi] = res;
        return res;
    }
}
