package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._412_binary_tree_construction;

/**
 * BINARY TREE SOLVING APPROACHES - SUMMARY AND COMPARISON
 * ======================================================
 * <p>
 * This class provides a comprehensive summary of the two main approaches for solving
 * binary tree problems, as illustrated through the three example problems:
 * <p>
 * 1. Invert Binary Tree (LeetCode 226)
 * 2. Populate Next Right Pointers (LeetCode 116)
 * 3. Flatten Binary Tree to Linked List (LeetCode 114)
 * <p>
 * Key Insights:
 * <p>
 * 1. Two Core Thinking Patterns:
 * <p>
 * a) Traversal Approach:
 * - Uses a traverse function to visit each node
 * - Often uses external state or variables
 * - Similar to backtracking algorithm patterns
 * - Works well for problems where you need to visit every node and perform an action
 * - Example: Inverting a binary tree by swapping children at each node
 * <p>
 * b) Problem Decomposition Approach:
 * - Defines a recursive function with a clear purpose
 * - Leverages the function's return value
 * - Similar to dynamic programming patterns
 * - Works well for problems where the solution can be built from solutions to subproblems
 * - Example: Flattening a binary tree by recursively flattening subtrees first
 * <p>
 * 2. Choosing the Right Approach:
 * <p>
 * - Some problems can be solved using either approach (e.g., inverting a binary tree)
 * - Some problems naturally fit one approach better than the other:
 * - Populate Next Right Pointers: Best with modified traversal (need to connect nodes with different parents)
 * - Flatten Binary Tree: Best with problem decomposition (in-place transformation using subtree solutions)
 * <p>
 * 3. General Problem Solving Framework:
 * <p>
 * For any binary tree problem, ask:
 * - What does a single node need to do?
 * - When should it do it? (pre-order, in-order, or post-order position)
 * - Can I solve this by traversing the tree once? If yes, use the traversal approach
 * - Can I define a recursive function to solve this using solutions to subproblems? If yes, use the problem decomposition approach
 * <p>
 * This summary encapsulates the binary tree problem-solving framework presented in the text.
 */

public class _412_d_BinaryTreeApproachesSummary {
    public static void main(String[] args) {
        _412_d_BinaryTreeApproachesSummary solution = new _412_d_BinaryTreeApproachesSummary();

        System.out.println("Binary Tree Solution Approaches Summary");
        System.out.println("======================================");
        System.out.println();
        System.out.println("This class summarizes the two main approaches for solving binary tree problems:");
        System.out.println("1. Traversal Approach: Visit each node and perform actions with external state");
        System.out.println("2. Problem Decomposition Approach: Use recursive function's return value to build solutions");
        System.out.println();
        System.out.println("The key is to determine which approach better fits the problem at hand,");
        System.out.println("while focusing on what each individual node needs to do during traversal.");
    }

    /**
     * EXAMPLE 1: Inverting a Binary Tree
     * Can be solved effectively with either approach
     */

    // Traversal Approach
    public TreeNode invertTreeTraversal(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;

        // Pre-order position: Swap children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        traverse(root.left);
        traverse(root.right);
    }

    // Problem Decomposition Approach
    public TreeNode invertTreeDecomposition(TreeNode root) {
        if (root == null) return null;

        // Recursively invert subtrees
        TreeNode left = invertTreeDecomposition(root.left);
        TreeNode right = invertTreeDecomposition(root.right);

        // Swap the inverted subtrees
        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * EXAMPLE 2: Populating Next Right Pointers
     * Best solved with a modified traversal approach
     */
    public Node connect(Node root) {
        if (root == null) return null;

        // Start the "three-node" traversal
        connectTwoNodes(root.left, root.right);
        return root;
    }

    private void connectTwoNodes(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;

        // Connect the two input nodes
        node1.next = node2;

        // Connect children with the same parent
        connectTwoNodes(node1.left, node1.right);
        connectTwoNodes(node2.left, node2.right);

        // Connect children with different parents
        connectTwoNodes(node1.right, node2.left);
    }

    /**
     * EXAMPLE 3: Flattening Binary Tree to Linked List
     * Best solved with the problem decomposition approach
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        // Recursively flatten subtrees
        flatten(root.left);
        flatten(root.right);

        // Post-order position: Rearrange pointers
        TreeNode left = root.left;
        TreeNode right = root.right;

        // Make right pointer point to the left subtree
        root.left = null;
        root.right = left;

        // Find the end of the new right subtree
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        // Connect the end to the original right subtree
        current.right = right;
    }

    /**
     * Summary method showcasing the binary tree problem-solving framework
     */
    public void binaryTreeFrameworkSummary() {
        /*
         * When faced with a binary tree problem:
         *
         * 1. First, identify if the problem can be solved by:
         *    - Traversing the tree once with external variables ("Traversal" approach)
         *    - Breaking down the problem into subproblems ("Problem Decomposition" approach)
         *
         * 2. For either approach, focus on what a single node needs to do:
         *    - What operation does this node need to perform?
         *    - When should it perform this operation? (pre-order, in-order, post-order)
         *    - Let recursion handle the rest by applying the same operation to all nodes
         *
         * 3. For the traversal approach:
         *    - Define a traverse function without return value
         *    - Use external variables to accumulate or track the solution
         *    - Implement the node operation at the appropriate traversal position
         *
         * 4. For the problem decomposition approach:
         *    - Define a recursive function with a clear purpose/definition
         *    - Use the function's return value (solutions to subproblems)
         *    - Implement the combination logic (usually in post-order position)
         *
         * Remember: Both approaches are valid for many problems.
         * The right choice depends on the specific problem characteristics.
         */
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Definition for a Node with next pointer
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int _val) {
            val = _val;
        }
    }
}
