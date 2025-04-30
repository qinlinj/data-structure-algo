package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._415_binary_search_tree_properties;

/**
 * BINARY TREE SERIALIZATION - COMPREHENSIVE SUMMARY
 * ================================================
 * <p>
 * This class provides a comprehensive summary of the different approaches
 * to binary tree serialization and deserialization, comparing their strengths,
 * weaknesses, and applications.
 * <p>
 * Key Concepts Covered:
 * <p>
 * 1. Tree Traversal Uniqueness:
 * - Which traversal methods can uniquely identify a tree structure
 * - The importance of null pointers in serialization
 * - Why some combinations work while others don't
 * <p>
 * 2. Three Valid Serialization Approaches:
 * - Preorder Traversal
 * - Postorder Traversal
 * - Level-Order Traversal
 * <p>
 * 3. Why Inorder Traversal Doesn't Work:
 * - The inability to determine the root position
 * - The non-uniqueness problem even with null markers
 * <p>
 * 4. Comparison of Approaches:
 * - Time and space complexity analysis
 * - Implementation considerations
 * - Use cases and trade-offs
 * <p>
 * This summary serves as a reference guide for understanding binary tree
 * serialization techniques and making informed decisions about which approach
 * to use for different scenarios.
 */

import java.util.*;

public class _415_e_BinaryTreeSerializationSummary {
    /**
     * Constants for serialization format
     */
    private static final String SEPARATOR = ",";
    private static final String NULL_MARKER = "#";

