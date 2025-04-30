package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._415_binary_search_tree_properties;

/**
 * BINARY TREE SERIALIZATION - LEVEL ORDER APPROACH
 * ===============================================
 * <p>
 * This class implements the binary tree serialization and deserialization
 * using level order traversal (BFS), as described in LeetCode 297.
 * <p>
 * Problem Description:
 * Design an algorithm to serialize and deserialize a binary tree. The serialization
 * should convert a binary tree to a string representation that can be deserialized
 * back to the original tree structure.
 * <p>
 * Key Insights:
 * <p>
 * 1. Level Order Traversal for Serialization:
 * - Process nodes level by level, from left to right
 * - Include null pointers (as special markers) to preserve structure
 * - We need to enqueue null nodes (unlike typical BFS) to maintain structure
 * <p>
 * 2. Deserialization from Level Order Representation:
 * - Process nodes in the same order they were serialized
 * - Use a queue to keep track of nodes whose children we need to construct
 * - Skip adding null nodes to the queue during deserialization
 * <p>
 * 3. Unique Characteristics:
 * - Level order traversal naturally reflects the visual structure of the tree
 * - More intuitive for humans to read and understand
 * - Can terminate early if all remaining nodes are null
 * <p>
 * 4. Data Format:
 * - Use a special character (e.g., "#") to represent null pointers
 * - Use a separator (e.g., ",") between node values
 * <p>
 * Time and Space Complexity:
 * - Time: O(n) for both serialization and deserialization
 * - Space: O(n) for the queue and the serialized string
 */

import java.util.*;

public class _415_d_BinaryTreeLevelOrderSerialization {
    
    private static final String NULL_MARKER = "#";
    private static final String SEPARATOR = ",";

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
     * Print a binary tree in level-order format for visualization
     */
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        System.out.println("Level-order traversal:");
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        queue.offer(root);
        levelQueue.offer(1);

        int currentLevel = 1;
        System.out.print("Level 1: ");

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int level = levelQueue.poll();

            if (level > currentLevel) {
                System.out.println();
                System.out.print("Level " + level + ": ");
                currentLevel = level;
            }

