package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._416_binary_search_tree_properties;

/**
 * GREATER SUM TREE (BST TO GREATER SUM TREE)
 * =========================================
 * <p>
 * This class implements the solution to LeetCode Problems 538 and 1038:
 * "Convert BST to Greater Sum Tree" and "Binary Search Tree to Greater Sum Tree"
 * <p>
 * Problem Description:
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Sum Tree
 * where each node's value is replaced with the sum of all nodes with greater or equal value.
 * <p>
 * Key Insights:
 * <p>
 * 1. Using Reverse Inorder Traversal:
 * - A regular inorder traversal visits nodes in ascending order
 * - A reverse inorder traversal (right, node, left) visits nodes in descending order
 * - By maintaining a running sum during reverse inorder traversal, we can efficiently
 * compute the greater sum for each node
 * <p>
 * 2. Implementation Approach:
 * - Use a reverse inorder traversal (right subtree, node, left subtree)
 * - Maintain a running sum of all visited nodes
 * - Replace each node's value with the current running sum
 * - Time Complexity: O(n) where n is the number of nodes
 * - Space Complexity: O(h) where h is the height of the tree (recursion stack)
 * <p>
 * This solution elegantly leverages the BST property to solve the problem with a
 * single traversal of the tree.
 */

public class _416_c_GreaterSumTree {

    // Running sum of all visited nodes
    private int sum = 0;

    /**
     * Creates a sample BST for demonstration
     */
    public static TreeNode createSampleBST() {
        // Create BST structure:
        //       4
        //      / \
        //     1   6
        //    / \   \
        //   0   2   7
        //        \
        //         3
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.right = new TreeNode(7);

        return root;
    }

    /**
     * Creates another sample BST for testing
     */
    public static TreeNode createSampleBST2() {
        // Create BST structure:
        //       5
        //      / \
        //     2   13
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);

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

