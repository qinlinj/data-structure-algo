package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._421_traversal_thinking_practice_I;

/**
 * Problem 1457: Pseudo-Palindromic Paths in a Binary Tree
 * <p>
 * Description:
 * Given a binary tree where node values are digits from 1 to 9, a path is pseudo-palindromic
 * if at least one permutation of the node values in the path is a palindrome.
 * Return the number of pseudo-palindromic paths from root to leaf.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - A path can form a palindrome if at most one digit appears an odd number of times
 * - Two implementation approaches are shown:
 * 1. Array-based counting: Use an array to count occurrences of each digit
 * 2. Bit manipulation: Use XOR operations for more efficient counting
 * <p>
 * - Bit manipulation approach leverages:
 * - XOR (^) to toggle bit states when a number is seen
 * - n & (n-1) technique to check if at most one bit is set to 1
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _421_f_PseudoPalindromicPaths {
    // Counter array for digits 1-9
    private int[] count = new int[10];
    private int res = 0;

    // Solution 1: Using an array to count occurrences
    public int pseudoPalindromicPaths(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Preorder traversal position - increment count for current digit
        count[root.val]++;

        // Check if current node is a leaf node
        if (root.left == null && root.right == null) {
            // Count how many digits appear an odd number of times
            int oddCount = 0;
            for (int n : count) {
                if (n % 2 == 1) {
                    oddCount++;
                }
            }

            // If at most one digit appears an odd number of times, path is pseudo-palindromic
            if (oddCount <= 1) {
                res++;
            }
        }

        // Recursively traverse left and right subtrees
        traverse(root.left);
        traverse(root.right);

        // Postorder traversal position - decrement count for current digit
        count[root.val]--;
    }

    // Solution 2: Using bit manipulation for more efficient counting
    private static class BitManipulationSolution {
        // Using bit manipulation to track odd/even occurrences
        private int bitMask = 0;
        private int res = 0;

        public int pseudoPalindromicPaths(TreeNode root) {
            traverseBitwise(root);
            return res;
        }

        private void traverseBitwise(TreeNode root) {
            if (root == null) {
                return;
            }

            // XOR operation flips the bit for the current value
            // If a digit appears even times, its bit will be 0
            // If a digit appears odd times, its bit will be 1
            bitMask ^= (1 << root.val);

            // Check if current node is a leaf node
            if (root.left == null && root.right == null) {
                // Check if at most one bit is set to 1 using Brian Kernighan's algorithm
                // This means at most one digit appears an odd number of times
                if (bitMask == 0 || (bitMask & (bitMask - 1)) == 0) {
                    res++;
                }
            }

            // Recursively traverse left and right subtrees
            traverseBitwise(root.left);
            traverseBitwise(root.right);

            // Restore the bitmask by XORing again with the same value
            bitMask ^= (1 << root.val);
        }
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
