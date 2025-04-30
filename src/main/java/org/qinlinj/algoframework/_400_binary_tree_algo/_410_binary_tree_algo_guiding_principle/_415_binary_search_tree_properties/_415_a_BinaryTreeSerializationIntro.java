package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._415_binary_search_tree_properties;

/**
 * BINARY TREE SERIALIZATION - INTRODUCTION
 * ========================================
 * <p>
 * This class provides an introduction to the serialization and deserialization
 * of binary trees, explaining key concepts from the article.
 * <p>
 * Problem Background:
 * Serialization is the process of converting a data structure into a format that can be
 * stored or transmitted. Deserialization is the reverse process of constructing a data
 * structure from the serialized format. These techniques are essential for:
 * - Storing data structures in files or databases
 * - Transmitting data structures across networks
 * - Making data structures language-independent
 * <p>
 * Key Insights on Binary Tree Serialization:
 * <p>
 * 1. Uniqueness Criteria:
 * - To deserialize a binary tree uniquely, we need sufficient information
 * - Traversal results without null pointers are generally insufficient
 * - Different combinations of traversal methods provide different levels of information
 * <p>
 * 2. Traversal Combinations and Uniqueness:
 * a) Without null pointer information:
 * - Single traversal (pre/in/post): Cannot uniquely identify a tree
 * - Preorder + Inorder: Can uniquely identify a tree
 * - Postorder + Inorder: Can uniquely identify a tree
 * - Preorder + Postorder: Cannot uniquely identify a tree
 * <p>
 * b) With null pointer information:
 * - Preorder only: Can uniquely identify a tree
 * - Postorder only: Can uniquely identify a tree
 * - Inorder only: Cannot uniquely identify a tree
 * <p>
 * This class demonstrates these concepts and provides a foundation for the
 * implementation of various serialization and deserialization approaches.
 */

public class _415_a_BinaryTreeSerializationIntro {
    /**
     * Main method to demonstrate the concepts
     */
    public static void main(String[] args) {
        _415_a_BinaryTreeSerializationIntro demo = new _415_a_BinaryTreeSerializationIntro();

        System.out.println("BINARY TREE SERIALIZATION INTRODUCTION");
        System.out.println("======================================");
        System.out.println();
        demo.demonstrateNonUniquePreorder();
        System.out.println();
        demo.demonstrateNonUniqueInorder();
        System.out.println();
        demo.summarizeUniquenessRules();
    }

    /**
     * Demonstrates why preorder traversal without null markers is insufficient
     * for unique tree reconstruction
     */
    public void demonstrateNonUniquePreorder() {
        System.out.println("Demonstrating Non-Unique Preorder Traversal:");
        System.out.println("--------------------------------------------");

        // Create two different trees with the same preorder traversal [1,2,3,4,5]
        // Tree 1:       Tree 2:
        //     1             1
        //    / \             \
        //   2   5             2
        //  / \                 \
        // 3   4                 3
        //                        \
        //                         4
        //                          \
        //                           5

        // Tree 1
        TreeNode tree1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4)),
                new TreeNode(5));

        // Tree 2
        TreeNode tree2 = new TreeNode(1,
                null,
                new TreeNode(2,
                        null,
                        new TreeNode(3,
                                null,
                                new TreeNode(4,
                                        null,
                                        new TreeNode(5)))));

        // Print preorder traversal for both trees
        System.out.println("Tree 1 Preorder: [1,2,3,4,5]");
        System.out.println("Tree 2 Preorder: [1,2,3,4,5]");
        System.out.println("Same preorder traversal, but different tree structures!");
        System.out.println();

        // Print preorder traversal with null markers for both trees
        System.out.println("Tree 1 Preorder with null markers: [1,2,3,#,#,4,#,#,5,#,#]");
        System.out.println("Tree 2 Preorder with null markers: [1,#,2,#,3,#,4,#,5,#,#]");
        System.out.println("With null markers, the preorder traversals are different!");
    }

    /**
     * Demonstrates why inorder traversal with null markers is still insufficient
     * for unique tree reconstruction
     */
    public void demonstrateNonUniqueInorder() {
        System.out.println("Demonstrating Non-Unique Inorder Traversal (even with null markers):");
        System.out.println("----------------------------------------------------------------");

        // Create two different trees with the same inorder traversal [#,1,#,1,#]
        // Tree 1:    Tree 2:
        //   1          1
        //  /            \
        // 1              1

        // Tree 1
        TreeNode tree1 = new TreeNode(1,
                new TreeNode(1),
                null);

        // Tree 2
        TreeNode tree2 = new TreeNode(1,
                null,
                new TreeNode(1));

        // Print inorder traversal with null markers for both trees
        System.out.println("Tree 1 Inorder with null markers: [#,1,#,1,#]");
        System.out.println("Tree 2 Inorder with null markers: [#,1,#,1,#]");
        System.out.println("Same inorder traversal with null markers, but different tree structures!");
        System.out.println("This is because inorder traversal doesn't identify the root position.");
    }

    /**
     * Summarizes the uniqueness criteria for binary tree reconstruction
     */
    public void summarizeUniquenessRules() {
        System.out.println("Binary Tree Reconstruction Uniqueness Rules:");
        System.out.println("-------------------------------------------");
        System.out.println("1. Without null pointer information and with only one traversal:");
        System.out.println("   - Cannot uniquely reconstruct a binary tree");
        System.out.println();

        System.out.println("2. Without null pointer information but with two traversals:");
        System.out.println("   a) Preorder + Inorder: Can uniquely reconstruct a binary tree");
        System.out.println("   b) Postorder + Inorder: Can uniquely reconstruct a binary tree");
        System.out.println("   c) Preorder + Postorder: Cannot uniquely reconstruct a binary tree");
        System.out.println();

        System.out.println("3. With null pointer information and with one traversal:");
        System.out.println("   a) Preorder: Can uniquely reconstruct a binary tree");
        System.out.println("   b) Postorder: Can uniquely reconstruct a binary tree");
        System.out.println("   c) Inorder: Cannot uniquely reconstruct a binary tree");
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
