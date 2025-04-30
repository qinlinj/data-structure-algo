package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._417_binary_search_tree_operations;

/**
 * BINARY SEARCH TREE OPERATIONS SUMMARY
 * ====================================
 * <p>
 * This class provides a comprehensive summary of fundamental Binary Search Tree (BST)
 * operations, including:
 * 1. Validation - Verifying a tree is a valid BST
 * 2. Search - Finding a node with a specific value
 * 3. Insertion - Adding a new node while maintaining BST properties
 * 4. Deletion - Removing a node while maintaining BST properties
 * <p>
 * Key BST Principles:
 * <p>
 * 1. BST Property:
 * - Every node's left subtree contains only values less than the node's value
 * - Every node's right subtree contains only values greater than the node's value
 * - Both left and right subtrees must also be valid BSTs
 * <p>
 * 2. Operation Patterns:
 * - Most BST operations follow a similar recursive pattern
 * - They use the BST property to navigate the tree efficiently
 * - Tree modifications require handling return values to maintain parent-child relationships
 * <p>
 * 3. Time and Space Complexity:
 * - All operations have O(h) time complexity, where h is the height of the tree
 * - For a balanced tree: O(log n) time complexity
 * - For a skewed tree: O(n) time complexity
 * - Space complexity is O(h) for the recursion stack
 * <p>
 * This summary class implements all core BST operations and provides visual examples
 * and explanations to facilitate understanding.
 */

import java.util.*;

public class _417_e_BinarySearchTreeOperationsSummary {

    /**
     * UTILITY: Print the BST structure
     */
    public static void printBST(TreeNode root) {
        printBST(root, 0);
    }

    private static void printBST(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        // Print right subtree first
        printBST(node.right, level + 1);

        // Print current node with indentation
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.val);

