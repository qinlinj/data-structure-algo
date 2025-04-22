package org.qinlinj.algoframework._200_core_framework._220_divide_and_conquer_algo_framework._221_divide_and_conquer_thinking;


/**
 * Divide and Conquer Algorithm
 * <p>
 * This class summarizes the core principles and techniques of divide and conquer algorithms.
 * <p>
 * Key Points:
 * <p>
 * 1. Difference between "Divide and Conquer Thinking" and "Divide and Conquer Algorithm":
 * - Divide and Conquer Thinking is a broad concept often referred to as "problem decomposition approach"
 * - It involves breaking down a problem into smaller sub-problems, solving each sub-problem,
 * and then combining their solutions to solve the original problem
 * - This thinking is widely present in recursive algorithms
 * <p>
 * 2. Examples of Divide and Conquer Thinking:
 * - Fibonacci sequence recursive solution
 * - Binary tree algorithms (e.g., counting nodes)
 * - Dynamic programming algorithms
 * <p>
 * 3. Classification of Recursive Algorithms:
 * - There are two main approaches in recursive algorithms:
 * a) Traversal approach (typical in backtracking algorithms)
 * b) Problem decomposition approach (divide and conquer thinking)
 * - Apart from backtracking algorithms, most recursive algorithms
 * fall under the divide and conquer thinking
 * <p>
 * 4. Formal Divide and Conquer Algorithm:
 * While many algorithms use the divide and conquer thinking, formal "Divide and Conquer Algorithm"
 * refers to a specific class of algorithms with these characteristics:
 * - The problem is divided into completely independent sub-problems
 * - Sub-problems don't overlap (unlike dynamic programming)
 * - Examples include: merge sort, quick sort, binary search
 */
public class DivideAndConquer {
    /**
     * Example 1: Fibonacci sequence using recursive approach
     * This demonstrates divide and conquer thinking by breaking the problem
     * into smaller sub-problems (fib(n-1) and fib(n-2))
     */
    public static int fib(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return n;
        }
        // Divide the problem and combine the results
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * Example 2: Binary Tree Node Count
     * This demonstrates divide and conquer thinking in tree traversal
     */
    public static int count(TreeNode root) {
        // Base case
        if (root == null) {
            return 0;
        }
        // Divide: Count nodes in left and right subtrees
        int leftCount = count(root.left);
        int rightCount = count(root.right);

        // Combine: Total count is left count + right count + 1 (current node)
        return leftCount + rightCount + 1;
    }

    /**
     * Example 3: Merge Sort - A formal Divide and Conquer Algorithm
     * This is a classic example of a true divide and conquer algorithm
     * where the problem is divided into independent sub-problems
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Divide the array into two halves
            int mid = left + (right - left) / 2;

            // Recursively sort both halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
    }

    /**
     * TreeNode class for binary tree examples
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
