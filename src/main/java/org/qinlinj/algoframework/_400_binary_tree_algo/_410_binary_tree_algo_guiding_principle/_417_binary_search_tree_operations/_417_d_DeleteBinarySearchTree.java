package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._417_binary_search_tree_operations;

/**
 * DELETE NODE IN A BINARY SEARCH TREE
 * ==================================
 * <p>
 * This class implements the solution to LeetCode Problem 450:
 * "Delete Node in a Binary Search Tree"
 * <p>
 * Problem Description:
 * Given the root of a binary search tree and a key value, delete the node with the given key
 * and return the root of the modified tree. If the key doesn't exist in the tree, return the tree unchanged.
 * <p>
 * Key Insights:
 * <p>
 * 1. BST Deletion Algorithm has Three Cases:
 * a) Node to delete is a leaf (no children) - Simply remove it
 * b) Node to delete has only one child - Replace with its child
 * c) Node to delete has two children - Replace with successor or predecessor
 * (smallest node in right subtree or largest in left subtree)
 * <p>
 * 2. Deletion Process:
 * - First, find the node to delete using BST search properties
 * - Then, handle the deletion based on which case applies
 * - Maintain BST properties throughout the process
 * <p>
 * 3. Implementation Details:
 * - For case (c), we replace the node's value with its successor's value
 * and then delete the successor node (which is guaranteed to have at most one child)
 * - Like insertion, we need to handle return values carefully to maintain
 * parent-child relationships
 * <p>
 * 4. Time and Space Complexity:
 * - Time: O(h) where h is the height of the tree
 * - Space: O(h) for the recursion stack
 * <p>
 * This implementation demonstrates the most complex of the basic BST operations
 * and shows how to maintain BST properties during structural modifications.
 */

public class _417_d_DeleteBinarySearchTree {

