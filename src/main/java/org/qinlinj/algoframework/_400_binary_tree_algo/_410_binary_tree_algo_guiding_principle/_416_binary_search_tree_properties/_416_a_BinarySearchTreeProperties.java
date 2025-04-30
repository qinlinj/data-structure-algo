package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._416_binary_search_tree_properties;

/**
 * BINARY SEARCH TREE PROPERTIES
 * ============================
 * <p>
 * This class introduces the fundamental properties of Binary Search Trees (BST)
 * and demonstrates how these properties can be leveraged to solve problems efficiently.
 * <p>
 * Key BST Properties:
 * <p>
 * 1. Node Ordering:
 * - For every node, all values in its left subtree are less than the node's value
 * - For every node, all values in its right subtree are greater than the node's value
 * <p>
 * 2. Recursive Structure:
 * - Every subtree in a BST is also a valid BST
 * <p>
 * 3. Inorder Traversal:
 * - The inorder traversal of a BST produces values in ascending order
 * - This is a crucial property that enables many efficient operations
 * <p>
 * Applications of BST Properties:
 * - Efficient searching, insertion, and deletion (O(log n) in balanced trees)
 * - Sorted data representation without explicit sorting
 * - Range queries and ordered iteration
 * <p>
 * This class demonstrates these properties and shows how to utilize them.
 */

import java.util.*;

public class _416_a_BinarySearchTreeProperties {

    /**
     * Creates and returns a sample BST for demonstration
     */
    public static TreeNode createSampleBST() {
        // Create a BST with structure:
        //       8
        //      / \
        //     3   10
        //    / \    \
        //   1   6    14
        //      / \   /
        //     4   7 13
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        return root;
    }

    /**
     * Prints a representation of the BST for visualization
     */
    public static void printBST(TreeNode root) {
        printBST(root, 0);
    }

    private static void printBST(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        // Print right subtree first (so it appears at the top)
        printBST(node.right, level + 1);

        // Print current node with indentation
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.val);

        // Print left subtree
        printBST(node.left, level + 1);
    }

    public static void main(String[] args) {
        _416_a_BinarySearchTreeProperties bst = new _416_a_BinarySearchTreeProperties();

        // Create a sample BST
        TreeNode root = createSampleBST();

        System.out.println("Binary Search Tree Properties Demo");
        System.out.println("=================================");

        // Print the BST structure
        System.out.println("\nBST Structure:");
        printBST(root);

        // Validate the BST
        System.out.println("\nIs this a valid BST? " + bst.isValidBST(root));

        // Demonstrate inorder traversal producing sorted output
        System.out.println("\nInorder Traversal (Ascending Order): " + bst.inorderTraversal(root));

        // Demonstrate reverse inorder traversal
        System.out.println("\nReverse Inorder Traversal (Descending Order): " + bst.reverseInorderTraversal(root));

        // Demonstrate search operation
        int target = 6;
        TreeNode searchResult = bst.search(root, target);
        System.out.println("\nSearching for value " + target + ": " +
                (searchResult != null ? "Found" : "Not found"));

        // Demonstrate insert operation
        int newValue = 5;
        System.out.println("\nInserting value " + newValue);
        root = bst.insert(root, newValue);

        // Verify the tree is still a valid BST after insertion
        System.out.println("Is still a valid BST after insertion? " + bst.isValidBST(root));

        // Show the updated inorder traversal
        System.out.println("Updated Inorder Traversal: " + bst.inorderTraversal(root));
    }

    /**
     * Verifies if a given binary tree is a valid BST
     * - Uses the BST property that all values in left subtree are less than node value
     * - All values in right subtree are greater than node value
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        // Empty tree is a valid BST
        if (root == null) {
            return true;
        }

        // Check if current node's value violates BST property
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }

        // Recursively verify left and right subtrees
        // Left subtree values must be less than root.val
        // Right subtree values must be greater than root.val
        return isValidBST(root.left, min, root.val) &&
                isValidBST(root.right, root.val, max);
    }

    /**
     * Performs an inorder traversal of the BST, which produces values in ascending order
     * Demonstrates the key property that inorder traversal of a BST produces sorted output
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        // First visit left subtree
        inorderTraversal(root.left, result);

        // Then visit the current node (inorder position)
        result.add(root.val);

        // Finally visit right subtree
        inorderTraversal(root.right, result);
    }

    /**
     * Performs a reverse inorder traversal of the BST, producing values in descending order
     * This is the same as a regular inorder traversal but visiting right children before left
     */
    public List<Integer> reverseInorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        reverseInorderTraversal(root, result);
        return result;
    }

    private void reverseInorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        // First visit right subtree
        reverseInorderTraversal(root.right, result);

        // Then visit the current node (inorder position)
        result.add(root.val);

        // Finally visit left subtree
        reverseInorderTraversal(root.left, result);
    }

    /**
     * Searches for a value in a BST using the BST property for efficiency
     * Time complexity: O(log n) for balanced BSTs, O(n) worst case for unbalanced
     */
    public TreeNode search(TreeNode root, int target) {
        // Base case: empty tree or found the target
        if (root == null || root.val == target) {
            return root;
        }

        // Use BST property to decide which subtree to search
        if (target < root.val) {
            // If target is smaller, search in left subtree
            return search(root.left, target);
        } else {
            // If target is larger, search in right subtree
            return search(root.right, target);
        }
    }

    /**
     * Inserts a new value into a BST, maintaining the BST property
     * Returns the root of the modified BST
     */
    public TreeNode insert(TreeNode root, int val) {
        // Base case: if tree is empty, create a new node
        if (root == null) {
            return new TreeNode(val);
        }

        // Use BST property to decide which subtree to insert into
        if (val < root.val) {
            // Insert in left subtree
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            // Insert in right subtree
            root.right = insert(root.right, val);
        }

        // Return the (possibly modified) root node
        return root;
    }

    // Definition of a BST node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
