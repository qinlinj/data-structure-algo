package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._416_binary_search_tree_properties;

/**
 * KTH SMALLEST ELEMENT IN A BINARY SEARCH TREE
 * ===========================================
 * <p>
 * This class implements the solution to LeetCode Problem 230:
 * "Find the kth smallest element in a Binary Search Tree (BST)."
 * <p>
 * Problem Description:
 * Given a binary search tree root and a positive integer k, return the kth
 * smallest element in the BST (considering 1-based indexing).
 * <p>
 * Key Insights:
 * <p>
 * 1. BST Inorder Traversal Property:
 * - Inorder traversal of a BST produces nodes in ascending order
 * - We can leverage this to find the kth smallest element efficiently
 * <p>
 * 2. Solution Approaches:
 * a) Basic Solution:
 * - Perform an inorder traversal and track node count
 * - When count equals k, we've found our element
 * - Time Complexity: O(n), Space Complexity: O(h) where h is tree height
 * <p>
 * b) Optimized Solution (discussed but not implemented):
 * - Augment the BST to track subtree size
 * - Use binary search to find kth element in O(log n) time
 * - Useful when frequent queries or tree modifications occur
 * <p>
 * The implementation demonstrates both the basic inorder traversal solution
 * and discusses the optimization approach for advanced scenarios.
 */

public class _416_b_KthSmallestElementBST {

    // Instance variables to track state during traversal
    private int k;        // The target rank we're looking for
    private int count;    // Current count of visited nodes
    private int result;   // Result value to return

    /**
     * Creates and returns a sample BST for testing
     */
    public static TreeNode createSampleBST() {
        // Create BST structure:
        //       5
        //      / \
        //     3   6
        //    / \
        //   2   4
        //  /
        // 1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

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
        _416_b_KthSmallestElementBST solution = new _416_b_KthSmallestElementBST();

        // Create a sample BST
        TreeNode root = createSampleBST();

        System.out.println("Finding Kth Smallest Element in BST");
        System.out.println("==================================");

        // Print the BST structure
        System.out.println("\nBST Structure:");
        printBST(root);

        // Find and print various order statistics
        System.out.println("\nOrder Statistics:");
        for (int k = 1; k <= 6; k++) {
            int result = solution.kthSmallest(root, k);
            System.out.println(k + "th smallest element: " + result);
        }

        // Trace the execution for the 3rd smallest element
        System.out.println("\nDetailed Execution Trace:");
        solution.traceKthSmallest(root, 3);

        // Explain the optimized solution
        solution.explainOptimizedSolution();
    }

    /**
     * Solution approach using inorder traversal to find the kth smallest element
     */
    public int kthSmallest(TreeNode root, int k) {
        // Result will be stored here
        this.k = k;
        this.result = 0;

        // Start inorder traversal
        inorderTraverse(root);

        return result;
    }

    /**
     * Helper method for inorder traversal
     */
    private void inorderTraverse(TreeNode root) {
        // Base case: empty subtree or already found result
        if (root == null || count >= k) {
            return;
        }

        // Inorder traversal: left subtree first
        inorderTraverse(root.left);

        // Process current node (inorder position)
        count++;
        if (count == k) {
            // Found the kth element!
            result = root.val;
            return;
        }

        // Continue with right subtree
        inorderTraverse(root.right);
    }

    /**
     * Alternative implementation that collects all values during inorder traversal
     * and returns the kth element. Less efficient for large trees when k is small.
     */
    public int kthSmallestUsingList(TreeNode root, int k) {
        // Collect all values using inorder traversal
        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        collectInorder(root, values);

        // Return the kth element (0-indexed in the list, so k-1)
        return values.get(k - 1);
    }

    private void collectInorder(TreeNode root, java.util.ArrayList<Integer> values) {
        if (root == null) {
            return;
        }

        collectInorder(root.left, values);
        values.add(root.val);
        collectInorder(root.right, values);
    }

