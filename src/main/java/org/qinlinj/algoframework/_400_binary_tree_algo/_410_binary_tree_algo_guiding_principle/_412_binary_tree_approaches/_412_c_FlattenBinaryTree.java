package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._412_binary_tree_approaches;

/**
 * FLATTEN BINARY TREE TO LINKED LIST
 * =================================
 * <p>
 * This class demonstrates how to solve the "Flatten Binary Tree to Linked List" problem (LeetCode 114):
 * Given the root of a binary tree, flatten it into a linked list in-place.
 * <p>
 * Problem Description:
 * Given the root of a binary tree, flatten it to a linked list in-place.
 * The "linked list" should use the same TreeNode class where:
 * - The right child pointer points to the next node in the list
 * - The left child pointer is always null
 * - The linked list should be in the same order as a pre-order traversal of the binary tree
 * <p>
 * Key Insights:
 * <p>
 * 1. Traversal Approach Limitations:
 * - While we could use pre-order traversal to collect nodes in the desired order,
 * it's hard to modify the tree in-place using this approach
 * - Creating a new linked list doesn't satisfy the "in-place" requirement
 * <p>
 * 2. Problem Decomposition Approach:
 * - Define the function's purpose: "flatten the tree rooted at this node into a linked list"
 * - Recursively flatten the left and right subtrees
 * - Then rearrange pointers to create the desired linked list structure:
 * 1. Save pointers to flattened left and right subtrees
 * 2. Make the right pointer point to the flattened left subtree
 * 3. Find the end of the new right subtree
 * 4. Connect the end to the original flattened right subtree
 * 5. Set left pointer to null
 * - This approach works in-place and maintains the pre-order traversal order
 * <p>
 * This problem demonstrates how the "problem decomposition" approach can be more
 * suitable for in-place transformations of a binary tree.
 */

public class _412_c_FlattenBinaryTree {
    /**
     * Utility method to print the flattened tree as a linked list
     */
    public static void printFlattenedTree(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.val);

            // Verify that left pointer is null
            if (current.left != null) {
                System.out.print(" (Error: left pointer not null!)");
            }

            if (current.right != null) {
                System.out.print(" -> ");
            } else {
                System.out.print(" -> null");
            }

            current = current.right;
        }
        System.out.println();
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        _412_c_FlattenBinaryTree solution = new _412_c_FlattenBinaryTree();

        // Create a sample tree: [1,2,5,3,4,null,6]
        //       1
        //      / \
        //     2   5
        //    / \   \
        //   3   4   6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        // Flatten the tree
        solution.flatten(root);

        // Print the flattened tree
        System.out.println("Flattened tree as a linked list (pre-order traversal):");
        printFlattenedTree(root);

        // Expected output:
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
    }

    /**
     * Approach using the "problem decomposition" thinking pattern
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (recursion stack)
     */
    public void flatten(TreeNode root) {
        // Base case
        if (root == null) {
            return;
        }

        // Recursively flatten left and right subtrees
        flatten(root.left);
        flatten(root.right);

        // Post-order position: rearrange pointers

        // 1. Save flattened left and right subtrees
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2. Make right pointer point to the flattened left subtree
        root.left = null;
        root.right = left;

        // 3. Find the end of the new right subtree
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        // 4. Connect the end to the original flattened right subtree
        current.right = right;
    }

    /**
     * A non-working traversal approach for comparison
     * This demonstrates why traversal is challenging for this problem
     */
    public void flattenTraversalAttempt(TreeNode root) {
        // This won't work correctly for in-place modification
        // We would need external state to keep track of the last node
        TreeNode dummy = new TreeNode(-1);
        TreeNode current = dummy;

        preorderTraverse(root, current);

        // We can't modify the original tree this way
    }

    private void preorderTraverse(TreeNode root, TreeNode current) {
        if (root == null) {
            return;
        }

        // Creating a new node instead of modifying in-place
        current.right = new TreeNode(root.val);
        current = current.right;

        preorderTraverse(root.left, current);
        preorderTraverse(root.right, current);
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