            if (node == null) {
                System.out.print("null ");
            } else {
                System.out.print(node.val + " ");

                queue.offer(node.left);
                queue.offer(node.right);
                levelQueue.offer(level + 1);
                levelQueue.offer(level + 1);
            }
        }
        System.out.println();
    }

    /**
     * Trace the level-order serialization process for clarity
     */
    public static void traceLevelOrderSerialization(TreeNode root) {
        System.out.println("Tracing Level-Order Serialization:");
        System.out.println("----------------------------------");
        if (root == null) {
            System.out.println("Empty tree, returning empty string");
            return;
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        queue.offer(root);
        pathQueue.offer("root");

        System.out.println("Starting with root node " + root.val);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            String path = pathQueue.poll();

            if (node == null) {
                System.out.println("  Processing " + path + ": null -> Append '#,'");
                sb.append(NULL_MARKER).append(SEPARATOR);
                continue;
            }

            System.out.println("  Processing " + path + ": " + node.val + " -> Append '" + node.val + ",'");
            sb.append(node.val).append(SEPARATOR);

            // Add children to queue
            queue.offer(node.left);
            pathQueue.offer(path + ".left");

            queue.offer(node.right);
            pathQueue.offer(path + ".right");
        }

        System.out.println("Result: " + sb.toString());
    }

    /**
     * Trace the level-order deserialization process for clarity
     */
    public static void traceLevelOrderDeserialization(String serialized) {
        System.out.println("Tracing Level-Order Deserialization:");
        System.out.println("------------------------------------");
        System.out.println("Serialized string: " + serialized);

        if (serialized == null || serialized.isEmpty()) {
            System.out.println("Empty string, returning null");
            return;
        }

        String[] values = serialized.split(SEPARATOR);
        System.out.println("Split into tokens: " + Arrays.toString(values));

        if (values.length == 0 || values[0].equals(NULL_MARKER)) {
            System.out.println("First value is null, returning null");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        System.out.println("Creating root node with value " + values[0]);
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.offer(root);
        pathQueue.offer("root");

        int i = 1; // Start from the second element
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            String path = pathQueue.poll();

            System.out.println("Processing children of " + path + " (value: " + current.val + ")");

            // Process left child
            if (i < values.length) {
                String leftVal = values[i];
                System.out.print("  Left child: " + leftVal);

                if (!leftVal.equals(NULL_MARKER)) {
                    current.left = new TreeNode(Integer.parseInt(leftVal));
                    queue.offer(current.left);
                    pathQueue.offer(path + ".left");
                    System.out.println(" - Created node");
                } else {
                    System.out.println(" - Null, not creating node");
                }
                i++;
            }

            // Process right child
            if (i < values.length) {
                String rightVal = values[i];
                System.out.print("  Right child: " + rightVal);

                if (!rightVal.equals(NULL_MARKER)) {
                    current.right = new TreeNode(Integer.parseInt(rightVal));
                    queue.offer(current.right);
                    pathQueue.offer(path + ".right");
                    System.out.println(" - Created node");
                } else {
                    System.out.println(" - Null, not creating node");
                }
                i++;
            }
        }

        System.out.println("Deserialization complete");
    }

    /**
     * Demonstrate key advantages of level-order serialization
     */
    public static void explainLevelOrderAdvantages() {
        System.out.println("\nAdvantages of Level-Order Serialization:");
        System.out.println("---------------------------------------");
        System.out.println("1. Intuitive Representation:");
        System.out.println("   - Matches the visual top-to-bottom, left-to-right representation of trees");
        System.out.println("   - Often easier for humans to visualize the tree structure from the serialized string");
        System.out.println();
        System.out.println("2. Efficient for Breadth-First Algorithms:");
        System.out.println("   - Natural fit for problems that require level-by-level processing");
        System.out.println("   - Particularly useful for level-based operations (e.g., finding level averages)");
        System.out.println();
        System.out.println("3. Early Termination:");
        System.out.println("   - Can potentially save space by stopping serialization when all remaining nodes are null");
        System.out.println("   - Works well for balanced trees where all leaf nodes are at similar depths");
        System.out.println();
        System.out.println("4. Queue-based Implementation:");
        System.out.println("   - Uses iterative approach with a queue instead of recursion");
        System.out.println("   - Can be more efficient for very deep trees (avoids stack overflow)");
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

        System.out.println("BINARY TREE LEVEL-ORDER SERIALIZATION");
        System.out.println("=====================================");
        System.out.println();

        System.out.println("Original Tree:");
        printTree(root);
        System.out.println();

        printLevelOrder(root);
        System.out.println();

        // Create a codec instance
        Codec codec = new Codec();

        // Serialize the tree
        String serialized = codec.serialize(root);
        System.out.println("Serialized Tree (Level-Order): " + serialized);
        System.out.println();

        // Deserialize back to a tree
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized Tree:");
        printTree(deserialized);
        System.out.println();

        // Trace the serialization and deserialization for better understanding
        traceLevelOrderSerialization(root);
        System.out.println();
        traceLevelOrderDeserialization(serialized);
        System.out.println();

        // Explain the advantages of level-order approach
        explainLevelOrderAdvantages();

        // Additional example with a different tree shape
        System.out.println("\n\nAdditional Example - Unbalanced Tree:");
        //      1
        //     /
        //    2
        //   /
        //  3
        // /
        //4
        TreeNode unbalancedRoot = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4),
                                null),
                        null),
                null);

        System.out.println("Original Tree:");
        printTree(unbalancedRoot);
        System.out.println();

        // Serialize and deserialize
        String unbalancedSerialized = codec.serialize(unbalancedRoot);
        System.out.println("Serialized Tree (Level-Order): " + unbalancedSerialized);

        TreeNode unbalancedDeserialized = codec.deserialize(unbalancedSerialized);
        System.out.println("\nDeserialized Tree:");
        printTree(unbalancedDeserialized);
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
     * using level order traversal
     */
    public static class Codec {
        // Constants for serialization format
        private static final String SEPARATOR = ",";
        private static final String NULL_MARKER = "#";

        /**
         * Serialize a binary tree to a string using level order traversal
         */
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }

            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                if (node == null) {
                    sb.append(NULL_MARKER).append(SEPARATOR);
                    continue;
                }

                // Add the current node's value to the result
                sb.append(node.val).append(SEPARATOR);

                // Add left and right children to the queue (even if they are null)
                queue.offer(node.left);
                queue.offer(node.right);
            }

            return sb.toString();
        }

        /**
         * Deserialize a string to a binary tree using level order traversal
         */
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }

            String[] values = data.split(SEPARATOR);
            if (values.length == 0 || values[0].equals(NULL_MARKER)) {
                return null;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(values[0]));
            queue.offer(root);

            int i = 1; // Start from the second element (index 1)
            while (!queue.isEmpty() && i < values.length) {
                TreeNode current = queue.poll();

                // Process left child
                if (i < values.length) {
                    if (!values[i].equals(NULL_MARKER)) {
                        current.left = new TreeNode(Integer.parseInt(values[i]));
                        queue.offer(current.left);
                    }
                    i++;
                }

                // Process right child
                if (i < values.length) {
                    if (!values[i].equals(NULL_MARKER)) {
                        current.right = new TreeNode(Integer.parseInt(values[i]));
                        queue.offer(current.right);
                    }
                    i++;
                }
            }

            return root;
        }
    }
}
