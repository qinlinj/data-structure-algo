package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._441_lowest_common_ancestor_framework;

/**
 * Finding Nodes in Binary Trees
 * ---------------------------------------------------------
 * This class demonstrates different approaches to searching for nodes in a binary tree.
 * <p>
 * Key points:
 * 1. Basic binary tree traversal for finding nodes with a specific value
 * 2. Demonstrates three different traversal approaches with efficiency differences:
 * - Early return: Most efficient, stops searching once found
 * - Full search with pre-order check: Less efficient, continues in right subtree
 * - Full search with post-order check: Least efficient, always traverses entire tree
 * 3. Variations of the find function form the foundation for LCA algorithms
 * 4. Search efficiency depends on where the target check occurs (pre-order vs post-order)
 */
public class _441_b_FindNodeInBinaryTree {

    // Create a sample tree for demonstration
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = createSampleTree();
        _441_b_FindNodeInBinaryTree finder = new _441_b_FindNodeInBinaryTree();

        // Demonstrate the three approaches
        int targetVal = 4;

        System.out.println("Finding node with value " + targetVal);

        TreeNode found1 = finder.findEfficient(root, targetVal);
        System.out.println("Efficient approach found: " + (found1 != null ? "Yes" : "No"));

        TreeNode found2 = finder.findLessEfficient(root, targetVal);
        System.out.println("Less efficient approach found: " + (found2 != null ? "Yes" : "No"));

        TreeNode found3 = finder.findLeastEfficient(root, targetVal);
        System.out.println("Least efficient approach found: " + (found3 != null ? "Yes" : "No"));

        // Demonstrate finding either of two values
        int val1 = 5, val2 = 4;
        TreeNode foundEither = finder.findEither(root, val1, val2);
        System.out.println("Found either " + val1 + " or " + val2 + ": " +
                (foundEither != null ? "Yes, value: " + foundEither.val : "No"));
    }

    /**
     * Approach 1: Most efficient approach with early return
     * Time Complexity: O(n) in worst case, but can be much better
     * Stops traversal once the node is found
     */
    public TreeNode findEfficient(TreeNode root, int val) {
        // Base case
        if (root == null) {
            return null;
        }

        // Check current node (pre-order position)
        if (root.val == val) {
            return root;
        }

        // Search left subtree first
        TreeNode left = findEfficient(root.left, val);
        if (left != null) {
            return left;
        }

        // Only search right subtree if not found in left
        TreeNode right = findEfficient(root.right, val);
        if (right != null) {
            return right;
        }

        // Node not found
        return null;
    }

    /**
     * Approach 2: Less efficient approach that may search more nodes than necessary
     * Still uses pre-order check but searches both subtrees regardless
     * Time Complexity: O(n), often traverses more nodes than necessary
     */
    public TreeNode findLessEfficient(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        // Pre-order position check
        if (root.val == val) {
            return root;
        }

        // Search both subtrees regardless
        TreeNode left = findLessEfficient(root.left, val);
        TreeNode right = findLessEfficient(root.right, val);

        // Return whichever found the node
        return left != null ? left : right;
    }

    /**
     * Approach 3: Least efficient approach that always traverses the entire tree
     * Uses post-order check, so root is checked after both subtrees
     * Time Complexity: O(n), always traverses all nodes
     */
    public TreeNode findLeastEfficient(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        // Search both subtrees first
        TreeNode left = findLeastEfficient(root.left, val);
        TreeNode right = findLeastEfficient(root.right, val);

        // Post-order position check
        if (root.val == val) {
            return root;
        }

        // Return whichever found the node
        return left != null ? left : right;
    }

    /**
     * Special version that finds either of two values
     * This forms the foundation for LCA algorithms
     */
    public TreeNode findEither(TreeNode root, int val1, int val2) {
        // Base case
        if (root == null) {
            return null;
        }

        // Pre-order position, check if current node is either target
        if (root.val == val1 || root.val == val2) {
            return root;
        }

        // Search both subtrees
        TreeNode left = findEither(root.left, val1, val2);
        TreeNode right = findEither(root.right, val1, val2);

        // Return whichever found a target node
        return left != null ? left : right;
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}