    /**
     * Print inorder traversal of the tree
     */
    public static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    /**
     * Print reverse inorder traversal of the tree (descending order)
     */
    public static void printReverseInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        printReverseInorder(root.right);
        System.out.print(root.val + " ");
        printReverseInorder(root.left);
    }

    /**
     * Calculate and return the expected value for each node in the Greater Sum Tree
     * This is mainly for validation purposes
     */
    public static int calculateGreaterSum(TreeNode root, int nodeVal) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        // Traverse the tree to find all nodes with value >= nodeVal
        if (root.val >= nodeVal) {
            sum += root.val;
        }

        sum += calculateGreaterSum(root.left, nodeVal);
        sum += calculateGreaterSum(root.right, nodeVal);

        return sum;
    }

    /**
     * Creates a deep copy of a BST
     */
    public static TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = cloneTree(root.left);
        newRoot.right = cloneTree(root.right);

        return newRoot;
    }

    /**
     * Visualize the BST before and after conversion
     */
    public static void visualizeConversion(TreeNode originalRoot) {
        // Create a deep copy of the tree for conversion
        TreeNode root = cloneTree(originalRoot);

        // Print the original tree
        System.out.println("Original BST:");
        printBST(root);

        System.out.println("\nOriginal Inorder Traversal (ascending):");
        printInorder(root);
        System.out.println();

        System.out.println("\nOriginal Reverse Inorder Traversal (descending):");
        printReverseInorder(root);
        System.out.println();

        // Convert to Greater Sum Tree
        _416_c_GreaterSumTree solution = new _416_c_GreaterSumTree();
        root = solution.convertBST(root);

        // Print the converted tree
        System.out.println("\nGreater Sum Tree:");
        printBST(root);

        System.out.println("\nConverted Tree Inorder Traversal:");
        printInorder(root);
        System.out.println();

        // Verify correctness by calculating expected values for each node
        System.out.println("\nVerification (for selected nodes):");
        TreeNode original = originalRoot;
        System.out.println("Node with original value 4 should have greater sum: " +
                calculateGreaterSum(original, 4));
        System.out.println("Node with original value 1 should have greater sum: " +
                calculateGreaterSum(original, 1));
        System.out.println("Node with original value 0 should have greater sum: " +
                calculateGreaterSum(original, 0));
    }

    /**
     * Explain why reverse inorder traversal works for this problem
     */
    public static void explainReverseInorderApproach() {
        System.out.println("\nWHY REVERSE INORDER TRAVERSAL WORKS");
        System.out.println("==================================");

        System.out.println("1. BST Property:");
        System.out.println("   - In a BST, all nodes in the right subtree are greater than the current node");
        System.out.println("   - All nodes in the left subtree are less than the current node");
        System.out.println();

        System.out.println("2. Inorder Traversal Property:");
        System.out.println("   - Regular inorder (left, node, right) visits nodes in ascending order");
        System.out.println("   - Reverse inorder (right, node, left) visits nodes in descending order");
        System.out.println();

        System.out.println("3. Greater Sum Calculation:");
        System.out.println("   - By visiting nodes in descending order, we first process the largest values");
        System.out.println("   - We maintain a running sum of all nodes visited so far");
        System.out.println("   - When we visit a node, the running sum contains the sum of all larger nodes");
        System.out.println("     plus the current node itself");
        System.out.println("   - This exactly matches the definition of the 'greater sum'");
        System.out.println();

        System.out.println("4. Efficiency:");
        System.out.println("   - This approach requires only a single traversal of the tree (O(n) time)");
        System.out.println("   - We modify the tree in-place, requiring O(1) extra space");
        System.out.println("     (apart from the recursion stack)");
        System.out.println();

        System.out.println("The elegance of this solution lies in how it leverages the inherent");
        System.out.println("properties of the BST structure to solve the problem efficiently!");
    }

    public static void main(String[] args) {
        // Create a sample BST
        TreeNode root = createSampleBST();

        System.out.println("BINARY SEARCH TREE TO GREATER SUM TREE");
        System.out.println("======================================");

        // Trace the conversion process for better understanding
        _416_c_GreaterSumTree solution = new _416_c_GreaterSumTree();
        System.out.println("\nStep-by-Step Trace:");
        TreeNode tracingTree = cloneTree(root);
        solution.traceConversion(tracingTree);

        // Visualize the conversion
        System.out.println("\nVisualization of Before and After:");
        visualizeConversion(root);

        // Explain the approach
        explainReverseInorderApproach();

        // Additional example
        System.out.println("\n\nADDITIONAL EXAMPLE");
        System.out.println("==================");
        TreeNode root2 = createSampleBST2();
        visualizeConversion(root2);
    }

    /**
     * Main method to convert a BST to a Greater Sum Tree
     * Also known as:
     * - Greater Tree (LeetCode 538)
     * - Greater Sum Tree (LeetCode 1038)
     */
    public TreeNode convertBST(TreeNode root) {
        // Start with a sum of 0
        sum = 0;

        // Perform reverse inorder traversal
        reverseInorderTraversal(root);

        // Return the modified tree
        return root;
    }

    /**
     * Helper method that performs a reverse inorder traversal
     * (right, node, left) to visit nodes in descending order
     */
    private void reverseInorderTraversal(TreeNode root) {
        // Base case: empty subtree
        if (root == null) {
            return;
        }

        // 1. Visit right subtree first (higher values)
        reverseInorderTraversal(root.right);

        // 2. Visit current node (inorder position)
        // Add current value to sum
        sum += root.val;
        // Replace node's value with accumulated sum
        root.val = sum;

        // 3. Visit left subtree last (lower values)
        reverseInorderTraversal(root.left);
    }

    /**
     * Trace the execution of the algorithm for better understanding
     */
    public void traceConversion(TreeNode root) {
        System.out.println("Tracing the BST to Greater Sum Tree conversion:");
        System.out.println("(Using reverse inorder traversal: right -> node -> left)");

        // Reset state
        sum = 0;

        // Start tracing
        traceReverseInorder(root, 0);
    }

    private void traceReverseInorder(TreeNode node, int depth) {
        if (node == null) {
            indent(depth);
            System.out.println("Reached null node, backtracking...");
            return;
        }

        // Start processing current node
        indent(depth);
        System.out.println("Visiting node with value " + node.val);

        // Go right first (descending order)
        indent(depth);
        System.out.println("Going right from " + node.val);
        traceReverseInorder(node.right, depth + 1);

        // Process current node (inorder position)
        indent(depth);
        System.out.println("Processing node " + node.val + " (inorder position)");
        indent(depth);

        int oldVal = node.val;
        sum += oldVal;
        node.val = sum;

        System.out.println("Adding " + oldVal + " to sum = " + sum);
        System.out.println("Replacing node value with sum: " + oldVal + " -> " + node.val);

        // Go left
        indent(depth);
        System.out.println("Going left from " + node.val);
        traceReverseInorder(node.left, depth + 1);

        // Finished with this node
        indent(depth);
        System.out.println("Finished processing subtree at " + node.val);
    }

    private void indent(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("|  ");
        }
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
