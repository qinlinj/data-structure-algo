package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._437_binary_search_tree_classic_practice_II;

/**
 * Problem 108: Convert Sorted Array to Binary Search Tree (Easy)
 * <p>
 * Problem Description:
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree (BST).
 * A height-balanced binary tree is a binary tree in which the depth of the two
 * subtrees of every node never differs by more than one.
 * <p>
 * Key Concepts:
 * - Height-Balanced BST: A binary tree where subtree heights never differ by more than one
 * - Recursive Construction: Build the tree by selecting the middle element as root
 * - Divide and Conquer: Split the array into left and right halves for balanced structure
 * - Inorder Traversal Property: The inorder traversal of a BST produces the elements in sorted order
 * <p>
 * Time Complexity: O(n) where n is the number of elements in the array
 * Space Complexity: O(log n) for the recursion stack in a balanced tree
 */

class _437_a_SortedArrayToBST {
    // Main method for testing
    public static void main(String[] args) {
        _437_a_SortedArrayToBST solution = new _437_a_SortedArrayToBST();

        // Example 1: [-10,-3,0,5,9]
        int[] nums1 = {-10, -3, 0, 5, 9};
        TreeNode root1 = solution.sortedArrayToBST(nums1);

        System.out.println("Example 1:");
        System.out.println("Inorder traversal (should be sorted):");
        solution.printInorder(root1);
        System.out.println();

        System.out.println("Tree structure:");
        solution.printTree(root1, "", false);

        System.out.println("Is balanced: " + solution.isBalanced(root1));

        // Example 2: [1,3]
        int[] nums2 = {1, 3};
        TreeNode root2 = solution.sortedArrayToBST(nums2);

        System.out.println("\nExample 2:");
        System.out.println("Inorder traversal (should be sorted):");
        solution.printInorder(root2);
        System.out.println();

        System.out.println("Tree structure:");
        solution.printTree(root2, "", false);

        System.out.println("Is balanced: " + solution.isBalanced(root2));
    }

    /**
     * Main function to convert a sorted array to balanced BST
     *
     * @param nums A sorted array in ascending order
     * @return Root of the balanced BST
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return buildBST(nums, 0, nums.length - 1);
    }

    /**
     * Helper method to recursively build a balanced BST from a sorted array
     *
     * @param nums  The sorted array
     * @param left  Start index of the current subarray
     * @param right End index of the current subarray
     * @return Root of the BST constructed from nums[left...right]
     */
    private TreeNode buildBST(int[] nums, int left, int right) {
        // Base case: invalid range
        if (left > right) {
            return null;
        }

        // Find the middle element to be the root
        int mid = left + (right - left) / 2;

        // Create the root node with the middle element
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build left subtree using elements before the middle
        root.left = buildBST(nums, left, mid - 1);

        // Recursively build right subtree using elements after the middle
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }

    /**
     * Helper method to print the inorder traversal of the tree
     * Inorder traversal of a BST should produce sorted values
     */
    private void printInorder(TreeNode root) {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    /**
     * Helper method to check if a tree is height-balanced
     */
    private boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * Helper method to check height and balance of a tree
     * Returns -1 if the tree is not balanced, otherwise returns the height
     */
    private int checkHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is not balanced
        }

        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is not balanced
        }

        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current node is not balanced
        }

        // Return height of current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Helper method to print tree structure
     */
    private void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "null");
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);

        printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
        printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
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