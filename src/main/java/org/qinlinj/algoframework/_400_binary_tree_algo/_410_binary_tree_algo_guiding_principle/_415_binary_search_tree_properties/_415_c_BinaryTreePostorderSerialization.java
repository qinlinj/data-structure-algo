package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._415_binary_search_tree_properties;

/**
 * BINARY TREE SERIALIZATION - POSTORDER APPROACH
 * ==============================================
 * <p>
 * This class implements the binary tree serialization and deserialization
 * using postorder traversal, as described in LeetCode 297.
 * <p>
 * Problem Description:
 * Design an algorithm to serialize and deserialize a binary tree. The serialization
 * should convert a binary tree to a string representation that can be deserialized
 * back to the original tree structure.
 * <p>
 * Key Insights:
 * <p>
 * 1. Postorder Traversal for Serialization:
 * - Process order: left subtree -> right subtree -> root
 * - We must include null pointers (as special markers) to ensure uniqueness
 * - The root of the tree is the last element in the serialized string
 * <p>
 * 2. Deserialization from Postorder Representation:
 * - Process the serialized data in reverse order (from the end to the beginning)
 * - The last element is always the root
 * - Recursively build the right and left subtrees (in that order!)
 * - Null markers indicate leaf boundaries
 * <p>
 * 3. Key Difference from Preorder:
 * - While deserializing, we must build the right subtree before the left subtree
 * - We process nodes from the end of the list, not the beginning
 * <p>
 * 4. Data Format:
 * - Use a special character (e.g., "#") to represent null pointers
 * - Use a separator (e.g., ",") between node values
 * <p>
 * Time and Space Complexity:
 * - Time: O(n) for both serialization and deserialization
 * - Space: O(n) for the recursion stack and the serialized string
 * <p>
 * This implementation demonstrates how postorder traversal with null markers
 * can be used to uniquely serialize and deserialize a binary tree.
 */

import java.util.*;

public class _415_c_BinaryTreePostorderSerialization {
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
     * Tracing the postorder serialization process for clarity
     */
    public static void tracePostorderSerialization(TreeNode root) {
        System.out.println("Tracing Postorder Serialization:");
        System.out.println("-------------------------------");
        traceSerialize(root, 0);
        System.out.println();
    }

    private static void traceSerialize(TreeNode node, int depth) {
        String indent = "  ".repeat(depth);

        if (node == null) {
            System.out.println(indent + "Process null -> Append '#,'");
            return;
        }

        // Process left subtree first
        System.out.println(indent + "Traverse to left child of " + node.val);
        traceSerialize(node.left, depth + 1);

        // Then process right subtree
        System.out.println(indent + "Traverse to right child of " + node.val);
        traceSerialize(node.right, depth + 1);

        // Postorder position
        System.out.println(indent + "Process node " + node.val + " -> Append '" + node.val + ",'");
    }