    /**
     * Creates a sample BST for testing
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
        _417_d_DeleteBinarySearchTree deleter = new _417_d_DeleteBinarySearchTree();

        // Create a sample BST
        TreeNode root = createSampleBST();

        System.out.println("DELETE NODE IN BINARY SEARCH TREE");
        System.out.println("===============================");

        // Print the original BST
        System.out.println("\nOriginal BST:");
        printBST(root);

        // Delete nodes and show the modified tree
        int[] keysToDelete = {2, 3, 5};
        for (int key : keysToDelete) {
            System.out.println("\nDeleting key: " + key);

            // Create a deep copy of the tree to avoid modifying the original for each example
            TreeNode treeCopy = cloneTree(root);

            // Delete the node
            treeCopy = deleter.deleteNode(treeCopy, key);

            // Show the modified tree
            System.out.println("BST after deletion:");
            printBST(treeCopy);
        }

        // Trace a deletion with explanation
        System.out.println("\nDetailed Deletion Trace:");
        TreeNode tracedTree = cloneTree(root);
        tracedTree = deleter.traceDelete(tracedTree, 5);

        // Additional explanations
        deleter.explainDeletionCases();
        deleter.explainSuccessorVsPredecessor();
        deleter.explainValuesVsPointers();
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
     * Delete a node with the given key from the BST
     * Returns the root of the modified tree
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        // Base case: empty tree or key not found
        if (root == null) {
            return null;
        }

        // Search for the node to delete
        if (key < root.val) {
            // Key is in the left subtree
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            // Key is in the right subtree
            root.right = deleteNode(root.right, key);
        } else {
            // Found the node to delete (root.val == key)

            // Case 1 & 2: Node has 0 or 1 child
            if (root.left == null) {
                return root.right;  // Replace with right child (or null if no children)
            } else if (root.right == null) {
                return root.left;   // Replace with left child
            }

            // Case 3: Node has 2 children
            // Find the successor (smallest node in right subtree)
            TreeNode successor = findMin(root.right);

            // Replace current node's value with successor's value
            root.val = successor.val;

            // Delete the successor (which is now a duplicate)
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }

    /**
     * Find the node with the minimum value in a BST
     * This is the leftmost node
     */
    private TreeNode findMin(TreeNode node) {
        // Keep going left until we reach a leaf
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Alternative version that uses the predecessor (largest node in left subtree)
     * instead of the successor
     */
    public TreeNode deleteNodeUsingPredecessor(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNodeUsingPredecessor(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNodeUsingPredecessor(root.right, key);
        } else {
            // Found the node to delete

            // Case 1 & 2: Node has 0 or 1 child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has 2 children
            // Find the predecessor (largest node in left subtree)
            TreeNode predecessor = findMax(root.left);

            // Replace current node's value with predecessor's value
            root.val = predecessor.val;

            // Delete the predecessor (which is now a duplicate)
            root.left = deleteNodeUsingPredecessor(root.left, predecessor.val);
        }

        return root;
    }

    /**
     * Find the node with the maximum value in a BST
     * This is the rightmost node
     */
    private TreeNode findMax(TreeNode node) {
        // Keep going right until we reach a leaf
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * Trace the execution of BST deletion for debugging and visualization
     */
    public TreeNode traceDelete(TreeNode root, int key) {
        System.out.println("Tracing BST deletion for key: " + key);
        System.out.println("--------------------------------");

        return traceDeleteHelper(root, key, 0);
    }

    private TreeNode traceDeleteHelper(TreeNode root, int key, int depth) {
        String indent = "  ".repeat(depth);

        // Base case: empty tree or key not found
        if (root == null) {
            System.out.println(indent + "Reached null node. Key not found!");
            return null;
        }

        System.out.println(indent + "Checking node with value: " + root.val);

        if (key < root.val) {
            System.out.println(indent + "Key " + key + " < " + root.val + ", going to left subtree...");
            root.left = traceDeleteHelper(root.left, key, depth + 1);
            System.out.println(indent + "Returning from left subtree deletion");
        } else if (key > root.val) {
            System.out.println(indent + "Key " + key + " > " + root.val + ", going to right subtree...");
            root.right = traceDeleteHelper(root.right, key, depth + 1);
            System.out.println(indent + "Returning from right subtree deletion");
        } else {
            // Found the node to delete
            System.out.println(indent + "✓ Found node to delete: " + root.val);

            if (root.left == null && root.right == null) {
                System.out.println(indent + "Case 1: Node has no children. Simply remove it.");
                return null;
            } else if (root.left == null) {
                System.out.println(indent + "Case 2: Node has only right child. Replace with right child.");
                return root.right;
            } else if (root.right == null) {
                System.out.println(indent + "Case 2: Node has only left child. Replace with left child.");
                return root.left;
            } else {
                System.out.println(indent + "Case 3: Node has two children.");
                System.out.println(indent + "Finding successor (smallest node in right subtree)...");

                // Find the successor
                TreeNode successor = findMin(root.right);
                System.out.println(indent + "Successor is: " + successor.val);

                // Replace current node's value with successor's value
                System.out.println(indent + "Replacing " + root.val + " with " + successor.val);
                root.val = successor.val;

                // Delete the successor
                System.out.println(indent + "Now deleting successor node (duplicate " + successor.val + ") from right subtree...");
                root.right = traceDeleteHelper(root.right, successor.val, depth + 1);
                System.out.println(indent + "Successor deleted");
            }
        }

        System.out.println(indent + "Returning node " + root.val + " after deletion");
        return root;
    }

    /**
     * Explain the three deletion cases with visual examples
     */
    public void explainDeletionCases() {
        System.out.println("\nTHE THREE CASES OF BST DELETION");
        System.out.println("==============================");

        System.out.println("Case 1: Node to delete is a leaf (no children)");
        System.out.println("-------------------------------------------");
        System.out.println("Example: Deleting 2 from this tree:");
        System.out.println("    5        5");
        System.out.println("   / \\      / \\");
        System.out.println("  3   7 →  3   7");
        System.out.println(" / \\         \\");
        System.out.println("2   4         4");
        System.out.println("Solution: Simply remove the node");

        System.out.println("\nCase 2: Node to delete has one child");
        System.out.println("-----------------------------------");
        System.out.println("Example: Deleting 3 from this tree:");
        System.out.println("    5        5");
        System.out.println("   / \\      / \\");
        System.out.println("  3   7 →  4   7");
        System.out.println("   \\");
        System.out.println("    4");
        System.out.println("Solution: Replace the node with its child");

        System.out.println("\nCase 3: Node to delete has two children");
        System.out.println("--------------------------------------");
        System.out.println("Example: Deleting 5 (the root) from this tree:");
        System.out.println("    5        6");
        System.out.println("   / \\      / \\");
        System.out.println("  3   7 →  3   7");
        System.out.println(" / \\ /    / \\");
        System.out.println("2  4 6   2  4");
        System.out.println("Solution:");
        System.out.println("1. Find successor (smallest node in right subtree, which is 6)");
        System.out.println("2. Replace node's value with successor's value");
        System.out.println("3. Delete the successor node (which now becomes a simpler case)");
    }

    /**
     * Successor vs. Predecessor for deletion
     */
    public void explainSuccessorVsPredecessor() {
        System.out.println("\nSUCCESSOR VS. PREDECESSOR FOR DELETION");
        System.out.println("=====================================");

        System.out.println("For the case where a node has two children, we have two options:");
        System.out.println("1. Replace with successor (smallest node in right subtree)");
        System.out.println("2. Replace with predecessor (largest node in left subtree)");

        System.out.println("\nBoth approaches are valid and maintain BST properties!");
        System.out.println("The choice between them is often arbitrary or based on tree balance considerations.");

        System.out.println("\nSuccessor approach (shown in our implementation):");
        System.out.println("- Finds the leftmost node in the right subtree");
        System.out.println("- This node has the smallest value that is still greater than all nodes in the left subtree");

        System.out.println("\nPredecessor approach (alternative implementation):");
        System.out.println("- Finds the rightmost node in the left subtree");
        System.out.println("- This node has the largest value that is still less than all nodes in the right subtree");
    }

    /**
     * Special note about values vs. pointers approach
     */
    public void explainValuesVsPointers() {
        System.out.println("\nVALUES VS. POINTERS APPROACH");
        System.out.println("===========================");

        System.out.println("In our implementation, we use the 'values' approach for Case 3:");
        System.out.println("1. We copy the successor's value to the node we want to delete");
        System.out.println("2. Then we delete the successor node itself");

        System.out.println("\nAn alternative 'pointers' approach would be:");
        System.out.println("1. Physically remove the node by adjusting pointers");
        System.out.println("2. Directly connect the predecessor to the successor");

        System.out.println("\nThe 'values' approach is simpler to implement in most languages");
        System.out.println("and works well for interview settings.");

        System.out.println("\nHowever, in practice, the 'pointers' approach might be preferred when:");
        System.out.println("- Nodes contain complex data beyond simple values");
        System.out.println("- The language/framework encourages immutability");
        System.out.println("- You want to minimize data copying in performance-critical applications");
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
