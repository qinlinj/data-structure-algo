package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._436_binary_search_tree_classic_practice_I; /**
 * Problem 653: Two Sum IV - Input is a BST (Easy)
 * <p>
 * Problem Description:
 * Given the root of a Binary Search Tree and a target number k, return true if there exist
 * two elements in the BST such that their sum is equal to the given target.
 * <p>
 * Key Concepts:
 * - BST to Sorted Array: Convert BST to sorted array using inorder traversal
 * - Two-Pointer Technique: Use two pointers on the sorted array to find target sum
 * - Alternative HashSet Approach: Use a set to track seen values
 * - BST Properties: Leverage the sorted nature of BST for efficient solutions
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n) for storing the array or HashSet
 */

import java.util.*;

class _436_f_TwoSumBST {
    // Main method for testing
    public static void main(String[] args) {
        _436_f_TwoSumBST solution = new _436_f_TwoSumBST();

        // Example 1: [5,3,6,2,4,null,7], k = 9
        TreeNode root1 = solution.new TreeNode(5);
        root1.left = solution.new TreeNode(3);
        root1.right = solution.new TreeNode(6);
        root1.left.left = solution.new TreeNode(2);
        root1.left.right = solution.new TreeNode(4);
        root1.right.right = solution.new TreeNode(7);

        int k1 = 9;

        System.out.println("Example 1 - Tree inorder traversal:");
        solution.printInorder(root1);
        System.out.println("\nTarget sum: " + k1);

        boolean result1 = solution.findTarget(root1, k1);
        System.out.println("Result (Two-Pointer approach): " + result1);
        // Expected output: true (5+4=9)

        boolean result1Alt = solution.findTargetUsingHashSet(root1, k1);
        System.out.println("Result (HashSet approach): " + result1Alt);

        // Example 2: [5,3,6,2,4,null,7], k = 28
        int k2 = 28;

        System.out.println("\nExample 2 - Same tree with different target:");
        System.out.println("Target sum: " + k2);

        boolean result2 = solution.findTarget(root1, k2);
        System.out.println("Result (Two-Pointer approach): " + result2);
        // Expected output: false (no pair sums to 28)

        boolean result2Alt = solution.findTargetUsingHashSet(root1, k2);
        System.out.println("Result (HashSet approach): " + result2Alt);

        // Example 3: [2,1,3], k = 4
        TreeNode root3 = solution.new TreeNode(2);
        root3.left = solution.new TreeNode(1);
        root3.right = solution.new TreeNode(3);

        int k3 = 4;

        System.out.println("\nExample 3 - Tree inorder traversal:");
        solution.printInorder(root3);
        System.out.println("\nTarget sum: " + k3);

        boolean result3 = solution.findTarget(root3, k3);
        System.out.println("Result (Two-Pointer approach): " + result3);
        // Expected output: true (1+3=4)
    }

    /**
     * Main function to find if there are two elements in the BST with sum equal to target
     * Uses the approach of converting BST to sorted array and using two pointers
     *
     * @param root The root of the binary search tree
     * @param k The target sum
     * @return True if there exist two elements with sum equal to k, false otherwise
     */
    public boolean findTarget(TreeNode root, int k) {
        // Convert BST to sorted array using inorder traversal
        List<Integer> values = new ArrayList<>();
        inorderTraversal(root, values);

        // Use two-pointer technique on the sorted array
        int left = 0;
        int right = values.size() - 1;

        while (left < right) {
            int sum = values.get(left) + values.get(right);

            if (sum == k) {
                return true; // Found the pair
            } else if (sum < k) {
                left++; // Need a larger sum, move left pointer right
            } else {
                right--; // Need a smaller sum, move right pointer left
            }
        }

        return false; // No such pair found
    }

    /**
     * Helper method for inorder traversal to collect values in sorted order
     */
    private void inorderTraversal(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, values);
        values.add(root.val);
        inorderTraversal(root.right, values);
    }

    /**
     * Alternative implementation using HashSet
     * This approach doesn't require the BST property and works for any binary tree
     */
    public boolean findTargetUsingHashSet(TreeNode root, int k) {
        Set<Integer> seen = new HashSet<>();
        return dfs(root, k, seen);
    }

    /**
     * Helper method for DFS traversal with HashSet
     */
    private boolean dfs(TreeNode root, int k, Set<Integer> seen) {
        if (root == null) {
            return false;
        }

        // Check if we've seen the complement
        if (seen.contains(k - root.val)) {
            return true;
        }

        // Add current value to set
        seen.add(root.val);

        // Check left and right subtrees
        return dfs(root.left, k, seen) || dfs(root.right, k, seen);
    }

    /**
     * Helper method to print the inorder traversal of the tree
     */
    private void printInorder(TreeNode root) {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
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