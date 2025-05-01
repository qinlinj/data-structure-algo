package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._419_binary_search_tree_postorder;

/**
 * Binary Search Tree - Postorder Traversal Applications
 * <p>
 * This class demonstrates practical applications of postorder traversal in solving
 * various binary tree problems, showing how to leverage bottom-up computation for efficiency.
 * <p>
 * Key applications:
 * 1. Tree property validation:
 * - BST validation
 * - Height-balanced tree checks
 * - Complete binary tree validation
 * <p>
 * 2. Subtree optimization problems:
 * - Maximum sum paths
 * - Largest BST subtree
 * - Subtree matching
 * <p>
 * 3. Tree transformation problems:
 * - Tree pruning
 * - Node deletion
 * - Tree flattening
 * <p>
 * Postorder traversal is particularly powerful for these applications because:
 * - It enables informed decision-making at each node based on subtree information
 * - It allows for efficient bottom-up transformation of tree structures
 * - It prevents redundant calculations through a single pass approach
 */

public class _419_d_PostorderApplications {

    /**
     * Application 5: Find diameter of binary tree
     * Diameter is the length of the longest path between any two nodes
     */
    private int maxDiameter = 0;

    public static void main(String[] args) {
        _419_d_PostorderApplications apps = new _419_d_PostorderApplications();
        TreeNode tree = apps.createTestTree();

        System.out.println("Demonstrating postorder traversal applications:");

        // Application 1: Largest BST Subtree
        int largestBST = apps.largestBSTSubtree(tree);
        System.out.println("\nApplication 1 - Largest BST subtree size: " + largestBST);

        // Application 2: Remove Leaf Nodes
        TreeNode prunedTree = apps.removeLeafNodes(tree, 3);
        System.out.println("\nApplication 2 - Removed leaf nodes with value 3");

        // Application 3: Lowest Common Ancestor
        TreeNode lca = apps.lowestCommonAncestor(tree, tree.left, tree.right);
        System.out.println("\nApplication 3 - LCA value: " + lca.val);

        // Application 4: Serialize Tree
        String serialized = apps.serialize(tree);
        System.out.println("\nApplication 4 - Serialized tree: " + serialized);

        // Application 5: Tree Diameter
        int diameter = apps.diameterOfBinaryTree(tree);
        System.out.println("\nApplication 5 - Tree diameter: " + diameter);

        // Application 6: Symmetric Tree
        boolean isSymmetric = apps.isSymmetric(tree);
        System.out.println("\nApplication 6 - Is tree symmetric: " + isSymmetric);

        // Summary
        System.out.println("\nCommon pattern in all applications:");
        System.out.println("1. Process left subtree first");
        System.out.println("2. Process right subtree next");
        System.out.println("3. Make decisions at current node based on subtree information");
        System.out.println("\nKey advantages of postorder traversal in these applications:");
        System.out.println("- Enables bottom-up computation and decision-making");
        System.out.println("- Allows for efficient propagation of information up the tree");
        System.out.println("- Eliminates need for multiple tree traversals");
        System.out.println("- Reduces time complexity from O(n²) to O(n) in many cases");
    }

    /**
     * Application 1: Find the largest BST subtree
     * Returns the size of the largest BST within the tree
     */
    public int largestBSTSubtree(TreeNode root) {
        // result[0] = size of largest BST found so far
        int[] result = new int[1];
        largestBSTHelper(root, result);
        return result[0];
    }

    // Returns an array: [isBST, min, max, size]
    private int[] largestBSTHelper(TreeNode root, int[] result) {
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        // Process left and right subtrees
        int[] left = largestBSTHelper(root.left, result);
        int[] right = largestBSTHelper(root.right, result);

        // Postorder position: Process current node
        int[] current = new int[4];

        // If both subtrees are valid BSTs and current node maintains BST property
        if (left[0] == 1 && right[0] == 1 &&
                root.val > left[2] && root.val < right[1]) {

            // This subtree is a valid BST
            current[0] = 1;
            current[1] = Math.min(left[1], root.val);
            current[2] = Math.max(right[2], root.val);
            current[3] = left[3] + right[3] + 1;

            // Update largest BST size if needed
            result[0] = Math.max(result[0], current[3]);
        } else {
            // Not a valid BST
            current[0] = 0;
        }

        return current;
    }

    /**
     * Application 2: Delete leaves with given value
     * Removes all leaf nodes with a specific value
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;

        // Process children first
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        // Postorder position: After processing children, check if current node is a leaf
        if (root.left == null && root.right == null && root.val == target) {
            return null; // Remove this leaf node
        }

        return root;
    }

    /**
     * Application 3: Lowest Common Ancestor (LCA) in a binary tree
     * Demonstrates how postorder traversal can efficiently find relationships
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // Postorder position: Determine LCA based on subtree results
        if (left != null && right != null) {
            // Both p and q found in different subtrees, so root is LCA
            return root;
        }

        // Either left or right contains the LCA (or neither contains p or q)
        return left != null ? left : right;
    }

    /**
     * Application 4: Serialize and deserialize binary tree
     * Using postorder traversal for tree reconstruction
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("N,");
            return;
        }

        // Postorder: left, right, node
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
        sb.append(node.val).append(",");
    }

    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return maxDiameter;
    }

    private int calculateHeight(TreeNode root) {
        if (root == null) return 0;

        // Calculate heights of left and right subtrees
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        // Postorder position: Update diameter using height information
        // Diameter passing through current node = left height + right height
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return height of this subtree to parent
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Application 6: Determine if a binary tree is symmetric
     * Uses postorder to compare subtree structures
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // Base cases
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        // Recursively check if subtrees are mirrors of each other
        boolean leftMirror = isMirror(left.left, right.right);
        boolean rightMirror = isMirror(left.right, right.left);

        // Postorder position: Combine results to determine if trees are mirrors
        return left.val == right.val && leftMirror && rightMirror;
    }

    /**
     * Utility method to create a test tree
     */
    private TreeNode createTestTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        return root;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
