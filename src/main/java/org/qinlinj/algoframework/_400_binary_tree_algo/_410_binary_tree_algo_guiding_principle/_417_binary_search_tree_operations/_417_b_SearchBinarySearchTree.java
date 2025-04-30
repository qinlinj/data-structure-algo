package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._417_binary_search_tree_operations;

/**
 * SEARCH IN A BINARY SEARCH TREE
 * =============================
 * <p>
 * This class implements the solution to LeetCode Problem 700:
 * "Search in a Binary Search Tree"
 * <p>
 * Problem Description:
 * Given the root node of a binary search tree (BST) and a value to search for,
 * return the subtree rooted at that value. If the value doesn't exist in the BST, return null.
 * <p>
 * Key Insights:
 * <p>
 * 1. Regular Binary Tree Search vs. BST Search:
 * - In a regular binary tree, we would need to explore all possible paths
 * (potentially the entire tree) to find a value
 * - In a BST, we can leverage the "left smaller, right larger" property
 * to eliminate half of the remaining tree at each step
 * <p>
 * 2. BST Search Algorithm:
 * - If current node's value equals target, return the node
 * - If target is less than current node's value, search in left subtree
 * - If target is greater than current node's value, search in right subtree
 * - If we reach a null node, the target doesn't exist in the tree
 * <p>
 * 3. Time and Space Complexity:
 * - Time: O(h) where h is the height of the tree
 * - Best case (balanced tree): O(log n)
 * - Worst case (skewed tree): O(n)
 * - Space: O(h) for the recursion stack
 * <p>
 * This implementation demonstrates the efficiency advantage of a BST
 * over a regular binary tree for search operations.
 */

public class _417_b_SearchBinarySearchTree {

    /**
     * Creates a sample BST for testing
     */
    public static TreeNode createSampleBST() {
        // Create a BST:
        //       8
        //      / \
        //     3   10
        //    / \    \
        //   1   6    14
        //      / \   /
        //     4   7 13
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

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
        _417_b_SearchBinarySearchTree searcher = new _417_b_SearchBinarySearchTree();

        // Create a sample BST
        TreeNode root = createSampleBST();

        System.out.println("SEARCH IN BINARY SEARCH TREE");
        System.out.println("===========================");

        // Print the BST
        System.out.println("\nSample BST:");
        printBST(root);

        // Test searching for various values
        int[] targets = {6, 13, 5};
        for (int target : targets) {
            System.out.println("\nSearching for value: " + target);

            // Run the BST search
            TreeNode result = searcher.searchBST(root, target);

            if (result != null) {
                System.out.println("Found! Subtree rooted at " + target + ":");
                printBST(result);
            } else {
                System.out.println("Value " + target + " not found in the BST.");
            }
        }

        // Trace a search execution
        System.out.println("\nDetailed Search Trace:");
        searcher.traceSearch(root, 13);

        // Compare efficiency
        searcher.compareEfficiency();
    }

    /**
     * Search in a regular binary tree (inefficient for BST)
     * This is a general approach that works for any binary tree
     */
    public TreeNode searchRegularBinaryTree(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        // Check current node
        if (root.val == target) {
            return root;
        }

        // Search in both left and right subtrees
        TreeNode leftResult = searchRegularBinaryTree(root.left, target);
        if (leftResult != null) {
            return leftResult;
        }

        TreeNode rightResult = searchRegularBinaryTree(root.right, target);
        return rightResult;
    }

    /**
     * Efficient search in a binary search tree
     * Leverages the BST property to perform a binary search
     */
    public TreeNode searchBST(TreeNode root, int target) {
        // Base case: empty tree or found target
        if (root == null || root.val == target) {
            return root;
        }

        // If target is smaller than current node, search in left subtree
        if (target < root.val) {
            return searchBST(root.left, target);
        }

        // If target is larger than current node, search in right subtree
        return searchBST(root.right, target);
    }

    /**
     * Iterative version of BST search (eliminates recursion stack overhead)
     */
    public TreeNode searchBSTIterative(TreeNode root, int target) {
        // Start from the root
        TreeNode current = root;

        // Traverse until we find the target or reach a leaf
        while (current != null && current.val != target) {
            if (target < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return current; // Either null (not found) or the target node
    }

    /**
     * Trace the execution of BST search for debugging and visualization
     */
    public void traceSearch(TreeNode root, int target) {
        System.out.println("Tracing BST search for target value: " + target);
        System.out.println("----------------------------------------");

        traceSearchHelper(root, target, 0);
    }

    private TreeNode traceSearchHelper(TreeNode root, int target, int depth) {
        String indent = "  ".repeat(depth);

        // Base case
        if (root == null) {
            System.out.println(indent + "Reached null node. Target not found!");
            return null;
        }

        // Current node
        System.out.println(indent + "Checking node with value: " + root.val);

        // Found target
        if (root.val == target) {
            System.out.println(indent + "✓ Found target value " + target + "!");
            return root;
        }

        // Decide which subtree to search
        if (target < root.val) {
            System.out.println(indent + "Target " + target + " < " + root.val + ", going to left subtree...");
            return traceSearchHelper(root.left, target, depth + 1);
        } else {
            System.out.println(indent + "Target " + target + " > " + root.val + ", going to right subtree...");
            return traceSearchHelper(root.right, target, depth + 1);
        }
    }

    /**
     * Compare the efficiency of regular binary tree search vs BST search
     */
    public void compareEfficiency() {
        System.out.println("\nCOMPARING SEARCH EFFICIENCIES");
        System.out.println("============================");

        System.out.println("1. Regular Binary Tree Search:");
        System.out.println("   - Must potentially explore all nodes");
        System.out.println("   - Time Complexity: O(n) where n is the number of nodes");
        System.out.println("   - For a tree with 1,000 nodes, may need to check all 1,000 nodes");

        System.out.println("\n2. Binary Search Tree Search:");
        System.out.println("   - Eliminates half of remaining nodes at each step");
        System.out.println("   - Time Complexity: O(h) where h is the height of the tree");
        System.out.println("   - For a balanced tree with 1,000 nodes, only need ~10 comparisons");

        System.out.println("\nDemonstration with Path Lengths:");
        System.out.println("In our sample tree, to find value 13:");
        System.out.println("- Regular search might check many nodes before finding 13");
        System.out.println("- BST search follows the direct path: 8 → 10 → 14 → 13 (only 4 nodes)");

        System.out.println("\nThis efficiency gap becomes even more significant as the tree size increases!");
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
