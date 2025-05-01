package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._419_binary_search_tree_postorder;

/**
 * Binary Search Tree - Postorder Traversal Patterns
 * <p>
 * This class explores common patterns and techniques for solving tree problems
 * using postorder traversal, with a focus on eliminating redundant calculations.
 * <p>
 * Key patterns:
 * 1. Information propagation:
 * - Gathering information from subtrees and passing it upward
 * - Combining and transforming data at each level
 * <p>
 * 2. Multi-value returns:
 * - Using arrays, custom objects, or multiple return values to pass multiple pieces of information
 * - This avoids multiple traversals of the same tree
 * <p>
 * 3. State transformation:
 * - Converting subtree states into parent node states
 * - Maintaining invariants as information flows upward
 * <p>
 * 4. Optimizing recursive algorithms:
 * - Avoiding calling recursive functions inside recursive functions
 * - Instead, capturing all needed information in a single traversal
 * <p>
 * These patterns are especially valuable for problems where:
 * - Node properties depend on subtree properties
 * - Multiple calculations need to be performed on the same tree
 * - Bottom-up computation is more efficient than top-down
 */

public class _419_c_PostorderPatterns {

    /**
     * Pattern 1: Postorder for path-related problems
     * Example: Calculate maximum path sum in binary tree
     */
    private int maxPathSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        _419_c_PostorderPatterns patterns = new _419_c_PostorderPatterns();
        TreeNode tree = patterns.createTestTree();

        System.out.println("Demonstrating postorder traversal patterns:");

        // Pattern 1: Max Path Sum
        int maxPath = patterns.maxPathSum(tree);
        System.out.println("\nPattern 1 - Max Path Sum: " + maxPath);

        // Pattern 2: Tree Balance Check
        boolean isBalanced = patterns.isBalanced(tree);
        System.out.println("\nPattern 2 - Is Balanced: " + isBalanced);

        // Pattern 3: BST Validation
        boolean isValidBST = patterns.isValidBST(tree);
        System.out.println("\nPattern 3 - Is Valid BST: " + isValidBST);

        // Pattern 4: Multiple Properties
        TreeProperties props = patterns.calculateProperties(tree);
        System.out.println("\nPattern 4 - Tree Properties: " + props);

        // Summary
        System.out.println("\nKey pattern in all examples:");
        System.out.println("1. Calculate information from left subtree");
        System.out.println("2. Calculate information from right subtree");
        System.out.println("3. Use this information in postorder position to:");
        System.out.println("   - Update global state and/or");
        System.out.println("   - Calculate and return values for parent nodes");
        System.out.println("\nThis approach eliminates the need for multiple tree traversals,");
        System.out.println("reducing time complexity from O(n²) to O(n) for many problems.");
    }

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxPathSum;
    }

    // Helper function returns the maximum gain from a path starting at this node
    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Recursively calculate max path sum from left and right subtrees
        // If the path sum is negative, we can choose not to include that path
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // Postorder position: Use subtree information to calculate path sum through current node
        int pathSum = node.val + leftGain + rightGain;

        // Update global maximum path sum
        maxPathSum = Math.max(maxPathSum, pathSum);

        // Return maximum path sum starting from current node
        // Note: we can only choose one path (left or right) to continue upward
        return node.val + Math.max(leftGain, rightGain);
    }

    public boolean isBalanced(TreeNode root) {
        return checkBalanced(root).isBalanced;
    }

    private TreeInfo checkBalanced(TreeNode root) {
        if (root == null) {
            return new TreeInfo(true, -1);
        }

        // Get information from left and right subtrees
        TreeInfo left = checkBalanced(root.left);
        TreeInfo right = checkBalanced(root.right);

        // Postorder position: Process current node using subtree information
        boolean isBalanced = left.isBalanced && right.isBalanced &&
                Math.abs(left.height - right.height) <= 1;

        int height = Math.max(left.height, right.height) + 1;

        return new TreeInfo(isBalanced, height);
    }

    /**
     * Pattern 3: State transformation with arrays
     * Example: Determine if tree is a valid BST, while tracking min, max, and size
     */
    public boolean isValidBST(TreeNode root) {
        // The returned array contains: [isBST, min, max, size]
        int[] result = validateBST(root);
        return result[0] == 1;
    }

    private int[] validateBST(TreeNode root) {
        if (root == null) {
            // For empty tree: [isBST=1, min=MAX_VALUE, max=MIN_VALUE, size=0]
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        // Get information from subtrees
        int[] left = validateBST(root.left);
        int[] right = validateBST(root.right);

        // Postorder position: Process current node
        int[] result = new int[4];

        // Check if current tree is a valid BST
        if (left[0] == 1 && right[0] == 1 &&
                root.val > left[2] && root.val < right[1]) {

            result[0] = 1; // Is BST
            result[1] = Math.min(left[1], root.val); // Min value
            result[2] = Math.max(right[2], root.val); // Max value
            result[3] = left[3] + right[3] + 1; // Size
        } else {
            result[0] = 0; // Not a BST
            // Other values don't matter
        }

        return result;
    }

    public TreeProperties calculateProperties(TreeNode root) {
        if (root == null) {
            TreeProperties props = new TreeProperties();
            props.height = -1;
            return props;
        }

        // Get properties from left and right subtrees
        TreeProperties left = calculateProperties(root.left);
        TreeProperties right = calculateProperties(root.right);

        // Postorder position: Combine properties
        TreeProperties props = new TreeProperties();
        props.height = Math.max(left.height, right.height) + 1;
        props.nodeCount = left.nodeCount + right.nodeCount + 1;
        props.leafCount = (root.left == null && root.right == null) ? 1 :
                left.leafCount + right.leafCount;
        props.sum = left.sum + right.sum + root.val;

        return props;
    }

    /**
     * Utility method to create a test tree
     */
    private TreeNode createTestTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
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

    /**
     * Pattern 2: Multi-value returns for complex tree properties
     * Example: Check if a binary tree is balanced and calculate height
     */
    // Custom class to hold multiple return values
    private static class TreeInfo {
        boolean isBalanced;
        int height;

        TreeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    /**
     * Pattern 4: Optimizing recursive calls to avoid redundancy
     * Example: Calculate various properties of a tree in one pass
     */
    public static class TreeProperties {
        int height;
        int nodeCount;
        int leafCount;
        int sum;

        @Override
        public String toString() {
            return "Height: " + height + ", Nodes: " + nodeCount +
                    ", Leaves: " + leafCount + ", Sum: " + sum;
        }
    }
}
