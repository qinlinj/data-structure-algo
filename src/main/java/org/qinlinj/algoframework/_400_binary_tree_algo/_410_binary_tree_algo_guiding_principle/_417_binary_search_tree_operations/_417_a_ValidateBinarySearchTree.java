package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._417_binary_search_tree_operations;

/**
 * VALIDATE BINARY SEARCH TREE
 * ==========================
 * <p>
 * This class implements the solution to LeetCode Problem 98:
 * "Validate Binary Search Tree"
 * <p>
 * Problem Description:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 * <p>
 * Key Insights:
 * <p>
 * 1. Common Mistake: Only checking if each node's direct children satisfy the constraints
 * - This is insufficient because BST's property applies to the entire subtree,
 * not just immediate children
 * - Example: A node with value 5 must have ALL left subtree nodes < 5 and
 * ALL right subtree nodes > 5
 * <p>
 * 2. Correct Approach: Enforcing Valid Range Constraints
 * - Each node must fall within a valid range determined by its ancestors
 * - Left subtree nodes must be less than their parent AND satisfy all upper bounds
 * from ancestors
 * - Right subtree nodes must be greater than their parent AND satisfy all lower bounds
 * from ancestors
 * - We pass these constraints down the tree during recursion
 * <p>
 * 3. Implementation using Additional Parameters:
 * - Use two additional parameters (min and max) to track the valid range for each node
 * - For a left subtree, update the max bound to the parent's value
 * - For a right subtree, update the min bound to the parent's value
 * - Use null to represent no bound (at the root level)
 */

public class _417_a_ValidateBinarySearchTree {

    /**
     * Creates a valid BST for testing
     */
    public static TreeNode createValidBST() {
        // Create a valid BST:
        //      5
        //     / \
        //    3   8
        //   / \ / \
        //  1  4 7  9
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        return root;
    }

