package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._417_binary_search_tree_operations;

/**
 * INSERT INTO A BINARY SEARCH TREE
 * ===============================
 * <p>
 * This class implements the solution to LeetCode Problem 701:
 * "Insert into a Binary Search Tree"
 * <p>
 * Problem Description:
 * Given the root node of a binary search tree (BST) and a value to insert,
 * insert the value into the BST. Return the root node after the insertion.
 * The input will be guaranteed to have a unique value to insert.
 * <p>
 * Key Insights:
 * <p>
 * 1. BST Insert Algorithm:
 * - If tree is empty, create a new node with the value and return it
 * - If value is less than current node's value, insert in left subtree
 * - If value is greater than current node's value, insert in right subtree
 * - Return the modified tree after insertion
 * <p>
 * 2. Implementation Pattern:
 * - Like most tree modifications, we need to process the return values
 * of recursive calls to update parent-child links
 * - The modified subtree is returned and assigned to the appropriate
 * child pointer (either left or right)
 * <p>
 * 3. Key Understanding:
 * - BST insertion always occurs at a leaf node
 * - The search path is determined by BST property (left smaller, right larger)
 * - There are often multiple valid ways to insert a value while maintaining BST property
 * <p>
 * 4. Time and Space Complexity:
 * - Time: O(h) where h is the height of the tree
 * - Space: O(h) for the recursion stack
 */

public class _417_c_InsertBinarySearchTree {

    /**
     * Creates a sample BST for testing
     */
    public static TreeNode createSampleBST() {
        // Create BST structure:
        //      5
        //     / \
        //    3   7
        //   / \   \
        //  2   4   8
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(8);

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

    public static void main(String[] args) {
        _417_c_InsertBinarySearchTree inserter = new _417_c_InsertBinarySearchTree();

        // Create a sample BST
        TreeNode root = createSampleBST();

        System.out.println("INSERT INTO BINARY SEARCH TREE");
        System.out.println("=============================");

        // Print the original BST
        System.out.println("\nOriginal BST:");
        printBST(root);

        // Insert values and show the modified tree
        int[] valuesToInsert = {1, 6, 9};
        for (int val : valuesToInsert) {
            System.out.println("\nInserting value: " + val);

            // Create a deep copy of the tree to avoid modifying the original for each example
            TreeNode treeCopy = cloneTree(root);

            // Insert the value
            treeCopy = inserter.insertIntoBST(treeCopy, val);

            // Show the modified tree
            System.out.println("BST after insertion:");
            printBST(treeCopy);
        }

        // Trace an insertion
        System.out.println("\nDetailed Insertion Trace:");
        TreeNode tracedTree = cloneTree(root);
        tracedTree = inserter.traceInsertion(tracedTree, 6);

        // Additional explanations
        inserter.explainReturnValueHandling();
        inserter.demonstrateMultipleValidInsertions();
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

    /**
     * Insert a value into a BST
     * Returns the root of the modified tree
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Base case: empty tree or reached insertion point
        if (root == null) {
            return new TreeNode(val);
        }

        // If value is less than current node, insert in left subtree
        if (val < root.val) {
            // Important: update the left child with the result of the recursive call
            root.left = insertIntoBST(root.left, val);
        }
        // If value is greater than current node, insert in right subtree
        else if (val > root.val) {
            // Important: update the right child with the result of the recursive call
            root.right = insertIntoBST(root.right, val);
        }
        // Note: We don't handle the case where val == root.val because
        // the problem guarantees unique values

        // Return the (potentially modified) root of this subtree
        return root;
    }

    /**
     * Iterative version of BST insertion
     */
    public TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        // Special case: empty tree
        if (root == null) {
            return new TreeNode(val);
        }

        // Start at the root
        TreeNode current = root;

        // Traverse until we find the insertion point
        while (true) {
            if (val < current.val) {
                // Go left
                if (current.left == null) {
                    // Found insertion point
                    current.left = new TreeNode(val);
                    break;
                }
                current = current.left;
            } else {
                // Go right
                if (current.right == null) {
                    // Found insertion point
                    current.right = new TreeNode(val);
                    break;
                }
                current = current.right;
            }
        }

        // Return the root (which hasn't changed)
        return root;
    }

    /**
     * Trace the execution of BST insertion for debugging and visualization
     */
    public TreeNode traceInsertion(TreeNode root, int val) {
        System.out.println("Tracing BST insertion for value: " + val);
        System.out.println("----------------------------------");

        return traceInsertionHelper(root, val, 0);
    }

    private TreeNode traceInsertionHelper(TreeNode root, int val, int depth) {
        String indent = "  ".repeat(depth);

        // Base case: reached leaf node
        if (root == null) {
            System.out.println(indent + "Reached null node - inserting new node with value " + val);
            return new TreeNode(val);
        }

        System.out.println(indent + "Checking node with value: " + root.val);

        if (val < root.val) {
            System.out.println(indent + "Value " + val + " < " + root.val + ", going to left subtree...");
            // Recurse left
            root.left = traceInsertionHelper(root.left, val, depth + 1);
            System.out.println(indent + "Returning from left subtree insertion");
        } else {
            System.out.println(indent + "Value " + val + " > " + root.val + ", going to right subtree...");
            // Recurse right
            root.right = traceInsertionHelper(root.right, val, depth + 1);
            System.out.println(indent + "Returning from right subtree insertion");
        }

        System.out.println(indent + "Returning node " + root.val + " after insertion");
        return root;
    }

    /**
     * Visually explain how to handle return values in tree modifications
     */
    public void explainReturnValueHandling() {
        System.out.println("\nHANDLING RETURN VALUES IN TREE MODIFICATIONS");
        System.out.println("===========================================");

        System.out.println("When modifying trees, we need to maintain parent-child relationships.");
        System.out.println("For insertions, each recursive call returns the root of the modified subtree.");

        System.out.println("\nHere's why this pattern is necessary:");
        System.out.println("1. When we reach a null node, we create and return a new node");
        System.out.println("2. The parent needs to update its child pointer to this new node");
        System.out.println("3. We accomplish this by assigning the result of the recursive call back to the child pointer");

        System.out.println("\nWithout this pattern, inserted nodes would be created but never connected to the tree!");

        System.out.println("\nThe key line in our implementation:");
        System.out.println("  root.left = insertIntoBST(root.left, val);");
        System.out.println("This updates the left child pointer with whatever the recursive call returns");
        System.out.println("(either the original subtree root or a newly created node)");
    }

    /**
     * Demonstrate multiple valid insertion points
     */
    public void demonstrateMultipleValidInsertions() {
        System.out.println("\nMULTIPLE VALID INSERTION POINTS");
        System.out.println("=============================");

        System.out.println("For BST insertion, there are often multiple valid places to insert a new value");
        System.out.println("while maintaining the BST property.");

        System.out.println("\nConsider inserting 6 into this BST:");
        TreeNode bst1 = createSampleBST();
        printBST(bst1);

        System.out.println("\nValid insertion option 1:");
        System.out.println("Insert 6 as the left child of 7");

        System.out.println("\nValid insertion option 2:");
        System.out.println("Insert 6 as the right child of 5 (and move the current right subtree)");

        System.out.println("\nBoth resulting trees would be valid BSTs!");
        System.out.println("The standard algorithm always chooses the first option (follows the BST property).");
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