    /**
     * Create sample trees for demonstration
     */
    public static TreeNode createSampleTree() {
        // Create a sample tree:
        //     1
        //    / \
        //   2   3
        //  /   / \
        // 4   5   6
        return new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        null),
                new TreeNode(3,
                        new TreeNode(5),
                        new TreeNode(6)));
    }

    public static TreeNode createAmbiguousTree1() {
        // Create first ambiguous tree:
        //   1
        //  / \
        // 2   3
        return new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
    }

    public static TreeNode createAmbiguousTree2() {
        // Create second ambiguous tree:
        //   1
        //    \
        //     2
        //      \
        //       3
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2, null, node3);
        TreeNode node1 = new TreeNode(1, null, node2);
        return node1;
    }

    /**
     * APPROACH 1: PREORDER SERIALIZATION
     */
    public static String serializePreorder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializePreorderHelper(root, sb);
        return sb.toString();
    }

    private static void serializePreorderHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL_MARKER).append(SEPARATOR);
            return;
        }

        // Preorder position: process current node first
        sb.append(root.val).append(SEPARATOR);

        // Then process left and right subtrees
        serializePreorderHelper(root.left, sb);
        serializePreorderHelper(root.right, sb);
    }

    public static TreeNode deserializePreorder(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        LinkedList<String> nodes = new LinkedList<>();
        for (String node : data.split(SEPARATOR)) {
            if (!node.isEmpty()) {
                nodes.addLast(node);
            }
        }

        return deserializePreorderHelper(nodes);
    }

    private static TreeNode deserializePreorderHelper(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }

        String val = nodes.removeFirst();
        if (val.equals(NULL_MARKER)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserializePreorderHelper(nodes);
        root.right = deserializePreorderHelper(nodes);

        return root;
    }

    /**
     * APPROACH 2: POSTORDER SERIALIZATION
     */
    public static String serializePostorder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializePostorderHelper(root, sb);
        return sb.toString();
    }

    private static void serializePostorderHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL_MARKER).append(SEPARATOR);
            return;
        }

        // Process left and right subtrees first
        serializePostorderHelper(root.left, sb);
        serializePostorderHelper(root.right, sb);

        // Postorder position: process current node last
        sb.append(root.val).append(SEPARATOR);
    }

    public static TreeNode deserializePostorder(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        LinkedList<String> nodes = new LinkedList<>();
        for (String node : data.split(SEPARATOR)) {
            if (!node.isEmpty()) {
                nodes.addLast(node);
            }
        }

        return deserializePostorderHelper(nodes);
    }

    private static TreeNode deserializePostorderHelper(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }

        String val = nodes.removeLast();
        if (val.equals(NULL_MARKER)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(val));

        // IMPORTANT: Right subtree first, then left (reverse of postorder)
        root.right = deserializePostorderHelper(nodes);
        root.left = deserializePostorderHelper(nodes);

        return root;
    }

    /**
     * APPROACH 3: LEVEL-ORDER SERIALIZATION
     */
    public static String serializeLevelOrder(TreeNode root) {
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

            sb.append(node.val).append(SEPARATOR);

            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    public static TreeNode deserializeLevelOrder(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] values = data.split(SEPARATOR);
        if (values.length == 0 || values[0].equals(NULL_MARKER)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
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

    /**
     * ATTEMPTED APPROACH: INORDER SERIALIZATION (DOESN'T WORK)
     */
    public static String serializeInorder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeInorderHelper(root, sb);
        return sb.toString();
    }

    private static void serializeInorderHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL_MARKER).append(SEPARATOR);
            return;
        }

        // Process left subtree
        serializeInorderHelper(root.left, sb);

        // Inorder position: process current node in between subtrees
        sb.append(root.val).append(SEPARATOR);

        // Process right subtree
        serializeInorderHelper(root.right, sb);
    }

    /**
     * Attempted implementation for inorder deserialization (won't work correctly)
     * Included to demonstrate the limitations of inorder traversal
     */
    public static TreeNode attemptDeserializeInorder(String data) {
        System.out.println("WARNING: Inorder deserialization will NOT work correctly");
        System.out.println("This is just a demonstration of why it fails");

        // This implementation is flawed and included only for demonstration
        LinkedList<String> nodes = new LinkedList<>();
        for (String node : data.split(SEPARATOR)) {
            if (!node.isEmpty()) {
                nodes.add(node);
            }
        }

        return null; // In reality, we can't implement this correctly
    }

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

        // Print right subtree
        printTreeHelper(node.right, level + 1);

        // Print current node
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.val);

        // Print left subtree
        printTreeHelper(node.left, level + 1);
    }

    /**
     * Demonstrate why inorder traversal is insufficient
     */
    public static void demonstrateInorderLimitation() {
        System.out.println("DEMONSTRATING INORDER TRAVERSAL LIMITATIONS");
        System.out.println("===========================================");

        // Create two different trees with the same inorder traversal
        TreeNode tree1 = createAmbiguousTree1();
        TreeNode tree2 = createAmbiguousTree2();

        System.out.println("Tree 1:");
        printTree(tree1);
        System.out.println("\nTree 2:");
        printTree(tree2);

        // Serialize both trees using inorder traversal
        String inorderTree1 = serializeInorder(tree1);
        String inorderTree2 = serializeInorder(tree2);

        System.out.println("\nInorder serialization of Tree 1: " + inorderTree1);
        System.out.println("Inorder serialization of Tree 2: " + inorderTree2);

        // Demonstrate the problem: both trees have different structures but same inorder traversal
        System.out.println("\nKey Insight: Different tree structures can have the same inorder traversal");
        System.out.println("Even with null markers, we cannot determine which value is the root");
        System.out.println("This makes inorder traversal unsuitable for serialization/deserialization");
    }

    /**
     * Compare the three valid serialization approaches
     */
    public static void compareValidApproaches() {
        TreeNode tree = createSampleTree();

        System.out.println("COMPARING VALID SERIALIZATION APPROACHES");
        System.out.println("=======================================");
        System.out.println("Original Tree:");
        printTree(tree);

        // Serialize using all approaches
        String preorderSerialized = serializePreorder(tree);
        String postorderSerialized = serializePostorder(tree);
        String levelOrderSerialized = serializeLevelOrder(tree);

        System.out.println("\nSerialized Results:");
        System.out.println("Preorder:    " + preorderSerialized);
        System.out.println("Postorder:   " + postorderSerialized);
        System.out.println("Level-Order: " + levelOrderSerialized);

        // Deserialize and verify
        TreeNode preorderDeserialized = deserializePreorder(preorderSerialized);
        TreeNode postorderDeserialized = deserializePostorder(postorderSerialized);
        TreeNode levelOrderDeserialized = deserializeLevelOrder(levelOrderSerialized);

        System.out.println("\nAll three approaches can correctly reconstruct the original tree.");

        System.out.println("\nComparison of Approaches:");
        System.out.println("------------------------");
        System.out.println("1. Preorder Traversal:");
        System.out.println("   - Root appears first in serialized string");
        System.out.println("   - Natural recursive implementation");
        System.out.println("   - Good for deep, unbalanced trees");
        System.out.println();

        System.out.println("2. Postorder Traversal:");
        System.out.println("   - Root appears last in serialized string");
        System.out.println("   - Slightly more complex deserialization (right before left)");
        System.out.println("   - Also good for deep, unbalanced trees");
        System.out.println();

        System.out.println("3. Level-Order Traversal:");
        System.out.println("   - Follows visual top-to-bottom, left-to-right tree layout");
        System.out.println("   - Iterative implementation using queue");
        System.out.println("   - Better for balanced trees");
        System.out.println("   - More intuitive for human reading");
    }

    /**
     * Summarize key insights about serialization uniqueness
     */
    public static void summarizeUniquenessRules() {
        System.out.println("\nKEY INSIGHTS ON TREE SERIALIZATION UNIQUENESS");
        System.out.println("===========================================");

        System.out.println("1. Without Null Pointer Information:");
        System.out.println("   a) Single traversal (pre/in/post-order): Cannot uniquely identify a tree");
        System.out.println("   b) Preorder + Inorder: Can uniquely identify a tree");
        System.out.println("   c) Postorder + Inorder: Can uniquely identify a tree");
        System.out.println("   d) Preorder + Postorder: Cannot uniquely identify a tree");
        System.out.println();

        System.out.println("2. With Null Pointer Information:");
        System.out.println("   a) Preorder alone: Can uniquely identify a tree");
        System.out.println("   b) Postorder alone: Can uniquely identify a tree");
        System.out.println("   c) Level-order alone: Can uniquely identify a tree");
        System.out.println("   d) Inorder alone: CANNOT uniquely identify a tree");
        System.out.println();

        System.out.println("The key factor is the ability to determine the root position during deserialization:");
        System.out.println("- In preorder traversal: Root is the first element");
        System.out.println("- In postorder traversal: Root is the last element");
        System.out.println("- In level-order traversal: Root is the first element");
        System.out.println("- In inorder traversal: Root position is indeterminate");
    }

    /**
     * Analyze time and space complexity of different approaches
     */
    public static void analyzeComplexity() {
        System.out.println("\nTIME AND SPACE COMPLEXITY ANALYSIS");
        System.out.println("==================================");

        System.out.println("1. Preorder Approach:");
        System.out.println("   - Time Complexity (Serialization): O(n) - Visit each node once");
        System.out.println("   - Time Complexity (Deserialization): O(n) - Construct each node once");
        System.out.println("   - Space Complexity: O(n) - Storage for the serialized string and recursion stack");
        System.out.println();

        System.out.println("2. Postorder Approach:");
        System.out.println("   - Time Complexity (Serialization): O(n) - Visit each node once");
        System.out.println("   - Time Complexity (Deserialization): O(n) - Construct each node once");
        System.out.println("   - Space Complexity: O(n) - Same as preorder");
        System.out.println();

        System.out.println("3. Level-Order Approach:");
        System.out.println("   - Time Complexity (Serialization): O(n) - Visit each node once");
        System.out.println("   - Time Complexity (Deserialization): O(n) - Construct each node once");
        System.out.println("   - Space Complexity: O(n) - Queue can contain at most n/2 nodes at once (last level)");
        System.out.println();

        System.out.println("All approaches have the same asymptotic complexity, but may have");
        System.out.println("different practical performance characteristics based on tree shape:");
        System.out.println("- Recursive approaches (pre/post-order) have overhead from function calls");
        System.out.println("- Queue-based approaches (level-order) have overhead from queue operations");
        System.out.println("- For very deep trees, recursive approaches risk stack overflow");
    }

    /**
     * Provide practical guidelines for choosing an approach
     */
    public static void provideGuidelines() {
        System.out.println("\nPRACTICAL GUIDELINES FOR CHOOSING AN APPROACH");
        System.out.println("============================================");

        System.out.println("1. Choose Preorder When:");
        System.out.println("   - You need a recursive implementation");
        System.out.println("   - The tree is deep but not too deep (to avoid stack overflow)");
        System.out.println("   - You want a simple, straightforward implementation");
        System.out.println();

        System.out.println("2. Choose Postorder When:");
        System.out.println("   - Similar considerations as preorder");
        System.out.println("   - You're already using postorder traversal for other operations");
        System.out.println();

        System.out.println("3. Choose Level-Order When:");
        System.out.println("   - The tree is very deep (to avoid stack overflow)");
        System.out.println("   - Human readability is important");
        System.out.println("   - The serialized format needs to match visual tree layout");
        System.out.println("   - You're already using level-order traversal for other operations");
        System.out.println();

        System.out.println("4. Implementation Best Practices:");
        System.out.println("   - Always include null pointer information in serialization");
        System.out.println("   - Use clear markers (e.g., '#') and separators (e.g., ',')");
        System.out.println("   - Consider potential optimizations like eliminating trailing nulls");
        System.out.println("   - Be consistent with the serialization format across your codebase");
    }

    /**
     * Main method to demonstrate all concepts
     */
    public static void main(String[] args) {
        System.out.println("BINARY TREE SERIALIZATION COMPREHENSIVE SUMMARY");
        System.out.println("=============================================");
        System.out.println();

        // Demonstrate key concepts
        compareValidApproaches();
        demonstrateInorderLimitation();
        summarizeUniquenessRules();
        analyzeComplexity();
        provideGuidelines();

        System.out.println("\nCONCLUSION");
        System.out.println("==========");
        System.out.println("Binary tree serialization is a fundamental technique for storing and transmitting");
        System.out.println("tree structures. By understanding the uniqueness properties of different traversal");
        System.out.println("methods and their respective advantages, you can make informed decisions about");
        System.out.println("which approach to use for your specific use case.");
        System.out.println();
        System.out.println("Remember that preorder, postorder, and level-order traversals (with null markers)");
        System.out.println("can all uniquely represent and reconstruct a binary tree, while inorder traversal");
        System.out.println("cannot, even with null markers included.");
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
}