    /**
     * Tracing the postorder deserialization process for clarity
     */
    public static void tracePostorderDeserialization(String serialized) {
        System.out.println("Tracing Postorder Deserialization:");
        System.out.println("---------------------------------");
        System.out.println("Serialized string: " + serialized);
        String[] tokens = serialized.split(",");
        StringBuilder tokenDisplay = new StringBuilder();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                tokenDisplay.append(token).append(", ");
            }
        }
        System.out.println("Split into tokens: " + tokenDisplay);
        System.out.println();

        LinkedList<String> nodes = new LinkedList<>();
        for (String node : serialized.split(",")) {
            if (!node.isEmpty()) {
                nodes.addLast(node);
            }
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

        String val = nodes.getLast();
        System.out.println(indent + "Processing token from end: " + val);

        nodes.removeLast();

        if (val.equals("#")) {
            System.out.println(indent + "Found null marker, return null");
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));
        System.out.println(indent + "Created node with value: " + node.val);

        System.out.println(indent + "Building right subtree of node " + node.val + " first");
        node.right = traceDeserialize(nodes, depth + 1);

        System.out.println(indent + "Building left subtree of node " + node.val);
        node.left = traceDeserialize(nodes, depth + 1);

        System.out.println(indent + "Completed node " + node.val + " with left=" +
                (node.left == null ? "null" : node.left.val) +
                ", right=" + (node.right == null ? "null" : node.right.val));

        return node;
    }

    /**
     * Compare serialized results from preorder and postorder for the same tree
     */
    public static void compareWithPreorder(TreeNode root) {
        // Import the preorder codec class
        try {
            // Try to use reflection to get the Codec class from the preorder implementation
            Class<?> preorderClass = Class.forName("_415_b_BinaryTreePreorderSerialization$Codec");
            Object preorderCodec = preorderClass.getDeclaredConstructor().newInstance();
            java.lang.reflect.Method preorderSerialize =
                    preorderClass.getMethod("serialize", TreeNode.class);

            Codec postorderCodec = new Codec();

            String preorderSerialized = (String) preorderSerialize.invoke(preorderCodec, root);
            String postorderSerialized = postorderCodec.serialize(root);

            System.out.println("Comparison of Serialization Approaches:");
            System.out.println("--------------------------------------");
            System.out.println("Preorder:  " + preorderSerialized);
            System.out.println("Postorder: " + postorderSerialized);
            System.out.println();
            System.out.println("Key Differences:");
            System.out.println("1. In preorder, the root appears first");
            System.out.println("2. In postorder, the root appears last");
            System.out.println("3. Both contain the same null markers, but in different positions");
            System.out.println("4. Both uniquely identify the tree structure");
        } catch (Exception e) {
            // Fall back to just showing postorder if preorder class is not available
            System.out.println("Preorder implementation not found for comparison");
            System.out.println("Postorder serialization places the root value last, after both subtrees");
        }
    }

    /**
     * Explain why inorder serialization doesn't work
     */
    public static void explainInorderLimitation() {
        System.out.println("Why Inorder Serialization Doesn't Work:");
        System.out.println("-------------------------------------");
        System.out.println("Inorder traversal (even with null markers) cannot uniquely identify a tree because:");
        System.out.println("1. The position of the root node in the serialized data is not fixed");
        System.out.println("2. During deserialization, we can't determine which element is the root");
        System.out.println("3. Without knowing the root, we can't properly split the data into left and right subtrees");
        System.out.println();
        System.out.println("Example:");
        System.out.println("The inorder traversal with null markers [#,1,#,2,#] could represent either:");
        System.out.println("  2       or       1");
        System.out.println(" /                  \\");
        System.out.println("1                    2");
        System.out.println();
        System.out.println("In contrast, both preorder and postorder traversals fix the position of the root");
        System.out.println("(first or last element), enabling unique reconstruction of the tree.");
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

        System.out.println("BINARY TREE POSTORDER SERIALIZATION");
        System.out.println("==================================");
        System.out.println();

        System.out.println("Original Tree:");
        printTree(root);
        System.out.println();

        // Create a codec instance
        Codec codec = new Codec();

        // Serialize the tree
        String serialized = codec.serialize(root);
        System.out.println("Serialized Tree (Postorder): " + serialized);
        System.out.println();

        // Deserialize back to a tree
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized Tree:");
        printTree(deserialized);
        System.out.println();

        // Trace the serialization and deserialization for better understanding
        tracePostorderSerialization(root);
        System.out.println();
        tracePostorderDeserialization(serialized);
        System.out.println();

        // Compare with preorder approach
        compareWithPreorder(root);
        System.out.println();

        // Explain inorder limitations
        explainInorderLimitation();
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
     * using postorder traversal
     */
    public static class Codec {
        // Constants for serialization format
        private static final String SEPARATOR = ",";
        private static final String NULL_MARKER = "#";

        /**
         * Serialize a binary tree to a string using postorder traversal
         */
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }

        /**
         * Helper method for serialization that performs the actual postorder traversal
         */
        private void serializeHelper(TreeNode root, StringBuilder sb) {
            // Base case: null node
            if (root == null) {
                sb.append(NULL_MARKER).append(SEPARATOR);
                return;
            }

            // Recurse on left and right subtrees first (postorder)
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);

            // Postorder position: process the current node after both subtrees
            sb.append(root.val).append(SEPARATOR);
        }

        /**
         * Deserialize a string to a binary tree using postorder traversal
         */
        public TreeNode deserialize(String data) {
            // Convert the string to a list of values
            LinkedList<String> nodes = new LinkedList<>();
            for (String node : data.split(SEPARATOR)) {
                if (!node.isEmpty()) {
                    nodes.addLast(node);
                }
            }

            // Start the recursive deserialization
            return deserializeHelper(nodes);
        }

        /**
         * Helper method for deserialization that recursively constructs the tree
         * For postorder, we need to process nodes from the end of the list
         */
        private TreeNode deserializeHelper(LinkedList<String> nodes) {
            // Check if the list is empty
            if (nodes.isEmpty()) {
                return null;
            }

            // Get the last value (root of current subtree)
            String val = nodes.removeLast();

            // If it's a null marker, return null (end of this branch)
            if (val.equals(NULL_MARKER)) {
                return null;
            }

            // Create the current node
            TreeNode root = new TreeNode(Integer.parseInt(val));

            // Recursively build right and left subtrees
            // IMPORTANT: The order is critical - right first, then left (reverse of postorder)
            // This is because we're reading the serialized data from the end
            root.right = deserializeHelper(nodes);
            root.left = deserializeHelper(nodes);

            return root;
        }
    }
}
