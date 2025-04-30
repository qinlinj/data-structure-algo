package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._415_binary_search_tree_properties;

/**
 * BINARY TREE SERIALIZATION - PREORDER APPROACH
 * =============================================
 * <p>
 * This class implements the binary tree serialization and deserialization
 * using preorder traversal, as described in LeetCode 297.
 * <p>
 * Problem Description:
 * Design an algorithm to serialize and deserialize a binary tree. The serialization
 * should convert a binary tree to a string representation that can be deserialized
 * back to the original tree structure.
 * <p>
 * Key Insights:
 * <p>
 * 1. Preorder Traversal for Serialization:
 * - Process order: root -> left subtree -> right subtree
 * - We must include null pointers (as special markers) to ensure uniqueness
 * - This creates a "flattened" representation of the tree structure
 * <p>
 * 2. Deserialization from Preorder Representation:
 * - Process the serialized data in the same preorder sequence
 * - The first element is always the root
 * - Recursively build the left and right subtrees
 * - Null markers indicate leaf boundaries
 * <p>
 * 3. Data Format:
 * - Use a special character (e.g., "#") to represent null pointers
 * - Use a separator (e.g., ",") between node values
 * <p>
 * Time and Space Complexity:
 * - Time: O(n) for both serialization and deserialization
 * - Space: O(n) for the recursion stack and the serialized string
 * <p>
 * This implementation demonstrates how preorder traversal with null markers
 * can be used to uniquely serialize and deserialize a binary tree.
 */

import java.util.*;

public class _415_b_BinaryTreePreorderSerialization {
    /**
     * Helper method to print a binary tree for visualization
     */
    public static void printTree(TreeNode root) {
        printTreeHelper(root, 0);
    }

    private static void printTreeHelper(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        // Print right subtree (top of the visual representation)
        printTreeHelper(node.right, level + 1);

        // Print current node
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.val);

        // Print left subtree (bottom of the visual representation)
        printTreeHelper(node.left, level + 1);
    }

    /**
     * Tracing the preorder serialization process for clarity
     */
    public static void tracePreorderSerialization(TreeNode root) {
        System.out.println("Tracing Preorder Serialization:");
        System.out.println("------------------------------");
        traceSerialize(root, 0);
        System.out.println();
    }

    private static void traceSerialize(TreeNode node, int depth) {
        String indent = "  ".repeat(depth);

        if (node == null) {
            System.out.println(indent + "Process null -> Append '#,'");
            return;
        }

        // Preorder position
        System.out.println(indent + "Process node " + node.val + " -> Append '" + node.val + ",'");

        // Process left subtree
        System.out.println(indent + "Traverse to left child of " + node.val);
        traceSerialize(node.left, depth + 1);

        // Process right subtree
        System.out.println(indent + "Traverse to right child of " + node.val);
        traceSerialize(node.right, depth + 1);
    }

    /**
     * Tracing the preorder deserialization process for clarity
     */
    public static void tracePreorderDeserialization(String serialized) {
        System.out.println("Tracing Preorder Deserialization:");
        System.out.println("--------------------------------");
        System.out.println("Serialized string: " + serialized);
        System.out.println("Split into tokens: " + String.join(", ", serialized.split(",")));
        System.out.println();

        LinkedList<String> nodes = new LinkedList<>();
        for (String node : serialized.split(",")) {
            nodes.addLast(node);
        }

        traceDeserialize(nodes, 0);
        System.out.println();
    }

    private static TreeNode traceDeserialize(LinkedList<String> nodes, int depth) {
        String indent = "  ".repeat(depth);

        if (nodes.isEmpty()) {
            System.out.println(indent + "No more nodes, return null");
            return null;
        }

        String val = nodes.getFirst();
        System.out.println(indent + "Processing token: " + val);

        nodes.removeFirst();

        if (val.equals("#")) {
            System.out.println(indent + "Found null marker, return null");
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));
        System.out.println(indent + "Created node with value: " + node.val);

        System.out.println(indent + "Building left subtree of node " + node.val);
        node.left = traceDeserialize(nodes, depth + 1);

        System.out.println(indent + "Building right subtree of node " + node.val);
        node.right = traceDeserialize(nodes, depth + 1);

        System.out.println(indent + "Completed node " + node.val + " with left=" +
                (node.left == null ? "null" : node.left.val) +
                ", right=" + (node.right == null ? "null" : node.right.val));

        return node;
    }

    /**
     * Main method to demonstrate serialization and deserialization
     */
    public static void main(String[] args) {
        // Create a sample tree:
        //     1
        //    / \
        //   2   3
        //  /   / \
        // 4   5   6
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        null),
                new TreeNode(3,
                        new TreeNode(5),
                        new TreeNode(6)));

        System.out.println("BINARY TREE PREORDER SERIALIZATION");
        System.out.println("=================================");
        System.out.println();

        System.out.println("Original Tree:");
        printTree(root);
        System.out.println();

        // Create a codec instance
        Codec codec = new Codec();

        // Serialize the tree
        String serialized = codec.serialize(root);
        System.out.println("Serialized Tree (Preorder): " + serialized);
        System.out.println();

        // Deserialize back to a tree
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized Tree:");
        printTree(deserialized);
        System.out.println();

        // Trace the serialization and deserialization for better understanding
        tracePreorderSerialization(root);
        System.out.println();
        tracePreorderDeserialization(serialized);
    }

    // Definition for a binary tree node
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

    /**
     * Codec class for serializing and deserializing a binary tree
     * using preorder traversal
     */
    public static class Codec {
        // Constants for serialization format
        private static final String SEPARATOR = ",";
        private static final String NULL_MARKER = "#";

        /**
         * Serialize a binary tree to a string using preorder traversal
         */
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }

        /**
         * Helper method for serialization that performs the actual preorder traversal
         */
        private void serializeHelper(TreeNode root, StringBuilder sb) {
            // Base case: null node
            if (root == null) {
                sb.append(NULL_MARKER).append(SEPARATOR);
                return;
            }

            // Preorder position: process the current node
            sb.append(root.val).append(SEPARATOR);

            // Recurse on left and right subtrees
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }

        /**
         * Deserialize a string to a binary tree using preorder traversal
         */
        public TreeNode deserialize(String data) {
            // Convert the string to a list of values
            LinkedList<String> nodes = new LinkedList<>();
            for (String node : data.split(SEPARATOR)) {
                nodes.addLast(node);
            }

            // Start the recursive deserialization
            return deserializeHelper(nodes);
        }

        /**
         * Helper method for deserialization that recursively constructs the tree
         */
        private TreeNode deserializeHelper(LinkedList<String> nodes) {
            // Check if the list is empty
            if (nodes.isEmpty()) {
                return null;
            }

            // Get the first value (root of current subtree)
            String val = nodes.removeFirst();

            // If it's a null marker, return null (end of this branch)
            if (val.equals(NULL_MARKER)) {
                return null;
            }

            // Create the current node
            TreeNode root = new TreeNode(Integer.parseInt(val));

            // Recursively build left and right subtrees
            // The order is important: left first, then right (preorder)
            root.left = deserializeHelper(nodes);
            root.right = deserializeHelper(nodes);

            return root;
        }
    }
}