        // Print left subtree
        printBST(node.left, level + 1);
    }

    /**
     * Create a sample BST for demonstration
     */
    public static TreeNode createSampleBST() {
        // Create a BST:
        //       5
        //      / \
        //     3   7
        //    / \ / \
        //   2  4 6  8
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        return root;
    }

    /**
     * Helper method to create a deep copy of a BST
     */
    private static TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = cloneTree(root.left);
        newRoot.right = cloneTree(root.right);

        return newRoot;
    }

    public static void main(String[] args) {
        _417_e_BinarySearchTreeOperationsSummary bstOps = new _417_e_BinarySearchTreeOperationsSummary();

        System.out.println("BINARY SEARCH TREE OPERATIONS SUMMARY");
        System.out.println("====================================");

        bstOps.demonstrateAllOperations();
        bstOps.explainOperationPatterns();
        bstOps.explainComplexity();
        bstOps.explainCommonMistakes();
        bstOps.discussAdvancedTopics();

        System.out.println("\nCONCLUSION");
        System.out.println("==========");
        System.out.println("Binary Search Trees are fundamental data structures that support efficient");
        System.out.println("searching, insertion, and deletion operations. The BST property (left smaller,");
        System.out.println("right larger) enables binary-search-like navigation, making BSTs suitable for");
        System.out.println("ordered data storage and retrieval.");
        System.out.println();
        System.out.println("While basic BST operations have O(h) time complexity, maintaining balance");
        System.out.println("through techniques like AVL or Red-Black trees ensures O(log n) performance");
        System.out.println("even in worst-case scenarios, making balanced BSTs one of the most");
        System.out.println("versatile and widely used data structures in computer science.");
    }

    /**
     * OPERATION 1: VALIDATE BST
     * Checks if a given binary tree is a valid BST
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private boolean isValidBSTHelper(TreeNode root, TreeNode min, TreeNode max) {
        // Base case: empty tree is valid
        if (root == null) {
            return true;
        }

        // Check if current node violates constraints
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }

        // Recursively check subtrees with updated constraints
        return isValidBSTHelper(root.left, min, root) &&
                isValidBSTHelper(root.right, root, max);
    }

    /**
     * OPERATION 2: SEARCH IN BST
     * Finds and returns the node with the given value, or null if not found
     */
    public TreeNode search(TreeNode root, int val) {
        // Base case: empty tree or found the target
        if (root == null || root.val == val) {
            return root;
        }

        // Use BST property to decide which subtree to search
        if (val < root.val) {
            return search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }

    /**
     * OPERATION 3: INSERT INTO BST
     * Inserts a new value into the BST and returns the modified tree
     */
    public TreeNode insert(TreeNode root, int val) {
        // Base case: found insertion point
        if (root == null) {
            return new TreeNode(val);
        }

        // Recursively insert into appropriate subtree
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }
        // If val == root.val, we typically don't insert duplicates in BST

        return root;
    }

    /**
     * OPERATION 4: DELETE FROM BST
     * Removes the node with the given value and returns the modified tree
     */
    public TreeNode delete(TreeNode root, int val) {
        // Base case: empty tree
        if (root == null) {
            return null;
        }

        // Find the node to delete
        if (val < root.val) {
            root.left = delete(root.left, val);
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else {
            // Found the node to delete

            // Case 1 & 2: Node has 0 or 1 child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has 2 children
            // Find successor (smallest node in right subtree)
            TreeNode successor = findMin(root.right);
            root.val = successor.val;  // Replace value with successor

            // Delete successor (which is now a duplicate)
            root.right = delete(root.right, successor.val);
        }

        return root;
    }

    /**
     * Helper method to find the minimum value node in a BST
     */
    private TreeNode findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * UTILITY: Inorder Traversal
     * Performs an inorder traversal of the BST (resulting in sorted values)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorderHelper(root.left, result);
        result.add(root.val);
        inorderHelper(root.right, result);
    }

    /**
     * BST operation patterns and common code structures
     */
    public void explainOperationPatterns() {
        System.out.println("BST OPERATION PATTERNS");
        System.out.println("=====================");

        System.out.println("All BST operations follow a similar pattern:");
        System.out.println("1. Base case: empty tree or found target node");
        System.out.println("2. Compare current node's value with target value");
        System.out.println("3. Recursively process left or right subtree based on comparison");
        System.out.println("4. For modifications (insert, delete), update child pointers with recursion results");

        System.out.println("\nCommon Code Structure:");
        System.out.println("```java");
        System.out.println("TreeNode operation(TreeNode root, int target) {");
        System.out.println("    // Base case");
        System.out.println("    if (root == null) return ...;");
        System.out.println();
        System.out.println("    // Compare and decide which subtree to process");
        System.out.println("    if (target < root.val) {");
        System.out.println("        root.left = operation(root.left, target);");
        System.out.println("    } else if (target > root.val) {");
        System.out.println("        root.right = operation(root.right, target);");
        System.out.println("    } else {");
        System.out.println("        // Found target node, perform operation");
        System.out.println("        // ...");
        System.out.println("    }");
        System.out.println();
        System.out.println("    return root;");
        System.out.println("}");
        System.out.println("```");
    }

    /**
     * Summary of time and space complexity
     */
    public void explainComplexity() {
        System.out.println("\nTIME AND SPACE COMPLEXITY");
        System.out.println("=========================");

        System.out.println("BST Operations Complexity:");
        System.out.println("- Search: O(h) time");
        System.out.println("- Insert: O(h) time");
        System.out.println("- Delete: O(h) time");
        System.out.println("- Validate: O(n) time (must visit all nodes)");
        System.out.println("Where h is the height of the tree");

        System.out.println("\nBest Case (Balanced BST):");
        System.out.println("- Height h = log₂(n)");
        System.out.println("- All operations take O(log n) time");

        System.out.println("\nWorst Case (Skewed BST, essentially a linked list):");
        System.out.println("- Height h = n");
        System.out.println("- All operations degrade to O(n) time");

        System.out.println("\nSpace Complexity:");
        System.out.println("- All recursive operations: O(h) space for the recursion stack");
        System.out.println("- Iterative versions can achieve O(1) space");
    }

    /**
     * Common mistakes and traps in BST implementations
     */
    public void explainCommonMistakes() {
        System.out.println("\nCOMMON MISTAKES IN BST IMPLEMENTATIONS");
        System.out.println("====================================");

        System.out.println("1. Validation Mistake:");
        System.out.println("   - Only checking immediate children instead of entire subtrees");
        System.out.println("   - Correct approach: Pass down min/max constraints");

        System.out.println("\n2. Return Value Handling:");
        System.out.println("   - Forgetting to update child pointers with recursion results");
        System.out.println("   - Example: root.left = insert(root.left, val);");

        System.out.println("\n3. Deletion Case 3 (Two Children):");
        System.out.println("   - Mishandling the successor replacement");
        System.out.println("   - Forgetting to delete the successor after replacing values");

        System.out.println("\n4. Edge Cases:");
        System.out.println("   - Empty trees (null root)");
        System.out.println("   - Deleting the root node");
        System.out.println("   - Handling duplicates (depends on BST policy)");
    }

    /**
     * BST advanced topics and extensions
     */
    public void discussAdvancedTopics() {
        System.out.println("\nADVANCED BST TOPICS");
        System.out.println("==================");

        System.out.println("1. Self-Balancing BSTs:");
        System.out.println("   - AVL Trees: Height difference between subtrees ≤ 1");
        System.out.println("   - Red-Black Trees: Used in most language standard libraries");
        System.out.println("   - B-Trees: Used in databases and file systems");

        System.out.println("\n2. BST Augmentation:");
        System.out.println("   - Size-augmented BST for order statistics (kth smallest element)");
        System.out.println("   - Sum-augmented BST for range sum queries");

        System.out.println("\n3. BST Traversal Variations:");
        System.out.println("   - Morris Traversal: O(1) space inorder traversal without recursion");
        System.out.println("   - Iterator pattern: Lazy evaluation of the next element");

        System.out.println("\n4. Tree Rotations:");
        System.out.println("   - Left and right rotations to rebalance trees");
        System.out.println("   - Foundation for self-balancing tree implementations");
    }

    /**
     * Demonstrate all operations on a sample BST
     */
    public void demonstrateAllOperations() {
        System.out.println("\nDEMONSTRATION OF ALL BST OPERATIONS");
        System.out.println("=================================");

        // Create a sample BST
        TreeNode root = createSampleBST();
        System.out.println("Original BST:");
        printBST(root);

        // Validation
        System.out.println("\n1. Validation:");
        System.out.println("Is valid BST? " + isValidBST(root));

        // Search
        System.out.println("\n2. Search:");
        int searchVal = 6;
        TreeNode foundNode = search(root, searchVal);
        System.out.println("Searching for " + searchVal + ": " +
                (foundNode != null ? "Found" : "Not found"));

        // Insertion
        System.out.println("\n3. Insertion:");
        int insertVal = 9;
        System.out.println("Inserting " + insertVal);
        TreeNode afterInsert = insert(cloneTree(root), insertVal);
        printBST(afterInsert);

        // Deletion - leaf node
        System.out.println("\n4. Deletion (leaf node):");
        int deleteLeaf = 8;
        System.out.println("Deleting " + deleteLeaf);
        TreeNode afterDeleteLeaf = delete(cloneTree(root), deleteLeaf);
        printBST(afterDeleteLeaf);

        // Deletion - node with one child
        System.out.println("\n5. Deletion (node with one child):");
        int deleteOneChild = 7;
        System.out.println("Deleting " + deleteOneChild);
        TreeNode afterDeleteOneChild = delete(cloneTree(root), deleteOneChild);
        printBST(afterDeleteOneChild);

        // Deletion - node with two children
        System.out.println("\n6. Deletion (node with two children):");
        int deleteTwoChildren = 3;
        System.out.println("Deleting " + deleteTwoChildren);
        TreeNode afterDeleteTwoChildren = delete(cloneTree(root), deleteTwoChildren);
        printBST(afterDeleteTwoChildren);

        // Inorder traversal
        System.out.println("\n7. Inorder Traversal (values in sorted order):");
        List<Integer> inorderResult = inorderTraversal(root);
        System.out.println(inorderResult);
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
