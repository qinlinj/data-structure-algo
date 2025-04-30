package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._411_binary_tree_guiding_principle;

/**
 * BINARY TREE ALGORITHMS - CORE CONCEPTS
 * ======================================
 * <p>
 * This class introduces the fundamental concepts of binary tree algorithms.
 * Binary trees are one of the most important data structures in computer science,
 * and understanding how to work with them is crucial for mastering algorithms.
 * <p>
 * Key points:
 * <p>
 * 1. Two Primary Thinking Modes for Binary Tree Problems:
 * - Traversal mode: Can we solve the problem by traversing the tree once?
 * If yes, use a traverse function with external variables.
 * - Problem decomposition mode: Can we define a recursive function that uses
 * solutions to subproblems (subtrees) to derive the solution to the original problem?
 * If yes, define this recursive function and fully utilize its return value.
 * <p>
 * 2. For any approach, always ask yourself:
 * - What does a single node need to do?
 * - When should it do it (pre-order, in-order, or post-order position)?
 * - Let recursion handle the rest by applying the same operation on all nodes.
 * <p>
 * 3. Binary trees demonstrate the importance of recursive thinking, which applies
 * to many other algorithms including dynamic programming, backtracking, and divide-and-conquer.
 * <p>
 * Example: Quick sort is essentially a pre-order traversal of a binary tree,
 * while merge sort is a post-order traversal.
 */

public class _411_a_BinaryTreeIntroduction {
    public static void main(String[] args) {
        System.out.println("Binary Tree Introduction - showcasing that sorting algorithms " +
                "like quicksort and mergesort can be viewed as binary tree traversals");
    }

    /**
     * Example showing how quicksort can be viewed as a pre-order traversal of a binary tree
     */
    public void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // Pre-order position: partition the array first
        int p = partition(nums, lo, hi);

        // Then recursively sort the left and right partitions
        quickSort(nums, lo, p - 1);
        quickSort(nums, p + 1, hi);
    }

    /**
     * Example showing how mergesort can be viewed as a post-order traversal of a binary tree
     */
    public void mergeSort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        // Recursively sort the left and right parts
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);

        // Post-order position: merge the sorted parts
        merge(nums, lo, mid, hi);
    }

    /**
     * Partitioning logic for quicksort
     */
    private int partition(int[] nums, int lo, int hi) {
        // Implementation details omitted for brevity
        // This function would arrange elements and return the pivot index
        return lo; // Placeholder return
    }

    /**
     * Merging logic for mergesort
     */
    private void merge(int[] nums, int lo, int mid, int hi) {
        // Implementation details omitted for brevity
        // This function would merge two sorted subarrays
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