    /**
     * Creates an invalid BST for testing
     */
    public static TreeNode createInvalidBST() {
        // Create an invalid BST:
        //      5
        //     / \
        //    3   8
        //   / \ / \
        //  1  6 7  9
        //         Node 6 violates BST property (it's in left subtree but > 5)
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);  // This violates BST property!
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        return root;
    }

    /**
     * Creates a more subtle invalid BST that would fool the naive algorithm
     */
    public static TreeNode createSubtleInvalidBST() {
        // Create a subtly invalid BST:
        //      10
        //     /  \
        //    5    15
        //   / \   / \
        //  3   7 6   20
        //          Node 6 violates BST property (it's in right subtree but < 10)
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(6);  // This violates BST property!
        root.right.right = new TreeNode(20);

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
        _417_a_ValidateBinarySearchTree validator = new _417_a_ValidateBinarySearchTree();

        // Create sample trees
        TreeNode validTree = createValidBST();
        TreeNode invalidTree = createInvalidBST();
        TreeNode subtleInvalidTree = createSubtleInvalidBST();

        System.out.println("VALIDATING BINARY SEARCH TREES");
        System.out.println("=============================");

        // Test valid tree
        System.out.println("\nValid BST:");
        printBST(validTree);
        System.out.println("Naive approach: " + validator.isValidBSTNaive(validTree));
        System.out.println("Correct approach: " + validator.isValidBST(validTree));

        // Test obviously invalid tree
        System.out.println("\nObviously Invalid BST (node 6 in left subtree > 5):");
        printBST(invalidTree);
        System.out.println("Naive approach: " + validator.isValidBSTNaive(invalidTree));
        System.out.println("Correct approach: " + validator.isValidBST(invalidTree));

        // Test subtly invalid tree
        System.out.println("\nSubtly Invalid BST (node 6 in right subtree < 10):");
        printBST(subtleInvalidTree);
        System.out.println("Naive approach: " + validator.isValidBSTNaive(subtleInvalidTree));
        System.out.println("Correct approach: " + validator.isValidBST(subtleInvalidTree));

        // Trace validation process
        System.out.println("\nDetailed Validation Trace for Valid BST:");
        validator.traceValidation(validTree);

        // Explain why naive approach fails
        validator.explainNaiveApproachFailure();
    }

    /**
     * Naive (incorrect) approach that only checks immediate children
     * This fails for more complex cases where violations occur deeper in the tree
     */
    public boolean isValidBSTNaive(TreeNode root) {
        if (root == null) return true;

        // Check immediate children only
        if (root.left != null && root.left.val >= root.val)
            return false;
        if (root.right != null && root.right.val <= root.val)
            return false;

        // Recursively check left and right subtrees
        return isValidBSTNaive(root.left) && isValidBSTNaive(root.right);
    }

    /**
     * Correct approach using valid range constraints
     * Main function that initializes the recursive validation
     */
    public boolean isValidBST(TreeNode root) {
        // Start with no constraints (null represents unbounded)
        return isValidBSTHelper(root, null, null);
    }

    /**
     * Helper function that performs the actual validation with range constraints
     *
     * @param root The current node being validated
     * @param min  The lower bound (null means no lower bound)
     * @param max  The upper bound (null means no upper bound)
     * @return true if the tree rooted at 'root' is a valid BST within the given range
     */
    private boolean isValidBSTHelper(TreeNode root, TreeNode min, TreeNode max) {
        // Base case: empty trees are valid BSTs
        if (root == null) {
            return true;
        }

        // Check if current node's value violates the constraints
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }

        // Recursively validate left and right subtrees with updated constraints:
        // - Left subtree nodes must be less than current node (max = root)
        // - Right subtree nodes must be greater than current node (min = root)
        return isValidBSTHelper(root.left, min, root) &&
                isValidBSTHelper(root.right, root, max);
    }

    /**
     * Trace the validation process with detailed explanation
     */
    public void traceValidation(TreeNode root) {
        System.out.println("Tracing BST Validation Process:");
        System.out.println("------------------------------");

        traceValidationHelper(root, null, null, 0);
    }

    private boolean traceValidationHelper(TreeNode root, TreeNode min, TreeNode max, int depth) {
        String indent = "  ".repeat(depth);

        // Print current state
        System.out.print(indent + "Checking node: ");
        if (root == null) {
            System.out.println("null (Base case: empty tree is valid)");
            return true;
        }

        System.out.println(root.val);
        System.out.print(indent + "Valid range: ");
        System.out.print(min == null ? "-∞" : min.val);
        System.out.print(" < X < ");
        System.out.println(max == null ? "+∞" : max.val);

        // Check constraints
        if (min != null && root.val <= min.val) {
            System.out.println(indent + "❌ INVALID: " + root.val + " <= " + min.val + " (violates min constraint)");
            return false;
        }
        if (max != null && root.val >= max.val) {
            System.out.println(indent + "❌ INVALID: " + root.val + " >= " + max.val + " (violates max constraint)");
            return false;
        }

        System.out.println(indent + "✓ Node " + root.val + " is within valid range");

        // Recursively validate left subtree
        System.out.println(indent + "Validating left subtree of " + root.val + ":");
        boolean leftValid = traceValidationHelper(root.left, min, root, depth + 1);

        // Recursively validate right subtree
        System.out.println(indent + "Validating right subtree of " + root.val + ":");
        boolean rightValid = traceValidationHelper(root.right, root, max, depth + 1);

        // Final result for this subtree
        boolean result = leftValid && rightValid;
        System.out.println(indent + (result ? "✓" : "❌") + " Subtree rooted at " + root.val + " is " + (result ? "valid" : "invalid"));

        return result;
    }

    /**
     * Explain why the naive approach fails
     */
    public void explainNaiveApproachFailure() {
        System.out.println("\nWHY THE NAIVE APPROACH FAILS");
        System.out.println("===========================");

        TreeNode subtleInvalidTree = createSubtleInvalidBST();

        System.out.println("Consider this tree:");
        printBST(subtleInvalidTree);

        System.out.println("\nThe naive algorithm only checks immediate child relationships:");
        System.out.println("- 10 > 5 and 10 < 15 ✓");
        System.out.println("- 5 > 3 and 5 < 7 ✓");
        System.out.println("- 15 > 6 and 15 < 20 ✓");
        System.out.println("- 3, 7, 6, 20 have no children ✓");
        System.out.println("Based on these checks, the naive algorithm incorrectly concludes the tree is valid.");

        System.out.println("\nThe problem: Node 6 is in the right subtree of 10, but 6 < 10!");
        System.out.println("This violates the BST property that ALL nodes in the right subtree must be > parent.");

        System.out.println("\nThe correct algorithm tracks valid ranges for each node as we traverse down the tree:");
        System.out.println("- Root node 10: Valid range is (-∞, +∞)");
        System.out.println("- Left child 5: Valid range is (-∞, 10)");
        System.out.println("- Right child 15: Valid range is (10, +∞)");
        System.out.println("- When we check node 6 in right subtree: Valid range is (10, +∞)");
        System.out.println("- Since 6 < 10, it violates the constraint, and the tree is invalid.");
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