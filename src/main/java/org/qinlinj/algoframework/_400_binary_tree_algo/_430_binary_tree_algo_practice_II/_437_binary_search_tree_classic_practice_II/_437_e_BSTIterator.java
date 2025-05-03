package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._437_binary_search_tree_classic_practice_II; /**
 * Problem 173: Binary Search Tree Iterator (Medium)
 * <p>
 * Problem Description:
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 * - BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 * - boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * - int next() Moves the pointer to the right, then returns the number at the pointer.
 * <p>
 * Key Concepts:
 * - Iterative Inorder Traversal: Using stack to simulate recursive traversal
 * - Controlled Traversal: Advancing through the tree only when necessary
 * - Left Branch Processing: Pushing all left nodes to stack at initialization and after moving right
 * - Space Optimization: O(h) space complexity where h is the height of the tree
 * <p>
 * Time Complexity:
 * - Constructor: O(h) where h is the height of the tree
 * - hasNext(): O(1)
 * - next(): Amortized O(1) (occasional O(h) operations averaged over n calls)
 * <p>
 * Space Complexity: O(h) for the stack which stores at most h nodes
 */

import java.util.*;

class _437_e_BSTIterator {
    // Main method for testing
    public static void main(String[] args) {
        _437_e_BSTIterator solution = new _437_e_BSTIterator();

        // Example 1: [7,3,15,null,null,9,20]
        TreeNode root1 = solution.new TreeNode(7);
        root1.left = solution.new TreeNode(3);
        root1.right = solution.new TreeNode(15);
        root1.right.left = solution.new TreeNode(9);
        root1.right.right = solution.new TreeNode(20);

        System.out.println("Example 1 - BST Structure:");
        solution.printTree(root1, "", false);

        System.out.println("\nInorder traversal (manual):");
        solution.printInorder(root1);
        System.out.println();

        System.out.println("\nIterator traversal:");
        BSTIterator iterator = solution.new BSTIterator(root1);

        // Test the iterator
        System.out.print("Using next() and hasNext(): ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // Example 2: More complex BST
        int[] values = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        TreeNode root2 = solution.createExampleBST(values);

        System.out.println("\n\nExample 2 - BST Structure:");
        solution.printTree(root2, "", false);

        System.out.println("\nInorder traversal (manual):");
        solution.printInorder(root2);
        System.out.println();

        System.out.println("\nIterator traversal:");
        BSTIterator iterator2 = solution.new BSTIterator(root2);

        System.out.print("Using next() and hasNext(): ");
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }
        System.out.println();

        // Example 3: Test the enhanced iterator with peek
        System.out.println("\n\nExample 3 - Testing BSTIteratorWithPeek:");
        BSTIteratorWithPeek peekIterator = solution.new BSTIteratorWithPeek(root2);

        System.out.println("Peeking and advancing:");
        while (peekIterator.hasNext()) {
            System.out.println("Peek: " + peekIterator.peek() + ", Next: " + peekIterator.next());
        }
    }

    /**
     * Helper method to create an example BST
     */
    private TreeNode createExampleBST(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        // Simple approach for demo: not balanced, just inserts in order
        TreeNode root = null;
        for (int val : values) {
            root = insertIntoBST(root, val);
        }

        return root;
    }

    /**
     * Helper method to insert a value into a BST
     */
    private TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
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

    /**
     * BSTIterator implementation using stack to simulate inorder traversal
     */
    public class BSTIterator {
        // Stack to simulate recursive traversal
        private Stack<TreeNode> stack;

        /**
         * Constructor initializes the iterator with the BST root
         * Pushes all left branch nodes onto the stack
         */
        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            // Initialize by pushing all left nodes onto stack
            pushLeftBranch(root);
        }

        /**
         * Returns whether the iterator has a next node
         * Time Complexity: O(1)
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next node value and advances the iterator
         * Time Complexity: Amortized O(1)
         */
        public int next() {
            // The next node in inorder traversal is at the top of the stack
            TreeNode node = stack.pop();

            // If this node has a right child, push all left nodes of the right subtree
            // This prepares the stack for future next() calls
            pushLeftBranch(node.right);

            // Return the current node's value
            return node.val;
        }

        /**
         * Helper method to push all nodes along the leftmost path onto the stack
         * This is a key optimization to achieve O(h) space complexity
         */
        private void pushLeftBranch(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    /**
     * Enhanced implementation with peek functionality
     * Useful for problems like merging two BSTs
     */
    public class BSTIteratorWithPeek {
        private Stack<TreeNode> stack;

        public BSTIteratorWithPeek(TreeNode root) {
            stack = new Stack<>();
            pushLeftBranch(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode node = stack.pop();
            pushLeftBranch(node.right);
            return node.val;
        }

        // Additional method to peek at the next value without advancing
        public int peek() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator");
            }
            return stack.peek().val;
        }

        private void pushLeftBranch(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }
}