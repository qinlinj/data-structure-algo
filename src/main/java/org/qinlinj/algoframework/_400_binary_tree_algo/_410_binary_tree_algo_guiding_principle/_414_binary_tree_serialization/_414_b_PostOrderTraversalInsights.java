package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._414_binary_tree_serialization;

/**
 * POST-ORDER TRAVERSAL INSIGHTS AND APPLICATIONS
 * ==============================================
 * <p>
 * This class explores the special importance of post-order position in binary tree algorithms,
 * building on the concepts introduced in the "Binary Tree Post-Order" article.
 * <p>
 * Key insights about post-order traversal:
 * <p>
 * 1. Information Access in Different Traversal Positions:
 * - Pre-order position: Can only access data from parent nodes (via function parameters)
 * - In-order position: Can access parent node data and left subtree results
 * - Post-order position: Can access parent node data AND results from BOTH left and right subtrees
 * <p>
 * 2. When to Use Post-Order Position:
 * - When you need information about the complete subtree structure
 * - When the solution depends on combining results from both subtrees
 * - When dealing with problems that ask about subtree properties or relationships
 * <p>
 * 3. Common Post-Order Applications:
 * - Finding duplicate subtrees
 * - Calculating tree height/depth
 * - Determining if a tree is balanced
 * - Computing the diameter of a tree
 * - Serializing a tree
 * <p>
 * This class demonstrates these concepts through examples and visualizations.
 */

public class _414_b_PostOrderTraversalInsights {
    /**
     * EXAMPLE 5: Finding the diameter of a binary tree
     * (Diameter: length of the longest path between any two nodes)
     */
    private int diameter = 0;

    /**
     * Main method to demonstrate all concepts
     */
    public static void main(String[] args) {
        _414_b_PostOrderTraversalInsights demo = new _414_b_PostOrderTraversalInsights();

        // Create a sample tree for demonstration
        //      1
        //     / \
        //    2   3
        //   / \
        //  4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("POST-ORDER TRAVERSAL INSIGHTS");
        System.out.println("============================");
        System.out.println();

        System.out.println("Sample Tree Results:");
        System.out.println("-------------------");
        System.out.println("1. Node Count: " + demo.countNodes(root));
        System.out.println("2. Max Depth: " + demo.maxDepth(root));
        System.out.println("3. Is Balanced: " + demo.isBalanced(root));
        System.out.println("4. Post-order Serialization: " + demo.serializePostorder(root));
        System.out.println("5. Tree Diameter: " + demo.diameterOfBinaryTree(root));

        System.out.println();
        demo.demonstratePostOrder();

        System.out.println();
        demo.explainPostOrderPower();
    }

    /**
     * EXAMPLE 1: Counting nodes in a binary tree
     * This demonstrates a classic application of post-order traversal
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // First, get counts from left and right subtrees
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        // Post-order position: combine results
        // Add 1 to include the current node
        return leftCount + rightCount + 1;
    }

    /**
     * EXAMPLE 2: Calculating the height/depth of a binary tree
     * Another classic post-order application
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Calculate depths of subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Post-order position: use the max depth of subtrees plus one
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * EXAMPLE 3: Determining if a binary tree is balanced
     * (Height-balanced: the depth of the two subtrees of every node never differs by more than 1)
     */
    public boolean isBalanced(TreeNode root) {
        return balancedHeight(root) != -1;
    }

    /**
     * Helper function for isBalanced that returns:
     * - The height of the tree if it's balanced
     * - -1 if the tree is not balanced
     */
    private int balancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Calculate height of left and right subtrees
        int leftHeight = balancedHeight(root.left);
        int rightHeight = balancedHeight(root.right);

        // If either subtree is unbalanced or heights differ by more than 1
        if (leftHeight == -1 || rightHeight == -1 ||
                Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Post-order position: calculate and return height
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * EXAMPLE 4: Serializing a binary tree (post-order approach)
     * This is the core technique used in finding duplicate subtrees
     */
    public String serializePostorder(TreeNode root) {
        if (root == null) {
            return "#";
        }

        // Serialize left and right subtrees
        String leftSerialized = serializePostorder(root.left);
        String rightSerialized = serializePostorder(root.right);

        // Post-order position: combine serialized subtrees with current value
        return leftSerialized + "," + rightSerialized + "," + root.val;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return diameter;
    }

    private int calculateHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Calculate height of left and right subtrees
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        // Post-order position: update the diameter
        // Diameter is the longest path passing through this node
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return the height of this subtree
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Visual demonstration of information flow in post-order traversal
     */
    public void demonstratePostOrder() {
        // Create a simple binary tree
        //      1
        //     / \
        //    2   3
        //   / \
        //  4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Demonstration of Post-Order Information Flow:");
        System.out.println("--------------------------------------------");
        tracePostOrder(root, 0);
    }

    /**
     * Helper method to trace the post-order traversal with visual indentation
     */
    private void tracePostOrder(TreeNode root, int indent) {
        if (root == null) {
            return;
        }

        String padding = " ".repeat(indent * 2);

        // Enter the node
        System.out.println(padding + "Enter node " + root.val);

        // Process left subtree
        tracePostOrder(root.left, indent + 1);

        // In-order position
        System.out.println(padding + "After processing left subtree of node " + root.val);

        // Process right subtree
        tracePostOrder(root.right, indent + 1);

        // Post-order position
        System.out.println(padding + "POST-ORDER POSITION at node " + root.val + " - Now have access to:");

        if (root.left != null) {
            System.out.println(padding + "  - Left subtree information of node " + root.val);
        }

        if (root.right != null) {
            System.out.println(padding + "  - Right subtree information of node " + root.val);
        }

        System.out.println(padding + "Exit node " + root.val);
    }

    /**
     * Explanation of why post-order position is powerful
     */
    public void explainPostOrderPower() {
        System.out.println("\nWhy Post-Order Position is Powerful:");
        System.out.println("-----------------------------------");
        System.out.println("The post-order position is special because you have access to complete information");
        System.out.println("about both your left and right subtrees. This makes it perfect for problems where");
        System.out.println("you need to combine or process information from entire subtrees.");
        System.out.println();
        System.out.println("Think of it this way:");
        System.out.println("1. Pre-order: You only know about yourself and your ancestors");
        System.out.println("2. In-order: You know about yourself, your ancestors, and your left descendants");
        System.out.println("3. Post-order: You know about yourself, your ancestors, AND ALL your descendants");
        System.out.println();
        System.out.println("This is why most subtree-related problems are solved in the post-order position.");
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
}