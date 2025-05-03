package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._437_binary_search_tree_classic_practice_II; /**
 * Problem 449: Serialize and Deserialize BST (Medium)
 * <p>
 * Problem Description:
 * Design an algorithm to serialize and deserialize a binary search tree.
 * Serialization converts a data structure or object into a sequence of bits
 * so that it can be stored or transmitted and reconstructed later.
 * <p>
 * Key Concepts:
 * - BST Property Utilization: Left < Root < Right for efficient serialization
 * - Preorder Traversal: Root-first traversal for serialization
 * - Value Range Validation: Using min-max boundaries during deserialization
 * - Null Pointer Optimization: Avoiding null marker serialization by using BST properties
 * <p>
 * Time Complexity:
 * - Serialization: O(n) where n is the number of nodes
 * - Deserialization: O(n) where n is the number of nodes
 * Space Complexity: O(n) for both operations
 */

import java.util.*;

class _437_c_SerializeBST {
    // Main method for testing
    public static void main(String[] args) {
        _437_c_SerializeBST solution = new _437_c_SerializeBST();
        Codec codec = solution.new Codec();

        // Example 1: [2,1,3]
        TreeNode root1 = solution.new TreeNode(2);
        root1.left = solution.new TreeNode(1);
        root1.right = solution.new TreeNode(3);

        System.out.println("Example 1 - Original BST:");
        solution.printTree(root1, "", false);

        System.out.println("\nPreorder Traversal:");
        solution.printPreorder(root1);
        System.out.println();

        // Serialize the tree
        String serialized1 = codec.serialize(root1);
        System.out.println("\nSerialized representation: " + serialized1);

        // Deserialize back to a tree
        TreeNode deserialized1 = codec.deserialize(serialized1);
        System.out.println("\nDeserialized BST:");
        solution.printTree(deserialized1, "", false);

        // Verify if the trees are identical
        boolean identical1 = solution.areIdentical(root1, deserialized1);
        System.out.println("\nOriginal and deserialized trees are identical: " + identical1);

        // Example 2: Create a more complex BST
        TreeNode root2 = solution.new TreeNode(5);
        root2.left = solution.new TreeNode(3);
        root2.right = solution.new TreeNode(8);
        root2.left.left = solution.new TreeNode(2);
        root2.left.right = solution.new TreeNode(4);
        root2.right.left = solution.new TreeNode(6);
        root2.right.right = solution.new TreeNode(9);

        System.out.println("\n\nExample 2 - Original BST:");
        solution.printTree(root2, "", false);

        // Serialize the tree
        String serialized2 = codec.serialize(root2);
        System.out.println("\nSerialized representation: " + serialized2);

        // Deserialize back to a tree
        TreeNode deserialized2 = codec.deserialize(serialized2);
        System.out.println("\nDeserialized BST:");
        solution.printTree(deserialized2, "", false);

        // Verify if the trees are identical
        boolean identical2 = solution.areIdentical(root2, deserialized2);
        System.out.println("\nOriginal and deserialized trees are identical: " + identical2);

        // Example 3: Empty tree
        TreeNode root3 = null;
        String serialized3 = codec.serialize(root3);
        System.out.println("\n\nExample 3 - Empty tree serialized: \"" + serialized3 + "\"");
        TreeNode deserialized3 = codec.deserialize(serialized3);
        System.out.println("Deserialized empty tree is null: " + (deserialized3 == null));
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
     * Helper method to print the preorder traversal of the tree
     */
    private void printPreorder(TreeNode root) {
        if (root == null) return;

        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
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

    /**
     * Helper method to check if two trees are structurally identical
     */
    private boolean areIdentical(TreeNode a, TreeNode b) {
        // If both are null, they are identical
        if (a == null && b == null) {
            return true;
        }

        // If only one is null, they are not identical
        if (a == null || b == null) {
            return false;
        }

        // Check if current nodes and their subtrees are identical
        return (a.val == b.val) &&
                areIdentical(a.left, b.left) &&
                areIdentical(a.right, b.right);
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
     * Codec class to handle serialization and deserialization
     */
    public class Codec {
        // Delimiter to separate node values in the serialized string
        private static final String DELIMITER = ",";

        /**
         * Serializes a BST to a string.
         * Uses preorder traversal (root-left-right) without null markers
         *
         * @param root Root node of the BST
         * @return Serialized string representation
         */
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }

        /**
         * Helper method for serialization
         */
        private void serializeHelper(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }

            // Preorder traversal: root first
            sb.append(root.val).append(DELIMITER);

            // Then left and right subtrees
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }

        /**
         * Deserializes a string to a BST.
         * Reconstructs the tree using BST property and preorder traversal
         *
         * @param data Serialized string representation
         * @return Root node of the reconstructed BST
         */
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }

            // Convert string to a queue of integers
            String[] values = data.split(DELIMITER);
            Queue<Integer> queue = new LinkedList<>();

            for (String value : values) {
                if (!value.isEmpty()) {
                    queue.offer(Integer.parseInt(value));
                }
            }

            // Reconstruct the BST using value range boundaries
            return deserializeHelper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        /**
         * Helper method for deserialization
         * Uses the BST property to determine tree structure without explicit null markers
         */
        private TreeNode deserializeHelper(Queue<Integer> queue, int minVal, int maxVal) {
            if (queue.isEmpty()) {
                return null;
            }

            // Peek at the next value to see if it falls within the valid range
            int currentVal = queue.peek();

            // If current value is outside the valid range, it belongs to a different subtree
            if (currentVal < minVal || currentVal > maxVal) {
                return null;
            }

            // Remove the value from queue and create a node
            currentVal = queue.poll();
            TreeNode root = new TreeNode(currentVal);

            // Recursively build left and right subtrees
            // Left subtree values must be less than current value
            root.left = deserializeHelper(queue, minVal, currentVal);

            // Right subtree values must be greater than current value
            root.right = deserializeHelper(queue, currentVal, maxVal);

            return root;
        }
    }
}