    /**
     * Conceptual framework for the optimized O(log n) solution.
     * Not fully implemented as it would require a custom BST implementation.
     */
    public int kthSmallestOptimized(AugmentedTreeNode root, int k) {
        if (root == null) {
            return -1; // Error case, should not happen with valid inputs
        }

        // Size of left subtree tells us how many elements are smaller than current node
        int leftSize = (root.left != null) ? root.left.size : 0;

        if (k == leftSize + 1) {
            // Current node is the kth smallest
            return root.val;
        } else if (k <= leftSize) {
            // The kth smallest is in the left subtree
            return kthSmallestOptimized(root.left, k);
        } else {
            // The kth smallest is in the right subtree
            // Adjust k to account for elements in left subtree and current node
            return kthSmallestOptimized(root.right, k - leftSize - 1);
        }
    }

    /**
     * Trace the execution of inorder traversal to find the kth smallest element
     */
    public void traceKthSmallest(TreeNode root, int k) {
        System.out.println("Tracing the inorder traversal to find the " + k + "th smallest element:");

        // Reset state
        this.k = k;
        this.count = 0;
        this.result = 0;

        // Start tracing
        traceInorder(root, 0);

        System.out.println("Result: The " + k + "th smallest element is " + result);
    }

    private void traceInorder(TreeNode node, int depth) {
        if (node == null || count >= k) {
            if (node == null) {
                indent(depth);
                System.out.println("Reached null node, backtracking...");
            }
            return;
        }

        // Start processing current node
        indent(depth);
        System.out.println("Visiting node with value " + node.val);

        // Go left
        indent(depth);
        System.out.println("Going left from " + node.val);
        traceInorder(node.left, depth + 1);

        // Process current node (inorder position)
        indent(depth);
        count++;
        System.out.println("Processing node " + node.val + " (inorder) - Count = " + count);

        if (count == k) {
            indent(depth);
            System.out.println("Found " + k + "th smallest: " + node.val);
            result = node.val;
            return;
        }

        // Go right
        indent(depth);
        System.out.println("Going right from " + node.val);
        traceInorder(node.right, depth + 1);

        // Finished with this node
        indent(depth);
        System.out.println("Finished processing subtree at " + node.val);
    }

    private void indent(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("|  ");
        }
    }

    /**
     * Explain the optimized solution with augmented BST nodes
     */
    public void explainOptimizedSolution() {
        System.out.println("\nOPTIMIZED SOLUTION EXPLANATION");
        System.out.println("===============================");
        System.out.println("To achieve O(log n) time complexity for finding the kth smallest element,");
        System.out.println("we need to augment the BST nodes with additional information.");
        System.out.println();

        System.out.println("1. Augmented Node Structure:");
        System.out.println("   - Each node stores its value and pointers to left/right children");
        System.out.println("   - Additionally, each node stores the size of its subtree (including itself)");
        System.out.println();

        System.out.println("2. Finding the kth Element:");
        System.out.println("   - At each node, we know the size of the left subtree (leftSize)");
        System.out.println("   - If k == leftSize + 1, the current node is the answer");
        System.out.println("   - If k <= leftSize, the answer is in the left subtree");
        System.out.println("   - If k > leftSize + 1, the answer is in the right subtree (with adjusted k)");
        System.out.println();

        System.out.println("3. Maintaining the Size Field:");
        System.out.println("   - When inserting/deleting nodes, update the size of each node on the path");
        System.out.println("   - size = 1 + size(left) + size(right)");
        System.out.println();

        System.out.println("This optimization is particularly useful when:");
        System.out.println("- The tree is frequently modified (insertions/deletions)");
        System.out.println("- There are frequent queries for order statistics (kth smallest/largest)");
        System.out.println("- The tree is large but well-balanced");
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

    /**
     * Definition of an augmented BST node that also tracks subtree size.
     * This would be used in the optimized O(log n) solution.
     */
    public static class AugmentedTreeNode {
        int val;
        int size;  // Size of the subtree rooted at this node
        AugmentedTreeNode left;
        AugmentedTreeNode right;

        AugmentedTreeNode(int x) {
            val = x;
            size = 1;  // Initially just the node itself
        }
    }
}
